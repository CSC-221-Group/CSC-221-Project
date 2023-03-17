package Chess.pieces.Chess;

import Chess.pieces.InvalidMovementException;
import Chess.pieces.Piece;

/** 
 * and how it should move.
 */
public class Pawn extends Piece 
{
    public boolean color;//Holds color of pawn.
    
    /** 
     * Setting up a pawns x location, y location, color, and owner.
     * 
     * @param color - color of pawn.
     * @param x - horizontal location of pawn.
     * @param y -Vertical location of pawn.
     */
        public Pawn(boolean color, int x, int y) 
    {
        //Calls Piece contructor 
        super(x,y);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        if (color == true) {
            loadImage(Chess, "whiteBishop");
        }
        else {
            loadImage(Chess, "blackBishop");
        }
        this.color = color;
    }

    /** 
     * 
     * This method is responsible for confirming if a move is valid
     * 
     * @param x the x pos to move to
     * @param y the y pos to moce to
     * @throws Chess.pieces.InvalidMovementException when an invalid move is attempted
     */
    @Override
    public void move(int x, int y) throws InvalidMovementException {
        //local vars
        int originx = this.getPos().x;
        int originy = this.getPos().y;
        int totalMoved = originy - y;
        boolean allow2Tiles = false;
        System.out.println(y + " - " + originy + " = " + totalMoved);
        //check if x value changed 
        if (x != originx) {
            throw new InvalidMovementException("Pawns can't move along the x axis without capturing");
        }
        //check if black moved up
        if (this.color == false && originy <= y) {
            throw new InvalidMovementException("Black pawns can't move upwards");
        }
        //check if white moved down
        if (this.color == true && originy >= y) {
            throw new InvalidMovementException("White pawns can't move downwards");
        }
        //check if totalMoved is over 2
        if (totalMoved > 2 || totalMoved < -2) {
            throw new InvalidMovementException("Pawns can't move more than 2 tiles");
        }
        //check if on first move
        if ((originy == 1 && this.color == true) || (originy == 6 && this.color == false)) {
            allow2Tiles = true;
        }
        //check if moving twice but already moved before
        if (allow2Tiles == false && (totalMoved == 2)) {
            throw new InvalidMovementException("This pawn already moved therefore it can no longer move 2 tiles.");
        }
        //set position
        this.setPos(x, originy + y);
    }
}


