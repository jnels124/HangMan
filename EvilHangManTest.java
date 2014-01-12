import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EvilHangManTest.
 *
 * @author (Jesse Nelson) 
 * @version (October 19, 2012 Windows 7(x64) : Java 1.7)
 */
public class EvilHangManTest {
    EvilHangMan test;
    /**
     * Default constructor for test class EvilHangManTest
     */
    public EvilHangManTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        test = new EvilHangMan();
    }
    
    
    public void updateCandidates() {
        assertTrue(test.candidates.size() == 7);
        test.updateCandidates('c');
        assertFalse(test.candidates.contains("cool"));
        assertTrue(test.candidates.contains("fool"));
        assertTrue(test.candidates.contains("pool"));
        assertTrue(test.candidates.contains("peal"));
        assertTrue(test.candidates.contains("deal"));
        assertTrue(test.candidates.contains("real"));
        assertTrue(test.candidates.contains("meal"));
        assertTrue(test.candidates.size() == 6);
        test.updateClue('c');
        test.template = new String(test.clue);
        assertTrue(test.template == "____");
        test.updateCandidates('l');
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
