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
* InvalidMovementException(String msg) - returns custom string if thrown
* INvalidMovementException() - Returns "Invalid Move"
**********************************************************/
public class InvalidMovementException extends Exception {

    /**********************************************************
	* Method Name    : InvalidMovementException
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This exception will be throw if 
    * a check piece moves in a illegal way
	**********************************************************/
    public InvalidMovementException(String msg) 
    {
        super(msg);
    }//END InvalidMovementException
    
    /**********************************************************
	* Method Name    : InvalidMovementException
	* Author         : Jordan
	* Date           : 
	* Course/Section : Software Engineering 221-301
	* Program Description: This exception will be throw if 
    * a check piece moves in a illegal way
	**********************************************************/
    public InvalidMovementException()
    {
        System.out.println("Invalid Move");
    }//END InvalidMovementException
}//END InvalidMovementException
