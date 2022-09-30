package game.piece;

import game.board.Board;
import game.board.Square;

public abstract class Piece {

    protected Board board;
    protected Color color;
    protected int x;
    protected int y;


    public Piece(Board board, Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.board = board;
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

    public abstract boolean move(int newX, int newY);

    public abstract boolean canMoveTo(int newX, int newY);

    public abstract boolean canMoveAnywhere();

    protected void changePosition(int newX, int newY) {
        board.incrMoveCount();
        Square[][] squares = board.getSquares();

        if (!squares[newX][newY].isEmpty()) {
            board.removePiece(squares[newX][newY].getPiece().color, squares[newX][newY].getPiece());
            board.resetMoveCount();
        }

        squares[x][y].removePiece();
        squares[newX][newY].setPiece(this);
        x = newX;
        y = newY;

    }

    public int getXDistance(int newX) {
        return Math.abs(x - newX);
    }

    public int getYDistance(int newY) {
        return Math.abs(y - newY);
    }
}
