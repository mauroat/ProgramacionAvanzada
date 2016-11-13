package rescatando;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Grafo {

	private int cantidadNodos;
	private int cantidadAristas;
	private int cantidadDragones;
	private int nodoPrincipe, nodoPrincesa;
	private int[] nodosDragones;
	private List<Arista> aristas;
	private int[][] matriz;
	private int[] camino;	
	private Nodo[] listaNodos;
	
	public Grafo(String path) throws FileNotFoundException{
		Scanner sc = new Scanner (new File(path));
		
		this.cantidadNodos = sc.nextInt();
	//	this.matriz = new int[this.cantidadNodos][this.cantidadNodos];
	//	inicializarMatriz();

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
				matriz[i][j] = 999999;
	}
	
	 
	
	private void dijsktra(Nodo origen){
		int[] distancias = new int[this.cantidadNodos];
		Arrays.fill(distancias, 999999);
		
		boolean[] vistos = new boolean[this.cantidadNodos];
		Arrays.fill(vistos, false);
		
		int[] camino = new int[this.cantidadNodos];
		Arrays.fill(camino, -2);
		
		int[][] matrizCostos = new int[cantidadNodos][cantidadNodos];
		inicializarMatriz();
		
		
		for(int i = 0; i < this.cantidadNodos ; i++){
			if(origen.getPosicion() != i+1){
				if (isAdyacentes(origen,listaNodos[i+1])) {
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
		
	protected boolean isAdyacentes(Nodo origen, Nodo destino){
		return matrizCostos[origen.getPosicion()][destino.getPosicion()] != 999999;
	}
		
		
	

	
}
