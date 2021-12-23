module com.example.fxlayout {
    requires javafx.controls;
    requires javafx.fxml;
    requires reactor.kafka;
    requires reactor.core;
    requires kafka.clients;
    requires lombok;

    opens com.example.fxlayout2 to javafx.fxml;
    exports com.example.fxlayout2;
}