package Que_me_pongo.sugeridor;

public class ConfiguracionesSugeridor {
	private double margenBase, margenExtendido;
	private int cantParaExtender;
	
	public ConfiguracionesSugeridor(double margenBase, double margenExtendido, int cantParaExtender) {
		this.margenBase = margenBase;
		this.margenExtendido = margenExtendido;
		this.cantParaExtender = cantParaExtender;
	}
	
	public double getMargenBase() {
		return this.margenBase;
	}
	
	public double getMargenExtendido() {
		return this.margenExtendido;
	}
	
	public int getCantParaExtender() {
		return this.cantParaExtender;
	}

}
