 
package main.Piece.ChessPieces;

import java.awt.Point;
<<<<<<< HEAD
=======
import java.lang.reflect.Array;

>>>>>>> 81a996bc6fe7d81352e3b46b3d6bc9b35490e55a
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
    /**
     * Consructor of King.
     * Sets owner, color, and (x,y) position of Rook piece.
     * @param color - color of Rook piece.
     * @param x - x positon of Rook piece.
     * @param y - y position of Rook piece 
     * @param owner - sets owner of Rook piece,
     */
    public Rook (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Rook");
        this.color = color;
    }
    public boolean hasNotMoved() {
        Point pos = getPos();
        if(getOwnedBy() == 1 && pos.x == 0 && pos.y == 0) {
            return true;
        } else if(getOwnedBy() == 2 && pos.x == 7 && pos.y == 0) {
            return true;
        } else {
            return false;
        }
    }
   
    @Override
<<<<<<< HEAD
    public void move(Cell cells[][],Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        if(end.getPiece() != null)
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
        else
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
=======
    public void move(Screen board, Cell start, Cell end) throws InvalidMovementException {
        if(end.getPiece() != null) {
            // This is capturing
            if(start.getX() == end.getX()) {
                // assume this is a vertial move
                if(getOwnedBy() == 1) {
                    // white moves up the y axis
                    if(start.getY() < end.getY()) {
                        // moving up
                        System.out.println("Checking path: " + start.getY() + " to " + end.getY());
                        if(checkPotentialPath(board, start, end, 0, 1)) {
                            
                            // move is valid
                            capture(end.getPiece(), start, end);
                        }
                    } else {
                        // moving down
                        if(checkPotentialPath(board, start, end, 0, -1)) {
                            // move is valid
                            capture(end.getPiece(), start, end);
                        }
                    }
                } else {
                    // black moves down the y axis
                    if(start.getY() < end.getY()) {
                        // moving up
                        if(checkPotentialPath(board, start, end, 0, 1)) {
                            // move is valid
                            capture(end.getPiece(), start, end);
                        }
                    } else {
                        // moving down
                        if(checkPotentialPath(board, start, end, 0, -1)) {
                            // move is valid
                            capture(end.getPiece(), start, end);
                        }
                    }
                }
            } else if(start.getY() == end.getY()) {
                int x = start.getX() < end.getX() ? 1 : -1;
                if(checkPotentialPath(board, start, end, x, 0)) {
                    // move is valid
                    capture(end.getPiece(), start, end);
                }
            }
        } else {
            // Normal movement, not capture
            if(start.getX() == end.getX()) {
                // assume this is a vertial move
                if(getOwnedBy() == 1) {
                    // white moves up the y axis
                    if(start.getY() < end.getY()) {
                        // moving up
                        if(checkPotentialPath(board, start, end, 0, 1)) {
                            // move is valid
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                        }
                    } else {
                        // moving down
                        if(checkPotentialPath(board, start, end, 0, -1)) {
                            // move is valid
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                        }
                    }
                } else {
                    // black moves down the y axis
                    if(start.getY() < end.getY()) {
                        // moving up
                        if(checkPotentialPath(board, start, end, 0, 1)) {
                            // move is valid
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                        }
                    } else {
                        // moving down
                        if(checkPotentialPath(board, start, end, 0, -1)) {
                            // move is valid
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                        }
                    }
                }
            } else if(start.getY() == end.getY()) {
                int x = start.getX() < end.getX() ? 1 : -1;
                if(checkPotentialPath(board, start, end, x, 0)) {
                    // move is valid
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                }
>>>>>>> 81a996bc6fe7d81352e3b46b3d6bc9b35490e55a
            }
        }
    }
    @Override
    public String toString()
    {
        return "Rook";
    }
<<<<<<< HEAD
}
=======

    @Override
    public Array getAllPossibleMoves() {
        return null;
    }
}    
>>>>>>> 81a996bc6fe7d81352e3b46b3d6bc9b35490e55a
