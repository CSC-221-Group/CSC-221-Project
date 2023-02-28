package main.Piece.ChessPieces;
import main.Piece.Piece;
import main.java.Chess.frontend.App;
import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.Cell;
public class King extends Piece
{
    public String color;
    public King (String color, int x, int y, int owner)
    {
        super(x, y, owner);
        loadImage(color + "Bishop");
        this.color = color;
    }
    @Override
    public boolean move(Piece[][] board, Cell start, Cell end) 
    {
       return false;
    }
}
    

