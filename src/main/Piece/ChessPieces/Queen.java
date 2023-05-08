package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import main.Piece.InvalidMovementException;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : Queen
 * Author         : Jordan/Alan
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a Queen piece
 * is and the legal moves of a Queen piece.
 *  
 * Methods:
 * -------
 * Queen - sets color position and owner of Queen piece.
 * move - sets legal moves for Queen piece.
 **********************************************************/
public class Queen extends Piece
{
    //class constants
    //class variables 
    public String color; //Color of Piece 
    /************************************/

    /**********************************************************
	* Method Name    : Queen
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Constructor for Queen.
	**********************************************************/
    public Queen (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Queen");
        this.color = color;
    }//END Queen
    
    /**********************************************************
	* Method Name    : move
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method determines what move the Queen can do.
    *BEGIN -move
    *  IF(piece is moving diagonaly)
    *     IF (piece made a valid move and no pieces in the way)
    *        move or capture
    *     ELSE
    *        move is not legal
    *        Throw invalid move
    *  ELSE IF( piece is moving veritcally or horizontally)
        FOR(loop 7 times)
    *     IF(move is valid and or caputing)
    *        move or capture
    *     ELSE IF(piece is moving right)
    *        capture and or move
    *     ELSE IF(Piece is movig down)
    *        capture and or move
    *     ELSE IF(piece is moving to left)
    *        capture and or move
    *     ELSE
    *        move is not legal
    *        Throw Invalid move
    *     END IF
    *   END FOR
    *  END IF
    *END - move
	**********************************************************/
    @Override
    public void move(Cell[][] cells, Screen board, Cell start, Cell end) throws InvalidMovementException
    {
        //Local constants
        //Local variables
        int endX = Math.abs(start.getX() - end.getX());
        int endY = Math.abs(start.getY() - end.getY()); 
        /*****************************************************/

        //IF Piece is moving diagonaly
        if(end.getY() != start.getY() && end.getX() != start.getX())
        {
            //IF endX == endY && no pieces in the way
            if(endX == endY && diagonalCheck(cells, start, end))
            {
                captureORMove(start, end);
            }  
            //ELSE move not legal 
            else
            {
                //Throw InvalidMovementException
                throw new InvalidMovementException();
            }//END IF
        }
        //ELSE IF Piece is moving vertically or horizontally
        else if(end.getY() == start.getY() || end.getX() == start.getX())
        {

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
    }//END move
    
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
        return "Queen";
    }//END Queen
    
    // /**********************************************************
	// * Method Name    : getAllPossibleMoves 
	// * Author         : Jordan
	// * Date           : 
	// * Course/Section : Software Engineering 221-301
	// * Program Description: Return the Piece type
	// **********************************************************/
    // @Override
    // public Cell[][] getAllPossibleMoves(Screen board)
    // {
    //     // Local constants
    //     // Local variables
    //     Cell possibleMoves[][] = new Cell[8][];
    //     /**************************************/
    //     possibleMoves[0] = getPotentialMoves(1,1, board);
    //     possibleMoves[1] = getPotentialMoves(1,-1, board);
    //     possibleMoves[2] = getPotentialMoves(-1,1, board);
    //     possibleMoves[3] = getPotentialMoves(-1,-1, board);
    //     possibleMoves[4] = getPotentialMoves(0,1, board);
    //     possibleMoves[5] = getPotentialMoves(1,0, board);
    //     possibleMoves[6] = getPotentialMoves(0,-1, board);
    //     possibleMoves[7] = getPotentialMoves(-1,0, board);
    //     return possibleMoves;
    // }
}


