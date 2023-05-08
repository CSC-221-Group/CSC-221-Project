package main.java.Chess.frontend;

import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
 * winScreen - 
 * main -
 *********************************************************/
public class guiCreator
{
    //Class constants
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int FONT_SIZE = 48;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 64;
    private static final int OPTIONS_BUTTON_WIDTH = 32;
    private static final int OPTIONS_BUTTON_HEIGHT = 32;
    public static int gameSize = 1;
    //Class variables 
    public static int turn = 1; 
    public static Screen screen = new Screen(gameSize);//
    public static JLabel move = new JLabel();         //
    /*******************************************/
    
    /**********************************************************
    * Method Name    : makeText 
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: This method creates a JLabel and return it.
    **********************************************************/
    private static JLabel makeText(String text, int x, int y, int w, int h, int gameSize) 
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
        mainScreentitle.setFont(new Font("Colon", Font.BOLD, FONT_SIZE * gameSize));
        //set position of label to the center
        mainScreentitle.setHorizontalAlignment(JLabel.CENTER);;
        mainScreentitle.setVerticalAlignment(JLabel.CENTER);
        //set bounds to passed int
        w*=gameSize;
        h*=gameSize;

        mainScreentitle.setBounds(new Rectangle(x,y, w,h));;
        
        return mainScreentitle;
    }//end makeText 

    /**********************************************************
    * Method Name    : makeMainFrame
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: This method creates a JFrame and return it.
    **********************************************************/
    private static JFrame makeMainFrame() 
    {
        //local constants
        //locals variable 
        JFrame frame = new JFrame();
        /*******************************************/
        //sets size of frame to the classes constants 
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        //IF Screen width is less than width times games size or screen lenght less than height times gamesize
        if(screenSize.getWidth() < WIDTH*gameSize || screenSize.getHeight() < HEIGHT*gameSize)
        {
            gameSize = gameSize-1;
            System.out.println("Screen size too small, reducing game size to " + gameSize);
            return makeMainFrame();
        }//END IF 

        frame.setSize(WIDTH*gameSize,HEIGHT*gameSize);
        frame.setResizable(false);
        //set background to black 
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        return frame;
    }//end makeMainFrame 

    /**********************************************************
    * Method Name    : makeButton
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: This method creates a JButton and return it.
    **********************************************************/
    private static JButton makeButton(String text, int x, int y, int w, int h, int imageSize) 
    {
        //local constants
        //local variables
        JButton button = new JButton();
        /******************************************/
        //get buttons png based on passed text 
        button.setIcon(new ImageIcon("images/" + text + "Gui.png"));
        
        //IF imageSize greater than or equal to 2 
        if(imageSize >= 2)
        {
            // Increase the size of the image
            w *= imageSize;
            h *= imageSize;
            x *= imageSize;
            y *= imageSize;
            BufferedImage icon;
            // Read the image
            try 
            {
                icon = ImageIO.read(new File("images/" + text + "Gui.png"));
            } 
            catch (IOException e) 
            {
                System.out.println("Error: " + e);
                return null;
            }
            // Resize the image
            BufferedImage newImage = new BufferedImage(icon.getWidth() * gameSize, icon.getHeight() * gameSize, BufferedImage.TYPE_INT_ARGB);
            Graphics g = newImage.createGraphics();
            g.drawImage(icon, 0, 0, icon.getWidth() * gameSize, icon.getHeight() * gameSize, null);
            g.dispose();
            icon = newImage;
            // Set the new image
            ImageIcon newIcon = new ImageIcon(icon);
            button.setIcon(newIcon);
            button.setSize(icon.getWidth(), icon.getHeight());
            button.setBounds(new Rectangle(new Point(x,y),new Dimension(icon.getWidth(), icon.getHeight())));
            // Set the new size
        }
        //ELSE gamesize is 1 
        else 
        {
            button.setSize(w,h);
            button.setBounds(new Rectangle(new Point(x,y),new Dimension(w, h)));
        }//END IF 
        button.setBackground(Color.BLACK);
        
        return button;
    }//end makeButton

    /**********************************************************
    * Method Name    : makeTtielScreen
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: This method creates the title screen for Board Classics
    **********************************************************/
    public static void makeTitleScreen() 
    {
        //local constants
        //local variables 
        JFrame titleScreen = makeMainFrame();
        JLabel mainScreentitle = makeText("Board Classics", 0, 0, 500, 100, gameSize);
        JButton playGameButton = makeButton("playGame", WIDTH/4, HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT, gameSize);
        JButton optionsButton = makeButton("options", WIDTH/4, (HEIGHT/2) + (BUTTON_HEIGHT+(BUTTON_HEIGHT/2)), BUTTON_WIDTH, BUTTON_HEIGHT, gameSize);
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
                //open options screen
                optionsScreen();
            }
        });
        titleScreen.add(optionsButton);

    }//end makeTitleScreen
    
    /**********************************************************
    * Method Name    : makeGameScreen
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Display a screen where user select a game in Board Classics.
    **********************************************************/
    public static void makeGameSelect()
    {
        //local constants
        //local variabels 
        JFrame gameSelectFrame = makeMainFrame();
        JLabel gameSelectTitle = makeText("Game Selection", 0, 0,  500, 100, gameSize);
        JButton playChessButton = makeButton("playChess", WIDTH/4, HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT, gameSize);
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

    }//end gameSelectScreen

    /**********************************************************
    * Method Name    : chessGame
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Displays chess game.
    **********************************************************/
    public static void chessGame()
    {
        //local constants 
        final int WIDTH = 400;
        final int HEIGHT = 300;
        //local variables 
        move.setText(null);
        Screen screen = new Screen(gameSize);
        JFrame chessFrame = new JFrame();
        JPanel movePanel = new JPanel();
        JButton drawbt = makeButton("draw",256,130,100, 30,gameSize);
        JButton surrenderButton = makeButton("surrender", 256, 100, 96, 30, gameSize);
        /*****************************************************/
        move.setForeground(Color.YELLOW);
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
            chessFrame.setSize(new Dimension(WIDTH,HEIGHT));
            move.setFont(new Font("Colon", Font.BOLD, 7));
            movePanel.setBounds(new Rectangle(new Point(256,164),new Dimension(96, 20)));
        }
        //ELSE gameSize == 2
        else
        {
            //Set variables to fit board
            chessFrame.setSize(new Dimension(WIDTH*gameSize,HEIGHT*gameSize));
            move.setFont(new Font("Colon", Font.BOLD, 14));
            movePanel.setBounds(new Rectangle(new Point(256*gameSize,164*gameSize),new Dimension(192, 40)));
        }

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

    /**********************************************************
    * Method Name    : winScreen
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description:Displays win screen of 'board classics'
    **********************************************************/
    public static void winScreen()
    {
        //local constants
        //local variables 
        JFrame winFrame = makeMainFrame();
        JLabel playerXWon = makeText(" ", 0, 0,  500, 100, gameSize);
        JButton playAgainButon = makeButton("playAgain", 140, 100, 200, 64, gameSize);
        JButton titleScreenButton = makeButton("titleScreen", 140, 200, 200, 64, gameSize);
        JButton gameSelectButton = makeButton("gameSelect", 140, 300, 200, 64, gameSize);
        /***********************************************/
        playerXWon.setText(" ");
        //IF Turn equal 1 
        if(turn == 1)
        {
            //Display what player two
            playerXWon.setText("Player " + (turn + 1)  + " Won");
        }
        else if(turn == 2)
        {
            //Display what player one
            playerXWon.setText("Player " + (turn - 1)  + " Won");
        }
        //ELSE Turn equal 3
        else
        {
            //Display players have drawn
            playerXWon.setText("Draw");
            //set currentTurn equal to 1
        }//END IF
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

    /**********************************************************
    * Method Name    : optionsScreen
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Displays options screen
    **********************************************************/
    public static void optionsScreen()
    {
        //local constants
        //local variables
        JLabel options = makeText("Set size",0, 0,  500, 100, gameSize);
        JFrame optionsFrame = makeMainFrame();
        JButton twoX =  makeButton("2x", WIDTH/2, HEIGHT/4, OPTIONS_BUTTON_WIDTH, OPTIONS_BUTTON_HEIGHT,gameSize);
        JButton oneX =  makeButton("1x",WIDTH/2, (HEIGHT/4) + (OPTIONS_BUTTON_HEIGHT+(OPTIONS_BUTTON_HEIGHT/2)), OPTIONS_BUTTON_WIDTH, OPTIONS_BUTTON_HEIGHT,gameSize);
        JButton accept = makeButton("accept", WIDTH/3,  (HEIGHT/4) + (OPTIONS_BUTTON_HEIGHT*2+(BUTTON_HEIGHT)), BUTTON_WIDTH, BUTTON_HEIGHT,gameSize);
        /************************************************/
        optionsFrame.add(options);
        optionsFrame.setBackground(Color.BLACK);
        optionsFrame.setLayout(null);

        //When twoX is pressed 
        twoX.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //set gameSize to 2
                gameSize = 2;
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
            }
        });
        optionsFrame.add(oneX);

       //When accept X is clicked 
        accept.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                optionsFrame.setVisible(false);
                makeTitleScreen();
            }
        });
        optionsFrame.add(accept);
    }//end optionsScreen

    /**********************************************************
    * Method Name    : optionsScreen
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Displays promote screen
    **********************************************************/
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
        Queenbt.setSize(100,100); // TODO adjust this with gameSize
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
    
    /**********************************************************
    * Method Name    : main
    * Author         : Jordan/Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Displays makeTitleScreen
    **********************************************************/
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
