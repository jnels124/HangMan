import javax.swing.JFileChooser;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Write a description of class UserConsole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserConsole implements HangMan { 
    private boolean flag;
    private String userInput;
    private JFileChooser chooser;
    private Scanner console;
    
    /**
     */
    public UserConsole() {
        flag = false; 
        console = new Scanner(System.in);
    }
    
    /**
     * Asks User for dictionary they wish to use,
     * and creates the Dictionary to pass to the game 
     * 
     * @return Dictionary for the game
     */
    public Dictionary dictionarySetUp() throws FileNotFoundException {
        userInput = msg("Please type d to load the default or l to load your own");
        if(userInput.toLowerCase().charAt(0) == 'l') {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(null);
            int code = chooser.showOpenDialog(null);
            if (code == JFileChooser.APPROVE_OPTION) {
                //filename = ;
                return new Dictionary(chooser.getSelectedFile().getPath());
            } else {
                msg( "There was an error loading your Dictionary.\n" + 
                     "The default Dictionary has been loaded instead");
                return new Dictionary(); 
            }
        } else {
            return new Dictionary();
          }
    }
    public String msg(String request) {
        System.out.println(request);
        return console.next();
    }
    
   /**
     * Gets the length of the word User wants to use and returns info to game
     * 
     * @return Length of the word to be used for the game
     */
   public int wordLength() { 
        do {
            userInput = msg("How many letters do you want to use for the word?");
            if(userInput.length() < 1 ||  !Character.isDigit(userInput.charAt(0)) ||
                   Integer.parseInt(userInput) < Dictionary.MIN_WORD_LENGTH  ||
                   Integer.parseInt(userInput) > Dictionary.MAX_WORD_LENGTH) {
                   msg( "You have not entered a valid number!\n\n"
                         + "Please enter a valid number between "
                         + Dictionary.MIN_WORD_LENGTH + " and " 
                         + Dictionary.MAX_WORD_LENGTH);
                   flag = false;
            } else {
                flag = true;                    
              }
                
        } while(!flag);
            
        return Integer.parseInt(userInput); 
    }             
    
    /**
     * Gets the number of guesses user would like to allow 
     * 
     * @return Number of guesses allowed in game 
     */
    public int numberOfGuesses() {
        do {
            userInput = msg("How many guesses do you want to allow?");
            if(userInput.length() < 1 || !Character.isDigit(userInput.charAt(0)) ||
                   Integer.parseInt(userInput) < Game.MIN_NUMBER_GUESSES ||
                   Integer.parseInt(userInput) > Game.MAX_NUMBER_GUESSES) {
                   msg( "You have not entered a valid number!\n\n"
                         + "Please enter a valid number between "
                         + Game.MIN_NUMBER_GUESSES + " and " 
                         + Game.MAX_NUMBER_GUESSES);
                   flag = false;
            } else {
                flag = true;                    
              }
                
        } while(!flag);
            
        return Integer.parseInt(userInput); 
   }
        
    /**
     * Shows the current status of the game 
     * 
     * @args Message to display to the user 
     * @args The current Game
     * 
     * @return User's new guess
     */
    public char showGame(String message, Game game) { 
        do {
            userInput = msg(message + "\n\n" + game + "\n\nWhat is your Guess? ");
            if(userInput.length() < 1 || 
               userInput.charAt(0) < Dictionary.LOWEST_CHARACTER||
               userInput.charAt(0) > Dictionary.HIGHEST_CHARACTER) {
                   msg( "You have not entered a valid letter!\n\n"
                         + "Please enter a valid letter");
                   flag = false;     
            } else {
                flag = true;                    
              }
                
        } while(!flag);
        
        return Character.toLowerCase(userInput.charAt(0));
    }

    /**
     * Notifies user they lost
     */
    public void gameLost(String secretWord, Game game) {
        msg( "You are out of guesses, you lose!\n\n" + game 
            + "\n\nThe correct Secret word is " + secretWord);
    } 
    
    /**
     * Notifies User the game was won
     */
    public void gameWon(Game game) {
        msg( "Congratulations you have guessed " + 
              "all of the corret letters!\n\n" + game);                                  
    } 

    /**
     * Ask user if they would like to play another Game
     * and creates a new Game if they do otherwise, exits
     * the application. 
     */
    public void playAgain() {
        userInput = msg("Would you like to play again y/n?");
        if(userInput.toLowerCase().charAt(0) == 'y') {
            try {
                new EvilHangMan(dictionarySetUp(), new UserConsole());            
            } catch(FileNotFoundException e) {
              }
        } else {
            System.exit(0);
          }
    }
}
