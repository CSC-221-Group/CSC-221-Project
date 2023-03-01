package main.Piece.ChessPieces;
import main.Piece.Piece;
import main.java.Chess.frontend.Cell;
/** 
 * and how it should move.
 */
public class Pawn extends Piece 
{
    public String color;//Holds color of pawn.
    
    /** 
     * Setting up a pawns x location, y location, color, and owner.
     * 
     * @param color - color of pawn.
     * @param x - horizontal location of pawn.
     * @param y -Vertical location of pawn.
     * @param owner - which color(White/Black) owns the piece.
     */
        public Pawn(String color, int x, int y, int owner) 
    {
        //Calls Piece contructor 
        super(x,y, owner);

        //Sets image of pawn depending on color passed.
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
