package main.Piece.ChessPieces;
import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.Cell;
import main.Piece.Piece;
import main.Piece.InvalidMovementException;

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
        public Pawn(String color, int x, int y, int owner, Screen board) 
    {
        //Calls Piece contructor 
        super(x,y, owner, board);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        loadImage(Chess,color + "Pawn");
        this.color = color;
    }

    /** 
     * @param board
     * @param start
     * @param end
     * @throws InvalidMovementException
     */
    @Override
    public void move(Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        Piece [][] pawn = new Piece [start.getX() ][start.getY()];



        if(end.getX() == start.getX() && end.getY() == start.getY())
        {
            throw new InvalidMovementException("You cannot move to the same spot");
        } 
        if(end.isOccupied() )
        {
            // handle capturing, ensure that the piece is of the opposite color and it can capture like that
            return;
        }
        if(pawn[end.getX()][end.getY()] != null)
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


