package game;

import game.board.Board;
import game.board.Square;
import game.piece.*;
import game.piece.Color;

import java.util.ArrayList;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Game {

    private final Board board;
    private Color currentPlayer;
    private Color nextPlayer;

    public Game() {
        board = new Board();
        currentPlayer = Color.WHITE;
        nextPlayer = Color.BLACK;
    }

    public Board getBoard() {
        return board;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Color getNextPlayer() {
        return nextPlayer;
    }

    public void switchPlayer() {
        Color tmp = nextPlayer;
        nextPlayer = currentPlayer;
        currentPlayer = tmp;
    }

    public ArrayList<Integer[]> makeMove(int oldX, int oldY, int newX, int newY) {
        board.getSquaresToUpdate().clear();
        Square oldSquare = board.getSquares()[oldX][oldY];

        if (!oldSquare.isEmpty()) {
            Piece currentPiece = oldSquare.getPiece();

            if (currentPlayer == currentPiece.getColor()) {
                if (moveIsValid(oldX, oldY, newX, newY)) {
                  // for (int i = 0; i < 8; i++) {
                  //     for (int j = 0; j < 8; j++) {
                  //         System.out.print(board.getSquares()[i][j].getPiece() + ", ");
                  //         }
                  //     System.out.println();
                  //     }
                  // System.out.println();

                    currentPiece.move(newX, newY);
                    switchPlayer();
                }
            }
        }

        return board.getSquaresToUpdate();
    }

    public boolean checkEnd() {
        return checkCheckMate() || checkDraw();
    }

    public boolean checkCheckMate() {
        int x = board.getKingCoord(currentPlayer)[0];
        int y = board.getKingCoord(currentPlayer)[1];

        return board.pieceCanBeTakenAt(x, y, currentPlayer)
                && !board.getSquares()[x][y].getPiece().canMoveAnywhere();
    }

    public boolean checkDraw() {
        return checkStalemate() || checkInsufficientMaterial() || check50MoveRule() || checkRepetition();
    }

    public boolean checkStalemate() {
        return board.getPieceSet(nextPlayer).stream().noneMatch(Piece::canMoveAnywhere);
    }

    public boolean checkInsufficientMaterial() {
        return (board.getPieceSet(WHITE).size() == 1 || board.getPieceSet(BLACK).size() == 1) // only King left
                || checkKingAndMinorPiece();
    }

    public boolean checkKingAndMinorPiece() {
        return (board.getPieceSet(WHITE).size() == 1 && board.getPieceSet(BLACK).size() == 2
                    && board.getPieceSet(BLACK).stream().anyMatch(piece -> piece instanceof King)
                    && board.getPieceSet(BLACK).stream().anyMatch(piece -> (piece instanceof Bishop) || (piece instanceof Knight)))
                || (board.getPieceSet(BLACK).size() == 1 && board.getPieceSet(WHITE).size() == 2
                    && board.getPieceSet(WHITE).stream().anyMatch(piece -> piece instanceof King)
                    && board.getPieceSet(WHITE).stream().anyMatch(piece -> (piece instanceof Bishop) || (piece instanceof Knight)));
    }

    public boolean check50MoveRule() {
        return board.getMoveCount() == 50;
    }

    public boolean checkRepetition() {
        return false;
    }

    private boolean moveIsValid(int oldX, int oldY, int newX, int newY) {
        Board boardCopy = new Board();
        boardCopy.copySquares(board.getSquares());
        boardCopy.setKingCoord(WHITE, board.getKingCoord(WHITE)[0], board.getKingCoord(WHITE)[1]);
        boardCopy.setKingCoord(BLACK, board.getKingCoord(BLACK)[0], board.getKingCoord(BLACK)[1]);

        Piece currentPiece = boardCopy.getSquares()[oldX][oldY].getPiece();

        if (currentPiece.canMoveTo(newX, newY)) {
            currentPiece.move(newX, newY);

        //   for (int i = 0; i < 8; i++) {
        //       for (int j = 0; j < 8; j++) {
        //           System.out.print(boardCopy.getSquares()[i][j].getPiece() + ", ");
        //       }
        //       System.out.println();
        //   }
        //   System.out.println();

            return boardCopy.kingCantBetaken(currentPlayer);
        }

        return false;
    }
}
