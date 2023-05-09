package main.Piece;

/**********************************************************
* Program Name   : InvalidMoveException
* Author         : Jordan
* Date           : 
* Course/Section : Software Engineering 221-301
* Program Description: 
*  
* Methods:
* -------
* InvalidMovementException() - Returns "Invalid Move"
**********************************************************/
public class InvalidMovementException extends Exception 
{

    /**********************************************************
	* Method Name    : InvalidMovementException
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This exception will be throw if 
    * a check piece moves in a illegal way
    *BEGIN - InvalidMovmentExpextion
    * print "invalid Move"
    *END - InvalidMovementExeption
	**********************************************************/
    public InvalidMovementException()
    {
        //Local constants
        //Local variables 
        /*****************************************************/

        System.out.println("Invalid Move");
    }//END InvalidMovementException
}//END InvalidMovementException
