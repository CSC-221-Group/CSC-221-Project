package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : King
 * Author         : Alan, Refactoring by Jordan
 * Date           : 3/19/23, 4/25, 4/22
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a King piece
 * is and the legal moves of a King piece.
 * It also handles Castling.
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
        if(Screen.cells[0][4].getPiece() != null)
        {
            if(Screen.cells[0][4].getPiece().getClass() != King.class)
            {
                whiteKing = false;
            }
        }
        if(Screen.cells[7][4].getPiece() != null)
        {
            if(Screen.cells[7][4].getPiece().getClass() != King.class)
            {
                blackKing = false;
            }
        }
    }
    private static boolean checkIfLaneClear(String side, int player) {
        if(player == 1)
        {
            if(side.equals("left"))
            {
                for(int i = 1; i < 4; i++)
                {
                    if(Screen.cells[i][0].isOccupied())
                    {
                        return false;
                    }
                }
                return true;
            }
            else
            {
                for(int i = 5; i < 7; i++)
                {
                    if(Screen.cells[i][0].isOccupied())
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        else
        {
            if(side.equals("left"))
            {
                for(int i = 1; i < 4; i++)
                {
                    if(Screen.cells[i][7].isOccupied())
                    {
                        return false;
                    }
                }
                return true;
            }
            else
            {
                for(int i = 5; i < 7; i++)
                {
                    if(Screen.cells[i][7].isOccupied())
                    {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private static Piece getRook(String side, int player) { // player 1 = white, player 2 = black
        if(player == 1)
        {
            if(side.equals("left"))
            {
                return Screen.cells[0][0].getPiece();
            }
            else
            {
                return Screen.cells[7][0].getPiece();
            }
        }
        else
        {
            if(side.equals("left"))
            {
                return Screen.cells[0][7].getPiece();
            }
            else
            {
                return Screen.cells[7][7].getPiece();
            }
        }
    }

    public static void castling(Piece piece, int x, int y, Screen board)
    {
        // removed gthe check to see if it is a king, logically speaking it should only be called if it is a king


            if(piece.getOwnedBy() == 1)
            {
                Rook rook = (Rook) getRook("left", 1);
                if(whiteKing && rook.hasNotMoved()) // if the king and rook have not moved
                {
                    if(checkIfLaneClear("left", 1))
                    {
                        if(x == 2 && y == 0)
                        {
                            Screen.cells[3][0].setPiece(Screen.cells[0][0].getPiece());
                            Screen.cells[0][0].setPiece(null);
                        }

                    }
                }
                rook = (Rook) getRook("right", 1);
                if(whiteKing && rook.hasNotMoved())
                {
                    if(checkIfLaneClear("right", 1))
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
                Rook rook = (Rook) getRook("left", 2);
                if(blackKing && rook.hasNotMoved())
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
                rook = (Rook) getRook("right", 2);
                if(blackKing && rook.hasNotMoved())
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
            board.repaint();
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