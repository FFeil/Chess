package game.piece;

import game.Square;

import java.util.ArrayList;

public class StraightHelper {

    public static boolean moveCheck(Piece piece, int newX, int newY) {
        if (piece instanceof Queen || piece instanceof Rook) {
            Square square = piece.getBoard().getSquares()[newX][newY];

            if ((square.isEmpty()) || (square.containsPieceOfOtherColor(piece.getColor()))) {
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

        ArrayList<Square> column = new ArrayList<>();

        for (int i = smaller; i < bigger; i++) {
            column.add(piece.getBoard().getSquares()[i][piece.getY()]);
        }

        return column.stream().allMatch(Square::isEmpty);
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
        }
        return true;
    }
}
