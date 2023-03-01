package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.Piece.Piece;

public class Queen extends Piece
{
    public String color;
    public Queen (String color, int x, int y, int owner)
    {
        super(x, y, owner);
        loadImage(color + "Queen");
        this.color = color;
    }
    @Override
    public boolean move(Piece[][] board, Cell start, Cell end) 
    {
       return false;
    }
}

