/**********************************************************
 * Program Name   : Pawn
 * Author         : Jordan
 * Date           : 2/22/2023
 * Course/Section : Software Engineering 221 - 301
 * Program Description: This class hold the information
 *  on how a pawn should move
 * 
 * Methods: 
 * -------
 * Constructor:  
 **********************************************************/
public class Pawn extends Piece
{
    //class constants

    //class variables
    public String color; // color of pawn 

    /**********************************************************
	* Method Name    : Pawn(Constructor)
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: The pawn constructor parameters 
    *   holds color and x,y positions. and sets the png for
    *   the pawn  
	**********************************************************/
    public Pawn(String color, int x, int y) 
    {
        //local constants

        //local variables
        
        /*****************************************************/

         //calls Pieces constructor and passes x and y positions
        super(x,y); 
        //sets pawn png to a pawn and sets if the pawns color
        loadImage(color + "Pawn");
        //intializing color
        this.color = color;

    }//end Pawn(Consturctor)
}//end Pawn

