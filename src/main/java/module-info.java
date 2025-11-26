module com.isw.app.isw321ecosystemsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.isw.app to javafx.fxml;
    exports com.isw.app;
}