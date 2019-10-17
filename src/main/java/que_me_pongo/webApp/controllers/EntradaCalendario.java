package que_me_pongo.webApp.controllers;

import java.time.LocalDateTime;

public class EntradaCalendario {
	public String title;
	public String start;
	
	public EntradaCalendario(String title, LocalDateTime start) {
		this.title = title;
		this.start = start.toString().substring(0, 19);
	}

}
