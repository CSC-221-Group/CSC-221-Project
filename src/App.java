
import javax.swing.*;
/**********************************************************
 * Program Name   : App
 * Author         : Jordan
 * Date           : 2/21/2023
 * Course/Section : Software Engineering 221 - 301
 * Program Description: 
 * 
 * Methods:
 * -------
 * createAndShowGUI:
 * main: 
 **********************************************************/
public class App 
{
    /**********************************************************
	* Method Name    : createAndShowGUI
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description: This method 
    * 
    * BEGIN
    * END
	**********************************************************/
	private static void createAndShowGUI() 
    {
        //local constants

        //local variables
        JFrame window = new JFrame();
        Screen screen = new Screen();
        /*****************************************************/
        
        window = new JFrame("Board Classics");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        // 
        window.add(screen);
        //
        window.addKeyListener(screen);
        
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }//end createAndShowGUI

    /**********************************************************
	* Method Name    : main
	* Author         : Jordan
	* Date           : 2/22/23
	* Course/Section : Software Engineering 221 - 301
	* Program Description:  
	**********************************************************/
    public static void main(String[] args) throws Exception 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();

                System.out.println("Space changes turns, it has to be player 1's turn to move white's pieces" +
                        " and player 2's turn to move black's pieces") ;
            }
        });
    }//end main
}
