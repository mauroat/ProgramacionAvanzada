package model;

public class Nodo implements Comparable<Nodo>{
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
	
	public Nodo(Nodo otro){
		this(otro.getIndice(), otro.getColor(), otro.getGrado());
	}

	public int getIndice() {
		return indice;
	}
	
	public void setColor(int color){
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public int getGrado() {
		return grado;
	}
	
	public void addGrado(){
		this.grado++;
	}

	@Override
	public int compareTo(Nodo otro) {
		if (this.grado > otro.getGrado()) {
			return 1;
		}else if (this.grado < otro.getGrado()) {
			return -1;
		}else{
			return 0;
		}
	}

	public void clean() {
		this.color = 0;
	}

	@Override
	public String toString() {
		return "[" + indice + ", " + color + ", "+ grado + "]";
	}
}
