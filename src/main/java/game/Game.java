package game;

import game.board.Board;
import game.board.Square;
import game.piece.*;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;

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
                if (board.moveIsValid(oldX, oldY, newX, newY, currentPlayer)) {
                    if (currentPiece instanceof Pawn || !board.getSquares()[newX][newY].isEmpty()) {
                        board.getBoardConfigs().clear();
                    }

                    currentPiece.move(newX, newY);
                    switchPlayer();

                    board.addCurrenBoardConfig();
                }
            }
        }

        return board.getSquaresToUpdate();
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
        ArrayList<EnumPiece[][]> boardConfigs = board.getBoardConfigs();

        return boardConfigs.stream().anyMatch(config -> {
            int counter = 0;
            for (EnumPiece[][] currenConfig : boardConfigs) {
                if (Arrays.deepEquals(currenConfig, config)) {
                    counter++;
                }
            }
            return counter == 3;
        });
    }
}
