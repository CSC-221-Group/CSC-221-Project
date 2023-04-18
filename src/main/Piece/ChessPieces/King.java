package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : King
 * Author         : 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a King piece
 * is and the legal moves of a King piece.
 *  
 * Methods:
 * -------
 * King- sets color position and owner of King piece.
 * move - sets legal moves for King piece.
 **********************************************************/
public class King extends Piece
{
    //class constants
    //class variables 
    public String color;
    /************************************/
    /**
     * Consructor of King.
     * Sets owner, color, and (x,y) position of King piece.
     * @param color - color of King piece.
     * @param x - x positon of King piece.
     * @param y - y position of King piece 
     * @param owner - sets owner of King piece,
     */
    public King (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Bishop");
        this.color = color;
    }//end King 
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    @Override
    public String toString()
    {
        return "King";
    }
}

    

