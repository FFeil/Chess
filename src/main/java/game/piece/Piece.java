package game.piece;

import game.Board;
import game.Square;

public abstract class Piece {

    protected Board board;
    protected Color color;
    protected int x;
    protected int y;
    protected boolean active;

    public Piece(Board board, Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.board = board;
        active = true;
    }

    public Board getBoard() {
        return board;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive() {
        return active;
    }

    public abstract boolean move(int newX, int newY);

    protected void changePosition(int newX, int newY) {
        board.getSquares()[x][y].removePiece();
        board.getSquares()[newX][newY].setPiece(this);
        x = newX;
        y = newY;
    }

    protected int getXDistance(int newX) {
        return Math.abs(x - newX);
    }

    protected int getYDistance(int newY) {
        return Math.abs(y - newY);
    }
}
