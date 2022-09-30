package game.piece;

import game.board.Board;
import game.board.Square;
import game.piece.helper.CanMoveAnywhereHelper;
import game.piece.helper.StraightHelper;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

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
                    board.setRookCoord(newX, newY, x, y + 1);
                    board.getSquares()[newX][newY].getPiece().changePosition(x, y + 1);
                    changePosition(x, y + 2);
                } else { // long castle -> left
                    board.setRookCoord(newX, newY, x, y - 1);
                    board.getSquares()[newX][newY].getPiece().changePosition(x, y - 1);
                    changePosition(x, y - 2);
                }
                board.decrMoveCount();
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

        return canSee(newX, newY) && !otherKingCanSee(newX, newY)
                && (square.containsPieceOfOtherColor(color) || square.isEmpty()) || canCastle(newX, newY);
    }

    public boolean canSee(int newX, int newY) {
        return getXDistance(newX) + getYDistance(newY) == 1 || (getXDistance(newX) == 1 && getYDistance(newY) == 1);
    }

    private boolean otherKingCanSee(int newX, int newY) {
        Color otherColor;
        if (color == WHITE) {
            otherColor = BLACK;
        } else {
            otherColor = WHITE;
        }

        return ((King) board.getSquares()[board.getKingCoord(otherColor)[0]][board.getKingCoord(otherColor)[1]]
                .getPiece()).canSee(newX, newY);
    }

    private boolean canCastle(int newX, int newY) {
        if (isSameColorRookAt(newX, newY) && !moved) {
            return StraightHelper.moveCheck(this, newX, newY)
                    && !board.pieceCanBeTakenAt(x, y, color);
        }

        return false;
    }

    private boolean isSameColorRookAt(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (!square.isEmpty()) {
            if (square.getPiece() instanceof Rook
                    && square.getPiece().getColor() == color) {
                return (!((Rook) square.getPiece()).hasMoved());
            }
        }

        return false;
    }

    @Override
    public boolean canMoveAnywhere() {
        return CanMoveAnywhereHelper.canMoveAnywhere(this, -1);
    }
}
