package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static game.piece.enums.Color.BLACK;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void moveToEmptySquare() {
        Assertions.assertEquals(0, board.getSquaresToUpdate().size());

        squares[1][1].getPiece().move(2, 1);

        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{1, 1})));
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{2, 1})));
    }

    @Test
    void capture(){

        squares[1][1].getPiece().move(3, 1);
        squares[3][1].getPiece().move(4, 1);
        squares[4][1].getPiece().move(5, 1);
        board.getSquaresToUpdate().clear();
        Assertions.assertEquals(0, board.getSquaresToUpdate().size());

        Piece capturePiece = squares[6][2].getPiece();
        squares[5][1].getPiece().move(6, 2);

        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{5, 1})));
        Assertions.assertTrue(board.getSquaresToUpdate().stream().anyMatch(x -> Arrays.equals(x, new Integer[]{6, 2})));
        Assertions.assertFalse(board.getPieceSet(BLACK).contains(capturePiece));
    }
}