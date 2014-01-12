import java.io.FileNotFoundException;

/**
 * This class will drive the HangMan Game. An Initial Game is created 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver {
        
    public static void main(String [] args) {
        HangMan game;
        if(args.length > 1) {
            game = new UserConsole();
            new Game(game);
        } else {
            game = new User();
            new Game(game);
          }
        while(true) {
           game.playAgain();               
        }
    }     
}