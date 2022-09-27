package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class KnightTest {

    private final Board board = new Board();

    @Test
    void move() {
        Assertions.assertTrue(board.getSquares()[0][1].getPiece() instanceof Knight);
        Assertions.assertTrue(board.getSquares()[0][1].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[0][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof Knight);

        Assertions.assertTrue(board.getSquares()[2][2].getPiece().move(0, 1));
        Assertions.assertTrue(board.getSquares()[2][2].isEmpty());
        Assertions.assertTrue(board.getSquares()[0][1].getPiece() instanceof Knight);
    }

    @Test
    void capture() {
        board.getSquares()[2][2].setPiece(new Queen(board, WHITE, 2, 2));

        Assertions.assertTrue(board.getSquares()[0][1].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[0][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof Knight);

    }

    @Test
    void movePieceOfSameColor() {
        board.getSquares()[2][2].setPiece(new Queen(board, BLACK, 2, 2));

        Assertions.assertFalse(board.getSquares()[0][1].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[0][1].getPiece() instanceof Knight);
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof Queen);
    }

    @Test
    public void dontMove() {
        Assertions.assertFalse(board.getSquares()[0][1].getPiece().move(0, 1));
    }
}