package org.example;
import java.util.*;

//Eccezione personalizzata per movimenti fuori dai limiti
class OutOfBoundsException extends Exception {
	public OutOfBoundsException(String message) {
		super(message);
	}
}

//Eccezione personalizzata per collisione con muri
class WallCollisionException extends Exception {
	public WallCollisionException(String message) {
		super(message);
	}
}

public class MazeEscape {
	static Random rand = new Random();

	// Dimensione del labirinto
	private static final int d = rand.nextInt(10)+5;

	// Direzioni di movimento (su, giù, sinistra, destra)
	private static final int[] DIREZIONI_X = {-1, 1, 0, 0};
	private static final int[] DIREZIONI_Y = {0, 0, -1, 1};

	// Dichiarazione della matrice del labirinto
	private static final char[][] LABIRINTO =generaLabirinto();

	// Coordinate iniziali del giocatore
	private static int playerX = 0;
	private static int playerY = 0;
	private static boolean trappola = false;


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean escaped = false;

		System.out.println("Benvenuto in Maze Escape! Trova l'uscita ('E').");
		mettiTrappola();

		while (!escaped) {
			printMaze();
			System.out.print("Muoviti (W = su, A = sinistra, S = giù, D = destra): ");
			char move = scanner.next().toUpperCase().charAt(0);

			try {
				// Chiamare il metodo per muovere il giocatore
				movePlayer(move);
				// Verificare se ha raggiunto l'uscita e terminare il gioco
				if(playerX==4&&playerY==4) {
					escaped=true;
					System.out.println("hai vinto");
					printMaze();
				}
				if(trappola) {
					escaped=true;
					System.out.println("hai perso");
					printMaze();
				}
			} catch (OutOfBoundsException | WallCollisionException e) {
				// Stampare il messaggio di errore dell'eccezione
				System.out.println("Eccezione catturata: " + e.getMessage());
			}
		}

		scanner.close();
	}

	/**
	 * Metodo per spostare il giocatore all'interno del labirinto
	 * Deve controllare:
	 * - Se il movimento è fuori dai limiti → lancia OutOfBoundsException
	 * - Se il movimento porta su un muro ('#') → lancia WallCollisionException
	 * - Se il movimento è valido, aggiornare la posizione
	 */
	private static void movePlayer(char direction) throws OutOfBoundsException, WallCollisionException {
		// Dichiarare nuove variabili per la posizione dopo il movimento
		int newX = playerX;
		int newY = playerY;
		// Switch-case per aggiornare le nuove coordinate in base alla direzione
		switch (direction) {
		case 'W':
			newX--;
			break;
		case 'A':
			newY--;
			break;
		case 'S':
			newX++;
			break;
		case 'D':
			newY++;
			break;
		}
		// Controllare se il movimento è fuori dalla matrice e lanciare OutOfBoundsException
		if(newX<0||newX>5||newY<0||newY>5) {
			throw new OutOfBoundsException("stai uscendo dai bordi");
		}else if(LABIRINTO[newX][newY]=='#') {// Controllare se il movimento porta su un muro e lanciare WallCollisionException
			throw new WallCollisionException("stai andando sul muro");
		}
		if (LABIRINTO[newX][newY]=='T'){
			trappola=true;
		}

		// Aggiornare la matrice con la nuova posizione del giocatore
		LABIRINTO[newX][newY]='P';
		LABIRINTO[playerX][playerY]='.';
		playerX=newX;
		playerY=newY;
	}

	/**
	 * Metodo per stampare il labirinto attuale
	 */
	private static void mettiTrappola() {
		int I = rand.nextInt(LABIRINTO.length-2)+1;
		int J = rand.nextInt(LABIRINTO[0].length-1);
		LABIRINTO[I][J]='T';
	}

	private static void printMaze() {
		// Stampare la matrice riga per riga
		for(int i = 0; i < LABIRINTO.length; i++){
			for(int j = 0; j < LABIRINTO[0].length; j++)
				System.out.print(LABIRINTO[i][j]);
			System.out.println();		
		}

	}

	// Genera un labirinto casuale con P e E fissi negli angoli
	private static char[][] generaLabirinto() {
		char[][] labirinto = new char[d][d];

		// Inizializza il labirinto con muri
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				labirinto[i][j] = '#';  // Muro
			}
		}

		// Imposta il punto di partenza 'P' e di arrivo 'E'
		labirinto[0][0] = 'P';  // Punto di partenza in alto a sinistra
		labirinto[d - 1][d - 1] = 'E';  // Punto di arrivo in basso a destra

		// Crea il percorso tra 'P' ed 'E'
		creaPercorso(labirinto, 0, 1);  // Partenza da (0, 0)

		// I punti accanto all'uscita sono liberi
		labirinto[d - 1][d - 2] = '.';  
		labirinto[d - 2][d - 1] = '.';

		return labirinto;
	}


	// Metodo per creare un percorso valido (DFS)
	private static void creaPercorso(char[][] labirinto, int x, int y) {

		// Definisce la direzione di movimento
		labirinto[x][y] = '.';  // Imposta la posizione come spazio libero

		// Mescola le direzioni per garantire un percorso casuale
		Integer[] direzioni = {0, 1, 2, 3};
		for (int i = 0; i < direzioni.length; i++) {
			int dir = direzioni[rand.nextInt(direzioni.length)];
			int newX = x + DIREZIONI_X[dir] * 2;
			int newY = y + DIREZIONI_Y[dir] * 2;

			if (isDentroLabirinto(newX, newY) && labirinto[newX][newY] == '#') {
				// Crea un passaggio tra la cella attuale e la nuova cella
				labirinto[x + DIREZIONI_X[dir]][y + DIREZIONI_Y[dir]] = '.';
				creaPercorso(labirinto, newX, newY);  // Chiamata ricorsiva
			}
		}
	}

	private static boolean isDentroLabirinto(int x, int y) {
		return x >= 0 && x < d && y >= 0 && y < d;
	}
}