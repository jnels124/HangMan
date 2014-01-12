import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import javax.swing.JFileChooser;
 
/**
 * This class is used to gather information from the User to pass to the game 
 * 
 * All methods can be Protected once testing is complete
 * 
 * @author (Jesse Nelson) 
 * @version (Septmber 17, 2012: Windows Vista(x32) Java 1.7
 */
public class User implements HangMan {
    private boolean flag;
    private String userInput;
    private JFileChooser chooser;
    
    /**
       */
    public User() {
        flag = false; 
    }
    
    /**
     * Asks User for dictionary they wish to use,
     * and creates the Dictionary to pass to the game 
     * 
     * @return Dictionary for the game
     */
    public Dictionary dictionarySetUp() throws FileNotFoundException {
        String filename;
        int n = JOptionPane.showConfirmDialog( 
                            null, "Would you like to load your own dictionary from " +
                                  "your computer?", "Welcome to the Hang Man Game! ", 
                            JOptionPane.YES_NO_CANCEL_OPTION); 
        if (n == JOptionPane.YES_OPTION) { 
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(null);
            int code = chooser.showOpenDialog(null);
            if (code == JFileChooser.APPROVE_OPTION) {
                filename = chooser.getSelectedFile().getPath();
                return new Dictionary(filename);
            } else {
                JOptionPane.showMessageDialog(null, "There was an error loading your Dictionary.\n" + 
                                                   "The default Dictionary has been loaded instead", 
                                                   "ERROR", JOptionPane.ERROR_MESSAGE);
                return new Dictionary(); 
            }
        } else if (n == JOptionPane.NO_OPTION) { 
            System.out.print("Loading default dictionary");
                    return new Dictionary();    
          } else { // Exits game if user hits cancel or closes the screen on initial dialog box
                System.exit(0);
                return null;
            } 
    }
    
    /**
     * Gets the length of the word User wants to use and returns info to game
     * 
     * @return Length of the word to be used for the game
     */
    public int wordLength() {
            do { // Verifies valid data is provided
                userInput = JOptionPane.showInputDialog
                ("How many letters do you want to use for the word?");
                if(userInput == null) {
                    do {
                        int n = JOptionPane.showConfirmDialog( 
                            null,  "Would you like to quit the current game?", 
                            "Quit", JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.NO_OPTION) {
                            userInput = JOptionPane.showInputDialog
                            ("How many letters do you want to use for the word?");
                        } else  playAgain();
                        
                    } while (userInput == null);
                }
                if(userInput.length() < 1 ||  !Character.isDigit(userInput.charAt(0)) ||
                   Integer.parseInt(userInput) < Dictionary.MIN_WORD_LENGTH  ||
                   Integer.parseInt(userInput) > Dictionary.MAX_WORD_LENGTH) {
                   JOptionPane.showMessageDialog(null, "You have not entered a valid number!\n\n"
                                                + "Please enter a valid number between "
                                                + Dictionary.MIN_WORD_LENGTH + " and " + 
                                                Dictionary.MAX_WORD_LENGTH,"ERROR", JOptionPane.ERROR_MESSAGE); 
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
                userInput = JOptionPane.showInputDialog
                ("How many guesses do you want to allow?");
                if(userInput == null) {
                    do {
                        int n = JOptionPane.showConfirmDialog( 
                            null,  "Would you like to quit the current game?", 
                            "Quit", JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.NO_OPTION) {
                            userInput = JOptionPane.showInputDialog
                           ("How many guesses do you want to allow?"); 
                        } else  playAgain();
                    
                    } while (userInput == null);
                }
                if(userInput.length() < 1 || !Character.isDigit(userInput.charAt(0)) ||
                   Integer.parseInt(userInput) < Game.MIN_NUMBER_GUESSES ||
                   Integer.parseInt(userInput) > Game.MAX_NUMBER_GUESSES) {
                   JOptionPane.showMessageDialog(null, "You have not entered a valid number\n"
                                                + "Please enter a valid number between "
                                                + Game.MIN_NUMBER_GUESSES + " and " + Game.MAX_NUMBER_GUESSES,
                                                 "ERROR", JOptionPane.ERROR_MESSAGE); 
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
                userInput = JOptionPane.showInputDialog
                (message + "\n\n" + game + "\n\n What is your Guess? ");
                if(userInput == null) {
                    do {
                        int n = JOptionPane.showConfirmDialog( 
                            null,  "Would you like to quit the current game?", 
                            "Quit", JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.NO_OPTION) {
                            userInput = JOptionPane.showInputDialog
                            (message + "\n\n" + game + "\n\nWhat is your Guess? ");
                        } else  playAgain();
   
                    } while (userInput == null);
                }
                if(userInput.length() < 1 || 
                   userInput.charAt(0) < Dictionary.LOWEST_CHARACTER||
                   userInput.charAt(0) > Dictionary.HIGHEST_CHARACTER) { // Checks for most invalid keys 
                   JOptionPane.showMessageDialog(null, "You have not entered a valid value!\n"
                                                    + "Please enter a valid letter. " , "ERROR", 
                                                       JOptionPane.ERROR_MESSAGE); 
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
        JOptionPane.showMessageDialog(null, "You are out of guesses, you lose!\n\n" + 
                                       game + "\n\nThe correct Secret word is " + secretWord);
    }
    
    /**
     * Notifies User the game was won
     */
    public void gameWon(Game game) {
        JOptionPane.showMessageDialog(null, "Congratulations you have guessed " + 
                                           "all of the corret letters!\n\n" + game);                                  
    }

    /**
     * Ask user if they would like to play another Game
     * and creates a new Game if they do otherwise, exits
     * the application. 
     */
    public void playAgain() {
        int n = JOptionPane.showConfirmDialog( null, 
                "Would you like to play again?", "Hang Man",
                JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            try {
               new EvilHangMan(dictionarySetUp(), new User());            
            } catch(FileNotFoundException e) {
        }
        } else {
            System.exit(0);
          }
    }
}
