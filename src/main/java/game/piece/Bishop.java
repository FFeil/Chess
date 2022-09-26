package game.piece;

import game.Board;
import game.Square;

public class Bishop extends Piece {

    public Bishop(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public boolean move(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if ((getXDistance(newX) == (getYDistance(newY))) && getXDistance(newX) > 0
                && ((square.isEmpty()) || (square.containsPieceOfOtherColor(color)))) {
            changePosition(newX, newY);
            return true;
        }

        return false;
    }

    private boolean noPiecesDiagonal(int newX, int newY) {
        int biggerX;
        int smallerX;
        //int biggerY;
        int smallerY;

        if (newX > x) {
            biggerX = newX;
            smallerX = x + 1;
        } else {
            biggerX = x - 1;
            smallerX = newX;
        }
        if (newY > y) {
            //biggerY = newY;
            smallerY = y + 1;
        } else {
            //biggerY = y - 1;
            smallerY = newY;
        }

        for (int i = smallerX; i < biggerX; i++) {
            if (!board.getSquares()[i][smallerY].isEmpty()) {
                return false;
            }
            smallerY++;
        }

        return false;
    }
}
