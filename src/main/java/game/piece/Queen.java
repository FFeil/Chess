package game.piece;

import game.Board;
import game.Square;

public class Queen extends Piece {

    public Queen(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        if (StraightHelper.moveCheck(this, newX, newY) || DiagonalHelper.moveCheck(this, newX, newY)) {
            changePosition(newX, newY);
            return true;
        }
        return false;
    }
}
