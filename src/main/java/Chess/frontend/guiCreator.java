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
    // constants
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int FONT_SIZE = 48;
    // methods
    private static JLabel makeText(String text, int x, int y, int w, int h) {
        JLabel mainScreentitle = new JLabel();
        mainScreentitle.setText(text);
        mainScreentitle.setForeground(Color.YELLOW);
        mainScreentitle.setFont(new Font("Colon", Font.BOLD, FONT_SIZE));
        mainScreentitle.setHorizontalAlignment(JLabel.CENTER);;
        mainScreentitle.setVerticalAlignment(JLabel.CENTER);
        mainScreentitle.setBounds(new Rectangle(x,y, w,h));;
        return mainScreentitle;
    }

    private static JFrame makeMainFrame() {
        JFrame frame = new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    private static JButton makeButton(String text, int x, int y, int w, int h) {
        JButton button = new JButton();

        button.setIcon(new ImageIcon("images/" + text + "Gui.png"));
        button.setSize(w,h);
        button.setBounds(new Rectangle(new Point(x,y),new Dimension(w, h)));
        button.setBackground(Color.BLACK);
        return button;
    }
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
        final JFrame titleScreen = makeMainFrame();
        JLabel mainScreentitle = makeText("Board Classics", 0, 50, 500, 100);
        JButton playGameButton = makeButton("playGame", 140, 250, 200, 64);
        JButton optionsButton = makeButton("options", 140, 350, 200, 64);
        titleScreen.add(mainScreentitle);
        titleScreen.add(optionsButton);
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
        JFrame gameSelectFrame = makeMainFrame();
        JLabel gameSelectTitle = makeText("Game Selection", 0, -100, 485, 450);
        JButton playChessButton = makeButton("playChess", 140, 250, 200, 64);
        JButton playCheckersButton = makeButton("playCheckers", 140, 350, 200, 64);

        gameSelectFrame.add(gameSelectTitle);

        playChessButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameSelectFrame.setVisible(false);
                chessGame();
            } 
        });
        gameSelectFrame.add(playChessButton);
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
        Screen screen = new Screen(); //TODO make screen chess class
        JFrame chessFrame = new JFrame();
        JButton surrenderButton = makeButton("surrender", 255, 100, 96, 30);

        chessFrame.add(screen);
        chessFrame.addKeyListener(screen);
        chessFrame.pack();
        chessFrame.setSize(new Dimension(360,300));
        chessFrame.setLayout(null);
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.setVisible(true);

        surrenderButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               chessFrame.setVisible(false);
               winScreen(screen.getCurrentTurn()); //TODO add paramater for which turn it is
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
               winScreen(screen.getCurrentTurn());
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
    public static void winScreen(int turn)
    {
        JFrame winFrame = makeMainFrame();
        JLabel playerXWon = new JLabel();
        JButton playAgainButon = makeButton("playAgain", 140, 100, 200, 64);
        JButton titleScreenButton = makeButton("titleScreen", 140, 200, 200, 64);
        JButton gameSelectButton = makeButton("gameSelect", 140, 300, 200, 64);



        playerXWon.setText("Player " + turn + " Won");;
        playerXWon.setForeground(Color.YELLOW);
        playerXWon.setFont(new Font("", Font.PLAIN, 30));
        playerXWon.setHorizontalAlignment(JLabel.CENTER);
        playerXWon.setVerticalAlignment(JLabel.CENTER);
        playerXWon.setBounds(new Rectangle(0,-200, 485,450));
        winFrame.add(playerXWon);
        
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

