package Chess.frontend;

import Chess.pieces.Piece;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Chess.pieces.Chess.*;
import Chess.pieces.InvalidMovementException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
/**********************************************************
 * Program Name   : Screen 
 * Author         : Jordan 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program draws the board and piece 
 *  and displays them, also assgins the piece to black or white and
 * update the pieces location if piece was moved.
 *  
 * Methods:
 * -------
 * Screen - displays the board.
 * initScreen - sets the size and design of the board.
 * initGame - starts the game by starting the timer and reseting board and assigning pieces.
 * actionPerfromed - checks where all the pieces are and redraws them.
 * keyPressed - Checks if spacebar was pressed and switches player if it was pressed.
 * paintComponent - Displays the pieces and board.
 * resetBoard - Sets the chess baord to the starting position.
 * assignPieces - assign the pieces to player 1 or 2.
 * drawBackground - Draws the board. 
 * draw - Draws the pieces.
 * getCurrentTurn - gets what players turn is it.
 **********************************************************/
public class Screen extends JPanel implements ActionListener, KeyListener 
{
    //class constants 
    public static int gameSize = 1;  //size of the board
    private final int DELAY = 10;          //delay between each frame in ms
    public static final int TILE_SIZE = 32;//size of square on board
    public static final int ROWS = 8;      //size of the board horizontally 
    public static final int COLS = 8;      //size of the board vertically 
    //private static final int serialVersionUID = 1;

    //class variables 
    private Timer timer;                                       //triggers actionPerformed()
    private int currentTurn = 1;                               //1 = player1, 2 = player2
    private ArrayList<Piece> p1Pieces = new ArrayList<Piece>();//Pieces owned by White
    private ArrayList<Piece> p2Pieces = new ArrayList<Piece>();//Pieces owned by Black
    private Cell[][] cells = new Cell[ROWS][COLS];             //Makes a cell object for every sqaure on the board
    /**********************************************************
     * Program Name   : mouseAdapter 
     * Author         : Jordan 
     * Date           : 3/19/23
     * Course/Section : Software Engineering 221-301
     * Program Description: This class checks all the mouse movements events
     * that happened on the board and updates accordingly  
     *  
     * Methods:
     * -------
     * mousePressed - Checks where the mouse was pressed and if the player owns that piece or not.
     * mouseReleased - Checks where the mouse was realed and update the cell where it was released.
     * updateLocation - redraws the piece at the location it was released.
     **********************************************************/
    private class mouseAdapter extends MouseAdapter 
    {
        int preX, preY;
        boolean mousePressed = false;
        Piece currentPiece = null;

        private void displayLocationError() {
            System.out.println("Mouse pressed outside of board");
            System.out.println("GameSize = " + gameSize + " Tile Size = " + TILE_SIZE);
            System.out.println("Mouse X: " + preX + " Mouse Y: " + preY);
            System.out.println("Cords: " + (preX / TILE_SIZE) + ", " + ((ROWS-1) - (preY / TILE_SIZE)));
            System.out.println("Adjusted Cords: " + (preX / (TILE_SIZE * gameSize)) + ", " + ((ROWS-1) - (preY / (TILE_SIZE * gameSize))));
        }

        /**
        * This methode determines which cell was clicked and sets the currentPiece variable
        * to the piece occupying that cell if it is owned by the current turn's player.
        * @param e The mouse event that triggered this method.
        */
        @Override
        public void mousePressed(MouseEvent e)
        {
            //if the where the mouse click is outside the game board.
            int x = e.getX() / (TILE_SIZE * gameSize);
            int y = (ROWS-1) - (e.getY() / (TILE_SIZE * gameSize));
            if(x < 0 || y < 0) {
                displayLocationError();
                return;
            }
            if(x > COLS || y > ROWS )
            {
                displayLocationError();
                return;
            }//end IF

            //the cell that was clicked.
            Cell cell = cells[e.getX() / (TILE_SIZE * gameSize)][(ROWS-1) - (e.getY() / (TILE_SIZE * gameSize))];

            //if the cell is unoccupied.
            if(!cell.isOccupied())
            {
                return;
            }//end IF

            //if the piece thats clicked is not owned by player who clicked it
            if(cell.getPiece().getOwnedBy() != currentTurn)
            {
                //print out who the piece is owned by
                System.out.println(cell.getPiece().getOwnedBy() + " != " + currentTurn);
                return;
            }//end IF 

            currentPiece = cell.getPiece();
            mousePressed = true;
            preX = e.getX() / (TILE_SIZE * gameSize);
            preY = (ROWS-1) - (e.getY() / (TILE_SIZE * gameSize));
        }//end mousePressed


