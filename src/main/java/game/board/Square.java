package game.board;

import game.piece.Color;
import game.piece.Piece;

public class Square {

    private Piece piece;

    public Square () {
        piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void removePiece() {
        piece = null;
    }

    public boolean containsPieceOfOtherColor(Color color) {
        if (piece == null) {
            return false;
        }
        return piece.getColor() != color;
    }
}
