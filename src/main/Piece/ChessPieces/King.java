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
    public static boolean whiteKing = true; 
    public static boolean blackKing = true;
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
    public static void kingMove()
    {
        if(Screen.cells[4][0].getPiece() == null || Screen.cells[4][0].getPiece().getClass() != King.class)
        {
            whiteKing = false;
        }
        else

        if(Screen.cells[4][7].getPiece() == null || Screen.cells[4][7].getPiece().getClass() != King.class)
        {
            blackKing = false;
        }
    }
    public static void castling(Piece piece,int x, int y)
    {
    
        if(piece.getOwnedBy() == 1)
        {
            if(whiteKing && Rook.rookWLeft)
            {
                if(!Screen.cells[2][0].isOccupied() && !Screen.cells[1][0].isOccupied() &&  !Screen.cells[3][0].isOccupied())
                {
                    if(x == 2 && y == 0)
                    {
                        Screen.cells[3][0].setPiece(Screen.cells[0][0].getPiece());
                        Screen.cells[0][0].setPiece(null);
                    }

                }
            }

            if(whiteKing && Rook.rookWRight)
            {
                if(!Screen.cells[5][0].isOccupied() && !Screen.cells[6][0].isOccupied())
                {
                    if(x == 6 && y == 0)
                    {
                        Screen.cells[5][0].setPiece(Screen.cells[7][0].getPiece());
                        Screen.cells[7][0].setPiece(null);
                    }

                }
            }
        }
        else
        {

            if(blackKing && Rook.rookBRight)
            {
                if(!Screen.cells[5][7].isOccupied() && !Screen.cells[6][7].isOccupied())
                {
                    if(x == 6 && y == 7)
                    {
                        Screen.cells[5][7].setPiece(Screen.cells[7][7].getPiece());
                        Screen.cells[7][7].setPiece(null);
                    }

                }
            }

            if(blackKing && Rook.rookBLeft)
            {
                if(!Screen.cells[1][7].isOccupied() && !Screen.cells[2][7].isOccupied() &&  !Screen.cells[3][7].isOccupied())
                {
                    if(x == 2 && y == 7)
                    {
                        Screen.cells[3][7].setPiece(Screen.cells[0][7].getPiece());
                        Screen.cells[0][7].setPiece(null);
                    }

                }
            }
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
        return "King";
    }
}

    

