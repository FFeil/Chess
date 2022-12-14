package game.piece.helper;

import game.piece.Piece;

public class CanMoveAnywhereHelper {

    private CanMoveAnywhereHelper() {}

    public static boolean canMoveAnywhere(Piece piece, int start) {
        int x = piece.getX();
        int y = piece.getY();
        
        for (int i = start; i < start * -1 + 1; i++) {
            for (int j = start; j < start * -1 + 1; j++) {
                if ((x + i > -1 && x + i < 8 && y + j > -1 && y + j < 8)
                        && piece.getBoard().moveIsValid(x, y, x + i, y + j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
