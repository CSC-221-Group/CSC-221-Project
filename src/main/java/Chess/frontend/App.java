package main.java.Chess.frontend;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App 
{  
        /*JFrame titleScreen = new JFrame("Board Classics");
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
        chessPanel.setVisible(true);*/
       
        public static void makeTitle() 
        {

            JFrame mainScreen = new JFrame("Board Classics");
            mainScreen.setSize(500,500);
            mainScreen.setVisible(true);
            mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainScreen.setResizable(false);
            mainScreen.getContentPane().setBackground(Color.BLACK);
            mainScreen.setLayout(null);

            JLabel title = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
            title.setBorder(border);
            title.setText("Board Classics");;
            title.setForeground(Color.YELLOW);
            title.setFont(new Font("", Font.PLAIN, 30));
            title.setHorizontalAlignment(JLabel.CENTER);;
            title.setVerticalAlignment(JLabel.CENTER);
            title.setBounds(new Rectangle(20,5, 450,450));;
            mainScreen.add(title);
        
            JButton playGameButton = new JButton();
            playGameButton.setText("Play game");
            playGameButton.setBounds(new Rectangle(new Point(200,250), playGameButton.getPreferredSize()));
            playGameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    mainScreen.setVisible(false);
                    makeGameSelect();
             }
            });
            mainScreen.add(playGameButton);
        }
        public static void makeGameSelect()
        {
            JFrame gameSelect = new JFrame();
            gameSelect.setSize(500,500);
            gameSelect.setVisible(true);
            gameSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameSelect.setResizable (false);
            gameSelect.getContentPane().setBackground(Color.BLACK);
            gameSelect.setLayout(null);

            JLabel gameSelectTitle = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
            gameSelectTitle.setBorder(border);
            gameSelectTitle.setText("Game Selection");;
            gameSelectTitle.setForeground(Color.YELLOW);
            gameSelectTitle.setFont(new Font("", Font.PLAIN, 30));
            gameSelectTitle.setHorizontalAlignment(JLabel.CENTER);;
            gameSelectTitle.setVerticalAlignment(JLabel.CENTER);
            gameSelectTitle.setBounds(new Rectangle(20,5, 450,450));;
            gameSelect.add(gameSelectTitle);

            JButton chess = new JButton();
            Screen screen = new Screen();
            chess.setText("Play chess");
            chess.setSize(100,100);
            chess.setBounds(new Rectangle(new Point(200,250),chess.getPreferredSize()));
            chess.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    gameSelect.setVisible(false);
                    JFrame chessPanel = new JFrame();
                    chessPanel.add(screen);
                    chessPanel.addKeyListener(screen);
                    chessPanel.setVisible(true);
                    chessPanel.pack();
                } 
            });
            gameSelect.add(chess);

            JButton checkers = new JButton();
            
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
                makeTitle();
                System.out.println("Space changes turns, it has to be player 1's turn to move white's pieces" +
                " and player 2's turn to move black's pieces") ;
            }

        });
    }
}
