package com.example.conversordivisasfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.util.Map;


public class HelloController {

    @FXML
    private ChoiceBox<String> choiceBoxIn;

    @FXML
    private ChoiceBox<String> choiceBoxOut;

    @FXML
    private TextField textFieldImporte;

    public void initialize() {
        // Agregar un EventListener al TextField para manejar los eventos de teclado
        textFieldImporte.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            // Obtener el carácter ingresado por el usuario
            String input = event.getCharacter();

            // Verificar si el carácter no es un número
            if (!input.matches("\\d")) {
                // Consumir el evento, evitando que se ingrese el carácter no válido en el TextField
                event.consume();
            }
        });
    }

    @FXML
    private TextField textFiledEquivalencia;

    @FXML
    private Button buttonConvert;

    @FXML
    protected void convertirValores(ActionEvent event) {
        String res = APIExchangeRateImplementacion.convertir(obtenerSimbolo(choiceBoxIn.getValue()), obtenerSimbolo(choiceBoxOut.getValue()), textFieldImporte.getText());
        textFiledEquivalencia.setText(leerJson(res));
    }

    @FXML
    protected String leerJson(String json) {
        //Crear objeto JSONObject a partir de JSON de la API
        JSONObject jsonObject = new JSONObject(json);

        // Acceder al valor de "conversion_result"
        Double conversionResult = jsonObject.getDouble("conversion_result");

        return conversionResult.toString();
    }

    private String obtenerSimbolo(String valor) {
        Map<String, String> simbolos = Map.of(
                "Dólar", "USD",
                "Euros", "EUR",
                "Libras Esterlinas", "GBP",
                "Yen Japonés", "JPY",
                "Won sul-coreano", "KRW",
                "Pesos colombianos", "COP"
        );
        return simbolos.getOrDefault(valor, "ERROR");
    }

}