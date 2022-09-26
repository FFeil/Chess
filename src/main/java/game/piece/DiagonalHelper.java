package game.piece;

import game.Square;

public class DiagonalHelper {

    public static boolean move(Piece piece, int newX, int newY) {
        Square square = piece.getBoard().getSquares()[newX][newY];

        return (piece.getXDistance(newX) == (piece.getYDistance(newY))) && piece.getXDistance(newX) > 0
                && ((square.isEmpty()) || (square.containsPieceOfOtherColor(piece.getColor())))
                && noPiecesDiagonal(piece, newX, newY);
    }

    private static boolean noPiecesDiagonal(Piece piece, int newX, int newY) {
        int biggerX;
        int smallerX;
        //int biggerY;
        int smallerY;

        if (newX > piece.getX()) {
            biggerX = newX;
            smallerX = piece.getX() + 1;
        } else {
            biggerX = piece.getX() - 1;
            smallerX = newX;
        }
        if (newY > piece.getY()) {
            //biggerY = newY;
            smallerY = piece.getY() + 1;
        } else {
            //biggerY = y - 1;
            smallerY = newY;
        }

        for (int i = smallerX; i < biggerX; i++) {
            if (!piece.getBoard().getSquares()[i][smallerY].isEmpty()) {
                return false;
            }
            smallerY++;
        }

        return false;
    }
}
