package main.java.Chess.frontend;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;
public class guiCreator {
    public static void makeTitle() {
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

        
    }
}
