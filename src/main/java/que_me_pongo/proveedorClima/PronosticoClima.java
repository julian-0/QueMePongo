package que_me_pongo.proveedorClima;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import que_me_pongo.LocalDateTimeAttributeConverter;

import java.time.LocalDateTime;

@Entity
public class PronosticoClima {
	@Id
	@GeneratedValue
	private Long id;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
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
