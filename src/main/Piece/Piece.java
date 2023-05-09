package main.Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import java.awt.Point;
import java.awt.event.MouseEvent;
/**********************************************************
 * Program Name   : Piece
 * Author         : Jordan/Alan 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program does everything relating to 
 *  the pieces on a board game.
 *
 * Methods:
 * -------
 * Piece(int x, int y) - sets piece obejct at x,y.
 * Piece(int x, int y, int ownedBy) - sets piece object at x,y and sets which player owns the piece.
 * loadImage - Sets the png of the piece passed.
 * update - updates the location of the pieces. 
 * draw - draws the graphic of the piece.
 * getPos- get current position of piece.
 * setPos - sets location of piece passed.
 * isClicked - checks if piece was cliked, and displays if it was or not.
 * getOwnedBy - gets who the piece is owned by
 * setOwnedBy - sets who owns the piece passed.
 * isCaptured - Checks if the pieces has been capture or not.
 * setCaptured - sets pieces to be captured 
 * capture - deletes pieces that is "captured" and sets piece that captured to thay sqaure
 * laneCheckYAxis -  checks if Y axis is free from starting position to ending postion of the piece
 * laneCheckXAxis -checks if X axis is free from starting position to ending postion of the piece
 * diagonalCheck -checks if diagnal is free from starting position to ending postions 
 * captureOrMove - checks if the release point of the piece is occupied and occupied take else just move the piece there
 **********************************************************/
