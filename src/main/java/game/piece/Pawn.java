package game.piece;

import game.board.Board;
import game.piece.helper.CanMoveAnywhereHelper;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Pawn extends Piece {

    private int row;
    private boolean moved2Squares;

    public Pawn(Board board, Color color, int x, int y) {
        super(board, color, x, y);
        row = 2;
        moved2Squares = false;
    }

    public boolean hasMoved2Squares() {
        return moved2Squares;
    }

    @Override
    public boolean move(int newX, int newY) {
        if (canMoveTo(newX, newY) && !board.kingHasXray(getX(), getY(), newX, newY, color)) {
            if (getXDistance(newX) == 2) {
                row = 4;
                moved2Squares = true;
            } else {
                row++;
                moved2Squares = false;
            }
            if (row == 8) {
                board.setPromotePawn(true);
            }

            // en pasant move
            if (getXDistance(newX) == 1 && getYDistance(newY) == 1 && board.getSquares()[newX][newY].isEmpty()) {
                int backwardsStep = 1;

                if (color == WHITE) {
                    backwardsStep *= -1;
                }

                board.getSquares()[newX - backwardsStep][newY].removePiece();
            }

            changePosition(newX, newY);
            board.resetMoveCount();

            return true;
        }

        return false;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return canMove1Forward(newX, newY) || canMove2Forward(newX, newY)
                || canMoveDiagonal(newX, newY) ||canDoEnPasant(newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return CanMoveAnywhereHelper.canMoveAnywhere(this, -1);
    }

    private boolean canMove1Forward(int newX, int newY) {
         return ((newX - x == 1 && color == BLACK ) || (newX - x == -1 && color == WHITE))
                 && y == newY && board.getSquares()[newX][newY].isEmpty();
    }

    private boolean canMove2Forward(int newX, int newY) {
        return getXDistance(newX) == 2 && y == newY && board.getSquares()[newX][newY].isEmpty()
                && ((color == BLACK && board.getSquares()[x + 1][y].isEmpty())
                || ((color == Color.WHITE && board.getSquares()[x - 1][y].isEmpty())))
                && row == 2;
    }

    private boolean canMoveDiagonal(int newX, int newY) {
        return getXDistance(newX) == 1 && getYDistance(newY) == 1
                && board.getSquares()[newX][newY].containsPieceOfOtherColor(color);
    }

    private boolean canDoEnPasant(int newX, int newY) {
        int backwardsStep = 1;
        if (color == WHITE) {
            backwardsStep*=-1;
        }

        if (board.getSquares()[newX][newY].isEmpty() && row == 5
                && getXDistance(newX) == 1 && getYDistance(newY) == 1) {
            if (board.getSquares()[newX - backwardsStep][newY].containsPieceOfOtherColor(color)) {
                if (board.getSquares()[newX - backwardsStep][newY].getPiece() instanceof Pawn) {
                    return ((Pawn) board.getSquares()[newX - backwardsStep][newY].getPiece()).hasMoved2Squares();
                }
            }
        }

        return false;
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/picture/" + color.toString().toLowerCase() + "_" + "pawn.png";
    }
}
