 
package main.Piece.ChessPieces;

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
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}    


