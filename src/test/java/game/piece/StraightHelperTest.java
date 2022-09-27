package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class StraightHelperTest {

    private final Board board = new Board();

    @Test
    void move() {
        board.getSquares()[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        board.getSquares()[5][7].setPiece(new Rook(board, WHITE, 5, 7));

        Assertions.assertTrue(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 3, 7));
        Assertions.assertTrue(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 5, 0));
        Assertions.assertTrue(StraightHelper.moveCheck(board.getSquares()[5][7].getPiece(), 3, 7));
        Assertions.assertTrue(StraightHelper.moveCheck(board.getSquares()[5][7].getPiece(), 5, 0));
    }

    @Test
    void capture() {
        board.getSquares()[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        board.getSquares()[3][5].setPiece(new Rook(board, BLACK, 3, 5));
        board.getSquares()[5][0].setPiece(new Rook(board, BLACK, 5, 0));

        Assertions.assertTrue(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 3, 5));
        Assertions.assertTrue(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 5, 0));
    }

    @Test
    void captureSameColor() {
        board.getSquares()[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        board.getSquares()[3][1].setPiece(new Rook(board, WHITE, 3, 5));

        Assertions.assertFalse(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 3, 5));
    }

    @Test
    void moveFigureAhead() {
        board.getSquares()[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        board.getSquares()[3][1].setPiece(new Rook(board, BLACK, 3, 1));
        board.getSquares()[4][0].setPiece(new Rook(board, BLACK, 4, 0));

        Assertions.assertFalse(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 3, 7));
        Assertions.assertFalse(StraightHelper.moveCheck(board.getSquares()[3][0].getPiece(), 5, 0));
    }

    @Test
   void dontMove() {
        board.getSquares()[4][0].setPiece(new Rook(board, WHITE, 4, 0));

        Assertions.assertFalse(StraightHelper.moveCheck(board.getSquares()[4][0].getPiece(), 4, 0));
    }
}