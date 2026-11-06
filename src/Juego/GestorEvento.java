package Juego;

import java.util.Random;

public class GestorEvento {
   private SimpleLinkedList<Jugador> jugadoresInscriptos;

    public GestorEvento() {
        this.jugadoresInscriptos = new SimpleLinkedList<Jugador>();
    }

    // 1. Isncripción de jugadores
    public boolean inscribirJugador(String dni, int numeroJugador, String nombre, String apellido, int edad) {
        for (Jugador j : jugadoresInscriptos) {
            if (j.getNumeroJugador() == numeroJugador) {
                System.out.println("❌ Error: El número de jugador " + numeroJugador + " ya está asignado.");
                return false;
            }
            if (j.getDni().equalsIgnoreCase(dni)) {
                System.out.println("❌ Error: El estudiante con DNI " + dni + " ya se encuentra inscripto.");
                return false;
            }
        }

        Jugador nuevo = new Jugador(dni, numeroJugador, nombre, apellido, edad);
        jugadoresInscriptos.addLast(nuevo);
        System.out.println("✅ Inscripción exitosa: " + nuevo.getNombreCompleto() + " con Número de Jugador #" + numeroJugador);
        return true;
    }

    // 2. Selección de 4 jugadores y inicio del juego
    public void iniciarNuevaPartida() {
        if (jugadoresInscriptos.size() < 4) {
            System.out.println("❌ No hay suficientes jugadores inscriptos para iniciar una partida (Mínimo 4).");
            return;
        }

        Jugador[] seleccionados = new Jugador[4];
        Random random = new Random();
        int seleccionadosCount = 0;

        while (seleccionadosCount < 4) {
            int indiceAleatorio = random.nextInt(jugadoresInscriptos.size());
            Jugador candidato = obtenerJugadorPorIndice(indiceAleatorio);

            boolean yaSeleccionado = false;
            for (int i = 0; i < seleccionadosCount; i++) {
                if (seleccionados[i].getNumeroJugador() == candidato.getNumeroJugador()) {
                    yaSeleccionado = true;
                    break;
                }
            }

            if (!yaSeleccionado) {
                seleccionados[seleccionadosCount] = candidato;
                seleccionadosCount++;
            }
        }

        System.out.println("\n=========================================");
        System.out.println("🎮 INICIANDO NUEVA PARTIDA CON:");
        for (Jugador j : seleccionados) {
            System.out.println("- " + j.getNombreCompleto() + " (#" + j.getNumeroJugador() + ")");
        }
        System.out.println("=========================================");

        Mazo mazo = new Mazo();
        Partida partida = new Partida(seleccionados[0], seleccionados[1], seleccionados[2], seleccionados[3], mazo);
        partida.iniciarJuego();
    }

    private Jugador obtenerJugadorPorIndice(int index) {
        int i = 0;
        for (Jugador j : jugadoresInscriptos) {
            if (i == index) {
                return j;
            }
            i++;
        }
        return null;
    }

    // 3. Ranking de jugadores basado en puntaje máximo
    public void mostrarRanking() {
        if (jugadoresInscriptos.size() == 0) {
            System.out.println("No hay jugadores inscriptos para generar el ranking.");
            return;
        }

        BinarySearchTree<JugadorRanking> arbolRanking = new BinarySearchTree<>();

        for (Jugador j : jugadoresInscriptos) {
            arbolRanking.add(new JugadorRanking(j));
        }

        System.out.println("\n🏆 ==========================================");
        System.out.println("🏆 RANKING ACTUAL DE JUGADORES (MAYOR A MENOR)");
        System.out.println("🏆 ==========================================");
        
        // Se utiliza el recorrido descendente con formato de líneas individuales
        arbolRanking.imprimirRankingDescendente();
        
        System.out.println("============================================\n");
    }
}