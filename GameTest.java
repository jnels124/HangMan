import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * This Test class will test the public methods inside
 * the Game class that return a value.
 *
 * @author  (Jesse Nelson)
 * @version (September 19, 2012 : Windows Vista(x32) Java 1.7 )
 */
public class GameTest {
    Game G;
    Game G2;
    String word;
    int guesses;
    
    @Before
    public void setup () {
        G = new Game(15, 15, "personification");
        G2 = new Game(4, 8, null);
        word = "";
        guesses = 0;
    }

    @Test
    public void checkGuessTest() {
        word = G.secretWord;
        for(int i = 0; i < 14; i++) {
            guesses = G.numGuesses;
            assertFalse("This is not a valid character " + i, G.checkGuess((char)(i))); 
            G.decreaseGuessesLeft((char)(i)); // Cant call normal method to do this as it interacts with User
            G.guesses.add((char)(i)); // Cant call normal method to do this as it interacts with User
            assertTrue("The set of guesses should now contain all of i's values",
                         G.guesses.contains((char)(i)));
            assertTrue("The guesses left are incorrect!", G.numGuesses == guesses - 1 );
        }
        
        for(int i = 0; i < word.length(); i++ ) {
           assertTrue("These are all part of the secretWord " + word.charAt(i), 
                        G.checkGuess(word.charAt(i)));
           assertTrue("All letters are correct and you should still have one guess remaining",
                        G.numGuesses == 1);
        }
        
        // G2
        word = G2.secretWord;
        for(int i = 0; i < 3; i++) {
            guesses = G2.numGuesses;
            assertFalse("This is not a valid character " + i, G2.checkGuess((char)(i))); 
            G2.decreaseGuessesLeft((char)(i)); // Cant call normal method to do this as it interacts with User
            G2.guesses.add((char)(i)); // Cant call normal method to do this as it interacts with User
            assertTrue("The set of guesses should now contain all of i's values",
                         G2.guesses.contains((char)(i)));
            assertTrue("The guesses left are incorrect!", G2.numGuesses == guesses - 1 );
        }
        
        for(int i = 0; i < word.length(); i++ ) {
           assertTrue("These are all part of the secretWord " + word.charAt(i), 
                        G2.checkGuess(word.charAt(i)));
           assertTrue("All letters are correct and you should still have one guess remaining",
                        G2.numGuesses == 1);
        }        
    }
    
    @Test
    public void gameOverTest() {        
        assertFalse("Game just began and shouldn't be over!", G.gameOver());
        assertEquals(15, G.secretWord.length());
        assertEquals(15, G.numGuesses);
        G.updateClue('p');
        G.updateClue('e');
        G.updateClue('r');
        G.updateClue('s');
        G.updateClue('o');
        G.updateClue('n');
        assertFalse("Game should not be over!", G.gameOver());
        assertFalse("The clue and secret word should be different",
                   Arrays.equals(G.secretWord.toCharArray(), G.clue));
        assertEquals(15, G.numGuesses);
        
        G.decreaseGuessesLeft('m');
        G.decreaseGuessesLeft('u');
        G.decreaseGuessesLeft('w');
        G.decreaseGuessesLeft('q');
        G.decreaseGuessesLeft('z');
        G.decreaseGuessesLeft('x');
        G.decreaseGuessesLeft('y');
        G.decreaseGuessesLeft('l');
        G.decreaseGuessesLeft('t');
        G.decreaseGuessesLeft('d');
        G.decreaseGuessesLeft('j');
        assertEquals(4, G.numGuesses);
        assertFalse("The clue and secret word should be different",
                   Arrays.equals(G.secretWord.toCharArray(), G.clue));
        assertFalse("Game should not be over!", G.gameOver());
        
        G.updateClue('i');
        G.updateClue('f');
        G.updateClue('c');
        G.updateClue('a');
        assertEquals(4, G.numGuesses);
        assertFalse("Game should not be over!", G.gameOver());
        assertFalse("The clue and secret word should be different",
                   Arrays.equals(G.secretWord.toCharArray(), G.clue));
        
        G.decreaseGuessesLeft('k');
        G.decreaseGuessesLeft('h');
        G.decreaseGuessesLeft('b');
        assertEquals(1, G.numGuesses);
        assertFalse("Game should not be over!", G.gameOver());
        assertFalse("The clue and secret word should be different",
                   Arrays.equals(G.secretWord.toCharArray(), G.clue));
                   
        G.updateClue('t');
        assertEquals(1, G.numGuesses);
        assertTrue("Game should be over!", G.gameOver());
        assertTrue("The clue and secret word should contain the same letters",
                   Arrays.equals(G.secretWord.toCharArray(), G.clue));
                 
        //G2
        assertFalse("Game just began and shouldn't be over!", G2.gameOver());
        assertEquals(8, G2.secretWord.length());
        assertEquals(4, G2.numGuesses);
        
        G2.decreaseGuessesLeft('b');
        assertEquals(3, G2.numGuesses);
        assertFalse("Game  shouldn't be over!", G2.gameOver());
        
        G2.decreaseGuessesLeft('x');
        assertEquals(2, G2.numGuesses);
        assertFalse("Game just began and shouldn't be over!", G2.gameOver());
        
        G2.decreaseGuessesLeft('z');
        assertEquals(1, G2.numGuesses);
        assertFalse("Game just began and shouldn't be over!", G2.gameOver());
        
        G2.decreaseGuessesLeft('p');
        assertEquals(0, G2.numGuesses);
        assertTrue("Game should be over! There are 0 guesses left", G2.gameOver());
        assertFalse("The clue and secret word should be different",
                   Arrays.equals(G2.secretWord.toCharArray(), G2.clue));  
    }
}
