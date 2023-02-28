package main.java.Chess;

public class Bishop extends Piece
{
    public String color;
    public Bishop(String color, int x, int y, int owner)
    {
        super(x, y, owner);
        loadImage(color + "Bishop");
        this.color = color;
    }
    @Override
    public void move(Screen board, Cell start, Cell end) 
    {
        
    }
}