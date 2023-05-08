package main.Piece.ChessPieces;

import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
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
 * isInCheck - Checks if King is in check.
 * setInCheck- Sets King in check.
 * didKingMove - This method checks if King has moved from starting position.e 
 * kingMove - Checks if King is at original sqaure.
 * checkIfLaneClear - checks if lanes are clear for castling
 * castling - This method set pieces to right position for castling
 **********************************************************/
public class King extends Piece
{
    //Class constants
    //Class variables 
    public String color;              //Color of piece
    public boolean whiteKing = false; //to check if kings have moved
    public boolean blackKing = false; //to check if kings have moved
    private boolean inCheck = false;  //to check if king is in check
    /************************************/

    /**********************************************************
	* Method Name    : King
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Constructor for King.
	**********************************************************/
    public King (String color, int x, int y, int owner, Screen board)
    {
        super(x, y, owner, board);
        String Chess = "Chess"; 
        loadImage(Chess,color + "King");
        this.color = color;
    }//END King 

    /**********************************************************
     * Method Name    : isInCheck
     * Author         : Jordan
     * Date           : 5/7/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Checks if King is in check.
     *  
     * BEGIN isInCheck
     *    IF King is in check
     *      return true
     *   ELSE
     *     return false
     *  END IF
     * END isInCheck
     **********************************************************/

    public boolean isInCheck()
    {
        return inCheck;
    }//END isInCheck

    /**********************************************************
     * Method Name    : setInCheck
     * Author         : Jordan
     * Date           : 5/7/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Sets King in check.
     *  
     * BEGIN setInCheck
     *    IF King is in check
     *      set inCheck to true
     *   ELSE
     *     set inCheck to false
     *  END IF
     * END setInCheck
     **********************************************************/

    public void setInCheck(boolean inCheck)
    {
        this.inCheck = inCheck;
    }//END setInCheck

    /**********************************************************
	* Method Name    : didKingMove
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method checks if King has moved
    * from starting position.
    * BEGIN - didKingMove
    *  IF(Location of king is empty on white side)
    *     IF(piece at location is a king)
    *        return true
    *     END IF
    *  ELSE
    *     King is Black
    *     IF(piece at location is a king)
    *        return true
    *     END IF
    *  END IF
    *  return False
	**********************************************************/
    public boolean didKingMove() 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF Piece at (X:0,Y:4) is not empty and owned by white
        if(getOwnedBy() == 1 && Screen.cells[0][4].getPiece() != null)
        {
            //IF Piece at (X:0,Y:4) is equal to a king
            if(Screen.cells[0][4].getPiece().getClass() == King.class)
            {
                return true;
            }//END IF
        }
        //ELSE IF Piece at (X:7,Y:4) is not empty and owned by black
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

    /**********************************************************
	* Method Name    : kingMove
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Checks if King is at original sqaure.

    * IF(white King start has piece)
    *     IF( Piece at king start != King)
    *        set white king to false
    *     END IF
    *  END IF
    *  IF(black King start has piece)
    *     IF(piece at king start != king)
    *        set black king to false
    *     END IF
    *  END IF
    *END - kingMove

	**********************************************************/
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

    /**********************************************************
	* Method Name    : checkIfLaneClear
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method checks if lanes 
    * 
    *BEGIN - checkIfLaneClear
    *  IF(White Right Rook is at starting position)
    *    IF(Lane is Open)
    *       display "Lane is Open"
    *       Retrun True
    *    END IF
    *  ELSE IF( White Left Rook is at starting postition)
    *     IF(lane is open)
    *        display "Lane is Open"
    *        Return True
    *     END IF
    *  IF(Black Right Rook is at starting postion)
    *     IF(lane is open)
    *        display "Lane is Open"
    *        Return Ture
    *      END IF
    *  END IF
    *  IF(Black Left Rook is at starting postion)
    *     IF(lane is open)
    *        display "Lane is Open"
    *        Retrun True
    *     END IF
    *  END IF
    * Return False
    *END-checkIfLaneClear
	**********************************************************/
    public boolean checkIfLaneClear(Rook rook, Cell cells[][]) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF rookWRight is at starting position
        if(rook.rookWRight) 
        {
            //IF Piece at (X:5Y:0) is empty and Piece(X:6Y:0) is empty
            if(!cells[5][0].isOccupied()  && !cells[6][0].isOccupied()) 
            {
                return true;
            }
        }
        //ELSE IF rookWLeft is at starting position
        else if(rook.rookWLeft)
        {
            //IF Piece at (X:1Y:0) is empty and Piece(X:2Y:0) is empty and
            //   Piece at(X:3Y:0) is empty
            if(!cells[1][0].isOccupied() && !cells[2][0].isOccupied() && 
               !cells[3][0].isOccupied()) 
            {
                return true;
            }
        }
        //ELSE IF rookBRight is at starting position
        else if(rook.rookBRight) 
        {
            //IF Piece at (X:5Y:7) is empty and Piece(X:6Y:7) is empty
            if(!cells[5][7].isOccupied() && !cells[6][7].isOccupied())
            {
                return true;
            }
        }
        //ELSE IF rookBLeft is at starting position
        else if(rook.rookBLeft) 
        {
            //IF Piece at (X:1Y:7) is empty and Piece(X:2Y:7) is empty and
            //   Piece at(X:3Y:7) is empty
            if(!cells[1][7].isOccupied() && !cells[2][7].isOccupied() && 
               !cells[3][7].isOccupied()) 
            {
                return true;
            }
        }//END IF

