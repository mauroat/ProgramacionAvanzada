package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Grafo {

	protected int cantidadNodos;
	protected int cantidadAristas;
	protected int cantidadTotalAristas;
	protected int porcentajeAdy;
	protected int probabilidadArista;
	protected MatrizSimetrica vector;
	protected Nodo[] nodos;
	protected int gradoMin, gradoMax;
	
	public Grafo (){		
	}
	
	
	public Grafo (String path) throws FileNotFoundException{
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
	
	public void inicializarLista() {
		for (int i = 0; i < this.nodos.length; i++) 
			this.nodos[i] = new Nodo(i, 0, 0);
	}
	
	private int calcularCantidadTotalDeAristas(int cantidadNodos, double porcentajeAdy){
		// Esto esta OK
		return (int) ((porcentajeAdy/100) * ((cantidadNodos * (cantidadNodos-1))/2));
	}
	
	/**
	 * Método 1 - Generador con N y probabilidad por arista
	 * */
	
	public void generadorConNyProbabilidadDeArista(int n, int probabilidadArista){
		/*
		 * Modificacion base de atributos
		 * */
		this.cantidadNodos = n;
		this.porcentajeAdy = 0;
		this.cantidadAristas = 0;
		this.cantidadTotalAristas = (cantidadNodos*(cantidadNodos-1))/2;
		this.probabilidadArista = probabilidadArista;
		this.gradoMax = 0;
		this.gradoMin = 0;	
		this.vector = new MatrizSimetrica (cantidadNodos);
		this.nodos = new Nodo[cantidadNodos];
		inicializarLista();
		
		
		
		Random r = new Random();	// Devuelve 0 ó 1
		for(int i = 0; i < cantidadNodos; i++)
			for(int j = cantidadNodos-1; i < j; j--){
				int aux = r.nextInt(2);
				if(aux == 1){
					vector.setDato(i, j);
					nodos[i].incrementarGrado();
					nodos[j].incrementarGrado();
					this.cantidadAristas++;
				}
			}
						
		Arrays.sort(nodos);
		this.gradoMax = nodos[this.cantidadNodos - 1].getGrado();
		this.gradoMin = nodos[0].getGrado();
	}
	
	
	/**
	 * Método 2 - Generador con N y porcentaje de Adyacencia
	 * */
	
	
	
	public void generadorConNyPorcentajeAdyacencia(int n, int porcentajeAdyacencia){
		
		this.cantidadNodos = n;
		this.nodos = new Nodo[cantidadNodos];
		inicializarLista();
		this.porcentajeAdy = porcentajeAdyacencia;
		this.vector = new MatrizSimetrica(this.cantidadNodos);
		this.cantidadAristas = (int) (Math
				.rint((cantidadNodos * cantidadNodos - cantidadNodos) /2
						* (porcentajeAdy / 100.0)));

		Random rand = new Random();
		int aristas = 0;
		while (aristas != this.cantidadAristas) {
			for (int i = 0; i < cantidadNodos; i++) {
				for (int j = i + 1; j < cantidadNodos; j++) {
					if (!sonAdyacentes(i, j)) {
						if (rand.nextInt(2) == 1) {
							nodos[i].incrementarGrado();
							nodos[j].incrementarGrado();
							vector.setDato(i, j);
							aristas++;
						}
					}
					if (aristas == this.cantidadAristas) {
						break;
					}
				}
				if (aristas == this.cantidadAristas) {
					break;
				}
			}
		}

		Arrays.sort(nodos);
		
		
		this.gradoMax = nodos[this.cantidadNodos - 1].getGrado();
		this.gradoMin = nodos[0].getGrado();
	}
	
	/**
	 * Método 3 - Generador de grafos regulares dado N y el grado
	 * */
	
	public void generadorRegularConNyGrado(int n, int grado){
		/*
		 * Modificacion base de atributos
		 * */
		this.cantidadNodos = n;
		this.porcentajeAdy = 0;
		this.cantidadAristas = 0;		
		this.gradoMax = grado;
		this.gradoMin = grado;
		this.vector = new MatrizSimetrica (cantidadNodos);
		this.nodos = new Nodo[cantidadNodos];
		inicializarLista();

		
		/*
		 * Generador
		 * */
		
		int gr = this.gradoMin;
		
		/* 
		 * Chequeo que el grado sea menor que la cantidad de combinaciones posibles 
		 * */
		
		if(gr + 1 > this.cantidadNodos || (this.cantidadNodos * gr) % 2 != 0)
			return;
		
		if(this.gradoMin != 0) {
			
			for(int i = 0; i < this.cantidadNodos; i++)	{
				
				for(int j = this.cantidadNodos-1; i < j; j--)	{
					
					if (this.nodos[i].getGrado() == gr || this.nodos[j].getGrado() == gr)
						break;
						
					this.vector.setDato(i, j);
					
					this.nodos[i].incrementarGrado();
					this.nodos[j].incrementarGrado();	
					this.cantidadAristas++;
				}
			}
		}
		//int totalAristas = factorial(this.cantidadNodos - 1);
		//this.porcentajeAdy = this.cantidadAristas / totalAristas * 100;
		float aux = (float) gr / (this.cantidadNodos-1);
		this.porcentajeAdy = (int) (aux*100);
		
	}
	
	
	/**
	 * 	Método 4 -  Generador de grafos regulares dado N y porcentaje de adyacencia
	 * */
	
	public void generadorRegularConNyPorcentajeAdyacencia( int n, int porcentajeDeAdyacencia){
		this.cantidadNodos = n;
		this.porcentajeAdy = porcentajeDeAdyacencia;
		
		// Redondeo para arriba
		int aux = (int) ((Math.rint((cantidadNodos * cantidadNodos - cantidadNodos) * 0.5* (porcentajeAdy / 100))) / cantidadNodos);
		generadorRegularConNyGrado(cantidadNodos, aux);
	}
	
	
	/**
	 * Método 5 - Generador N-Partito
	 * */
	
	public void generadorNPartito(int n, int partes){
		if (partes >= 1) {
			
			this.cantidadNodos = n;
			this.nodos = new Nodo[cantidadNodos];
			inicializarLista();
			this.vector = new MatrizSimetrica(cantidadNodos);
			this.cantidadAristas = 0;
			
			boolean nodosRestantes = false;			
			int nodosPorParte = n/partes;
			nodosRestantes = n%partes != 0;
			
			int ini = 0;
			int fin = nodosPorParte-1;
		
			for (int i = ini; i < nodos.length; i++) {
				for (int j = 0; j < nodos.length; j++) {
					if (i != j) {
						if (!(j <= fin && j >= ini)) {
							if (!sonAdyacentes(nodos[i].getIndice(), nodos[j].getIndice())) {
								this.vector.setDato(nodos[i].getIndice(), nodos[j].getIndice());
								this.cantidadAristas++;
								nodos[i].incrementarGrado();
								nodos[j].incrementarGrado();													
							}
						}
					}
				}
				if (i == fin) {
					ini = fin + 1;
					fin += nodosPorParte;
					if (nodosRestantes && fin == (nodos.length - 2)) {
						fin++;
					}
				}
			}

			
			double aristasMaximas = factorial(n - 1);
			this.porcentajeAdy = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
			Arrays.sort(nodos);
			this.gradoMax = nodos[this.cantidadNodos - 1].getGrado();
			this.gradoMin = nodos[0].getGrado();
				
		}else{
			System.err.println("No se puede generar un grafo " + partes + "-partito.");
		}
	}

	/*
	 * Métodos auxiliares 
	 * */
	
		
	public boolean sonAdyacentes(int nodoOrigen, int nodoDestino) {
		return this.vector.sonAdyacentes(nodoOrigen, nodoDestino);
	}
	
	private double factorial(int numero) {
		double factorial = 1;
		while (numero != 0) {
			factorial = factorial * numero;
			numero--;
		}
		return factorial;
	}
	
	public void exportar(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter ( new File (path));
	//	String texto = (cantidadNodos + " " + cantidadAristas + " "+ porcentajeAdy + " " + gradoMax + " "
	//			+ gradoMin +"\n");
		
		
		salida.println(cantidadNodos + " " + cantidadAristas + " "+ porcentajeAdy + " " + gradoMax + " "
						+ gradoMin);
		for (int i = 0; i < cantidadNodos; i++){
			for (int j = i + 1; j < cantidadNodos; j++) {
				if (vector.sonAdyacentes(i, j)) {
					int ini = i+1, fin = j+1;
					//texto.concat(i+1 + " " + k + "\n");
					salida.print(i + " " + j + "\n");
				}
			}
		}
		//texto.charAt(texto.length()-1);
		//salida.print(texto);
		salida.close();
	}


	public int getCantidadNodos() {
		return cantidadNodos;
	}


	public void setCantidadNodos(int cantidadNodos) {
		this.cantidadNodos = cantidadNodos;
	}


	public int getCantidadAristas() {
		return cantidadAristas;
	}


	public void setCantidadAristas(int cantidadAristas) {
		this.cantidadAristas = cantidadAristas;
	}


	public int getCantidadTotalAristas() {
		return cantidadTotalAristas;
	}


	public void setCantidadTotalAristas(int cantidadTotalAristas) {
		this.cantidadTotalAristas = cantidadTotalAristas;
	}


	public int getPorcentajeAdy() {
		return porcentajeAdy;
	}


	public void setPorcentajeAdy(int porcentajeAdy) {
		this.porcentajeAdy = porcentajeAdy;
	}


	public int getProbabilidadArista() {
		return probabilidadArista;
	}


	public void setProbabilidadArista(int probabilidadArista) {
		this.probabilidadArista = probabilidadArista;
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


	public int getGradoMin() {
		return gradoMin;
	}


	public void setGradoMin(int gradoMin) {
		this.gradoMin = gradoMin;
	}


	public int getGradoMax() {
		return gradoMax;
	}


	public void setGradoMax(int gradoMax) {
		this.gradoMax = gradoMax;
	}
	
	
	
}
