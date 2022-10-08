package game.piece;

import game.board.Board;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;
import game.piece.helper.DiagonalHelper;

import static game.piece.enums.Color.WHITE;
import static game.piece.enums.EnumPiece.BLACK_BISHOP;
import static game.piece.enums.EnumPiece.WHITE_BISHOP;

public class Bishop extends Piece {

    public Bishop(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        changePosition(newX, newY);
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return DiagonalHelper.moveCheck(this, newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return DiagonalHelper.canMoveAnywhere(this);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "bishop.png";
    }

    @Override
    public EnumPiece getEnumPiece() {
        if (color == WHITE) {
            return WHITE_BISHOP;
        }
        return BLACK_BISHOP;
    }
}
