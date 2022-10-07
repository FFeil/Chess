package game.piece;

import game.board.Board;
import game.piece.helper.CanMoveAnywhereHelper;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Pawn extends Piece {

    private boolean hasMoved;
    private int row;

    public Pawn(Board board, Color color, int x, int y) {
        super(board, color, x, y);

        hasMoved = false;

        if (color == BLACK) {
            row = x + 1;
        } else {
            row = 8 - x;
        }
    }

    public boolean hasMoved() {
        return row > 2;
    }

    @Override
    public boolean move(int newX, int newY) {
        hasMoved = true;

        if (getXDistance(newX) == 2 && row == 2) {
            row++;
        }

        row++;

        if (row == 8) {
            board.setPromotePawn(this);
        }

        // en pasant move
        if (getXDistance(newX) == 1 && getYDistance(newY) == 1 && board.getSquares()[newX][newY].isEmpty()) {
            int backwardsStep = 1;

            if (color == WHITE) {
                backwardsStep *= -1;
            }

            board.getSquares()[newX - backwardsStep][newY].removePiece();
            board.getSquaresToUpdate().add(new Integer[]{newX - backwardsStep, newY});
        }

        changePosition(newX, newY);
        board.resetMoveCount();

        return true;
    }

    @Override
    public boolean canMoveTo(int newX, int newY) {
        return canMove1Forward(newX, newY) || canMove2Forward(newX, newY)
                || canMoveDiagonal(newX, newY) || canDoEnPasant(newX, newY);
    }

    @Override
    public boolean canMoveAnywhere() {
        return CanMoveAnywhereHelper.canMoveAnywhere(this, -1);
    }

    private boolean canMove1Forward(int newX, int newY) {
         return ((newX - x == 1 && color == BLACK )
                 || (newX - x == -1 && color == WHITE))
                 && y == newY && board.getSquares()[newX][newY].isEmpty();
    }

    private boolean canMove2Forward(int newX, int newY) {
        return row == 2 && getXDistance(newX) == 2 && y == newY && board.getSquares()[newX][newY].isEmpty()
                && ((color == BLACK && board.getSquares()[3][y].isEmpty() && board.getSquares()[newX - 1][y].isEmpty())
                || ((color == WHITE && board.getSquares()[5][y].isEmpty() && board.getSquares()[newX + 1][y].isEmpty())));
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

        if (board.getSquares()[newX][newY].isEmpty() && row == 5 && !hasMoved
                && getXDistance(newX) == 1 && getYDistance(newY) == 1) {
            if (board.getSquares()[newX - backwardsStep][newY].containsPieceOfOtherColor(color)) {
                if (board.getSquares()[newX - backwardsStep][newY].getPiece() instanceof Pawn) {
                    return ((Pawn) board.getSquares()[newX - backwardsStep][newY].getPiece()).hasMoved();
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
