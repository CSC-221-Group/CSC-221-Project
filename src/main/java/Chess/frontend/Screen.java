package main.java.Chess.frontend;

import main.Piece.InvalidMovementException;
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
 * Program Name : Screen
 * Author : Jordan/Alan
 * Date : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program draws the board and piece
 * and displays them, also assgins the piece to black or white and
 * update the pieces location if piece was moved.
 * 
 * Methods:
 * -------
 * Screen - displays the board.
 * initScreen - sets the size and design of the board.
 * initGame - starts the game by starting the timer and reseting board and
 * assigning pieces.
 * actionPerfromed - checks where all the pieces are and redraws them.
 * keyPressed - Checks if spacebar was pressed and switches player if it was
 * pressed.
 * paintComponent - Displays the pieces and board.
 * resetBoard - Sets the chess baord to the starting position.
 * assignPieces - assign the pieces to player 1 or 2.
 * drawBackground - Draws the board.
 * draw - Draws the pieces.
 * getCurrentTurn - gets what players turn is it.
 **********************************************************/
public class Screen extends JPanel implements ActionListener, KeyListener                      
{
    // class constants
    public final char[] letters =  { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private final int DELAY = 10; // delay between each frame in ms
    public static final int TILE_SIZE = 32;// size of square on board
    public static final int ROWS = 8; // size of the board horizontally
    public static final int COLS = 8; // size of the board vertically
    // private static final int serialVersionUID = 1;

    // Class variables
    public static int gameSize = 1; // size of the board
    private Timer timer; // triggers actionPerformed()
    public static int currentTurn = 1; // 1 = player1, 2 = player2
    private static ArrayList<Piece> p1Pieces = new ArrayList<Piece>();// Pieces owned by White
    private static ArrayList<Piece> p2Pieces = new ArrayList<Piece>(); // Pieces owned by Black
    private static ArrayList<Piece> capturedPieces = new ArrayList<Piece>(); // Pieces that have been captured

    public static moveHandler moveHandler; // Handles all possible moves



    public static Cell[][] cells = new Cell[ROWS][COLS]; // Boards squares

    /**********************************************************
     * Program Name : mouseAdapter
     * Author : Jordan/Alan
     * Date : 3/19/23
     * Course/Section : Software Engineering 221-301
     * Program Description: This class checks all the mouse movements events
     * that happened on the board and updates accordingly
     * 
     * Methods:
     * -------
     * mousePressed - Checks where the mouse was pressed and if the player owns that
     * piece or not.
     * mouseReleased - Checks where the mouse was realed and update the cell where
     * it was released.
     * updateLocation - redraws the piece at the location it was released.
     **********************************************************/
    private class mouseAdapter extends MouseAdapter   
    {
        // Class constants
        // Class variable
        int preX, preY;
        boolean mousePressed = false;
        public static Piece currentPiece = null;
        private Screen board;
        /**************************************

        /**********************************************************
        * Method Name    : mouseAdapter 
        * Author         : Jordan
        * Date           : 
        * Course/Section : Software Engineering 221-301
        * Program Description: receives mouse events 
        **********************************************************/
        public mouseAdapter(Screen board) 
        {
            this.board = board;
        }//END mouseAdapter

        
        /**********************************************************
        * Method Name    : displaylocationError
        * Author         : Jordan
        * Date           : 
        * Course/Section : Software Engineering 221-301
        * Program Description:Display the infomation of the erorr that occured.
        **********************************************************/
        private void displayLocationError()
        {
            System.out.println("Mouse pressed outside of board");
            System.out.println("GameSize = " + gameSize + " Tile Size = " + TILE_SIZE);
            System.out.println("Mouse X: " + preX + " Mouse Y: " + preY);
            System.out.println("Cords: " + (preX / TILE_SIZE) + ", " + ((ROWS - 1) - (preY / TILE_SIZE)));
            System.out.println("Adjusted Cords: " + (preX / (TILE_SIZE * gameSize)) + ", "
                    + ((ROWS - 1) - (preY / (TILE_SIZE * gameSize))));
        }//END displaylocationerror

        /**********************************************************
        * Method Name    : mousePressed
        * Author         : Jordan
        * Date           : 
        * Course/Section : Software Engineering 221-301
        * Program Description: This method determines which cell was clicked .
        **********************************************************/
        @Override
        public void mousePressed(MouseEvent e) 
        {
            // local constants  
            // local variables
            int x = e.getX() / (TILE_SIZE * gameSize);
            int y = (ROWS - 1) - (e.getY() / (TILE_SIZE * gameSize));
            Cell cell = cells[x][y];
            /************************************************/

            // IF x or y equals less than 0
            if (x < 0 || y < 0)  
            {
                displayLocationError();
                // exit method
                return;
            } // END IF

            // IF x greater than boards limits or y greater than boards limits
            if (x > COLS || y > ROWS)
            {
                displayLocationError();
                return;
            } // END IF

            // IF the cell is unoccupied.
            if (!cell.isOccupied())  
            {
                // exit method
                return;
            } // END IF

            // IF the piece thats clicked is not owned by player who clicked it
            if (cell.getPiece().getOwnedBy() != currentTurn)  
            {
                // exit method
                return;
            } // END IF

            // set currentPiece to equal piece at cell the that was pressed
            currentPiece = cell.getPiece();
            mousePressed = true;
            // set preX and preY equal to place mouse was pressed
            preX = x;
            preY = y;
        }// end mousePressed

        /**
         * This method checks where the mouse was released.
         * 
         * @param e The mouse event.
         */
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            // IF mouse not pressed
            if (!mousePressed) 
            {
                // exit method
                return;
            } // end IF

            mousePressed = false;
            // call updatedLocation
            try 
            {
                updateLocation(e);
            }
            catch (InvalidMovementException e1)  
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }//end mouseRelease

        /**********************************************************
        * Method Name    : tryCastling
        * Author         : Jordan
        * Date           : 5/6/2023
        * Course/Section : Software Engineering 221-301
        * This method runs by three checks and castles if it is possible
        **********************************************************/

        private boolean tryCastling(Cell startCell , Cell endCell) {
            //local constants
            //local variables
            Rook rook;
            King king;
            /************************************************/
            if(castlingPreCheck(startCell) && castlingRookCheck(endCell))
            {
                rook = (Rook)endCell.getPiece();
                king = (King)startCell.getPiece();
                System.out.println("Castling, " + rook.rookWRight + " " + rook.rookWLeft + " " + rook.rookBRight + " " + rook.rookBLeft);
                if(king.checkIfLaneClear(rook, cells))
                {
                    System.out.println("Castling2");
                    king.castling(rook, cells);
                    currentPiece = null;
                    repaint();
                    setCurrentTurn();
                    return true;
                }
            }
            return false;
        }
         /**********************************************************
        * Method Name    : castlingPreCheck
        * Author         : Jordan
        * Date           : 5/6/2023
        * Course/Section : Software Engineering 221-301
        * Checks if the king is castling & can castle
        **********************************************************/

        private boolean castlingPreCheck(Cell theCell)
        {
            // local constants
            // local variables
            Piece piece = theCell.getPiece() != null ? theCell.getPiece() : null;
            King king;
            /************************************************/
            if(theCell.isOccupied() == false || piece == null ||
                 theCell.getPiece().getClass() != King.class ||
                theCell.getPiece().getOwnedBy() != currentTurn)
            {
                return false;
            }
            king = (King)piece;
            if(king.didKingMove())
            {
                return false;
            }
            return true;
        }
        /**********************************************************
        * Method Name    : castlingRookCheck
        * Author         : Jordan
        * Date           :  5/6/2023
        * Course/Section : Software Engineering 221-301
        * Checks if the piece is a rook and if it has moved or not.
        **********************************************************/

        private boolean castlingRookCheck(Cell theCell)
        {
            // local constants
            // local variables
            Piece piece = theCell.getPiece() != null ? theCell.getPiece() : null;
            Rook rook;
            /************************************************/
            if(theCell.isOccupied() == false || piece == null ||
                 theCell.getPiece().getClass() != Rook.class)
            {
                return false;
            }
            rook = (Rook)piece;
            if(rook.hasNotMoved() == false)
            {
                return false;
            }
            return true;
        }


        /**********************************************************
        * Method Name    : mousePressed
        * Author         : Jordan/Alan
        * Date           : 
        * Course/Section : Software Engineering 221-301
        * Program Description: Updates the location of the current piece based on
        * the coordinates of the mouse release event..
        **********************************************************/
        public void updateLocation(MouseEvent e) throws InvalidMovementException  
        {    
            //local constants
            final int enemyTurn = currentTurn == 1 ? 2 : 1;
            //local variables
            int x = e.getX() / (TILE_SIZE * gameSize);
            int y = (ROWS - 1) - (e.getY() / (TILE_SIZE * gameSize));
            Cell cell = cells[x][y];
            Piece endPiece = null;
            King king;
            /********************************************/
            
            //IF x or y outside the board
            if (x > COLS || y > ROWS)  
            {
                // exit method
                return;
            } //END IF

            // Attempt to Castle
            if(tryCastling(cells[preX][preY], cells[x][y]))
            {
                return;
            }
            // If this isn't castling, capture the piece if it is occupied
            if(cell.isOccupied() && cell.getPiece().getOwnedBy() != currentTurn)
            {
                endPiece = cell.getPiece();
            }//END ELSE IF
            // IFcurrent piece is a pawn
            if (currentPiece.getClass() == Pawn.class )  
            {
                //IF starting locations Y is 1 and ending location y = 0
                // or if starting location is 6 and ending position is 7
                if(preY == 1 && y == 0 || preY == 6 && y == 7)
                {
                    //call guiCreator promoteScreen
                    guiCreator.promoteScreen(x, y);
                }//END IF 
            }// END IF

            //Check if move is legal
            currentPiece.move(cells,null, cells[preX][preY], cells[x][y]);
            king = (King) findPiece(King.class, currentTurn == 1 ? p2Pieces : p1Pieces);
            System.out.println("King in check: " + king.isInCheck() + "Location: " + king.getPos().x + " " + king.getPos().y); 
            king.setInCheck(moveHandler.checkForCheck(enemyTurn));
            String information = "";
            
            //IF piece moved 
            if (cells[preX][preY].isOccupied() == false && endPiece != null && endPiece != cells[x][y].getPiece())  
            {
                //Add captured piece to array
                capturedPieces.add(endPiece);
                //IF piece owned by white 
                if (endPiece.getOwnedBy() == 1)  
                {
                    p1Pieces.remove(endPiece);
                    information += "White Captured \n" + endPiece.toString() + " at " + letters[x] + y;
                } 
                //ELSE piece is owned by black
                else  
                {
                    p2Pieces.remove(endPiece);
                    information += "Black Captured \n" + endPiece.toString() + "at " + letters[x] + y;
                }//END IF 
            }
            //ELSE IF piece owned by white 
            else if (currentPiece.getOwnedBy() == 1)  
            {
                // updated guiCreator move JLabel
                information += "White" + currentPiece.toString() + ":" + letters[x] + y;
            } 
            //ELSE piece is owned by black
            else  
            {
                // updated guiCreator move JLabel
                information += "Black" + currentPiece.toString() + ":" + letters[x] + y;
            }

            displayToTextBox(information);

            //IF moved piece is a pawn
            if (currentPiece.getClass() == Pawn.class)  
            {
                Pawn.enPassant(currentPiece, x, y);
            }//end if 

            currentPiece = null;
            // draw all pieces again
            repaint();
            // Sets current turn
            setCurrentTurn();
        }//END IF 
    }//end mouseAdapter

