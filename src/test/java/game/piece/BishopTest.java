package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class BishopTest {

    private final Board board = new Board();

    @Test
    void move() {
        board.getSquares()[4][4].setPiece(new Bishop(board, WHITE, 4, 4));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof Bishop);

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(3, 3));
        Assertions.assertTrue(board.getSquares()[4][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[3][3].getPiece() instanceof Bishop);

        Assertions.assertTrue(board.getSquares()[3][3].getPiece().move(2, 4));
        Assertions.assertTrue(board.getSquares()[3][3].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][4].getPiece() instanceof Bishop);

        Assertions.assertTrue(board.getSquares()[2][4].getPiece().move(3, 3));
        Assertions.assertTrue(board.getSquares()[2][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[3][3].getPiece() instanceof Bishop);
    }

    @Test
    void capture() {
        board.getSquares()[4][4].setPiece(new Bishop(board, WHITE, 4, 4));
        board.getSquares()[5][5].setPiece(new Bishop(board, BLACK, 5, 5));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(5, 5));
        Assertions.assertTrue(board.getSquares()[5][5].getPiece() instanceof Bishop);
    }

    @Test
    void captureSameColor() {
        board.getSquares()[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        board.getSquares()[2][2].setPiece(new Pawn(board, WHITE, 2, 2));

        Assertions.assertFalse(board.getSquares()[3][3].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[3][3].getPiece() instanceof Bishop);
    }

    @Test
    void moveFigureAhead() {
        board.getSquares()[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        board.getSquares()[4][4].setPiece(new Pawn(board, BLACK, 4, 4));

        Assertions.assertFalse(board.getSquares()[3][3].getPiece().move(5, 5));
        Assertions.assertTrue(board.getSquares()[3][3].getPiece() instanceof Bishop);
        Assertions.assertTrue(board.getSquares()[5][5].isEmpty());
    }

    @Test
    void dontMove() {
        board.getSquares()[4][0].setPiece(new Bishop(board, WHITE, 4, 0));

        Assertions.assertFalse(board.getSquares()[4][0].getPiece().move(4, 0));
        Assertions.assertTrue(board.getSquares()[4][0].getPiece() instanceof Bishop);
    }

    @Test
    void moveNotDiagonal() {
        board.getSquares()[4][4].setPiece(new Bishop(board, WHITE, 4, 4));

        Assertions.assertFalse(board.getSquares()[4][4].getPiece().move(4, 5));
    }
}