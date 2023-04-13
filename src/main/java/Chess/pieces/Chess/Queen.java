package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class Queen extends Piece
{
    public boolean color;
    public Queen (boolean color,int x, int y, int ownedBy) 
    {
        super(color, x,y, ownedBy);

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
        int totalXMoved = x - originx;
        int totalYMoved = y - originy;
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

