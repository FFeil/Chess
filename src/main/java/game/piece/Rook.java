package game.piece;

import game.Board;
import game.Square;

public class Rook extends Piece {

    boolean moved;

    public Rook(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        if (StraightHelper.moveCheck(this, newX, newY)) {
            changePosition(newX, newY);
            moved = true;
            return true;
        }
        return false;
    }
}
