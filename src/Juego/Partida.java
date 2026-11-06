package Juego;

public class Partida {

    private final static int cantidadJugadores = 4;
    private final static int cartasPorJugador = 3;
    private final static int cantidadRondas = 3;

    private Queue<Jugador> turnoJugadores;
    private Mazo mazo;
    private Jugador[] jugadores; // Arreglo para manejar a los jugadores al final

    public Partida(Jugador j1, Jugador j2, Jugador j3, Jugador j4, Mazo mazo) {
        this.mazo = mazo;
        this.jugadores = new Jugador[]{j1, j2, j3, j4};
        this.turnoJugadores = new Queue<>(cantidadJugadores);
 
        for (int i = 0; i < cantidadJugadores; i++) {
            this.turnoJugadores.offer(this.jugadores[i]);
        }
    }

    public void iniciarJuego() {
        // Un jugador puede jugar varias partidas, cada una arranca con sus cartas ganadas en cero
        for (int i = 0; i < cantidadJugadores; i++) {
            jugadores[i].reiniciarCartasGanadas();
        }
        int numeroRonda = 1;
        int cartasPorRonda = cantidadJugadores * cartasPorJugador;

        // El juego termina tras jugar tres rondas (o antes, si el mazo no alcanza para otra ronda completa)
        while (numeroRonda <= cantidadRondas && mazo.cartasRestantes() >= cartasPorRonda) {
            System.out.println("\n--- Ronda " + numeroRonda + " ---");
            jugarRonda();
            numeroRonda++;
        }
        determinarGanadorFinal();
    }

    private void jugarRonda() {
        // ARREGLOS obligatorios para controlar las cartas tiradas y quién las tiró
        // mesaCartas[i] es la mano de tres cartas del jugador mesaJugadores[i]
        Carta[][] mesaCartas = new Carta[cantidadJugadores][cartasPorJugador];
        Jugador[] mesaJugadores = new Jugador[cantidadJugadores];
        int[] puntajesRonda = new int[cantidadJugadores];

        // 1. Cada jugador, en su turno, saca tres cartas
        for (int i = 0; i < cantidadJugadores; i++) {
            Jugador jugadorActual = turnoJugadores.pool();
            mesaJugadores[i] = jugadorActual;
            for (int c = 0; c < cartasPorJugador; c++) {
                mesaCartas[i][c] = mazo.agarrarCarta();
            }
            puntajesRonda[i] = calcularPuntajeRonda(mesaCartas[i]);
            System.out.println(jugadorActual.getNombre() + " saca: " + describirMano(mesaCartas[i]) + " -> " + puntajesRonda[i] + " puntos" + (tieneAs(mesaCartas[i]) ? " (as: puntaje duplicado)" : ""));
            // El jugador vuelve al final de la cola
            turnoJugadores.offer(jugadorActual);
        }

        // 2. Se busca el puntaje maximo y cuantos jugadores lo alcanzan
        int puntajeMaximo = -1;
        int cantidadConMaximo = 0;
        Jugador ganadorRonda = null;
        for (int i = 0; i < cantidadJugadores; i++) {
            if (puntajesRonda[i] > puntajeMaximo) {
                puntajeMaximo = puntajesRonda[i];
                cantidadConMaximo = 1;
                ganadorRonda = mesaJugadores[i];
            } else if (puntajesRonda[i] == puntajeMaximo) {
                cantidadConMaximo++;
            }
        }

        // 3. Resolución de la ronda
        if (cantidadConMaximo > 1) {
            System.out.println("-> ¡Empate con " + puntajeMaximo + " puntos! Cada jugador conserva sus cartas.");
            for (int i = 0; i < cantidadJugadores; i++) {
                for (int c = 0; c < cartasPorJugador; c++) {
                    mesaJugadores[i].agregarCartaGanada(mesaCartas[i][c]);
                }
            }
        } else {
            System.out.println("-> " + ganadorRonda.getNombre() + " gana la ronda con " + puntajeMaximo + " puntos.");
            for (int i = 0; i < cantidadJugadores; i++) {
                for (int c = 0; c < cartasPorJugador; c++) {
                    ganadorRonda.agregarCartaGanada(mesaCartas[i][c]);
                }
            }
        }
    }
    
    // Puntaje de una mano en una ronda: suma de los valores de las cartas (el comodin vale 100).
    // Si la mano tiene al menos un as, el total se duplica una sola vez (la regla no es acumulativa).
    static int calcularPuntajeRonda(Carta[] mano) {
        int suma = 0;
        for (Carta carta : mano) {
            suma += carta.getValor();
        }
        return tieneAs(mano) ? suma * 2 : suma;
    }
 
    static boolean tieneAs(Carta[] mano) {
        for (Carta carta : mano) {
            if (carta.esAs()) {
                return true;
            }
        }
        return false;
    }
 
    private static String describirMano(Carta[] mano) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < mano.length; c++) {
            if (c > 0) {
                sb.append(", ");
            }
            sb.append(mano[c].descripcion());
        }
        return sb.toString();
    }

    private void determinarGanadorFinal() {
        System.out.println("\n=== FIN DEL JUEGO ===");
        int puntajeMaximo = -1;
        int[] puntajesFinales = new int[4];
        for (int i = 0; i < 4; i++) {
            puntajesFinales[i] = jugadores[i].calcularPuntaje();
            System.out.println("Puntaje de " + jugadores[i].getNombreCompleto() + ": " + puntajesFinales[i]);
            if (puntajesFinales[i] > puntajeMaximo) {
                puntajeMaximo = puntajesFinales[i];
            }
        }
        System.out.print("\n🏆 Ganador(es): ");
        for (int i = 0; i < 4; i++) {
            if (puntajesFinales[i] == puntajeMaximo) {
                System.out.print(jugadores[i].getNombreCompleto() + "  ");
            }
        }
        System.out.println("\n");
    }
}