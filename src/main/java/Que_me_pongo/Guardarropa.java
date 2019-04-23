package Que_me_pongo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Guardarropa {
	public Map<Categoria,List<Prenda>> prendas = new HashMap<Categoria, List<Prenda>>();
	
	public Guardarropa()
	{
		prendas.put(Categoria.SUPERIOR, new LinkedList<Prenda>());
		prendas.put(Categoria.INFERIOR, new LinkedList<Prenda>());
		prendas.put(Categoria.CALZADO, new LinkedList<Prenda>());
		prendas.put(Categoria.ACCESORIO, new LinkedList<Prenda>());
	}

	protected void agregarPrenda(Prenda prenda) {
		
	}
	
//	protected List<Atuendo> atuendos(){
//		
//	}

}
