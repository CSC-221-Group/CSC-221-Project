package Chess.pieces;

import java.awt.Point;
import java.io.IOException;

/**
 * 
 * This class represents a Pawn
 * 
 * @author Jordan
 * @author Kyle
 */
public class Pawn extends Piece {
    //class vars
    public boolean color = false;
    public boolean canDoubleMovement = true;
    /**
     * 
     * This constructor sets up a pawn
     * 
     * @param color true if white, false if black
     * @param x the x pos
     * @param y the y pos
     * @throws java.io.IOException base constructor requires it
     */
    public Pawn(boolean color, int x, int y) throws IOException {
        super(x,y,"blackPawn");
        if (color == true) {
            this.loadImage("whitePawn");
        }
        this.color = color;
    }
    /**
     * 
     * This method moves this pawn to a certain location
     * 
     * @param pos the pos to move twords
     */
    @Override
    protected void movePiece(Point pos) {
        
    }
    /**
     * 
     * This method attempts a capture on a given location
     * 
     * @param pos the pos to capture
     * @return true if succeeded
     */
    @Override
    protected boolean attemptCapture(Point pos) {
        return false;
    }
}

