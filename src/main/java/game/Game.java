package game;

import game.board.Board;
import game.board.Square;
import game.piece.*;

import java.util.ArrayList;
import java.util.Objects;

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
        ArrayList<Integer[]> squaresToUpdate = new ArrayList<>();
        Square oldSquare = board.getSquares()[oldX][oldY];

        if (!oldSquare.isEmpty()) {
            if (currentPlayer == oldSquare.getPiece().getColor() && oldSquare.getPiece().move(newX, newY)) {
                switchPlayer();

                squaresToUpdate.add(new Integer[]{oldX, oldY});
                squaresToUpdate.add(new Integer[]{newX, newY});
                squaresToUpdate.add(board.getOldRookCoord());
                squaresToUpdate.add(board.getNewRookCoord());
                squaresToUpdate.removeIf(Objects::isNull);
            }
        }
        return squaresToUpdate;
    }

    public boolean checkEnd() {
        return checkCheckMate() || checkDraw();
    }

    public boolean checkCheckMate() {
        int x =  board.getKingCoord(currentPlayer)[0];
        int y =  board.getKingCoord(currentPlayer)[1];

        return board.pieceCanBeTakenAt(x, y, currentPlayer)
                && !board.getSquares()[x][y].getPiece().canMoveAnywhere();
    }

    public boolean checkDraw() {
        return checkStalemate() || checkInsufficientMaterial() || check50MoveRule() || checkRepetition();
    }

    public boolean checkStalemate() {
        return board.getPieceSet(nextPlayer).stream().anyMatch(Piece::canMoveAnywhere);
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
}
