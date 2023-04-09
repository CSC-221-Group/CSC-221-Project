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
    private static final int WIDTH = 500;   //
    private static final int HEIGHT = 500;  //
    private static final int FONT_SIZE = 48;//

    //Class variables 
    public static int turn = Screen.currentTurn; 
    public static int gameSize = Screen.getGameSize(); //
    public static Screen screen = new Screen(gameSize);//
    public static JLabel move = new JLabel();          //
    /*******************************************/
    /**
     * This method creates a JLabel and return it.
     * @param text - text that will be put on JLable 
     * @param x 
     * @param y
     * @param w
     * @param h
     * @return jlabel - returns label
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
     * This method creates a JFrame and return it.
     * 
     * @return Jframe - returns a frame
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
     * @return jButton - return button
     */
    private static JButton makeButton(String text, int x, int y, int w, int h) 
    {
        //local constants
        //local variables
        JButton button = new JButton();
        /******************************************/
        //gets buttons png based on passed text 
        button.setIcon(new ImageIcon("images/" + text + "Gui.png"));
        button.setSize(w,h);
        button.setBounds(new Rectangle(new Point(x,y),new Dimension(w, h)));
        //sets button color to black
        button.setBackground(Color.BLACK);

        return button;

    }//end makeButton

    /**
    * This method creates the title screen for Board Classics
    * 
    */
    public static void makeTitleScreen() 
    {
        //local constants
        //local variables 
        JFrame titleScreen = makeMainFrame();
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

        //When optionButtons is clicked 
        optionsButton.addActionListener(new ActionListener()
        { 
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //make titleScreen close
                titleScreen.setVisible(false);
                //open options creen
                optionsScreen();
            }
        });
        titleScreen.add(optionsButton);

    }//end makeTitleScreen

    /**
     * Display a screen where user select a game in Board Classics.
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
        Screen screen = new Screen(gameSize);
        JFrame chessFrame = new JFrame();
        JPanel movePanel = new JPanel();
        JButton drawbt = new JButton();
        JButton surrenderButton = makeButton("surrender", 255, 100, 96, 30);
        /*****************************************************/
        
        movePanel.setBackground(Color.BLACK);
        movePanel.add(move);

        chessFrame.add(movePanel);
        chessFrame.add(screen);
        chessFrame.pack();
        chessFrame.addKeyListener(screen);
        chessFrame.setLayout(null);

        //IF gameSize equal 1 
        if(gameSize == 1)
        {
            //Set variables to fit board
            chessFrame.setSize(new Dimension(360,294));
            drawbt.setBounds(new Rectangle(new Point(250,150),new Dimension(100, 35)));
            move.setBounds(new Rectangle(new Point(250,170),new Dimension(100, 35)));
            movePanel.setBounds(new Rectangle(new Point(250,190),new Dimension(100, 35)));
        }
        //ELSE IF gameSize equal 2
        else if(gameSize == 2)
        {
            //Set variables to fit board
            chessFrame.setSize(new Dimension(720,600));
            surrenderButton.setLocation(510, 300);
            drawbt.setBounds(new Rectangle(new Point(510,255),new Dimension(100, 35)));
            move.setBounds(new Rectangle(new Point(510,170),new Dimension(100, 35)));
            movePanel.setBounds(new Rectangle(new Point(510,190),new Dimension(100, 35)));
        }
        //ELSE
        else
        {
            //Set variables to fit board
            chessFrame.setSize(new Dimension(1080,900));
            surrenderButton.setLocation(767, 300);
            drawbt.setBounds(new Rectangle(new Point(767,255),new Dimension(100, 35)));
            move.setBounds(new Rectangle(new Point(767,170),new Dimension(100, 35)));
            movePanel.setBounds(new Rectangle(new Point(767,190),new Dimension(100, 35)));
        }//END IF

        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessFrame.setVisible(true);
        //When surrenderButton is pressed 
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

        drawbt.setBackground(Color.BLACK);
        //When drawnbt is pressed 
        drawbt.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //set Turn equal to 3
                turn = 3;
                //close chess frame
                chessFrame.setVisible(false);
                //display winScreen
                winScreen();
            }
        });
        chessFrame.add(drawbt);

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
        JButton surrenderButton = new JButton();
        Screen screen = new Screen(gameSize);
        /************************************/

        if(gameSize == 2)
        {
            checkersFrame.setSize(new Dimension(720,600));
        }
        else if(gameSize == 1)
        {
            checkersFrame.setSize(new Dimension(360,300));
        }

        checkersFrame.add(screen);  
        checkersFrame.addKeyListener(screen);
        checkersFrame.pack();
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
        
        //IF Turn equal 1 
        if(turn == 1)
        {
            //Display what player two
            playerXWon.setText("Player " + (turn + 1)  + " Won");
        }
        //ELSE IF currentTurn equal two
        else if(turn == 2)
        {
            //Display what player 1
            playerXWon.setText("Player " + (turn - 1) + " Won");
        }
        //ELSE Turn equal 3
        else
        {
            //Display players have drawn
            playerXWon.setText("Draw");
            //set currentTurn equal to 1
            turn = 1;
        }//END IF

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

        //when titleScreen button is pressed 
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

    /*
     * Displays options screen
     * 
     */
    public static void optionsScreen()
    {
        //local constants
        //local variables
        JFrame optionsFrame = makeMainFrame();
        JRadioButton  threeX = new   JRadioButton ("3x");
        JRadioButton  twoX = new  JRadioButton ("2x");
        JRadioButton oneX = new JRadioButton("1x");
        /************************************************/

        optionsFrame.setBackground(Color.BLACK);
        optionsFrame.setLayout(new GridLayout(0,1));

        //When threeX is pressed
        threeX.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            { 
                //set gameSize to 3 
                gameSize = 3;
                //close options frame 
                optionsFrame.setVisible(false);
                //open title screen
                makeTitleScreen();
            }
        });
        optionsFrame.add(threeX);

        //When twoX is pressed 
        twoX.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //set gameSize to 2
                gameSize = 2;
                //close options frame
                optionsFrame.setVisible(false);
                //open title screen
                makeTitleScreen();
            }
        });
        optionsFrame.add(twoX);

        //When oneX is clicked 
        oneX.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //set gameSize to 1
                gameSize = 1;
                //close options frame 
                optionsFrame.setVisible(false);
                //open title screen
                makeTitleScreen();
            }
        });
        optionsFrame.add(oneX);

    }//end optionsScreen

    /*
     * Displays promote screen
     */
    public static void promoteScreen(int x , int y)
    {
        //local constants 
        //local variables 
        JFrame promoteScreen = new JFrame();
        JButton Queenbt = new JButton();
        JButton Rookbt= new JButton();
        JButton Knightbt = new JButton();
        JButton Bishopbt = new JButton();
        /**************************************/
       
        promoteScreen.setLayout(new GridLayout(0,1));
        promoteScreen.setVisible(true);

        Queenbt.setIcon(new ImageIcon("images/Chess/whiteQueen.png"));
        Queenbt.setSize(100,100);
        Queenbt.setBackground(Color.BLACK);
        //When Queenbt is pressed 
        Queenbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //IF pawn at y:0
                if(y == 0)
                {
                    //promote pawn to black Queen
                    screen.promotePawn("black",2,x,y, "Queen");
                    //close promote screen
                    promoteScreen.setVisible(false);
                }
                //ELSE pawn at y:7
                else
                {
                    //promote pawn to white Queen
                    screen.promotePawn("white",1,x,y, "Queen");
                    //close promote screen
                    promoteScreen.setVisible(false);
                }//END IF
            }
        });
        promoteScreen.add(Queenbt);

        Rookbt.setIcon(new ImageIcon("images/Chess/whiteRook.png"));
        Rookbt.setSize(100,100);
        Rookbt.setBackground(Color.BLACK);
        //when Rookbt is pressed 
        Rookbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //IF pawn at y:0
                if(y == 0)
                {
                    //promote pawn to black rook
                    screen.promotePawn("black",2,x,y, "Rook");
                    //close promote screen
                    promoteScreen.setVisible(false);
                }
                //ELSE pawn at y:7
                else
                {
                    screen.promotePawn("white",1,x,y, "Rook");
                    promoteScreen.setVisible(false);
                }//END IF
            }
        });
        promoteScreen.add(Rookbt);

        Knightbt.setIcon(new ImageIcon("images/Chess/whiteKnight.png"));
        Knightbt.setSize(100,100);
        Knightbt.setBackground(Color.BLACK);
        //When Knighbt is pressed 
        Knightbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //IF pawn at y:0
                if(y == 0)
                {
                    //promote pawn to black Knight
                    screen.promotePawn("black",2,x,y, "Knight");
                    //close promote screen
                    promoteScreen.setVisible(false); 
                }
                //ELSE pawn at y:7
                else
                {
                   screen.promotePawn("white",1,x,y, "Knight");
                    promoteScreen.setVisible(false);

                }//END IF
            }
        });
        promoteScreen.add(Knightbt);
        
        Bishopbt.setIcon(new ImageIcon("images/Chess/whiteBishop.png"));
        Bishopbt.setSize(100,100); 
        Bishopbt.setBackground(Color.BLACK);
        //When Bishopbt is pressed 
        Bishopbt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //IF pawn at y:0
                if(y == 0)
                {
                    //promote pawn to black bishop
                    screen.promotePawn("black",2,x,y, "Bishop");
                    //close promote screen
                    promoteScreen.setVisible(false);
                }
                //ELSE pawn at y:7
                else
                {
                    //promote pawn to white bishop
                    screen.promotePawn("white",1,x,y, "Bishop");
                    //close promote screen
                    promoteScreen.setVisible(false);
                }//END IF
            }       
        });
        promoteScreen.add(Bishopbt);

        promoteScreen.setVisible(true);
        promoteScreen.pack();

    }//end promoteScreen
   
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
            }
        });
    }//end main

}//end guiCreator
