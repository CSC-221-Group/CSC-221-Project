package main.java.Chess.frontend;

import javax.swing.*;
import main.Piece.Piece;
import java.awt.*;
import java.awt.event.*;
// import java.util.Random;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import main.Piece.ChessPieces.*;

public class Screen extends JPanel implements ActionListener, KeyListener 
{
    private final int DELAY = 10; // delay between each frame in ms
    public static final int TILE_SIZE = 32;
    public static final int ROWS = 8;
    public static final int COLS = 8;
    public static final int NUM_COINS = 10;
    // private static final int serialVersionUID = 1;

    private Timer timer; // triggers actionPerformed()
    private int currentTurn = 1; // 1 = player1, 2 = player2
    private ArrayList<Piece> p1Pieces = new ArrayList<Piece>();
    private ArrayList<Piece> p2Pieces = new ArrayList<Piece>();
    private Cell[][] cells = new Cell[ROWS][COLS];
    
    private class mouseAdapter extends MouseAdapter 
    {
        int preX, preY;
        boolean mousePressed = false;
        Piece currentPiece = null;

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getX() / TILE_SIZE > COLS || e.getY() / TILE_SIZE > ROWS) 
            {
                return;
            }
            Cell cell = cells[e.getX() / TILE_SIZE][(ROWS-1) - (e.getY() / TILE_SIZE)];
            
            if(!cell.isOccupied()) {

                return;
            }
            
            if(cell.getPiece().getOwnedBy() != currentTurn) 
            {
                System.out.println(cell.getPiece().getOwnedBy() + " != " + currentTurn);
                return;
            }
            currentPiece = cell.getPiece();
            mousePressed = true;
            preX = e.getX() / TILE_SIZE;
            preY = (ROWS-1) - (e.getY() / TILE_SIZE);
        }

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            // Not needed for now
        }

        @Override
        public void mouseReleased(MouseEvent e) 
        {
            if (!mousePressed) 
            {
                return;
            }
            System.out.println("Mouse released at " + e.getX() / TILE_SIZE + ", " + ((ROWS-1) - (e.getY() / TILE_SIZE)));
            mousePressed = false;
            updateLocation(e);
        }

        public void updateLocation(MouseEvent e)
         {
            int x = e.getX() / TILE_SIZE;
            int y = (ROWS-1) - (e.getY() / TILE_SIZE);
            if (x > COLS || y > ROWS) 
            {
                return;
            }
            Cell cell = cells[x][y];
            if (cell.isOccupied()) {
                return;
            }
            cells[preX][preY].setPiece(null);
            cells[x][y].setPiece(currentPiece);
            currentPiece = null;
            repaint();
        }

    }

    public Screen()
    {
        initScreen();
    }

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

    private void initGame() 
    {
        // Random rand = new Random();
        timer = new Timer(DELAY, this);
        timer.start();
        resetBoard();
        assignPieces();
    }

    
    /** 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
     {
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

    
    /** 
     * @param e
     */
    // Key Binds
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
       if (e.getKeyCode() == KeyEvent.VK_SPACE) {
           System.out.println("Spacebar pressed");
           if (currentTurn == 1) 
           {
               currentTurn = 2;
           } else if (currentTurn == 2) 
           {
               currentTurn = 1;
           }
       }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }

    // GRAPHICS/DRAWING METHODS
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawBackground(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void resetBoard() 
    {
        cells[0][0] = new Cell(0, 0, new Rook("white", 0, 0, 1)); // ROOK
        cells[1][0] = new Cell(1, 0, new Knight("white", 1, 0, 1)); // KNIGHT
        cells[2][0] = new Cell(2, 0, new Bishop("white", 2, 0, 1)); // BISHOP
        cells[3][0] = new Cell(3, 0, new Queen("white", 3, 0, 1)); // QUEEN
        cells[4][0] = new Cell(4, 0, new King("white", 4, 0, 1)); // KING
        cells[5][0] = new Cell(5, 0, new Bishop("white", 5, 0, 1)); // BISHOP
        cells[6][0] = new Cell(6, 0, new Knight("white", 6, 0, 1)); // KNIGHT
        cells[7][0] = new Cell(7, 0, new Rook("white", 7, 0, 1)); // ROOK
        for (int i = 0; i < 8; i++) 
        {
            cells[i][1] = new Cell(i, 1, new Pawn("white", i, 1,1)); // PAWN
        }

        cells[0][7] = new Cell(0, 7, new Rook("black", 0, 7, 2)); // ROOK
        cells[1][7] = new Cell(1, 7, new Knight("black", 1, 7, 2)); // KNIGHT
        cells[2][7] = new Cell(2, 7, new Bishop("black", 2, 7, 2)); // BISHOP
        cells[3][7] = new Cell(3, 7, new Queen("black", 3, 7, 2)); // QUEEN
        cells[4][7] = new Cell(4, 7, new King("black", 4, 7, 2)); // KING
        cells[5][7] = new Cell(5, 7, new Bishop("black", 5, 7, 2)); // BISHOP
        cells[6][7] = new Cell(6, 7, new Knight("black", 6, 7, 2)); // KNIGHT
        cells[7][7] = new Cell(7, 7, new Rook("black", 7, 7, 2)); // ROOK
        for (int i = 0; i < 8; i++) 
        {
            cells[i][6] = new Cell(i, 6, new Pawn("black", i, 6, 2)); // PAWN
        }

        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 2; j < 6; j++) 
            {
                cells[i][j] = new Cell(i, j);
            }
        }
        //cells[0][0] = new Cell(0,0, new CheckerPiece("red",0,0,1)); 
    }

    private void assignPieces() 
    {
        p1Pieces.clear();
        p2Pieces.clear();
        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 0; j < COLS; j++) 
            {
                if (cells[i][j].getPiece() != null)
                {
                    if (cells[i][j].getPiece().getOwnedBy() == 1) 
                    {
                        p1Pieces.add(cells[i][j].getPiece());
                    } 
                    else if (cells[i][j].getPiece().getOwnedBy() == 2) 
                    {
                        p2Pieces.add(cells[i][j].getPiece());
                    }
                }
            }
        }
    }

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
                }
            }
        }
    }

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
