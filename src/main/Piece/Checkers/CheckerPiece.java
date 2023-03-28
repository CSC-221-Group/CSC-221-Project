package main.Piece.Checkers;

import main.Piece.*;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;;
/**********************************************************
 * Program Name   : Screen 
 * Author         : 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a checker piece
 * is and the legal moves of a checkers piece.
 *  
 * Methods:
 * -------
 * CheckerPiece - sets color position and owner of chekcer piece.
 * move - sets legal moves for checkers piece.
 **********************************************************/
public class CheckerPiece extends Piece
{
    //class constants
    //class variables 
    public String color;
    /************************************/
    /**
     * Consructor of CheckerPiece.
     * Sets owner, color, and (x,y) position of checker piece.
     * @param color - color of checkers piece.
     * @param x - x positon of checkers piece.
     * @param y - y position of checkers piece 
     * @param owner - sets owner of checkers piece,
     */
    public CheckerPiece(String color,int x, int y, int owner) 
    {
        super(x,y,owner);
        String Checkers = "Checkers";
        loadImage(Checkers, color + "CheckerPiece");
        this.color = color;
    }//end checkerPiece 
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}
