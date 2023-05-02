package main.Piece;

/**
 *
 * This exception should be thrown when a piece attempts to do an invalid move
 * 
 */
public class InvalidMovementException extends Exception {

    /**
     * 
     * This constructor calls a new InvalidMovement
     *
     * @param msg the message.
     */
    public InvalidMovementException(String msg) 
{
        super(msg);
    }
    public InvalidMovementException()
    {
        System.out.println("Invalid Move");
    }
}
