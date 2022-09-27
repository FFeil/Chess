package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class RookTest {

    private final Board board = new Board();

    @Test
    void move() {
        board.getSquares()[4][4].setPiece(new Rook(board, WHITE, 4, 4));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof Rook);

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(4, 0));
        Assertions.assertTrue(board.getSquares()[4][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[4][0].getPiece() instanceof Rook);

        Assertions.assertTrue(board.getSquares()[4][0].getPiece().move(2, 0));
        Assertions.assertTrue(board.getSquares()[4][0].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][0].getPiece() instanceof Rook);
    }

    @Test
    void capture() {
        board.getSquares()[4][4].setPiece(new Rook(board, WHITE, 4, 4));
        board.getSquares()[4][0].setPiece(new Bishop(board, BLACK, 4, 0));
        board.getSquares()[4][0].setPiece(new Bishop(board, BLACK, 2, 0));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(4, 0));
        Assertions.assertTrue(board.getSquares()[4][0].getPiece() instanceof Rook);
        Assertions.assertTrue(board.getSquares()[4][4].isEmpty());

        Assertions.assertTrue(board.getSquares()[4][0].getPiece().move(2, 0));
        Assertions.assertTrue(board.getSquares()[2][0].getPiece() instanceof Rook);
        Assertions.assertTrue(board.getSquares()[4][0].isEmpty());
    }

    @Test
    void captureSameColor() {
        board.getSquares()[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        board.getSquares()[3][1].setPiece(new Bishop(board, WHITE, 3, 1));

        Assertions.assertFalse(board.getSquares()[3][0].getPiece().move(3, 1));
        Assertions.assertTrue(board.getSquares()[3][0].getPiece() instanceof Rook);
        Assertions.assertTrue(board.getSquares()[3][1].getPiece() instanceof Bishop);
    }

    @Test
    void moveFigureAhead() {
        board.getSquares()[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        board.getSquares()[3][1].setPiece(new Rook(board, BLACK, 3, 1));
        board.getSquares()[4][0].setPiece(new Rook(board, BLACK, 4, 0));

        Assertions.assertFalse(board.getSquares()[3][0].getPiece().move(3, 7));
        Assertions.assertFalse(board.getSquares()[3][0].getPiece().move(5, 0));
    }

    @Test
    void dontMove() {
        board.getSquares()[4][0].setPiece(new Rook(board, WHITE, 4, 0));

        Assertions.assertFalse(board.getSquares()[4][0].getPiece().move(4, 0));
    }

    @Test
    void moveNotStraight() {
        board.getSquares()[4][0].setPiece(new Rook(board, WHITE, 4, 0));

        Assertions.assertFalse(board.getSquares()[4][0].getPiece().move(5, 4));
    }
}