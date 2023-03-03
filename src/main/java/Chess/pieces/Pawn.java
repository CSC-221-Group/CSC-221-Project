package main.java.Chess.pieces;

import java.awt.Point;
import java.io.IOException;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;

/**
 * 
 * This class represents a Pawn
 * 
 * @author Jordan
 * @author Kyle
 */
public class Pawn extends Piece {
    // class vars
    public boolean color = false;
    public boolean canDoubleMovement = true;

    /**
     * 
     * This constructor sets up a pawn
     * 
     * @param color true if white, false if black
     * @param x     the x pos
     * @param y     the y pos
     * @throws java.io.IOException base constructor requires it
     */
    public Pawn(boolean color, int x, int y, int owner) {
        super(x, y, owner);
        String colorString = color ? "white" : "black";
        loadImage( colorString + "Pawn");
        this.color = color;
    }

    /**
     * 
     * This method moves this pawn to a certain location
     * 
     * @param pos the pos to move twords
     */
    @Override
    protected boolean moveCheck(Cell start, Cell end) {
        int x = end.getX();
        int y = end.getY();
        int xStart = start.getX();
        int yStart = start.getY();
        if (color) {
            if (y == yStart + 1 && x == xStart) {
                return true;
            } else if (y == yStart + 2 && x == xStart && canDoubleMovement) {
                return true;
            } else if (y == yStart + 1 && (x == xStart + 1 || x == xStart - 1)) {
                if (end.getPiece() != null) {
                    return true;
                }
            }
        } else {
            if (y == yStart - 1 && x == xStart) {
                return true;
            } else if (y == yStart - 2 && x == xStart && canDoubleMovement) {
                return true;
            } else if (y == yStart - 1 && (x == xStart + 1 || x == xStart - 1)) {
                if (end.getPiece() != null) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 
     * This method attempts a capture on a given location
     * 
     * @param pos the pos to capture
     * @return true if succeeded
     */
    @Override
    protected boolean attemptCapture(Cell pos) {
        return false;
    }
}
