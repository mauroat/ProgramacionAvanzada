package grafos;

public class MatrizSimetrica {

	private int cantNodos;
	private int tamano;
	private boolean[] vector;
	
	public MatrizSimetrica(int cantNodos){
		this.cantNodos = cantNodos;
		this.tamano = (int) (Math.pow(cantNodos, 2) - cantNodos) / 2; //cálculo de tamaño de vector
		this.vector = new boolean[tamano];
		for (int i = 0; i < cantNodos; i++){
			vector[i] = false;
		}
	}
	
	private int encontrarPosicion(int origen, int destino) {
		
		if (origen > destino) {
			int aux = origen;
			origen = destino;
			destino = aux;
		}
		return (int) (origen * cantNodos + destino - (Math.pow(origen, 2) + 3 * origen + 2) / 2);
	}

	public boolean getDato(int origen, int destino) {

		int pos = encontrarPosicion(origen, destino);
		return vector[pos];

	}
	
	public void setDato(int origen, int destino) {
		if (origen != destino) {
			int pos = encontrarPosicion(origen, destino);
			vector[pos] = true;
		}
	}
}
