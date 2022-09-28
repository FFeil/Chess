package game.piece;

import game.Board;

public class Rook extends Piece {

    boolean moved;

    public Rook(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        if (canMoveTo(newX, newY) && !board.kingHasXray(getX(), getY(), newX, newY, color)) {
            changePosition(newX, newY);
            moved = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return StraightHelper.moveCheck(this, newX, newY);
    }
}
