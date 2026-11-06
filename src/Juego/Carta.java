package Juego;

public class Carta {
	
	public final static String paloComodin = "Comodin";
	public final static int valorComodin = 100;
	public final static int valorAs = 1;

	private String palo;
	private int valor;
	private boolean disponible;
	
	public Carta (String palo, int valor) {
		this.palo = palo;
		this.valor = valor;
		this.disponible = true;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public boolean esComodin() {
		return paloComodin.equals(palo);
	}
 
	// El as vale 1 y es de cualquier palo; el comodín nunca cuenta como as
	public boolean esAs() {
		return !esComodin() && valor == valorAs;
	}
 
	// Texto corto para mostrar por consola: "12 de Pica" o "Comodin (100)"
	public String descripcion() {
		if (esComodin()) {
			return "Comodin (" + valor + ")";
		}
		return valor + " de " + palo;
	}

	@Override
	public String toString() {
		return "Carta [palo=" + palo + ", valor=" + valor + ", disponible=" + disponible + "]";
	}
	
}
