package main.java.Chess.frontend;
import main.Piece.Piece;
/**********************************************************
 * Program Name   : Cell
 * Author         : Jordan
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: 
 * This programs represent a square on a game board.
 *
 * Methods:
 * -------
 * Cell(int x, int y) - Gets the x,y position and sets piece to null.
 * Cell(int x, int y,Piece piece) - Gets the x,y postiion and sets piece to passed piece.
 * setPiece - Sets what piece the passed cell should have. 
 * getPiece - gets the type of piece the cell. 
 * setHighlighted - Sets wether the cell is highlighted.
 * isHighlighted - gets wether the cell is highlighted or not.
 * getx - returns x positon of cell.
 * gety - returns y position of cell.
 * isOccupied - gets whether the cell is occuppied by another piece or not.
 * isOccupied(Piece piece) - checks if cell has a Piece.
 *********************************************************/
public class Cell 
{
    //Class constants
    //Class variables
    private int cell_x;                //x position of cell
    private int cell_y;                //y position of cell
    private Piece cell_piece;          //game piece on cell
    private boolean cell_isHighlighted;//cell is highlighed or not 
    /********************************************************/
    
    /**
     * Constructor for Cell.
     * Sets the coordinate of piece passed and sets the piece to null.
     * @param x - x position of cell
     * @param y - y position of cell
     */
    public Cell(int x, int y)
    {
        cell_x = x;
        cell_y = y;
        cell_piece = null;
        cell_isHighlighted = false;
    }//end Cell

     /**
     * Constructor for Cell.
     * Sets the coordinate of piece passed and sets the piece to piece passed.
     * @param x - x postion of cell
     * @param y - y position of cell
     * @param piece - type of piece 
     */
    public Cell(int x, int y, Piece piece) 
    {
        cell_x = x;
        cell_y = y;
        cell_piece = piece;
        cell_isHighlighted = false;
    }//end cell

    /** 
    * This method sets the piece on cell to the given piece and updates the piece's position.
    * @param piece The piece to set on this cell.
    */
    public void setPiece(Piece piece) 
    {
        cell_piece = piece;

        //IF piece not null
        if (piece != null)
        { 
            //set piece location to class variables cell_x cell_y
            piece.setPos(cell_x,cell_y);
            System.out.println("Piece set to " + cell_x + " " + cell_y);
        }//END IF

    }//end setPiece

    /**
    * This method returns the piece currently occupying cell.
    * @return The piece currently occupying this cell.
    */
    public Piece getPiece()
    {
        return cell_piece;
    }//end getPiece

    /**
    * This method sets whether or not this cell is highlighted.
    * @param isHighlighted checks if this cell is highlighted.
    */
    public void setHighlighted(boolean isHighlighted)
    {
        cell_isHighlighted = isHighlighted;
    }//end setHighlighted

   /**
    * This method returns whether or not this cell is currently highlighted.
    * @return Whether or not this cell is currently highlighted.
    */
    public boolean isHighlighted()
    {
        return cell_isHighlighted;
    }//end isHighlighted

    /**
    * This method returns the x-coordinate of the cell
    * @return x cordinate of the cell
    */
    public int getX()
    {
        return cell_x;
    }//end getX

    /**
    * This method returns the y-coordinate of the cell
    * @return y coordinate of the cell
    */
    public int getY() 
    {
        return cell_y;
    }//end getY

   /**
    * This method returns whether or not this cell is occupied by a game piece.
    * @return Whether or not this cell is occupied.
    */
    public boolean isOccupied()
    {
        return cell_piece != null;
    }//end isOccupied

    /**
     * Checks if a given Piece object is occupying this cell.
     * @param piece check if this piece is in thiscell.
     * @return true if the given piece is occupying this cell, false otherwise.
     */
    public boolean isOccupiedBy(Piece piece) 
    {
        return cell_piece == piece;
    }//end isOccupiedBy
    
}//end cell
