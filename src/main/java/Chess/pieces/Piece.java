package Chess.pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Chess.frontend.Screen;
import java.awt.Point;

//mouse listener imports
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * 
 * This class represents a base for all Pieces
 * 
 * @author Alan
 */
public abstract class Piece
 {
    private BufferedImage icon;
    private Point pos;
    private boolean captured = false;
    protected static ArrayList<Piece> totalPieces = new ArrayList<>();

    /*
     * Constructor
     */
    public Piece(int x, int y) 
    {
        pos = new Point(x, y);
        totalPieces.add(this);
    }
    
    /** 
     * @param path
     */
    // Image loading
    protected void loadImage(String game,String path)
    {
        try 
        {
            icon = ImageIO.read(new File("C:/Users/Butte/OneDrive/Documents/NetBeansProjects/CSC-221-Project/src/main/resources/Images/" + game + "/" + path + ".png"));
        } catch (IOException e) 
        {
            System.out.println("Error loading player image " + e.getMessage());
        }
    }
    //  public methods
    public void update() 
    {
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
    public void draw(Graphics g, ImageObserver obs) 
    {
        g.drawImage(icon, pos.x * Screen.TILE_SIZE, (7 * Screen.TILE_SIZE) - pos.y * Screen.TILE_SIZE, obs);
    }

    public Point getPos() 
    {
        return pos;
    }
//TODO add a method to see if the move is valid
//TODO add a method to see if the move results in a capture

    public void setPos(int x, int y) 
    {
        pos.x = x;
        pos.y = y;
    }

    public boolean isClicked(MouseEvent e) 
    {
        double x = e.getX() / Screen.TILE_SIZE;
        double y = e.getY() / Screen.TILE_SIZE;
        if(x == pos.x && y == pos.y) {
            System.out.println("Piece clicked");
            return true;
        }
        System.out.println("Piece not clicked");
        return false;
    }

    public boolean isCaptured() 
    {
        return captured;
    }
    
    public abstract void move(int x, int y) throws InvalidMovementException;
}