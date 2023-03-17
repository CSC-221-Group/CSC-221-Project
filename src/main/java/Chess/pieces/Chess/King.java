package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class King extends Piece
{
    public boolean color;
    public King (boolean color, int x, int y)
    {
       //Calls Piece contructor 
        super(x,y);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        if (color == true) {
            loadImage(Chess, "whiteKing");
        }
        else {
            loadImage(Chess, "blackKing");
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
        int absXMoved = totalXMoved;
        int absYMoved = totalYMoved;
        System.out.println(x + " - " + originx + " = " + totalXMoved);
        System.out.println(y + " - " + originy + " = " + totalYMoved);
        if (totalXMoved < 0) {
            absXMoved*=-1;
        }
        if (totalYMoved < 0) {
            absYMoved*=-1;
        }
        //check if x value changed 
        if (absYMoved != absXMoved && (absYMoved != 0 && absXMoved != 0)) {
            throw new InvalidMovementException("Kings can only move diagonally, vertically or horizontally");
        }
        if (absYMoved > 1 || absXMoved > 1) {
            throw new InvalidMovementException("Kings can't move more than 1 tile");
        }
        for (Piece key : Piece.totalPieces) {
            if ((key.getPos().x == x && key.getPos().y == (7 - y))) {
                throw new InvalidMovementException("You should be calling the capture method when pieces touch each other");
            }
        }
        //set position
        this.setPos(x, 7 - y);
    }

}
    

