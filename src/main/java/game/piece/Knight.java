package game.piece;

import game.board.Board;
import game.board.Square;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;
import game.piece.helper.CanMoveAnywhereHelper;

import static game.piece.enums.Color.WHITE;
import static game.piece.enums.EnumPiece.*;

public class Knight extends Piece {

    public Knight(Board board, Color color, int x, int y) {
        super(board, color, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        changePosition(newX, newY);
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        Square square = board.getSquares()[newX][newY];

        return ((getXDistance(newX)  == 1 && getYDistance(newY) == 2)
                || (getXDistance(newX) == 2 && getYDistance(newY) == 1 ))
                && (square.containsPieceOfOtherColor(color) || square.isEmpty());
    }

    @Override
    public boolean canMoveAnywhere() {
        return CanMoveAnywhereHelper.canMoveAnywhere(this, -2);
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "knight.png";
    }

    @Override
    public EnumPiece getEnumPiece() {
        if (color == WHITE) {
            return WHITE_KING;
        }

        return BLACK_KNIGHT;
    }
}
