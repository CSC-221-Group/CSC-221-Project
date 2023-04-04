package main.Piece.Checkers;

import main.Piece.*;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;;
public class CheckerPiece extends Piece
{
    public String color;
    public CheckerPiece(String color,int x, int y, int owner) 
    {
        super(x,y,owner);
        String Checkers = "Checkers";
        loadImage(Checkers, color + "CheckerPiece");
        this.color = color;
    }
    @Override
    public void move(Screen board, Cell start, Cell end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}