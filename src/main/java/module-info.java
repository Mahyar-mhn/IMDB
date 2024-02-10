module com.example.testjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testjavafx to javafx.fxml;
    exports com.example.testjavafx;
}