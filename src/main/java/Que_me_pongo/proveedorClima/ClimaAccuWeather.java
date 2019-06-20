package Que_me_pongo.proveedorClima;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClimaAccuWeather implements ProveedorClima {

	@Override
	public double getTemp(LocalDate date) {
		//awAPIKey=YvIhxAway23Sl6WGzmUMltTS2ZpAn0oM
		String APIKey = System.getenv("awAPIKey");
		String ciudad = "buenos aires, argentina";
		//awSearchAPIURL=http://dataservice.accuweather.com/locations/v1/cities/search
		String codeURL = System.getenv("awSearchAPIURL");
		//awForecastAPIURL=http://dataservice.accuweather.com/forecasts/v1/daily/5day
		String forecastURL = System.getenv("awForecastAPIURL");
		LocalDate ahora = LocalDate.now();
		
		if(date.isBefore(ahora) || date.isAfter(ahora.plusDays(5))) 
      throw new RangoDiasException("");
		
		Client client = Client.create();
    WebResource webResource = client.resource(codeURL)
            .queryParam("q", ciudad)
            .queryParam("apikey", APIKey);
    ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
    
    if (response.getStatus() != 200)
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
    
    String output = response.getEntity(String.class);
    JsonArray list = new Gson().fromJson(output, JsonArray.class);
    String codigoCiudad = list.get(0).getAsJsonObject().get("Key").getAsString();
    
    webResource = client.resource(forecastURL).
    								queryParam("q", codigoCiudad).
    								queryParam("apikey", APIKey).
    								queryParam("metric", "true").
    								path(codigoCiudad);
    
    response = webResource.accept("application/json").get(ClientResponse.class);
    
    if (response.getStatus() != 200)
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
    
    output = response.getEntity(String.class);
    
    JsonArray dailyWeathers = new Gson().fromJson(output, JsonObject.class).get("DailyForecasts").getAsJsonArray();
    
    double temperaturaPromedio = this.temperaturaPromedio(dailyWeathers, date);
    
    return temperaturaPromedio;
	}
	
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
  	String dailyDate = dailyWeather.getAsJsonObject().get("Date").getAsString();
  	return LocalDate.parse(dailyDate.substring(0, 10)).isEqual(date);
  }
  
  private double getTempOfWeather(JsonElement dailyWeather)
  {
  	JsonObject temperatura = dailyWeather.getAsJsonObject().get("Temperature").getAsJsonObject();
  	double minima = temperatura.get("Minimum").getAsJsonObject().get("Value").getAsDouble();
  	double maxima = temperatura.get("Maximum").getAsJsonObject().get("Value").getAsDouble();
  	return (minima + maxima) / 2;
  }

}
