package game.piece;

import game.Board;
import game.Square;

public class King extends Piece {

    private boolean moved;

    public King(Board board, Color color, int x, int y) {
        super(board, color, x, y);
        moved = false;
    }

    @Override
    public boolean move(int newX, int newY) {
        if (canMoveTo(newX, newY)) {
            if (isSameColorRookAt(newX, newY)) {
                if (newY > y) { // short castle -> right
                    board.getSquares()[newX][newY].getPiece().changePosition(x, y + 1);
                    changePosition(x, y + 2);
                } else { // long castle -> left
                    board.getSquares()[newX][newY].getPiece().changePosition(x, y - 1);
                    changePosition(x, y - 2);
                }
            } else {
                changePosition(newX, newY);
            }

            board.setKingCoord(color, newX, newY);
            moved = true;

            return true;
        }

        return false;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        return (getXDistance(newX) + getYDistance(newY) == 1 || (getXDistance(newX) == 1 && getYDistance(newY) == 1 ))
                && (square.containsPieceOfOtherColor(color) || square.isEmpty()) || canCastle(newX, newY);
    }

    private boolean canCastle(int newX, int newY) {
        if (isSameColorRookAt(newX, newY) && !moved) {
            return StraightHelper.moveCheck(this, newX, newY)
                    && !board.pieceCanBeTakenAt(x, y, color);
        }

        return false;
    }

    private boolean isSameColorRookAt(int newX, int newY) {
        if (!board.getSquares()[newX][newY].isEmpty()) {
            if (board.getSquares()[newX][newY].getPiece() instanceof Rook
                    && board.getSquares()[newX][newY].getPiece().getColor() == color) {
                return (!((Rook) board.getSquares()[newX][newY].getPiece()).hasMoved());
            }
        }

        return false;
    }

}
