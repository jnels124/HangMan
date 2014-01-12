import java.util.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import javax.swing.JOptionPane;

/**
 * This class creates a Dictionary by taking a file selected by the user,
 * reading the file, and storing the words in a data structure
 * 
 * @author (Jesse Nelson) 
 * @version (September 19, 2012 : Windows Vista(x32) Java 1.7)
 */
public class Dictionary { 
    /**
     * Maximum word length in the dictionary
     */
    static final int MAX_WORD_LENGTH = 29;
    
    /**
     * Minimum word length in the dictionary
     */
    static final int MIN_WORD_LENGTH = 2;
    
    /**
     * Default Dictionary file
     */
    static final String DEFAULT_DICTIONARY = "DefaultDictionary.txt";
    
    /**
     * Lowest character a word may have in Dictionary
     */
    static final char LOWEST_CHARACTER = 'A';
    
    /**
     * Highest character a word may have in Dictionary
     */
    static final char HIGHEST_CHARACTER = 'z';
    
    private Scanner dictionaryFile;
    private ArrayList<LinkedList<String>> words;
    private Random r;
    private LinkedList<String> candidates;
    
    /**
     * Default constructor to load default dictionary
     * 
     * Initializes private instance variables, calls readFile(), and closes the file when writing to it is finished 
     */
    public Dictionary() throws FileNotFoundException {
        words = new ArrayList<LinkedList<String>>(); 
        r = new Random();
        dictionaryFile = new Scanner(new FileReader(DEFAULT_DICTIONARY));
        initializeDictionary();
        readFile();
        dictionaryFile.close();
    }
    
    /**
     * Initializes private instance variables, calls readFile(), and closes the file when writing to it is finished 
     * 
     * @args File to be used as Dictionary
     */
    public Dictionary(String file) throws FileNotFoundException {
        words = new ArrayList<LinkedList<String>>(); 
        r = new Random();
        dictionaryFile = new Scanner(new FileReader(file));
        initializeDictionary();
        readFile();
        dictionaryFile.close();
    }
    
    /**
     * Reads file and stores Dictionary in an array of LinkedLists
     * 
     * @return Returns the current Dictionary to the Game
     */
   private Dictionary readFile()  {
        while(dictionaryFile.hasNext()) 
            insert(dictionaryFile.nextLine());
            
        return this;
   }
    
    /**
     * Verifies the dictionary contains a word of the specifed 
     * length and if none are present, a new length is requested.
     * 
     * Generates a random word to be returned to the game 
     * 
     * @args Size of word to be returned 
     * 
     * @return Random word from the dictionary of the specified size
     */
    public String randWord(int wordSize)  {
        candidates = words.get(wordSize);
        if(candidates.size() < 1 ) {
            boolean flag = false;
            do {
                String s =(JOptionPane.showInputDialog("I apologize, but I can not " +
                                                  "seem to find any words of length " + wordSize
                                                    + "\nPlease enter a new word length")); 
               if(s == null || s.length() < 1 || !Character.isDigit(s.charAt(0)) ||
                  Integer.parseInt(s) < Dictionary.MIN_WORD_LENGTH ||
                  Integer.parseInt(s) > Dictionary.MAX_WORD_LENGTH) {
                  JOptionPane.showMessageDialog(null, "You have not enterd a valid length.\n "
                                                + "Please enter a valid value in between "
                                                + Dictionary.MIN_WORD_LENGTH + " and " + 
                                                Dictionary.MAX_WORD_LENGTH,"ERROR", JOptionPane.ERROR_MESSAGE);   
               } else {
                   flag = true;
                   return randWord(Integer.parseInt(s));
                 }
                
           } while(!flag);
        }
        return candidates.get(r.nextInt(candidates.size()));
    }
    
    /**
     * @return Entire Dictionary 
     */
    public ArrayList<LinkedList<String>> returnDictionary () { // Added to use in tests
        return this.words;
    }
    
    public LinkedList<String> allWordsOfLength(int wordSize) {
		candidates = words.get(wordSize);
        if(candidates.size() < 1 ) {
            boolean flag = false;
            do {
                String s =(JOptionPane.showInputDialog("I apologize, but I can not " +
                                                  "seem to find any words of length " + wordSize
                                                    + "\nPlease enter a new word length")); 
               if(s == null || s.length() < 1 || !Character.isDigit(s.charAt(0)) ||
                  Integer.parseInt(s) < Dictionary.MIN_WORD_LENGTH ||
                  Integer.parseInt(s) > Dictionary.MAX_WORD_LENGTH) {
                  JOptionPane.showMessageDialog(null, "You have not enterd a valid length.\n "
                                                + "Please enter a valid value in between "
                                                + Dictionary.MIN_WORD_LENGTH + " and " + 
                                                Dictionary.MAX_WORD_LENGTH,"ERROR", JOptionPane.ERROR_MESSAGE);   
               } else {
                   flag = true;
                   return allWordsOfLength(Integer.parseInt(s));
                 }
                
           } while(!flag);
        }
	
        return candidates;
    }
    
    // Inserts current word from file into data structure
    private void insert(String word) {
        words.get(word.length()).add(word);
    }
    
    private void initializeDictionary() {
        for(int i = 0; i <= MAX_WORD_LENGTH; i++  ) {
             words.add(new LinkedList<String>());
        } 
    }
}
