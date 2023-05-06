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
    private int gameSize = 1;        //size of png and board
    /***********************************************************************/

    /**********************************************************
	* Method Name    : Piece 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Constructor for Piece
	**********************************************************/
    public Piece(int x, int y, Screen board) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/
        
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
	**********************************************************/
    public Piece(int x, int y, int ownedBy, Screen board) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        pos = new Point(x, y); 
        // Set ownedBy to white or black
        gameSize = Screen.getGameSize();
        this.ownedBy = ownedBy;
    }//END Piece
    
    /**********************************************************
	* Method Name    : loadImage
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Loads image of the pieces.
	**********************************************************/
    protected void loadImage(String game, String path)
    {
        //Local constants
        //Local variables 
        /*****************************************************/
        try 
        {
            //loads image depending on game and pieces name
            icon = ImageIO.read(new File("images/" + game + "/" + path + ".png"));
            //IF gamesize is greater or equal to two 
            if(gameSize >= 2)
            {
                BufferedImage newImage = new BufferedImage(icon.getWidth() * gameSize, icon.getHeight() * gameSize, BufferedImage.TYPE_INT_ARGB);
                Graphics g = newImage.createGraphics();
                g.drawImage(icon, 0, 0, icon.getWidth() * gameSize, icon.getHeight() * gameSize, null);
                g.dispose();
                icon = newImage;
            }//END IF
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading player image " + e.getMessage());
        }//END tryCatchblock
    }//End loadImage

    /**********************************************************
	* Method Name    : update
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Updates the position of the game pieces, 
    * also checks if the piece still on the board.
    * If the pieces is outside the board, the piece doesnt move at all.
	**********************************************************/
    public void update() 
    {  
        //Local constants
        //Local variables 
        /*****************************************************/

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
	**********************************************************/
    public void draw(Graphics g, ImageObserver obs) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        g.drawImage(icon, pos.x * (Screen.TILE_SIZE * gameSize), (7 * (Screen.TILE_SIZE * gameSize)) - pos.y * (Screen.TILE_SIZE * gameSize), obs);
    }//END draw

    /**********************************************************
	* Method Name    : getPos
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Returns the current position of the game piece (x,y) cordinates
	**********************************************************/
    public Point getPos() 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //returns pieces current x- and y-cordinates.
        return pos;
    }//end getPos

    /**********************************************************
	* Method Name    : setPos
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Sets the position of the games pieces to passed (x,y) coordinates.
	**********************************************************/
    public void setPos(int x, int y) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        pos.x = x;
        pos.y = y;
    }//end SetPos

    /**********************************************************
	* Method Name    : isClicked
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Checks if the game piece has been clicked.
	**********************************************************/
    public boolean isClicked(MouseEvent e) 
    {
        //Local constants
        //Local variables 
        double x = e.getX() / Screen.TILE_SIZE;
        double y = e.getY() / Screen.TILE_SIZE;
        /*****************************************************/
       
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
	**********************************************************/
    public int getOwnedBy() 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        return ownedBy;
    }//End getOwnedBy

    /**********************************************************
	* Method Name    : setOwnedBy
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Sets if black or white own the piece.
	**********************************************************/
    public void setOwnedBy(int ownedBy) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        this.ownedBy = ownedBy;
    }//end setOwnedBy

    /**********************************************************
	* Method Name    : isCaptured 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: return true if game piece has been captured false otherwise.
	**********************************************************/
    public boolean isCaptured() 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        return captured;
    }//end is captured

    /**********************************************************
	* Method Name    : setCaptued 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: sets pieces to be captured
	**********************************************************/
    public void setCaptured(boolean captured) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/
        this.captured = captured;
    }//end setCaptured
    
    /**********************************************************
	* Method Name    : capture 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description:deletes pieces that is "captured" and
    * sets piece that captured to thay sqaure
	**********************************************************/
    public boolean capture(Piece endPiece, Cell start, Cell end)
    {
        //Local constants
        //Local variables 
        boolean capture = false; 
        /*****************************************************/

        //IF square at releasepoint doesnt have a piece in it
        if(endPiece != null) 
        {
            //IF piece is owned by opposite team
            if(endPiece.getOwnedBy() != getOwnedBy()) 
            {
                //Set capture to true
                capture = true;
                //set piece at release point to captured
                endPiece.setCaptured(true);
                //delete piece at release point 
                end.setPiece(null);
                //delete piece as starting point 
                start.setPiece(null);
                //Set piece to the piece at starting point 
                end.setPiece(this);
                //Set position of piece
                setPos(end.getX(), end.getY());
            }//END IF 
        }//END IF 
       return capture;
   }//END capture 

    /*
        This Method gets all potential moves for a piece given an x/y
    */
    public Cell[] getPotentialMoves(int x, int y, Screen board) 
    {
        // x / y is the direction it is moving in, increment it until it hits a piece or the edge of the board
        //Local constants
        //Local variables
        Point pos = this.getPos();
        Cell possibleMoves[] = new Cell[8];
        /*****************************************************/
        for(int i = 0; i < 8; i++)
        {
            if(pos.x + x < 0 || pos.x + x > 7 || pos.y + y < 0 || pos.y + y > 7)
            {
                break;
            }
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
	**********************************************************/
   public  boolean laneCheckYAxis(Cell cells[][], Cell start, Cell end)
    {
        //Local constants
        //Local variables 
        boolean laneCheck = true; // Checks if lane is clear
        /*****************************************************/ 
        for(int i = 0;i <= 7; i++)
        {
            //IF Piece is moving up
            if(end.getY() > start.getY())
            {  
                //IF pieces Y is not outside of board and y is not equal to starting position and y minus i is not less than starting position 
                if(end.getY() - i > -1 && end.getY() - i != start.getY() && end.getY() - i > start.getY())
                {
                    //IF y - i position has a piece in it 
                    if(cells[end.getX()][end.getY() - i].getPiece() != null )
                    {
                        laneCheck =  false;
                    }//END IF 
                }//END IF 
            }
            //ELSE IF piece is moving down
            else if(end.getY() < start.getY())
            {
                //IF pieces Y is not outside of board and y is not equal to starting position and y plus i is not greater than starting position 
                if(end.getY() + i < 8 && end.getY() + i != start.getY()&& end.getY() + i < start.getY())
                {
                    //IF y + i position has a piece in it 
                    if(cells[end.getX()][end.getY() + i].getPiece() != null)
                    {
                        laneCheck = false;
                    }//END IF 
                }//END IF 
            }//END IF 
        }//ENd FOR
       return laneCheck;
    }//END lanecheckYaxis
    
    /**********************************************************
	* Method Name    : laneCheckXAxis
	* Author         : Alan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: checks if X axis is free from starting position
    *   to ending postion of the piece
	**********************************************************/
    public boolean laneCheckXAxis(Cell cells [][], Cell start, Cell end)
    {
        //Local constants
        //Local variables 
        boolean laneCheck = true; // Checks if lane is clear
        /*****************************************************/
        for(int i = 0;i <= 7; i++)
        {
            //IF Piece is moving to the right
            if(end.getX() > start.getX())
            { 
                //IF pieces x is not outside of board and x is not equal to starting position and x minus i is not less than starting position 
                if(end.getX() - i > -1 && end.getX() - i != start.getX() && end.getX() - i > start.getX())
                { 
                    //IF x - i position has a piece in it 
                    if(cells[end.getX() - i][end.getY()].getPiece() != null)
                    {
                        laneCheck =  false;
                    }//END IF 
                }//END IF 
            }
            //ELSE IF piece is moving to the left
            else if(end.getX() < start.getX())
            {
                 //IF pieces x is not outside of board and x is not equal to starting position and x plus i is not less than starting position 
                if(end.getX() + i < 8 && end.getX() + i != start.getX() && end.getX() + i < start.getX())
                {
                    //IF x + i position has a piece in it 
                    if(cells[end.getX() + i][end.getY()].getPiece() != null)
                    {
                        laneCheck = false;
                    }//END IF 
                }//END IF
            }//END IF 
        }//END FOR
        return laneCheck;
    }//END laneCheckXAxis

    /**********************************************************
	* Method Name    : capture 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: checks if diagnal is free from starting position
    * to ending postions 
	**********************************************************/
    public boolean diagonalCheck(Cell cells [][], Cell start, Cell end)
    {
        //Local constants
        //Local variables 
        boolean diagonalCheck = true;
        int endX = Math.abs(start.getX() - end.getX());
        /*****************************************************/

        for(int i = 1; i < endX; i++)
        {
            //IF piece is moving up and to the left 
            if(end.getX() < start.getX() && end.getY() > start.getY())
            {
                //IF x - i position ans start + i has a piece in it 
                if(cells[start.getX() - i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            } 
            //ELSE IF piece is moving up and to the right 
            else if(end.getX() > start.getX() && end.getY() > start.getY())
            {
                //IF x + i position ans start + i has a piece in it 
                if(cells[start.getX() + i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            }
            //ELSE IF piece is moving down and to the right 
            else if(end.getX() > start.getX() && end.getY() < start.getY())
            {
                //IF x + i position ans start - i has a piece in it 
                if(cells[start.getX() + i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }
            //ELSE IF piece is moving down and to the left 
            else if(end.getX() < start.getX() && end.getY() < start.getY())
            {
                //IF x - i position ans start - i has a piece in it 
                if(cells[start.getX() - i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }//END IF 
            }//ENDIF 
        }//END FFOR 
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
	**********************************************************/
    public void captureORMove(Cell start, Cell end)
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF release point not empty
        if(end.getPiece() != null)
        { 
            //Capture piece at release point 
            capture(end.getPiece(), start, end);
        }
        //ELSE release point is empty  
        else
        {
            //Set release point piece to starts piece 
            end.setPiece(start.getPiece());
            //delete piece at start
            start.setPiece(null);
        }//END IF 
    }//END captureOrMove 

    public abstract Cell[][] getAllPossibleMoves(Screen board);
    public abstract void move(Cell cells[][],Screen board, Cell start, Cell end) throws InvalidMovementException;
}//End Piece