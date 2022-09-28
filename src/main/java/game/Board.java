package game;

import game.piece.*;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Board {

    private final Square[][] squares;
    private boolean pawnMoved2Squares;
    private final boolean promotePawn;
    private final int[] whiteKingCoord;
    private final int[] blackKingCoord;

    public Board() {
        pawnMoved2Squares = false;
        promotePawn = false;
        whiteKingCoord = new int[2];
        blackKingCoord = new int[2];

        squares = new Square[8][8];

        initiateSquares();
        initiatePieces();
    }

    public Square[][] getSquares() {
        return squares;
    }

    public boolean hasPawnMoved2Squares() {
        return pawnMoved2Squares;
    }

    public void setPawnMoved2Squares(boolean pawnMoved2Squares) {
        this.pawnMoved2Squares = pawnMoved2Squares;
    }

    public boolean isPawnMoved2Squares() {
        return pawnMoved2Squares;
    }

    public boolean isPromotePawn() {
        return promotePawn;
    }

    public int[] getWhiteKingCoord() {
        return whiteKingCoord;
    }

    public int[] getBlackKingCoord() {
        return blackKingCoord;
    }

    public void setWhiteKingCoord(int x, int y) {
        whiteKingCoord[0] = x;
        whiteKingCoord[1] = y;
    }

    public void setBlackKingCoord(int x, int y) {
        blackKingCoord[0] = x;
        blackKingCoord[1] = y;
    }

    private void initiatePieces() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            squares[1][i].setPiece(new Pawn(this, BLACK, 1, i));
        }
        for (int i = 0; i < 8; i++) {
            squares[6][i].setPiece(new Pawn(this, WHITE, 6, i));
        }

        //Rooks
        squares[0][0].setPiece(new Rook(this, BLACK, 0, 0));
        squares[0][7].setPiece(new Rook(this, BLACK, 0, 7));
        squares[7][0].setPiece(new Rook(this, WHITE, 7, 0));
        squares[7][7].setPiece(new Rook(this, WHITE, 7, 7));

        // Knights
        squares[0][1].setPiece(new Knight(this, BLACK, 0, 1));
        squares[0][6].setPiece(new Knight(this, BLACK, 0, 6));
        squares[7][1].setPiece(new Knight(this, WHITE, 7, 1));
        squares[7][6].setPiece(new Knight(this, WHITE, 7, 6));

        // Bishops
        squares[0][2].setPiece(new Bishop(this, BLACK, 0, 2));
        squares[0][5].setPiece(new Bishop(this, BLACK, 0, 5));
        squares[7][2].setPiece(new Bishop(this, WHITE, 7, 2));
        squares[7][5].setPiece(new Bishop(this, WHITE, 7, 5));

        // Queens
        squares[0][3].setPiece(new Queen(this, BLACK, 0, 3));
        squares[7][3].setPiece(new Queen(this, WHITE, 7, 3));

        // Kings
        squares[0][4].setPiece(new King(this, BLACK, 0, 4));
        squares[7][4].setPiece(new King(this, WHITE, 7, 4));
    }

    private void initiateSquares() {
        Color[] colors = {WHITE, BLACK};
        int colorIndex;

        for (int i = 0; i < 8; i ++) {
            if (i % 2 == 0) {
                colorIndex = 0;
            } else {
                colorIndex = 1;
            }
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(colors[colorIndex++ % 2]);
            }
        }
    }

    public boolean kingHasXray(int oldX, int oldY, int newX, int newY, Color currentPlayer) {
        boolean result = false;

        Piece movingPiece = squares[oldX][oldY].getPiece();
        squares[oldX][oldY].removePiece();

        Piece pieceToBeTaken = null;
        if (!squares[newX][newY].isEmpty()) {
            squares[newX][newY].removePiece();
            pieceToBeTaken = squares[newX][newY].getPiece();
        }

        if (kingCanBeTaken(currentPlayer)) {
            return true;
        }

        squares[oldX][oldY].setPiece(movingPiece);
        squares[newX][newY].setPiece(pieceToBeTaken);

        return result;
    }

    public boolean kingCanBeTaken(Color currentPlayer) {
        for (Square[] squareArray : squares) {
            for (Square square : squareArray) {
                if (!square.isEmpty()) {
                    if (currentPlayer == WHITE) {
                        if (square.getPiece().getColor() == BLACK) {
                            if (square.getPiece().canMoveTo(whiteKingCoord[0], whiteKingCoord[1])) {
                                return true;
                            }
                        }
                    } else {
                        if (square.getPiece().getColor() == WHITE) {
                            if (square.getPiece().canMoveTo(blackKingCoord[0], blackKingCoord[1])) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
