package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;

import javax.swing.plaf.nimbus.State;

import main.Piece.InvalidMovementException;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : Knight
 * Author         : 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a Knight piece
 * is and the legal moves of a Knight piece.
 *  
 * Methods:
 * -------
 * Knight - sets color position and owner of Knight piece.
 * move - sets legal moves for Knight piece.
 * toString - returns "Knight".
 **********************************************************/
public class Knight extends Piece
{
    //class constants
    //class variables 
    public String color;
    /************************************/
    /**
     * Consructor of Knight.
     * Sets owner, color, and (x,y) position of Knight piece.
     * @param color - color of Knight piece.
     * @param x - x positon of Knight piece.
     * @param y - y position of Knight piece 
     * @param owner - sets owner of Knight piece,
     */
    public Knight (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Knight");
        this.color = color;
    }//END Knight

    /**
     *  This method determines what move the knights can do.
     * @throws InvalidMovementException - if move not legal it throws the exception
     */
    @Override
    public void move(Cell[][] cells, Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        //IF release points Y greater then starting point Y
        if(end.getY() > start.getY())
        {
            //IF relesse point is 2 greater than starting points Y and 
            //release point is 1 greater than starting point X
            if(end.getY() == start.getY() + 2 && end.getX() == start.getX() + 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF relesse point is 2 greater than starting points Y and 
            //release point is 1 less than starting point X
            else if(end.getY() == start.getY() + 2 && end.getX() == start.getX() -1)
            {
                captureORMove(start, end);
            }
            //ELSE IF relesse point is 1 greater than starting points Y and 
            //release point is 2 less than starting point X
            else if (end.getY() == start.getY() + 1 && end.getX() == start.getX() - 2)
            {
                captureORMove(start, end);
            }
            //ELSE IF relesse point is 1 greater than starting points Y and 
            //release point is 2 lessthan starting point X
            else if(end.getY() == start.getY() + 1 && end.getX() == start.getX() + 2)
            {
                captureORMove(start, end);
            }//END IF
        }
        else if(end.getY() < start.getY())
        {
            //IF relesse point is 2 less than starting points Y and 
            //release point is 1 greater than starting point X
            if(end.getY() == start.getY()  - 2 && end.getX() == start.getX() + 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF relesse point is 2 less than starting points Y and 
            //release point is 1 less than starting point X
            else if(end.getY() == start.getY() - 2 && end.getX() == start.getX() - 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF relesse point is 1 less than starting points Y and 
            //release point is 12less than starting point X
            else if(end.getY() == start.getY() - 1 && end.getX() == start.getX() - 2)
            {
                captureORMove(start, end);
            }
            //ELSE IF relesse point is 1 less than starting points Y and 
            //release point is 2greater than starting point X
            else if(end.getY() == start.getY() - 1 && end.getX() == start.getX() + 2)
            {
                captureORMove(start, end);
            }//END IF 
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
        return "Knight";
    }
    @Override
    public Cell[][] getAllPossibleMoves(Screen board) 
    {
        //Local constants
        //Local variables
        Cell[][] possibleMoves = new Cell[4][2];
        /****************************************/
        // Add checks to make sure the cell the knight can move to is not null
        // Add checks to make sure the cell the knight can move to is not occupied by a piece of the same color
        if(getPos().x - 2 >= 0 && getPos().y + 1 < 8 && getPos().y + 1 >= 0 && getPos().x - 2 < 8)
        {
            possibleMoves[0][0] = board.getCell(getPos().x - 2, getPos().y + 1);
            possibleMoves[0][1] = board.getCell(getPos().x - 1, getPos().y + 2);
        }
        if(getPos().x + 2 >= 0 && getPos().y + 1 < 8 && getPos().y + 1 >= 0 && getPos().x + 2 < 8)
        {
            possibleMoves[1][0] = board.getCell(getPos().x + 2, getPos().y + 1);
            possibleMoves[1][1] = board.getCell(getPos().x + 1, getPos().y + 2);
        }
        if(getPos().x - 2 >= 0 && getPos().y - 1 < 8 && getPos().y - 1 >= 0 && getPos().x - 2 < 8)
        {
            possibleMoves[2][0] = board.getCell(getPos().x - 2, getPos().y - 1);
            possibleMoves[2][1] = board.getCell(getPos().x - 1, getPos().y - 2);
        }
        if(getPos().x + 2 >= 0 && getPos().y - 1 < 8 && getPos().y - 1 >= 0 && getPos().x + 2 < 8)
        {
            possibleMoves[3][0] = board.getCell(getPos().x + 2, getPos().y - 1);
            possibleMoves[3][1] = board.getCell(getPos().x + 1, getPos().y - 2);
        }
        return possibleMoves;
    }
}


