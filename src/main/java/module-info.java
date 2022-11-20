module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.javaFX to javafx.fxml;
    exports com.javaFX;
}