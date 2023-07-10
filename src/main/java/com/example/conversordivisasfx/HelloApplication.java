package com.example.conversordivisasfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que inicia y muestra la aplicación del Conversor de Divisas.
 */
public class HelloApplication extends Application {

    /**
     * Método que se ejecuta antes de que la aplicación se inicie.
     * Inicia la conexión con la API ExchangeRate.
     *
     * @throws Exception Si ocurre algún error durante la inicialización.
     */
    @Override
    public void init() throws Exception {
        super.init();
        APIExchangeRateImplementacion.iniciarConection();
    }

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Carga y muestra la interfaz de usuario desde el archivo "hello-view.fxml".
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre algún error al cargar la interfaz de usuario.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Conversor de divisas");
        stage.setScene(scene);
        stage.setResizable(false); // No se puede redimensionar la ventana
        stage.show();
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        launch();
    }
}
