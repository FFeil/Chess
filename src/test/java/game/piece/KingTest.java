package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class KingTest {

    private final Board board = new Board();

    @Test
    public void move() {
        board.getSquares()[4][4].setPiece(new King(board, WHITE, 4, 4));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof King);

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(3, 4));
        Assertions.assertTrue(board.getSquares()[4][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[3][4].getPiece() instanceof King);

        Assertions.assertTrue(board.getSquares()[3][4].getPiece().move(2, 3));
        Assertions.assertTrue(board.getSquares()[3][4].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][3].getPiece() instanceof King);
    }

    @Test
    public void dontMove() {
        board.getSquares()[4][4].setPiece(new King(board, WHITE, 4, 4));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof King);

        Assertions.assertFalse(board.getSquares()[4][4].getPiece().move(4, 4));
        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof King);
    }

    @Test
    public void capture() {
        board.getSquares()[4][4].setPiece(new King(board, WHITE, 4, 4));
        board.getSquares()[4][5].setPiece(new Pawn(board, BLACK, 4, 5));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof King);
        Assertions.assertTrue(board.getSquares()[4][5].getPiece() instanceof Pawn);

        Assertions.assertTrue(board.getSquares()[4][4].getPiece().move(4, 5));
        Assertions.assertTrue(board.getSquares()[4][5].getPiece() instanceof King);
    }

    @Test
    public void moveToSameColorPiece() {
        board.getSquares()[4][4].setPiece(new King(board, WHITE, 4, 4));
        board.getSquares()[4][5].setPiece(new Pawn(board, WHITE, 4, 5));

        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof King);
        Assertions.assertTrue(board.getSquares()[4][5].getPiece() instanceof Pawn);

        Assertions.assertFalse(board.getSquares()[4][4].getPiece().move(4, 5));
        Assertions.assertTrue(board.getSquares()[4][4].getPiece() instanceof King);
        Assertions.assertTrue(board.getSquares()[4][5].getPiece() instanceof Pawn);
    }
}