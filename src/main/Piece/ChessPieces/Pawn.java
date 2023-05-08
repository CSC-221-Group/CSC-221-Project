package main.Piece.ChessPieces;
import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.Cell;
import main.Piece.Piece;
import main.Piece.InvalidMovementException;
/**********************************************************
 * Program Name : Pawn
 * Author : Jordan/Alan
 * Date : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program sets what a Pawn piece
 * is and the legal moves of a Pawn piece.
 * 
 * Methods:
 * -------
 * Pawn - sets color position and owner of Pawn piece.
 * move - sets legal moves for Pawn piece.
 * enPassant - his method set pieces to right position for enPassant
 **********************************************************/
public class Pawn extends Piece 
{
    //Class constants
    //Class variables
    public String color;                     //Color of Piece 
    public boolean canDoubleMovement = true; //wehther piece can move two squares up or not
    public static int enPassantW = 0;        //white pawn hasn't enpessant yet
    public static int enPassantB = 0;        //black pawn hasn't enpessant yet
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
    *BEGIN - move
    *  IF(piece is capturing)
    *    IF(white owns piece)
    *       IF(piece is capturing right)
    *           capture
    *           set double move to false
    *       ELSE IF(piece is capturing it left)
    *           move or capture
    *           double move to false
    *       ELSE
    *           Move not legal
    *           Throw Invalid Move
    *     END IF
    *     ELSE black is capturing
    *        IF(piece is capturing Right)
    *           capture
    *           set double move to false
    *        ELSE IF(pice is captruing right)
    *           capture
    *           set double move to false
    *        ELSE
    *            Move is not legal
    *           Throw invalid move
    *        END IF
    *     ELSE (Piece is moving)
    *        IF(White Pawn is at start)
    *           IF(piece is moving 2 squares)
    *              set double move to false
    *           ELSE IF( Pawn is moving 1 square)
    *              move pawn 1 square
    *              set double move to false
    *           ELSE
    *              Move is not legal
    *              Throw invalid move
    *           END IF
    *         ELSE IF(Black pawn is at start)
    *            IF(pawn moves 2 squares)
    *               Move 2 squares
    *              set double move to false
    *           ELSE IF(pawn is moving 1 square)
    *               move pawn 1 square
    *               set double move to false
    *            ELSE
    *               Move not legal
    *               Throw invalid move
    *             END IF
    *       END IF
    *     END IF
    *END - move
    *
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
                    throw new InvalidMovementException();
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
                    throw new InvalidMovementException();
                }//END IF 
            }//END IF
        }
        //ELSE piece is moving but not capturing
        else 
        {
            //IF white pawn at starting square and is able to move two square up
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
                    throw new InvalidMovementException();
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
                    throw new InvalidMovementException();
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
                    throw new InvalidMovementException();
                }//END IF
            }//END IF 
        }//END IF
    }//END move

    /**********************************************************
	* Method Name    : enPassant
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method set pieces to right position 
    * for enPassant
    *
    *BEGIN - enPassant
    *  IF(Piece owned by white)
    *   IF(Piece behind pawn is a pawn and owned by black)
    *        delete piece behind pawn
    *        set white enpassant to true
    *        return true 
    *   END IF 
    *   ELSE(piece is owned by black)
    *     IF(piece behind pawn is a pawn and owned by white)
    *       delete piece behind pawn
    *       set white enpassant to true
    *       return true
    *     END IF 
    *   END IF
    * END - enPassant
	**********************************************************/
    // public static boolean enPassant(Cell cells[][],Piece piece, int x, int y) 
    // {
    //     //IF Piece owned by white 
    //     if (piece.getOwnedBy() == 1) 
    //     {
    //         //IF Piece behind pawn is a pawn and owned by black
    //         if (cells[x][y - 1].getPiece().getClass() == Pawn.class && cells[x][y - 1].getPiece().getOwnedBy() != 1 && enPassantW == 0) 
    //         {
    //             //delete piece behind pawn
    //             cells[x][y - 1].setPiece(null);
    //             //set white enpassant to true
    //             enPassantW = 1;
    //             //return true 
    //             return true;
    //         }//END IF 
    //     }
    //     //ELSE piece is owned by black
    //     else 
    //     {
    //         if (cells[x][y + 1].getPiece().getClass() == Pawn.class && cells[x][y + 1].getPiece().getOwnedBy() != 2 && enPassantB == 0) 
    //         {
    //             //delter piece behind pawn
    //             cells[x][y + 1].setPiece(null);
    //             //set black enpassant to true 
    //             enPassantB = 1;
    //             //return true
    //             return true;
    //         }//END IF 
    //     }//END IF 
    //     return false;
    // }//END enPassant

    /**********************************************************
	* Method Name    : toString
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Return the Piece type
	**********************************************************/ 
    @Override
    public String toString() 
    {
        return "Pawn";
    }//END toString

    // @Override
    // public Cell[][] getAllPossibleMoves(Screen board)
    // {
    //     Cell[][] possibleMoves = new Cell[2][1];
    //     if(getOwnedBy() == 1 ) 
    //         {
    //             if(board.getCell(getPos().x + 1, getPos().y + 1) != null)
    //         {
    //             possibleMoves[0][0] = board.getCell(getPos().x + 1, getPos().y + 1);
    //         }
    //         if(board.getCell(getPos().x -1 , getPos().y + 1) != null)
    //         {
    //             possibleMoves[1][0] = board.getCell(getPos().x - 1, getPos().y + 1);
    //         }
    //     } else {
    //         if(board.getCell(getPos().x + 1, getPos().y - 1) != null)
    //         {
    //             possibleMoves[0][0] = board.getCell(getPos().x + 1, getPos().y - 1);
    //         }
    //         if(board.getCell(getPos().x -1 , getPos().y - 1) != null)
    //         {
    //             possibleMoves[1][0] = board.getCell(getPos().x - 1, getPos().y - 1);
    //         }
    //     }
    //     return possibleMoves;
}//END Pawn 

