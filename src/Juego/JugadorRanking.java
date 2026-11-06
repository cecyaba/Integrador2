package Juego;

public class JugadorRanking implements Comparable<JugadorRanking> {
    
    private Jugador jugador;

    public JugadorRanking(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    @Override
    public int compareTo(JugadorRanking otro) {
        // Ordenamiento por puntaje máximo acumulado de mayor a menor
        if (this.jugador.getPuntajeMaximo() != otro.jugador.getPuntajeMaximo()) {
            return Integer.compare(this.jugador.getPuntajeMaximo(), otro.jugador.getPuntajeMaximo());
        }
        // Desempate por número de jugador único
        return Integer.compare(this.jugador.getNumeroJugador(), otro.jugador.getNumeroJugador());
    }

    @Override
    public String toString() {
        return jugador.toString();
    }
}

