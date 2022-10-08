package game.board;

import game.piece.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;

class BoardTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

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
    void pieceCanBeTaken() {
        squares[2][1].setPiece(new Knight(board, WHITE, 2, 1));
        board.getPieceSet(WHITE).add(squares[2][1].getPiece());

        Assertions.assertTrue(board.pieceCanBeTakenAt(0, 0, BLACK));
        Assertions.assertTrue(board.pieceCanBeTakenAt(0, 2, BLACK));
        Assertions.assertTrue(board.pieceCanBeTakenAt(2, 1, WHITE));
    }

    @Test
    void pieceCantBeTaken() {
        Assertions.assertFalse(board.pieceCanBeTakenAt(0, 0, WHITE));
        Assertions.assertFalse(board.pieceCanBeTakenAt(0, 2, WHITE));
        Assertions.assertFalse(board.pieceCanBeTakenAt(7, 1, BLACK));
    }

    @Test
    void kingCanBeTaken() {
        squares[2][3].setPiece(new Knight(board, WHITE, 2, 3));
        board.getPieceSet(WHITE).add(squares[2][3].getPiece());

        Assertions.assertFalse(board.kingCantBetaken(BLACK));

        squares[3][0].setPiece(new Queen(board, BLACK, 3, 0));
        board.getPieceSet(BLACK).add(squares[3][0].getPiece());
        board.getPieceSet(WHITE).remove(squares[6][3].getPiece());
        squares[6][3].removePiece();

        Assertions.assertFalse(board.kingCantBetaken(WHITE));
    }

    @Test
    void kingCantBeTaken() {
        Assertions.assertTrue(board.kingCantBetaken(WHITE));
        Assertions.assertTrue(board.kingCantBetaken(BLACK));
    }

    @Test
    void boardCopy() {
        squares[1][3].getPiece().move(2, 3);
        squares[1][4].getPiece().move(3, 4);
        squares[6][0].getPiece().move(4, 0);
        squares[0][1].getPiece().move(2, 2);

        Board boardCopy = new Board(squares, new int[]{7, 4}, new int[]{0, 4});

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Assertions.assertEquals(squares[i][j].getClass(), boardCopy.getSquares()[i][j].getClass());
            }
        }
    }

    @Test
    void kingHasXray() {
        squares[0][0].setPiece(new King(board, WHITE, 0, 0));
        squares[1][0].setPiece(new Queen(board, WHITE, 1, 0));
        squares[2][0].setPiece(new Rook(board, BLACK, 2, 0));
        board.getPieceSet(WHITE).add(squares[0][0].getPiece());
        board.getPieceSet(WHITE).add(squares[1][0].getPiece());
        board.getPieceSet(BLACK).add(squares[2][0].getPiece());
        board.setKingCoord(WHITE, 0 , 0);

        Assertions.assertFalse(board.moveIsValid(1, 0, 1, 1));

        squares[1][1].setPiece(new Queen(board, WHITE, 1, 1));
        squares[2][2].setPiece(new Bishop(board, BLACK, 2, 2));
        board.getPieceSet(WHITE).add(squares[1][1].getPiece());
        board.getPieceSet(BLACK).add(squares[2][2].getPiece());

        Assertions.assertFalse(board.moveIsValid(1, 1, 0, 1));
    }

    @Test
    void moveIsValid() {
        Assertions.assertTrue(board.moveIsValid(1, 0, 2, 0));
        Assertions.assertTrue(board.moveIsValid(0, 1, 2, 2));
    }

    @Test
    void moveKingToDeath() {
        squares[3][0].setPiece(new King(board, WHITE, 3, 0));
        board.getPieceSet(WHITE).add(squares[3][0].getPiece());
        board.setKingCoord(WHITE, 3 , 0);

        Assertions.assertFalse(board.moveIsValid(3, 0, 2, 0));
    }

    @Test
    void promotePawnToBishop() {
        board.setPawnToPromote((Pawn) squares[1][0].getPiece());
        board.promotePiece(0);

        Assertions.assertTrue(squares[1][0].getPiece() instanceof Bishop);
        Assertions.assertEquals(BLACK, squares[1][0].getPiece().getColor());
    }

    @Test
    void promotePawnToKnight() {
        board.setPawnToPromote((Pawn) squares[1][0].getPiece());
        board.promotePiece(1);

        Assertions.assertTrue(squares[1][0].getPiece() instanceof Knight);
        Assertions.assertEquals(BLACK, squares[1][0].getPiece().getColor());
    }

    @Test
    void promotePawnToRook() {
        board.setPawnToPromote((Pawn) squares[1][0].getPiece());
        board.promotePiece(2);

        Assertions.assertTrue(squares[1][0].getPiece() instanceof Rook);
        Assertions.assertEquals(BLACK, squares[1][0].getPiece().getColor());
    }

    @Test
    void promotePawnToQueen() {
        board.setPawnToPromote((Pawn) squares[1][0].getPiece());
        board.promotePiece(3);

        Assertions.assertTrue(squares[1][0].getPiece() instanceof Queen);
        Assertions.assertEquals(BLACK, squares[1][0].getPiece().getColor());
    }
}