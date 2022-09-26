package game.piece;

import game.Board;
import game.Square;

public class King extends Piece {

    boolean moved;

    public King(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (((getXDistance(newX) == 1) && (y == newY)) || (((getYDistance(newY) == 1)) && (x == newX))
                || ((getXDistance(newX) == 1 && (getYDistance(newY) == 1)))
                && square.isEmpty() ) {
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        return false;
    }
}
