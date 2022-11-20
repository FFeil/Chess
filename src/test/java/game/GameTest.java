package game;

import game.board.Board;
import game.board.Square;
import game.piece.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;

class GameTest {

    private final Game game = new Game();
    private final Board board = game.getBoard();
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
    void makeMove() {
        Assertions.assertEquals(WHITE, game.getCurrentPlayer());

        ArrayList<Integer[]> squaresToUpdate = (ArrayList<Integer[]>) game.makeMove(6, 1, 5 ,1);

        Assertions.assertEquals(2, squaresToUpdate.size());
        Assertions.assertTrue(squaresToUpdate.stream().anyMatch(x -> Arrays.equals(x, new Integer[]{6, 1})));
        Assertions.assertTrue(squaresToUpdate.stream().anyMatch(x -> Arrays.equals(x, new Integer[]{5, 1})));
        Assertions.assertEquals(BLACK, game.getCurrentPlayer());

         squaresToUpdate = (ArrayList<Integer[]>) game.makeMove(1, 1, 2 ,1);

        Assertions.assertEquals(2, squaresToUpdate.size());
        Assertions.assertTrue(squaresToUpdate.stream().anyMatch(x -> Arrays.equals(x, new Integer[]{1, 1})));
        Assertions.assertTrue(squaresToUpdate.stream().anyMatch(x -> Arrays.equals(x, new Integer[]{2, 1})));
        Assertions.assertEquals(WHITE, game.getCurrentPlayer());
    }

    @Test
    void makeMoveWrongPlayer() {
        Assertions.assertEquals(0, game.makeMove(1, 1, 2 ,1).size());
        Assertions.assertTrue(squares[1][1].getPiece() instanceof Pawn);
        Assertions.assertTrue(squares[2][1].isEmpty());
        Assertions.assertEquals(WHITE, game.getCurrentPlayer());
    }

    @Test
    void checkCheckMate() {
        clearBoard();

        squares[0][0].setPiece(new King(board, BLACK, 0, 0));
        squares[7][1].setPiece(new Rook(board, WHITE, 7, 1));
        squares[6][7].setPiece(new Queen(board, WHITE, 6, 7));

        board.setKingCoord(BLACK, 0, 0);
        board.getPieceSet(BLACK).add(squares[0][0].getPiece());
        board.getPieceSet(WHITE).add(squares[7][1].getPiece());
        board.getPieceSet(WHITE).add(squares[6][7].getPiece());

        game.makeMove(6, 7, 6, 0);

        Assertions.assertTrue(game.checkCheckMate());
    }

    @Test
    void noCheckMate() {
        Assertions.assertFalse(game.checkCheckMate());
    }

    @Test
    void checkStalemate() {
        clearBoard();

        squares[0][0].setPiece(new King(board, BLACK, 0, 0));
        squares[2][1].setPiece(new Queen(board, WHITE, 2, 1));
        squares[5][5].setPiece(new Pawn(board, BLACK, 5, 5));
        squares[6][5].setPiece(new Pawn(board, WHITE, 6, 5));

        board.getPieceSet(BLACK).add(squares[0][0].getPiece());
        board.getPieceSet(WHITE).add(squares[2][1].getPiece());
        board.getPieceSet(BLACK).add(squares[5][5].getPiece());
        board.getPieceSet(WHITE).add(squares[6][5].getPiece());

        Assertions.assertTrue(game.checkStalemate());
    }

    @Test
    void noStaleMate() {
        Assertions.assertFalse(game.checkStalemate());
    }

    @Test
    void checkInsufficientMaterialOnlyKing() {
        board.getPieceSet(WHITE).removeIf(piece -> !(piece instanceof King));
        for (Square square : squares[7]) {
            square.removePiece();
        }

        Assertions.assertTrue(game.checkInsufficientMaterial());
    }

