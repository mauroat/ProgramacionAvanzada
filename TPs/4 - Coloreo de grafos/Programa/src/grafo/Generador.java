package grafo;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Generador extends GrafoNDNP {

	public Generador() {
	}

	private double factorial(int numero) {
		double factorial = 1;
		while (numero != 0) {
			factorial = factorial * numero;
			numero--;
		}
		return factorial;
	}
	/*
	 * 1- Generador de Grafos dado N y probabilidad de aristas
	 * */
	public void grafoDadoNYProbAristas(int n, double probabilidadAristas) {
		this.cantidadNodos = n;
		this.nodos = new Nodo[n];
		inicializarNodos();
		this.matrizAdyacencia = new MatrizSimetrica(this.cantidadNodos);
		this.cantidadAristas = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (!sonAdyacentes(i, j)) {
					if (Math.random() < probabilidadAristas) {
						nodos[i].incrementarGrado();
						nodos[j].incrementarGrado();
						matrizAdyacencia.setNodo(i, j);
						this.cantidadAristas++;
					}
				}
			}
		}

		Arrays.sort(nodos);
		this.gradoMaximo = nodos[this.cantidadNodos - 1].getGrado();
		this.gradoMinimo = nodos[0].getGrado();

		double aristasMaximas = factorial(n - 1);
		this.porcentajeAdyacencia = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
	}

	
	/*
	 * 2 - Generador de grafos dado N y porcentaje de adyacencia
	 * 
	 * */
	public void grafoDadoNYPorcentajeAdy(int n, int porcentajeAdy) {
		Random rand = new Random();
		this.cantidadNodos = n;
		this.nodos = new Nodo[n];
		//OK
		inicializarNodos();
		this.porcentajeAdyacencia = porcentajeAdy;
		this.matrizAdyacencia = new MatrizSimetrica(this.cantidadNodos);
		this.cantidadAristas = (int) (Math
				.rint((n * n - n) * 0.5
						* (porcentajeAdy / 100.0)));

		int aristasAplicadas = 0;
		while (aristasAplicadas != this.cantidadAristas) {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (!sonAdyacentes(i, j)) {
						if (rand.nextInt(2) == 1) {
							nodos[i].incrementarGrado();
							nodos[j].incrementarGrado();
							matrizAdyacencia.setNodo(i, j);
							aristasAplicadas++;
						}
					}
					if (aristasAplicadas == this.cantidadAristas) {
						break;
					}
				}
				if (aristasAplicadas == this.cantidadAristas) {
					break;
				}
			}
		}

		Arrays.sort(nodos);
		this.gradoMaximo = nodos[this.cantidadNodos - 1].getGrado();
		this.gradoMinimo = nodos[0].getGrado();
	}

	/*
	 * 3- Generador de grafos regulaes dado N y grado
	 * */
	
	public void grafoRegularDadoNYGrado(int n, int gradoRegular) {
		this.cantidadNodos = n;
		this.nodos = new Nodo[n];
		inicializarNodos();
		this.matrizAdyacencia = new MatrizSimetrica(n);
		this.gradoMaximo = gradoRegular;
		this.gradoMinimo = gradoRegular;
		this.cantidadAristas = 0;

		if (gradoRegular + 1 > n) {
			System.err.println("No se puede generar un grafo " + gradoRegular + "-regular de " + n + " nodos.");
			System.exit(-1);
		}
		if (n * gradoRegular % 2 != 0) {
			System.err.println("No se puede generar un grafo " + gradoRegular + "-regular de " + n + " nodos.");
			System.exit(-1);
		}
		if (gradoRegular != 0) {
			
			for (int i = 0; i < this.cantidadNodos; i++) {
				for (int j = i; j < this.cantidadNodos; j++) {
					if (i != j) {
						if (!sonAdyacentes(nodos[i].getIndice(), nodos[j].getIndice())) {
							if (nodos[i].getGrado() == gradoRegular || nodos[j].getGrado() == gradoRegular) {
								break;
							}
							
							
							this.matrizAdyacencia.setNodo(nodos[i].getIndice(), nodos[j].getIndice());
							this.cantidadAristas++;
							nodos[i].incrementarGrado();
							nodos[j].incrementarGrado();													
						}	
					}
				}
			}
		}		

		double aristasMaximas = factorial(n - 1);
		this.porcentajeAdyacencia = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
	}

	/*
	 * 4 - Generador de grafos regulares dado N y porcentaje de adyacencia
	 * */
	public void grafoRegularDadoNYPorcentajeAdy(int cantNodos, int porcentajeAdy) {
		int aux = (int) ((Math.rint((cantNodos * cantNodos - cantNodos) * 0.5* (porcentajeAdy / 100.0))) / cantNodos);
		grafoRegularDadoNYGrado(cantNodos,aux);
	}

	public void grafoNPartito(int n, int partes) {
		if (partes >= 1) {
			
			this.cantidadNodos = n;
			this.nodos = new Nodo[n];
			inicializarNodos();
			this.matrizAdyacencia = new MatrizSimetrica(n);
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
								this.matrizAdyacencia.setNodo(nodos[i].getIndice(), nodos[j].getIndice());
								this.cantidadAristas++;
								nodos[i].incrementarGrado();
								nodos[j].incrementarGrado();													
							}
						}
					}
				}
				if (i == fin) {
					ini = fin+1;
					fin += nodosPorParte;
					if (nodosRestantes && fin == (nodos.length - 2)) {
						fin++;
					}
				}
			}
			
			
			//double aristasMaximas = factorial(n - 1);
			//this.porcentajeAdyacencia = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
			Arrays.sort(nodos);
			this.gradoMaximo = nodos[this.cantidadNodos - 1].getGrado();
			this.gradoMinimo = nodos[0].getGrado();
			this.porcentajeAdyacencia = (int)(((float)this.gradoMaximo /(this.cantidadNodos-1))*100);
			
		}else{
			System.err.println("No se puede generar un grafo " + partes + "-partito.");
		}
	}
	
	public void exportarGrafo(String ruta) {
		File archivo = null;
		PrintWriter pw = null;
		StringBuffer sb = null;
		try {
			archivo = new File(ruta);
			pw = new PrintWriter(archivo);
			sb = new StringBuffer();
			pw.println(cantidadNodos + " " + cantidadAristas + " "+ porcentajeAdyacencia + " " + gradoMaximo + " "
					+ gradoMinimo);
			for (int i = 0; i < cantidadNodos; i++)
				for (int j = i + 1; j < cantidadNodos; j++) {
					if (matrizAdyacencia.sonAdyacentes(i, j)) {
						sb.append((i+1) + " " + (j+1) + "\n");
					}
				}
			pw.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}