package game.piece;

import game.board.Board;
import game.board.Square;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;
import game.piece.helper.CanMoveAnywhereHelper;
import game.piece.helper.StraightHelper;

import static game.piece.enums.Color.WHITE;
import static game.piece.enums.EnumPiece.*;

public class King extends Piece {

    private boolean moved;

    public King(Board board, Color color, int x, int y) {
        super(board, color, x, y);
        moved = false;
    }

    @Override
    public void move(int newX, int newY) {
        if (isSameColorRookAt(newX, newY)) {
            board.getSquaresToUpdate().add(new Integer[]{newX, newY});
            if (newY > y) { // short castle -> right
                board.getSquares()[newX][newY].getPiece().changePosition(x, y + 1);
                board.getSquaresToUpdate().add(new Integer[]{newX, newY, x, y + 1});
                changePosition(x, y + 2);
            } else { // long castle -> left
                board.getSquares()[newX][newY].getPiece().changePosition(x, y - 1);
                board.getSquaresToUpdate().add(new Integer[]{newX, newY, x, y - 1});
                changePosition(x, y - 2);
            }
            board.decrMoveCount();
        } else {
            changePosition(newX, newY);
        }

        board.setKingCoord(color, newX, newY);
        moved = true;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        return (getXDistance(newX) + getYDistance(newY) == 1 || (getXDistance(newX) == 1 && getYDistance(newY) == 1))
                && (square.containsPieceOfOtherColor(color) || square.isEmpty()) || canCastle(newX, newY);
    }

    private boolean canCastle(int newX, int newY) {
        if (isSameColorRookAt(newX, newY) && !moved) {
            return StraightHelper.moveCheck(this, newX, newY) && board.kingCantBetaken(color);
        }

        return false;
    }

    private boolean isSameColorRookAt(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        if (!square.isEmpty()) {
            if (square.getPiece() instanceof Rook && square.getPiece().getColor() == color) {
                return (!((Rook) square.getPiece()).hasMoved());
            }
        }

        return false;
    }

    @Override
    public boolean canMoveAnywhere() {
        return CanMoveAnywhereHelper.canMoveAnywhere(this, -1);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "king.png";
    }

    @Override
    public EnumPiece getEnumPiece() {
        if (color == WHITE) {
            return WHITE_KING;
        }

        return BLACK_KING;
    }
}
