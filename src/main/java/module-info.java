module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens com.javaFX to javafx.fxml;
    exports com.javaFX;
}