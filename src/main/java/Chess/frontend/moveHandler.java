package main.java.Chess.frontend;
import java.util.ArrayList;

import main.Piece.Piece;
import main.Piece.ChessPieces.King;

// /**********************************************************
// * Class Name : moveHandler
// * Author : Jordan
// * Date : 3/19/23
// * Course/Section : Software Engineering 221-301
// * Program Description: This class handles all potential moves that could be made
// * in doing that, it also checks for check and checkmate
// * 
// * Methods:
// * -------
// * 
// * 
// * 
// * 
// * 
// **********************************************************/

// public class moveHandler 
// {
//     Piece[][] allPieces = new Piece[2][16]; // A two dimensional array that holds p1 and p2's pieces
//     Screen board;
//     ArrayList<Cell> possibleMoves = new ArrayList<Cell>(); // An arraylist that holds all possible moves for a current
//                                                            // turn
//     ArrayList<Cell> enemyPossibleMoves = new ArrayList<Cell>(); // An arraylist that holds all possible moves for the
//                                                                 // enemy's turn

//     /*
//      * Method Name : moveHandler
//      * Author : Jordan
//      * Date : 5/4/2023
//      * Course/Section : Software Engineering 221-301
//      * Program Description: Constructor for the moveHandler class
//      */
//     public moveHandler(Screen board, ArrayList<Piece> p1Pieces, ArrayList<Piece> p2Pieces)
//     {
//         this.board = board;
//         // Adds all of p1's pieces to the allPieces array
//         for (int i = 0; i < p1Pieces.size(); i++) {
//             allPieces[0][i] = p1Pieces.get(i);
//         }
//         // Adds all of p2's pieces to the allPieces array
//         for (int i = 0; i < p2Pieces.size(); i++) {
//             allPieces[1][i] = p2Pieces.get(i);
//         }
//     }

//     /*
//      * Method Name : gatherPossibleMoves
//      * Author : Jordan
//      * Date : 5/4/2023
//      * Course/Section : Software Engineering 221-301
//      * Program Description: Gathers all possible moves for the given turn
//      */

//     public Cell[][] gatherPossibleMoves(int currentTurn) {
//         // Clears the possibleMoves arraylist
//         possibleMoves.clear();
//         // Loops through all of the current player's pieces
//         for (int i = 0; i <= allPieces[currentTurn].length - 1 ; i++) {
//             System.out.println("i: " + i);
//             // Checks to see if the current piece is not null
//             if (allPieces[currentTurn][i] != null) {
//                 // Loops through all of the current piece's possible moves
//                 Cell[][] temp = allPieces[currentTurn][i].getAllPossibleMoves(board);
//                 for (int j = 0; j < temp.length; j++) {
//                     for (int k = 0; k <= temp[j].length; k++) {
//                         // Checks to see if the current move is not null
//                         if (temp[j][k] != null) {
//                             // Adds the current move to the possibleMoves arraylist
//                             possibleMoves.add(temp[j][k]);
//                         }
//                     }
//                 }
//             }
//         }
//         return possibleMoves.toArray(new Cell[possibleMoves.size()][possibleMoves.size()]);
//     }


//     public boolean checkIfKingMoveValid(Cell[][] possibleMoves, Cell endCell) 
//     {
//         // Loops through all of the possible moves
//         for (int i = 0; i < possibleMoves.length; i++) {
//             for (int j = 0; j < possibleMoves[i].length; j++) {
//                 // Checks to see if the current move is not null
//                 if (possibleMoves[i][j] != null) {
//                     // Checks to see if the current move is the same as the endCell
//                     if (possibleMoves[i][j] == endCell) {
//                         // Returns true if the move is valid
//                         return false;
//                     }
//                 }
//             }
//         }
//         // Returns false if the move is valid
//         return true;
//     }


//     public boolean checkIfCheckMate(Cell[][] possibleMoves, King king) 
//     {
        
//         // Loops through all of the possible moves
//         Cell[][] kingMoves = king.getAllPossibleMoves(board);
//         for(int i = 0; i < kingMoves.length; i++)
//         {
//             for(int j = 0; j < kingMoves[i].length; j++)
//             {
//                 // Checks to see if the current move is not null
//                 if(kingMoves[i][j] != null)
//                 {
//                     // Checks to see if the current move is not null
//                     if(searchMove(possibleMoves, kingMoves[i][j]))
//                     {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }

//     static boolean searchMove(Cell[][] arr, Cell target)
//     {
//         for (int i = 0; i < arr.length; i++) {
//             for (int j = 0; j < arr[i].length; j++) {
//                 if (arr[i][j] == target) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

    

//     /*
//      * Method Name : checkForCheck
//      * Author : Jordan
//      * Date : 5/4/2023
//      * Course/Section : Software Engineering 221-301
//      * Program Description: Checks to see if the current player is in check
//      */
//     public boolean checkForCheck(int currentTurn) {
//         // Loops through all of the enemy's pieces
//         int enemyTurn = currentTurn == 1 ? 1 : 0;
//         for (int i = 0; i < allPieces[enemyTurn].length; i++)
//         {
//             // Checks to see if the current piece is not null
//             if (allPieces[enemyTurn][i] != null)
//             {
//                 // Loops through all of the current piece's possible moves
//                 Cell[][] temp = allPieces[enemyTurn][i].getAllPossibleMoves(board);
//                 for (int j = 0; j < temp.length; j++)
//                 {
//                     for (int k = 0; k < temp[j].length; k++)
//                     {
//                         // Checks to see if the current move is not null
//                         if (temp[j][k] != null && temp[j][k].getPiece() != null)
//                         {
//                             // Checks to see if the current move is the same as the current player's king
//                             if (temp[j][k].getPiece().getClass() == King.class && temp[j][k].getPiece().getOwnedBy() == currentTurn)
//                             {
//                                 // Returns true if the current player is in check
//                                 System.out.println("The King is in check");
//                                 allPieces[enemyTurn][i].setCheckingKing(true);
//                                 return true;
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         // Returns false if the current player is not in check
//         System.out.println("The King is not in check");
//         return false;
//     }

// }
