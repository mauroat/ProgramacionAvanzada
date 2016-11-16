package rescatando;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Grafo {

	private final static int INF = 999999;
	private final static int NONE = -2;
	
	private int cantidadNodos;
	private int cantidadAristas;
	private int cantidadDragones;
	private int nodoPrincipe, nodoPrincesa;
	private int[] nodosDragones;	
	private int[][] matriz;
	
	private List<Arista> aristas;
	private Nodo[] listaNodos;
	
	// para dijsktra
	private int[] distancias;
	private int[] camino;
	private boolean[] vistos;
	private int[][] matrizCostos;
	private Nodo nodoOrigen;
	
	public Nodo getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(Nodo nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public Grafo(String path) throws FileNotFoundException{
		Scanner sc = new Scanner (new File(path));
		
		this.cantidadNodos = sc.nextInt();
		this.listaNodos = new Nodo[this.cantidadNodos];
		inicializarNodos();
		
		this.cantidadAristas = sc.nextInt();
		this.aristas = new LinkedList<Arista>();
		
		this.cantidadDragones = sc.nextInt();
		this.nodosDragones = new int[this.cantidadDragones];
		
		this.nodoPrincesa = sc.nextInt();
		this.listaNodos[nodoPrincesa-1].setOcupadoPor("Princesa");
		
		this.nodoPrincipe = sc.nextInt();
		this.listaNodos[nodoPrincipe-1].setOcupadoPor("Principe");
		
				
		for(int i=0; i < this.cantidadDragones; i++){
			int pos = sc.nextInt();
			this.listaNodos[pos-1].setOcupadoPor("Dragon");	
		}
				
		
		for(int i=0 ; i < this.cantidadAristas ; i++){
			Nodo origen = listaNodos[sc.nextInt()-1];
			Nodo destino = listaNodos[sc.nextInt()-1];
			
			Arista a = new Arista (origen,destino,sc.nextInt());
			aristas.add(a);
		}
		
			
		sc.close();
	}

	private void inicializarNodos() {
		for (int i = 1; i < this.cantidadNodos+1;i++)
			this.listaNodos[i-1] = new Nodo(i);	
	}
	
	private void inicializarMatriz() {
		for (int i = 0; i < this.cantidadNodos;i++)
			for (int j = 0; j < this.cantidadNodos;j++)
				matriz[i][j] = INF;
	}
	
	 
	
	public void dijsktra(){
		
		/*
		 * Inicializo todo
		 * */
		this.distancias = new int[cantidadNodos];
		this.camino = new int[cantidadNodos];
		this.vistos = new boolean[cantidadNodos];
		this.matrizCostos = new int[cantidadNodos][cantidadNodos];
		
		Arrays.fill(distancias, INF);
		Arrays.fill(camino, NONE);
		Arrays.fill(vistos, false);
		
		for (int i = 0; i < matrizCostos.length; i++) 
			for (int j = 0; j < matrizCostos.length; j++) 
				matrizCostos[i][j] = INF;
			
		
		for (Arista arista : aristas) {
			int origen = arista.getOrigen().getPosicion();
			int destino = arista.getDestino().getPosicion();
			int costo = arista.getPeso();
			matrizCostos[origen-1][destino-1] = costo;
		}
		
		this.nodoOrigen = nodoOrigen;
		
		/*
		 * Comienzo a resolver
		 * */
		
		Nodo origen = null;
		boolean first = true;
		int nodosVistos = 0;
		while (nodosVistos < listaNodos.length) {
			if (first) {
				origen = listaNodos[0];
				distancias[0] = 0;
				camino[0] = 0;
				first = false;
			}else{
				origen = nextMenor(origen);
			}
			
			vistos[origen.getPosicion()-1] = true;
			nodosVistos++;
			caminoMasCorto(origen);
		}
		
	}
	
	// actualizo las distancias relativas al nodo origen
	private void caminoMasCorto(Nodo origen){
		for (int i = 0; i < listaNodos.length; i++) {
			if (origen.getPosicion() != i) {
				if (isAdyacentes(origen,listaNodos[i])) {
					if (!vistos[listaNodos[i].getPosicion()]) {
						int acum = distancias[origen.getPosicion()] + matrizCostos[origen.getPosicion()][listaNodos[i].getPosicion()];
						if(distancias[listaNodos[i].getPosicion()] > acum){
							distancias[listaNodos[i].getPosicion()] = acum;
							camino[listaNodos[i].getPosicion()] = origen.getPosicion();
						}
					}
				}				
			}
		}
	}
	
	private Nodo nextMenor(Nodo actual) {
		Nodo next = null;
		int costoMenor = INF+1;
		for (int i = 0; i < listaNodos.length; i++) {
			if (actual.getPosicion() != i) {
				if (!vistos[i] && costoMenor >= distancias[i]) {
					next = listaNodos[i];
					costoMenor = distancias[i];
				}
			}
		}
		return next;
	}
	
	protected boolean isAdyacentes(Nodo origen, Nodo destino){
		return matrizCostos[origen.getPosicion()-1][destino.getPosicion()-1] != INF;
	}
		
		
	

	
}
