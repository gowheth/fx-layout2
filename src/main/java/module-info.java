module com.example.fxlayout {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxlayout2 to javafx.fxml;
    exports com.example.fxlayout2;
}