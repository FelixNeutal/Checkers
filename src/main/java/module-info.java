module com.example.checkers {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.checkers to javafx.fxml;
    exports com.example.checkers;
    exports com.example.checkers.Controllers;
}