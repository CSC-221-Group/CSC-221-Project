package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;

import java.lang.reflect.Array;
import java.util.ArrayList;

import main.Piece.InvalidMovementException;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : King
 * Author         : Alan, Refactoring by Jordan
 * Date           : 3/19/23, 4/25, 4/22
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a King piece
 * is and the legal moves of a King piece.
 * It also handles Castling.
 *  
 * Methods:
 * -------
 * King- sets color position and owner of King piece.
 * move - sets legal moves for King piece.
 **********************************************************/
public class King extends Piece
{
    //class constants
    //class variables 
    public String color;
    public boolean whiteKing = false; //
    public boolean blackKing = false;
    /************************************/

    /*
     * Consructor of King.
     * Sets owner, color, and (x,y) position of King piece.
     * @param color - color of King piece.
     * @param x - x positon of King piece.
     * @param y - y position of King piece 
     * @param owner - sets owner of King piece.
     * @param board - sets the size of png of piece.
     */
    public King (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "Bishop");
        this.color = color;
    }//END King 

    /**
     * This method checks if King moved.
     * @return true if King didn't move false if King did move
     */
    public boolean didKingMove() 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF Piece at (X:0,Y:4) is not empty and owned by white
        //Starting position of White King
        if(getOwnedBy() == 1 && Screen.cells[0][4].getPiece() != null)
        {
            //IF Piece at (X:0,Y:4) is equal to a king
            if(Screen.cells[0][4].getPiece().getClass() == King.class)
            {
                return true;
            }//END IF
        }
        //ELSE IF Piece at (X:7,Y:4) is not empty and owned by black
        //Starting position of Black King
        else if(getOwnedBy() == 2 && Screen.cells[7][4].getPiece() != null)
        {
            //IF Piece at (X:7,Y:4) is equal to a king
            if(Screen.cells[7][4].getPiece().getClass() == King.class)
            {
                return true;
            }//END IF

        }//END IF
        return false;
    }//END didKingMove

    /**
     * This method checks if King moved.
     */
    public void kingMove()
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF Piece at (X:0,Y:4) is not empty
        if(Screen.cells[0][4].getPiece() != null)
        {
            //IF Piece at (X:0,Y:4) is not equal to a king
            if(Screen.cells[0][4].getPiece().getClass() != King.class)
            {
                //Set whiteKing to false
                whiteKing = false;
            }//END IF
        }//END IF

        //IF Piece at (X:7,Y:4) is not empty
        if(Screen.cells[7][4].getPiece() != null)
        {
            //IF Piece at (X:7,Y:4) is not equal to a king
            if(Screen.cells[7][4].getPiece().getClass() != King.class)
            {
                //Set blackKing to false 
                blackKing = false;
            }//END IF
        }//END IF
    }//END kingMove

    /**
     * Checks if lane is clear to see if castling is possible.
     * @param rook - check if rooks are at starting position.
     * @param cells - checks if specific squares on board have no Pieces 
     * @return - returns true if castling is possible false if not.
     */
    public boolean checkIfLaneClear(Rook rook, Cell cells[][]) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF rookWRight is at starting position
        if(rook.rookWRight) 
        {
            //IF Piece at (X:5Y:0) is empty and Piece(X:6Y:0) is empty
            //Checks right side of whiteKing to see if castling is possible
            if(cells[5][0].getPiece() == null && cells[6][0].getPiece() == null) 
            {
                return true;
            }
        }
        //ELSE IF rookWLeft is at starting position
        else if(rook.rookWLeft)
        {
            //IF Piece at (X:1Y:0) is empty and Piece(X:2Y:0) is empty and
            //   Piece at(X:3Y:0) is empty
            //Checks left side of whiteKing to see if castling is possible
            if(cells[1][0].getPiece() == null && cells[2][0].getPiece() == null 
                && cells[3][0].getPiece() == null) 
            {
                return true;
            }
        }
        //ELSE IF rookBRight is at starting position
        else if(rook.rookBRight) 
        {
            //IF Piece at (X:5Y:7) is empty and Piece(X:6Y:7) is empty
            //Checks right side of blackKing to see if castling is possible
            if(cells[5][7].getPiece() == null && cells[6][7].getPiece() == null) 
            {
                return true;
            }
        }
        //ELSE IF rookBLeft is at starting position
        else if(rook.rookBLeft) 
        {
            //IF Piece at (X:1Y:7) is empty and Piece(X:2Y:7) is empty and
            //   Piece at(X:3Y:7) is empty
            //Checks left side of blackKing to see if castling is possible
            if(cells[1][7].getPiece() == null && cells[2][7].getPiece() == null
                && cells[3][7].getPiece() == null) 
            {
                return true;
            }
        }//END IF

        return false;
     }//END checkIfLaneClear

    /**
     * 
     * @param side - direction of castling
     * @param player - White or Black lanes.
     * @return
     */
    private static Piece getRook(String side, int player) 
    { 
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF player is white 
        if(player == 1)
        {
            //IF String equal "left"
            if(side.equals("left"))
            {
                //Return piece at (X:0Y:0)
                return Screen.cells[0][0].getPiece();
            }
            //ELSE String equal "right"
            else
            {
                //Return piece at (X:7Y:0)
                return Screen.cells[7][0].getPiece();
            }//END IF
        }
        //ELSE Player is black
        else
        {
            //IF String equal "left"
            if(side.equals("left"))
            {
                //Return piece at (X:0Y:7)
                return Screen.cells[0][7].getPiece();
            }
            //ELSE String equal "right"
            else
            {
                //Return piece at (X:7Y:7)
                return Screen.cells[7][7].getPiece();
            }//END IF
        }//END IF
    }//END getRook

    /**
     * This method set pieces to right position for the technique
     * castling.
     * @param rook
     * @param cells
     */
    public void castling(Rook rook,  Cell cells[][]) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        // depending on the rook and owner we know which side to castle
        // we also know that the king is in the middle of the board

        //IF rookWleft is true
        //This means that rook hasn't moved
        if(rook.rookWLeft) 
        {
            cells[2][0].setPiece(this);
            cells[3][0].setPiece(rook);
        }
        //ELSE IF rookWright is true
        else if(rook.rookWRight) 
        {
            cells[6][0].setPiece(this);
            cells[5][0].setPiece(rook);
        }
        //ELSE IF rookBLeft is true
        else if(rook.rookBLeft) 
        {
            cells[2][7].setPiece(this);
            cells[3][7].setPiece(rook);
        }
        //ELSE IF rookBRight is true
        else if(rook.rookBRight) 
        {
            cells[6][7].setPiece(this);
            cells[5][7].setPiece(rook);
        }

    }
    /**
     * This method determines what move the kings can do.
     * @throws InvalidMovementException - if move not legal it throws the exception
     */
    @Override
    public void move(Cell[][] cells, Screen board, Cell start, Cell end) throws InvalidMovementException
    {
        //IF Piece moved up
        if(end.getY() > start.getY())
        {
            //IF piece moved to right and up 1
            if((end.getX() == start.getX() + 1) && end.getY() == start.getY() + 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF piece move to the left and up one 
            else if((end.getX() == start.getX() - 1) && end.getY() == start.getY() + 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF piece move up 1 
            else if(end.getX() == start.getX() && end.getY() == start.getY() + 1)
            {
                captureORMove(start, end);
            }
            //ELSE move not legal
            else
            {
                //Throw InvalidMoveException
                throw new InvalidMovementException();
            }//END IF 
        }
        //ELSE IF piece stayed in same collum
        else if(end.getY() == start.getY())
        {
            //IF piece moved left 1 and stayed in the same collum
            if(end.getX() == start.getX() - 1 && end.getY() == start.getY())
            {
                captureORMove(start, end);
            }
            //ELSE IF piece move to the right and stayed in the same collum
            else if(end.getX() == start.getX() + 1 && end.getY() == start.getY())
            {
                captureORMove(start, end);
            }
            //ELSE move not legal 
            else
            {
                //Throw InvalidMoveException
                throw new InvalidMovementException();
            }
        }
        //ELSE IF piece moved down 1 
        else if(end.getY()< start.getY())
        {
            //IF piece moved to the right 1 and down 1
            if((end.getX() == start.getX() + 1) && end.getY() == start.getY() - 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF piece moved left 1 and down 1
            else if(end.getX() == start.getX() - 1 && end.getY() == start.getY() - 1)
            {
                captureORMove(start, end);
            }
            //ELSE IF piece move down 1 
            else if(end.getX() == start.getX() && end.getY() == start.getY() - 1)
            {
                captureORMove(start, end);
            }
            //ELSE move not legal
            else
            {
                //Throw InvalidMoveException
                throw new InvalidMovementException();
            }//END IF
        }//END IF 
    }//END move
    @Override
    public String toString()
    {
        return "King";
    }
    // @Override
    // public Array getAllPossibleMoves() {
    //     return null;
    // }
}