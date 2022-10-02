package com.javaFX;

import game.Game;
import game.board.Square;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChessController {

    @FXML
    private Pane pane;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane10;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane12;
    @FXML
    private Pane pane13;
    @FXML
    private Pane pane14;
    @FXML
    private Pane pane15;
    @FXML
    private Pane pane16;
    @FXML
    private Pane pane17;
    @FXML
    private Pane pane18;
    @FXML
    private Pane pane19;
    @FXML
    private Pane pane20;
    @FXML
    private Pane pane21;
    @FXML
    private Pane pane22;
    @FXML
    private Pane pane23;
    @FXML
    private Pane pane24;
    @FXML
    private Pane pane25;
    @FXML
    private Pane pane26;
    @FXML
    private Pane pane27;
    @FXML
    private Pane pane28;
    @FXML
    private Pane pane29;
    @FXML
    private Pane pane30;
    @FXML
    private Pane pane31;
    @FXML
    private Pane pane32;
    @FXML
    private Pane pane33;
    @FXML
    private Pane pane34;
    @FXML
    private Pane pane35;
    @FXML
    private Pane pane36;
    @FXML
    private Pane pane37;
    @FXML
    private Pane pane38;
    @FXML
    private Pane pane39;
    @FXML
    private Pane pane40;
    @FXML
    private Pane pane41;
    @FXML
    private Pane pane42;
    @FXML
    private Pane pane43;
    @FXML
    private Pane pane44;
    @FXML
    private Pane pane45;
    @FXML
    private Pane pane46;
    @FXML
    private Pane pane47;
    @FXML
    private Pane pane48;
    @FXML
    private Pane pane49;
    @FXML
    private Pane pane50;
    @FXML
    private Pane pane51;
    @FXML
    private Pane pane52;
    @FXML
    private Pane pane53;
    @FXML
    private Pane pane54;
    @FXML
    private Pane pane55;
    @FXML
    private Pane pane56;
    @FXML
    private Pane pane57;
    @FXML
    private Pane pane58;
    @FXML
    private Pane pane59;
    @FXML
    private Pane pane60;
    @FXML
    private Pane pane61;
    @FXML
    private Pane pane62;
    @FXML
    private Pane pane63;

    @FXML
    private ImageView dragImage;
    @FXML
    private Pane chessPane;

    private Pane[][] panes;
    private Pane sourcePane;
    private Pane targetPane;

    private Game game;

    public void initialize() {
        panes = new Pane[][]{
                {pane, pane1, pane2, pane3, pane4, pane5, pane6, pane7},
                {pane8, pane9, pane10, pane11, pane12, pane13, pane14, pane15},
                {pane16, pane17, pane18, pane19, pane20, pane21, pane22, pane23},
                {pane24, pane25, pane26, pane27, pane28, pane29, pane30, pane31},
                {pane32, pane33, pane34, pane35, pane36, pane37, pane38, pane39},
                {pane40, pane41, pane42, pane43, pane44, pane45, pane46, pane47},
                {pane48, pane49, pane50, pane51, pane52, pane53, pane54, pane55},
                {pane56, pane57, pane58, pane59, pane60, pane61, pane62, pane63}
        };

        game = new Game();

        initImageViews();
    }

    private int[] getCoord(Pane pane) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pane == panes[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private void initImageViews() {
        Square[][] squares = game.getBoard().getSquares();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!squares[i][j].isEmpty()) {
                    setImage(i, j, squares[i][j].getPiece().getImagePath());
                }
            }
        }
    }

    private Image getImage(int x, int y) {
        return ((ImageView) panes[x][y].getChildren().get(0)).getImage();
    }

    private void setImage(int x, int y, String image) {
        ((ImageView) panes[x][y].getChildren().get(0)).setImage(new Image(new File(image).toURI().toString()));
    }

    @FXML
    private void onDragDetected(MouseEvent event) {
        Pane sourcePane = ((Pane) event.getSource());
        int[] coord = getCoord(sourcePane);
        assert coord != null;

        if (!game.getBoard().getSquares()[coord[0]][coord[1]].isEmpty()) {
            dragImage.setDisable(false);
            dragImage.setImage(getImage(coord[0], coord[1]));
            chessPane.setCursor(Cursor.NONE);

            ((ImageView) sourcePane.getChildren().get(0)).setImage(null);
            sourcePane.startDragAndDrop(TransferMode.ANY);
            event.consume();
        }
    }

    @FXML
    private void onDragDropped(DragEvent event) {
    }

    @FXML
    private void onDragOver(DragEvent event) {
        sourcePane = null;

        event.consume();
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        dragImage.setX(event.getX() - 37);
        dragImage.setY(event.getY() - 37);
    }

    @FXML
    private void onMouseReleased(MouseEvent event) {
        chessPane.setCursor(Cursor.DEFAULT);
        dragImage.setDisable(true);
        dragImage.setImage(null);
    }

    @FXML
    public void onResetButtonPressed(ActionEvent actionEvent) {
        initImageViews();

        chessPane.setCursor(Cursor.DEFAULT);
        dragImage.setImage(null);
        dragImage.setDisable(true);
    }
}