module com.isw.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.isw.app.controllers to javafx.fxml;
    exports com.isw.app;
}