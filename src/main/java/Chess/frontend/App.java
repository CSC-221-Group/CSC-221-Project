package main.java.Chess.frontend;
import main.java.Chess.frontend.guiCreator;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;

public class App 
{  
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
                guiCreator makeTitle = new guiCreator();
                makeTitle.makeTitle();
                System.out.println("Space changes turns, it has to be player 1's turn to move white's pieces" +
                " and player 2's turn to move black's pieces") ;
            }

        });
    }
}
