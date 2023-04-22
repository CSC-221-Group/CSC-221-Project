package main.Piece.ChessPieces;

import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.Cell;
import main.Piece.Piece;
import main.Piece.InvalidMovementException; 
/**********************************************************
 * Program Name   : Pawn
 * Author         : 
 * Date           : 3/19/23
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
    //class constants
    //class variables 
    public String color;
    /************************************/
    /**
     * Consructor of Pawn.
     * Sets owner, color, and (x,y) position of Pawn piece.
     * @param color - color of Pawn piece.
     * @param x - x positon of Pawn piece.
     * @param y - y position of Pawn piece 
     * @param owner - sets owner of Pawn piece
     */
        public Pawn(String color, int x, int y, int owner, Screen board) 
    {
        //Calls Piece contructor 
        super(x,y, owner, board);

        //Sets image of pawn depending on color passed.
        String Chess = "Chess"; 
        loadImage(Chess,color + "Pawn");
        this.color = color;
    }
    
    /** 
     * @param board
     * @param start
     * @param end
     * @throws InvalidMovementException
     */
    @Override
    public void move(Screen board, Cell start, Cell end) throws InvalidMovementException 
    {
        Piece [][] pawn = new Piece [start.getX() ][start.getY()];


        if(end.getX() == start.getX() && end.getY() == start.getY())
        {
            throw new InvalidMovementException("You cannot move to the same spot");
        } 
        //if(end.isOccupied(currentPiece, pawn.ge, ))
        
  // handle capturing, ensure that the piece is of the opposite color and it can capture like that
        //    return;
        
        if(pawn[end.getX()][end.getY()] != null)
        {
            
            return;
        }
        if(color.equals("white"))
        {
            if(start.getY() == 1 && end.getY() == 3 && start.getX() == end.getX())
            {
                return;
            }
        }
    }
    
    /*protected boolean moveCheck(Cell start, Cell end) 
    {
        int x = end.getX();
        int y = end.getY();
        int xStart = start.getX();
        int yStart = start.getY();
        if (color)
        {
            if (y == yStart + 1 && x == xStart) 
            {
                return true;
            } 
            else if (y == yStart + 2 && x == xStart && canDoubleMovement) 
            {
                return true;
            } 
            else if (y == yStart + 1 && (x == xStart + 1)) 
            {
                if (end.getPiece() != null) 
                {
                    return true;
                }
            }
        } 
        else 
        {
            if (y == yStart - 1 && x == xStart)
            {
                return true;
            } 
            else if (y == yStart - 2 && x == xStart && canDoubleMovement) 
            {
                return true;
            } else if (y == yStart - 1 && ( x == xStart - 1)) 
            {
                if (end.getPiece() != null) 
                {
                    return true;
                }
            }
        }
        return false;
    }*/
    @Override
    public String toString()
    {
        return "Pawn";
    }
    
}

