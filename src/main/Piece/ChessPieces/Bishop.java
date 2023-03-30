package main.Piece.ChessPieces;


import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import main.Piece.Piece;

public class Bishop extends Piece
{
    public String color;
    public Bishop(String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Bishop");
        this.color = color;
    }
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}