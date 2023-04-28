 
package main.Piece.ChessPieces;

import java.awt.Point;

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
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    @Override
    public String toString()
    {
        return "Rook";
    }
}    
