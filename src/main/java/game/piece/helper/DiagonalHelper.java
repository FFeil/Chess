package game.piece.helper;

import game.board.Square;
import game.piece.Bishop;
import game.piece.Piece;
import game.piece.Queen;

public class DiagonalHelper {

    public static boolean moveCheck(Piece piece, int newX, int newY) {
        if (piece instanceof Queen || piece instanceof Bishop) {
            Square square = piece.getBoard().getSquares()[newX][newY];

            return piece.getXDistance(newX) == piece.getYDistance(newY) && piece.getXDistance(newX) > 0
                    && (square.isEmpty() || square.containsPieceOfOtherColor(piece.getColor()))
                    && noPiecesDiagonal(piece, newX, newY);
        }

        return false;
    }

    private static boolean noPiecesDiagonal(Piece piece, int newX, int newY) {
        int xStep;
        int yStep;

        if (newX > piece.getX()) {
            xStep = 1;
        } else {
            xStep = -1;
        }
        if (newY > piece.getY()) {
            yStep = 1;
        } else {
            yStep = -1;
        }

        int j = piece.getY() + yStep;
        for (int i = piece.getX() + xStep; piece.getXDistance(i) < piece.getXDistance(newX) ; i+=xStep) {
            if (i * yStep > -1) {
                if (!piece.getBoard().getSquares()[i][j].isEmpty()) {
                    return false;
                }
            }
            j+=yStep;
        }

        return true;
    }

    public static boolean canMoveAnywhere(Piece piece) {
        int j = piece.getX();
        for (int i = 0; i < 8; i++) {
            if (piece.getY() - j > -1 && piece.getY() - j < 8) {
                if (piece.canMoveTo(i, piece.getY() - j)) {
                    return true;
                }
            }
            j++;
        }

        j = piece.getX();
        for (int i = 0; i < 8; i++) {
            if (piece.getY() + j > -1 && piece.getY() + j < 8) {
                if (piece.canMoveTo(i, piece.getY() + j)) {
                    return true;
                }
            }
            j--;
        }
        return false;
    }
}
