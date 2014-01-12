import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * This test class tests the public methods, which return a method, 
 * in the Dictionary class
 *
 * @author  (Jesse Nelson)
 * @version (September 20, 2012 : Windows Vista(x32) Java 1.7)
 */
public class DictionaryTest {
  Dictionary D;
  Random rand;
  
  @Test
  public void constructorTest() {
      try {
         D = new Dictionary(" ");  
         fail("FileNotFoundException not thrown ");
        } catch(FileNotFoundException e) {
            assertTrue(true);
          }
          catch(Exception e) {
            fail("dictionarySetUp threw incorrect Exception");
        }
      assertNull("Dictionary Should be null", D); 
       
      try {
         D = new Dictionary();  
        } catch(FileNotFoundException e) {
            fail("FileNotFoundException should never be thrown Here " +
                    "Unable to locate default Dictionary");
          }
          catch(Exception e) {
            fail("Unknown error occured");
        }
      assertNotNull("Should have a valid dictionary", D);
      assertNotNull("Should have a valid ArrayList of LinkedList with words", 
                    D.returnDictionary().get(5).get(1));// verify there are words
      assertEquals("Should be of type Dictionary", D.getClass(), Dictionary.class);
      
      try {
         D = new Dictionary(Dictionary.DEFAULT_DICTIONARY);  
        } catch(FileNotFoundException e) {
            fail("Unable to find default dictionary");
          }
          catch(Exception e) {
            fail("Unknown error occured");
          }
      assertNotNull("Should have a valid dictionary", D);
      assertNotNull("Should have a valid ArrayList of LinkedList with words", 
                    D.returnDictionary().get(5).get(1));// verify there are words
      assertEquals("Should be of type Dictionary", D.getClass(), Dictionary.class);
  }
  
  @Test 
  public void randWordTest() {
      rand = new Random();
      String test1;
      String test2;
      String test3;
      String test4;
      String test5;
      String test6;
      String test7;
      String test8;
      String test9;
      String test10;
      try {
         D = new Dictionary();  
        } catch(FileNotFoundException e) {
            fail("Unable to find default dictionary");
          }
          catch(Exception e) {
            fail("Unknown error occured");
          }
      test1 = D.randWord(3);
      test2 = D.randWord(3);
      test3 = D.randWord(5);
      test4 = D.randWord(5);
      test5 = D.randWord(7);
      test6 = D.randWord(7);
      test7 = D.randWord(9);
      test8 = D.randWord(9);
      test9 = D.randWord(11);
      test10 = D.randWord(11);
      
      assertNotNull("A string should be returned", test1);
      assertNotNull("A string should be returned", test2);
      assertNotNull("A string should be returned", test3);
      assertNotNull("A string should be returned", test4);
      assertNotNull("A string should be returned", test5);
      assertNotNull("A string should be returned", test6);
      assertNotNull("A string should be returned", test7);
      assertNotNull("A string should be returned", test8);
      assertNotNull("A string should be returned", test9);
      assertNotNull("A string should be returned", test10);
      
      assertEquals(3, test1.length());
      assertEquals(3, test2.length());
      assertEquals(5, test3.length());
      assertEquals(5, test4.length());
      assertEquals(7, test5.length());
      assertEquals(7, test6.length());
      assertEquals(9, test7.length());
      assertEquals(9, test8.length());
      assertEquals(11, test9.length());
      assertEquals(11, test10.length());
      
      assertNotSame(test1, test2);
      assertNotSame(test3, test4);
      assertNotSame(test5, test6);
      assertNotSame(test7, test8);
      assertNotSame(test9, test10);
  }
}
