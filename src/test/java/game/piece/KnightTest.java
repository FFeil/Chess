package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;

class KnightTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        Assertions.assertTrue(squares[0][1].getPiece() instanceof Knight);
        squares[0][1].getPiece().move(2, 2);
        Assertions.assertTrue(squares[0][1].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Knight);

        squares[2][2].getPiece().move(0, 1);
        Assertions.assertTrue(squares[2][2].isEmpty());
        Assertions.assertTrue(squares[0][1].getPiece() instanceof Knight);
    }

    @Test
    void capture() {
        squares[2][2].setPiece(new Queen(board, WHITE, 2, 2));

        squares[0][1].getPiece().move(2, 2);
        Assertions.assertTrue(squares[0][1].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Knight);

    }

    @Test
    void movePieceOfSameColor() {
        squares[2][2].setPiece(new Queen(board, BLACK, 2, 2));

        Assertions.assertFalse(squares[0][1].getPiece().canMoveTo(2, 2));

    }

    @Test
    void dontMove() {
        Assertions.assertFalse(squares[0][1].getPiece().canMoveTo(0, 1));
    }
}