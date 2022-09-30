package com.javaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChessView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 675, 575);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }

    public static void main(String[] args) {
        launch();
    }
}