package Chess.frontend;

//avoid wildcards if you can. They waste resources.
import Chess.pieces.Pawn;
import Chess.pieces.Piece;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.Random;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;


/**
 * 
 * Most front-end elements. We really should use packages as they are much nicer.
 * Also lots of these could be broken up into other classes (ex Control for the mouse
 * and keyboard inputs)
 * 
 * @author (add anyone who works on these here.)
 */
public class Screen extends JPanel implements ActionListener, KeyListener {
    //constants
    private final int DELAY = 10; // delay between each frame in ms
    public static final int TILE_SIZE = 32;
    public static final int ROWS = 8;
    public static final int COLS = 8;
    public static final int NUM_COINS = 10;
    // private static final int serialVersionUID = 1;

    private Timer timer; // triggers actionPerformed()
    private int currentTurn = 1; // 1 = player1, 2 = player2
    private Piece[] p1Pieces = new Piece[1]; // TODO: Make this an array of pieces
    private Piece[] p2Pieces = new Piece[1]; // TODO: Make this an array of pieces


    private Piece pieceWasClicked(MouseEvent e, Piece[] pieces) {
        for (Piece piece : pieces) {
            if (piece.isClicked(e)) {
                return piece;
            }
        }
        return null;
    }
    private class mouseAdapter extends MouseAdapter {
        int preX, preY;
        boolean mousePressed = false;
        Piece currentPiece = null;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed at " + e.getX() / TILE_SIZE + ", " + e.getY() / TILE_SIZE);
            if(currentTurn == 1) {
                Piece piece = pieceWasClicked(e, p1Pieces);
                if(piece != null) {
                    preX = e.getX() / TILE_SIZE;
                    preY = e.getY()/ TILE_SIZE;
                    mousePressed = true;
                    currentPiece = piece;
                }
            } else if(currentTurn == 2) {
                Piece piece = pieceWasClicked(e, p2Pieces);
                if(piece != null) {
                    preX = e.getX() / TILE_SIZE;
                    preY = e.getY() / TILE_SIZE;
                    mousePressed = true;
                    currentPiece = piece;
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (mousePressed && currentPiece != null) {
                updateLocation(e);
                preX = e.getX() / TILE_SIZE;
                preY = e.getY() / TILE_SIZE;
            }
        }

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

        public void updateLocation(MouseEvent e) {
            int x = e.getX() / TILE_SIZE;
            int y = e.getY() / TILE_SIZE;
            try {
                currentPiece.move(x, y);
            }
            catch (InvalidMovementException ex) {
                System.out.println(ex.getMessage());
            }
            repaint();
        }

    }

    public Screen() {
        initScreen();
    }

    private void initScreen() {
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
        addMouseMotionListener(new mouseAdapter());
        addMouseListener(new mouseAdapter());
        setBackground(Color.BLACK);
        initGame();
    }

    private void initGame() {
        try {
            p1Pieces[0] = new Pawn(true, 1,1);
            p2Pieces[0]= new Pawn(false, 1,6);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        // Random rand = new Random();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Piece piece : p1Pieces) {
            piece.update();
        }
        for (Piece piece : p2Pieces) {
            piece.update();
        }
        repaint();
    }

    // Key Binds
    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    // GRAPHICS/DRAWING METHODS
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        // for every other index in the array, draw a white square
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if ((i + j) % 2 == 0) {
                    g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void draw(Graphics g) {
        for (Piece piece : p1Pieces) {
            piece.draw(g, this);
        }
        for (Piece piece : p2Pieces) {
            piece.draw(g, this);
        }
    }

}
