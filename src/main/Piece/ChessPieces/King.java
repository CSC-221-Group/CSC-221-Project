package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public boolean whiteKing = false; 
    public boolean blackKing = false;
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
    public boolean didKingMove() {
        if(getOwnedBy() == 1 && Screen.cells[0][4].getPiece() != null)
        {
            if(Screen.cells[0][4].getPiece().getClass() == King.class)
            {
                return true;
            }
        }
        else if(getOwnedBy() == 2 && Screen.cells[7][4].getPiece() != null)
        {
            if(Screen.cells[7][4].getPiece().getClass() == King.class)
            {
                return true;
            }
        }
        return false;
    }


    public void kingMove()
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
    public boolean checkIfLaneClear(Rook rook, Cell cells[][]) {
        if(rook.rookWRight) {
            if(cells[5][0].getPiece() == null && cells[6][0].getPiece() == null) {
                return true;
            }
        }
        else if(rook.rookWLeft) {
            if(cells[1][0].getPiece() == null && cells[2][0].getPiece() == null && cells[3][0].getPiece() == null) {
                return true;
            }
        }
        else if(rook.rookBRight) {
            if(cells[5][7].getPiece() == null && cells[6][7].getPiece() == null) {
                return true;
            }
        }
        else if(rook.rookBLeft) {
            if(cells[1][7].getPiece() == null && cells[2][7].getPiece() == null && cells[3][7].getPiece() == null) {
                return true;
            }
        }
        return false;
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
    public void castling(Rook rook,  Cell cells[][]) {
        // depending on the rook and owner we know which side to castle
        // we also know that the king is in the middle of the board
        if(rook.rookWLeft) {
            cells[2][0].setPiece(this);
            cells[3][0].setPiece(rook);
        }
        else if(rook.rookWRight) {
            cells[6][0].setPiece(this);
            cells[5][0].setPiece(rook);
        }
        else if(rook.rookBLeft) {
            cells[2][7].setPiece(this);
            cells[3][7].setPiece(rook);
        }
        else if(rook.rookBRight) {
            cells[6][7].setPiece(this);
            cells[5][7].setPiece(rook);
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

    @Override
    public Array getAllPossibleMoves() {
        return null;
    }
}