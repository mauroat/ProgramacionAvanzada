package kruskal;

public class Nodo {
	private int indice;
	private int grado;
	private int dato;
	
	public Nodo()
	{
		this.indice = 0;
		this.grado = 0;
		this.dato = 0;
	}
	
	public Nodo(int indice)
	{
		this.indice = indice;
		this.grado = 0;
		this.dato = 0;
	}
	
	public Nodo(int indice, int grado, int dato)
	{
		this.indice = indice;
		this.grado = grado;
		this.dato = dato;
	}
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
}
