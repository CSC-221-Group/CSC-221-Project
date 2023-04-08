

package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : Bishop
 * Author         : 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a bishop piece
 * is and the legal moves of a bishop piece.
 *  
 * Methods:
 * -------
 * Bishop - sets color position and owner of bishop piece.
 * move - sets legal moves for bishop piece.
 **********************************************************/
public class Bishop extends Piece
{
    //class constants
    //class variables 
    public String color;
    /************************************/
    /**
     * Consructor of Bishop.
     * Sets owner, color, and (x,y) position of bishop piece.
     * @param color - color of bishop piece.
     * @param x - x positon of bishop piece.
     * @param y - y position of bishop piece 
     * @param owner - sets owner of bishop piece,
     */
    public Bishop(String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Bishop");
        this.color = color;
    }//end Bishop
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    @Override
    public String toString()
    {
        return "Bishop";
    }
}
