package main.java.Chess.frontend;

/**********************************************************
    * Class Name : moveHandler
    * Author : Jordan
    * Date : 3/19/23
    * Course/Section : Software Engineering 221-301
    * Program Description: This class handles all potential moves that could be made
    * in doing that, it also checks for check and checkmate
    * 
    * Methods:
    * -------
    * 
    * 
    * 
    * 
    * 
    **********************************************************/

import java.util.ArrayList;

import main.Piece.Piece;
import main.Piece.ChessPieces.King;

public class moveHandler {
    Piece[][] allPieces = new Piece[2][16]; // A two dimensional array that holds p1 and p2's pieces
    Screen board;
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>(); // An arraylist that holds all possible moves for a current
                                                           // turn
    ArrayList<Cell> enemyPossibleMoves = new ArrayList<Cell>(); // An arraylist that holds all possible moves for the
                                                                // enemy's turn

    /*
     * Method Name : moveHandler
     * Author : Jordan
     * Date : 5/4/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Constructor for the moveHandler class
     */
    public moveHandler(Screen board, ArrayList<Piece> p1Pieces, ArrayList<Piece> p2Pieces)
    {
        this.board = board;
        // Adds all of p1's pieces to the allPieces array
        for (int i = 0; i < p1Pieces.size(); i++) {
            allPieces[0][i] = p1Pieces.get(i);
        }
        // Adds all of p2's pieces to the allPieces array
        for (int i = 0; i < p2Pieces.size(); i++) {
            allPieces[1][i] = p2Pieces.get(i);
        }
    }

    /*
     * Method Name : gatherPossibleMoves
     * Author : Jordan
     * Date : 5/4/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Gathers all possible moves for the given turn
     */

    public void gatherPossibleMoves(int currentTurn) {
        // Clears the possibleMoves arraylist
        possibleMoves.clear();
        // Loops through all of the current player's pieces
        for (int i = 0; i < allPieces[currentTurn].length; i++) {
            // Checks to see if the current piece is not null
            if (allPieces[currentTurn][i] != null) {
                // Loops through all of the current piece's possible moves
                Cell[][] temp = allPieces[currentTurn][i].getAllPossibleMoves(board);
                for (int j = 0; j < temp.length; j++) {
                    for (int k = 0; k < temp[j].length; k++) {
                        // Checks to see if the current move is not null
                        if (temp[j][k] != null) {
                            // Adds the current move to the possibleMoves arraylist
                            possibleMoves.add(temp[j][k]);
                        }
                    }
                }
            }
        }
    }

    /*
     * Method Name : checkForCheck
     * Author : Jordan
     * Date : 5/4/2023
     * Course/Section : Software Engineering 221-301
     * Program Description: Checks to see if the current player is in check
     */
    public boolean checkForCheck(int currentTurn) {
        // Loops through all of the enemy's pieces
        int enemyTurn = currentTurn == 1 ? 1 : 0;
        for (int i = 0; i < allPieces[enemyTurn].length; i++)
        {
            // Checks to see if the current piece is not null
            if (allPieces[enemyTurn][i] != null)
            {
                // Loops through all of the current piece's possible moves
                Cell[][] temp = allPieces[enemyTurn][i].getAllPossibleMoves(board);
                for (int j = 0; j < temp.length; j++)
                {
                    for (int k = 0; k < temp[j].length; k++)
                    {
                        // Checks to see if the current move is not null
                        if (temp[j][k] != null && temp[j][k].getPiece() != null)
                        {
                            // Checks to see if the current move is the same as the current player's king
                            if (temp[j][k].getPiece().getClass() == King.class && temp[j][k].getPiece().getOwnedBy() == currentTurn)
                            {
                                // Returns true if the current player is in check
                                System.out.println("The King is in check");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        // Returns false if the current player is not in check
        System.out.println("The King is not in check");
        return false;
    }

}
