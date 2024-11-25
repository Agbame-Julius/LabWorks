module com.labs.test_processing_tool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.labs.test_processing_tool to javafx.fxml;
    exports com.labs.test_processing_tool;
}