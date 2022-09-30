package game.piece;

import game.board.Board;
import game.board.Square;
import game.piece.helper.CanMoveAnywhereHelper;

public class Knight extends Piece {

    public Knight(Board board, Color color, int x, int y) {
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
        Square square = board.getSquares()[newX][newY];

        return ((getXDistance(newX)  == 1 && getYDistance(newY) == 2)
                || (getXDistance(newX) == 2 && getYDistance(newY) == 1 ))
                && (square.containsPieceOfOtherColor(color) || square.isEmpty());
    }

    @Override
    public boolean canMoveAnywhere() {
        return CanMoveAnywhereHelper.canMoveAnywhere(this, -2);
    }
}
