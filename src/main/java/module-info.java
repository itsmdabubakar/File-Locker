module com.example.filelocker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filelocker to javafx.fxml;
    exports com.example.filelocker;
}