[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=18667648)
### Esercizio basato su un gioco di **Maze Escape (Fuga dal Labirinto)**.  

### **Obiettivi dell'esercizio**
1. **Usare le eccezioni** per gestire:
   - Input non validi (InputMismatchException).
   - Tentativi di movimento fuori dal labirinto (OutOfBoundsException).
   - Collisioni con muri (WallCollisionException).
2. **Lavorare con matrici**, simulando una griglia 5x5.
3. **Gestire il movimento del giocatore** all'interno del labirinto.
4. **Utilizzare una classe dedicata per le eccezioni personalizzate.**

---

### **Regole del gioco**
- Il giocatore parte dalla posizione `(0,0)`.
- Deve raggiungere l’uscita alla posizione `(4,4)`.
- Il labirinto è una matrice 5x5 con muri (`#`), spazio libero (`.`) e il giocatore (`P`).
- Il giocatore si muove con `W (su), A (sinistra), S (giù), D (destra)`.
- Se prova a muoversi fuori dalla griglia o contro un muro, viene lanciata un'eccezione.

---

### **Funzionamento del gioco**
1. **La griglia**:
   - Il giocatore inizia in `(0,0)`, rappresentato con `'P'`.
   - L'uscita è alla posizione `(4,4)`, indicata da `'E'`.
   - I muri (`'#'`) bloccano il passaggio.
   - Gli spazi vuoti (`'.'`) sono percorribili.

2. **Movimento**:
   - L'utente può inserire `W, A, S, D` per muoversi.
   - Se prova a uscire dai confini, si attiva un'`OutOfBoundsException`.
   - Se prova a camminare su un muro, si attiva una `WallCollisionException`.

3. **Stampa del labirinto**:
   - Dopo ogni mossa, il labirinto viene aggiornato e stampato.

4. **Vittoria**:
   - Il gioco termina quando il giocatore raggiunge `'E'`.

---

### **Estensioni possibili**
- **Aggiungere trappole (`'T'`)**: Se il giocatore finisce su una trappola, perde e il gioco termina.
- **Contare i movimenti**: Mostrare il numero di passi impiegati per trovare l'uscita.
- **Generare il labirinto in modo casuale**: Creare una versione più dinamica con un generatore casuale.

---

Ecco la **struttura del codice** per l'esercizio, lasciando degli spazi da completare. Gli studenti dovranno implementare la gestione delle eccezioni, il movimento del giocatore e la logica di controllo.  

---

```java
import java.util.Scanner;

// Eccezione personalizzata per movimenti fuori dai limiti
class OutOfBoundsException extends Exception {
    public OutOfBoundsException(String message) {
        super(message);
    }
}

// Eccezione personalizzata per collisione con muri
class WallCollisionException extends Exception {
    public WallCollisionException(String message) {
        super(message);
    }
}

public class MazeEscape {
    // Dichiarazione della matrice del labirinto
    private static final char[][] LABIRINTO = {
        { 'P', '.', '#', '.', '.' },
        { '#', '.', '#', '.', '#' },
        { '.', '.', '.', '#', '.' },
        { '#', '#', '.', '.', '.' },
        { '#', '.', '#', '#', 'E' }
    };

    // Coordinate iniziali del giocatore
    private static int playerX = 0;
    private static int playerY = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean escaped = false;

        System.out.println("Benvenuto in Maze Escape! Trova l'uscita ('E').");

        while (!escaped) {
            printMaze();
            System.out.print("Muoviti (W = su, A = sinistra, S = giù, D = destra): ");
            char move = scanner.next().toUpperCase().charAt(0);

            try {
                // Chiamare il metodo per muovere il giocatore
                // Verificare se ha raggiunto l'uscita e terminare il gioco
            } catch (OutOfBoundsException | WallCollisionException e) {
                // Stampare il messaggio di errore dell'eccezione
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
        
        // Switch-case per aggiornare le nuove coordinate in base alla direzione
        
        // Controllare se il movimento è fuori dalla matrice e lanciare OutOfBoundsException
        
        // Controllare se il movimento porta su un muro e lanciare WallCollisionException
        
        // Aggiornare la matrice con la nuova posizione del giocatore
    }

    /**
     * Metodo per stampare il labirinto attuale
     */
    private static void printMaze() {
        // Stampare la matrice riga per riga
    }
}
```

---

### **Cosa devono fare gli studenti?**
✅ **Completare il metodo `movePlayer()`**:  
   - Usare un `switch-case` per aggiornare le coordinate in base alla direzione (`W, A, S, D`).  
   - Controllare se il nuovo movimento è **fuori dai limiti** e lanciare `OutOfBoundsException`.  
   - Controllare se il nuovo movimento colpisce un **muro** e lanciare `WallCollisionException`.  
   - Aggiornare la posizione del giocatore nella matrice.  

✅ **Gestire le eccezioni in `main()`**:  
   - Stampare il messaggio di errore quando viene catturata un'eccezione.  

✅ **Implementare il metodo `printMaze()`**:  
   - Stampare la matrice riga per riga per mostrare la posizione del giocatore.  

---

### **Possibili Varianti**
- **Aggiungere un contatore di mosse**, per valutare chi impiega meno tentativi.  
- **Introdurre trappole (`'T'`)**, che terminano il gioco se il giocatore ci passa sopra.  
- **Aggiungere una modalità multigiocatore**, con due giocatori che cercano l'uscita.  

---

**Obiettivo dell'esercizio:** Sviluppare **logica di programmazione**, gestione delle **eccezioni personalizzate**, utilizzo di **matrici** e gestione dell'**input utente**.  

Buon lavoro! 🎲
