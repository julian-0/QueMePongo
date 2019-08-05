package que_me_pongo.window;

import org.apache.commons.collections15.Transformer;
import que_me_pongo.prenda.Prenda;

import java.util.Deque;
import java.util.List;

public final class CollectionToSiNoTransformer implements Transformer<Deque<List<Prenda>>, String> {
    @Override
    public String transform(Deque<List<Prenda>> sugerencias) {
        return sugerencias==null ? "NO" : "SI";
    }
}
