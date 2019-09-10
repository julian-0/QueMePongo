package que_me_pongo.prenda;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

public class GrupoDePrendas {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private Set<Prenda> prendas;
}
