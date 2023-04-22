package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
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
    }
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    @Override
    public String toString()
    {
        return "Knight";
    }
}


