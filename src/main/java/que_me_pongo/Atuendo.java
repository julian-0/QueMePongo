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
}
