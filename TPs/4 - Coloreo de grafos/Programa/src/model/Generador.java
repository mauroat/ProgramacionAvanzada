package model;

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

	public void grafoDadoNYProbAristas(int cantNodos, double probabilidadAristas) {
		this.cantidadNodos = cantNodos;
		this.nodos = new Nodo[cantNodos];
		inicializarNodos();
		this.matrizAdyacencia = new MatrizSimetrica(this.cantidadNodos);
		this.cantidadAristas = 0;

		for (int i = 0; i < cantNodos; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				if (!sonAdyacentes(i, j)) {
					if (Math.random() < probabilidadAristas) {
						nodos[i].addGrado();
						nodos[j].addGrado();
						matrizAdyacencia.setNodo(i, j);
						this.cantidadAristas++;
					}
				}
			}
		}

		Arrays.sort(nodos);
		this.gradoMaximo = nodos[this.cantidadNodos - 1].getGrado();
		this.gradoMinimo = nodos[0].getGrado();

		double aristasMaximas = factorial(cantNodos - 1);
		this.porcentajeAdyacencia = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
	}

	public void grafoDadoNYPorcentajeAdy(int cantNodos, int porcentajeAdy) {
		Random rand = new Random();
		this.cantidadNodos = cantNodos;
		this.nodos = new Nodo[cantNodos];
		inicializarNodos();
		this.porcentajeAdyacencia = porcentajeAdy;
		this.matrizAdyacencia = new MatrizSimetrica(this.cantidadNodos);
		this.cantidadAristas = (int) (Math
				.rint((cantNodos * cantNodos - cantNodos) * 0.5
						* (porcentajeAdy / 100.0)));

		int aristasAplicadas = 0;
		while (aristasAplicadas != this.cantidadAristas) {
			for (int i = 0; i < cantNodos; i++) {
				for (int j = i + 1; j < cantNodos; j++) {
					if (!sonAdyacentes(i, j)) {
						if (rand.nextInt(2) == 1) {
							nodos[i].addGrado();
							nodos[j].addGrado();
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

	public void grafoRegularDadoNYGrado(int cantNodos, int gradoRegular) {
		this.cantidadNodos = cantNodos;
		this.nodos = new Nodo[cantNodos];
		inicializarNodos();
		this.matrizAdyacencia = new MatrizSimetrica(cantNodos);
		this.gradoMaximo = gradoRegular;
		this.gradoMinimo = gradoRegular;
		this.cantidadAristas = 0;

		// Condiciones para que G(N, k) sea k-regular:
        // 1. k + 1 <= N.
        // 2. N * k par.
		if (gradoRegular + 1 > cantNodos) {
			System.err.println("No se puede generar un grafo " + gradoRegular + "-regular de " + cantNodos + " nodos.");
			System.exit(-1);
		}
		if (cantNodos * gradoRegular % 2 != 0) {
			System.err.println("No se puede generar un grafo " + gradoRegular + "-regular de " + cantNodos + " nodos.");
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
							nodos[i].addGrado();
							nodos[j].addGrado();													
						}	
					}
				}
			}
		}		

		double aristasMaximas = factorial(cantNodos - 1);
		this.porcentajeAdyacencia = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
	}

	public void grafoRegularDadoNYPorcentajeAdy(int cantNodos, int porcentajeAdy) {
		int aux = (int) ((Math.rint((cantNodos * cantNodos - cantNodos) * 0.5* (porcentajeAdy / 100.0))) / cantNodos);
		grafoRegularDadoNYGrado(cantNodos,aux);
	}

	public void grafoNPartito(int cantNodos, int partes) {
		if (partes >= 1) {
			
			this.cantidadNodos = cantNodos;
			this.nodos = new Nodo[cantNodos];
			inicializarNodos();
			this.matrizAdyacencia = new MatrizSimetrica(cantNodos);
			this.cantidadAristas = 0;
			boolean nodosRestantes = false;
			
			int nodosPorParte = cantNodos/partes;
			nodosRestantes = cantNodos%partes != 0;
			
			int ini = 0;
			int fin = nodosPorParte-1;
			
			for (int i = ini; i < nodos.length; i++) {
				for (int j = 0; j < nodos.length; j++) {
					if (i != j) {
						if (!(j <= fin && j >= ini)) {
							if (!sonAdyacentes(nodos[i].getIndice(), nodos[j].getIndice())) {
								this.matrizAdyacencia.setNodo(nodos[i].getIndice(), nodos[j].getIndice());
								this.cantidadAristas++;
								nodos[i].addGrado();
								nodos[j].addGrado();													
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
			
			
			double aristasMaximas = factorial(cantNodos - 1);
			this.porcentajeAdyacencia = (int) (Math.rint((this.cantidadAristas/ aristasMaximas) * 100.0));
			Arrays.sort(nodos);
			this.gradoMaximo = nodos[this.cantidadNodos - 1].getGrado();
			this.gradoMinimo = nodos[0].getGrado();
			
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