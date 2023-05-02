package main.Piece.ChessPieces;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;

import java.awt.Point;
import java.lang.reflect.Array;

import main.Piece.InvalidMovementException;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : Bishop
 * Author         : Jordan/Alan
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a bishop piece
 * is and the legal moves of a bishop piece.
 *  
 * Methods:
 * -------
 * Bishop - sets color position and owner of bishop piece.
 * move - sets legal moves for bishop piece.
 * toString - returns "Bishop".
 **********************************************************/
public class Bishop extends Piece
{
    //class constants
    //class variables 
    public String color; //Color of piece
    /************************************/

    /**
     * Consructor of Bishop.
     * Sets owner, color, and (x,y) position of bishop piece.
     * @param color - color of bishop piece.
     * @param x - x positon of bishop piece.
     * @param y - y position of bishop piece .
     * @param owner - sets owner of bishop piece.
     * @param board - sets the size of png of piece.
     */
    public Bishop(String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Bishop");
        this.color = color;
    }//END Bishop
    
    /**
     * This method determines what move the bishops can do.
     * @throws InvalidMovementException - if move not legal it throws the exception 
     */
    @Override
    public void move(Cell[][] cells, Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        //Local constants
        //Local variables 
        int endX = Math.abs(start.getX() - end.getX());//Absolute value of the starting X minus the ending X
        int endY = Math.abs(start.getY() - end.getY());//Absolute value of the starting Y minus the ending Y
        /*****************************************************/
       
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
    }//END move

    @Override
    public String toString()
    {
        return "Bishop";
    }

    @Override
    public Cell[][] getAllPossibleMoves(Screen board)
    {
        //Local constants
        //Local variables
        Cell possibleMoves[][] = new Cell[4][];
        /*****************************************************/
        possibleMoves[0] = getPotentialMoves(1, 1, board);
        possibleMoves[1] = getPotentialMoves(-1, 1, board);
        possibleMoves[2] = getPotentialMoves(1, -1, board);
        possibleMoves[3] = getPotentialMoves(-1, -1, board);
        return possibleMoves;
    }



}

