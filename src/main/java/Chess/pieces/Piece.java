package Chess.pieces;

import Chess.frontend.Screen;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * 
 * This is a basic class all pieces are needed to extend.
 * I haven't made much code changes (except adding abstract methods), mostly adding javadocs
 * Its fine to not write javadocs initially, but we should have them down for every class
 * and method at some point (except maybe getters and setters as they're self explanitory).
 * 
 * @author Jordan
 * @author Kyle
 */
public abstract class Piece {
    //class vars
    private BufferedImage icon;
    private Point pos; 
    /**
     * 
     * This constructor sets up a piece
     * 
     * @param x the x pos
     * @param y the y pos
     * @param imgfilepath the filepath for the image (starts from Chess folder, ignore .png)
     * @throws java.io.IOException required due to Image needing a file
     */
    protected Piece(int x, int y, String imgfilepath) throws IOException {
        this.pos = new Point(x,y);
        this.icon = ImageIO.read(new File("src/main/resources/Chess/" + imgfilepath + ".png"));
    }
    /**
     * 
     * This abstract method should be used for movement calculations
     * 
     * @param pos the position to attempt to move twords
     */
    protected abstract void movePiece(Point pos);
    /**
     * 
     * This abstract method should be used to see if a capture can occur at a certain location
     * 
     * @param pos the position to attempt a capture on
     * @return true if capture succeeds
     */
    protected abstract boolean attemptCapture(Point pos);
    /**
     * 
     * This method loads in an image to associate with this piece
     * 
     * @param path the filepath to use
     */
    protected void loadImage(String path) {
        try {
            icon = ImageIO.read(new File("src/main/resources/Chess/" + path + ".png"));
        } 
        catch (IOException e) {
            System.out.println("Error loading player image " + e.getMessage());
        }
    }
    /**
     * 
     * Not sure yet what this actually does
     * (seems like this should be inside draw())
     * 
     */
    public void update() {
        if(pos.x < 0) {
            pos.x = 0;
        } 
        else if(pos.x >= Screen.COLS) {
            pos.x = Screen.COLS - 1;
        }
        if(pos.y < 0) {
            pos.y = 0;
        } else if(pos.y >= Screen.ROWS) {
            pos.y = Screen.ROWS - 1;
        }
    }
    /**
     * 
     * This method re-draws the piece at its location
     * 
     * @param g not quite sure what these params do
     * @param obs feel free to change this if you know
     */
    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(icon, pos.x * Screen.TILE_SIZE, pos.y * Screen.TILE_SIZE, obs);
    }
    /**
     * 
     * This method checks if this piece was clicked using an event
     * 
     * @param e the event to read
     * @return true if clicked
     */
    public boolean isClicked(MouseEvent e) {
        double x = e.getX() / Screen.TILE_SIZE;
        double y = e.getY() / Screen.TILE_SIZE;
        if(x == pos.x && y == pos.y) {
            System.out.println("Piece clicked");
            return true;
        }
        System.out.println("Piece not clicked");
        return false;
    }
    //Have getters and setters at the bottom. That way the unique methods are easier to find
    //Don't necessary need to do that, just what I prefer
    /**
     * 
     * This method sets the position for this piece
     * 
     * @param x the x pos
     * @param y the y pos
     */
    public void setPos(int x, int y) {
        //Output should not be in this method
        //System.out.println("Piece moving " + x + ", " + y);
        this.pos.x = x;
        this.pos.y = y;
    }
    /**
     * 
     * This method gets the position for this piece
     * 
     * @return The point object representing the position
     */
    public Point getPos() {
        return pos;
    }
}
