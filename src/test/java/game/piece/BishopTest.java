package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class BishopTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        squares[4][4].setPiece(new Bishop(board, WHITE, 4, 4));

        Assertions.assertTrue(squares[4][4].getPiece() instanceof Bishop);

        Assertions.assertTrue(squares[4][4].getPiece().move(3, 3));
        Assertions.assertTrue(squares[4][4].isEmpty());
        Assertions.assertTrue(squares[3][3].getPiece() instanceof Bishop);

        Assertions.assertTrue(squares[3][3].getPiece().move(2, 4));
        Assertions.assertTrue(squares[3][3].isEmpty());
        Assertions.assertTrue(squares[2][4].getPiece() instanceof Bishop);

        Assertions.assertTrue(squares[2][4].getPiece().move(3, 3));
        Assertions.assertTrue(squares[2][4].isEmpty());
        Assertions.assertTrue(squares[3][3].getPiece() instanceof Bishop);
    }

    @Test
    void capture() {
        squares[4][4].setPiece(new Bishop(board, WHITE, 4, 4));
        squares[5][5].setPiece(new Bishop(board, BLACK, 5, 5));

        Assertions.assertTrue(squares[4][4].getPiece().move(5, 5));
        Assertions.assertTrue(squares[5][5].getPiece() instanceof Bishop);
    }

    @Test
    void captureSameColor() {
        squares[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        squares[2][2].setPiece(new Pawn(board, WHITE, 2, 2));

        Assertions.assertFalse(squares[3][3].getPiece().canMoveTo(2, 2));
    }

    @Test
    void moveFigureAhead() {
        squares[3][3].setPiece(new Bishop(board, WHITE, 3, 3));
        squares[4][4].setPiece(new Pawn(board, BLACK, 4, 4));

        Assertions.assertFalse(squares[3][3].getPiece().canMoveTo(5, 5));
    }

    @Test
    void dontMove() {
        squares[4][0].setPiece(new Bishop(board, WHITE, 4, 0));

        Assertions.assertFalse(squares[4][0].getPiece().canMoveTo(4, 0));
    }

    @Test
    void moveNotDiagonal() {
        squares[4][4].setPiece(new Bishop(board, WHITE, 4, 4));

        Assertions.assertFalse(squares[4][4].getPiece().canMoveTo(4, 5));
    }

    @Test
    void cantMoveAnyWhere() {
        Assertions.assertFalse(squares[0][2].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[0][5].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[7][2].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[7][5].getPiece().canMoveAnywhere());
    }

    @Test
    void canMoveSomeWhere() {
        squares[1][3].removePiece();
        squares[1][6].removePiece();

        Assertions.assertFalse(squares[0][0].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[0][7].getPiece().canMoveAnywhere());
    }
}