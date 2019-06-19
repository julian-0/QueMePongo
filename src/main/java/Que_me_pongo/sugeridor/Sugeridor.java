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
		return Range.open(celsius-margen, celsius+margen).contains(1 / nivelAbrigoTotal);
	}
	
	private double reducirNivelAbrigo(double nivelPrenda1, Prenda prenda2) {
		return nivelPrenda1 + prenda2.getNivelAbrigo();
	}
	

}
