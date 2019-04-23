package Que_me_pongo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Guardarropa {
	public Map<Categoria,Set<Prenda>> prendas = new HashMap<Categoria, Set<Prenda>>();
	
	public Guardarropa()
	{
		prendas.put(Categoria.SUPERIOR, new HashSet<Prenda>());
		prendas.put(Categoria.INFERIOR, new HashSet<Prenda>());
		prendas.put(Categoria.CALZADO, new HashSet<Prenda>());
		prendas.put(Categoria.ACCESORIO, new HashSet<Prenda>());
	}

	public void agregarPrenda(Prenda prenda) {
		Categoria categoria = prenda.getCategoria();
		prendas.get(categoria).add(prenda);
	}
	
	public int cantidadPrendasEn(Categoria categoria) {
		return prendas.get(categoria).size();
	}
	
//	protected List<Atuendo> atuendos(){
//		
//	}

}
