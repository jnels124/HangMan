import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import javax.swing.JOptionPane;
import java.util.*;
/**
 * This class controls the aspects of the game by requesting info from a User, 
 * updating the game, and then passing the updated game back to the User
 * 
 * Variables will be declared as protected after testing is complete
 * 
 * @author (Jesse Nelson) 
 * @version (October 4, 2012 Windows 7(x64) : Java 1.7)
 */
public class Game {

    /**
     * Minimum number of guesses user is allowed to have
     */
    static final int MIN_NUMBER_GUESSES = 1;
    
    /**
     * Maximum number of guesses user is allowed to have
     */
    static final int MAX_NUMBER_GUESSES = 26;
    
    Dictionary d;
    int numGuesses;
    String secretWord;
    List<String> candidates;
    SortedSet<Character> guesses;
    char [] clue;
    protected Random rand;
    
    /**
     * Initialize instance variables and get first guess from user
     * 
     * Instance variables will be made private once testing is complete
     * 
     * @throws FileNotFoundException if the Dictionary file is not located
     */
    public Game(HangMan interfaceType) {
       try {
           this.d = interfaceType.dictionarySetUp();
           this.secretWord = this.d.randWord(interfaceType.wordLength());
           this.numGuesses = interfaceType.numberOfGuesses();
           this.guesses = new TreeSet<Character>();
           this.clue = new char [this.secretWord.length()];
           for(int i = 0; i <  clue.length; i++) {
               this.clue[i] = '_';   
           }
           updateGame(interfaceType.showGame("Welcome to HangMan!", this), interfaceType);   
       } catch(FileNotFoundException e){  
            System.out.println("There was an error loading the default and/or selected Dictionary");                     
          }
          /* Should never happen!*/
         catch(NullPointerException e ){
            JOptionPane.showMessageDialog(null, "There is critical information missing from the game.\n" 
                                          + "The game is restarting.", "ERROR", JOptionPane.ERROR_MESSAGE); 
         }
    }
    public Game(){
    
    }
    /**
     * Constructer to used for testing 
     */
    public Game(int guessesAllowed, int wordLength, String word) {
        try {
            this.d = new Dictionary();
        if(word == null) {
            this.secretWord = d.randWord(wordLength);  
        } else secretWord = word;
            this.numGuesses = guessesAllowed;
            this.guesses = new TreeSet<Character>();
            this.clue = new char [this.secretWord.length()];
            for(int i = 0; i <  clue.length; i++) {
               clue[i] = '_';   
            }
        } catch(FileNotFoundException e) {
            System.out.println("There was an error loading the default and/or selected Dictionary");      
          }
    }
    
    public Game(Dictionary dictionary, HangMan interfaceType) { // Used by EvilHangMan
       try {
           this.rand = new Random(); 
           this.d = dictionary;
           this.candidates = d.allWordsOfLength(interfaceType.wordLength());
           this.numGuesses = interfaceType.numberOfGuesses();
           this.secretWord = candidates.get(rand.nextInt(candidates.size()));
           this.guesses = new TreeSet<Character>();
           this.clue = new char [secretWord.length()];
           for(int i = 0; i <  clue.length; i++) {
               this.clue[i] = '_';   
           }
           /* Should never happen!*/
       } catch(NullPointerException e ){
            JOptionPane.showMessageDialog(null, "There is critical information missing from the game.\n" 
                                          + "The game is restarting.", "ERROR", JOptionPane.ERROR_MESSAGE); 
         }    
    }

    /**
     * Checks if current guess is part of the secretWord and updates the game accordingly
     * 
     * @param Current guess
     */
    public void updateGame(char c, HangMan interfaceType) {
        if(checkGuess(c)) {
            this.rightGuess(c, interfaceType);
        }
        else {
            this.wrongGuess(c, interfaceType);
        } 
    }
    
    /**
     * Checks to see if the User's guess is part of the secret word
     * 
     * @param Current guess
     * 
     * @return Returns true if guess is correct, else false
     */
    public boolean checkGuess(char c) {
        for(int i = 0; i < this.secretWord.length(); i++) {
            if(this.secretWord.charAt(i) == c) return true;                
        }   
        return false;
    }
    
    /**
     * Checks to see if the game is over 
     * 
     * @returns true if the secrect word has been guessed
     *          or the max number of guesses has been reached
     *          else returns false
     */
    public boolean gameOver() { 
        return this.numGuesses == 0 || Arrays.equals(this.secretWord.toCharArray(), this.clue);    
    } 
   
    /**
     * Update the string of wrongLetters, calls decreaseNumberOfGuesses, and checks 
     * to see if the Game is over. 
     * 
     * If the game is lost, GameLost() is called
     * 
     * Shows the game to the user
     * 
     * @param Current guess
     */
    public void wrongGuess(char c, HangMan interfaceType) throws IllegalArgumentException {
        this.decreaseGuessesLeft(c);
        this.guesses.add(c);
        if(gameOver()) {
            interfaceType.gameLost(secretWord, this);
        }
        else {
            this.updateGame(interfaceType.showGame(c + " Is incorrect", this), interfaceType);            
        }
    }
       
    /**
     * Update the clue with the correct guess and check to see if game is over
     * 
     * Shows the game to the user
     * 
     * @param Current guess
     */
    public void rightGuess(char c, HangMan interfaceType) {
        this.guesses.add(c);
        this.updateClue(c);
        if(gameOver()) {
            interfaceType.gameWon(this);
        }
        else {
            this.updateGame(interfaceType.showGame("You are getting closer! " + c + " Is correct!", this), interfaceType);              
        }
    }

    /**
     * Decreases the number of guesses unless the guess is a duplicate
     * 
     * @args The current guess
     */
    public void decreaseGuessesLeft(char c) {
        if (this.guesses.contains(c) || c == '`') {
             this.numGuesses = this.numGuesses;
        } else {
            this.numGuesses--;
         }       
    }
    
    /**
     * Updates clue with newly guessed letters when they are correct
     * 
     * @args Current guess
     */
    public void updateClue(char c) {
       for(int i = 0; i < this.secretWord.length(); i++) {
           if(this.secretWord.charAt(i) == c) {
               this.clue[i] = c;  
           }
       }
    } 
    
    /**
     * Displays the number of guesses left, the guessed letters, and the clue
     */
    public String toString() {
        return "Guesses Left: " + this.numGuesses + 
               "\nGuessed Letters: " + this.guesses + 
               "\nClue: " + this.formatClue(); 
    }
    
    /**
     * Formats the clue to be easily read by the user
     */
    private String formatClue() {
        String formattedClue = "";
        for(int i = 0; i < clue.length;  i++) {
            formattedClue += clue[i] + "  " ;
        }
        return formattedClue;
    }
}
    