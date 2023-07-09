module com.example.conversordivisasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.conversordivisasfx to javafx.fxml;
    exports com.example.conversordivisasfx;
}