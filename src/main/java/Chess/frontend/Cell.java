package Chess.frontend;
import Chess.pieces.Piece;
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
 * Cell(int x, int y) - Gets the x,y and sets piece to null.
 * Cell(int x, int y,Piece piece) - Gets the x,y and sets piece to passed piece.
 * setPiece - Sets what piece the passed piece should be. 
 * getPiece - gets the type of piece the passes piece is. 
 * setHighlighted - Sets wether the cell is highlighted.
 * isHighlighted - gets wether the cell is highlighted or not.
 * getx - returns x positon of cell.
 * gety - returns y position of cell.
 * isOccupied - gets whether the cell is occuppied by another piece or not.
 * isOccupied(Piece piece) - check that the correct piece is in the correct cell.
 *********************************************************/
public class Cell 
{
    //Class constants
    //Class variables
    private int c_x;              //x position of cell
    private int c_y;              //y position of cell
    private Piece piece;          //game piece on cell
    private boolean isHighlighted;//cell is highlighed or not 
    /********************************************************/
    
    /**
     * Constructor for Cell.
     * Sets the coordinate of piece passed and sets the piece to null.
     * @param x - x position of cell
     * @param y - y position of cell
     */
    public Cell(int x, int y)
    {
        c_x = x;
        c_y = y;
        piece = null;
        isHighlighted = false;
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
        c_x = x;
        c_y = y;
        this.piece = piece;
        isHighlighted = false;
    }//end cell

    /** 
    * This method sets the piece on this cell to the given piece and updates the piece's position.
    * @param piece The piece to set on this cell.
    */
    public void setPiece(Piece piece) 
    {
        this.piece = piece;
        //if piece not emptuy
        if (piece != null)
        { 
            piece.setPos(c_x,c_y);
        }//end if 
    }//end setPiece

    /**
    * This method returns the piece currently occupying this cell.
    * @return The piece currently occupying this cell.
    */
    public Piece getPiece()
    {
        return piece;
    }//end getPiece

    /**
    * This method sets whether or not this cell is highlighted.
    * @param isHighlighted checks if this cell is highlighted.
    */
    public void setHighlighted(boolean isHighlighted)
    {
        this.isHighlighted = isHighlighted;
    }//end setHighlighted

   /**
    * This method returns whether or not this cell is currently highlighted.
    * @return Whether or not this cell is currently highlighted.
    */
    public boolean isHighlighted()
    {
    // Return whether or not this cell is currently highlighted.
    return isHighlighted;
    }//end isHighlighted

    /**
    * This method returns the x-coordinate of the cell
    * @return x cordinate of the cell
    */
    public int getX()
    {
        return c_x;
    }//end getX

    /**
    * This method returns the y-coordinate of the cell
    * @return y coordinate of the cell
    */
    public int getY() 
    {
        return c_y;
    }//end getY

   /**
    * this method returns whether or not this cell is occupied by a game piece.
    * @return Whether or not this cell is occupied.
    */
    public boolean isOccupied()
    {
        return piece != null;
    }//end isOccupied

    /**
     * Checks if a given Piece object is occupying this cell.
     * @param piece The piece to check for occupancy.
     * @return true if the given piece is occupying this cell, false otherwise.
     */
    public boolean isOccupiedBy(Piece piece) 
    {
        return this.piece == piece;
    }//end isOccupiedBy
    
}//end cell
