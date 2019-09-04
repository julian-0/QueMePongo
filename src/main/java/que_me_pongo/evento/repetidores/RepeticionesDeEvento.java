package que_me_pongo.evento.repetidores;

public class RepeticionesDeEvento {
	static public RepeticionDeEvento noRepite() {
		return null; 
	}
	
	static public RepeticionDeEvento semanal() {
		return (fecha) -> fecha.plusWeeks(1);
	}
	
	static public RepeticionDeEvento diario() {
		return (fecha) -> fecha.plusDays(1);
	}
	
	static public RepeticionDeEvento mensual() {
		return (fecha) -> fecha.plusMonths(1);
	}
	
	static public RepeticionDeEvento anual() {
		return (fecha) -> fecha.plusYears(1);
	}
}
