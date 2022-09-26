package game;

import game.piece.*;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;

public class Board {

    private final Square[][] squares;
    private boolean pawnMoved2Squares;
    private boolean promotePawn;

    public Board() {
        pawnMoved2Squares = false;
        promotePawn = false;
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
        int colorIndex = 0;

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
}
