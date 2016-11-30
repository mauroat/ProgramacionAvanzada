package mapa;

/*Se utiliza para convertir el mapa en grafo y de ahi, calcular el camino mas corto.*/
public class Grafo {
	
	private int cantNodos;
	private int cantNodosTotal;
	private Nodo [] nodos;
	
	public Grafo(int cantNodosTotal){
		cantNodos = 0;
		nodos = new Nodo[cantNodosTotal];
		this.cantNodosTotal = cantNodosTotal;
	}
	
	public void agregarNodo(Nodo nodo){
		nodos [cantNodos++] = nodo;
	}
	
	public void agregarAdyacentes(Nodo nodoUno, Nodo nodoDos){
		nodoUno.agregarAdyacente(nodoDos);
	}
	
	public Nodo [] obtenerNodos(){
		return nodos;
	}
	
	public int obtenerCantidadDeNodos(){
		return cantNodos;
	}
	
	public int obtenerCantidadDeNodosTotal(){
		return cantNodosTotal;
	}

}
