package game.piece.helper;

import game.piece.Piece;

public class CanMoveAnywhereHelper {

    public static boolean canMoveAnywhere(Piece piece, int start) {
        for (int i = start; i < start * -1 + 1; i++) {
            for (int j = start; j < start * -1 + 1; j++) {
                if (piece.getX() + i > -1 && piece.getX() + i < 8 && piece.getY() + j > -1 && piece.getY() + j < 8) {
                    if (piece.canMoveTo(piece.getX() + i, piece.getY() + j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
