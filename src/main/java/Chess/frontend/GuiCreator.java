package Chess.frontend;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**********************************************************
 * Program Name   : guiCreator
 * Author         : Jordan/Alan
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: 
 * This program is gui of the game called 'Board Classics'
 *
 * Methods:
 * -------
 * makeText -
 * makeMainFrame -
 * makeFrame - 
 * makeTitle -
 * makeGameSelect -
 * chessGame - 
 * checkerGame -
 * winScreen - 
 * main -
 *********************************************************/
public class GuiCreator extends JFrame
{
    //Class constants
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int FONT_SIZE = 48;
    public static int gameSize = 2;
    //Class variables 
    /*******************************************/

    private static JLabel makeText(String text, int x, int y, int w, int h) 
    {
        //local constants
        //local variables
        JLabel mainScreentitle = new JLabel();
        /********************************************/
        //set text of label to passed string
        mainScreentitle.setText(text);
        //set color of label to yellow
        mainScreentitle.setForeground(Color.YELLOW);
        //set font of label 
        mainScreentitle.setFont(new Font("Colon", Font.BOLD, FONT_SIZE));
        //set position of label to the center
        mainScreentitle.setHorizontalAlignment(JLabel.CENTER);;
        mainScreentitle.setVerticalAlignment(JLabel.CENTER);
        //set bounds to passed int
        mainScreentitle.setBounds(new Rectangle(x,y, w,h));;
        
        return mainScreentitle;
    }//end makeText 

    private static JFrame makeMainFrame() 
    {
        //local constants
        //locals variable 
        JFrame frame = new JFrame();
        /*******************************************/
        //sets size of frame to the classes constants 
        frame.setSize(WIDTH*gameSize,HEIGHT*gameSize);
        frame.setResizable(false);
        //set background to black 
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        return frame;
    }//end makeMainFrame 

    private static JButton makeButton(String text, int x, int y, int w, int h) 
    {
        //local constants
        //local variables
        JButton button = new JButton();
        /******************************************/
        //get buttons png based on passed text 
        button.setIcon(new ImageIcon("images/" + text + "Gui.png"));
        button.setSize(w,h);
        button.setBounds(new Rectangle(new Point(x,y),new Dimension(w, h)));
        button.setBackground(Color.BLACK);

        return button;
    }//end makeButton
    /**
     * make the title screen and display it,
     * has a 3 buttons. "play game" opens up another
     * frame where user picks a game.
     * "options" sets the size of games.
     * "Quit" closes program.
     * @author Alan
     */
    public static void makeTitleScreen() 
    {
        //local constants
        final JFrame titleScreen = makeMainFrame();
        //local variables 
        JLabel mainScreentitle = makeText("Board Classics", 0, 50, 500, 100);
        JButton playGameButton = makeButton("playGame", 140, 250, 200, 64);
        JButton optionsButton = makeButton("options", 140, 350, 200, 64);
        /******************************************/
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
    }//end makeTitleScreen

    /**
     * Display a screen where user select 
     * a game in 'Board Classics'.
     * 
     * @author Alan
     */
    public static void makeGameSelect()
    {
        //local constants
        //local variabels 
        JFrame gameSelectFrame = makeMainFrame();
        JLabel gameSelectTitle = makeText("Game Selection", 0, -100, 485, 450);
        JButton playChessButton = makeButton("playChess", 140, 250, 200, 64);
        JButton playCheckersButton = makeButton("playCheckers", 140, 350, 200, 64);
        /*****************************************************/
        
        gameSelectFrame.add(gameSelectTitle);

        //when playchessbutton is pressed
        playChessButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //close gameSelectFrame 
                gameSelectFrame.setVisible(false);
                //call chessGame
                chessGame();
            } 
        });
        gameSelectFrame.add(playChessButton);
        //when playcheckersbutton is pressed 
        playCheckersButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //close gameselectFrame 
                gameSelectFrame.setVisible(false);
                //call CheckerGame
                checkersGame();
            }  
        });
        gameSelectFrame.add(playCheckersButton);
    }//end gameSelectScreen
    /**
     * Displays chess game.
     * 
    */
    public static void chessGame()
    {
        //local constants 
        //local variables 
        Screen screen = new Screen(gameSize); //TODO make screen chess class
        JFrame chessFrame = new JFrame();
        JButton surrenderButton = makeButton("surrender", 255, 100, 96, 30);
        /*****************************************************/

        chessFrame.add(screen);
        chessFrame.addKeyListener(screen);
        chessFrame.pack();
        if(gameSize == 2)
        {
            chessFrame.setSize(new Dimension(720,600));
        }
        else if(gameSize == 1)
        {
            chessFrame.setSize(new Dimension(360,300));
        }
        chessFrame.setLayout(null);
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.setVisible(true);

        //when surrenderButton is called 
        surrenderButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //close chess frame 
                chessFrame.setVisible(false);
                //display winScreen
                winScreen(screen.getCurrentTurn()); //TODO add paramater for which turn it is
            }
            
        });
        chessFrame.add(surrenderButton);
    }//end ChessGame

    /**
     * Displays checkers game.
     *
     */
    public static void checkersGame()
    {
        //local constants
        //local variables 
        JFrame checkersFrame = new JFrame();
        Screen screen = new Screen(gameSize);
        JButton surrenderButton = new JButton();
        /************************************/

        checkersFrame.add(screen);  
        checkersFrame.addKeyListener(screen);
        checkersFrame.pack();
        if(gameSize == 2)
        {
            checkersFrame.setSize(new Dimension(720,600));
        }
        else if(gameSize == 1)
        {
            checkersFrame.setSize(new Dimension(360,300));
        }
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
    }//end checkerGame
    /**
     * 
     * 
     * @author Alan
     */
    public static void winScreen(int turn)
    {
        //local constants
        //local variables 
        JFrame winFrame = makeMainFrame();
        JLabel playerXWon = new JLabel();
        JButton playAgainButon = makeButton("playAgain", 140, 100, 200, 64);
        JButton titleScreenButton = makeButton("titleScreen", 140, 200, 200, 64);
        JButton gameSelectButton = makeButton("gameSelect", 140, 300, 200, 64);
        /***********************************************/

        playerXWon.setText("Player " + turn + " Won");;
        playerXWon.setForeground(Color.YELLOW);
        playerXWon.setFont(new Font("", Font.PLAIN, 30));
        playerXWon.setHorizontalAlignment(JLabel.CENTER);
        playerXWon.setVerticalAlignment(JLabel.CENTER);
        playerXWon.setBounds(new Rectangle(0,-200, 485,450));
        winFrame.add(playerXWon);
        
        //when playagainbutton is pressed 
        playAgainButon.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //close winframe 
                winFrame.setVisible(false);
                //call chess game
                chessGame();
            }
        });
        winFrame.add(playAgainButon);

        //wheb titleScreen button is pressed 
        titleScreenButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //close winframe 
                winFrame.setVisible(false);
                //call makeTitleScreen
                makeTitleScreen();
            }

        });
        winFrame.add(titleScreenButton);

        //when gameselectbutton is pressed
        gameSelectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //close winScreen
                winFrame.setVisible(false);
                //open makegameSelect
                makeGameSelect();
            }
            
        });
        winFrame.add(gameSelectButton);
    }//close winScreen
    /**
     * 
     * @param args
     * @throws Exception
     */ 
    public static void main(String[] args) throws Exception 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                makeTitleScreen();

                System.out.println("Space changes turns, it has to be player 1's turn to move white's pieces" +
                " and player 2's turn to move black's pieces") ;
            }
        });
    }//end main
}//end guiCreator

