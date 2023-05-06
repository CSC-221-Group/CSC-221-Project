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
public class Pawn extends Piece 
{
    //Class constants
    //Class variables
    public String color;
    public boolean canDoubleMovement = true;
    public static int enPassantW = 0;
    public static int enPassantB = 0;
    /************************************/

   /**********************************************************
	* Method Name    : Pawn
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description:Constructor for Pawn.
	**********************************************************/
    public Pawn(String color, int x, int y, int owner, Screen board) 
    {
        // Calls Piece contructor
        super(x, y, owner, board);

        // Sets image of pawn depending on color passed.
        String Chess = "Chess";
        loadImage(Chess, color + "Pawn");
        this.color = color;
    }//END Pawn

    /**********************************************************
	* Method Name    : move 
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method determines what move the Pawn can do.
	**********************************************************/
    @Override
    public void move(Cell cell[][],Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        //Local constants
        //Local variables 
        /*****************************************************/
        
        //IF piece is capturing
        if (end.getPiece() != null) 
        {
            //IF white own the Piece
            if(getOwnedBy() == 1 ) 
            {
                //IF piece is moving up and taking the piece to the right
                if(end.getX() == start.getX() + 1 && end.getY() == start.getY() + 1) 
                {
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } 
                //ELSE IF piece is moving up and taking the piece to the left
                else if(end.getX() == start.getX() - 1 && end.getY() == start.getY() + 1) 
                {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                }
                //ELSE move not legal 
                else 
                {
                    throw new InvalidMovementException("Invalid move");
                }//END IF 
            } 
            //ELSE piece is owned by white
            else 
            {
                //IF piece is moving down and taking the piece to the right
                if(end.getX() == start.getX() + 1 && end.getY() == start.getY() - 1) 
                {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } 
                //ELSE IF piece is moving down and taking the piece to the left
                else if(end.getX() == start.getX() - 1 && end.getY() == start.getY() - 1) 
                {
                    // we can move the piece
                    capture(end.getPiece(), start, end);
                    canDoubleMovement = false;
                } 
                //ELSE move not legal
                else 
                {
                    throw new InvalidMovementException("Invalid move");
                }//END IF 
            }//END IF
        }
        //ELSE piece is moving but not capturing
        else 
        {
            //IF white pawn at starting square and is able to move to square up
            if(start.getY() == 1 && canDoubleMovement) 
            {
                //IF x is the same and piece moves 2 squares up
                if(end.getX() == start.getX() && end.getY() == start.getY() + 2) 
                {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                }
                //ELSE IF x is the same and piece is moving 1 square up
                else if(end.getX() == start.getX() && end.getY() == start.getY() + 1) 
                {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                }
                //ELSE Move not legal
                else 
                {
                    throw new InvalidMovementException("Invalid move");
                }//END IF 
            } 
            //ELSE IF pawn is owned by black and black pawns are in starting squares  
            else if(start.getY() == 6 && canDoubleMovement) 
            {
                //IF piece is same x axis and move two square up   
                if(end.getX() == start.getX() && end.getY() == start.getY() - 2) 
                {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } 
                //ELSE IF //IF piece is same x axis and move one square up   
                else if(end.getX() == start.getX() && end.getY() == start.getY() - 1) 
                {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } 
                //ELSE move not legal
                else 
                {
                    throw new InvalidMovementException("Invalid move");
                }//END IF 
            } 
            //ELSE if piece not moving to squares up
            else 
            {
                //IF piece owned by white and piece in same x axis and piece moves one square up
                if (start.getPiece().getOwnedBy() == 1 && end.getX() == start.getX() && end.getY() == start.getY() + 1) 
                {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } 
                ////ELSE IF piece owned by black and piece in same x axis and piece moves one square up
                else if(start.getPiece().getOwnedBy() == 2 &&end.getX() == start.getX() && end.getY() == start.getY() - 1) 
                {
                    // we can move the piece
                    end.setPiece(start.getPiece());
                    start.setPiece(null);
                    canDoubleMovement = false;
                } 
                else 
                {
                    throw new InvalidMovementException("Invalid move");
                }//END IF
            }//EnD IF 
        }//END IF
    }//END move

    /**********************************************************
	* Method Name    : enPassant
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method set pieces to right position 
    * for enPassant
	**********************************************************/
    public static void enPassant(Piece piece, int x, int y) 
    {
        //IF Piece owned by white 
        if (piece.getOwnedBy() == 1) 
        {
            if (Screen.cells[x][y - 1].getPiece() != null && Screen.cells[x][y - 1].getPiece().getClass() == Pawn.class
                    && Screen.cells[x][y - 1].getPiece().getOwnedBy() != 1 && enPassantW == 0) 
            {
                Screen.cells[x][y - 1].setPiece(null);

                enPassantW = 1;
            }
        } 
        else 
        {
            if (Screen.cells[x][y + 1].getPiece() != null && Screen.cells[x][y + 1].getPiece().getClass() == Pawn.class
                    && Screen.cells[x][y + 1].getPiece().getOwnedBy() != 2 && enPassantB == 0) 
            {
                Screen.cells[x][y + 1].setPiece(null);

                enPassantB = 1;
            }

        }
    }


    @Override
    public String toString() 
    {
        return "Pawn";
    }

    @Override
    public Cell[][] getAllPossibleMoves(Screen board)
    {
        Cell[][] possibleMoves = new Cell[2][1];
        if(getOwnedBy() == 1 ) 
            {
                if(board.getCell(getPos().x + 1, getPos().y + 1) != null)
            {
                possibleMoves[0][0] = board.getCell(getPos().x + 1, getPos().y + 1);
            }
            if(board.getCell(getPos().x -1 , getPos().y + 1) != null)
            {
                possibleMoves[1][0] = board.getCell(getPos().x - 1, getPos().y + 1);
            }
        } else {
            if(board.getCell(getPos().x + 1, getPos().y - 1) != null)
            {
                possibleMoves[0][0] = board.getCell(getPos().x + 1, getPos().y - 1);
            }
            if(board.getCell(getPos().x -1 , getPos().y - 1) != null)
            {
                possibleMoves[1][0] = board.getCell(getPos().x - 1, getPos().y - 1);
            }
        }
        return possibleMoves;
    } 
}
