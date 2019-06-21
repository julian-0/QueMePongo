package Que_me_pongo.proveedorClima;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ClimaAccuWeather extends APIProviders  {
	@Override
	protected String baseURL() {
		String cityCode = getCityCode();
		//awForecastAPIURL=http://dataservice.accuweather.com/forecasts/v1/daily/5day
		return System.getenv("awForecastAPIURL") + "/" + cityCode;
	}

	@Override
	protected MultivaluedMap<String, String> queryParams() {
		//awAPIKey=YvIhxAway23Sl6WGzmUMltTS2ZpAn0oM
		MultivaluedMap<String, String> map = new MultivaluedMapImpl();
		map.add("apikey", System.getenv("awAPIKey"));
		map.add("metric", "true");
		return map;
	}

	@Override
	protected int maxAmountOfDays() {
		return 4;
	}

	@Override
	protected double proccessJson(JsonElement obj, LocalDate date) {
		JsonArray dailyWeathers = obj.getAsJsonObject().get("DailyForecasts").getAsJsonArray();
    
    return temperaturaPromedio(dailyWeathers, date);
	}
	
	private String getCityCode() {
		//awSearchAPIURL=http://dataservice.accuweather.com/locations/v1/cities/search
		String codeURL = System.getenv("awSearchAPIURL");
		
		MultivaluedMap<String, String> map = new MultivaluedMapImpl();
		map.add("apikey", System.getenv("awAPIKey"));
		map.add("q", "buenos aires, argentina");
		
		JsonElement je = runQuery(codeURL, map);
		
		return je.getAsJsonArray().get(0).getAsJsonObject().get("Key").getAsString();
	}
	
	private double temperaturaPromedio(JsonArray weathers, LocalDate date) {
  	List<Double> predictions = StreamSupport.stream(weathers.spliterator(), false).
  													filter(dailyWeather -> this.weatherIsOf(date, dailyWeather)).
  													<Double>map(dailyWeather -> this.getTempOfWeather(dailyWeather)).
  													collect(Collectors.toList());
  													
  	return predictions.stream().reduce(.0, Double::sum) / predictions.size();
  }
  
  private boolean weatherIsOf(LocalDate date, JsonElement dailyWeather) {
  	String dailyDate = dailyWeather.getAsJsonObject().get("Date").getAsString();
  	return LocalDate.parse(dailyDate.substring(0, 10)).isEqual(date);
  }
  
  private double getTempOfWeather(JsonElement dailyWeather) {
  	JsonObject temperatura = dailyWeather.getAsJsonObject().get("Temperature").getAsJsonObject();
  	double minima = temperatura.get("Minimum").getAsJsonObject().get("Value").getAsDouble();
  	double maxima = temperatura.get("Maximum").getAsJsonObject().get("Value").getAsDouble();
  	return (minima + maxima) / 2;
  }

}
