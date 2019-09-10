package que_me_pongo;

import que_me_pongo.prenda.Prenda;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

public class Atuendo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private List<Prenda> prendas;

    public Atuendo(List<Prenda> p){
        prendas=p;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    public void setPrendas(List<Prenda> prendas) {
        this.prendas = prendas;
    }
}
