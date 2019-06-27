package que_me_pongo.sugeridor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Range;

import que_me_pongo.configuraciones.Configuraciones;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.proveedorClima.PronosticoClima;
import que_me_pongo.proveedorClima.ProveedorClima;

public class Sugeridor {
	private double margenBase, margenExtendido;
	private int cantParaExtender;
	
	public Sugeridor(double margenBase, double margenExtendido, int cantParaExtender)
	{
		this.margenBase = margenBase;
		this.margenExtendido = margenExtendido;
		this.cantParaExtender = cantParaExtender;
	}
	
	public Set<List<Prenda>> sugerir(Set<List<Prenda>> atuendos, PronosticoClima pronostico) {
		Set<List<Prenda>> sugeridos = this.filtrar(atuendos, pronostico, this.margenBase);
		
		if(sugeridos.size() <= this.cantParaExtender)
			sugeridos = this.filtrar(atuendos, pronostico, this.margenExtendido);
		
		return sugeridos;
	}
	
	private Set<List<Prenda>> filtrar(Set<List<Prenda>> atuendos, PronosticoClima pronostico, double margen) {
		return atuendos.stream().
		 filter(atuendo -> this.sugerirAtuendo(atuendo, pronostico, margen)).
		 collect(Collectors.toSet());
	}
	
	private boolean sugerirAtuendo(List<Prenda> atuendo, PronosticoClima pronostico, double margen) {
		double nivelAbrigoTotal = atuendo.stream().reduce(.0, this::reducirNivelAbrigo, (n1, n2) -> n1 + n2);
		return Range.closed(pronostico.getTemperatura()-margen, pronostico.getTemperatura()+margen).
								 contains(aTemperatura(nivelAbrigoTotal));
	}
	
	private double reducirNivelAbrigo(double nivelPrenda1, Prenda prenda2) {
		return nivelPrenda1 + prenda2.getNivelAbrigo();
	}
	
	private double aTemperatura(double nivelAbrigo)
	{
		/*
		 * Una funcion lineal que asume lo siguiente:
		 * -Con tres prendas basicas obligatorias, hay un nivel de abrigo de tres y eso sirve para dieciocho grados
		 * -Por cada tres grados que desciende la temperatura, aumento en dos el nivel de abrigo
		 */
		return -1.5 * nivelAbrigo + 22.5;
	}
}
