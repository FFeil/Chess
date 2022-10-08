package game.piece.helper;

import game.piece.King;
import game.piece.Piece;

public class CanMoveAnywhereHelper {

    public static boolean canMoveAnywhere(Piece piece, int start) {
        int x = piece.getX();
        int y = piece.getY();
        
        for (int i = start; i < start * -1 + 1; i++) {
            for (int j = start; j < start * -1 + 1; j++) {
                if (x + i > -1 && x + i < 8 && y + j > -1 && y + j < 8) {
                    if (piece.canMoveTo(x + i, y + j)) {
                        if (piece instanceof King ) {
                            if (piece.getBoard().
                                    moveIsValid(x, y, x + i, y + j)) {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
