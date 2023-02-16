import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Screen extends JPanel implements ActionListener, KeyListener {
    private final int DELAY = 10; // delay between each frame in ms
    public static final int TILE_SIZE = 16;
    public static final int ROWS = 12;
    public static final int COLS = 18;
    public static final int NUM_COINS = 10;
    private static final int serialVersionUID = 1;

    private Timer timer; // triggers actionPerformed()
    private Player player;


    public Screen() {
        initScreen();
    }

    private void initScreen() {
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
        setBackground(Color.BLACK);
        initGame();
    }

    private void initGame() {
        player = new Player();
        Random rand = new Random();
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
        g.setColor(Color.GRAY);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                g.drawRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void draw(Graphics g) {
        player.draw(g, this);
    }

}

