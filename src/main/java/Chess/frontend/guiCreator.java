package main.java.Chess.frontend;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class guiCreator extends JFrame
{
    public static void makeTitle() 
    {
        // Creates the view port essentially

        JFrame mainScreen = new JFrame("Board Classics");
        mainScreen.setSize(500,500);
        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setResizable(false);
        mainScreen.getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("Board Classics");
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        title.setBorder(border);
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("MV Boli", Font.PLAIN, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        mainScreen.add(title);
       
        JButton playGameButton = new JButton();
        playGameButton.setText("Play game");
        playGameButton.setBounds(100,100,100,50);
        playGameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                mainScreen.setVisible(false);
                makeGameSelect();
    
            }
        });
        mainScreen.add(playGameButton);
    }
    public static void makeGameSelect()
    {
        JFrame gameSelect = new JFrame();
        gameSelect.setSize(500,500);
        gameSelect.setVisible(true);
        gameSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameSelect.setResizable (false);
        gameSelect.getContentPane().setBackground(Color.BLACK);

        JButton chess = new JButton();
        Screen screen = new Screen();
        chess.setText("Play game");
        chess.setBounds(100, 100, 100, 50);
        chess.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                gameSelect.setVisible(false);
                JFrame chessPanel = new JFrame();
                chessPanel.add(screen);
                chessPanel.addKeyListener(screen);
            }
            
        });
       
    }
}   

