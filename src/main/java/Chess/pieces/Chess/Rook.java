package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class Rook extends Piece
{
    public boolean color;
    public Rook (boolean color, int x, int y)
    {
        //Calls Piece contructor 
        super(x,y);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        if (color == true) {
            loadImage(Chess, "whiteRook");
        }
        else {
            loadImage(Chess, "blackRook");
        }
        this.color = color;
    }
    @Override
    public void move(int x, int y) throws InvalidMovementException {
        //local vars
        int originx = this.getPos().x;
        int originy = this.getPos().y;
        int totalXMoved = originx - x;
        int totalYMoved = originy - (7 - y);
        System.out.println(x + " - " + originx + " = " + totalXMoved);
        System.out.println(y + " - " + originy + " = " + totalYMoved);
        //check if x value changed 
        if (totalXMoved !=0 && totalYMoved !=0) {
            throw new InvalidMovementException("Rooks can only move horizontally or vertically");
        }
        //set position
        this.setPos(x, 7 - y);
    }
}    

