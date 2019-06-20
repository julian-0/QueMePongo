package Que_me_pongo.sugeridor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Range;

import Que_me_pongo.configuraciones.Configuraciones;
import Que_me_pongo.prenda.Prenda;
import Que_me_pongo.proveedorClima.ProveedorClima;

public class Sugeridor {
	private double tMax = 20, abrigoMin = 3, abrigoParaCero = 10;
	private double margenBase, margenExtendido;
	private int cantParaExtender;
	private LocalDate fecha;
	
	public Sugeridor(double margenBase, double margenExtendido, int cantParaExtender, LocalDate fecha)
	{
		this.margenBase = margenBase;
		this.margenExtendido = margenExtendido;
		this.cantParaExtender = cantParaExtender;
		this.fecha = fecha;
	}
	
	public Set<List<Prenda>> sugerir(Set<List<Prenda>> atuendos) {
		double celsius = Configuraciones.get(ProveedorClima.class).getTemp(this.fecha);
		Set<List<Prenda>> sugeridos = this.filtrar(atuendos, celsius, this.margenBase);
		
		if(sugeridos.size() <= this.cantParaExtender)
			sugeridos = this.filtrar(atuendos, celsius, this.margenExtendido);
		
		return sugeridos;
	}
	
	private Set<List<Prenda>> filtrar(Set<List<Prenda>> atuendos, double celsius, double margen) {
		return atuendos.stream().
		 filter(atuendo -> this.sugerirAtuendo(atuendo, celsius, margen)).
		 collect(Collectors.toSet());
	}
	
	private boolean sugerirAtuendo(List<Prenda> atuendo, double celsius, double margen) {
		double nivelAbrigoTotal = atuendo.stream().reduce(.0, this::reducirNivelAbrigo, (n1, n2) -> n1 + n2);
		if(celsius >= 20)
			celsius = 20;
		return Range.closed(celsius-margen, celsius+margen).contains(aTemperatura(nivelAbrigoTotal));
	}
	
	private double reducirNivelAbrigo(double nivelPrenda1, Prenda prenda2) {
		return nivelPrenda1 + prenda2.getNivelAbrigo();
	}
	
	private double aTemperatura(double nivelAbrigo)
	{
		/*
		 * Una funcion lineal donde el nivel de abrigo minimo (3)
		 * pasa por la temperatura maxima (20) y el nivel de abrigo 
		 * 10 pasa por 0 grados
		 */
		return (-tMax / (abrigoParaCero - abrigoMin)) * nivelAbrigo + abrigoParaCero * tMax / (abrigoParaCero- abrigoMin);
	}
}
