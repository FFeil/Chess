package game.piece.helper;

import game.board.Board;
import game.board.Square;
import game.piece.Bishop;
import game.piece.helper.DiagonalHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class DiagonalHelperTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        squares[4][4].setPiece(new Bishop(board, WHITE, 4, 4));

        Assertions.assertTrue(DiagonalHelper.moveCheck(squares[4][4].getPiece(), 5, 5));
        Assertions.assertTrue(DiagonalHelper.moveCheck(squares[4][4].getPiece(), 2, 2));
        Assertions.assertTrue(DiagonalHelper.moveCheck(squares[4][4].getPiece(), 2, 6));
        Assertions.assertTrue(DiagonalHelper.moveCheck(squares[4][4].getPiece(), 5, 3));
    }

    @Test
    void capture() {
        squares[4][4].setPiece(new Bishop(board, WHITE, 4, 4));
        squares[5][5].setPiece(new Bishop(board, BLACK, 5, 5));
        squares[5][3].setPiece(new Bishop(board, BLACK, 5, 3));

        Assertions.assertTrue(DiagonalHelper.moveCheck(squares[4][4].getPiece(), 5, 5));
        Assertions.assertTrue(DiagonalHelper.moveCheck(squares[4][4].getPiece(), 5, 3));
    }

    @Test
    void captureSameColor() {
        squares[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        squares[2][2].setPiece(new Bishop(board, WHITE, 2, 2));

        Assertions.assertFalse(DiagonalHelper.moveCheck(squares[3][3].getPiece(), 2, 2));
    }

    @Test
    void moveFigureAhead() {
        squares[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        squares[4][4].setPiece(new Bishop(board, BLACK, 4, 4));

        Assertions.assertFalse(DiagonalHelper.moveCheck(squares[3][3].getPiece(), 5, 5));
    }

    @Test
    void dontMove() {
        squares[4][0].setPiece(new Bishop(board, WHITE, 4, 0));

        Assertions.assertFalse(DiagonalHelper.moveCheck(squares[4][0].getPiece(), 4, 0));
    }

    @Test
    void cantMoveAnyWhere() {
        Assertions.assertFalse(DiagonalHelper.canMoveAnywhere(squares[0][3].getPiece()));
        Assertions.assertFalse(DiagonalHelper.canMoveAnywhere(squares[7][2].getPiece()));
    }

    @Test
    void canMoveSomeWhere() {
        squares[1][3].removePiece();
        squares[1][6].removePiece();

        Assertions.assertFalse(squares[0][0].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[0][7].getPiece().canMoveAnywhere());
    }
}