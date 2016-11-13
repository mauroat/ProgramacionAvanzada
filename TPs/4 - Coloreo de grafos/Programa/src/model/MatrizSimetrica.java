package model;

public class MatrizSimetrica {
	private int cantidadNodos;
	private boolean vector[];

	public MatrizSimetrica(int cantNodos) {
		int tam = (int) (Math.pow(cantNodos, 2) - cantNodos) / 2;
		this.cantidadNodos = cantNodos;
		this.vector = new boolean[tam];
		for (int i = 0; i < vector.length; i++) {
			vector[i] = false;
		}
	}

	public void setNodo(int nodoOrigen, int nodoDestino) {
		if (nodoOrigen != nodoDestino) {
			int pos = getPosicionVector(nodoOrigen, nodoDestino);
			vector[pos] = true;
		}
	}

	public boolean getNodo(int nodoOrigen, int nodoDestino) {

		int pos = getPosicionVector(nodoOrigen, nodoDestino);
		return vector[pos];

	}

	public int getPosicionVector(int nodoOrigen, int nodoDestino) {
		if (nodoOrigen > nodoDestino) {
			int aux = nodoOrigen;
			nodoOrigen = nodoDestino;
			nodoDestino = aux;
		}
		return (int) (nodoOrigen * cantidadNodos + nodoDestino - (Math.pow(
				nodoOrigen, 2) + 3 * nodoOrigen + 2) / 2);
	}

	public boolean isAdyacentes(int nodoOrigen, int nodoDestino) {
		return this.vector[this.getPosicionVector(nodoOrigen, nodoDestino)];
	}

}