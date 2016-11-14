package grafo;
// Comparable<Arista> para usarlo en un array
public class Arista implements Comparable<Arista>{

	private int desde;
	private int hasta;
	private int distancia;
	private int posicion;
	
	public Arista(int posicion, int desde, int hasta, int distancia) {
		this.posicion = posicion;
		this.desde = desde;
		this.hasta = hasta;
		this.distancia = distancia;
	}

	public int getDesde() {
		return desde;
	}

	public void setDesde(int desde) {
		this.desde = desde;
	}

	public int getHasta() {
		return hasta;
	}

	public void setHasta(int hasta) {
		this.hasta = hasta;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	/*
	 *  Comparador
	 * */
	@Override
	public int compareTo(Arista a) {
		if (this.distancia > a.distancia) {
			return 1;
		}else if (this.distancia < a.distancia) {
			return -1;
		}else{
			return 0;
		}
	}
	
	
}
