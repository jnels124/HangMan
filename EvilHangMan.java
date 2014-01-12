import java.util.List;
import java.util.TreeSet;
import java.lang.*;
import java.util.*;

/**
 * This class creates a hangman game with a greater difficulty.
 * 
 * The greater difficulty is caused by the secret word as it keeps 
 * changing until there is only one word left that matches the 
 * template.
 * 
 * @author (Jesse Nelson) 
 * @version (October 15, 2012 Windows 7(x64) : Java 1.7)
 */
public class EvilHangMan extends Game {
    Map<String, List<String>> groups;
    String template;
    
    
    /**
     * Constructor for objects of class EvilHangMan
     */
    public EvilHangMan(Dictionary d, HangMan interfaceType) { 
       super(d, interfaceType);
       for(int i = 0; i < secretWord.length(); i++) {
            this.template += "_";
       }
       this.updateGame(interfaceType.showGame("Welcome to HangMan!", this), interfaceType);  
    }
    
    
    public EvilHangMan(){ // used for testing
       String [] words = {"cool", "fool", "pool", "deal", "real", "peal", "meal"} ;
       candidates = Arrays.asList(words);
       for(int i = 0; i < 4; i++) {
            this.template += "_";
       }
    }
   

    // overides method in super class
    public void updateGame(char c, HangMan interfaceType) {
        this.updateCandidates(c);
        secretWord = candidates.get(rand.nextInt(candidates.size()));
        this.template = new String(clue);
        super.updateGame(c, interfaceType);
    }
    /**
     * Checks the current list of candidates and removes invalid words 
     * based on game status
     */
    public void updateCandidates(char c) {
        if(c == '`') {
           evilCheater();
        } else {
            this.groups = new HashMap<String, List<String>>(candidates.get(0).length());
            for(int i = 0; i < candidates.size(); i++) {
               char tmp [] = template.toCharArray();
               for(int j = 0; j < candidates.get(i).length(); j++) {
                   if(candidates.get(i).charAt(j) == c) {
                       
                       tmp[j] = candidates.get(i).charAt(j);                      
                   }                    
               }
               List<String> tmpList = this.groups.get(new String(tmp));
               if(tmpList == null) {
                   tmpList = new LinkedList<String>();
                   tmpList.add(candidates.get(i));
                   this.groups.put(new String(tmp), tmpList);
               
               } else {
                   tmpList.add(candidates.get(i));
                   this.groups.put(new String(tmp), tmpList);    
                 }
            }
            this.largestGroup();
          }
    }
    
    /**
     * Finds the template that matches the most number of words
     * and assigns that list to be the new list of candidates
     */
    public void largestGroup() {
        List<String> largestList = new LinkedList<String>();
        List<String> groupsNext = new LinkedList<String>();
        Set<String> groupsKeys = groups.keySet();
        Iterator<String> itr = groupsKeys.iterator();
        while(itr.hasNext()) {
            groupsNext = groups.get(itr.next());
            if(groupsNext.size() > largestList.size()) {
                largestList = groupsNext; 
            }
        }
        candidates = largestList;
    }
    
    /**
     * Used for debugging and testing in EvilHangMan. 
     * 
     * Will display the current secret word, the clue, and all candidates
     */
    private void evilCheater() {
        System.out.println("The current secret word is " + secretWord);
        System.out.println("candidate words are:");
		for(int i = 0; i < candidates.size(); i++) {
			System.out.println(candidates.get(i));
			System.out.println(clue);
		}
    }
    
}
