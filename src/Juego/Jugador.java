package Juego;

public class Jugador {
	
	private String dni;
    private int numeroJugador;
	private String nombre;
	private String apellido;
	private int edad;
	private int puntajeMaximo;
	private Queue<Carta> cartasGanadas;
	
	public Jugador(String dni, int numeroJugador, String nombre, String apellido, int edad) {
        this.dni = dni;
        this.numeroJugador = numeroJugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.puntajeMaximo = 0;
        this.cartasGanadas = new Queue<Carta>(52);
    }

    public String getDni() {
        return dni;
    }

    public int getNumeroJugador() {
        return numeroJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }
    
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void actualizarPuntajeMaximo(int nuevoPuntaje) {
        if (nuevoPuntaje > this.puntajeMaximo) {
            this.puntajeMaximo = nuevoPuntaje;
        }
    }
	
	public void agregarCartaGanada(Carta carta) {
		cartasGanadas.offer(carta);
	}
	
	// Se llama al empezar cada partida: el puntaje de una partida no debe arrastrar las cartas de las anteriores
	public void reiniciarCartasGanadas() {
		this.cartasGanadas = new Queue<Carta>(52);
	}

	public int calcularPuntaje() {
	    int puntaje = 0;
	    Queue<Carta> aux = new Queue<>(52);
	    
	    while (!cartasGanadas.isEmpty()) {
	        Carta c = cartasGanadas.pool();
	        if (c != null) {
	            puntaje += c.getValor();
	            aux.offer(c);
	        }
	    }
	    
	    while (!aux.isEmpty()) {
	        Carta cAux = aux.pool();
	        if (cAux != null) {
	            cartasGanadas.offer(cAux);
	        }
	    }
	    return puntaje;
	}
	
	
	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}

	@Override
    public String toString() {
        return "[#" + numeroJugador + "] " + getNombreCompleto() + " (DNI: " + dni + ") - Máx Puntaje: " + puntajeMaximo;
    }
	
}
