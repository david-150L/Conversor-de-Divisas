package com.example.conversordivisasfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Clase controladora de los nodos pertenecientes a la GUI implementada en el archivo 'hello-view.fxml'.
 */
public class HelloController {

    @FXML
    private ChoiceBox<String> choiceBoxIn;

    @FXML
    private ChoiceBox<String> choiceBoxOut;

    @FXML
    private TextField textFieldImporte;

    /**
     * Valida y maneja los eventos de teclado en el campo de texto 'textFieldImporte'.
     * Si se ingresa un carácter no válido, se consume el evento y se establece un estilo de focus rojo.
     * Si se ingresa un carácter válido, se establece un estilo de foco por defecto y no se consumo el evento.
     */
    public void initialize() {
        // Agregar un EventListener al TextField para manejar los eventos de teclado
        textFieldImporte.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            // Obtener el carácter ingresado por el usuario
            String input = event.getCharacter();

            // Verificar si el carácter no es un número
            if (!(input.matches("\\d") || event.getCharacter().equals("\b"))) {
                // Consumir el evento, evitando que se ingrese el carácter no válido en el TextField
                event.consume();
                //Focus rojo si se introduce un caracter no valido
                String focusStyle = "-fx-focus-color: #ff1a1a;";
                textFieldImporte.setStyle(focusStyle);
            } else if (input.matches("\\d")) {
                //Focus por defecto si se introduce un caracter valido
                String defaultStyle = "-fx-control-inner-background: white;";
                textFieldImporte.setStyle(defaultStyle);
            }
        });
    }

    @FXML
    private TextField textFiledEquivalencia;

    @FXML
    private Button buttonConvert;

    /**
     * Llama al metodo estatico 'convertir' de la clase 'APIExchangeImplementacion', tambien
     * pasa como parametro al metodo 'setText' la llamada del metodo 'leerJson' y le pasa a
     * este metodo como parametro el return de 'APIExchangeImplementacion.convertir'
     * @param event Captura el evento clic del boton 'buttonConvert'
     */
    @FXML
    protected void convertirValores(ActionEvent event) {
        String res = APIExchangeRateImplementacion.convertir(obtenerSimbolo(choiceBoxIn.getValue()), obtenerSimbolo(choiceBoxOut.getValue()), textFieldImporte.getText());
        //res = String.valueOf(Math.round((leerJson(res) * 100) / 100));
        textFiledEquivalencia.setText(leerJson(res));
    }

    /**
     * Crea un objeto JSONObject a partir de el JSON que recibe como parametro, accede al valor de
     * la clave "conversion_result" dentro del objeto JSON.
     * Luego, formatea el valor obtenido con dos decimales utilizando DecimalFormat.
     * @param json JSON proviniente del return del metodo estatico 'APIExchangeImplementacion.convertir'
     * @return retorna el valor de la clave 'conversion_result' formateado con dos decimales.
     */
    @FXML
    protected String leerJson(String json) {
        //Crear objeto JSONObject a partir de JSON de la API
        JSONObject jsonObject = new JSONObject(json);

        // Acceder al valor de "conversion_result"
        Double conversionResult = jsonObject.getDouble("conversion_result");

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return decimalFormat.format(conversionResult);
    }

    /**
     * Obtiene el símbolo correspondiente a una moneda dada.
     *
     * @param moneda La moneda para la cual se desea obtener el símbolo.
     * @return El símbolo correspondiente al valor dado. En caso de no encontrar un símbolo correspondiente, se retorna "ERROR".
     */
    private String obtenerSimbolo(String moneda) {
        Map<String, String> simbolos = Map.of(
                "Dólar", "USD",
                "Euros", "EUR",
                "Libras Esterlinas", "GBP",
                "Yen Japonés", "JPY",
                "Won sul-coreano", "KRW",
                "Pesos colombianos", "COP"
        );
        return simbolos.getOrDefault(moneda, "ERROR");
    }

}