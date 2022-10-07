package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class RookTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        squares[4][4].setPiece(new Rook(board, WHITE, 4, 4));

        Assertions.assertTrue(squares[4][4].getPiece() instanceof Rook);

        squares[4][4].getPiece().move(4, 0);
        Assertions.assertTrue(squares[4][4].isEmpty());
        Assertions.assertTrue(squares[4][0].getPiece() instanceof Rook);

        squares[4][0].getPiece().move(2, 0);
        Assertions.assertTrue(squares[4][0].isEmpty());
        Assertions.assertTrue(squares[2][0].getPiece() instanceof Rook);
    }

    @Test
    void capture() {
        squares[4][4].setPiece(new Rook(board, WHITE, 4, 4));
        squares[4][0].setPiece(new Bishop(board, BLACK, 4, 0));
        squares[4][0].setPiece(new Bishop(board, BLACK, 2, 0));

        squares[4][4].getPiece().move(4, 0);
        Assertions.assertTrue(squares[4][0].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[4][4].isEmpty());

        squares[4][0].getPiece().move(2, 0);
        Assertions.assertTrue(squares[2][0].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[4][0].isEmpty());
    }

    @Test
    void captureSameColor() {
        squares[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        squares[3][1].setPiece(new Bishop(board, WHITE, 3, 1));

        Assertions.assertFalse(squares[3][0].getPiece().canMoveTo(3, 1));
    }

    @Test
    void moveFigureAhead() {
        squares[3][0].setPiece(new Rook(board, WHITE, 3, 0));
        squares[3][1].setPiece(new Rook(board, BLACK, 3, 1));
        squares[4][0].setPiece(new Rook(board, BLACK, 4, 0));

        Assertions.assertFalse(squares[3][0].getPiece().canMoveTo(3, 7));
        Assertions.assertFalse(squares[3][0].getPiece().canMoveTo(5, 0));
    }

    @Test
    void dontMove() {
        squares[4][0].setPiece(new Rook(board, WHITE, 4, 0));

        Assertions.assertFalse(squares[4][0].getPiece().canMoveTo(4, 0));
    }

    @Test
    void moveNotStraight() {
        squares[4][0].setPiece(new Rook(board, WHITE, 4, 0));

        Assertions.assertFalse(squares[4][0].getPiece().canMoveTo(5, 4));
    }

    @Test
    void cantMoveAnyWhere() {
        Assertions.assertFalse(squares[0][0].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[0][7].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[7][0].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[7][7].getPiece().canMoveAnywhere());
    }

    @Test
    void canMoveSomeWhere() {
        squares[1][0].removePiece();
        squares[6][0].removePiece();

        Assertions.assertTrue(squares[0][0].getPiece().canMoveAnywhere());
        Assertions.assertTrue(squares[7][0].getPiece().canMoveAnywhere());

        squares[1][0].setPiece(new Pawn(board, BLACK, 1, 0));
        squares[6][0].setPiece(new Pawn(board, WHITE, 6, 0));
        squares[0][1].removePiece();
        squares[7][1].removePiece();

        Assertions.assertTrue(squares[0][0].getPiece().canMoveAnywhere());
        Assertions.assertTrue(squares[7][0].getPiece().canMoveAnywhere());
    }
}