package que_me_pongo;

import que_me_pongo.prenda.Prenda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
public class Atuendo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private List<Prenda> prendas;

    public Atuendo() {
    }

    public Atuendo(List<Prenda> p){
        prendas=p;
    }
    
    public List<Prenda> getPrendas(){
    	return prendas;
    }
    
    public boolean tienePrenda(Prenda prenda) {
    	return getPrendas().contains(prenda);
    }
    
    public int cantidadPrendas() {
    	return getPrendas().size();
    }
    
    public boolean mismoAtuendo(Atuendo otro) {
  		return cantidadPrendas() == otro.cantidadPrendas() && 
  				getPrendas().stream().allMatch(prenda -> otro.tienePrenda(prenda));
  	}
}
