package main.java.Chess;

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
    public void move(Screen board, Cell start, Cell end) 
    {
       
    }
}    

