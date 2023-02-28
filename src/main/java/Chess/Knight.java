
package main.java.Chess;

public class Knight extends Piece
{
    public String color;
    public Knight (String color, int x, int y, int owner)
    {
        super(x, y, owner);
        loadImage(color + "Knight");
        this.color = color;
    }
    @Override
    public void move(Screen board, Cell start, Cell end) 
    {
        
        
    }
}
    

