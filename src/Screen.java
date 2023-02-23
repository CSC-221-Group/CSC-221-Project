
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
/**********************************************************
 * Program Name   : Screen
 * Author         : Jordan
 * Date           : 2/21/2023
 * Course/Section : Software Engineering 221 - 301
 * Program Description: 
 * 
 * Methods:
 * -------
 * 
 **********************************************************/
public class Screen extends JPanel implements ActionListener, KeyListener
{
    //class constants
    private final int DELAY = 10;            // delay between each frame in ms
    public static final int TILE_SIZE = 32;  // sets size of board
    public static final int ROWS = 8;        // amount of horizontal rows 
    public static final int COLS = 8;        // amount of vertical collums
    public static final int NUM_COINS = 10;  // no idea 
    // private static final int serialVersionUID = 1;
    //class variables
    private Timer timer;                     // triggers actionPerformed()
    private int currentTurn = 1;             // 1 = player1, 2 = player2
    private Piece[] p1Pieces = new Piece[1]; // TODO: Make this an array of pieces
    private Piece[] p2Pieces = new Piece[1]; // TODO: Make this an array of pieces
    /*****************************************************/
   
    /**********************************************************
	* Method Name    : pieaceWasClicked
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: This methods checks if a piece was 
    *   clicked and returns which was clicked. If non clicked
    *   returns null.
    * BEGIN pieceWasClicked
    *   FOR
    *        IF()
    *    END FOR
    * END
	**********************************************************/
    private Piece pieceWasClicked(MouseEvent e, Piece[] pieces)
    {
        //local constants

		//local variables

		/**************************************************/

        //for every index of Pieces array 
        for (Piece piece : pieces)
        {
            //IF 
            if (piece.isClicked(e)) 
            {
                return piece;
            }
        }
        return null;
    }//end pieaceWasClicked
    
    /**********************************************************
    * Program Name   : Screen
    * Author         : Jordan
    * Date           : 2/21/2023
    * Course/Section : Software Engineering 221 - 301
    * Program Description: 
    * 
    * Methods: createAndShowGUI
    * -------
    * createAndShowGUI:
    * main: 
    **********************************************************/
    private class mouseAdapter extends MouseAdapter 
    {
        //class constants

		//class variables
        int preX, preY;               //
        boolean mousePressed = false; //initializes that mouse wasn't pressed. 
        Piece currentPiece = null;    //
		/**************************************************/

        /**********************************************************
	    * Method Name    : mouseAdapter 
	    * Author         : Jordan
        * Date           : 2/22/23
	    * Course/Section : Software Engineering 221 - 301
	    * Program Description: 
	    **********************************************************/
        @Override
        public void mousePressed(MouseEvent e)
        {
            //local constants

		    //local variables
            Piece piece; //intalizing Piece object.
		    /**************************************************/

            //gets location of where mouse was pressed and returns x and y locations.
            System.out.println("Mouse pressed at " + e.getX() / TILE_SIZE + ", " + e.getY() / TILE_SIZE);

            //IF current turn is player 1/White
            if(currentTurn == 1)
            {
                piece = pieceWasClicked(e, p1Pieces);
                if(piece != null) 
                {
                    preX = e.getX() / TILE_SIZE;
                    preY = e.getY()/ TILE_SIZE;
                    mousePressed = true;
                    currentPiece = piece;
                }
            } 
            //ELSE IF current turn is player 2/Black
            else if(currentTurn == 2) 
            {
                piece = pieceWasClicked(e, p2Pieces);
                if(piece != null) 
                {
                    preX = e.getX() / TILE_SIZE;
                    preY = e.getY() / TILE_SIZE;
                    mousePressed = true;
                    currentPiece = piece;
                }
            }
        }
    /**********************************************************
	* Method Name    : 
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
        @Override
        public void mouseDragged(MouseEvent e) {
            if (mousePressed && currentPiece != null) {
                updateLocation(e);
                preX = e.getX() / TILE_SIZE;
                preY = e.getY() / TILE_SIZE;
            }
        }
    /**********************************************************
	* Method Name    : 
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
        @Override
        public void mouseReleased(MouseEvent e) {
            if (mousePressed && currentPiece != null) {
                System.out.println("Mouse released");
                System.out.println("Mouse released at " + e.getX() / TILE_SIZE + ", " + e.getY() / TILE_SIZE);
                updateLocation(e);
                mousePressed = false;
                currentPiece = null;
            }
        }
        /**********************************************************
	    * Method Name    : 
	    * Author         : 
	    * Date           : 
	    * Course/Section : Software Engineering 221 - 301
	    * Program Description:  
	    **********************************************************/
        public void updateLocation(MouseEvent e) 
        {

            //local constants

            //local variables 
            int x, y;
            int dx , dy; 
		    /**************************************************/
            x = e.getX() / TILE_SIZE;
            y = e.getY() / TILE_SIZE;
            dx = x - preX;
            dy = y - preY;

            currentPiece.setPos(dx, dy);
            repaint();
        }

    }

    public Screen() {
        initScreen();
    }
    /**********************************************************
	* Method Name    : initScreen
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    private void initScreen() 
    {
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
        addMouseMotionListener(new mouseAdapter());
        addMouseListener(new mouseAdapter());
        setBackground(Color.BLACK);
        initGame();
    }
    /**********************************************************
	* Method Name    : 
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    private void initGame() 
    {   
        //sets location of pawn on index 0 for white
        p1Pieces[0] = new Pawn("white", 1,1); //sets location of pawn on index 0 for white
        p2Pieces[0]= new Pawn("black", 1,6);  //sets location of pawn on index 0 for black 
        
        // Random rand = new Random();
        timer = new Timer(DELAY, this);
        timer.start();
    }//end initGame
    /**********************************************************
	* Method Name    : 
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Piece piece : p1Pieces) 
        {
            piece.update();
        }
        for (Piece piece : p2Pieces) 
        {
            piece.update();
        }
        repaint();
    }
    /**********************************************************
	* Method Name    : keyTyped 
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    // Key Binds
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }
    /**********************************************************
	* Method Name    : 
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    @Override
    public void keyPressed(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_SPACE) {
           System.out.println("Spacebar pressed");
           if (currentTurn == 1) {
               currentTurn = 2;
           } else if (currentTurn == 2) {
               currentTurn = 1;
           }
       }
    }
    /**********************************************************
	* Method Name    :  
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    /**********************************************************
	* Method Name    :  
	* Author         : 
	* Date           : 
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    // GRAPHICS/DRAWING METHODS
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }
    /**********************************************************
	* Method Name    : 
	* Author         :
	* Date           : 
	* Course/Section :  Software Engineering 221 - 301 
	* Program Description:  
	**********************************************************/
    private void drawBackground(Graphics g) 
    {
        g.setColor(Color.WHITE);
        // for every other index in the array, draw a white square
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++) 
            {
                if ((i + j) % 2 == 0) 
                {
                    g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }//end IF

            }//end FOR

        }//end FOR
    }//end drawBackground
    /**********************************************************
	* Method Name    : draw
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section :  Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    private void draw(Graphics g)
    {
        for (Piece piece : p1Pieces) 
        {
            piece.draw(g, this);
        }
        for (Piece piece : p2Pieces) 
        {
            piece.draw(g, this);
        }
    }

}
