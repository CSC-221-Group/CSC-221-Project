package main.java.Chess.frontend;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
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
public class guiCreator extends JFrame
{
    //Class constants
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int FONT_SIZE = 48;
    public static int gameSize = 2;
    private static Screen screen = new Screen(gameSize);
    //Class variables 
    public static Cell cell;
    /*******************************************/
    /**
     * This method creates a Jlabel and return it.
     * @param text - text that will be put on jlable 
     * @param x 
     * @param y
     * @param w
     * @param h
     * @return jlabel 
     */
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
/**
     * This method creates a Frame and return it.
     * @return Jframe
     */
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
    /**
     * This method creates a JButton and return it.
     * @param text - text that will be put on Button
     * @param x 
     * @param y
     * @param w
     * @param h
     * @return jButton
     */
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
    * This method creates the title screen for 
    * boardClassics
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

        optionsButton.addActionListener(new ActionListener()
        { 
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //make titleScreen close
                titleScreen.setVisible(false);
                //open game select screen
                optionsScreen();
         }
        });
        titleScreen.add(optionsButton);
    }//end makeTitleScreen

    /**
     * Display a screen where user select 
     * a game in 'Board Classics'.
     * 
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
        //TODO make screen chess class
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
                winScreen(); 
                //TODO add paramater for which turn it is
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
               winScreen();
            }
            
        }); 
        checkersFrame.add(surrenderButton);
    }//end checkerGame
    /**
     * Displays win screen of 'board classics'
     */
    public static void winScreen()
    {
        //local constants
        //local variables 
        JFrame winFrame = makeMainFrame();
        JLabel playerXWon = new JLabel();
        JButton playAgainButon = makeButton("playAgain", 140, 100, 200, 64);
        JButton titleScreenButton = makeButton("titleScreen", 140, 200, 200, 64);
        JButton gameSelectButton = makeButton("gameSelect", 140, 300, 200, 64);
        /***********************************************/
        playerXWon.setText("Player " + Screen.currentTurn + " Won");;
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

    public static void optionsScreen()
    {
        JFrame optionsFrame = makeMainFrame();
        JRadioButton fourX = new  JRadioButton ("4x");
        JRadioButton  threeX = new   JRadioButton ("3x");
        JRadioButton  twoX = new  JRadioButton ("2x");
        optionsFrame.setLayout(new GridLayout(0,1));
        optionsFrame.add(fourX);
        optionsFrame.add(threeX);
        optionsFrame.add(twoX);

    }

    public static void promoteScreen(int x , int y)
    {
        JFrame promoteScreen = new JFrame();
        //Screen screen = new Screen();
        promoteScreen.setLayout(new GridLayout(0,1));
        promoteScreen.setVisible(true);
        JButton Queenbt = new JButton();
        Queenbt.setIcon(new ImageIcon("images/Chess/whiteQueen.png"));
        Queenbt.setSize(100,100);
        Queenbt.setBackground(Color.BLACK);
        Queenbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(y == 0)
                {
                    screen.promotePawn("black",2,x,y, "Queen");
                    promoteScreen.setVisible(false);
                }
                else
                {
                    
                    screen.promotePawn("white",1,x,y, "Queen");
                    promoteScreen.setVisible(false);

                }
              
            }
            
        });
        promoteScreen.add(Queenbt);

        JButton Rookbt= new JButton();
        Rookbt.setIcon(new ImageIcon("images/Chess/whiteRook.png"));
        Rookbt.setSize(100,100);
        Rookbt.setBackground(Color.BLACK);
        Rookbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(y == 0)
                {
                    screen.promotePawn("black",2,x,y, "Rook");
                    promoteScreen.setVisible(false);


                }
                else
                {
                    screen.promotePawn("white",1,x,y, "Rook");
                    promoteScreen.setVisible(false);
                }
              
            }
            
        });
        promoteScreen.add(Rookbt);

        JButton Knightbt = new JButton();
        Knightbt.setIcon(new ImageIcon("images/Chess/whiteKnight.png"));
        Knightbt.setSize(100,100);
        Knightbt.setBackground(Color.BLACK);
        Knightbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(y == 0)
                {
                    screen.promotePawn("black",2,x,y, "Knight");
                    promoteScreen.setVisible(false); 
                }
                else
                {
                    screen.promotePawn("white",1,x,y, "Knight");
                    promoteScreen.setVisible(false);

                }
              
            }
            
        });
        promoteScreen.add(Knightbt);

        JButton Bishopbt = new JButton();
        Bishopbt.setIcon(new ImageIcon("images/Chess/whiteBishop.png"));
        Bishopbt.setSize(100,100); 
        Bishopbt.setBackground(Color.BLACK);
        Bishopbt.addActionListener(new ActionListener() 
        {
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(y == 0)
                {
                    screen.promotePawn("black",2,x,y, "Bishop");
                    promoteScreen.setVisible(false);

                }
                else
                {
                    screen.promotePawn("white",1,x,y, "Bishop");
                    promoteScreen.setVisible(false);
                }
            }
            
        });
        promoteScreen.add(Bishopbt);
        promoteScreen.pack();
    }  
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
