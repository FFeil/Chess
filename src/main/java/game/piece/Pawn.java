package game.piece;

import game.Board;
import game.Square;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Pawn extends Piece {

    private boolean moved = false;
    private final boolean onFifthRow = false;

    public Pawn(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (((newX - x == 1 && color == BLACK ) || (newX - x == -1 && color == WHITE))
                && y == newY && square.isEmpty()) { // move 1 forward
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        if (getXDistance(newX) == 2 && y == newY && square.isEmpty()
                && ((color == BLACK && board.getSquares()[x + 1][y].isEmpty())
                || ((color == Color.WHITE && board.getSquares()[x - 1][y].isEmpty())))
                && !moved) { // move 2 forward
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        if (getXDistance(newX) == 1 && getYDistance(newY) == 1
                && square.containsPieceOfOtherColor(color)) { // move diagonal
            changePosition(newX, newY);
            moved = true;
            return true;
        }

        return false;
    }

    //TODO: en pasant
}
