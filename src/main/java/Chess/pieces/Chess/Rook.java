package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

public class Rook extends Piece
{
    public boolean color;
    public Rook (boolean color,int x, int y, int ownedBy) 
    {
        super(color, x,y, ownedBy);

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
        int totalXMoved = x - originx;
        int totalYMoved = y - originy;
        System.out.println(x + " - " + originx + " = " + totalXMoved);
        System.out.println(y + " - " + originy + " = " + totalYMoved);
        //check if x value changed 
        if (totalXMoved !=0 && totalYMoved !=0) {
            throw new InvalidMovementException("Rooks can only move horizontally or vertically");
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

