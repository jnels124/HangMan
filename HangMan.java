import java.io.FileNotFoundException;
import java.util.SortedSet;
/**
 * This Interface defines the methods that will be used to 
 * gather information from the User to set up the rules of 
 * the HangMan Game
 * 
 * @author (Jesse Nelson) 
 * @version (September 18, 2012 : Windows Vista(x32)
 */
public interface HangMan {
    /**
     * Will set up the dictionary to be used for the game
     * 
     * @return Returns the dictionary to the Game
     */
    public Dictionary dictionarySetUp() throws FileNotFoundException; 
    
    /**
     * Gets the number of guesses 
     * 
     * @return Number of guesses User wants to allow
     */
    public int numberOfGuesses();
    
    /**
     * Gets the length of the word for the game
     * 
     * @return Length of word to be used for game
     */
    public int wordLength();
    

    /**
     * Checks to see if the user wants to play again  
     * 
     */
    public void playAgain(); 
    
    /**
     * Shows the current game status to the User
     * 
     * @args Info needed to show the game 
     */ 
    public char showGame(String message, Game g);
    
    /**
     * Notifies User the game was won
     */
    public void gameWon(Game g);
    
    /**
     * Notifies User the game was lost
     */
    public void gameLost(String secretWord, Game g);
}
