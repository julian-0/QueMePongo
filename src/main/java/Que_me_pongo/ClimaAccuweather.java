package Que_me_pongo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClimaAccuweather implements ProveedorClima {

    public ClimaAccuweather() {
    }

    @Override
    public double getTemp() {
        String owAPIKey = "6e68fbf87908ad1457a13fc6946d138e";
        String ciudad = "Buenos%20Aires,%20Argentina";
        String baseURL = "http://api.openweathermap.org/data/2.5/weather";
        double temperatura;

        Client client = Client.create();
        WebResource webResource = client.resource(baseURL + "?q=" + ciudad + "&APPID=" + owAPIKey);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        String output = response.getEntity(String.class);

        Gson g = new Gson();
        JsonObject jo = g.fromJson(output, JsonObject.class);

        temperatura = jo.get("main").getAsJsonObject().get("temp").getAsDouble();

        return (temperatura - 273.15);
    }
}
