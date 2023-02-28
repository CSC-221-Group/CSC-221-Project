package main.java.Chess;

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
    public void move(Screen board, Cell start, Cell end) 
    {
        
    }
}

