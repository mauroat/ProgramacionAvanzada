package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import model.GrafoNDNP;
import model.Nodo;

public class Probador {
	final static String DIR_IN = "../Lote de pruebas/Entrada/";
	final static String DIR_OUT = "../Lote de pruebas/Salida/";
	
	private GrafoNDNP grafo;
	private Integer cantidadNodosSalida;
	private Integer cantidadColoresSalida;
	private Integer cantidadAristasSalida;
	private Integer porcentajeAdyacenciaSalida;
	private Integer gradoMaximoSalida;
	private Integer gradoMinimoSalida;
	private Nodo[] nodosSalida;

	public Probador(String grafoEntrada, String coloreado) {

		File salida = new File(coloreado);
		FileReader fr = null;
		BufferedReader br = null;

		String linea = null;
		String[] lineaSplitted = null;

		try {
			grafo = new GrafoNDNP(grafoEntrada);
			fr = new FileReader(salida);
			br = new BufferedReader(fr);
			linea = br.readLine();
			lineaSplitted = linea.split(" ");

			cantidadNodosSalida = Integer.parseInt(lineaSplitted[0]);
			cantidadColoresSalida = Integer.parseInt(lineaSplitted[1]);
			cantidadAristasSalida = Integer.parseInt(lineaSplitted[2]);
			porcentajeAdyacenciaSalida = Integer.parseInt(lineaSplitted[3]);
			gradoMaximoSalida = Integer.parseInt(lineaSplitted[4]);
			gradoMinimoSalida = Integer.parseInt(lineaSplitted[5]);
			nodosSalida = new Nodo[cantidadNodosSalida];

			for (int i = 0; i < cantidadNodosSalida; i++) {
				linea = br.readLine();
				lineaSplitted = linea.split(" ");
				// Al leer los nodos de salida, les pongo grado -1, ya que no lo conozco
				nodosSalida[i] = new Nodo(Integer.parseInt(lineaSplitted[0]), Integer.parseInt(lineaSplitted[1]), -1);
			}

			try {
				fr.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public boolean isCorrecto() {
		// Verificar que los datos de entrada y de salida son los mismos

		if (!cantidadNodosSalida.equals(grafo.getCantidadNodos())) {
			System.err.println("No concuerda la cantidad de nodos de la entrada con la Salida");
			return false;
		}
		
		if (!cantidadAristasSalida.equals(grafo.getCantidadAristas())) {
			System.err.println("No concuerda la cantidad de aristas de la entrada con la Salida");
			return false;
		}

		if (!porcentajeAdyacenciaSalida.equals(grafo.getPorcentajeAdyacencia())) {
			System.err.println("No concuerda el porcentaje de adyacencia de la entrada con la Salida");
			return false;
		}

		if (!gradoMaximoSalida.equals(grafo.getGradoMaximo())) {
			System.err.println("No concuerda el grado maximo de la entrada con la Salida");
			return false;

		}
		
		if (!gradoMinimoSalida.equals(grafo.getGradoMinimo())) {
			System.err.println("No concuerda el grado minimo de la entrada con la Salida");
			return false;
		}

		// Verificar que la cantidad de colores de la salida, sea la misma que
		// se uso y verificar que no existen nodos sin colorear
		int[] vectorColores = new int[cantidadColoresSalida + 1];
		vectorColores[0] = 1;

		for (int i = 0; i < cantidadNodosSalida; i++) {
			vectorColores[nodosSalida[i].getColor()] = 1;
			if (nodosSalida[i].getColor() == 0) {
				System.err.println("Existen nodos sin colorear:" + nodosSalida[i].getIndice());
				return false;
			}
		}

		for (int i = 0; i < cantidadColoresSalida; i++){
			if (vectorColores[i] != 1) {
				System.err.println("No se han usado la cantidad de colores que se indican en la salida");
				return false;
			}
		}
		// Verificar que los nodos del mismo color, no tienen aristas que los unan
		for (int i = 0; i < (cantidadNodosSalida - 1); i++) {
			int aux = i;
			while (aux < (cantidadNodosSalida - 1) && nodosSalida[aux + 1].getColor() == nodosSalida[i].getColor()) {
				aux++;
				if (grafo.isAdyacentes(nodosSalida[i].getIndice() - 1, nodosSalida[aux].getIndice() - 1)) {
					System.out.println("Existen nodos del mismo color, conectados por una arista");
					return false;
				}
			}
		}
		return true;
	}
	
	
}