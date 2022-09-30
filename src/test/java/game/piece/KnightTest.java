package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

class KnightTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        Assertions.assertTrue(squares[0][1].getPiece() instanceof Knight);
        Assertions.assertTrue(squares[0][1].getPiece().move(2, 2));
        Assertions.assertTrue(squares[0][1].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Knight);

        Assertions.assertTrue(squares[2][2].getPiece().move(0, 1));
        Assertions.assertTrue(squares[2][2].isEmpty());
        Assertions.assertTrue(squares[0][1].getPiece() instanceof Knight);
    }

    @Test
    void capture() {
        squares[2][2].setPiece(new Queen(board, WHITE, 2, 2));

        Assertions.assertTrue(squares[0][1].getPiece().move(2, 2));
        Assertions.assertTrue(squares[0][1].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Knight);

    }

    @Test
    void movePieceOfSameColor() {
        squares[2][2].setPiece(new Queen(board, BLACK, 2, 2));

        Assertions.assertFalse(squares[0][1].getPiece().move(2, 2));
        Assertions.assertTrue(squares[0][1].getPiece() instanceof Knight);
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Queen);
    }

    @Test
    public void dontMove() {
        Assertions.assertFalse(squares[0][1].getPiece().move(0, 1));
    }
}