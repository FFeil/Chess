package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class QueenTest {

    private final Board board = new Board();

    @Test
    void moveStraight() {
        board.getSquares()[4][4].setPiece(new Queen(board, WHITE, 4, 4));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof Queen);

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(4, 0));
        Assertions.assertTrue(board.getSquares()[4][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[4][0].getPiece() instanceof Queen);

        Assertions.assertTrue(board.getSquares()[4][0].getPiece().move(2, 0));
        Assertions.assertTrue(board.getSquares()[4][0].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][0].getPiece() instanceof Queen);
    }

    @Test
    void moveDiagonal() {
        board.getSquares()[4][4].setPiece(new Queen(board, WHITE, 4, 4));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[4][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof Queen);

        Assertions.assertTrue(board.getSquares()[2][2].getPiece().move(5, 5));
        Assertions.assertTrue(board.getSquares()[2][2].isEmpty());
        Assertions.assertTrue(board.getSquares()[5][5].getPiece() instanceof Queen);
    }

    @Test
    void capture() {
        board.getSquares()[3][0].setPiece(new Queen(board, WHITE, 3, 0));
        board.getSquares()[3][5].setPiece(new Rook(board, BLACK, 3, 5));
        board.getSquares()[2][6].setPiece(new Rook(board, BLACK, 2, 6));

        Assertions.assertTrue(board.getSquares()[3][0].getPiece().move(3, 5));
        Assertions.assertTrue(board.getSquares()[3][5].getPiece() instanceof Queen);
        Assertions.assertTrue(board.getSquares()[3][0].isEmpty());

        Assertions.assertTrue(board.getSquares()[3][5].getPiece().move(2, 6));
        Assertions.assertTrue(board.getSquares()[2][6].getPiece() instanceof Queen);
        Assertions.assertTrue(board.getSquares()[3][5].isEmpty());
    }

    @Test
    void captureSameColor() {
        board.getSquares()[3][0].setPiece(new Queen(board, WHITE, 3, 0));
        board.getSquares()[3][1].setPiece(new Bishop(board, WHITE, 3, 1));

        Assertions.assertFalse(board.getSquares()[3][0].getPiece().move(3, 1));
        Assertions.assertTrue(board.getSquares()[3][0].getPiece() instanceof Queen);
        Assertions.assertTrue(board.getSquares()[3][1].getPiece() instanceof Bishop);
    }

    @Test
    void moveFigureAhead() {
        board.getSquares()[3][0].setPiece(new Queen(board, WHITE, 3, 0));
        board.getSquares()[3][1].setPiece(new Rook(board, BLACK, 3, 1));

        Assertions.assertFalse(board.getSquares()[3][0].getPiece().move(3, 2));
    }

    @Test
    void dontMove() {
        board.getSquares()[4][0].setPiece(new Queen(board, WHITE, 4, 0));

        Assertions.assertFalse(board.getSquares()[4][0].getPiece().move(4, 0));
    }

    @Test
    void moveNotStraightOrDiagonal() {
        board.getSquares()[2][3].setPiece(new Queen(board, WHITE, 2, 3));

        Assertions.assertFalse(board.getSquares()[2][3].getPiece().move(4, 4));
    }
}