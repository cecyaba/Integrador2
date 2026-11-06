package Juego;

public class Principal {
	public static void main(String[] args) {
		
        GestorEvento gestor = new GestorEvento();

        System.out.println("--- 1. INSCRIPCIÓN DINÁMICA DE ESTUDIANTES ---");
        gestor.inscribirJugador("44000111", 101, "Lucas", "Velazquez", 21);
        gestor.inscribirJugador("43000222", 102, "Ludmila", "Gomez", 22);
        gestor.inscribirJugador("45000333", 103, "Zoe", "Contreras", 20);
        gestor.inscribirJugador("42000444", 104, "Ivan", "Zumbay", 23);
        gestor.inscribirJugador("41000555", 105, "Sofia", "Lopez", 19);

        // Intento de inscripción con número de jugador duplicado
        gestor.inscribirJugador("46000666", 101, "Carlos", "Perez", 20);

        System.out.println("\n--- 2. RANKING INICIAL (PUNTAJES EN 0) ---");
        gestor.mostrarRanking();

        System.out.println("\n--- 3. EJECUCIÓN DE PRIMERA PARTIDA ---");
        gestor.iniciarNuevaPartida();

        System.out.println("\n--- 4. RANKING ACTUALIZADO TRAS PARTIDA 1 ---");
        gestor.mostrarRanking();

        System.out.println("\n--- 5. EJECUCIÓN DE SEGUNDA PARTIDA ---");
        gestor.iniciarNuevaPartida();

        System.out.println("\n--- 6. RANKING ACTUALIZADO TRAS PARTIDA 2 ---");
        gestor.mostrarRanking();
    }
}
