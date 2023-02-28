package main.java.Chess.frontend;
import javax.swing.*;
import main.java.Chess.Screen;
public class App 
{
    private static void createAndShowGUI()
    {
        JFrame window = new JFrame("Chess");
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
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
                System.out.println("Space changes turns, it has to be player 1's turn to move white's pieces" +
                        " and player 2's turn to move black's pieces") ;
            }
        });
    }
}
