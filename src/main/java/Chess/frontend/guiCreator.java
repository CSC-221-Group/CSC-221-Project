package main.java.Chess.frontend;
import java.awt.Color;
import java.awt.Dimension;

import javax.naming.TimeLimitExceededException;
import javax.swing.*;
import javax.swing.border.Border;

import main.Piece.Piece;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class guiCreator extends JFrame
{
    public static void makeTitle() 
    {
        JFrame titleScreen = new JFrame();
        Border mainScreenBorder;
        JLabel mainScreentitle = new JLabel();

        //title of frame
        titleScreen.setTitle("Board Classics");
        //size of main Screen
        titleScreen.setSize(500,500);
        //frame is visible 
        titleScreen.setVisible(true);
        //when user presses the x on the top left of frame it stops running the programs
        titleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //user can't make frame bigger or smaller 
        titleScreen.setResizable(false);
        //makes frame background black
        titleScreen.getContentPane().setBackground(Color.BLACK);
        titleScreen.setLayout(null);

        //makes a hollow square 
        mainScreenBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);
       
        mainScreentitle.setBorder(mainScreenBorder);
        mainScreentitle.setText("Board Classics");;
        mainScreentitle.setForeground(Color.YELLOW);
        mainScreentitle.setFont(new Font("", Font.PLAIN, 30));
        mainScreentitle.setHorizontalAlignment(JLabel.CENTER);;
        mainScreentitle.setVerticalAlignment(JLabel.CENTER);
        mainScreentitle.setBounds(new Rectangle(0,0, 480,60));;
        titleScreen.add(mainScreentitle);
    
        JButton playGameButton = new JButton();
        playGameButton.setText("Play game");
        playGameButton.setBounds(new Rectangle(new Point(200,250), playGameButton.getPreferredSize()));
        playGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                titleScreen.setVisible(false);
                makeGameSelect();
         }
        });
        titleScreen.add(playGameButton);
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
        gameSelectTitle.setBounds(new Rectangle(25,5, 450,450));;
        gameSelect.add(gameSelectTitle);

        JButton chess = new JButton();
        chess.setText("Play chess");
        chess.setSize(100,100);
        chess.setBounds(new Rectangle(new Point(200,250),chess.getPreferredSize()));
        chess.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameSelect.setVisible(false);
                chessGame();
            } 
        });
        gameSelect.add(chess);

        JButton checkers = new JButton();
        checkers.setText("Play checkers");
        checkers.setSize(100,100);
        checkers.setBounds(new Rectangle(new Point(190,300),checkers.getPreferredSize()));
        checkers.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                gameSelect.setVisible(false);
                checkersGame();
            }  
        });
        gameSelect.add(checkers);
    }  
    public static void chessGame()
    {
        Screen screen = new Screen();
        JFrame chessPanel = new JFrame();

        chessPanel.add(screen);
        chessPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessPanel.setBackground(Color.BLACK);
        chessPanel.addKeyListener(screen);
        chessPanel.pack();
        chessPanel.setSize(new Dimension(360,300));
        chessPanel.setLayout(null);
        chessPanel.setVisible(true);

        JButton surrenderButton = new JButton();
        surrenderButton.setText("Surrender");
        surrenderButton.setSize(100,100);
        surrenderButton.setBounds(new Rectangle(new Point(255,100),surrenderButton.getPreferredSize()));
        surrenderButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               chessPanel.setVisible(false);
               winScreen();
            }
            
        });
        chessPanel.add(surrenderButton);
    }
    public static void checkersGame()
    {
        JFrame checkersPanel = new JFrame();
        Screen screen = new Screen();

        checkersPanel.add(screen);  
        checkersPanel.addKeyListener(screen);
        checkersPanel.setVisible(true);
        checkersPanel.pack();
        checkersPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkersPanel.setLayout(new BoxLayout(checkersPanel, MAXIMIZED_BOTH));
        checkersPanel.setBounds(new Rectangle(new Point(190,300),checkersPanel.getPreferredSize()));
    }
    public static void winScreen()
    {
        JFrame winFrame = new JFrame();
        winFrame.setSize(500,500);
        winFrame.setVisible(true);
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setResizable (false);
        winFrame.getContentPane().setBackground(Color.BLACK);
        winFrame.setLayout(null);

        JLabel playerXWon = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        playerXWon.setBorder(border);
        playerXWon.setText("Player 1 Won");;
        playerXWon.setForeground(Color.YELLOW);
        playerXWon.setFont(new Font("", Font.PLAIN, 30));
        playerXWon.setHorizontalAlignment(JLabel.CENTER);
        playerXWon.setVerticalAlignment(JLabel.CENTER);
        playerXWon.setBounds(new Rectangle(20,5, 450,450));
        winFrame.add(playerXWon);
        
        JButton playAgainButon = new JButton();
        playAgainButon.setText("Play again");
        playAgainButon.setSize(100,100);
        playAgainButon.setBounds(new Rectangle(new Point(190,250),playAgainButon.getPreferredSize()));
        playAgainButon.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                winFrame.setVisible(false);
                chessGame();
            }
        });
        winFrame.add(playAgainButon);

        JButton titleScreenButton = new JButton();
        titleScreenButton.setText("Title Screen");
        titleScreenButton.setSize(200,100);
        titleScreenButton.setBounds(new Rectangle(new Point(190,290),titleScreenButton.getPreferredSize()));
        titleScreenButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                winFrame.setVisible(false);
                makeGameSelect();
            }

        });
        winFrame.add(titleScreenButton);

        JButton gameSelectButton = new JButton();
        gameSelectButton.setText("Game select");
        gameSelectButton.setSize(100,100);
        gameSelectButton.setBounds(new Rectangle(new Point(190,320),gameSelectButton.getPreferredSize()));
        gameSelectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                winFrame.setVisible(false);
                makeGameSelect();
            }
            
        });
        winFrame.add(gameSelectButton);
       }
} 

