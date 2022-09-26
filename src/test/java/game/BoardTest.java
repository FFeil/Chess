package game;

import game.piece.*;
import org.junit.Before;
import org.junit.Test;

import static game.piece.Color.BLACK;
import static game.piece.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Square[][] squares;

    @Before
    public void before() {
        squares = new Board().getSquares();
    }

    @Test
    public void initSquares() {
        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue(squares[i][j] != null);
            }
        }
    }

    @Test
    public void initPawns() {
        for (Square square : squares[1]) {
            assertTrue(square.getPiece() instanceof Pawn);
            assertEquals(BLACK, square.getPiece().getColor());
        }

        for (Square square : squares[6]) {
            assertTrue(square.getPiece() instanceof Pawn);
            assertEquals(WHITE, square.getPiece().getColor());
        }
    }

    @Test
    public void initRooks() {
        assertTrue(squares[0][0].getPiece() instanceof Rook);
        assertTrue(squares[0][7].getPiece() instanceof Rook);
        assertEquals(BLACK, squares[0][0].getPiece().getColor());
        assertEquals(BLACK, squares[0][7].getPiece().getColor());

        assertTrue(squares[7][0].getPiece() instanceof Rook);
        assertTrue(squares[7][7].getPiece() instanceof Rook);
        assertEquals(WHITE, squares[7][0].getPiece().getColor());
        assertEquals(WHITE, squares[7][7].getPiece().getColor());
    }

    @Test
    public void initKnights() {
        assertTrue(squares[0][1].getPiece() instanceof Knight);
        assertTrue(squares[0][6].getPiece() instanceof Knight);
        assertEquals(BLACK, squares[0][1].getPiece().getColor());
        assertEquals(BLACK, squares[0][6].getPiece().getColor());

        assertTrue(squares[7][1].getPiece() instanceof Knight);
        assertTrue(squares[7][6].getPiece() instanceof Knight);
        assertEquals(WHITE, squares[7][1].getPiece().getColor());
        assertEquals(WHITE, squares[7][6].getPiece().getColor());
    }

    @Test
    public void initBishops() {
        assertTrue(squares[0][2].getPiece() instanceof Bishop);
        assertTrue(squares[0][5].getPiece() instanceof Bishop);
        assertEquals(BLACK, squares[0][2].getPiece().getColor());
        assertEquals(BLACK, squares[0][5].getPiece().getColor());

        assertTrue(squares[7][2].getPiece() instanceof Bishop);
        assertTrue(squares[7][5].getPiece() instanceof Bishop);
        assertEquals(WHITE, squares[7][2].getPiece().getColor());
        assertEquals(WHITE, squares[7][5].getPiece().getColor());
    }

    @Test
    public void initQueens() {
        assertTrue(squares[0][3].getPiece() instanceof Queen);
        assertEquals(BLACK, squares[0][3].getPiece().getColor());

        assertTrue(squares[7][3].getPiece() instanceof Queen);
        assertEquals(WHITE, squares[7][3].getPiece().getColor());
    }

    @Test
    public void initKings() {
        assertTrue(squares[0][4].getPiece() instanceof King);
        assertEquals(BLACK, squares[0][4].getPiece().getColor());

        assertTrue(squares[7][4].getPiece() instanceof King);
        assertEquals(WHITE, squares[7][4].getPiece().getColor());
    }
}