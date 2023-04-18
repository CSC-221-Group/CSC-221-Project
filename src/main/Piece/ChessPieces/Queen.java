package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
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
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    @Override
    public String toString()
    {
        return "Queen";
    }
}


