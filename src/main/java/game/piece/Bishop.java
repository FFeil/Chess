package game.piece;

import game.Board;
import game.Square;

public class Bishop extends Piece {

    public Bishop(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        if (DiagonalHelper.moveCheck(this, newX, newY)) {
            changePosition(newX, newY);
            return true;
        }
        return false;
    }
}
