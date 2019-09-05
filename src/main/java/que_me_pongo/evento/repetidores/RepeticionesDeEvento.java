package que_me_pongo.evento.repetidores;

public class RepeticionesDeEvento {
	static public RepeticionDeEvento noRepite() {
		return (evento) -> {}; 
	}
	
	static public RepeticionDeEvento semanal() {
		return (evento) -> evento.generarRepeticion(evento.getFecha().plusWeeks(1));
	}
	
	static public RepeticionDeEvento diario() {
		return (evento) -> evento.generarRepeticion(evento.getFecha().plusDays(1));
	}
	
	static public RepeticionDeEvento mensual() {
		return (evento) -> evento.generarRepeticion(evento.getFecha().plusMonths(1));
	}
	
	static public RepeticionDeEvento anual() {
		return (evento) -> evento.generarRepeticion(evento.getFecha().plusYears(1));
	}
}
