import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Point;

//mouse listener imports
import java.awt.event.MouseEvent;



// THIS IS TEMPORARY

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

    public Point getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        System.out.println("Player set to " + x + ", " + y);
        pos.x += x / Screen.TILE_SIZE;
        pos.y += y / Screen.TILE_SIZE;
    }

    public boolean isClicked(MouseEvent e) {
        double x = pos.x * Screen.TILE_SIZE;
        double y = pos.y * Screen.TILE_SIZE;
        if(e.getX() <= x + Screen.TILE_SIZE && e.getX() >= x - Screen.TILE_SIZE) {
            if(e.getY() <= y  + Screen.TILE_SIZE && e.getY() >= y - Screen.TILE_SIZE) {
                System.out.println("Player clicked");
                return true;
            }
        }
        System.out.println("Player not clicked");
        return false;
    }
}
