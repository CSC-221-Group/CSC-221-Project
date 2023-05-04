package main.Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;
import main.java.Chess.frontend.Cell;
import main.java.Chess.frontend.Screen;
import java.awt.Point;
import java.awt.event.MouseEvent;
/**********************************************************
 * Program Name   : Piece
 * Author         : Jordan 
 * Date           : 3/19/23
 * Course/Section : Software Engineering 221-301
 * Program Description: This program does everything relating to 
 *  the pieces on a board game.
 *
 * Methods:
 * -------
 * Piece(int x, int y) - sets piece obejct at x,y.
 * Piece(int x, int y, int ownedBy) - sets piece object at x,y and sets which player owns the piece.
 * loadImage - Sets the png of the piece passed.
 * update - updates the location of the pieces. 
 * draw - draws the graphic of the piece.
 * setPos - sets location of piece passed.
 * isClicked - checks if piece was cliked, and displays if it was or not.
 * getOwnedBy - gets who the piece is owned by
 * setOwnedBy - sets who owns the piece passed.
 * isCaptured - Checks if the pieces has been capture or not.
 **********************************************************/
public abstract class Piece
 {
    //Class constants
    //Class variables 
    private BufferedImage icon;      //png of Piece
    private Point pos;               // Postiong of Piece 
    private int ownedBy;             //1 for white or 2 for black
    private boolean captured = false;//if the piece was captured or no.
    private int gameSize = 1;
    /***********************************************************************/
    /**
    * Constructor for the Piece class.
    * Makes a new Piece object at the position (x, y) on the board.
    *
    * @param x The x position of the Piece.
    * @param y The y position of the Piece.
    */
    public Piece(int x, int y, Screen board) 
    {
        //set position of piece to the given (x,y) cords.
        gameSize = Screen.getGameSize();
        pos = new Point(x, y);
    }//END Piece

    /**
    * Constructor for the Piece class.
    * Makes a new Piece object at the position (x, y) on the board and if white or black own the piece.
    * @param x The x postion of the Piece.
    * @param y The y position of the Piece.
    * @param ownedBy An int representing which color owns the piece. 1 = white 2 = black
    *        
    */
    public Piece(int x, int y, int ownedBy, Screen board) 
    {
        pos = new Point(x, y); 
        // Set ownedBy to white or black
        gameSize = Screen.getGameSize();
        this.ownedBy = ownedBy;
    }//END Piece
    
    /**
     * Loads an image file for a piece passed through.
     * @param game String representing the game to get the png from.
     * @param path String representing the Pieces in the specified game.
     *       
     */

    //  BufferedImage otherImage = // .. created somehow
    //  BufferedImage newImage = new BufferedImage(SMALL_SIZE, SMALL_SIZE, BufferedImage.TYPE_INT_RGB);
     
    //  Graphics g = newImage.createGraphics();
    //  g.drawImage(otherImage, 0, 0, SMALL_SIZE, SMALL_SIZE, null);
    //  g.dispose();



    protected void loadImage(String game, String path)
    {
        try 
        {
            //loads image depending on game and pieces name
            icon = ImageIO.read(new File("images/" + game + "/" + path + ".png"));
            if(gameSize >= 2)
            {
                BufferedImage newImage = new BufferedImage(icon.getWidth() * gameSize, icon.getHeight() * gameSize, BufferedImage.TYPE_INT_ARGB);
                Graphics g = newImage.createGraphics();
                g.drawImage(icon, 0, 0, icon.getWidth() * gameSize, icon.getHeight() * gameSize, null);
                g.dispose();
                icon = newImage;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading player image " + e.getMessage());
        }
    }//End loadImage

    /**
    * Updates the position of the game pieces, 
    * also checks if the piece still on the board.
    * If the pieces is outside the board, the piece doesnt move at all.
    */
    public void update() 
    {

        //If the Pieces x position is less than 0 
        if(pos.x < 0) 
        {
            //set the x position of the piece to 0 
            pos.x = 0;
        } 
        else if(pos.x >= Screen.COLS) 
        {
            //if the x position is greater than or equal boards borders
            //set the x position of the piece to the edge of the board.
            pos.x = Screen.COLS - 1;
        }//end IF 
        
        // Check if the Pieces y position is less than 0 
        if(pos.y < 0) 
        {
            //set the y-position to 0 
            pos.y = 0;
        }
        else if(pos.y >= Screen.ROWS) 
        {
            //if the y position is greater than or equal to the baords borders
            // set the y position og the piece to the edge of the board.
            pos.y = Screen.ROWS - 1;
        }//END IF  
    }//end Update
    
    /**
     * Draws the games pieces on the screen.
     * @param g used to draw the pieces image on the screen.
     * @param obs used to track the loading of the pieces image.
     */
    public void draw(Graphics g, ImageObserver obs) 
    {
        g.drawImage(icon, pos.x * (Screen.TILE_SIZE * gameSize), (7 * (Screen.TILE_SIZE * gameSize)) - pos.y * (Screen.TILE_SIZE * gameSize), obs);
    }//END Draw

    /**
     * Returns the current position of the game piece (x,y) cordinates
     * @return returns pieces current x- and y-cordinates.
     */
    public Point getPos() 
    {
        //returns pieces current x- and y-cordinates.
        return pos;
    }//end getPos

//TODO add a method to see if the move is valid
//TODO add a method to see if the move results in a capture

    /**
    * Sets the position of the games pieces to passed (x,y) coordinates.
    * @param x The new x-coordinate of passed piece.
    * @param y The new y- coordinate of passed piece.
    */
    public void setPos(int x, int y) 
    {
        pos.x = x;
        pos.y = y;
    }//end SetPos

    /**
     * Checks if the game piece has been clicked.
     * @param e A MouseEvent object representing the user's click event.
     * @return true if the entity has been clicked, false otherwise.
     */
    public boolean isClicked(MouseEvent e) 
    {
        double x = e.getX() / Screen.TILE_SIZE;
        double y = e.getY() / Screen.TILE_SIZE;
        //IF x and y have a piece there 
        if(x == pos.x && y == pos.y) 
        {

            System.out.println("Piece clicked");
            return true;
        }//end IF 

        //print piece not clicked if piece is not cliked 
        System.out.println("Piece not clicked");
        return false;
    }//end IsClicked

    /**
    * return who owns the piece.
    * @return int of the player that owns the piece.
    */
    public int getOwnedBy() 
    {
        return ownedBy;
    }//End getOwnedBy

    /**
    * Sets if black or white own the piece.
    * @param ownedBy sets who owns the piece passed.
    */
    public void setOwnedBy(int ownedBy) 
    {
        this.ownedBy = ownedBy;
    }//end setOwnedBy

    /**
    * return true if game piece has been captured false otherwise.
    * @return Whether or not the game piece has been captured.
    */
    public boolean isCaptured() 
    {
        return captured;
    }//end is captured

    public void setCaptured(boolean captured) 
    {
        this.captured = captured;
    }//end setCaptured
    
    public boolean capture(Piece endPiece, Cell start, Cell end)
    {
        boolean capture = false;
        if(endPiece != null) 
{
            if(endPiece.getOwnedBy() != getOwnedBy()) 
{
                capture = true;
                endPiece.setCaptured(true);
                end.setPiece(null);
                start.setPiece(null);
                end.setPiece(this);
                setPos(end.getX(), end.getY());
            }

        }
       
       return capture;
   }

//     // public boolean checkPotentialPath(Screen board, Cell start, Cell end, int x, int y) 
// {
//     //     System.out.println("Checking potential path");
//     //     boolean pathClear = true;
//     //     int xDir = 0;
//     //     int yDir = 0;
//     //     if(x > 0) 
// {
//     //         xDir = 1;
//     //     } else if(x < 0) 
// {
//     //         xDir = -1;
//     //     }
//     //     if(y > 0) 
// {
//     //         yDir = 1;
//     //     } else if(y < 0) 
// {
//     //         yDir = -1;
//     //     }
//     //     int xStart = start.getX() + xDir;
//     //     int yStart = start.getY() + yDir;
//     //     int xEnd = end.getX() - start.getX(); // the distance between the start and end
//     //     int yEnd = end.getY() - start.getY(); // the distance between the start and end
//     //     System.out.println("xStart: " + xStart + " yStart: " + yStart + " xEnd: " + xEnd + " yEnd: " + yEnd);
//     //     System.out.println("yStart + i * yDir: " + (yStart + (1 * yDir)));
//     //     if(xEnd == 0) 
// {
//     //         if(yDir == 1 ) 
// {
//     //             for(int i = 0; i < Math.abs(yEnd); i++) 
// {
//     //                 if(board.getCell(xStart, yStart + (i * yDir)).getPiece() != null&& board.getCell(end.getX(), end.getY()) != board.getCell(xStart, yStart + (i * yDir))) 
// {
//     //                     System.out.println("Piece in the way");
//     //                     pathClear = false;
//     //                 }
//     //             }
//     //         } else {
//     //             for(int i = 0; i < Math.abs(yEnd); i++) 
// {
//     //                 if(board.getCell(xStart, yStart + (i * yDir)).getPiece() != null && board.getCell(end.getX(), end.getY()) != board.getCell(xStart, yStart + (i * yDir))) 
// {
//     //                     System.out.println("Piece in the way");
//     //                     pathClear = false;
//     //                 }
//     //             }
//     //         }
//     //     } else if(yEnd == 0) 
// {
//     //         for(int i = 0; i < Math.abs(xEnd); i++) 
// {
//     //             if(board.getCell(xStart + (i * xDir), yStart).getPiece() != null && board.getCell(end.getX(), end.getY()) != board.getCell(xStart, yStart + (i * yDir))) 
// {
//     //                 pathClear = false;
//     //             }
//     //         }
//     //     } else if(Math.abs(xEnd) == Math.abs(yEnd)) 
// {
//     //         for(int i = 0; i < Math.abs(xEnd); i++) 
// {
//     //             if(board.getCell(xStart + (i * xDir), yStart + (i * yDir)).getPiece() != null && board.getCell(end.getX(), end.getY()) != board.getCell(xStart, yStart + (i * yDir))) 
// {
//     //                 pathClear = false;
//     //             }
//     //         }
//     //     } else {
//     //         pathClear = false;
//     //     }
        
//     //     return pathClear;
//     // }


/*
    Start Potential Move Methods
*/

    public abstract Cell[][] getAllPossibleMoves(Screen board);

    /*
        This Method gets all potential moves for a piece given an x/y
    */
    public Cell[] getPotentialMoves(int x, int y, Screen board) 
    {
        // x / y is the direction it is moving in, increment it until it hits a piece or the edge of the board
        //Local constants
        //Local variables
        Point pos = this.getPos();
        Cell possibleMoves[] = new Cell[8];
        /*****************************************************/
        for(int i = 0; i < 8; i++)
        {
            if(board.getCell(pos.x + x, pos.y + y).getPiece() == null)
            {
                possibleMoves[i] = board.getCell(pos.x + x, pos.y + y);
            }
            else if(board.getCell(pos.x + x, pos.y + y).getPiece().getOwnedBy() != this.getOwnedBy())
            {
                possibleMoves[i] = board.getCell(pos.x + x, pos.y + y);
                break;
            }
            else
            {
                break;
            }
            x+=x;
            y+=y;
        }
        return possibleMoves;
    }

   public  boolean laneCheckYAxis(Cell cells[][], Cell start, Cell end)
    {
        boolean laneCheck = true;
        for(int i = 0;i <= 7; i++)
        {
            if(end.getY() > start.getY())
            {  
                if(end.getY() - i > -1 && end.getY() - i != start.getY() && end.getY() - i > start.getY())
                {
                    if(cells[end.getX()][end.getY() - i].getPiece() != null )
                    {
                        laneCheck =  false;
                    }
                }
            }
            else if(end.getY() < start.getY())
            {
               if(end.getY() + i < 8 && end.getY() + i != start.getY()&& end.getY() + i < start.getY())
                {
                    if(cells[end.getX()][end.getY() + i].getPiece() != null)
                    {
                        laneCheck = false;
                    }
                }
            }    
        }
        System.out.println(laneCheck);
       return laneCheck;
    }
    public boolean laneCheckXAxis(Cell cells [][], Cell start, Cell end)
    {
        boolean laneCheck = true;

        for(int i = 0;i <= 7; i++)
        {
            if(end.getX() > start.getX())
            {
                if(end.getX() - i > -1 && end.getX() - i != start.getX() && end.getX() - i > start.getX())
                { 
                    if(cells[end.getX() - i][end.getY()].getPiece() != null)
                    {
                        laneCheck =  false;
                    }
                }
            }
            else if(end.getX() < start.getX())
            {
                if(end.getX() + i < 8 && end.getX() + i != start.getX() && end.getX() + i < start.getX())
                {
                    if(cells[end.getX() + i][end.getY()].getPiece() != null)
                    {
                        laneCheck = false;
                    }
                }
            }   
        }
        return laneCheck;
    }
    public boolean diagonalCheck(Cell cells [][], Cell start, Cell end)
    {
        boolean diagonalCheck = true;
        int endX = Math.abs(start.getX() - end.getX());
        for(int i = 1; i < endX; i++)
        {
            if(end.getX() < start.getX() && end.getY() > start.getY())
            {
                if(cells[start.getX() - i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            } 
            else if(end.getX() > start.getX() && end.getY() > start.getY())
            {
                if(cells[start.getX() + i][start.getY() + i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }
            else if(end.getX() > start.getX() && end.getY() < start.getY())
            {
                if(cells[start.getX() + i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }
            else if(end.getX() < start.getX() && end.getY() < start.getY())
            {
                if(cells[start.getX() - i][start.getY() - i].getPiece() != null)
                {
                    diagonalCheck = false;
                }
            }

        }
        return diagonalCheck;
    }
    public void captureORMove(Cell start, Cell end)
    {
        if(end.getPiece() != null)
        { 
            capture(end.getPiece(), start, end);
        }
        else
        {
            end.setPiece(start.getPiece());
            start.setPiece(null);
        }
    }
    public abstract void move(Cell cells[][],Screen board, Cell start, Cell end) throws InvalidMovementException;
}//End Piece