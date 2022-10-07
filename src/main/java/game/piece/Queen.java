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
        changePosition(newX, newY);
        return true;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return StraightHelper.moveCheck(this, newX, newY) || DiagonalHelper.moveCheck(this, newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return StraightHelper.canMoveAnywhere(this) || DiagonalHelper.canMoveAnywhere(this);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "queen.png";
    }
}
