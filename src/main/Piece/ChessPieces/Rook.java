 
package main.Piece.ChessPieces;

import java.awt.Point;

import main.Piece.InvalidMovementException;
import main.Piece.Piece;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
/**********************************************************
 * Program Name   : Rook
 * Author         : 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a Rook piece
 * is and the legal moves of a Rook piece.
 *  
 * Methods:
 * -------
 * Rook - sets color position and owner of Rook piece.
 * move - sets legal moves for Rook piece.
 **********************************************************/
public class Rook extends Piece
{
    //class constants
    //class variables 
    public String color;
    public  boolean rookWLeft  = false;
    public  boolean rookWRight = false;
    public  boolean rookBLeft = false;
    public  boolean rookBRight = false;
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
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Checks if Rook pieces have moved from 
    * starting position
	**********************************************************/
    public boolean hasNotMoved()
    {   
        //Local constants
        //Local variables 
        Point pos = getPos();
        /*****************************************************/

        //IF piece is owned by white and piece is (X:0Y:0)
        if(getOwnedBy() == 1 && pos.x == 0 && pos.y == 0) 
        {
            return true;
        } 
        //ELSE IF piece is owned by black and piece is (X:7Y:0)
        else if(getOwnedBy() == 2 && pos.x == 7 && pos.y == 0) 
        {
            return true;
        }
        //ELSE piece has moved
        else 
        {
            return false;
        }//END IF 
    }//END hasNotMoved

    /**********************************************************
	* Method Name    : move
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method determines what move the Rook can do.
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
	* Method Name    : getAllPossibleMoves 
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Return the Piece type
	**********************************************************/
    @Override
    public Cell[][] getAllPossibleMoves(Screen board)
    {
        //Local Constants
        //Local Variables
        Cell[][] possibleMoves = new Cell[4][];
        /*****************************************************/
        possibleMoves[0] = getPotentialMoves(1,0,board);
        possibleMoves[1] = getPotentialMoves(-1,0,board);
        possibleMoves[2] = getPotentialMoves(0,1,board);
        possibleMoves[3] = getPotentialMoves(0,-1,board);
        return possibleMoves;
    }

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

}//END Rook