        return false;
     }//END checkIfLaneClear

     /**********************************************************
	* Method Name    : getRook
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: checks if White and Blacks rooks are 
    * in starting locations
    *BEGIN - getRook
    * IF(player is White)
    *    IF(string is Left)
    *       retrun piece at (X y0 0)
    *     ELSE
    *        Return (x 7y 0)
    *     END IF
    *  ELSE
    *    IF(string is left)
    *      retrun (x 0y 7)
    *    END IF
    *    ELSE
    *       return (x 7y 7)
    *    END IF
    *  END IF
    *END - getRook
	**********************************************************/
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

    /**********************************************************
	* Method Name    : castling 
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method set pieces to right position 
    * for castling
    *
    *   BEGIN - castling
    *  IF(white left rook is true)
    *  ELSE(white right Rook)
    *  IF(black left rook is ture)
    *  ELSE(black right rook it ture)
    *  END IF
    *END - castling
	**********************************************************/
    public void castling(Rook rook,  Cell cells[][]) 
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        //IF rookWleft is true
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
        }//END IF
    }//END castling

    /**********************************************************
	* Method Name    : move
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This method determines what move the King can do.
    *BEGIN - move
    *  IF(piece moved up)
    *     IF(piece moved up and right)
    *        capture or move
    *     END IF
    *  ELSE IF(Piece moved left and up one)
    *     Capture or Move
    *  ELSE IF( piece move up 1)
    *     Capture or Move
    *  ELSE
    *     Move is not legal
    *     Throw Invalid move
    *   END IF
    * ELSE IF(Piece stayed in same collum)
    *    IF(Piece moved up 1 and stayed in same collum)
    *      capture or move
    *    ELSE
    *       Move is not legal
    *       Throw invalid Move
    *    END IF
    *  ELSE IF(piece moved down 1)
    *     IF(piece moved right and down 1)
    *        capture or move
    *     ELSE
    *        Move is not legal
    *        Throw Invalid Move
    *     END IF
    *  END IF
    *END - move
	**********************************************************/
    @Override
    public void move(Cell[][] cells, Screen board, Cell start, Cell end) throws InvalidMovementException
    {
        //Local constants
        //Local variables 
        /*****************************************************/

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
        else if(end.getY() < start.getY())
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
  
    /**********************************************************
	* Method Name    : toString
	* Author         : Alan/Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: Return the Piece type
	**********************************************************/
    public String toString()
    {
        return "King";
    }//END toString
    
    /**********************************************************
	// * Method Name    : getAllPossibleMoves
	// * Author         : Jordan
	// * Date           : 
	// * Course/Section : Software Engineering 221-301
	// * Program Description: 
	// **********************************************************/
    // //Local constants
    // //Local variables
    // Cell[][] possibleMoves = new Cell[8][];
    // /*****************************************************/
    // @Override
    // public Cell[][] getAllPossibleMoves(Screen board) 
    // {
    //     //Local constants
    //     //Local variables
    //     Cell[][] possibleMoves = new Cell[8][1];
    //     /*****************************************************/
    //     board.assignPossibleMove(possibleMoves, 0, 0, getPos().x, getPos().y + 1 );
    //     board.assignPossibleMove(possibleMoves, 1, 0, getPos().x + 1, getPos().y + 1 );
    //     board.assignPossibleMove(possibleMoves, 2, 0, getPos().x + 1, getPos().y );
    //     board.assignPossibleMove(possibleMoves, 3, 0, getPos().x + 1, getPos().y - 1 );
    //     board.assignPossibleMove(possibleMoves, 4, 0, getPos().x, getPos().y - 1 );
    //     board.assignPossibleMove(possibleMoves, 5, 0, getPos().x - 1, getPos().y - 1 );
    //     board.assignPossibleMove(possibleMoves, 6, 0, getPos().x - 1, getPos().y );
    //     board.assignPossibleMove(possibleMoves, 7, 0, getPos().x - 1, getPos().y + 1 );
    //     return possibleMoves;
    // }

}//END King