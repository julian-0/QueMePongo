 package Que_me_pongo.proveedorClima;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.MultivaluedMap;

public class ClimaOpenWeather extends APIProviders {

    public ClimaOpenWeather() {
    }

//    @Override
//    public double getTemp(LocalDate date) {
//    		//owAPIKey=6e68fbf87908ad1457a13fc6946d138e
//        String owAPIKey = System.getenv("owAPIKey");
//        String ciudad = "Buenos Aires, Argentina";
//        //owBaseURL=http://api.openweathermap.org/data/2.5/forecast
//        String baseURL = System.getenv("owBaseURL");
//        LocalDate now = LocalDate.now();
//
//        if(date.isBefore(now) || date.isAfter(now.plusDays(5))) {
//            throw new RangoDiasException("La fecha pasada por parámetro se encuentra fuera del rango de días disponible.");
//        }
//
//        Client client = Client.create();
//        WebResource webResource = client.resource(baseURL)
//                .queryParam("q", ciudad)
//                .queryParam("APPID", owAPIKey);
//        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//
//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
//        }
//
//        String output = response.getEntity(String.class);
//
//        Gson g = new Gson();
//        JsonObject jo = g.fromJson(output, JsonObject.class);
//
//        JsonArray weathers = jo.get("list").getAsJsonArray();
//
//        Double temperatura = this.temperaturaPromedio(weathers, date);
//
//        return (temperatura - 273.15);
//    }
    
    private double temperaturaPromedio(JsonArray weathers, LocalDate date)
    {
    	List<Double> predictions = StreamSupport.stream(weathers.spliterator(), false).
    													filter(dailyWeather -> this.weatherIsOf(date, dailyWeather)).
    													<Double>map(dailyWeather -> this.getTempOfWeather(dailyWeather)).
    													collect(Collectors.toList());
    	return predictions.stream().reduce(.0, Double::sum) / predictions.size();
    }
    
    private boolean weatherIsOf(LocalDate date, JsonElement dailyWeather)
    {
    	String dailyDate = dailyWeather.getAsJsonObject().get("dt_txt").getAsString();
    	return LocalDate.parse(dailyDate.substring(0, 10)).isEqual(date);
    }
    
    private double getTempOfWeather(JsonElement dailyWeather)
    {
    	return dailyWeather.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
    }

		@Override
		protected String baseURL() {
			//owBaseURL=http://api.openweathermap.org/data/2.5/forecast
      return System.getenv("owBaseURL");
		}

		@Override
		protected MultivaluedMap<String, String> queryParams() {
			//owAPIKey=6e68fbf87908ad1457a13fc6946d138e
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			map.add("q", "Buenos Aires, Argentina");
			map.add("APPID", System.getenv("owAPIKey"));
			return map;
		}

		@Override
		protected int maxAmountOfDays() {
			return 5;
		}

		@Override
		protected double proccessJson(JsonElement obj, LocalDate date) {
			JsonArray weathers = obj.getAsJsonObject().get("list").getAsJsonArray();

      Double temperatura = temperaturaPromedio(weathers, date);

      return (temperatura - 273.15);
		}
}
