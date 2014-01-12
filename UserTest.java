

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

/**
 * The test class UserTest. Test's user input
 *
 * @author  (Jesse Nelson)
 * @version (October 6, 2012 : Windows 7(x64) Java1.7)
 */
public class UserTest {
    User U;
    Dictionary D;
    String userInput;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        U = new User();
        D = null;
        userInput = "";
    }

    
    @Test
    public void dictionarySetUpTest() {
       
       try {
           D = U.dictionarySetUp();
       } catch(FileNotFoundException e) {
            fail("Default dictionary should have been loaded");
         }
         catch(Exception e) {
            fail("dictionarySetUp threw incorrect Exception");
         }
       assertNotNull(D);
       assertEquals(D.getClass(), Dictionary.class);
       
       try {
           D = U.dictionarySetUp();
       } catch(FileNotFoundException e) {
            fail("Unable to locate the selected file");
         }
         catch(Exception e) {
            fail("dictionarySetUp threw incorrect Exception");
         }
       assertNotNull(D);
       assertEquals(D.getClass(), Dictionary.class);
    }
     
    @Test
    public void numberOfGuessesTest() {
        userInput = JOptionPane.showInputDialog ("Enter 7");
        assertEquals(7, Integer.parseInt(userInput));
        
        userInput = JOptionPane.showInputDialog ("Enter 5");
        assertEquals(5, Integer.parseInt(userInput));
        
        userInput = JOptionPane.showInputDialog ("Enter 8");
        assertEquals(8, Integer.parseInt(userInput));
        
    }
    
    @Test
    public void WordLengthTest() {
        userInput = JOptionPane.showInputDialog ("Enter 7");
        assertEquals(7, Integer.parseInt(userInput));
        
        userInput = JOptionPane.showInputDialog ("Enter 5");
        assertEquals(5, Integer.parseInt(userInput));
        
        userInput = JOptionPane.showInputDialog ("Enter 8");
        assertEquals(8, Integer.parseInt(userInput));
    }
    
    @Test
    public void showGame() { 
       userInput = JOptionPane.showInputDialog ("Enter a");
       assertEquals('a', userInput.toLowerCase().charAt(0));
        
       userInput = JOptionPane.showInputDialog ("Enter b");
       assertEquals('b', userInput.toLowerCase().charAt(0));
        
       userInput = JOptionPane.showInputDialog ("Enter c");
       assertEquals('c', userInput.toLowerCase().charAt(0));
    }
}

