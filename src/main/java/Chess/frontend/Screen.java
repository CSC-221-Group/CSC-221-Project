package main.java.Chess.frontend;

import javax.swing.*;
import main.Piece.Piece;
import java.awt.*;
import java.awt.event.*;
//import java.util.Random;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import main.Piece.ChessPieces.*;
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
    private final int DELAY = 10;          //delay between each frame in ms
    public static final int TILE_SIZE = 32;//size of square on board
    public static final int ROWS = 8;      //size of the board horizontally 
    public static final int COLS = 8;      //size of the board vertically 
    //private static final int serialVersionUID = 1;

    //Class variables 
    public static int gameSize = 1;                                    //size of the board
    private Timer timer;                                               //triggers actionPerformed()
    public static int currentTurn = 1;                                        //1 = player1, 2 = player2
    private static  ArrayList<Piece> p1Pieces = new ArrayList<Piece>();//Pieces owned by White
    private static ArrayList<Piece> p2Pieces = new ArrayList<Piece>(); //Pieces owned by Black
    public static Cell[][] cells = new Cell[ROWS][COLS];               //Boards squares
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
        //Class constants
        //Class variable
        int preX, preY;
        boolean mousePressed = false;
        public static Piece currentPiece = null;  
        /*****************************************/
        private void displayLocationError()
        {
            System.out.println("Mouse pressed outside of board");
            System.out.println("GameSize = " + gameSize + " Tile Size = " + TILE_SIZE);
            System.out.println("Mouse X: " + preX + " Mouse Y: " + preY);
            System.out.println("Cords: " + (preX / TILE_SIZE) + ", " + ((ROWS-1) - (preY / TILE_SIZE)));
            System.out.println("Adjusted Cords: " + (preX / (TILE_SIZE * gameSize)) + ", " + ((ROWS-1) - (preY / (TILE_SIZE * gameSize))));
        }

        /**
        * This methode determines which cell was clicked and sets the currentPiece variable
        * to the piece occupying that cell if it is owned by the current turn's player.
        * And sets preX and preY to x and y of mousepressed event.
        * @param e The mouse event that triggered this method.
        */
        @Override
        public void mousePressed(MouseEvent e)
        {
            //local constants 
            //local variables
            int x = e.getX() / (TILE_SIZE * gameSize);
            int y = (ROWS-1) - (e.getY() / (TILE_SIZE * gameSize));
            Cell cell = cells[x][y];
            /************************************************/

            //IF x or y equals less than 0
            if(x < 0 || y < 0) 
            {
                //Display error location
                displayLocationError();
                //exit method
                return;
            }//END IF

            //IF x greater than boards limits or y greater than boards limits 
            if(x > COLS || y > ROWS )
            {
                displayLocationError();
                return;
            }//END IF

            //IF the cell is unoccupied.
            if(!cell.isOccupied())
            {
                //exit method
                return;
            }//END IF

            //IF the piece thats clicked is not owned by player who clicked it
            if(cell.getPiece().getOwnedBy() != currentTurn)
            {
                //exit method
                return;
            }//END IF
           
            //set currentPiece to equal piece at cell the that was pressed 
            currentPiece = cell.getPiece();
            mousePressed = true;
            //set preX and preY equal to place mouse was pressed
            preX = x;
            preY = y;
        }//end mousePressed

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            //Not needed for now
        }

        /**
        * This method checks where the mouse was released.
        * @param e The mouse event.
        */
        @Override
        public void mouseReleased(MouseEvent e)
        {
            //IF mouse not pressed
            if (!mousePressed)
            {
                //exit method
                return;
            }//end IF

            mousePressed = false;
            //call updatedLocation
            updateLocation(e);
            
        }//end mouseRelease
       
        /**
        * Updates the location of the current piece based on 
        * the coordinates of the mouse release event.
        * @param e The mouse release event.
        */
        public void updateLocation(MouseEvent e)
        {
            //local constants
            //local variables
            int x = e.getX() / (TILE_SIZE * gameSize);
            int y = (ROWS-1) - (e.getY() / (TILE_SIZE * gameSize));
            Cell cell = cells[x][y];
            /********************************************/
            //IF x or y outside the board 
            if (x > COLS || y > ROWS)
            {
                //exit method
                return;
            }//END IF
;
            //IF square is occupied 
            if (cell.isOccupied())
            {
                //exit method
                return;
            }//END IF
            
            //IF y equal to 7 and cell is not occupied or IF y equal to 0 and cell is not occupied 
            if(y == 7 && !cell.isOccupied() || y == 0 && !cell.isOccupied())
            {
                //IF current piece is a pawn
                if(currentPiece.getClass() == Pawn.class)
                {
                    //call guiCreator promoteScreen
                    guiCreator.promoteScreen(x, y);
                }//END IF
            }//END IF

            if(currentPiece.getOwnedBy() == 1)
            {  
                //updated guiCreator move JLabel
                guiCreator.move.setText("White" + currentPiece.toString() +"(" +  x + "," + y + ")");
            }
            else
            {
                //updated guiCreator move JLabel
                guiCreator.move.setText("Black" + currentPiece.toString() +"(" +  x + "," + y + ")");
            }

            //clears piece at starting position
            cells[preX][preY].setPiece(null);
            //set current piece to piece that was on preX and preY
            cells[x][y].setPiece(currentPiece);
            //clear current piece
            currentPiece = null;
            //draw all pieces again
            repaint();
            //Sets current turn
            setCurrentTurn();
        }//end update location

    }//end mouseAdapter

    /**
    * Constructor for Screen.
    * Displays the screen and pieces.
    *@param gameSize - set the size of board.
    */
    public Screen(int gameSize)
    {
        //calls initScreen
        initScreen(gameSize);
    }//end Screen

    /**
    * Initializes the screen with appropriate listeners
    * and dimensions, and initializes the game.
    */
    private void initScreen(int gameSize)
    {
        currentTurn = 1;
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
        //Set pieces to correct spots 
        resetBoard();
        //assigned pieces to black and white
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
        //FOR every piece white owns 
        for (Piece piece : p1Pieces)
        {
            piece.update();
        }//END FOR
        //FOR every piece black owns 
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
    @Override
    public void keyPressed(KeyEvent e) 
    {
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
    *Sets the chess board to the starting position.
    */
    private void resetBoard() 
    {
        cells[0][0] = new Cell(0, 0, new Rook("white", 0, 0, 1, this)); // ROOK
        cells[1][0] = new Cell(1, 0, new Knight("white", 1, 0, 1, this)); // KNIGHT
        cells[2][0] = new Cell(2, 0, new Bishop("white", 2, 0, 1, this)); // BISHOP
        cells[3][0] = new Cell(3, 0, new Queen("white", 3, 0, 1, this)); // QUEEN
        cells[4][0] = new Cell(4, 0, new King("white", 4, 0, 1, this)); // KING
        cells[5][0] = new Cell(5, 0, new Bishop("white", 5, 0, 1, this)); // BISHOP
        cells[6][0] = new Cell(6, 0, new Knight("white", 6, 0, 1, this)); // KNIGHT
        cells[7][0] = new Cell(7, 0, new Rook("white", 7, 0, 1, this)); // ROOK
        
        for (int i = 0; i < 8; i++) 
        {
            //set a white pawn on the board in the x positon of i and y positon of 1.
            cells[i][1] = new Cell(i, 1, new Pawn("white", i, 1,1, this)); // PAWN
        }//END FOR

        cells[0][7] = new Cell(0, 7, new Rook("black", 0, 7, 2, this)); // ROOK
        cells[1][7] = new Cell(1, 7, new Knight("black", 1, 7, 2, this)); // KNIGHT
        cells[2][7] = new Cell(2, 7, new Bishop("black", 2, 7, 2, this)); // BISHOP
        cells[3][7] = new Cell(3, 7, new Queen("black", 3, 7, 2, this)); // QUEEN
        cells[4][7] = new Cell(4, 7, new King("black", 4, 7, 2, this)); // KING
        cells[5][7] = new Cell(5, 7, new Bishop("black", 5, 7, 2, this)); // BISHOP
        cells[6][7] = new Cell(6, 7, new Knight("black", 6, 7, 2, this)); // KNIGHT
        cells[7][7] = new Cell(7, 7, new Rook("black", 7, 7, 2, this)); // ROOK
        
        for (int i = 0; i < 8; i++) 
        {
            //set a black pawn on the board in the the x position of i and y positon of 6.
            cells[i][6] = new Cell(i, 6, new Pawn("black", i, 6, 2, this)); // PAWN
        }//END FOR

        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 2; j < 6; j++) 
            {
                //creates a new cell for every square on the board
                cells[i][j] = new Cell(i, j);
            }//END FOR
        }//END FOR

    }//end resetBoard

    /**
    * This method assigns the pieces to the players, 
    * by iterating through each cell on the board 
    * and checking if it contains a piece. 
    * If a piece is found, it is added to the list of pieces owned by 
    * the corresponding player (player 1 or player 2).
    */
    private static void assignPieces() 
    {
        p1Pieces.clear();
        p2Pieces.clear();
        //FOR every row 
        for (int i = 0; i < ROWS; i++) 
        {
            //FORevery collum 
            for (int j = 0; j < COLS; j++) 
            {
                //IF the Cell doesn't have a piece
                if (cells[i][j].getPiece() != null)
                {
                    //IF Piece in the cell is owned by white 
                    if (cells[i][j].getPiece().getOwnedBy() == 1) 
                    {
                        //give the piece to whites 
                        p1Pieces.add(cells[i][j].getPiece());
                    } 
                    //ELSE IF Piece in the cell is owned by black
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
        //FOR every other index in the array, draw a white square
        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 0; j < COLS; j++) 
            {
                //if i plus j moduled by 2 equal 0
                if ((i + j) % 2 == 0) 
                {
                    //colors square white
                    g.fillRect(j * (TILE_SIZE * gameSize), i * (TILE_SIZE * gameSize), TILE_SIZE * gameSize, TILE_SIZE * gameSize);
                }//END IF
            }//END FOR
        }//END FOR

    }//END drawBackground

    /**
    * Draws all the pieces on the board using the Graphics object
    * @param g the Graphics object used for drawing
    */
    private void draw(Graphics g) 
    {
        //FOR every piece in Whited Pieces array
        for (Piece piece : p1Pieces) 
        {
            piece.draw(g, this);
        }//END FOR

        //FOR every piece in blacks Pieces array
        for (Piece piece : p2Pieces)
        {
            piece.draw(g, this);
        }//END FOR

    }//END draw

    /**
    * Returns the current turns value.
    * @return an integer representing the current turn value.
    */
    public int getCurrentTurn() 
    {
        return currentTurn;
    }//END getCurrentTurn
  
    public static void setCurrentTurn()
    {
        //IF its whites turn
        if(currentTurn == 1)
        {
            //set currentTurn equal to black
            currentTurn = 2;
        }
        //ELSE is blacks turn
        else
        {
            
        //set currentTurn equal to white
        currentTurn = 1;
        }//END IF
    }
    /**
     * Returns the game size of the game.
     * @return an integer representing the game size.
     */
    public static int getGameSize()
    {
        return gameSize;
    }//END getGameSize

    /**
     * Sets the game size of the game.
     * @param gameSize an integer representing the game size.
     */
    public void setGameSize(int gameSize)
    {
        Screen.gameSize = gameSize;
    }

    public void promotePawn(String color,int ownedBy,int x, int y,String promoteType)
    { 
        //Clear piece in cells x and y location
        cells[x][y].setPiece(null);
       
        //IF promoteType equal queen
        if(promoteType.equals("Queen"))
        {
            //set piece at x and y to a Queen
            cells[x][y] = new Cell(x,y, new Queen(color, x, y, ownedBy,this));
        }//END IF

        //IF promoteType equal Bishop
        if(promoteType.equals("Bishop"))
        {
            //set piece at x and y to a Bishop
            cells[x][y] = new Cell(x,y, new Bishop(color, x, y, ownedBy,this));
        }//END IF

        //IF promoteType equal Rook
        if(promoteType.equals("Rook"))
        {
            //set piece at x and y to a Rook
            cells[x][y] = new Cell(x,y, new Rook(color, x, y, ownedBy,this));
        }//END IF

        //IF promoteType equal Knight
        if(promoteType.equals("Knight"))
        {
            //set piece at x and y to a Knight
            cells[x][y] = new Cell(x,y, new Knight(color, x, y, ownedBy,this));
        }//END IF

        //IF y equal 7
        if(y == 7)
        {
            //add promted piece to white pieces  array
            p1Pieces.add(cells[x][y].getPiece());
        }
        //ELSE y equal 0
        else
        {
            //add promted piece to black pieces  array
            p2Pieces.add(cells[x][y].getPiece());
        }//END IF

        //IF piece at x and y owned by white 
        if(cells[x][y].getPiece().getOwnedBy() == 1)
        {
            //display piece and pieces x and y location
            guiCreator.move.setText("White" + cells[x][y].getPiece().toString() + "(" + x + "," + y + ")");
        }
        //ELSE piece at x and y owned by black
        else
        {
            //display piece and pieces x and y location
            guiCreator.move.setText("Black" + cells[x][y].getPiece().toString() + "(" + x + "," + y + ")");
        }//END IF    

        assignPieces();
    }//end promotedPawn

}//end Screen
