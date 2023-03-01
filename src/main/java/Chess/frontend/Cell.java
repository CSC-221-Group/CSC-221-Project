package main.java.Chess.frontend;

import main.Piece.Piece;

;

public class Cell 
{
    private int x;
    private int y;
    private Piece piece;
    private boolean isHighlighted;

    public Cell(int x, int y)
     {
        this.x = x;
        this.y = y;
        piece = null;
        isHighlighted = false;
    }

    public Cell(int x, int y, Piece piece) 
    {
        this.x = x;
        this.y = y;
        this.piece = piece;
        isHighlighted = false;
    }

    
    /** 
     * @param piece
     */
    public void setPiece(Piece piece) 
    {
        this.piece = piece;
        if (piece != null)
            piece.setPos(x, y);
    }

    public Piece getPiece() 
    {
        return piece;
    }

    public void setHighlighted(boolean isHighlighted) 
    {
        this.isHighlighted = isHighlighted;
    }

    public boolean isHighlighted()
    {
        return isHighlighted;
    }

    public int getX()
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public boolean isOccupied()
    {
        return piece != null;
    }

    public boolean isOccupiedBy(Piece piece) 
    {
        return this.piece == piece;
    }
    
}
