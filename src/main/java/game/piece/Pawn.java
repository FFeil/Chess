package game.piece;

import game.Board;
import game.Square;

public class Pawn extends Piece {

    private boolean moved = false;
    private final boolean onFifthRow = false;

    public Pawn(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (newX == x && newY == y + 1 && square.isEmpty()) { // move 1 forward
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        if (newX == x && newY == y + 2 && square.isEmpty() && board.getSquares()[newX][newY - 1].isEmpty()
                && !moved) { // move 2 forward
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        if (((newX == x + 1 && newY == y + 1) || (newX == x - 1 && newY == y + 1))
                && square.containsPieceOfOtherColor(color)) { // move diagonal
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        return false;
    }
}
