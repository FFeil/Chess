package game.piece;

import game.Board;
import game.Square;

public class Knight extends Piece {

    public Knight(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (DiagonalHelper.move(this, newX, newY)) {
            move(newX, newY);
            return true;
        }
        return false;
    }
}
