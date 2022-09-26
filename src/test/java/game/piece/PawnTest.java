package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.WHITE;

public class PawnTest {

    private final Board board = new Board();

    @Test
    void move1Forward() {
        //Black
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[1][1].getPiece().move(2, 1));
        Assertions.assertTrue(board.getSquares()[1][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][1].getPiece() instanceof Pawn);

        // White
        Assertions.assertTrue(board.getSquares()[6][6].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[6][6].getPiece().move(5, 6));
        Assertions.assertTrue(board.getSquares()[6][6].isEmpty());
        Assertions.assertTrue(board.getSquares()[5][6].getPiece() instanceof Pawn);
    }

    @Test
    void move1ForwardUnsuccessful() {
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);

        board.getSquares()[2][1].setPiece(new King(board, WHITE, 2, 1));

        Assertions.assertFalse(board.getSquares()[1][1].getPiece().move(2, 1));
        Assertions.assertFalse(board.getSquares()[1][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[2][1].getPiece() instanceof King);
    }

    @Test
    void move2Forward() {
        //Black
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[1][1].getPiece().move(3, 1));
        Assertions.assertTrue(board.getSquares()[1][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[3][1].getPiece() instanceof Pawn);

        // White
        Assertions.assertTrue(board.getSquares()[6][6].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[6][6].getPiece().move(4, 6));
        Assertions.assertTrue(board.getSquares()[6][6].isEmpty());
        Assertions.assertTrue(board.getSquares()[4][6].getPiece() instanceof Pawn);
    }

    @Test
    void move2ForwardFigureAhead() {
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);

        board.getSquares()[2][1].setPiece(new King(board, WHITE, 2, 1));

        Assertions.assertFalse(board.getSquares()[2][1].isEmpty());
        Assertions.assertFalse(board.getSquares()[1][1].getPiece().move(3, 1));
        Assertions.assertTrue(board.getSquares()[3][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][1].getPiece() instanceof King);
    }
}

