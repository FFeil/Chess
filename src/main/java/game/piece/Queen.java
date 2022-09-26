package game.piece;

import game.Board;
import game.Square;

public class Queen extends Piece {

    public Queen(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (StraightHelper.move(this, newX, newY) || DiagonalHelper.move(this, newX, newY)) {
            changePosition(newX, newY);
            return true;
        }
        return false;
    }
}
