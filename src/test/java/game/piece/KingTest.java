package game.piece;

import game.board.Board;
import game.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class KingTest {

    private final Board board = new Board();
    private final Square[][] squares = board.getSquares();

    private void clearBoard() {
        for (Square[] squareArray : squares) {
            for (Square square : squareArray) {
                square.setPiece(null);
            }
        }
        board.getPieceSet(WHITE).clear();
        board.getPieceSet(BLACK).clear();
    }

    @Test
    void move() {
        squares[4][4].setPiece(new King(board, WHITE, 4, 4));
        board.setKingCoord(WHITE, 4, 4);
        board.setKingCoord(BLACK, 0, 4);

        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);

        Assertions.assertTrue(squares[4][4].getPiece().move(5, 4));
        Assertions.assertTrue(squares[4][4].isEmpty());
        Assertions.assertTrue(squares[5][4].getPiece() instanceof King);

        Assertions.assertTrue(squares[5][4].getPiece().move(4, 4));
        Assertions.assertTrue(squares[5][4].isEmpty());
        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);
    }

    @Test
    void dontMove() {
        squares[4][4].setPiece(new King(board, WHITE, 4, 4));

        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);

        Assertions.assertFalse(squares[4][4].getPiece().move(4, 4));
        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);
    }

    @Test
    void capture() {
        squares[4][4].setPiece(new King(board, WHITE, 4, 4));
        squares[4][5].setPiece(new Pawn(board, BLACK, 4, 5));
        board.setKingCoord(WHITE, 4, 4);
        board.setKingCoord(BLACK, 0, 4);

        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[4][5].getPiece() instanceof Pawn);

        Assertions.assertTrue(squares[4][4].getPiece().move(4, 5));
        Assertions.assertTrue(squares[4][5].getPiece() instanceof King);
    }

    @Test
    void moveToSameColorPiece() {
        squares[4][4].setPiece(new King(board, WHITE, 4, 4));
        squares[4][5].setPiece(new Pawn(board, WHITE, 4, 5));
        board.setKingCoord(WHITE, 4, 4);
        board.setKingCoord(BLACK, 0, 4);

        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[4][5].getPiece() instanceof Pawn);

        Assertions.assertFalse(squares[4][4].getPiece().move(4, 5));
        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[4][5].getPiece() instanceof Pawn);
    }

    @Test
    void moveToSquareWithVision() {
        squares[4][4].setPiece(new King(board, WHITE, 4, 4));
        squares[6][5].setPiece(new Queen(board, BLACK, 6, 5));
        board.setKingCoord(WHITE, 4, 4);
        board.setKingCoord(BLACK, 0, 4);
        squares[4][4].setPiece(new King(board, BLACK, 0, 4));

        Assertions.assertFalse(squares[4][4].getPiece().move(4, 5));
        Assertions.assertTrue(squares[4][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[4][5].isEmpty());
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
        squares[5][5].setPiece(new Rook(board, BLACK, 5, 5));
        board.getPieceSet(WHITE).add(squares[7][4].getPiece());
        board.getPieceSet(WHITE).add(squares[7][7].getPiece());
        board.getPieceSet(BLACK).add(squares[5][5].getPiece());

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

    @Test
    void castlingWhileInCheck() {
        clearBoard();
        squares[7][4].setPiece(new King(board, WHITE, 7, 4));
        squares[7][7].setPiece(new Rook(board, WHITE, 7, 7));
        squares[4][4].setPiece(new Queen(board, BLACK, 7, 4));
        board.getPieceSet(WHITE).add(squares[7][4].getPiece());
        board.getPieceSet(WHITE).add(squares[7][7].getPiece());
        board.getPieceSet(BLACK).add(squares[7][4].getPiece());
        board.setKingCoord(WHITE, 7, 4);
        board.setKingCoord(BLACK, 0, 4);
        squares[7][4].setPiece(new King(board, WHITE, 0, 4));

        Assertions.assertFalse(squares[7][4].getPiece().move(7, 7));
        Assertions.assertTrue(squares[7][4].getPiece() instanceof King);
        Assertions.assertTrue(squares[7][7].getPiece() instanceof Rook);
        Assertions.assertTrue(squares[4][4].getPiece() instanceof Queen);
    }

    @Test
    void cantMoveAnyWhere() {
        Assertions.assertFalse(squares[0][4].getPiece().canMoveAnywhere());
        Assertions.assertFalse(squares[7][4].getPiece().canMoveAnywhere());
    }

    @Test
    void canMoveSomeWhere() {
        squares[1][5].removePiece();
        squares[7][3].removePiece();

        Assertions.assertTrue(squares[0][4].getPiece().canMoveAnywhere());
        Assertions.assertTrue(squares[7][4].getPiece().canMoveAnywhere());
    }
}