package game;

import game.piece.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class BoardTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    private void clearBoard() {
        for (Square[] squareArray : squares) {
            for (Square square : squareArray) {
                square.setPiece(null);
            }
        }
    }

    @Test
    void initSquares() {
        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                Assertions.assertNotNull(squares[i][j]);
                Assertions.assertTrue(squares[i][j].isEmpty());
            }
        }
    }

    @Test
    void initPawns() {
        for (Square square : squares[1]) {
            Assertions.assertTrue(square.getPiece() instanceof Pawn);
            Assertions.assertEquals(BLACK, square.getPiece().getColor());
        }

        for (Square square : squares[6]) {
            Assertions.assertTrue(square.getPiece() instanceof Pawn);
            Assertions.assertEquals(WHITE, square.getPiece().getColor());
        }
    }

    @Test
    void initRooks() {
        Assertions.assertTrue(squares[0][0].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[0][7].getPiece() instanceof Rook);
        Assertions.assertEquals(BLACK, squares[0][0].getPiece().getColor());
        Assertions.assertEquals(BLACK, squares[0][7].getPiece().getColor());

        Assertions.assertTrue(squares[7][0].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[7][7].getPiece() instanceof Rook);
        Assertions.assertEquals(WHITE, squares[7][0].getPiece().getColor());
        Assertions.assertEquals(WHITE, squares[7][7].getPiece().getColor());
    }

    @Test
    void initKnights() {
        Assertions.assertTrue(squares[0][1].getPiece() instanceof Knight);
        Assertions.assertTrue(squares[0][6].getPiece() instanceof Knight);
        Assertions.assertEquals(BLACK, squares[0][1].getPiece().getColor());
        Assertions.assertEquals(BLACK, squares[0][6].getPiece().getColor());

        Assertions.assertTrue(squares[7][1].getPiece() instanceof Knight);
        Assertions.assertTrue(squares[7][6].getPiece() instanceof Knight);
        Assertions.assertEquals(WHITE, squares[7][1].getPiece().getColor());
        Assertions.assertEquals(WHITE, squares[7][6].getPiece().getColor());
    }

    @Test
    void initBishops() {
        Assertions.assertTrue(squares[0][2].getPiece() instanceof Bishop);
        Assertions.assertTrue(squares[0][5].getPiece() instanceof Bishop);
        Assertions.assertEquals(BLACK, squares[0][2].getPiece().getColor());
        Assertions.assertEquals(BLACK, squares[0][5].getPiece().getColor());

        Assertions.assertTrue(squares[7][2].getPiece() instanceof Bishop);
        Assertions.assertTrue(squares[7][5].getPiece() instanceof Bishop);
        Assertions.assertEquals(WHITE, squares[7][2].getPiece().getColor());
        Assertions.assertEquals(WHITE, squares[7][5].getPiece().getColor());
    }

    @Test
    void initQueens() {
        Assertions.assertTrue(squares[0][3].getPiece() instanceof Queen);
        Assertions.assertEquals(BLACK, squares[0][3].getPiece().getColor());

        Assertions.assertTrue(squares[7][3].getPiece() instanceof Queen);
        Assertions.assertEquals(WHITE, squares[7][3].getPiece().getColor());
    }

    @Test
    void initKings() {
        Assertions.assertTrue(squares[0][4].getPiece() instanceof King);
        Assertions.assertEquals(BLACK, squares[0][4].getPiece().getColor());

        Assertions.assertTrue(squares[7][4].getPiece() instanceof King);
        Assertions.assertEquals(WHITE, squares[7][4].getPiece().getColor());
    }

    @Test
    void kingHasXray() {
        clearBoard();

        board.setKingCoord(WHITE, 0, 0);

        squares[0][0].setPiece(new King(board, WHITE, 0, 0));
        squares[1][0].setPiece(new Queen(board, WHITE, 1, 0));
        squares[2][0].setPiece(new Rook(board, BLACK, 2, 0));
        Assertions.assertTrue(board.kingHasXray(1, 0, 1, 1, WHITE));

        squares[1][1].setPiece(new Queen(board, WHITE, 1, 1));
        squares[2][2].setPiece(new Bishop(board, BLACK, 2, 2));

        Assertions.assertTrue(board.kingHasXray(1, 1, 0, 1, WHITE));
    }

    @Test
    void kingHasNoXray() {
        Assertions.assertFalse(board.kingHasXray(1, 0, 2, 0,  WHITE));
        Assertions.assertFalse(board.kingHasXray(0, 1, 2, 2,  WHITE));
    }

    @Test
    void castling() {
        clearBoard();
        squares[0][4].setPiece(new King(board, BLACK, 0, 4));
        squares[7][4].setPiece(new King(board, WHITE, 7, 4));
        squares[0][0].setPiece(new Rook(board, BLACK, 0, 0));
        squares[7][7].setPiece(new Rook(board, WHITE, 7, 7));

        Assertions.assertTrue(squares[0][4].getPiece().move(0, 0));
        Assertions.assertTrue(squares[0][2].getPiece() instanceof King);
        Assertions.assertTrue(squares[0][3].getPiece() instanceof Rook);

        Assertions.assertTrue(squares[7][4].getPiece().move(7, 7));
        Assertions.assertTrue(squares[7][6].getPiece() instanceof King);
        Assertions.assertTrue(squares[7][5].getPiece() instanceof Rook);
    }

    @Test
    void blockingCastleWithVision() {
        clearBoard();
        squares[7][4].setPiece(new King(board, WHITE, 7, 4));
        squares[7][7].setPiece(new Rook(board, WHITE, 7, 7));
        squares[5][5].setPiece(new Queen(board, BLACK, 5, 5));

        Assertions.assertFalse(squares[7][4].getPiece().move(7, 7));
        Assertions.assertTrue(squares[7][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[7][7].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[7][6].isEmpty());
        Assertions.assertTrue(squares[7][5].isEmpty());
    }

    @Test
    void blockingCastleWithPiece() {
        clearBoard();
        squares[7][4].setPiece(new King(board, WHITE, 7, 4));
        squares[7][7].setPiece(new Rook(board, WHITE, 7, 7));
        squares[7][5].setPiece(new Queen(board, WHITE, 7, 5));

        Assertions.assertFalse(squares[7][4].getPiece().move(7, 7));
        Assertions.assertTrue(squares[7][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[7][7].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[7][5].getPiece() instanceof Queen);
    }
}