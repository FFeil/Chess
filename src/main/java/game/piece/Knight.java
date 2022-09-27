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

        if (((getXDistance(newX)  == 1 && getYDistance(newY) == 2) || (getXDistance(newX) == 2 && getYDistance(newY) == 1 ))
                && (square.containsPieceOfOtherColor(color) || square.isEmpty())) {
            changePosition(newX, newY);

            return true;
        }

        return false;
    }
}
