package game.piece;

import game.board.Board;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;
import game.piece.helper.StraightHelper;

import static game.piece.enums.Color.WHITE;
import static game.piece.enums.EnumPiece.*;

public class Rook extends Piece {

    boolean moved;

    public Rook(Board board, Color color, int x, int y) {
        super(board, color, x, y);
        moved = false;
    }

    public boolean hasMoved() {
        return moved;
    }

    @Override
    public void move(int newX, int newY) {
        changePosition(newX, newY);
        moved = true;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return StraightHelper.moveCheck(this, newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return StraightHelper.canMoveAnywhere(this);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "rook.png";
    }

    @Override
    public EnumPiece getEnumPiece() {
        if (color == WHITE) {
            return WHITE_ROOK;
        }

        return BLACK_ROOK;
    }
}
