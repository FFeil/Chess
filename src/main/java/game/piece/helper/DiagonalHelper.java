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
        int biggerX;
        int smallerX;
        int smallerY;

        if (newX > piece.getX()) {
            biggerX = newX;
            smallerX = piece.getX() + 1;
        } else {
            biggerX = piece.getX();
            smallerX = newX + 1;
        }
        if (newY > piece.getY()) {
            smallerY = piece.getY() + 1;
        } else {
            smallerY = newY + 1;
        }

        for (int i = smallerX; i < biggerX; i++) {
            if (!piece.getBoard().getSquares()[i][smallerY].isEmpty()) {
                return false;
            }
            smallerY++;
        }

        return true;
    }

    public static boolean canMoveAnywhere(Piece piece) {
        for (int i = 0; i < 8; i++) {
            if (piece.canMoveTo(i, i)) {
                return true;
            }
        }

        int j = 0;
        for (int i = 7; i > 0; i--) {
            if (piece.canMoveTo(i, j)) {
                return true;
            }
            j++;
        }
        return false;
    }
}
