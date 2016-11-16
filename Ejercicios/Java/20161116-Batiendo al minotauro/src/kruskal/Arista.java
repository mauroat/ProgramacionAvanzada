package kruskal;

public class Arista implements Comparable<Arista> {
	private int origen;
	private int destino;
	private int costo;
	
	public Arista()
	{
		this.origen = 0;
		this.destino = 0;
		this.costo = 0;
	}
	
	public Arista(int origen, int destino, int costo)
	{
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}
	
	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	@Override
	public int compareTo(Arista a) {
		if(this.costo > a.costo)
			return 1;
		else if(this.costo < a.costo)
			return -1;
		else
			return 0;
	}
	
	
}
