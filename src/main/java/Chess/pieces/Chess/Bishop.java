package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class Bishop extends Piece
{
    public Bishop(boolean color,int x, int y, int ownedBy) 
    {
        super(color, x,y, ownedBy);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        if (color == true) {
            loadImage(Chess, "whiteBishop");
        }
        else {
            loadImage(Chess, "blackBishop");
        }
    }
    @Override
    public void move(int x, int y) throws InvalidMovementException {
        //local vars
        int originx = this.getPos().x;
        int originy = this.getPos().y;
        int totalXMoved = x - originx;
        int totalYMoved = y - originy;
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
        if (absYMoved != absXMoved) {
            throw new InvalidMovementException("Bishops can only move diagonally");
        }
        for (Piece key : Piece.totalPieces) {
            if (key != this && (key.getPos().x == x && key.getPos().y == y)) {
                capture(key, x, y);
            }
        }
        //set position
        this.setPos(x, y);
    }

    @Override
    public void capture(Piece piece, int x, int y) throws InvalidMovementException {
        if (piece.getColor() == this.getColor()) {
            throw new InvalidMovementException("You can't capture your own pieces");
        }
        this.setPos(x, y);
        piece.setIsCaptured(true);
    }
}