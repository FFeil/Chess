package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class QueenTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void moveStraight() {
        squares[4][4].setPiece(new Queen(board, WHITE, 4, 4));

        Assertions.assertTrue(squares[4][4].getPiece() instanceof Queen);

        squares[4][4].getPiece().move(4, 0);
        Assertions.assertTrue(squares[4][4].isEmpty());
        Assertions.assertTrue(squares[4][0].getPiece() instanceof Queen);

        squares[4][0].getPiece().move(2, 0);
        Assertions.assertTrue(squares[4][0].isEmpty());
        Assertions.assertTrue(squares[2][0].getPiece() instanceof Queen);
    }

    @Test
    void moveDiagonal() {
        squares[4][4].setPiece(new Queen(board, WHITE, 4, 4));

        squares[4][4].getPiece().move(2, 2);
        Assertions.assertTrue(squares[4][4].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Queen);

        squares[2][2].getPiece().move(5, 5);
        Assertions.assertTrue(squares[2][2].isEmpty());
        Assertions.assertTrue(squares[5][5].getPiece() instanceof Queen);
    }

    @Test
    void capture() {
        squares[3][0].setPiece(new Queen(board, WHITE, 3, 0));
        squares[3][5].setPiece(new Rook(board, BLACK, 3, 5));
        squares[2][6].setPiece(new Rook(board, BLACK, 2, 6));

        squares[3][0].getPiece().move(3, 5);
        Assertions.assertTrue(squares[3][5].getPiece() instanceof Queen);
        Assertions.assertTrue(squares[3][0].isEmpty());

        squares[3][5].getPiece().move(2, 6);
        Assertions.assertTrue(squares[2][6].getPiece() instanceof Queen);
        Assertions.assertTrue(squares[3][5].isEmpty());
    }

    @Test
    void captureSameColor() {
        squares[3][0].setPiece(new Queen(board, WHITE, 3, 0));
        squares[3][1].setPiece(new Bishop(board, WHITE, 3, 1));

        Assertions.assertFalse(squares[3][0].getPiece().canMoveTo(3, 1));
    }

    @Test
    void moveFigureAhead() {
        squares[3][0].setPiece(new Queen(board, WHITE, 3, 0));
        squares[3][1].setPiece(new Rook(board, BLACK, 3, 1));

        Assertions.assertFalse(squares[3][0].getPiece().canMoveTo(3, 2));
    }

    @Test
    void dontMove() {
        squares[4][0].setPiece(new Queen(board, WHITE, 4, 0));

        Assertions.assertFalse(squares[4][0].getPiece().canMoveTo(4, 0));
    }

    @Test
    void moveNotStraightOrDiagonal() {
        squares[2][3].setPiece(new Queen(board, WHITE, 2, 3));

        Assertions.assertFalse(squares[2][3].getPiece().canMoveTo(4, 4));
    }

    @Test
    void cantMoveAnyWhere() {
        Assertions.assertFalse(squares[0][3].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[7][3].getPiece().canMoveAnywhere());
    }

    @Test
    void canMoveSomeWhere() {
        squares[0][4].removePiece();
        squares[6][2].removePiece();
    }
}