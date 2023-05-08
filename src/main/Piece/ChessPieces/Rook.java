 
package main.Piece.ChessPieces;

import java.awt.Point;

import main.Piece.InvalidMovementException;
import main.Piece.Piece;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
/**********************************************************
* Program Name   : Rook
* Author         : Jordan/Alan
* Date           : 3/19/23
* Course/Section : Software Engineering 221-301
* Program Description:This program sets what a Rook piece
* is and the legal moves of a Rook piece.
*
* Methods:
* -------
* Rook - sets color position and owner of Rook piece.
* move - sets legal moves for Rook piece.
* hasNotMoved- Checks if Rook pieces have moved from starting position
**********************************************************/
public class Rook extends Piece
{
    //class constants
    //class variables 
    public String color;               //Color of Piece 
    public  boolean rookWLeft  = false;//Check if Rook to the left of white king has moved 
    public  boolean rookWRight = false;//Check if Rook to the right of white king has moved 
    public  boolean rookBLeft = false; //Check if Rook to the left of Black king has moved 
    public  boolean rookBRight = false;//Check if Rook to the right of Black king has moved 
    /************************************/

   /**********************************************************
	* Method Name    : Rook
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Constructor for Queen.
	**********************************************************/
    public Rook (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Rook");
        this.color = color;
    }//END Rook

    /**********************************************************
     * Method Name    : Rook
     * Author         : Jordan
     * Date           : 5/8/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Copy constructor for the Rook class
     * 
     * BEGIN Rook
     *   set color to other.color
     * END Rook
     **********************************************************/

    public Rook(Rook other)
    {
        super(other);
        this.color = other.color;
    }



    /**********************************************************
	* Method Name    : hasNotMoved
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Checks if Rook pieces have moved from 
    * starting position
    * BEGIN - Rook
    *  IF(piece is owned by white)
    *     return true
    *  ELSE IF(piece is owned by Black)
    *     return false
    *  END IF
    * return false
    *END rook
	**********************************************************/
    public boolean hasNotMoved()
    {   
        //Local constants
        Point WHITEPOS[] = {new Point(0,0), new Point(7,0)};
        Point BLACKPOS[] = {new Point(0,7), new Point(7,7)};
        //Local variables 
        Point pos = getPos();
        /*****************************************************/

        //IF piece is owned by white and piece is (X:0Y:0) or (X:0Y:7)
        if(getOwnedBy() == 1)
        {
            if(pos.x == WHITEPOS[0].x && WHITEPOS[0].y == pos.y || pos.x == WHITEPOS[1].x && WHITEPOS[1].y == pos.y)
            {
                return true;
            }//END IF 
        } 
        //ELSE piece is owned by black and piece is (X:7Y:0) or (X:7Y:7)
        else
        {
            if(pos.x == BLACKPOS[0].x && BLACKPOS[0].y == pos.y || pos.x == BLACKPOS[1].x && BLACKPOS[1].y == pos.y)
            {
                return true;
            }
        }//END IF 
        return false;
    }//END hasNotMoved

    /**********************************************************
	* Method Name    : move
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method determines what move the Rook can do.
    *BEGIN - move
    *  IF(pice is moving up)
    *     move and or capture
    *  ELSE IF(piece is moving right)
    *     move and or capture
    *  ELSE IF(piece is movin down)
    *     move or capture
    *  ELSE IF(piece is moving left)
    *     move or capture
    *  ELSE
    *     move is not legal
    *END - move 
	**********************************************************/
    @Override
    public void move(Cell cells[][],Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        for(int i = 0;i <= 7; i++)
        {  
            //IF piece is in same x axis and piece is going "up" and no piece is the way
            if(end.getX() == start.getX() && end.getY() == start.getY() + i && laneCheckYAxis(cells, start, end))
            {
                captureORMove(start, end);
            }
            //ELSE IF pieces is going to the "right" and in the same y axis and no pieces  in the way
            else if(end.getX() == start.getX() + i && end.getY() == start.getY() && laneCheckXAxis(cells, start, end))
            {
                captureORMove(start, end);
            }
            //ELSE IF pieces is in the same x axis and pieces is going "down" and no pieces in the way
            else if(end.getX() == start.getX() && end.getY() == start.getY() - i && laneCheckYAxis(cells, start, end))
            {
                captureORMove(start, end);
            }
            //ELSE IF piece is going to the "left" and stays in the same y axis and no piece in the way
            else if(end.getX() == start.getX() - i && end.getY() == start.getY() && laneCheckXAxis(cells, start, end))
            {
                captureORMove(start, end);
            }
            //ELSE IF loop is finished and there is a piece in the starting location
            else if(i == 7 && start.getPiece() != null)
            {
                //Throw InvalidMoveException
                throw new InvalidMovementException();
            }//END IF 
        }//END FOR
    }//END IF

    /**********************************************************
	* Method Name    : toString
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Return the Piece type
	**********************************************************/
    @Override
    public String toString()
    {
        return "Rook";
    }//END toString
    
    /********************************************************** 
	* Method Name    : getAllPossibleMoves 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Return the Piece type
	**********************************************************/
    // @Override
    // public Cell[][] getAllPossibleMoves(Screen board)
    // {
    //     //Local Constants
    //     //Local Variables
    //     Cell[][] possibleMoves = new Cell[4][];
    //     /*****************************************************/
    //     possibleMoves[0] = getPotentialMoves(1,0,board);
    //     possibleMoves[1] = getPotentialMoves(-1,0,board);
    //     possibleMoves[2] = getPotentialMoves(0,1,board);
    //     possibleMoves[3] = getPotentialMoves(0,-1,board);
    //     return possibleMoves;
    // }
    /**********************************************************
     * Method Name    : copy
     * Author         : Jordan
     * Date           :
     * Course/Section : Software Engineering 221-301
     * Program Description: Copy the piece
     * BEGIN copy
     *    create new piece
     *   return new piece
     * END copy
     * ********************************************************/
    @Override
    public Piece copy()
    {
        return new Rook(this);
    }

}//END Rook