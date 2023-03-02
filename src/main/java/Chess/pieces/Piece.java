package main.java.Chess.pieces;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.java.Chess.frontend.*;

import java.awt.Point;

//mouse listener imports
import java.awt.event.MouseEvent;

public abstract class Piece {
    private BufferedImage icon;
    private Point pos;
    private int ownedBy; // 1 or 2 for player 1 or player 2
    private boolean captured = false;

    /*
     * Constructor
     */
    public Piece(int x, int y) {
        pos = new Point(x, y);
    }

    public Piece(int x, int y, int ownedBy) {
        pos = new Point(x, y);
        this.ownedBy = ownedBy;
    }
    // Image loading
    protected void loadImage(String path) {
        // TODO make this work for other icons
        try {
            icon = ImageIO.read(new File("src/main/resources/Chess/" + path + ".png"));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }
    //  public methods
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
    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(icon, pos.x * Screen.TILE_SIZE, (7 * Screen.TILE_SIZE) - pos.y * Screen.TILE_SIZE, obs);
    }

    public Point getPos() {
        return pos;
    }
//TODO add a method to see if the move is valid
//TODO add a method to see if the move results in a capture

    public void setPos(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

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

    public int getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(int ownedBy) {
        this.ownedBy = ownedBy;
    }

    public boolean isCaptured() {
        return captured;
    }
    protected abstract boolean moveCheck(Cell start, Cell end);
    
    public void movePiece(Cell start, Cell end) {
        // just straight up move the piece, moveCheck will handle if it can move or if it captured or not
        if(moveCheck(start, end)) {
            start.isOccupiedBy(null);
            end.isOccupiedBy(this);
            this.setPos(end.getX(), end.getY());
        }
    }

    protected abstract boolean attemptCapture(Cell end);
}