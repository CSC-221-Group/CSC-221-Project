package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import main.Piece.InvalidMovementException;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : Queen
 * Author         : 
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
    public String color;
    /************************************/
    /**
     * Consructor of Queen.
     * Sets owner, color, and (x,y) position of Queen piece.
     * @param color - color of Queen piece.
     * @param x - x positon of Queen piece.
     * @param y - y position of Queen piece 
     * @param owner - sets owner of Queen piece,
     */
    public Queen (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Queen");
        this.color = color;
    }
    @Override
    public String toString()
    {
        return "Queen";
    }
    
    @Override
    public void move(Cell[][] cells, Screen board, Cell start, Cell end) throws InvalidMovementException
    {
        int endX = Math.abs(start.getX() - end.getX());
        int endY = Math.abs(start.getY() - end.getY());
        if(end.getPiece() != null)
        {
           
            
            if(end.getY() != start.getY() && end.getX() != start.getX())
            {
                if(endX == endY && diagonalCheck(cells, start, end))
                {
                    capture(end.getPiece(), start, end);
                }  
            }
            else if(end.getY() == start.getY() || end.getX() == start.getX())
            {
                for(int i = 0;i <= 7; i++)
                {  
                    if(end.getX() == start.getX() && end.getY() == start.getY() + i && laneCheckYAxis(cells, start, end))
                    {
                        capture(end.getPiece(), start, end);
                    }
                    else if(end.getX() == start.getX() + i && end.getY() == start.getY() && laneCheckXAxis(cells, start, end))
                    {
                        capture(end.getPiece(), start, end);
                    }
                    else if(end.getX() == start.getX() && end.getY() == start.getY() - i && laneCheckYAxis(cells, start, end))
                    {
                        capture(end.getPiece(), start, end);
                    }
                    else if(end.getX() == start.getX() - i && end.getY() == start.getY() && laneCheckXAxis(cells, start, end))
                    {
                        capture(end.getPiece(), start, end);
                    }
                    else if(!capture(end.getPiece(), start, end))
                    {
                        throw new InvalidMovementException("Invalid move");
                    }
                }
            }

        }
        else
        {
            if(end.getY() != start.getY() && end.getX() != start.getX())
            {
                if(endX == endY && diagonalCheck(cells, start, end))
                {
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                }  
                else
                {
                    throw new InvalidMovementException("Invalid move");
                }
            }
            else if(end.getY() == start.getY() || end.getX() == start.getX())
            {
                for(int i = 0;i <= 7; i++)
                { 
                
                    if(end.getX() == start.getX() - i && end.getY() == start.getY() && laneCheckXAxis(cells, start, end))
                    {
                        end.setPiece(start.getPiece());
                        start.setPiece(null);
                    }
                    else if(end.getX() == start.getX() + i && end.getY() == start.getY() && laneCheckXAxis(cells, start, end))
                    {
                        end.setPiece(start.getPiece());
                        start.setPiece(null);
                    }
                
                    if(end.getX() == start.getX() && end.getY() == start.getY() - i && laneCheckYAxis(cells, start, end))
                    {
                        end.setPiece(start.getPiece());
                        start.setPiece(null);
                    }
                    else if(end.getX() == start.getX() && end.getY() == start.getY() + i && laneCheckYAxis(cells, start, end))
                    {
                        end.setPiece(start.getPiece());
                        start.setPiece(null);
                    }
                    if(i == 7)
                    {
                        if(start.getPiece() != null)
                        {
                            throw new InvalidMovementException("Invalid move");
                        }
                    }
                }
            }
        }
    }
}


