package Que_me_pongo.proveedorClima;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.time.LocalDate;

public class ClimaOpenWeather implements ProveedorClima {

    public ClimaOpenWeather() {
    }

    @Override
    public double getTemp(LocalDate date) {
        String owAPIKey = "6e68fbf87908ad1457a13fc6946d138e";
        String ciudad = "Buenos Aires, Argentina";
        String baseURL = "http://api.openweathermap.org/data/2.5/forecast";
        double temperatura = 0;
        LocalDate now = LocalDate.now();

        if(date.isBefore(now) || date.isAfter(now.plusDays(5))) {
            throw new RangoDiasException("La fecha pasada por parámetro se encuentra fuera del rango de días disponible.");
        }

        Client client = Client.create();
        WebResource webResource = client.resource(baseURL)
                .queryParam("q", ciudad)
                .queryParam("APPID", owAPIKey);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        String output = response.getEntity(String.class);

        Gson g = new Gson();
        JsonObject jo = g.fromJson(output, JsonObject.class);

        JsonArray weathers = jo.get("list").getAsJsonArray();

        String dailyDate;
        int i=0;
        for (JsonElement dailyWeather : weathers) {
            dailyDate = dailyWeather.getAsJsonObject().get("dt_txt").getAsString();
            if (LocalDate.parse(dailyDate.substring(0, 10)).isEqual(date)) {
                i++;
                temperatura += dailyWeather.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
            }
        }


        return ((temperatura/i) - 273.15);
    }
}
