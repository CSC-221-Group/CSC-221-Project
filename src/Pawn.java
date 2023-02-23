

public class Pawn extends Piece {
    public String color;
    public Pawn(String color, int x, int y, int owner) {
        super(x,y, owner);
        loadImage(color + "Pawn");
        this.color = color;
    }

    
    /** 
     * @param board
     * @param start
     * @param end
     */
    @Override
    public void move(Screen board, Cell start, Cell end) {
        if (color.equals("white")) {
            if (start.getY() == 1) {
                if (end.getY() == 3) {
                    if (start.getX() == end.getX()) {
                        if (!end.isOccupied()) {
                            end.setPiece(this);
                            start.setPiece(null);
                        }
                    }
                }
            }
            if (end.getY() == start.getY() + 1) {
                if (start.getX() == end.getX()) {
                    if (!end.isOccupied()) {
                        end.setPiece(this);
                        start.setPiece(null);
                    }
                }
            }
        }
        if (color.equals("black")) {
            if (start.getY() == 6) {
                if (end.getY() == 4) {
                    if (start.getX() == end.getX()) {
                        if (!end.isOccupied()) {
                            end.setPiece(this);
                            start.setPiece(null);
                        }
                    }
                }
            }
            if (end.getY() == start.getY() - 1) {
                if (start.getX() == end.getX()) {
                    if (!end.isOccupied()) {
                        end.setPiece(this);
                        start.setPiece(null);
                    }
                }
            }
        }
    }
}

