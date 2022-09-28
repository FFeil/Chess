package game;

import game.piece.Color;

public class Game {

    private Board board;
    private Color currentPlayer;
    private Color nextPlayer;

    public Board getBoard() {
        return board;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Color getNextPlayer() {
        return nextPlayer;
    }

    public void switchPlayer() {
        Color tmp = nextPlayer;
        nextPlayer = currentPlayer;
        currentPlayer = tmp;
    }

    public void makeMove(int x, int y) {

    }
}
