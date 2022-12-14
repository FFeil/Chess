package game.board;

import game.piece.*;
import game.piece.enums.Color;
import game.piece.enums.EnumPiece;

import java.util.*;

import static game.piece.enums.Color.BLACK;
import static game.piece.enums.Color.WHITE;
import static game.piece.enums.EnumPiece.EMPTY;

public class Board {

    private final Square[][] squares;
    private final Set<Piece> whitePieces;
    private final Set<Piece> blackPieces;
    private Pawn pawnToPromote;
    private final int[] whiteKingCoord;
    private final int[] blackKingCoord;
    private final ArrayList<Integer[]> squaresToUpdate;
    private int moveCount; // 50 move rule
    private ArrayList<EnumPiece[][]> boardConfigs; // Repetition rule

    public Board() {
        pawnToPromote = null;
        whiteKingCoord = new int[2];
        blackKingCoord = new int[2];
        squaresToUpdate = new ArrayList<>();
        moveCount = 0;
        boardConfigs = new ArrayList<>();

        squares = new Square[8][8];
        whitePieces = new HashSet<>();
        blackPieces = new HashSet<>();

        initiateSquares();
        initiatePieces();
    }

    public Board(Square[][] squares, int[] whiteKingCoord, int[] blackKingCoord) { // copy constructor
        this.whiteKingCoord = whiteKingCoord;
        this.blackKingCoord = blackKingCoord;
        squaresToUpdate = new ArrayList<>();
        whitePieces = new HashSet<>();
        blackPieces = new HashSet<>();

        this.squares = new Square[8][8];
        initiateSquares();
        copySquares(squares);
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Set<Piece> getPieceSet(Color color) {
        if (color == WHITE) {
            return whitePieces;
        } else {
            return blackPieces;
        }
    }

    public void setPawnToPromote(Pawn pawnToPromote) {
        this.pawnToPromote = pawnToPromote;
    }

    public Pawn getPawnToPromote() {
        return pawnToPromote;
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

    public List<Integer[]> getSquaresToUpdate() {
        return squaresToUpdate;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incrMoveCount() {
        moveCount++;
    }

    public void resetMoveCount() {
        moveCount = 0;
    }

    public List<EnumPiece[][]> getBoardConfigs() {
        return boardConfigs;
    }

    public void addCurrenBoardConfig() {
        EnumPiece[][] newConfig = new EnumPiece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].isEmpty()) {
                    newConfig[i][j] = EMPTY;
                } else {
                    newConfig[i][j] = squares[i][j].getPiece().getEnumPiece();
                }
            }
        }
        boardConfigs.add(newConfig);
    }

    private void initiateSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square();
            }
        }
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

    public boolean pieceCanBeTakenAt(int x, int y, Color currentPlayer) {
        if (currentPlayer == WHITE) {
            return blackPieces.stream().anyMatch(piece -> piece.canMoveTo(x, y));
        } else {
            return whitePieces.stream().anyMatch(piece -> piece.canMoveTo(x, y));
        }
    }

    public boolean kingCantBetaken(Color currentPlayer) {
        if (currentPlayer == BLACK) {
            return !pieceCanBeTakenAt(blackKingCoord[0], blackKingCoord[1], BLACK);
        } else {
            return !pieceCanBeTakenAt(whiteKingCoord[0], whiteKingCoord[1], WHITE);
        }
    }

    private void copySquares(Square[][] squares) {
        whitePieces.clear();
        blackPieces.clear();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!squares[i][j].isEmpty()) {
                    Color color = squares[i][j].getPiece().getColor();

                    if (squares[i][j].getPiece() instanceof Pawn) {
                        this.squares[i][j].setPiece(new Pawn(this, color, i, j,
                                ((Pawn)(squares[i][j].getPiece())).hasJustMoved2Squares()));
                    } else if (squares[i][j].getPiece() instanceof Rook) {
                        this.squares[i][j].setPiece(new Rook(this, color, i, j));
                    } else if (squares[i][j].getPiece() instanceof Knight) {
                        this.squares[i][j].setPiece(new Knight(this, color, i, j));
                    } else if (squares[i][j].getPiece() instanceof Bishop) {
                        this.squares[i][j].setPiece(new Bishop(this, color, i, j));
                    } else if (squares[i][j].getPiece() instanceof Queen) {
                        this.squares[i][j].setPiece(new Queen(this, color, i, j));
                    } else if (squares[i][j].getPiece() instanceof King) {
                        this.squares[i][j].setPiece(new King(this, color, i, j));
                    }

                    getPieceSet(color).add(this.squares[i][j].getPiece());
                } else {
                    this.squares[i][j].removePiece();
                }
            }
        }
    }

    public boolean moveIsValid(int oldX, int oldY, int newX, int newY) {
        Board boardCopy = new Board();
        boardCopy.copySquares(squares);
        boardCopy.setKingCoord(WHITE, whiteKingCoord[0], whiteKingCoord[1]);
        boardCopy.setKingCoord(BLACK, blackKingCoord[0], blackKingCoord[1]);

        Piece currentPiece = boardCopy.getSquares()[oldX][oldY].getPiece();

        if (currentPiece.canMoveTo(newX, newY)) {
            currentPiece.move(newX, newY);

            return boardCopy.kingCantBetaken(currentPiece.getColor());
        }

        return false;
    }

    public void promotePiece(int choice) {
        // 0 = Bishop
        // 1 = Knight
        // 2 = Rook
        // 3 = Queen

        int x = pawnToPromote.getX();
        int y = pawnToPromote.getY();
        Color color = pawnToPromote.getColor();

        getPieceSet(color).remove(pawnToPromote);
        pawnToPromote = null;

        squaresToUpdate.add(new Integer[] {x, y});

        switch (choice) {
            case 0 -> {
                squares[x][y].setPiece(new Bishop(this, color, x, y));
                getPieceSet(color).add(squares[x][y].getPiece());
            }
            case 1 -> {
                squares[x][y].setPiece(new Knight(this, color, x, y));
                getPieceSet(color).add(squares[x][y].getPiece());
            }
            case 2 -> {
                squares[x][y].setPiece(new Rook(this, color, x, y));
                getPieceSet(color).add(squares[x][y].getPiece());
            }
            case 3 -> {
                squares[x][y].setPiece(new Queen(this, color, x, y));
                getPieceSet(color).add(squares[x][y].getPiece());
            }
            default -> {
            }
        }
    }
}
