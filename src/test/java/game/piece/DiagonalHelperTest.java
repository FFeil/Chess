package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class DiagonalHelperTest {

    private final Board board = new Board();

    @Test
    void move() {
        board.getSquares()[4][4].setPiece(new Bishop(board, WHITE, 4, 4));

        Assertions.assertTrue(DiagonalHelper.moveCheck(board.getSquares()[4][4].getPiece(), 5, 5));
        Assertions.assertTrue(DiagonalHelper.moveCheck(board.getSquares()[4][4].getPiece(), 2, 2));
        Assertions.assertTrue(DiagonalHelper.moveCheck(board.getSquares()[4][4].getPiece(), 2, 6));
        Assertions.assertTrue(DiagonalHelper.moveCheck(board.getSquares()[4][4].getPiece(), 5, 3));
    }

    @Test
    void capture() {
        board.getSquares()[4][4].setPiece(new Bishop(board, WHITE, 4, 4));
        board.getSquares()[5][5].setPiece(new Bishop(board, BLACK, 5, 5));
        board.getSquares()[5][3].setPiece(new Bishop(board, BLACK, 5, 3));

        Assertions.assertTrue(DiagonalHelper.moveCheck(board.getSquares()[4][4].getPiece(), 5, 5));
        Assertions.assertTrue(DiagonalHelper.moveCheck(board.getSquares()[4][4].getPiece(), 5, 3));
    }

    @Test
    void captureSameColor() {
        board.getSquares()[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        board.getSquares()[2][2].setPiece(new Bishop(board, WHITE, 2, 2));

        Assertions.assertFalse(DiagonalHelper.moveCheck(board.getSquares()[3][3].getPiece(), 2, 2));
    }

    @Test
    void moveFigureAhead() {
        board.getSquares()[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        board.getSquares()[4][4].setPiece(new Bishop(board, BLACK, 4, 4));

        Assertions.assertFalse(DiagonalHelper.moveCheck(board.getSquares()[3][3].getPiece(), 5, 5));
    }

    @Test
    void dontMove() {
        board.getSquares()[4][0].setPiece(new Bishop(board, WHITE, 4, 0));

        Assertions.assertFalse(DiagonalHelper.moveCheck(board.getSquares()[4][0].getPiece(), 4, 0));
    }
}