    @Test
    void checkInsufficientMaterialKingAndMinorPiece() {
        clearBoard();

        squares[0][0].setPiece(new King(board, WHITE, 0, 0));
        squares[2][1].setPiece(new Bishop(board, WHITE, 2, 1));
        squares[7][6].setPiece(new King(board, BLACK, 7, 6));

        board.getPieceSet(WHITE).add(squares[0][0].getPiece());
        board.getPieceSet(WHITE).add(squares[2][1].getPiece());
        board.getPieceSet(BLACK).add(squares[7][6].getPiece());

        Assertions.assertTrue(game.checkInsufficientMaterial());
    }


    @Test
    void noInsufficientMaterial() {
        clearBoard();

        squares[0][0].setPiece(new King(board, WHITE, 0, 0));
        squares[2][1].setPiece(new Bishop(board, WHITE, 2, 1));
        squares[7][6].setPiece(new King(board, BLACK, 7, 6));
        squares[7][7].setPiece(new Knight(board, BLACK, 7, 7));

        board.getPieceSet(WHITE).add(squares[0][0].getPiece());
        board.getPieceSet(WHITE).add(squares[2][1].getPiece());
        board.getPieceSet(BLACK).add(squares[7][6].getPiece());
        board.getPieceSet(BLACK).add(squares[7][7].getPiece());

        Assertions.assertFalse(game.checkInsufficientMaterial());
    }


    @Test
    void check50MoveRule() {
       game.makeMove(6, 3, 5, 3);
       game.makeMove(1, 3, 2, 3);

        Assertions.assertEquals(0, board.getMoveCount());
        Assertions.assertFalse(game.check50MoveRule());

       while (board.getMoveCount() < 48) {
           game.makeMove(7, 3, 6, 3);
           game.makeMove(0, 3, 1, 3);
           game.makeMove(6, 3, 7, 3);
           game.makeMove(1, 3, 0, 3);
       }
        game.makeMove(7, 3, 6, 3);
        game.makeMove(0, 3, 1, 3);

       Assertions.assertTrue(game.check50MoveRule());
       Assertions.assertEquals(50, board.getMoveCount());
    }

    @Test
    void no50MoveRule() {
        game.makeMove(6, 3, 5, 3);
        game.makeMove(1, 3, 2, 3);

        while (board.getMoveCount() < 49) {
            game.makeMove(7, 3, 6, 3);
            game.makeMove(0, 3, 1, 3);
            game.makeMove(6, 3, 7, 3);
            game.makeMove(1, 3, 0, 3);
        }

        game.makeMove(5, 3, 4, 3);

        Assertions.assertFalse(game.check50MoveRule());
        Assertions.assertEquals(0, board.getMoveCount());
    }

    @Test
    void checkRepetition() {
        game.makeMove(6, 3, 5, 3);
        game.makeMove(1, 3, 2, 3);

        Assertions.assertEquals(0, board.getMoveCount());
        Assertions.assertFalse(game.check50MoveRule());

        while (board.getMoveCount() < 5) {
            game.makeMove(7, 3, 6, 3);
            game.makeMove(0, 3, 1, 3);
            game.makeMove(6, 3, 7, 3);
            game.makeMove(1, 3, 0, 3);
        }

        Assertions.assertTrue(game.checkRepetition());
    }

    @Test
    void noRepetition() {
        game.makeMove(6, 3, 5, 3);
        game.makeMove(1, 3, 2, 3);

        Assertions.assertEquals(0, board.getMoveCount());
        Assertions.assertFalse(game.check50MoveRule());

        while (board.getMoveCount() < 5) {
            game.makeMove(7, 3, 6, 3);
            game.makeMove(0, 3, 1, 3);
            game.makeMove(6, 3, 7, 3);
            game.makeMove(1, 3, 0, 3);
        }

        game.makeMove(5, 3, 4, 3);

        Assertions.assertEquals(1, board.getBoardConfigs().size());
        Assertions.assertFalse(game.checkRepetition());
    }
}