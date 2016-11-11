package grafos;

import java.io.*;
import java.util.*;


public class GrafoNDNP extends Grafo{
	
	/*private int cantidadNodos; 
	private int cantidadAristas; 
	private int porcentajeAdy; 
	private MatrizSimetrica vector; 
	private Nodo[] nodos; 
	private int gradoMax; 
	private int gradoMin; 
	 
*/
	private int cantColores;
	
	public GrafoNDNP(String path) throws FileNotFoundException
	{	
		/*
		 * Abro el Scanner y seteo los parametros iniciales
		 * */
		Scanner sc = new Scanner(new File(path));
		
		this.cantidadNodos = sc.nextInt();
		this.cantidadAristas = sc.nextInt();
		this.porcentajeAdy = sc.nextInt();
		this.gradoMax = sc.nextInt();
		this.gradoMin = sc.nextInt();
		this.vector = new MatrizSimetrica(cantidadNodos);
		this.nodos = new Nodo[cantidadNodos];
		this.inicializarLista();
		
		/*
		 * Para cada arista...
		 * */
		
		for (int i = 0; i< this.cantidadAristas;i++){
			int origen = sc.nextInt();
			int destino = sc.nextInt();
			
			/*
			 * Inicializo el nodo
			 * */
			if(nodos[origen] == null){
				nodos[origen] = new Nodo(origen);
			} else {
				this.nodos[origen].incrementarGrado();
			}
			
			/*
			 * Lo agrego al vector de adyacencia
			 * */
			
			if (nodos[destino] == null) {
				nodos[destino] = new Nodo(destino);
			} else {
				nodos[destino].incrementarGrado();
			}

			this.vector.setDato(origen, destino);
		}
		
		/*
		 * Cierro Scanner
		 * */
		sc.close();
	}
	
	public void colorear(){
		int color = 0, nodosColoreados = 0;
		resetearNodos();

		while (nodosColoreados < this.cantidadNodos) {
			color++;
			for (int i = 0; i < this.cantidadNodos; i++) {
				if (puedoColorear(i, color)) {
					this.nodos[i].setColor(color);
					nodosColoreados++;
				}
			}
		}

		this.cantColores = color;
	}
	
	public boolean puedoColorear(int posicion, int color) {
		if (this.nodos[posicion].getColor() != 0) {
			return false;
		}

		for (int i = 0; i < this.cantidadNodos; i++) {
			if (i != posicion && this.vector.sonAdyacentes(i,posicion)) {
				if (this.nodos[i].getColor() == color) {
					return false;
				}
			}
		}

		return true;
	}
	
	
	public void  secuencialAleatorio(){
		/*
		 * Coloreo los nodos sin ordenarlos
		 * */		
		mezclar(0, this.cantidadNodos - 1);
		colorear();	
	}
	
	public void  matula(){
		/*
		 * Ordeno de menor a mayor grado
		 * */
		Arrays.sort(this.nodos);
		this.mezclar();
		colorear();
	}
	
	public void  welshPowell(){
		/*
		 * Ordeno de mayor a menor grado
		 * */
		Arrays.sort(this.nodos, Collections.reverseOrder());
		this.mezclar();
		colorear();
	}
	
	/**
	 * Metodos auxiliares
	 * */
	
	public void exportarResultado(String path) {
		File archivo = null;
		PrintWriter salida = null;
		StringBuffer sb = null;
		try {
			archivo = new File(path);
			salida = new PrintWriter(archivo);
			sb = new StringBuffer();

			salida.println(cantidadNodos + " " + cantColores + " "
					+ cantidadAristas + " " + porcentajeAdy + " "
					+ gradoMax + " " + gradoMin);

			for (int i = 0; i < cantidadNodos; i++) {
				sb.append(nodos[i].getIndice() + " " + nodos[i].getColor() + "\n");
			}

			salida.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (salida != null)
				salida.close();
		}
	}
	
	public void resetearNodos() {
		for (int i = 0; i < nodos.length; i++) 
			nodos[i].setColor(0);	
	}
	
	public void mezclar(int inicio, int fin) {
		int cantidad = (fin - inicio + 1);
		int random;
		Nodo aux;

		for (int i = inicio; i < fin; i++) {
			random = (int) (Math.random() * cantidad + inicio);
			aux = nodos[i];
			nodos[i] = nodos[random];
			nodos[random] = aux;
		}
	}
	
	public void mezclar() {
		int inicio = 0;
		int fin = 0;
		int gradoActual = 0;

		while (fin < cantidadNodos) {
			gradoActual = nodos[inicio].getGrado();
			while (fin < cantidadNodos && nodos[fin].getGrado() == gradoActual) {
				fin++;
			}
			if (inicio != (fin - 1)) {
				this.mezclar(inicio, fin - 1);
			}
			inicio = fin;
		}
	}

	public void inicializarLista() {
		for (int i = 0; i < this.nodos.length; i++) 
			this.nodos[i] = new Nodo(i, 0, 0);
	}
	
	/**
	 * GETTERS Y SETTERS 
	 * */
	
	public int getCantNodos() {
		return cantidadNodos;
	}

	public void setCantNodos(int cantNodos) {
		this.cantidadNodos = cantNodos;
	}

	public int getCantAristas() {
		return cantidadAristas;
	}

	public void setCantAristas(int cantAristas) {
		this.cantidadAristas = cantAristas;
	}

	public int getPorcentajeAdy() {
		return porcentajeAdy;
	}

	public void setPorcentajeAdy(int porcentajeAdy) {
		this.porcentajeAdy = porcentajeAdy;
	}

	public MatrizSimetrica getVector() {
		return vector;
	}

	public void setVector(MatrizSimetrica vector) {
		this.vector = vector;
	}

	public Nodo[] getNodos() {
		return nodos;
	}

	public void setNodos(Nodo[] nodos) {
		this.nodos = nodos;
	}

	public int getGradoMaximo() {
		return gradoMax;
	}

	public void setGradoMaximo(int gradoMaximo) {
		this.gradoMax = gradoMaximo;
	}

	public int getGradoMinimo() {
		return gradoMin;
	}

	public void setGradoMinimo(int gradoMinimo) {
		this.gradoMin = gradoMinimo;
	}

	public int getCantColores() {
		return cantColores;
	}

	public void setCantColores(int cantColores) {
		this.cantColores = cantColores;
	}
	
}
