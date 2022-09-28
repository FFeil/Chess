package game.piece;

import game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class PawnTest {

    private final Board board = new Board();

    @Test
    void move() {
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[1][1].getPiece().move(2, 1));
        Assertions.assertTrue(board.getSquares()[1][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][1].getPiece() instanceof Pawn);
    }

    @Test
    void capture() {
        board.getSquares()[2][2].setPiece(new Pawn(board, WHITE, 2, 2));

        Assertions.assertTrue(board.getSquares()[1][1].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[1][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof Pawn);
    }

    @Test
    void captureSameColor() {
        board.getSquares()[2][2].setPiece(new Queen(board, BLACK, 2, 2));

        Assertions.assertFalse(board.getSquares()[1][1].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[2][2].getPiece() instanceof  Queen);
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
    }

    @Test
    void captureNoPiece() {
        Assertions.assertFalse(board.getSquares()[1][1].getPiece().move(2, 2));
        Assertions.assertTrue(board.getSquares()[2][2].isEmpty());
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
    }

    @Test
    void move1ForwardPieceAhead() {
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);

        board.getSquares()[2][1].setPiece(new King(board, WHITE, 2, 1));

        Assertions.assertFalse(board.getSquares()[1][1].getPiece().move(2, 1));
        Assertions.assertFalse(board.getSquares()[1][1].isEmpty());
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
        Assertions.assertTrue(board.getSquares()[2][1].getPiece() instanceof King);
    }

    @Test
    void moveBackwards() {
        //Black
        Assertions.assertTrue(board.getSquares()[1][1].getPiece().move(3, 1));
        Assertions.assertTrue(board.getSquares()[3][1].getPiece() instanceof Pawn);
        Assertions.assertFalse(board.getSquares()[3][1].getPiece().move(2, 1));

        // White
        Assertions.assertTrue(board.getSquares()[6][1].getPiece().move(4, 1));
        Assertions.assertTrue(board.getSquares()[4][1].getPiece() instanceof Pawn);
        Assertions.assertFalse(board.getSquares()[4][1].getPiece().move(3, 1));
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

    @Test
    public void dontMove() {
        Assertions.assertFalse(board.getSquares()[1][1].getPiece().move(1, 1));
        Assertions.assertTrue(board.getSquares()[1][1].getPiece() instanceof Pawn);
    }
}

