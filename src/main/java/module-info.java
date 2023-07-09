module com.example.conversordivisasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens com.example.conversordivisasfx to javafx.fxml;
    exports com.example.conversordivisasfx;
}