    /**********************************************************
    * Method Name    : diplayToTextBox
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Updates text box
    **********************************************************/
    public static void displayToTextBox(String text)  
    {
        guiCreator.move.setText(text);
    }//END diplayTOTextBox

    /**********************************************************
    * Method Name    : Screen
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Constructor for Screen
    **********************************************************/
    public Screen(int gameSize)  
    {
        // calls initScreen
        initScreen(gameSize);
    }// end Screen

    /**********************************************************
    * Method Name    : findPiece
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Find specified piece 
    **********************************************************/
    private Piece findPiece(Class c, ArrayList<Piece> Pieces)  
    {
        //For each piece in pieces array
        for (Piece p : Pieces)  
        {
            //IF piece class equals specified piece 
            if (p.getClass() == c)  
            {
                return p;
            }//END IF 
        }//END IF 
        return null;
    }//END find piece 

    /**********************************************************
    * Method Name    : getCell
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Find specified cell
    **********************************************************/
    public Cell getCell(int x, int y) 
    {
        if(x > 7 || y > 7 || x < 0 || y < 0)
        {
            return null;
        }
        else
        {
            return cells[x][y];
        }//END IF
    }//END getCell

    /**********************************************************
    * Method Name    : assignPossibleMove
    * Author         : Jordan
    * Date           :
    * Course/Section : Software Engineering 221-301
    * Program Description: Takes a PossibleMove Array and assigns 
    * a valid cell to the given index
    **********************************************************/
   public void assignPossibleMove(Cell[][] possibleMoves, int iX, int iY, int posX, int posY)
   {
        if( posX > 7 || posY > 7 || posX < 0 || posY < 0 || getCell(posX, posY) == null)
        {
            return;
        }
        else
        {
            possibleMoves[iX][iY] = getCell(posX, posY);
        }//END IF
   }


