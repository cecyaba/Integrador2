package Juego;
import java.util.Random;

public class Mazo {

	private final static int cartasPorPalo = 13;
	private final static int cantidadComodines = 2;
	// 4 palos x 13 cartas + 2 comodines = 54
	private final static int totalCartas = 4 * cartasPorPalo + cantidadComodines;

	private StackGenerica<Carta> mazoPila;
	
	public Mazo() {
		mazoPila = new StackGenerica<Carta>();
		inicializarMazo();
	}
	
	public void inicializarMazo() {
		String[] palos = {"Corazon" ,"Diamante" , "Trébol" ,"Pica"};
		Carta[] baraja = new Carta[totalCartas];
		int contador = 0;
		
		//generar 52 cartas
		for (String palo : palos) {
			for (int valor = 1; valor <= cartasPorPalo; valor++)  {
				baraja[contador] = new Carta(palo,valor);
				contador++;
			}
		}
		
		//agregar los dos comodines
		for (int i = 0; i < cantidadComodines; i++) {
			baraja[contador] = new Carta(Carta.paloComodin, Carta.valorComodin);
			contador++;
		}

		//mezclar las cartas
		Random random = new Random();
		for(int i = baraja.length - 1; i>0; i--) {
			int j = random.nextInt(i + 1);
			
			//intercambiar posiciones
			Carta temp = baraja[i];
			baraja[i] = baraja[j];
			baraja[j] = temp;	
		}
		
		//agregar cartas al maazo (en la pila)
		for (Carta carta : baraja) {
			mazoPila.push(carta);	
		}	
	}
	
	public Carta agarrarCarta() {
		if (mazoPila.isEmpty()) 
			return null;
		Carta c = mazoPila.pop();
		c.setDisponible(false);
		return c;	
	}
	
	public boolean tieneCartas() {
		return !mazoPila.isEmpty();
	}

	// Cantidad de cartas que todavía se pueden repartir
	public int cartasRestantes() {
		return mazoPila.count();
	}

}
