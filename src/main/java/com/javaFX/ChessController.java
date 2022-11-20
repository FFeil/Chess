package com.javaFX;

import game.Game;
import game.board.Square;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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
    private int[] oldCoord;

    private Game game;
    private boolean active;

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
        active = true;

        initImageViews();

        dragImage.toBack();
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
                    setImage(i, j, new Image(new File(squares[i][j].getPiece().getImagePath()).toURI().toString()));
                }
            }
        }
    }

    private Image getImage(int x, int y) {
        return ((ImageView) panes[x][y].getChildren().get(0)).getImage();
    }

    private void setImage(int x, int y, Image image) {
        ((ImageView) panes[x][y].getChildren().get(0)).setImage(image);
    }

    private int doSingleChoicePopUp() {
        ArrayList<String> choices = new ArrayList<>(Arrays.asList("Bishop", "Knight", "Rook", "Queen"));

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Bishop", choices);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setHeaderText(null);
        dialog.setContentText("Choose a piece!");

        Optional<String> result = dialog.showAndWait();

        return result.map(choices::indexOf).orElse(0);
    }

    @FXML
    private void onDragDetected(MouseEvent event) {
        if (active && event.getButton() == MouseButton.PRIMARY) {
            Pane sourcePane = ((Pane) event.getSource());

            oldCoord = getCoord(sourcePane);
            assert oldCoord != null;

            pane.startFullDrag();

            if (!game.getBoard().getSquares()[oldCoord[0]][oldCoord[1]].isEmpty()) {
                dragImage.setDisable(false);
                dragImage.setImage(getImage(oldCoord[0], oldCoord[1]));
                dragImage.toFront();
                chessPane.setCursor(Cursor.NONE);

                ((ImageView) sourcePane.getChildren().get(0)).setImage(null);
            }

            event.consume();
        }
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        if (active && event.getButton() == MouseButton.PRIMARY) {
            dragImage.setX(event.getSceneX() - 37);
            dragImage.setY(event.getSceneY() - 20);
        }
    }

    @FXML
    private void onMouseDragReleased(MouseEvent event) {
        if (active && event.getButton() == MouseButton.PRIMARY) {
            if (((ImageView) event.getTarget()).getParent() instanceof Pane) {
                int[] newCoord = getCoord((Pane) ((ImageView) event.getTarget()).getParent());

                assert newCoord != null;

                ArrayList<Integer[]> updateList = (ArrayList<Integer[]>) game.makeMove(oldCoord[0], oldCoord[1], newCoord[0], newCoord[1]);

                if (updateList.isEmpty()) {
                    setImage(oldCoord[0], oldCoord[1], dragImage.getImage());
                } else {
                    if (game.getBoard().getPawnToPromote() != null) {
                        game.getBoard().promotePiece(doSingleChoicePopUp());
                    }

                    updateList.forEach(coord -> {
                        Square square = game.getBoard().getSquares()[coord[0]][coord[1]];
                        if (square.isEmpty()) {
                            setImage(coord[0], coord[1], null);
                        } else {
                            setImage(coord[0], coord[1],
                                    new Image(new File(square.getPiece().getImagePath()).toURI().toString()));
                        }
                    });

                    Stage stage = (Stage) ((Pane) event.getSource()).getScene().getWindow();
                    if (game.checkCheckMate()) {
                        active = false;
                        stage.setTitle("Player " + game.getNextPlayer().toString().toLowerCase() + " wins!");
                    } else if (game.checkDraw()) {
                        active = false;
                        stage.setTitle("It's a draw");
                    } else {
                        stage.setTitle("Player: " + game.getCurrentPlayer().toString().charAt(0)
                                        + game.getCurrentPlayer().toString().substring(1).toLowerCase());
                    }
                }
            }

            oldCoord = null;
            chessPane.setCursor(Cursor.DEFAULT);
            dragImage.setDisable(true);
            dragImage.toBack();
            dragImage.setImage(null);
        }
    }

    @FXML
    private void onResetButtonPressed() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setImage(i, j, null);
            }
        }

        game = new Game();
        initImageViews();

        active = true;

        chessPane.setCursor(Cursor.DEFAULT);
        dragImage.setImage(null);
        dragImage.setDisable(true);
    }
}