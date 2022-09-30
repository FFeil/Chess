package game.board;

import game.piece.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Board {

    private final Square[][] squares;
    private final Set<Piece> whitePieces;
    private final Set<Piece> blackPieces;
    private boolean promotePawn;
    private final int[] whiteKingCoord;
    private final int[] blackKingCoord;
    private Integer[] oldRookCoord;
    private Integer[] newRookCoord;
    private int moveCount; // 50 move rule
    //private int repetitionCount;

    public Board() {
        promotePawn = false;
        whiteKingCoord = new int[2];
        blackKingCoord = new int[2];
        oldRookCoord = null;
        newRookCoord = null;
        moveCount = 0;
        //repetitionCount = 0;

        squares = new Square[8][8];
        whitePieces = new HashSet<>();
        blackPieces = new HashSet<>();

        initiateSquares();
        initiatePieces();
    }

    public Square[][] getSquares() {
        return squares;
    }

    public boolean isPromotePawn() {
        return promotePawn;
    }

    public Set<Piece> getPieceSet(Color color) {
        if (color == WHITE) {
            return whitePieces;
        } else {
            return blackPieces;
        }
    }

    public void addPiece(Color color, Piece piece) {
        if (color == WHITE) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }

    public void removePiece(Color color, Piece piece) {
        if (color == WHITE) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

    public void setPromotePawn(boolean promotePawn) {
        this.promotePawn = promotePawn;
    }

    public int[] getKingCoord(Color color) {
        if (color == WHITE) {
            return whiteKingCoord;
        } else {
            return blackKingCoord;
        }
    }

    public void setKingCoord(Color color, int x, int y) {
        if (color == WHITE) {
            whiteKingCoord[0] = x;
            whiteKingCoord[1] = y;
        } else {
            blackKingCoord[0] = x;
            blackKingCoord[1] = y;
        }
    }

    public Integer[] getOldRookCoord() {
        Integer[] oldRookCoord = this.oldRookCoord;
        this.oldRookCoord = null;

        return oldRookCoord;
    }

    public Integer[] getNewRookCoord() {
        Integer[] newRookCoord = this.newRookCoord;
        this.newRookCoord = null;

        return newRookCoord;
    }

    public void setRookCoord(int oldX, int oldY, int newX, int newY) {
        oldRookCoord = new Integer[] {oldX, oldY};
        newRookCoord = new Integer[] {newX, newY};
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incrMoveCount() {
        moveCount++;
    }

    public void decrMoveCount() {
        moveCount--;
    }

    public void resetMoveCount() {
        moveCount = 0;
    }

// public int getRepetitionCount() {
//     return repetitionCount;
// }

// public void incrRepetitionCount() {
//     repetitionCount++;
// }

// public void resetRepetitionCount() {
//     repetitionCount = 0;
// }

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
        whiteKingCoord[0] = 7;
        whiteKingCoord[1] = 4;
        blackKingCoord[0] = 0;
        blackKingCoord[1] = 4;

        for (int i = 0; i < 2; i++) {
            Arrays.stream(squares[i]).forEach(square -> blackPieces.add(square.getPiece()));
        }

        for (int i = 6; i < 8; i++) {
            Arrays.stream(squares[i]).forEach(square -> whitePieces.add(square.getPiece()));
        }
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

        if (pieceCanBeTakenAt(getKingCoord(currentPlayer)[0], getKingCoord(currentPlayer)[1], currentPlayer)) {
            result = true;
        }

        squares[oldX][oldY].setPiece(movingPiece);
        squares[newX][newY].setPiece(pieceToBeTaken);

        return result;
    }

    public boolean pieceCanBeTakenAt(int x, int y, Color currentPlayer) {
        if (currentPlayer == WHITE) {
            return blackPieces.stream().anyMatch(piece -> piece.canMoveTo(x, y) && !(piece instanceof King));
        } else {
            return whitePieces.stream().anyMatch(piece -> piece.canMoveTo(x, y) && !(piece instanceof King));
        }
    }
}
