package main.Piece.ChessPieces;

import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.Cell;
import main.Piece.Piece;

import java.lang.reflect.Array;

import main.Piece.InvalidMovementException;

/**********************************************************
 * Program Name : Pawn
 * Author :
 * Date : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a Pawn piece
 * is and the legal moves of a Pawn piece.
 * 
 * Methods:
 * -------
 * Pawn - sets color position and owner of Pawn piece.
 * move - sets legal moves for Pawn piece.
 **********************************************************/
public class Pawn extends Piece {
    // class constants
    // class variables
    public String color;
    public boolean canDoubleMovement = true;
    public static int enPassantW = 0;
    public static int enPassantB = 0;
    /************************************/
    /**
     * Consructor of Pawn.
     * Sets owner, color, and (x,y) position of Pawn piece.
     * 
     * @param color - color of Pawn piece.
     * @param x     - x positon of Pawn piece.
     * @param y     - y position of Pawn piece
     * @param owner - sets owner of Pawn piece
     */
    public Pawn(String color, int x, int y, int owner, Screen board) {
        // Calls Piece contructor
        super(x, y, owner, board);

        // Sets image of pawn depending on color passed.
        String Chess = "Chess";
        loadImage(Chess, color + "Pawn");
        this.color = color;
    }

    /**
     * @param board
     * @param start
     * @param end
     * @throws InvalidMovementException
     */
    @Override
    public void move(Cell cell[][],Screen board, Cell start, Cell end) throws InvalidMovementException {
       
        // lets just isntantly detect capturing
        if (end.getPiece() != null) {
            // this is capturing
            if(getOwnedBy() == 1 ) {
                //white moves up the y axis
                if(end.getX() == start.getX() + 1 && end.getY() == start.getY() + 1) {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } else if(end.getX() == start.getX() - 1 && end.getY() == start.getY() + 1) {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } else {
                    throw new InvalidMovementException("Invalid move");
                }
            } else {
                //black moves down the y axis
                if(end.getX() == start.getX() + 1 && end.getY() == start.getY() - 1) {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } else if(end.getX() == start.getX() - 1 && end.getY() == start.getY() - 1) {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } else {
                    throw new InvalidMovementException("Invalid move");
                }
            }
        } else {
            // this declares that there is nothing at the end point, now we can do normal movemement
            if(start.getY() == 1 && canDoubleMovement) {
                if(end.getX() == start.getX() && end.getY() == start.getY() + 2) {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } else if(end.getX() == start.getX() && end.getY() == start.getY() + 1) {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } else {
                    throw new InvalidMovementException("Invalid move");
                }
            } else if(start.getY() == 6 && canDoubleMovement) {
                if(end.getX() == start.getX() && end.getY() == start.getY() - 2) {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } else if(end.getX() == start.getX() && end.getY() == start.getY() - 1) {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } else {
                    throw new InvalidMovementException("Invalid move");
                }
            } else {
                if(end.getX() == start.getX() && end.getY() == start.getY() + 1) {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } else if(end.getX() == start.getX() && end.getY() == start.getY() - 1) {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } else {
                    throw new InvalidMovementException("Invalid move");
                }
            }
        }
          // we must check if it results in a capture or not

    }

    public static void enPassant(Piece piece, int x, int y) {
        if (piece.getOwnedBy() == 1) {
            if (Screen.cells[x][y - 1].getPiece() != null && Screen.cells[x][y - 1].getPiece().getClass() == Pawn.class
                    && Screen.cells[x][y - 1].getPiece().getOwnedBy() != 1 && enPassantW == 0) {
                Screen.cells[x][y - 1].setPiece(null);

                enPassantW = 1;
            }
        } else {
            if (Screen.cells[x][y + 1].getPiece() != null && Screen.cells[x][y + 1].getPiece().getClass() == Pawn.class
                    && Screen.cells[x][y + 1].getPiece().getOwnedBy() != 2 && enPassantB == 0) {
                Screen.cells[x][y + 1].setPiece(null);

                enPassantB = 1;
            }

        }
    }


    @Override
    public String toString() {
        return "Pawn";
    }
    // @Override
    // public Array getAllPossibleMoves() {
    //     return null;
    // }

}
