package main.java.Chess;

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
    public void move(Screen board, Cell start, Cell end) 
    {
        
    }
}
    

