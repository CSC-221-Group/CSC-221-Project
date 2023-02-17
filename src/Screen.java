
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.Random;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Screen extends JPanel implements ActionListener, KeyListener {
    private final int DELAY = 10; // delay between each frame in ms
    public static final int TILE_SIZE = 32;
    public static final int ROWS = 8;
    public static final int COLS = 8;
    public static final int NUM_COINS = 10;
    // private static final int serialVersionUID = 1;

    private Timer timer; // triggers actionPerformed()
    private Player player;

    private class mouseAdapter extends MouseAdapter {
        int preX, preY;
        boolean mousePressed = false;
        boolean playerClicked = false;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed at " + e.getX() + ", " + e.getY());
            if (player.isClicked(e)) {
                preX = e.getX();
                preY = e.getY();
                mousePressed = true;
                playerClicked = true;
                updateLocation(e);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (mousePressed) {
                updateLocation(e);
                preX = e.getX();
                preY = e.getY();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released");
            System.out.println("Mouse released at " + e.getX() + ", " + e.getY());
            updateLocation(e);
            mousePressed = false;
            playerClicked = false;
        }

        public void updateLocation(MouseEvent e) {
            if (playerClicked) {
                System.out.println("GetX: " + e.getX() + " GetY: " + e.getY());
                System.out.println("PreX: " + preX + " PreY: " + preY);
                player.setPos(e.getX() - preX, e.getY() - preY);
            }
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
        player = new Player();
        // Random rand = new Random();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        repaint();
    }

    // Key Presses
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
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
        player.draw(g, this);
    }

}
