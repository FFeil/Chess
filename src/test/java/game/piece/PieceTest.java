package game.piece;


import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PieceTest {

    private final Board board = new Board();

    @Test
    public void changePosition() {
        Assertions.assertFalse(board.getSquares()[1][2].isEmpty());
        Assertions.assertEquals(1, board.getSquares()[1][2].getPiece().getX());
        Assertions.assertEquals(2, board.getSquares()[1][2].getPiece().getY());
        Assertions.assertTrue(board.getSquares()[1][2].getPiece() instanceof Pawn);

        board.getSquares()[1][2].getPiece().changePosition(3, 4);

        Assertions.assertTrue(board.getSquares()[1][2].isEmpty());
        Assertions.assertFalse(board.getSquares()[3][4].isEmpty());
        Assertions.assertEquals(3, board.getSquares()[3][4].getPiece().getX());
        Assertions.assertEquals(4, board.getSquares()[3][4].getPiece().getY());
        Assertions.assertTrue(board.getSquares()[3][4].getPiece() instanceof Pawn);
    }
}