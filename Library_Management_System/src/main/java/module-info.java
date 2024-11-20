module com.example.library_management_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;

    opens com.example.library_management_system to javafx.fxml;
    exports com.example.library_management_system;
    exports model;
    opens model to javafx.fxml;
}