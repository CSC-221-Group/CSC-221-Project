package main.java.Chess;

public class Pawn extends Piece 
{
    public String color;
    public Pawn(String color, int x, int y, int owner) 
    {
        super(x,y, owner);
        loadImage(color + "Pawn");
        this.color = color;
    }

    /** 
     * @param board
     * @param start
     * @param end
     */
    @Override
    public boolean move(Piece [][]board, Cell start, Cell end) 
    {
        Piece endMove = board[end.getX()][end.getY()];
        if(end.getX() == start.getX() && end.getY() == start.getY())
        {
            return false;
        } 
        if(endMove != null && color.equals(endMove))
        {
            return false;
        }
        if(color.equals("white"))
        {
            if(start.getY() == 1 && end.getY() == 3 && start.getX() == end.getX())
            {
                return true;
            }
        }
        return false; 
    }
}