    /**********************************************************
    * Method Name    : getScreen
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: returns screen
    **********************************************************/
    public Screen getScreen() 
    {
        return this;
    }//END getScreen

    /**********************************************************
    * Method Name    : initVariables
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Initializes variables for Chess game
    **********************************************************/
    private void initVariables()  
    {
        King abstractKing = (King) findPiece(King.class, p1Pieces);
        abstractKing.whiteKing = true;
        abstractKing = (King) findPiece(King.class, p2Pieces);
        abstractKing.blackKing = true;
        Rook abstractRook = (Rook) cells[0][7].getPiece();
        abstractRook.rookBLeft = true;
        abstractRook = (Rook) cells[7][7].getPiece();
        abstractRook.rookBRight = true;
        abstractRook = (Rook) cells[0][0].getPiece();
        abstractRook.rookWLeft = true;
        abstractRook = (Rook) cells[7][0].getPiece();
        abstractRook.rookWRight = true;
    }//END initVariable

    /**********************************************************
    * Method Name    : initScreen
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Initializes the screen with appropriate listeners
    * and dimensions, and initializes the game.
    **********************************************************/
    private void initScreen(int gameSize) 
    {
        currentTurn = 1;
        setGameSize(gameSize);
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(COLS * (TILE_SIZE * gameSize), ROWS * (TILE_SIZE * gameSize)));
        addMouseMotionListener(new mouseAdapter(this));
        addMouseListener(new mouseAdapter(this));
        setBackground(Color.BLACK);
        initGame();
        initVariables();
        moveHandler = new moveHandler(getScreen(), p1Pieces, p2Pieces);
    }// end initScreen

    /**********************************************************
    * Method Name    : initGame
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description:Initializes the game by starting the timer,
    * resetting the board, and assigning pieces.
    **********************************************************/
    private void initGame()  
    {
        timer = new Timer(DELAY, this);
        timer.start();
        // Set pieces to correct spots
        resetBoard();
        // assigned pieces to black and white
        assignPieces();
    }// end initGame

    /**********************************************************
    * Method Name    : actionPerformed
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Everytime an action is performed this method 
    * updates the pieces in the 2 pieces arrays
    **********************************************************/
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
    }// end actionPerformed

    /**
     * @param e
     */
    // Key Binds
    @Override
    public void keyTyped(KeyEvent e)  {
    }

    @Override
    public void keyPressed(KeyEvent e)  {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE)  {
            System.exit(0);
        }
        if (key == KeyEvent.VK_R)  {
            System.out.println("Checking for check Current Turn: " + currentTurn + "");
            moveHandler.checkForCheck(currentTurn);
            int enemyTurn = currentTurn == 1 ? 2 : 1;
            System.out.println("Checking for check Current Turn: " + enemyTurn + "");
            moveHandler.checkForCheck(enemyTurn);
        }
        if(key == KeyEvent.VK_C)
        {
            System.out.println("Moving black pawn to a position where it can capture the white King");
            cells[4][3].setPiece(null);
            cells[4][3].setPiece(p2Pieces.get(0));
        }
        if(key == KeyEvent.VK_B)
        {
            // Test KEY for Castling
            cells[5][4].setPiece(cells[5][0].getPiece());
            cells[5][0].setPiece(null);
            cells[6][4].setPiece(cells[6][0].getPiece());
            cells[6][0].setPiece(null);
            cells[1][4].setPiece(cells[1][0].getPiece());
            cells[1][0].setPiece(null);
            cells[2][4].setPiece(cells[2][0].getPiece());
            cells[2][0].setPiece(null);

            cells[5][5].setPiece(cells[5][7].getPiece());
            cells[5][7].setPiece(null);
            cells[6][5].setPiece(cells[6][7].getPiece());
            cells[6][7].setPiece(null);
            cells[1][5].setPiece(cells[1][7].getPiece());
            cells[1][7].setPiece(null);
            cells[2][5].setPiece(cells[2][7].getPiece());
            cells[2][7].setPiece(null);

        }
    }// end keypressed

    @Override
    public void keyReleased(KeyEvent e)  {

    }

    /**********************************************************
    * Method Name    : paintComponent
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description:  Paints the game board and pieces on it.
    **********************************************************/
    @Override
    public void paintComponent(Graphics g)  
    {
        super.paintComponent(g);
        drawBackground(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }// end painComponent

    /**********************************************************
    * Method Name    : resetBoard
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Sets the chess board to the starting position.
    **********************************************************/
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
            // set a white pawn on the board in the x positon of i and y positon of 1.
            cells[i][1] = new Cell(i, 1, new Pawn("white", i, 1, 1, this)); // PAWN
        } // END FOR

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
            // set a black pawn on the board in the the x position of i and y positon of 6.
            cells[i][6] = new Cell(i, 6, new Pawn("black", i, 6, 2, this)); // PAWN
        } // END FOR

        for (int i = 0; i < ROWS; i++)  
        {
            for (int j = 2; j < 6; j++)  
            {
                // creates a new cell for every square on the board
                cells[i][j] = new Cell(i, j);
            } // END FOR
        } // END FOR
    }// end resetBoard
    
    /**********************************************************
    * Method Name    : assignPieces
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: This method assigns the pieces to the players,
    * by iterating through each cell on the board
    * and checking if it contains a piece.
    * If a piece is found, it is added to the list of pieces owned by
    * the corresponding player (player 1 or player 2).
    **********************************************************/
    public static void assignPieces()  
    {
        p1Pieces.clear();
        p2Pieces.clear();
        // FOR every row
        for (int i = 0; i < ROWS; i++)  
        {
            // FORevery collum
            for (int j = 0; j < COLS; j++) 
            {
                // IF the Cell doesn't have a piece
                if (cells[i][j].getPiece() != null)  
                {
                    // IF Piece in the cell is owned by white
                    if (cells[i][j].getPiece().getOwnedBy() == 1) 
                    {
                        // give the piece to whites
                        p1Pieces.add(cells[i][j].getPiece());
                    }
                    // ELSE IF Piece in the cell is owned by black
                    else if (cells[i][j].getPiece().getOwnedBy() == 2)  
                    {
                        // give the piece to black
                        p2Pieces.add(cells[i][j].getPiece());
                    } // end IF
                } // end IF
            } // END FOR
        } // END FOR
    }// end assign piecees

    /********************************************************** 
    * Method Name    :drawBackGround
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Draws the background of the chess board.
    **********************************************************/
    private void drawBackground(Graphics g)  
    {
        g.setColor(Color.WHITE);
        // FOR every other index in the array, draw a white square
        for (int i = 0; i < ROWS; i++)  
        {
            for (int j = 0; j < COLS; j++)  
            {
                // if i plus j moduled by 2 equal 0
                if ((i + j) % 2 == 0)  
                {
                    // colors square white
                    g.fillRect(j * (TILE_SIZE * gameSize), i * (TILE_SIZE * gameSize), TILE_SIZE * gameSize,
                            TILE_SIZE * gameSize);
                } // END IF
            } // END FOR
        } // END FOR
    }// END drawBackground

    /********************************************************** 
    * Method Name    : draw
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Draws all the pieces on the board using the Graphics object
    **********************************************************/
    private void draw(Graphics g)  
    {
        // FOR every piece in Whited Pieces array
        for (Piece piece : p1Pieces)  
        {
            if (!piece.isCaptured()) 
            {
                piece.draw(g, this);
            }
        } // END FOR

        // FOR every piece in blacks Pieces array
        for (Piece piece : p2Pieces)  
        {
            if (!piece.isCaptured())  
            {
                piece.draw(g, this);
            }
        } // END FOR
    }// END draw
    
    /********************************************************** 
    * Method Name    : getCurrentTurn
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: returns whoes turn it is 
    **********************************************************/
    public int getCurrentTurn()  
    {
        return currentTurn;
    }// END getCurrentTurn

    /********************************************************** 
    * Method Name    : setCurrentTurn
    * Author         : Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: returns whoes turn it is 
    **********************************************************/
    public static void setCurrentTurn()  
    {
        // IF its whites turn
        if (currentTurn == 1) 
        {
            // set currentTurn equal to black
            currentTurn = 2;
            guiCreator.turn = 2;
        }
        // ELSE is blacks turn
        else  
        {
            // set currentTurn equal to white
            currentTurn = 1;
            guiCreator.turn = 1;
        } // END IF
    }//END setCurrentTurn

    /********************************************************** 
    * Method Name    : getGameSize
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description: Returns the game size of the game.
    **********************************************************/
    public static int getGameSize() 
    {
        return gameSize;
    }// END getGameSize

    /********************************************************** 
    * Method Name    : setGameSize
    * Author         : Jordan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description:  Sets the game size of the game.
    **********************************************************/
    public void setGameSize(int gameSize)  
    {
        Screen.gameSize = gameSize;
    }//END setGameSize

    /********************************************************** 
    * Method Name    : promotePawn
    * Author         : Alan
    * Date           : 
    * Course/Section : Software Engineering 221-301
    * Program Description:  promote pawn into passed promoteType
    **********************************************************/
    public void promotePawn(String color, int ownedBy, int x, int y, String promoteType)  
    {
        // Clear piece in cells x and y location
        cells[x][y].setPiece(null);
        repaint();
        // IF promoteType equal queen
        if (promoteType.equals("Queen"))  
        {
            // set piece at x and y to a Queen
            cells[x][y] = new Cell(x, y, new Queen(color, x, y, ownedBy, this));
        } // END IF

        // IF promoteType equal Bishop
        if (promoteType.equals("Bishop"))  
        {
            // set piece at x and y to a Bishop
            cells[x][y] = new Cell(x, y, new Bishop(color, x, y, ownedBy, this));
        } // END IF

        // IF promoteType equal Rook
        if (promoteType.equals("Rook"))  
        {
            // set piece at x and y to a Rook
            cells[x][y] = new Cell(x, y, new Rook(color, x, y, ownedBy, this));
        } // END IF

        // IF promoteType equal Knight
        if (promoteType.equals("Knight"))  
        {
            // set piece at x and y to a Knight
            cells[x][y] = new Cell(x, y, new Knight(color, x, y, ownedBy, this));
        } // END IF

        // IF y equal 7
        if (y == 7)  
        {
            // add promted piece to white pieces array
            p1Pieces.add(cells[x][y].getPiece());
        }
        // ELSE y equal 0
        else  
        {
            // add promted piece to black pieces array
            p2Pieces.add(cells[x][y].getPiece());
        } // END IF

        // IF piece at x and y owned by white
        if (cells[x][y].getPiece().getOwnedBy() == 1) 
        {
            // display piece and pieces x and y location
            guiCreator.move.setText("White" + cells[x][y].getPiece().toString() + "(" + x + "," + y + ")");
        }
        // ELSE piece at x and y owned by black
        else  
        {
            // display piece and pieces x and y location
            guiCreator.move.setText("Black" + cells[x][y].getPiece().toString() + "(" + x + "," + y + ")");
        } // END IF

        repaint();
    }// end promotedPawn
}// end Screen