        @Override
        public void mouseDragged(MouseEvent e) 
        {
            // Not needed for now
        }

        /**
        * This method checks where the mouse was released.
        * and prints out where the mouse was pressed.
        * @param e The mouse event.
        */
        @Override
        public void mouseReleased(MouseEvent e)
        {
            //iF mouse not pressed
            if (!mousePressed)
            {
                return;
            }//end IF

            //print where the mouse was clikced 
            System.out.println("Mouse released at " + e.getX() / (TILE_SIZE * gameSize) + ", " + ((ROWS-1) - (e.getY() / (TILE_SIZE * gameSize))));
            mousePressed = false;
            updateLocation(e);
        }//end mouseRelease

        /**
        * Updates the location of the current piece based on 
        *    the coordinates of the mouse release event.
        * @param e The mouse release event.
        */
        public void updateLocation(MouseEvent e)
        {
            int x = e.getX() / (TILE_SIZE * gameSize);
            int y = (ROWS-1) - (e.getY() / (TILE_SIZE * gameSize));
            Cell cell;

            //if x or y outside the board 
            if (x > COLS || y > ROWS)
            {
                return;
            }//end if 
            

            try {
                cell = cells[x][y];
                currentPiece.move(x, y);           
                cells[preX][preY].setPiece(null);
                cells[x][y].setPiece(currentPiece);
                currentPiece = null;
            } 
            catch (InvalidMovementException | ArrayIndexOutOfBoundsException | ConcurrentModificationException ex) {
                System.out.println(ex.getMessage());
            }
            repaint();
        }//end update location

    }//end mouseAdapter
    /**
    * Constructor for Screen.
    * Displays the screen and pieces.
    */
    public Screen(int gameSize)
    {
        initScreen(gameSize);
    }

