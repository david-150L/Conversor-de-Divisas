package com.example.conversordivisasfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.JSONObject;


public class HelloController {

    @FXML
    private ChoiceBox<String> choiceBoxIn;

    @FXML
    private ChoiceBox<String> choiceBoxOut;

    @FXML
    private TextField textFieldImporte;

    @FXML
    private TextField textFiledEquivalencia;

    @FXML
    protected void convertirValores(ActionEvent event){
        String res = APIExchangeRateImplementacion.convertir(obtenerSimbolo(choiceBoxIn.getValue()), obtenerSimbolo(choiceBoxOut.getValue()), textFieldImporte.getText());
        textFiledEquivalencia.setText(leerJson(res));
    }

    @FXML
    protected String leerJson(String json){

        //Crear objeto JSONObject a partir de JSON de la API
        JSONObject jsonObject = new JSONObject(json);

        // Acceder al valor de "conversion_result"
        double conversionResult = jsonObject.getDouble("conversion_result");

        //Convierte el valor conversionResult de double a String
        String resultString = Double.toString(conversionResult);

        return resultString;

    }

    private String obtenerSimbolo(String valor){
        switch (valor){
            case "Dólar" -> {
                return "USD";
            }
            case "Euros" -> {
                return "EUR";
            }
            case "Libras Esterlinas" -> {
                return "GBP";
            }

            case "Yen Japonés" -> {
                return "JPY";
            }
            case "Won sul-coreano" -> {
                return "KRW";
            }
            case "Pesos colombianos" -> {
                return "COP";
            }
            default -> {
                return "ERROR";
            }
        }
    }
}