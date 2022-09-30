package game.piece;

import game.board.Board;
import game.piece.helper.DiagonalHelper;
import game.piece.helper.StraightHelper;

public class Queen extends Piece {

    public Queen(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        if (canMoveTo(newX, newY) && !board.kingHasXray(getX(), getY(), newX, newY, color)) {
            changePosition(newX, newY);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return StraightHelper.moveCheck(this, newX, newY) || DiagonalHelper.moveCheck(this, newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return StraightHelper.canMoveAnywhere(this) || DiagonalHelper.canMoveAnywhere(this);
    }
}
