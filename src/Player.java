import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Player {
    private BufferedImage icon;
    private Point pos;

    public Player() {
        pos = new Point(0, 0);
        loadImage();
    }

    private void loadImage() {
        try {
            icon = ImageIO.read(new File("images/player.png"));
        } catch (IOException e) {
            System.out.println("Error loading player image" + e.getMessage());
        }
    }

    public void update() {
        if(pos.x < 0) {
            pos.x = 0;
        } else if(pos.x >= Screen.COLS) {
            pos.x = Screen.COLS - 1;
        }
        if(pos.y < 0) {
            pos.y = 0;
        } else if(pos.y >= Screen.ROWS) {
            pos.y = Screen.ROWS - 1;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            pos.x -= 1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pos.x += 1;
        }
        if (key == KeyEvent.VK_UP) {
            pos.y -= 1;
        }
        if (key == KeyEvent.VK_DOWN) {
            pos.y += 1;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(icon, pos.x * Screen.TILE_SIZE, pos.y * Screen.TILE_SIZE, obs);
    }

}
