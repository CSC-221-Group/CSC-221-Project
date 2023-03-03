package main.java.Chess.frontend;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;

public class App 
{  
    private static void createAndShowGUI() 
    {
        JFrame titleScreen = new JFrame("Board Classics");
        titleScreen.setSize(500,500);
        titleScreen.setVisible(true);
        titleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleScreen.setResizable(false);
        titleScreen.getContentPane().setBackground(Color.BLACK);
        
        //Board Classics label
        JLabel boardClassics = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.YELLOW,3);
        boardClassics.setText("Board Classics");
        
        titleScreen.add(boardClassics);
        boardClassics.setHorizontalAlignment(JLabel.CENTER);
        boardClassics.setForeground(Color.YELLOW);
        boardClassics.setFont(new Font("MV Boli",Font.ITALIC,50));
        boardClassics.setBorder(border);
        boardClassics.setVerticalAlignment(JLabel.CENTER);
        boardClassics.setHorizontalAlignment(JLabel.CENTER);
     
        JFrame chessPanel = new JFrame("Chess");
        chessPanel.pack();chessPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       

        Screen screen = new Screen();
        chessPanel.add(screen);
        chessPanel.addKeyListener(screen);
        chessPanel.setResizable(false);
        chessPanel.pack();
        chessPanel.setLocationRelativeTo(null);
        chessPanel.setVisible(true);
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