    /**
    * Initializes the screen with appropriate listeners
    * and dimensions, and initializes the game.
    */
    private void initScreen(int gameSize)
    {
        setGameSize(gameSize);
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(COLS * (TILE_SIZE * gameSize), ROWS * (TILE_SIZE * gameSize)));
        addMouseMotionListener(new mouseAdapter());
        addMouseListener(new mouseAdapter());
        setBackground(Color.BLACK);
        initGame();
    }//end initScreen

   /**
    * Initializes the game by starting the timer, 
    * resetting the board, and assigning pieces.
    */
    private void initGame()
    {
        timer = new Timer(DELAY, this);
        timer.start();
        resetBoard();
        assignPieces();
    }//end initGame
    
    /**
    * This method is called by the Swing Timer every time the timer clicks.
    *  It updates the state of each piece on the board and
    *  repaints the game screen.
    */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //for every piece white owns 
        for (Piece piece : p1Pieces)
        {
            piece.update();
        }//END FOR
        //for every piece black owns 
        for (Piece piece : p2Pieces)
        {
            piece.update();
        }//END FOR
        
        repaint();
    }//end actionPerformed 

    /** 
     * @param e
     */
    // Key Binds
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    /**
     * keyPrssed is called when a key is pressed. 
     * checks if the spacebar was pressed, 
     * it switches the current turn to the other player. 
     * Player 1 is represented by the int 1, 
     * and player 2 by the int 2.
     */
    @Override
    public void keyPressed(KeyEvent e) 
    {
        //checks if the key that was pressed was space
       if (e.getKeyCode() == KeyEvent.VK_SPACE) 
       {
            //print out space bar was pressed 
           System.out.println("Spacebar pressed");
           //if its player 1 turn
           if (currentTurn == 1) 
           {
               //set current turn to player 2
               currentTurn = 2;
           }
           //else if its player 2 turn
           else if (currentTurn == 2) 
           {
               //set current turn to player 1
               currentTurn = 1;
           }//end if 
       }//end if 
    }//end keypressed 

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }

    /**
    * Paints the game board and pieces on it.
    * @param g the graphics object used for painting
    */
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawBackground(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }//end painComponent

    /**
    *set the chess board to the starting position.
    */
    private void resetBoard() 
    {
        cells[0][0] = new Cell(0, 0, new Rook(false, 0, 0, 2)); // ROOK
        cells[1][0] = new Cell(1, 0, new Knight(false, 1, 0, 2)); // KNIGHT
        cells[2][0] = new Cell(2, 0, new Bishop(false, 2, 0, 2)); // BISHOP
        cells[3][0] = new Cell(3, 0, new Queen(false, 3, 0, 2)); // QUEEN
        cells[4][0] = new Cell(4, 0, new King(false, 4, 0, 2)); // KING
        cells[5][0] = new Cell(5, 0, new Bishop(false, 5, 0, 2)); // BISHOP
        cells[6][0] = new Cell(6, 0, new Knight(false, 6, 0, 2)); // KNIGHT
        cells[7][0] = new Cell(7, 0, new Rook(false, 7, 0, 2)); // ROOK
        
        for (int i = 0; i < 8; i++) 
        {
            //set a white pawn on the board in the x positon of i and y positon of 1.
            cells[i][1] = new Cell(i, 1, new Pawn(false, i, 1, 2)); // PAWN
        }//end For

        cells[0][7] = new Cell(0, 7, new Rook(true, 0, 7, 1)); // ROOK
        cells[1][7] = new Cell(1, 7, new Knight(true, 1, 7, 1)); // KNIGHT
        cells[2][7] = new Cell(2, 7, new Bishop(true, 2, 7, 1)); // BISHOP
        cells[3][7] = new Cell(3, 7, new Queen(true, 3, 7, 1)); // QUEEN
        cells[4][7] = new Cell(4, 7, new King(true, 4, 7, 1)); // KING
        cells[5][7] = new Cell(5, 7, new Bishop(true, 5, 7, 1)); // BISHOP
        cells[6][7] = new Cell(6, 7, new Knight(true, 6, 7, 1)); // KNIGHT
        cells[7][7] = new Cell(7, 7, new Rook(true, 7, 7, 1)); // ROOK
        
        for (int i = 0; i < 8; i++) 
        {
            //set a black pawn on the board in the the x position of i and y positon of 6.
            cells[i][6] = new Cell(i, 6, new Pawn(true, i, 6, 1)); // PAWN
        }//end FOF

        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 2; j < 6; j++) 
            {
                //creates a new cell for every square on the board
                cells[i][j] = new Cell(i, j);
            }
        }
    }//end resetBoard

    /**
    * This method assigns the pieces to the players, 
    * by iterating through each cell on the board 
    * and checking if it contains a piece. 
    * If a piece is found, it is added to the list of pieces owned by 
    * the corresponding player (player 1 or player 2).
    */
    private void assignPieces() 
    {
        p1Pieces.clear();
        p2Pieces.clear();
        //For every row 
        for (int i = 0; i < ROWS; i++) 
        {
            //for every collum 
            for (int j = 0; j < COLS; j++) 
            {
                //IF the Cell is does have a piece
                if (cells[i][j].getPiece() != null)
                {
                    //IF Piece in the cell is owned by white 
                    if (cells[i][j].getPiece().getOwnedBy() == 1) 
                    {
                        //give the piece to whites 
                        p1Pieces.add(cells[i][j].getPiece());
                    } 
                    else if (cells[i][j].getPiece().getOwnedBy() == 2) 
                    {
                        //give the piece to black 
                        p2Pieces.add(cells[i][j].getPiece());
                    }//end IF 
                }//end IF 
            }//END FOR
        }//END FOR 
    }//end assign piecees 
    /**
    * Draws the background of the chess board.
    * @param g the graphics object used for drawing
    */
    private void drawBackground(Graphics g) 
    {
        g.setColor(Color.WHITE);
        // for every other index in the array, draw a white square
        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 0; j < COLS; j++) 
            {
                if ((i + j) % 2 == 0) 
                {
                    g.fillRect(j * (TILE_SIZE * gameSize), i * (TILE_SIZE * gameSize), TILE_SIZE * gameSize, TILE_SIZE * gameSize);
                }
            }
        }
    }
    /**
    * Draws all the pieces on the board using the Graphics object
    * @param g the Graphics object used for drawing
    */
    private void draw(Graphics g) 
    {
        for (Piece piece : p1Pieces) 
        {
            piece.draw(g, this);
        }
        for (Piece piece : p2Pieces)
        {
            piece.draw(g, this);
        }
    }
    /**
    * Returns the current turn number of the game.
    * @return an integer representing the current turn number.
    */
    public int getCurrentTurn() 
    {
        return currentTurn;
    }

    /**
     * Returns the game size of the game.
     * @return an integer representing the game size.
     */
    public int getGameSize()
    {
        return gameSize;
    }
    /**
     * Sets the game size of the game.
     * @param gameSize an integer representing the game size.
     */
    public void setGameSize(int gameSize)
    {
        this.gameSize = gameSize;
    }

}//end Screen
