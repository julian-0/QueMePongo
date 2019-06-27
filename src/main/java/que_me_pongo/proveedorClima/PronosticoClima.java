package que_me_pongo.proveedorClima;

import java.time.LocalDateTime;

public class PronosticoClima {
	private LocalDateTime fecha;
	private double temperatura;
	//TODO Rellenar
	
	public PronosticoClima(LocalDateTime fechaPronostico, double temperatura) {
		this.fecha = fechaPronostico;
		this.temperatura = temperatura;
	}
	
	public LocalDateTime getFechaPronostico() {
		return fecha;
	}
	public double getTemperatura() {
		return temperatura;
	}
	
	public boolean difiere(PronosticoClima otro) {
		//TODO poner más condiciones una vez se rellene la clase
		return Math.abs(this.temperatura - otro.getTemperatura()) > 10;
	}
}
