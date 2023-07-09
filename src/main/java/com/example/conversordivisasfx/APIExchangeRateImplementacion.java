package com.example.conversordivisasfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIExchangeRateImplementacion {
    public static String convertir(String base, String destino, String importe) {

        String accessKey = "7a3736d8401e85227eae59ef";
        String endpoint = "https://v6.exchangerate-api.com/v6/" + accessKey + "/pair/" + base + "/" + destino + "/" + importe;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                System.out.println(response);
                return response.toString();
            } else {
                System.out.println("Error en la solicitud. Código de respuesta: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error en la conversion";
    }

}
