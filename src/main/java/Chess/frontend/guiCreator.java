package main.java.Chess.frontend;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * guiCreator holds all the gui related 
 * methods for 'Board Classics'.
 */
public class guiCreator extends JFrame
{
    /**
     * make the title screen and display it,
     * has a 3 buttons. "play game" opens up another
     * frame where user picks a game.
     * "options" sets the size of games.
     * "Quit" closes program.
     * @author Alan
     */
    public static void makeTitle() 
    {
        JFrame titleScreen = new JFrame();
        Border mainScreenBorder;
        Border playGameButtonBorder;
        JLabel mainScreentitle = new JLabel();
        JButton playGameButton = new JButton();

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

        mainScreentitle.setText("Board Classics");;
        mainScreentitle.setForeground(Color.YELLOW);
        mainScreentitle.setFont(new Font("Colon", Font.BOLD, 30));
        //makes a hollow yellow square 
        mainScreenBorder = BorderFactory.createLineBorder(Color.YELLOW, 3);
        mainScreentitle.setBorder(mainScreenBorder);
        //label location horizontal location is set to the center of the frame
        mainScreentitle.setHorizontalAlignment(JLabel.CENTER);;
        //label location vertical location is set to the center of the frame
        mainScreentitle.setVerticalAlignment(JLabel.CENTER);
        mainScreentitle.setBounds(new Rectangle(0,3, 485,450));;
        titleScreen.add(mainScreentitle);
    
        playGameButton.setText("Play game");
        playGameButton.setBounds(new Rectangle(new Point(190,250), playGameButton.getPreferredSize()));
        playGameButton.setBackground(Color.yellow);
        playGameButton.setForeground(Color.BLACK);
        
        playGameButtonBorder = BorderFactory.createRaisedBevelBorder();
        playGameButton.setBorder(playGameButtonBorder);
        //When playgameButton is clicked
        playGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //make titleScreen close
                titleScreen.setVisible(false);
                //open game select screen
                makeGameSelect();
         }
        });
        titleScreen.add(playGameButton);
    }
    /**
     * Display a screen where user select 
     * a game in 'Board Classics'.
     * 
     * @author Alan
     */
    public static void makeGameSelect()
    {
        JFrame gameSelectFrame = new JFrame();
        JLabel gameSelectTitle = new JLabel();
        Border gameSelectScreenBorder;
        JButton playChessButton = new JButton();
        JButton playCheckersButton = new JButton();

        gameSelectFrame.setSize(500,500);
        gameSelectFrame.setResizable(false);
        gameSelectFrame.getContentPane().setBackground(Color.BLACK);
        gameSelectFrame.setLayout(null);
        gameSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameSelectFrame.setVisible(true);
        
        gameSelectScreenBorder = BorderFactory.createLineBorder(Color.YELLOW, 3);
        gameSelectTitle.setBorder(gameSelectScreenBorder);

        gameSelectTitle.setText("Game Selection");;
        gameSelectTitle.setForeground(Color.YELLOW);
        gameSelectTitle.setFont(new Font("Colon", Font.BOLD, 30));
        gameSelectTitle.setHorizontalAlignment(JLabel.CENTER);;
        gameSelectTitle.setVerticalAlignment(JLabel.CENTER);
        gameSelectTitle.setBounds(new Rectangle(0,3, 485,450));;
        gameSelectFrame.add(gameSelectTitle);

        
        playChessButton.setText("Play chess");
        playChessButton.setBounds(new Rectangle(new Point(200,250),playChessButton.getPreferredSize()));
        playChessButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameSelectFrame.setVisible(false);
                chessGame();
            } 
        });
        gameSelectFrame.add(playChessButton);

        playCheckersButton.setText("Play checkers");
        playCheckersButton.setBounds(new Rectangle(new Point(190,300),playCheckersButton.getPreferredSize()));
        playCheckersButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                gameSelectFrame.setVisible(false);
                checkersGame();
            }  
        });
        gameSelectFrame.add(playCheckersButton);
    }  
    /**
     * Displays chess game.
     * 
     * @author Alan
    */
    public static void chessGame()
    {
        Screen screen = new Screen();
        JFrame chessFrame = new JFrame();
        JButton surrenderButton = new JButton();

        chessFrame.add(screen);
        chessFrame.addKeyListener(screen);
        chessFrame.pack();
        chessFrame.setSize(new Dimension(360,300));
        chessFrame.setLayout(null);
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.setVisible(true);

        surrenderButton.setText("Surrender");
        surrenderButton.setBounds(new Rectangle(new Point(255,100),surrenderButton.getPreferredSize()));
        surrenderButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               chessFrame.setVisible(false);
               winScreen();
            }
            
        });
        chessFrame.add(surrenderButton);
    }
    /**
     * Displays checkers game.
     * 
     * @author Alan
     */
    public static void checkersGame()
    {
        JFrame checkersFrame = new JFrame();
        Screen screen = new Screen();
        JButton surrenderButton = new JButton();

        checkersFrame.add(screen);  
        checkersFrame.addKeyListener(screen);
        checkersFrame.pack();
        checkersFrame.setSize(new Dimension(360,300));
        checkersFrame.setLayout(null);
        checkersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkersFrame.setVisible(true);
        
        surrenderButton.setText("Surrender");
        surrenderButton.setBounds(new Rectangle(new Point(255,100),surrenderButton.getPreferredSize()));
        surrenderButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               checkersFrame.setVisible(false);
               winScreen();
            }
            
        }); 
        checkersFrame.add(surrenderButton);
    }
    /**
     * Displays screen after winning or 
     * one of the players surrender.
     * Screen has 3 buttons, 'play again'
     * runs game user was playing again, 
     * 'title screen' open up the 'Board classics'
     * title screen, and 'game select' opens up
     * game select screen for user to pick one of
     * the game.
     * 
     * @author Alan
     */
    public static void winScreen()
    {
        JFrame winFrame = new JFrame();
        JLabel playerXWon = new JLabel();
        Border winFrameBorder;
        JButton playAgainButon = new JButton();
        JButton titleScreenButton = new JButton();
        JButton gameSelectButton = new JButton();

        winFrame.setSize(500,500);
        winFrame.setResizable (false);
        winFrame.getContentPane().setBackground(Color.BLACK);
        winFrame.setLayout(null);
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setVisible(true);

        winFrameBorder = BorderFactory.createLineBorder(Color.YELLOW, 3);
        playerXWon.setBorder(winFrameBorder);

        playerXWon.setText("Player 1 Won");;
        playerXWon.setForeground(Color.YELLOW);
        playerXWon.setFont(new Font("", Font.PLAIN, 30));
        playerXWon.setHorizontalAlignment(JLabel.CENTER);
        playerXWon.setVerticalAlignment(JLabel.CENTER);
        playerXWon.setBounds(new Rectangle(0,3, 485,450));
        winFrame.add(playerXWon);
        
        playAgainButon.setText("Play again");
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

        titleScreenButton.setText("Title Screen");
        titleScreenButton.setSize(200,100);
        titleScreenButton.setBounds(new Rectangle(new Point(190,290),titleScreenButton.getPreferredSize()));
        titleScreenButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                winFrame.setVisible(false);
                makeTitle();
            }

        });
        winFrame.add(titleScreenButton);

        gameSelectButton.setText("Game select");
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
<<<<<<< Updated upstream
       public static void main(String[] args) throws Exception 
=======
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception 
>>>>>>> Stashed changes
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

