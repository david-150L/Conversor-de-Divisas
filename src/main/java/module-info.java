module com.example.conversordivisasfx {

    requires org.json;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.conversordivisasfx to javafx.fxml;
    exports com.example.conversordivisasfx;
}