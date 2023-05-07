package main.Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import javax.imageio.ImageIO;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import java.awt.Point;
import java.awt.event.MouseEvent;
/**********************************************************
 * Program Name   : Piece
 * Author         : Jordan 
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
 * setPos - sets location of piece passed.
 * isClicked - checks if piece was cliked, and displays if it was or not.
 * getOwnedBy - gets who the piece is owned by
 * setOwnedBy - sets who owns the piece passed.
 * isCaptured - Checks if the pieces has been capture or not.
 **********************************************************/
public abstract class Piece
 {
    //Class constants
    
    //Class variables 
    private BufferedImage icon;      //png of Piece
    private Point pos;               // Postiong of Piece 
    private int ownedBy;             //1 for white or 2 for black
    private boolean captured = false;//if the piece was captured or no.
    private int gameSize = 1;
    
    /***********************************************************************/
    /**
    * Constructor for the Piece class.
    * Makes a new Piece object at the position (x, y) on the board.
    *
    * @param x The x position of the Piece.
    * @param y The y position of the Piece.
    */
    /**********************************************************
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
    
    //local constatns

    //local variables
/*************************************piece*********************** */
    public Piece(int x, int y, Screen board) 
    {
        //set position of piece to the given (x,y) cords.
        gameSize = Screen.getGameSize();
        pos = new Point(x, y);
    }//END Piece

    /**
    * Constructor for the Piece class.
    * Makes a new Piece object at the position (x, y) on the board and if white or black own the piece.
    * @param x The x postion of the Piece.
    * @param y The y position of the Piece.
    * @param ownedBy An int representing which color owns the piece. 1 = white 2 = black
    *        
    */
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
    
    /**
     * Loads an image file for a piece passed through.
     * @param game String representing the game to get the png from.
     * @param path String representing the Pieces in the specified game.
     *       
     */
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
    
    //local constatnts

    //local variables
/**********************************load Image************************* */
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
    }//End loadImage

    /**
    * Updates the position of the game pieces, 
    * also checks if the piece still on the board.
    * If the pieces is outside the board, the piece doesnt move at all.
    */
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
   
   //local constants

   //local variables

/*************************Update**************************** */
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
    
    /**
     * Draws the games pieces on the screen.
     * @param g used to draw the pieces image on the screen.
     * @param obs used to track the loading of the pieces image.
     */
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
    
    //local constants

    //local variables
/*************************************draw************************ */
     public void draw(Graphics g, ImageObserver obs) 
    {
        g.drawImage(icon, pos.x * (Screen.TILE_SIZE * gameSize), (7 * (Screen.TILE_SIZE * gameSize)) - pos.y * (Screen.TILE_SIZE * gameSize), obs);
    }//END Draw

    /**
     * Returns the current position of the game piece (x,y) cordinates
     * @return returns pieces current x- and y-cordinates.
     */
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
    //local constants

    //local variables
/*****************************getpos*************************** */
     public Point getPos() 
    {
        //returns pieces current x- and y-cordinates.
        return pos;
    }//end getPos

//TODO add a method to see if the move is valid

//TODO add a method to see if the move results in a capture

    /**
    * Sets the position of the games pieces to passed (x,y) coordinates.
    * @param x The new x-coordinate of passed piece.
    * @param y The new y- coordinate of passed piece.
    */
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
   
   //local constants

   //local variables
/**************************setpos ************************/ 
    public void setPos(int x, int y) 
    {
        pos.x = x;
        pos.y = y;
    }//end SetPos

    /**
     * Checks if the game piece has been clicked.
     * @param e A MouseEvent object representing the user's click event.
     * @return true if the entity has been clicked, false otherwise.
     */
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
     
    //local constants

    //local variables
/*************************************isClicked************* */
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

    /**
    * return who owns the piece.
    * @return int of the player that owns the piece.
    */
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
    
    //local constatns

    //local variables
/***************************Get Owned BY****************** */
    public int getOwnedBy() 
    {
        return ownedBy;
    }//End getOwnedBy

    /**
    * Sets if black or white own the piece.
    * @param ownedBy sets who owns the piece passed.
    */
   /**********************************************************
	* Method Name    : setOwnedBy
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Sets if black or white own the piece.
    *
    *BEGIN - setOwnedBy
    *
    *END - setOwnedBy
	**********************************************************/
    public void setOwnedBy(int ownedBy) 
    {
        this.ownedBy = ownedBy;
    }//end setOwnedBy

    /**
    * return true if game piece has been captured false otherwise.
    * @return Whether or not the game piece has been captured.
    */
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
    
    //local constatns

    //local variables
