package game.piece;

import game.board.Board;
import game.board.Square;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;

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

    public abstract void move(int newX, int newY);

    public abstract boolean canMoveTo(int newX, int newY);

    public abstract boolean canMoveAnywhere();

    public abstract String getImagePath();

    public abstract EnumPiece getEnumPiece();

    protected void changePosition(int newX, int newY) {
        Square[][] squares = board.getSquares();
        board.getSquaresToUpdate().add(new Integer[] {x , y});
        board.getSquaresToUpdate().add(new Integer[] {newX , newY});
        board.incrMoveCount();

        if (!squares[newX][newY].isEmpty()) {
            board.getPieceSet(squares[newX][newY].getPiece().color).remove(squares[newX][newY].getPiece());
            board.resetMoveCount();
        }

        squares[newX][newY].setPiece(this);
        squares[x][y].removePiece();
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
