package main.Piece.ChessPieces;
import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.Cell;
import main.Piece.Piece;

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
    public void move(Screen board, Cell start, Cell end) 
    {
        Piece [][] pawn = new Piece [start.getX() ][start.getY()];

        if(end.getX() == start.getX() && end.getY() == start.getY())
        {
            return ;
        } 
        if(end.isOccupied() )
        {
            return ;
        }
        if(pawn[end.getX()][end.getY()] != null && board.)
        {
            
            return;
        }
        if(color.equals("white"))
        {
            if(start.getY() == 1 && end.getY() == 3 && start.getX() == end.getX())
            {
                return;
            }
        }
    }
}


