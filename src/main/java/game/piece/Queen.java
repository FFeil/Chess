package game.piece;

import game.board.Board;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;
import game.piece.helper.DiagonalHelper;
import game.piece.helper.StraightHelper;

import static game.piece.enums.Color.WHITE;
import static game.piece.enums.EnumPiece.*;

public class Queen extends Piece {

    public Queen(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        changePosition(newX, newY);
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return StraightHelper.moveCheck(this, newX, newY) || DiagonalHelper.moveCheck(this, newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return StraightHelper.canMoveAnywhere(this) || DiagonalHelper.canMoveAnywhere(this);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "queen.png";
    }

    @Override
    public EnumPiece getEnumPiece() {
        if (color == WHITE) {
            return WHITE_QUEEN;
        }

        return BLACK_QUEEN;
    }
}
