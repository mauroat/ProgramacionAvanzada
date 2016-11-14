package grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Grafo {

	protected int cantidadNodos;
	protected List <Arista> padre;
	//protected int cantidadAristas;
	//protected int porcentajeAdyacencia;
	//protected int[][] matrizDistancias;
	protected List <Arista> aristas;
	
	
	public Grafo(int cantidadNodos){
		this.cantidadNodos = cantidadNodos;
		//this.matrizDistancias = new int[cantidadNodos][cantidadNodos];
		this.aristas = new ArrayList<Arista>();
	}
	
	public Grafo(String path) throws FileNotFoundException{
		Scanner sc = new Scanner (new File(path));
		int contador = 0;
		this.cantidadNodos = sc.nextInt();
		this.aristas = new ArrayList<Arista>();
		
		for(int i = 0 ; i < this.cantidadNodos ; i++){
			for(int j = 0 ; j < this.cantidadNodos ; j++){
				contador++;
				int valor = sc.nextInt();
				if(valor != 0){
					this.aristas.add(new Arista(contador,i+1,j+1,valor));
				}									
			}
		}	
		sc.close();
	}
	
	public void kruskal(){
		boolean incluido[] = new boolean[cantidadNodos];
		this.padre = new ArrayList<Arista>();
		Arrays.fill(incluido, false);
		
		Collections.sort(aristas);
		
		for (Arista a : aristas){
			if((!incluido[a.getDesde()-1] || !incluido[a.getHasta()-1] ) && padre.size() < aristas.size()-1){
				incluido[a.getDesde()-1] = true;
				incluido[a.getHasta()-1] = true;
				padre.add(a);
			}
		}
		
	}

	public int getCantidadNodos() {
		return cantidadNodos;
	}

	public void setCantidadNodos(int cantidadNodos) {
		this.cantidadNodos = cantidadNodos;
	}

	public List<Arista> getPadre() {
		return padre;
	}

	public void setPadre(List<Arista> padre) {
		this.padre = padre;
	}

	public List<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista> aristas) {
		this.aristas = aristas;
	}
	
	public void exportarResultado(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter (new File (path));
		salida.println(padre.size());
		
		for (Arista a : padre)
			salida.println(a.getDesde()+" "+a.getHasta()+" "+a.getDistancia());
	
		salida.close();
	}
	
	
	
}
