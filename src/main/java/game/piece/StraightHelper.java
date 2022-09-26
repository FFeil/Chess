package game.piece;

import game.Square;

import java.util.ArrayList;

public class StraightHelper {

    public static boolean move(Piece piece, int newX, int newY) {
        Square square = piece.getBoard().getSquares()[newX][newY];

        if ((square.isEmpty()) || (square.containsPieceOfOtherColor(piece.getColor()))) {
            if ((((newX > piece.getX()) || (newX < piece.getX())) && newY == piece.getY())) {
                return noPiecesVertically(piece, newY);
            } else if (((newY > piece.getY()) || (newY < piece.getY())) && newX == piece.getX()) {
                return noPiecesHorizontally(piece, newX);
            }
        }
        return false;
    }

    private static boolean noPiecesHorizontally(Piece piece, int newX) {
        int bigger;
        int smaller;

        if (newX > piece.getX()) {
            bigger = newX;
            smaller = piece.getX() + 1;
        } else {
            bigger = piece.getX() - 1;
            smaller = newX;
        }
        for (int i = smaller; i < bigger; i++) {
            if (!piece.getBoard().getSquares()[piece.getY()][i].isEmpty()) {
                return false;
            }
        }
        return false;
    }

    private static boolean noPiecesVertically(Piece piece, int newY) {
        int bigger;
        int smaller;

        if (newY > piece.getY()) {
            bigger = newY;
            smaller = piece.getY() + 1;
        } else {
            bigger = piece.getY() - 1;
            smaller = newY;
        }

        ArrayList<Square> column = new ArrayList<>();

        for (int i = 0; i < bigger; i++) {
            column.add(piece.getBoard().getSquares()[i][piece.getX()]);
        }

        for (int i = smaller; i < bigger; i++) {
            if (!column.get(i).isEmpty()) {
                return false;
            }
        }

        return false;
    }
}
