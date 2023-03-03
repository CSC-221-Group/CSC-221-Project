package main.java.Chess;

import javax.swing.*;

import main.java.Chess.frontend.Screen;
import main.java.Chess.frontend.guiCreator;



/**
 * 
 * The main class for Chess app
 * 
 * @author Jordan
 */
public class App {
    guiCreator gui = new guiCreator();
    /**
     * 
     * main method
     * removed "throws Exception" as no lines require an exception thrown
     * 
     * @param args
     */
    public static void main(String[] args) {
        guiCreator.makeTitle();
        //make this an on-screen prompt (I could handle that)
        System.out.println("Space changes turns, it has to be player 1's turn to move white's piecesn and player 2's turn to move black's pieces") ;
    }
}
