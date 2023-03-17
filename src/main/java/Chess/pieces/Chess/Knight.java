package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class Knight extends Piece
{
    public boolean color;
    public Knight (boolean color, int x, int y)
    {
        //Calls Piece contructor 
        super(x,y);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        if (color == true) {
            loadImage(Chess, "whiteKnight");
        }
        else {
            loadImage(Chess, "blackKnight");
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
        if (totalXMoved < 0) {
            absXMoved*=-1;
        }
        if (totalYMoved < 0) {
            absYMoved*=-1;
        }
        System.out.println(x + " - " + originx + " = " + absXMoved);
        System.out.println(y + " - " + originy + " = " + absYMoved);
        //check if x value changed 
        if (!(absYMoved == 2 && absXMoved == 1)) {
            if (!(absYMoved == 1 && absXMoved == 2)) {
                throw new InvalidMovementException("Knights can only move in increments of 1x, 2y or 2x, 1y");
            }
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
    

