package game.piece.helper;

import game.board.Board;
import game.board.Square;
import game.piece.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;

class CanMoveAnywhereHelperTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    @Test
    void cantMoveAnyWhere() {
        squares[2][1].setPiece(new Pawn(board, WHITE, 2, 1));
        squares[5][1].setPiece(new Pawn(board, WHITE, 5, 1));
        squares[2][0].setPiece(new Pawn(board, BLACK, 2, 0));
        squares[2][2].setPiece(new Pawn(board, BLACK, 2, 2));

        Assertions.assertFalse(CanMoveAnywhereHelper.canMoveAnywhere(squares[1][1].getPiece(), -1));
        Assertions.assertFalse(CanMoveAnywhereHelper.canMoveAnywhere(squares[6][1].getPiece(), -1));

        Assertions.assertFalse(CanMoveAnywhereHelper.canMoveAnywhere(squares[0][4].getPiece(), -1));
        Assertions.assertFalse(CanMoveAnywhereHelper.canMoveAnywhere(squares[7][4].getPiece(), -1));

        Assertions.assertFalse(CanMoveAnywhereHelper.canMoveAnywhere(squares[0][1].getPiece(), -2));
    }

    @Test
    void canMoveSomeWhere() {
        squares[0][3].removePiece();
        squares[6][4].removePiece();

        Assertions.assertTrue(CanMoveAnywhereHelper.canMoveAnywhere(squares[1][1].getPiece(), -1));
        Assertions.assertTrue(CanMoveAnywhereHelper.canMoveAnywhere(squares[6][1].getPiece(), -1));

        Assertions.assertTrue(CanMoveAnywhereHelper.canMoveAnywhere(squares[0][4].getPiece(), -1));
        Assertions.assertTrue(CanMoveAnywhereHelper.canMoveAnywhere(squares[7][4].getPiece(), -1));

        Assertions.assertTrue(CanMoveAnywhereHelper.canMoveAnywhere(squares[0][1].getPiece(), -2));
    }
}