package grafos;

public class Nodo {
	private int indice;
	private int color;
	private int grado;
	
	public Nodo(int indice, int color, int grado) {
		this.indice = indice;
		this.color = color;
		this.grado = grado;
	}
	
	public Nodo(int indice){
		this.indice = indice;
		this.color = 0;
		this.grado = 1;
	}

	public int getIndice() {
		return indice;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getGrado() {
		return grado;
	}

	public void incrementarGrado() {
		this.grado++;
	}
	
}
