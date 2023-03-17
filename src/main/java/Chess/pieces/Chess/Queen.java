package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class Queen extends Piece
{
    public boolean color;
    public Queen (boolean color, int x, int y)
    {
        //Calls Piece contructor 
        super(x,y);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        if (color == true) {
            loadImage(Chess, "whiteQueen");
        }
        else {
            loadImage(Chess, "blackQueen");
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
        //check if x value changed 
        if (absYMoved != absXMoved && (absYMoved != 0 && absXMoved != 0)) {
            throw new InvalidMovementException("Queens can only move diagonally, vertically or horizontally");
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

