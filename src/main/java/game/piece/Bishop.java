package game.piece;

import game.board.Board;
import game.piece.helper.DiagonalHelper;

public class Bishop extends Piece {

    public Bishop(Board board, Color color, int x, int y) {
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
        return DiagonalHelper.moveCheck(this, newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return DiagonalHelper.canMoveAnywhere(this);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "bishop.png";
    }
}