public abstract class Piece
 {
    //Class constants
    //Class variables 
    private BufferedImage icon;      //png of Pieces
    private Point pos;               // Postiong of Piece
    private Point lastPos;
    private int ownedBy;             //1 for white or 2 for black
    private boolean captured = false;//if the piece was captured or no.
    private int gameSize = 1;        //size of game 
    // private boolean checkingKing = false; 
    
    /***********************************************************************
	* Method Name    : Piece 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Constructor for Piece
    *
    *BEGIN - piece
    *  set position to cords
    *END - piece
	**********************************************************/
    public Piece(int x, int y, Screen board) 
    {
        //set position of piece to the given (x,y) cords.
        gameSize = Screen.getGameSize();
        pos = new Point(x, y);
    }//END Piece

    /**********************************************************
	* Method Name    : Piece 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Constructor for Piece
    *
    *BEGIN - piece
    *  set owned by balck or white
    *END - piece  
    *******************************************************/
    public Piece(int x, int y, int ownedBy, Screen board) 
    {
        pos = new Point(x, y); 
        // Set ownedBy to white or black
        gameSize = Screen.getGameSize();
        this.ownedBy = ownedBy;
    }//END Piece

    /**********************************************************
     * Method Name    : Piece
     * Author         : Jordan
     * Date           : 5/8/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Copy constructor for Piece
     * 
     * BEGIN - Piece
     * set position to cords
     * END - Piece
     **********************************************************/
    public Piece(Piece other) 
    {
        pos = new Point(other.pos.x, other.pos.y);
        ownedBy = other.ownedBy;
        captured = other.captured;
        gameSize = Screen.getGameSize();
    }//END Piece
    

    /**********************************************************
     * Method Name    : copy
     * Author         : Jordan
     * Date           : 5/8/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Abstract function to copy a piece
     **********************************************************/
    public abstract Piece copy ();


    /**********************************************************
     * Method Name    : fakeMove
     * Author         : Jordan
     * Date           : 5/8/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: function to move without updating the board
     * 
     * BEGIN - fakeMove
     * set lastPos to current pos
     * set pos to new pos
     * END - fakeMove
     **********************************************************/

    public void fakeMove (int x, int y, Screen board)
    {
        lastPos = new Point(pos.x, pos.y);
        pos.x = x;
        pos.y = y;
        board.getCell(x, y).setPiece(this);
    } 

    /***********************************************************************
     * Method Name    : undeoFakeMove
     * Author         : Jordan
     * Date           : 5/8/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: function to undo a fake move
     * 
     * BEGIN - undoFakeMove
     * set pos to lastPos
     * END - undoFakeMove
    */


    public void undoFakeMove(Screen board)
    {
        board.getCell(pos.x, pos.y).setPiece(null);
        pos.x = lastPos.x;
        pos.y = lastPos.y;
        board.getCell(lastPos.x, lastPos.y).setPiece(this);
    }


    /**********************************************************
	* Method Name    : loadImage
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Loads image of the pieces.
    *
    *BEGIN - loadImage
    *  TRY(load images)
    *     IF(game size is increased)
    *        increase image size
    *     END IF
    *  END TRY
    *  CATCH(loading error)
    *     display "loading Error"
    *  END CATCH 
    *END - laodImage
	**********************************************************/
    protected void loadImage(String game, String path)
    {
        try 
        {
            //loads image depending on game and pieces name
            icon = ImageIO.read(new File("images/" + game + "/" + path + ".png"));
            if(gameSize >= 2)
            {
                BufferedImage newImage = new BufferedImage(icon.getWidth() * gameSize, icon.getHeight() * gameSize, BufferedImage.TYPE_INT_ARGB);
                Graphics g = newImage.createGraphics();
                g.drawImage(icon, 0, 0, icon.getWidth() * gameSize, icon.getHeight() * gameSize, null);
                g.dispose();
                icon = newImage;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading player image " + e.getMessage());
        }

    }//EEND loadImage

    /**********************************************************
	* Method Name    : update
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Updates the position of the game pieces, 
    * also checks if the piece still on the board.
    * If the pieces is outside the board, the piece doesnt move at all.
    *
    *BEGIN - update
    *  IF(pieces position is less than 0)
    *     set position to 0
    *  ELSE IF(piece position is greater than or equal to board size)
    *     set postion to edge of board
    *  END IF
    *  IF(piece postion is less then 0)
    *     set Y cord to 0
    *  ELSE IF(pice postion is on edge of board)
    *     set Y to edge of board
    *  END IF
    *END - update
	**********************************************************/
    public void update() 
    {
        //If the Pieces x position is less than 0 
        if(pos.x < 0) 
        {
            //set the x position of the piece to 0 
            pos.x = 0;
        } 
        else if(pos.x >= Screen.COLS) 
        {
            //if the x position is greater than or equal boards borders
            //set the x position of the piece to the edge of the board.
            pos.x = Screen.COLS - 1;
        }//end IF 
        
        // Check if the Pieces y position is less than 0 
        if(pos.y < 0) 
        {
            //set the y-position to 0 
            pos.y = 0;
        }
        else if(pos.y >= Screen.ROWS) 
        {
            //if the y position is greater than or equal to the baords borders
            // set the y position og the piece to the edge of the board.
            pos.y = Screen.ROWS - 1;
        }//END IF  
    }//end Update
    
    /**********************************************************
	* Method Name    : draw
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Draws the games pieces on the screen.
    *
    *BEGIN - draw
    *  draw image
    *END - draw
	**********************************************************/
    public void draw(Graphics g, ImageObserver obs) 
    {
        g.drawImage(icon, pos.x * (Screen.TILE_SIZE * gameSize), (7 * (Screen.TILE_SIZE * gameSize)) - pos.y * (Screen.TILE_SIZE * gameSize), obs);
    }//END Draw

    /**********************************************************
	* Method Name    : getPos
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Returns the current position of the game piece (x,y) cordinates
	*
    *BEGIN - getpos
    *  retrun current piece x,y cords
    *END - getpos
    **********************************************************/
    public Point getPos() 
    {
        //returns pieces current x- and y-cordinates.
        return pos;
    }//end getPos

   /**********************************************************
	* Method Name    : setPos
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Sets the position of the games pieces to passed (x,y) coordinates.
	*BEGIN - setpos
    * set x cord
    * set y cord
    *END - setpos
    **********************************************************/
    public void setPos(int x, int y) 
    {
        pos.x = x;
        pos.y = y;
    }//end SetPos

   /**********************************************************
	* Method Name    : isClicked
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Checks if the game piece has been clicked.
	*
    *BEGIN - isClicked
    * get x from mosue
    * get y from mouse
    *END - isClicked
    **********************************************************/
    public boolean isClicked(MouseEvent e) 
    {
        double x = e.getX() / Screen.TILE_SIZE;
        double y = e.getY() / Screen.TILE_SIZE;
        //IF x and y have a piece there 
        if(x == pos.x && y == pos.y) 
        {

            System.out.println("Piece clicked");
            return true;
        }//end IF 

        //print piece not clicked if piece is not cliked 
        System.out.println("Piece not clicked");
        return false;
    }//end IsClicked

    /**********************************************************
	* Method Name    : getOwnedBy
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: return who owns the piece.
    *
    *BEGIN - getOwnedBy
    * return ownedBy
    *END - getOwnedBy  
	**********************************************************/
    public int getOwnedBy() 
    {
        return ownedBy;
    }//End getOwnedBy

   /**********************************************************
	* Method Name    : setOwnedBy
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Sets if black or white own the piece.
    *
    *BEGIN - setOwnedBy
    * sets the classes ownedby to passes int
    *END - setOwnedBy
	**********************************************************/
    public void setOwnedBy(int ownedBy) 
    {
        this.ownedBy = ownedBy;
    }//end setOwnedBy

   /**********************************************************
	* Method Name    : isCaptured 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: return true if game piece has been captured false otherwise.
    *
    *BEGIN - isCaptured
    * return captured
    *END isCaptured
	**********************************************************/
    public boolean isCaptured() 
    {
        return captured;
    }//end is captured

    /**********************************************************
	* Method Name    : setCaptued 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: sets pieces to be captured
    *
    *BEGIN - setCaptured
    *  set piece passed to captured
    *END - setCaptured
	**********************************************************/
    public void setCaptured(boolean captured) 
    {
        this.captured = captured;
    }//end setCaptured
    
    /**********************************************************
	* Method Name    : capture 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description:deletes pieces that is "captured" and
    * sets piece that captured to thay sqaure
    *
    *BEGIN - capture
    *  IF(endPoint for piece is not empty)
    *        IF(piece is owned by oposite team)
    *           set capture to True
    *           set release point to capture
    *           Remove captured piece
    *           Set peice at starting point
    *           set postion of piece
    *      END IF
    *  END IF
    * Return Capture
    *END - capture
	**********************************************************/
    public boolean capture(Piece endPiece, Cell start, Cell end)
    {
        boolean capture = false;
        //IF(endPoint for piece is not empty)
        if(endPiece != null) 
        {
            //IF(piece is owned by oposite team)
            if(endPiece.getOwnedBy() != getOwnedBy()) 
            {
                //set capture to True
                capture = true;
                //set release point to capture
                endPiece.setCaptured(true);
                //Remove captured piece
                end.setPiece(null);
                //delete piece at starting point 
                start.setPiece(null);
                //Set peice at starting point
                end.setPiece(this);
                //set postion of piece
                setPos(end.getX(), end.getY());
            }//END IF
        }//END IF 
       return capture;
   }//END capture 


     /**********************************************************
	* Method Name    : laneCheckYAxis
	* Author         : Alan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: checks if Y axis is free from starting position
    *   to ending postion of the piece
    *
    *BEGIN - laneCheckYAxis
    *  IF(Piece is moving up)
    *     IF(//IF piece is relase point it inside the board and Y - i is not at starting position and y - 1 not less than starting postion
    *        IF(Y index has piece in it)
    *           lanCheck false
    *        END IF
    *     END IF
    * ELSE IF (piece is moving down)
    *   IF(Pieces Y cord is indside board)
    *      IF(Y index ahs piece in it)
    *         laneCheck Flase
    *      END IF
    *    END IF 
    *  END IF
    * Return laneCheck
    *END - laneCheckYAxiz
	**********************************************************/
    public  boolean laneCheckYAxis(Cell cells[][], Cell start, Cell end)
    {
        boolean laneCheck = true;
        for(int i = 1;i <= 7; i++)
        {   
            //IF Piece is moving up
            if(end.getY() > start.getY())
            {  
                //IF piece is relase point it inside the board and Y - i is not at starting position and y - 1 not less than starting postion
                if(end.getY() - i > -1 && end.getY() - i != start.getY() && end.getY() - i > start.getY())
                {
                    //IFsquare at y - i not empty
                    if(cells[end.getX()][end.getY() - i].getPiece() != null )
                    {
                        //Lanecheck is fasle 
                        laneCheck =  false;
                    }//END IF 
                }//END IF 
            }
            //ELSE IF piece is moving down 
            else if(end.getY() < start.getY())
            {
                //IF piece is relase point it inside the board and Y + i is not at starting position and y + 1 not less than starting postion
               if(end.getY() + i < 8 && end.getY() + i != start.getY()&& end.getY() + i < start.getY())
                {
                    //IFsquare at y + i not empty
                    if(cells[end.getX()][end.getY() + i].getPiece() != null)
                    {
                        //LaneCheck is false 
                        laneCheck = false;
                    }//END IF 
                }//END IF 
            }//END IF 
        }//END FOR 
       return laneCheck;
    }//END lanCheckYAxis 
    
    /**********************************************************
	* Method Name    : laneCheckXAxis
	* Author         : Alan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: checks if X axis is free from starting position
    *   to ending postion of the piece
    *BEGIN - lanCheckXAxis
    *  IF(piece is moving right)
    *     IF(piece is not outside board)
    *        IF(Index position has peice in it)
    *           lane check false
    *        END IF
    *      END IF
    *  ELSE IF(piece is moving left)
    *     IF(Piece is not ouside board)
    *        IF(Index position has peice in it)
    *           Lane Check false
    *        END IF
    *     END IF
    *  END IF
    *END - landCheckXAxis
	**********************************************************/
    
    //local constatns

    //local variables
/*************************Lane Check X Axis**************** */
    public boolean laneCheckXAxis(Cell cells [][], Cell start, Cell end)
    {
        boolean laneCheck = true;

        for(int i = 1;i <= 7; i++)
        {
            //IF piece is moving to the right
            if(end.getX() > start.getX())
            {
                //IF piece is relase point it inside the board and Y - i is not at starting position and x - i not less than starting postion
                if(end.getX() - i > -1 && end.getX() - i != start.getX() && end.getX() - i > start.getX())
                { 
                    //IF square not empty
                    if(cells[end.getX() - i][end.getY()].getPiece() != null)
                    {
                        //laneCheck is false 
                        laneCheck =  false;
                    }//END IF 
                }//END IF 
            }//END IF 
            //ELSE IF piece is moving to the left 
            else if(end.getX() < start.getX())
            {
                //IF piece is relase point it inside the board and X + i is not at starting position and X + i not less than starting postion
                if(end.getX() + i < 8 && end.getX() + i != start.getX() && end.getX() + i > start.getX())
                {
                    //IF square not empty
                    if(cells[end.getX() + i][end.getY()].getPiece() != null)
                    {
                                                //laneCheck is false 

                        laneCheck = false;
                    }//END IF 
                }//END IF 
            }//END IF 
        }//END FOR
        return laneCheck;
    }//END laneCheckXAxis
    
     /**********************************************************
	* Method Name    : diagonalCheck
	* Author         : Alan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: checks if diagnal is free from starting position
    * to ending postions 
	*
    *BEGIN - diagonal check
    * for(all the possible moves)
    *    IF(piece is moving up and left)
    *       IF(index position as a piece in it)
    *          diagonal check to false
    *       END IF
    *    ELSE IF (piece is moving up and to the right and up)
    *       IF(index position hasa piece in it)
    *          diagonal check to false
    *       END IF
    *    ELSE IF (piece is moving down and to the right)
    *       IF(index postion has piece in it)
    *          diagonal check to false
    *       END IF
    *    ELSE IF (piece is moving down and to the left)
    *       IF(index position has piece in it)
    *           diagonal check to false
    *       END IF
    *    END IF
    * END FOR
    * Return diagonal check
    *END - diagonal check
    **********************************************************/
    public boolean diagonalCheck(Cell cells [][], Cell start, Cell end)
    {
        //local constatns
        //local variables
        boolean diagonalCheck = true;
        int endX = Math.abs(start.getX() - end.getX());
        /**************************diagonal check******************** */
        
        for(int i = 1; i < endX; i++)
        {
            //IF(piece is moving up and left)
            if(end.getX() < start.getX() && end.getY() > start.getY())
            {
                //IF square not empty
                if(cells[start.getX() - i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            }//END IF 
            //ELSE IF (piece is moving up and to the right and up)
            else if(end.getX() > start.getX() && end.getY() > start.getY())
            {
                //IF square not empty
                if(cells[start.getX() + i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            }
            //ELSE IF (piece is moving down and to the right)
            else if(end.getX() > start.getX() && end.getY() < start.getY())
            {
                //IF square not empty 
                if(cells[start.getX() + i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            }//END IF 
            //ELSE IF (piece is moving down and to the left)
            else if(end.getX() < start.getX() && end.getY() < start.getY())
            {
                //IF square not empty 
                if(cells[start.getX() - i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            }//END IF 
        }//END FOR 
        return diagonalCheck;
    }//END diagonalCheck

    /**********************************************************
	* Method Name    : capturOrMove
	* Author         : Alan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method checks if 
    * the release point of the piece is occupied
    * and occupied take else just move the piece there
    *
    *BEGIN - captureOrMove
    *  IF(release point != null)
    *     capture
    *  ELSE 
    *     release point is empty
    *     move to release point
    *  END IF
    *END - captureOrMove
	**********************************************************/
    public void captureORMove(Cell start, Cell end)
    {
        if(end.getPiece() != null)
        { 
            capture(end.getPiece(), start, end);
        }
        else
        {
            end.setPiece(start.getPiece());
            start.setPiece(null);
        }
    }
    public abstract void move(Cell cells[][],Screen board, Cell start, Cell end) throws InvalidMovementException;

    /*
        Start Potential Move Methods
    */
    // public abstract Cell[][] getAllPossibleMoves(Screen board);

    // /*
    //     This Method gets all potential moves for a piece given an x/y
    // */
    // public Cell[] getPotentialMoves(int x, int y, Screen board) 
    // {
    //     // x / y is the direction it is moving in, increment it until it hits a piece or the edge of the board
    //     //Local constants
       
    //     //Local variables
    //     Point pos = this.getPos();          //COMMENT
    //     Cell possibleMoves[] = new Cell[8]; //COMMENT
    //     /*****************************************************/
       
    //     for(int i = 0; i < 8; i++)
    //     {
    //         if(board.getCell(pos.x + x, pos.y + y) != null)
    //         {
    //             if(board.getCell(pos.x + x, pos.y + y).getPiece() == null)
    //             {
    //                 possibleMoves[i] = board.getCell(pos.x + x, pos.y + y);
    //             }
    //             else if(board.getCell(pos.x + x, pos.y + y).getPiece().getOwnedBy() != this.getOwnedBy())
    //             {
    //                 possibleMoves[i] = board.getCell(pos.x + x, pos.y + y);
    //                 break;
    //             }
    //         }
    //         x+=x;
    //         y+=y;
    //     }
    //     return possibleMoves;
    // }
}//End Piece