package game.piece.helper;

import game.board.Board;
import game.board.Square;
import game.piece.Pawn;
import game.piece.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class StraightHelperTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        squares[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        squares[5][7].setPiece(new Rook(board, WHITE, 5, 7));

        Assertions.assertTrue(StraightHelper.moveCheck(squares[3][0].getPiece(), 3, 7));
        Assertions.assertTrue(StraightHelper.moveCheck(squares[3][0].getPiece(), 5, 0));
        Assertions.assertTrue(StraightHelper.moveCheck(squares[5][7].getPiece(), 3, 7));
        Assertions.assertTrue(StraightHelper.moveCheck(squares[5][7].getPiece(), 5, 0));
    }

    @Test
    void capture() {
        squares[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        squares[3][5].setPiece(new Rook(board, BLACK, 3, 5));
        squares[5][0].setPiece(new Rook(board, BLACK, 5, 0));

        Assertions.assertTrue(StraightHelper.moveCheck(squares[3][0].getPiece(), 3, 5));
        Assertions.assertTrue(StraightHelper.moveCheck(squares[3][0].getPiece(), 5, 0));
    }

    @Test
    void captureSameColor() {
        squares[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        squares[3][1].setPiece(new Rook(board, WHITE, 3, 5));

        Assertions.assertFalse(StraightHelper.moveCheck(squares[3][0].getPiece(), 3, 5));
    }

    @Test
    void moveFigureAhead() {
        squares[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        squares[3][1].setPiece(new Rook(board, BLACK, 3, 1));
        squares[4][0].setPiece(new Rook(board, BLACK, 4, 0));

        Assertions.assertFalse(StraightHelper.moveCheck(squares[3][0].getPiece(), 3, 7));
        Assertions.assertFalse(StraightHelper.moveCheck(squares[3][0].getPiece(), 5, 0));
    }

    @Test
   void dontMove() {
        squares[4][0].setPiece(new Rook(board, WHITE, 4, 0));

        Assertions.assertFalse(StraightHelper.moveCheck(squares[4][0].getPiece(), 4, 0));
    }

    @Test
    void cantMoveAnyWhere() {
        Assertions.assertFalse(DiagonalHelper.canMoveAnywhere(squares[0][0].getPiece()));
        Assertions.assertFalse(DiagonalHelper.canMoveAnywhere(squares[0][7].getPiece()));
        Assertions.assertFalse(DiagonalHelper.canMoveAnywhere(squares[7][0].getPiece()));
        Assertions.assertFalse(DiagonalHelper.canMoveAnywhere(squares[7][7].getPiece()));
    }

    @Test
    void canMoveSomeWhere() {
        squares[1][0].removePiece();
        squares[6][0].removePiece();

        Assertions.assertTrue(StraightHelper.canMoveAnywhere(squares[0][0].getPiece()));
        Assertions.assertTrue(StraightHelper.canMoveAnywhere(squares[7][0].getPiece()));

        squares[1][0].setPiece(new Pawn(board, BLACK, 1, 0));
        squares[6][0].setPiece(new Pawn(board, WHITE, 6, 0));
        squares[0][1].removePiece();
        squares[7][1].removePiece();

        Assertions.assertTrue(StraightHelper.canMoveAnywhere(squares[0][0].getPiece()));
        Assertions.assertTrue(StraightHelper.canMoveAnywhere(squares[7][0].getPiece()));
    }
}