package game.piece.helper;

import game.board.Square;
import game.piece.King;
import game.piece.Piece;
import game.piece.Queen;
import game.piece.Rook;

import java.util.ArrayList;
import java.util.Arrays;

public class StraightHelper {

    public static boolean moveCheck(Piece piece, int newX, int newY) {
        if (piece instanceof Queen || piece instanceof Rook || piece instanceof King) {
            Square square = piece.getBoard().getSquares()[newX][newY];

            if (square.isEmpty() || square.containsPieceOfOtherColor(piece.getColor()) || piece instanceof King) {
                if ((newX > piece.getX() || (newX < piece.getX())) && newY == piece.getY()) {
                    return noPiecesVertically(piece, newX);
                } else if ((newY > piece.getY() || newY < piece.getY()) && newX == piece.getX()) {
                    return noPiecesHorizontally(piece, newY);
                }
            }
        }

        return false;
    }

    private static boolean noPiecesVertically(Piece piece, int newX) {
        int bigger;
        int smaller;

        if (newX > piece.getX()) {
            bigger = newX;
            smaller = piece.getX() + 1;
        } else {
            bigger = piece.getX();
            smaller = newX + 1;
        }

        for (int i = smaller; i < bigger; i++) {
            if (!piece.getBoard().getSquares()[i][piece.getY()].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private static boolean noPiecesHorizontally(Piece piece, int newY) {
        int bigger;
        int smaller;

        if (newY > piece.getY()) {
            bigger = newY;
            smaller = piece.getY() + 1;
        } else {
            bigger = piece.getY();
            smaller = newY + 1;
        }

        for (int i = smaller; i < bigger; i++) {
            if (!piece.getBoard().getSquares()[piece.getX()][i].isEmpty()) {

                return false;
            }

            if (piece instanceof King) {
                if (piece.getBoard().pieceCanBeTakenAt(piece.getX(), i, piece.getColor())) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean canMoveAnywhere(Piece piece) {
        for (int i = 0; i < 8; i++) {
            if (piece.canMoveTo(i, piece.getY())) {
                return true;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (piece.canMoveTo(piece.getX(), i)) {
                return true;
            }
        }

        return false;
    }
}