/**************************isCaptured*********************** */
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
    *  
    *END - setCaptured
	**********************************************************/
    
    //local constants

    //local variables
/***********************************set captured**************** */
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
    *  IF(endPoint for piece is empty)
    *     end != null
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
    
    //local constants

    //local variables
/****************************capture*********************** */
    public boolean capture(Piece endPiece, Cell start, Cell end)
    {
        boolean capture = false;
        if(endPiece != null) 
        {
            if(endPiece.getOwnedBy() != getOwnedBy()) 
            {
                capture = true;
                endPiece.setCaptured(true);
                end.setPiece(null);
                start.setPiece(null);
                end.setPiece(this);
                setPos(end.getX(), end.getY());
            }

        }
       
       return capture;
   }

/*
    Start Potential Move Methods
*/

    public abstract Cell[][] getAllPossibleMoves(Screen board);

    /*
        This Method gets all potential moves for a piece given an x/y
    */
    public Cell[] getPotentialMoves(int x, int y, Screen board) 
    {
        // x / y is the direction it is moving in, increment it until it hits a piece or the edge of the board
        //Local constants
       
        //Local variables
        Point pos = this.getPos();          //COMMENT
        Cell possibleMoves[] = new Cell[8]; //COMMENT
        /*****************************************************/
       
        for(int i = 0; i < 8; i++)
        {
            if(board.getCell(pos.x + x, pos.y + y).getPiece() == null)
            {
                possibleMoves[i] = board.getCell(pos.x + x, pos.y + y);
            }
            else if(board.getCell(pos.x + x, pos.y + y).getPiece().getOwnedBy() != this.getOwnedBy())
            {
                possibleMoves[i] = board.getCell(pos.x + x, pos.y + y);
                break;
            }
            else
            {
                break;
            }
            x+=x;
            y+=y;
        }
        return possibleMoves;
    }

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
    *     IF(pieces Y cord is inside board)
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
   
    //local constatns

    //local variables
/*****************laneCheckYAxis**************** */
    public  boolean laneCheckYAxis(Cell cells[][], Cell start, Cell end)
    {
        boolean laneCheck = true;
        for(int i = 0;i <= 7; i++)
        {
            if(end.getY() > start.getY())
            {  
                if(end.getY() - i > -1 && end.getY() - i != start.getY() && end.getY() - i > start.getY())
                {
                    if(cells[end.getX()][end.getY() - i].getPiece() != null )
                    {
                        laneCheck =  false;
                    }
                }
            }
            else if(end.getY() < start.getY())
            {
               if(end.getY() + i < 8 && end.getY() + i != start.getY()&& end.getY() + i < start.getY())
                {
                    if(cells[end.getX()][end.getY() + i].getPiece() != null)
                    {
                        laneCheck = false;
                    }
                }
            }    
        }
        System.out.println(laneCheck);
       return laneCheck;
    }
    
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

        for(int i = 0;i <= 7; i++)
        {
            if(end.getX() > start.getX())
            {
                if(end.getX() - i > -1 && end.getX() - i != start.getX() && end.getX() - i > start.getX())
                { 
                    if(cells[end.getX() - i][end.getY()].getPiece() != null)
                    {
                        laneCheck =  false;
                    }
                }
            }
            else if(end.getX() < start.getX())
            {
                if(end.getX() + i < 8 && end.getX() + i != start.getX() && end.getX() + i < start.getX())
                {
                    if(cells[end.getX() + i][end.getY()].getPiece() != null)
                    {
                        laneCheck = false;
                    }
                }
            }   
        }
        return laneCheck;
    }
    
     /**********************************************************
	* Method Name    : Diagonal Check
	* Author         : Jordan
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
    *    ELSE IF (piece is moving up and to the right)
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
    
    //local constatns

    //local variables
/**************************diagonal check******************** */
    public boolean diagonalCheck(Cell cells [][], Cell start, Cell end)
    {
        boolean diagonalCheck = true;
        int endX = Math.abs(start.getX() - end.getX());
        for(int i = 1; i < endX; i++)
        {
            if(end.getX() < start.getX() && end.getY() > start.getY())
            {
                if(cells[start.getX() - i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            } 
            else if(end.getX() > start.getX() && end.getY() > start.getY())
            {
                if(cells[start.getX() + i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }
            else if(end.getX() > start.getX() && end.getY() < start.getY())
            {
                if(cells[start.getX() + i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }
            else if(end.getX() < start.getX() && end.getY() < start.getY())
            {
                if(cells[start.getX() - i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }

        }
        return diagonalCheck;
    }
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
    
    //local constatns

    //local variables
/**************************capture or move******************* */
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
}//End Piece