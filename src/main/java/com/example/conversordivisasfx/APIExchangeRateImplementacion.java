package com.example.conversordivisasfx;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class APIExchangeRateImplementacion {
    public static String convertir(String base, String destino, String importe) {

        String endpoint = "https://v6.exchangerate-api.com/v6/7a3736d8401e85227eae59ef" + "/pair/" + base + "/" + destino + "/" + importe;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(endpoint);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    String responseBody = EntityUtils.toString(entity);
                    System.out.println(responseBody);
                    return responseBody;
                } else {
                    System.out.println("Error en la solicitud. Código de respuesta: " + statusCode);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error en la conversión";
    }

}
