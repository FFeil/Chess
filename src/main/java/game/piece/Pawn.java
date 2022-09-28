package game.piece;

import game.Board;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Pawn extends Piece {

    private int row;
    private boolean moved2Squares;

    public Pawn(Board board, Color color, int x, int y) {
        super(board, color, x, y);
        row = 1;
        moved2Squares = false;
    }

    public boolean hasMoved2Squares() {
        return moved2Squares;
    }

    @Override
    public boolean move(int newX, int newY) {
        if (canMoveTo(newX, newY) && !board.kingHasXray(getX(), getY(), newX, newY, color)) {
            changePosition(newX, newY);

       //  if (getXDistance(newX) == 1 && getYDistance(newY) == 1
       //          && board.getSquares()[newX - 1][newY].containsPieceOfOtherColor(color))) {
       //      board.getSquares()[newX - 1][newY].removePiece();
       //  }
       //
            if (canMove2Forward(newX, newY)) {
                row+=2;
                moved2Squares = true;
            } else {
                row++;
                moved2Squares = false;
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return canMove1Forward(newX, newY) || canMove2Forward(newX, newY)
                || canMoveDiagonal(newX, newY) ||canDoEnPasant(newX, newY);
    }

    private boolean canMove1Forward(int newX, int newY) {
         return ((newX - x == 1 && color == BLACK ) || (newX - x == -1 && color == WHITE))
                 && y == newY && board.getSquares()[newX][newY].isEmpty();
    }

    private boolean canMove2Forward(int newX, int newY) {
        return getXDistance(newX) == 2 && y == newY && board.getSquares()[newX][newY].isEmpty()
                && ((color == BLACK && board.getSquares()[x + 1][y].isEmpty())
                || ((color == Color.WHITE && board.getSquares()[x - 1][y].isEmpty())))
                && row == 1;
    }

    private boolean canMoveDiagonal(int newX, int newY) {
        return getXDistance(newX) == 1 && getYDistance(newY) == 1
                && board.getSquares()[newX][newY].containsPieceOfOtherColor(color);
    }

    private boolean canDoEnPasant(int newX, int newY) {
        if (!board.getSquares()[newX - 1][newY].isEmpty() && row == 5
                && board.getSquares()[newX - 1][newY].containsPieceOfOtherColor(color)) {
            if ((board.getSquares()[newX + 1][newY].getPiece() instanceof Pawn && color == BLACK)
                || (board.getSquares()[newX - 1][newY].getPiece() instanceof Pawn && color == WHITE)){
                if (((Pawn) board.getSquares()[newX - 1][newY].getPiece()).hasMoved2Squares()) {
                    board.getSquares()[newX - 1][newY].removePiece();
                    return true;
                }
            }
        }

        return false;
    }


    //TODO: en pasant
}
