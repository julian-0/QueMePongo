package Que_me_pongo.evento;

import java.time.LocalDate;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Que_me_pongo.prenda.Categoria;
import Que_me_pongo.prenda.Prenda;
import Que_me_pongo.sugeridor.Sugeridor;
import Que_me_pongo.usuario.Usuario;

public class Evento {
    LocalDate fecha;
    Usuario usuario;
    String lugar;
    Deque<List<Prenda>> sugerencias, rechazados;
    List<Prenda> aceptado;

    //Se crea un evento y se carga en el repo de eventos
    public Evento(LocalDate fecha,Usuario usuario,String lugar) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.lugar = lugar;

        RepositorioEventos.getInstance().agendar(this);
    }

    //Le va a cargar una lista de atuendos al usuario en su lista atuendos pendientes
    public void sugerir(Sugeridor sugeridor){
        sugerencias = new LinkedList<List<Prenda>>(sugeridor.sugerir(usuario.atuendos()));
        rechazados = new LinkedList<List<Prenda>>();
        //Avisar a listeners
    }
    
    public void rechazarSugerencia() {
    	validarExistenSugerencias();
    	validarNoAceptado();
    	
    	//removeFirst tira su propia exception si está vacia, tal vez atajarlo antes y tirar una nuestra
    	rechazados.add(sugerencias.removeFirst());
    }
    
    public void aceptarSugerencia(Set<Categoria> aumentarAbrigo, Set<Categoria> reducirAbrigo) {
    	validarExistenSugerencias();
    	validarNoAceptado();
    	aceptado = sugerencias.removeFirst();
    	//Pasar info al usuario
    }
    
    public void deshacerDecicion() {
    	validarExistenSugerencias();
    	if(aceptado != null) {
    		sugerencias.addFirst(aceptado);
    		aceptado = null;
    	}
    	else if(!rechazados.isEmpty()) {
    		sugerencias.addFirst(rechazados.removeLast());
    	}
    	else
    		throw new NoHistorialException();
    }

    /*Verdadero si las fechas coinciden o la fecha del evento es posterior a la consultada
    y ademas esta dentro del rango de cantDias
    */
    public boolean esProximo(LocalDate fConsultada, int cantDias){
        return fConsultada.isEqual(fecha) || (fConsultada.isBefore(fecha) && fecha.isBefore(fConsultada.plusDays(cantDias)));
    }
    
    private void validarNoAceptado() {
    	if(aceptado != null)
    		throw new AtuendoYaDecididoException();
    }
    
    private void validarExistenSugerencias() {
    	if(sugerencias == null)
    		throw new SinSugerenciasException();
    }
}