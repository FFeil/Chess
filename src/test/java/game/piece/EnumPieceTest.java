package game.piece;


import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnumPieceTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void changePosition() {
        Assertions.assertFalse(squares[1][2].isEmpty());
        Assertions.assertEquals(1, squares[1][2].getPiece().getX());
        Assertions.assertEquals(2, squares[1][2].getPiece().getY());
        Assertions.assertTrue(squares[1][2].getPiece() instanceof Pawn);

        squares[1][2].getPiece().changePosition(3, 4);

        Assertions.assertTrue(squares[1][2].isEmpty());
        Assertions.assertFalse(squares[3][4].isEmpty());
        Assertions.assertEquals(3, squares[3][4].getPiece().getX());
        Assertions.assertEquals(4, squares[3][4].getPiece().getY());
        Assertions.assertTrue(squares[3][4].getPiece() instanceof Pawn);
    }
}