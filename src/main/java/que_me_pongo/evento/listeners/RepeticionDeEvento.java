package que_me_pongo.evento.listeners;

import que_me_pongo.evento.Evento;

public class RepeticionDeEvento {
	static public EventoListener noRepite() {
		return (ev) -> {}; 
	}
	
	static public EventoListener semanal() {
		return (ev) -> new Evento(ev.getFecha().plusWeeks(1), ev.getUsuario(), ev.getDescripcion(), ev.getListenersSugerir());
	}
	
	static public EventoListener diario() {
		return (ev) -> new Evento(ev.getFecha().plusDays(1), ev.getUsuario(), ev.getDescripcion(), ev.getListenersSugerir());
	}
	
	static public EventoListener mensual() {
		return (ev) -> new Evento(ev.getFecha().plusMonths(1), ev.getUsuario(), ev.getDescripcion(), ev.getListenersSugerir());
	}
	
	static public EventoListener anual() {
		return (ev) -> new Evento(ev.getFecha().plusYears(1), ev.getUsuario(), ev.getDescripcion(), ev.getListenersSugerir());
	}
}
