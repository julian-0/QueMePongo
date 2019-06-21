package Que_me_pongo.proveedorClima;

import java.time.LocalDate;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public abstract class APIProviders implements ProveedorClima {

	@Override
	public double getTemp(LocalDate date) {
		validateDate(date, LocalDate.now());
    
    return proccessJson(runQuery(baseURL(), queryParams()), date);
	}
	
	protected void validateDate(LocalDate date, LocalDate hoy) {
		if(date.isBefore(hoy) || date.isAfter(hoy.plusDays(maxAmountOfDays()))) 
      throw new RangoDiasException("La fecha pasada por parámetro se encuentra fuera del rango de días disponible.");
	}
	
	protected void validateStatusCode(int statusCode) {
		if (statusCode != 200)
      throw new RuntimeException("Failed : HTTP error code : " + statusCode);
	}
	
	protected JsonElement runQuery(String url, MultivaluedMap<String, String> params) {
		Client client = Client.create();
    WebResource webResource = client.resource(url).queryParams(params);
    ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
    
    validateStatusCode(response.getStatus());
    
    String output = response.getEntity(String.class);
    
    Gson g = new Gson();
    return g.fromJson(output, JsonElement.class);
	}
	
	abstract protected String baseURL();
	abstract protected MultivaluedMap<String, String> queryParams();
	abstract protected int maxAmountOfDays();
	abstract protected double proccessJson(JsonElement obj, LocalDate date);

}
