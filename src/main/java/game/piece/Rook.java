package game.piece;

import game.Board;
import game.Square;

import java.util.ArrayList;

public class Rook extends Piece {

    boolean moved;

    public Rook(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (StraightHelper.move(this, newX, newY)) {
            changePosition(newX, newY);
            moved = true;
            return true;
        }
        return false;
    }
}
