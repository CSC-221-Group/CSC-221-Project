package chess;

import Chess.frontend.Screen;
import javax.swing.*;



/**
 * 
 * The main class for Chess app
 * 
 * @author Jordan
 */
public class App {
    /**
     * 
     * Really no need for this method as its only used once.
     * Just have it apart of the main method (unless it'll be used
     * multiple times in the future)
     * 
     */
    private static void createAndShowGUI() {
        //window
        JFrame window = new JFrame("Chess App");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        window.add(screen);
        window.addKeyListener(screen);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    /**
     * 
     * main method
     * removed "throws Exception" as no lines require an exception thrown
     * 
     * @param args
     */
    public static void main(String[] args) {
        //removed the runnable here as it doesnt do anything
        createAndShowGUI();
        //make this an on-screen prompt (I could handle that)
        System.out.println("Space changes turns, it has to be player 1's turn to move white's piecesn and player 2's turn to move black's pieces") ;
    }
}
