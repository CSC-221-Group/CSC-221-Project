import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.event.MouseEvent;
/**********************************************************
 * Program Name   : Piece
 * Author         : Jordan
 * Date           : 2/21/2023
 * Course/Section : Software Engineering 221 - 301
 * Program Description: 
 * 
 * Methods: 
 * -------
 * Constructor:
 * loadImage:
 * update:
 * draw:
 * getPos:
 * setPos;
 * isClicked:
 **********************************************************/
public class Piece 
{
    //class constants 

    //class variables 
    private BufferedImage icon; //holds the pieces png.
    private Point pos;          //holds x and y position of pieces.

    /**********************************************************
	* Method Name    : Piece(Constructor)
	* Author         : Jordan 
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: This method recieves x and y as parameters 
    *   and stores it in the class variable pos.    
	**********************************************************/
    public Piece(int x, int y) 
    {
        //local constants

        //local variables
        
        /*****************************************************/
        
        pos = new Point(x, y);
    }
    /**********************************************************
	* Method Name    : loadImage
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: loadImage has a String parameter 
    *   that holds a chess pieces name. Which them determines the correct
    *   PNG.
	**********************************************************/
    protected void loadImage(String path)
    {
        //local constants

        //local variables
        
        /*****************************************************/
        try 
        {
            //path is the name of the chess piece(Example 'pawn','king', etc)
            icon = ImageIO.read(new File("images/Chess/" + path + ".png"));
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading player image " + e.getMessage());
        }
    }
    /**********************************************************
	* Method Name    : update
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: update checks where x and y are and updates 
    *    there positions accordingly. 
	**********************************************************/
    public void update() 
    {
        //local constants

        //local variables
        
        /*****************************************************/

        //IF x postion out of bounds.
        if(pos.x < 0)
        {
            //reset x cord to 0. 
            pos.x = 0;
        } 
        //else if x postition in bounds 
        else if(pos.x >= Screen.COLS) 
        {
            pos.x = Screen.COLS - 1;
        }//end IF

        //if y position out of bounds
        if(pos.y < 0) 
        {
            //reset y cord to 0.
            pos.y = 0;
        } 
        else if(pos.y >= Screen.ROWS) 
        {
            pos.y = Screen.ROWS - 1;
        }//end if 
    }//end update
    /**********************************************************
	* Method Name    : draw
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  The method draw, draws the png 
    *    in the corresponding row and collum. 
	**********************************************************/
    public void draw(Graphics g, ImageObserver obs) 
    {
        g.drawImage(icon, pos.x * Screen.TILE_SIZE, pos.y * Screen.TILE_SIZE, obs);
    }
    /**********************************************************
	* Method Name    : getPos
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: The method getPos checks where the pieces
    *   are and returns the position.
	**********************************************************/
    public Point getPos() 
    {
        //local constants

        //local variables
        
        /*****************************************************/
        
        //return position of x and y.
        return pos;
    }
//TODO add a method to see if the move is valid
//TODO add a method to see if the move results in a capture
    /**********************************************************
	* Method Name    : setPos
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: setPos updates the position of the  
    *   pieces. 
	**********************************************************/
    public void setPos(int updateX, int updateY) 
    {
        //local constants

        //local variables
        
        /*****************************************************/
        System.out.println("Piece moving " + updateX + ", " + updateY);
       
        pos.x += updateX; //uptades postion of x
        pos.y += updateY; //updates postion of y
    }
    /**********************************************************
	* Method Name    : isClicked 
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: isCLicked checks if a piece was
    *   clicked or not and returns a boolean, true if it was clicked 
    *   false if it wasn't clicked.  
	**********************************************************/
    public boolean isClicked(MouseEvent e) 
    {
        //local constants

        //local variables
        double clickedXPos; //hold horizontal position
        double clickedYPos; //holds vertical postition
        /*****************************************************/
        
        clickedXPos = e.getX() / Screen.TILE_SIZE;
        clickedYPos = e.getY() / Screen.TILE_SIZE;

        //IF clickedXPos value the same as the x value of passed piece 
        //and clickedYPos same as the passed pieces y value
        if(clickedXPos == pos.x && clickedYPos == pos.y)
        {
            System.out.println("Piece clicked");
            return true;
        }
        else
        {
            System.out.println("Piece not clicked");
            return false;
        }//end IF
    }//end isClicked 
}//end Pieces 