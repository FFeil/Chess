package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;

class PawnTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void move() {
        Assertions.assertTrue(squares[1][1].getPiece() instanceof Pawn);
        squares[1][1].getPiece().move(2, 1);
        Assertions.assertTrue(squares[1][1].isEmpty());
        Assertions.assertTrue(squares[2][1].getPiece() instanceof Pawn);
    }

    @Test
    void capture() {
        squares[2][2].setPiece(new Pawn(board, WHITE, 2, 2));

        squares[1][1].getPiece().move(2, 2);
        Assertions.assertTrue(squares[1][1].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Pawn);
    }

    @Test
    void captureSameColor() {
        squares[2][2].setPiece(new Queen(board, BLACK, 2, 2));

        Assertions.assertFalse(squares[1][1].getPiece().canMoveTo(2, 2));
    }

    @Test
    void captureNoPiece() {
        Assertions.assertFalse(squares[1][1].getPiece().canMoveTo(2, 2));
    }

    @Test
    void move1ForwardPieceAhead() {
        Assertions.assertTrue(squares[1][1].getPiece() instanceof Pawn);

        squares[2][1].setPiece(new King(board, WHITE, 2, 1));

        Assertions.assertFalse(squares[1][1].getPiece().canMoveTo(2, 1));
    }

    @Test
    void moveBackwards() {
        //Black
        squares[1][1].getPiece().move(3, 1);
        Assertions.assertTrue(squares[3][1].getPiece() instanceof Pawn);
        Assertions.assertFalse(squares[3][1].getPiece().canMoveTo(2, 1));

        // White
        squares[6][1].getPiece().move(4, 1);
        Assertions.assertTrue(squares[4][1].getPiece() instanceof Pawn);
        Assertions.assertFalse(squares[4][1].getPiece().canMoveTo(3, 1));
    }

    @Test
    void move2Forward() {
        //Black
        Assertions.assertTrue(squares[1][1].getPiece() instanceof Pawn);
        squares[1][1].getPiece().move(3, 1);
        Assertions.assertTrue(squares[1][1].isEmpty());
        Assertions.assertTrue(squares[3][1].getPiece() instanceof Pawn);

        // White
        Assertions.assertTrue(squares[6][6].getPiece() instanceof Pawn);
        squares[6][6].getPiece().move(4, 6);
        Assertions.assertTrue(squares[6][6].isEmpty());
        Assertions.assertTrue(squares[4][6].getPiece() instanceof Pawn);
    }

    @Test
    void move2ForwardFigureAhead() {
        Assertions.assertTrue(squares[1][1].getPiece() instanceof Pawn);

        squares[2][1].setPiece(new King(board, WHITE, 2, 1));

        Assertions.assertFalse(squares[2][1].isEmpty());
        Assertions.assertFalse(squares[1][1].getPiece().canMoveTo(3, 1));
    }

    @Test
    void dontMove() {
        Assertions.assertFalse(squares[1][1].getPiece().canMoveTo(1, 1));
    }

    @Test
    void enPasant() {
        // White
        squares[6][3].getPiece().move(4, 3);
        squares[4][3].getPiece().move(3, 3);
        squares[1][2].getPiece().move(3, 2);

        board.getSquaresToUpdate().clear();
        squares[3][3].getPiece().move(2, 2);

        Assertions.assertTrue(squares[3][3].isEmpty());
        Assertions.assertTrue(squares[2][2].getPiece() instanceof Pawn);
        Assertions.assertEquals(WHITE, squares[2][2].getPiece().getColor());
        Assertions.assertTrue(squares[3][2].isEmpty());
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{3, 3})));
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{2, 2})));
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{3, 2})));

        // Black
        squares[1][3].getPiece().move(3, 3);
        squares[3][3].getPiece().move(4, 3);
        squares[6][2].getPiece().move(4, 2);

        board.getSquaresToUpdate().clear();
        squares[4][3].getPiece().move(5, 2);

        Assertions.assertTrue(squares[4][3].isEmpty());
        Assertions.assertTrue(squares[5][2].getPiece() instanceof Pawn);
        Assertions.assertEquals(BLACK, squares[5][2].getPiece().getColor());
        Assertions.assertTrue(squares[4][2].isEmpty());
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{4, 3})));
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{5, 2})));
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{4, 2})));
    }

    @Test
    void enPasantFail() {
        squares[6][3].getPiece().move(4, 3);
        squares[4][3].getPiece().move(3, 3);
        squares[1][2].getPiece().move(2, 2);
        squares[2][2].getPiece().move(3, 2);

        Assertions.assertFalse(squares[3][3].getPiece().canMoveTo(2, 2));
        Assertions.assertFalse(squares[2][2].getPiece() instanceof Pawn);

    }

    @Test
    void cantMoveAnyWhere() {
        squares[2][1].setPiece(new Pawn(board, WHITE, 2, 1));
        squares[5][1].setPiece(new Pawn(board, WHITE, 5, 1));

        Assertions.assertFalse(squares[1][1].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[6][1].getPiece().canMoveAnywhere());
    }

    @Test
    void canMoveSomeWhere() {
        Assertions.assertTrue(squares[1][1].getPiece().canMoveAnywhere());
        Assertions.assertTrue(squares[6][1].getPiece().canMoveAnywhere());

        squares[2][1].setPiece(new Pawn(board, WHITE, 2, 1));
        squares[2][2].setPiece(new Pawn(board, WHITE, 2, 1));

        Assertions.assertTrue(squares[1][1].getPiece().canMoveAnywhere());
    }
}

