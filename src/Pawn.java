public class Pawn extends Piece {
    public String color;
    public Pawn(String color, int x, int y) {
        super(x,y);
        loadImage(color + "Pawn");
        this.color = color;
    }
}

