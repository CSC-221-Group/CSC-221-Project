package main.Piece.ChessPieces;
import main.Piece.Piece;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;

public class Rook extends Piece
{
    public String color;
    public Rook (String color, int x, int y, int owner)
    {
        super(x, y, owner);
        loadImage(color + "Rook");
        this.color = color;
    }
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}    

