package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

import grafo.GrafoNDNP;
import grafo.Nodo;

public class Probador {
	
	final static String DIR_IN = "../Lote de pruebas/Entrada/";
	final static String DIR_OUT = "../Lote de pruebas/Salida/";
	
	private GrafoNDNP grafo;
	private Integer cantidadNodosSalida;
	private Integer cantidadColoresSalida;
	private Integer cantidadAristasSalida;
	private Double porcentajeAdyacenciaSalida;
	private Integer gradoMaximoSalida;
	private Integer gradoMinimoSalida;
	private Nodo[] nodosSalida;

	public Probador(String entrada, String coloreado) throws FileNotFoundException {

		String linea = null;
		String[] lineaSplitted = null;

		grafo = new GrafoNDNP(entrada);
		Scanner sc = new Scanner (new File(coloreado));
		cantidadNodosSalida = sc.nextInt();
		cantidadColoresSalida = sc.nextInt();
		
		cantidadAristasSalida = sc.nextInt();
		porcentajeAdyacenciaSalida = sc.nextDouble();
		gradoMaximoSalida = sc.nextInt();
		gradoMinimoSalida = sc.nextInt();
		nodosSalida = new Nodo[cantidadNodosSalida];
		for (int i = 0; i < cantidadNodosSalida; i++) {			
			nodosSalida[i] = new Nodo(sc.nextInt(),sc.nextInt(),-1);
			
			//nodosSalida[i] = new Nodo(Integer.parseInt(lineaSplitted[0]), Integer.parseInt(lineaSplitted[1]), -1);
		}
		
		sc.close();

	}

	public boolean esCorrecto() {
		// Verificar que los datos de entrada y de salida son los mismos

		if (!cantidadNodosSalida.equals(grafo.getCantidadNodos())) {
			System.out.println("ERROR: DISTINTA CANTIDAD DE NODOS");
			return false;
		}
		
		if (!cantidadAristasSalida.equals(grafo.getCantidadAristas())) {
			System.out.println("ERROR: DISTINTA CANTIDAD DE ARISTAS");
			return false;
		}

		if (!porcentajeAdyacenciaSalida.equals(grafo.getPorcentajeAdyacencia())) {
			System.out.println("ERROR: DISTINTO PORCENTAJE DE ADYACENCIA");
			return false;
		}

		/*
		 * Grados
		 * */
		
		if (!gradoMinimoSalida.equals(grafo.getGradoMinimo())) {
			System.out.println("ERROR: DISTINTO GRADO MINIMO");
			return false;
		}
		
		if (!gradoMaximoSalida.equals(grafo.getGradoMaximo())) {
			System.out.println("ERROR: DISTINTO GRADO MAXIMO");
			return false;

		}
		
		

		// Verificar que la cantidad de colores de la salida, sea la misma que
		// se uso y verificar que no existen nodos sin colorear
	
		
		int[] vectorColores = new int[cantidadColoresSalida + 1];
		vectorColores[0] = 1;

		for (int i = 0; i < cantidadNodosSalida; i++) {
			vectorColores[nodosSalida[i].getColor()] = 1;
			if (nodosSalida[i].getColor() == 0) {
				System.out.println("Existen nodos sin colorear:" + nodosSalida[i].getIndice());
				return false;
			}
		}

		for (int i = 0; i < cantidadColoresSalida; i++){
			if (vectorColores[i] != 1) {
				System.out.println("No se han usado la cantidad de colores que se indican en la salida");
				return false;
			}
		}

	/*
		Arrays.sort(nodosSalida);
		
		for(int i = 1; i< nodosSalida.length;i++){
			if(nodosSalida[i-1].getColor() == nodosSalida[i].getColor()){
				System.out.println("ERROR: HAY NODOS ADYACENTES DEL MISMO COLOR");
				return false;
			}
		}
		
*/
		// Nodos adyacentes del mismo color
		
		
		for (int i = 0; i < (cantidadNodosSalida - 1); i++) {
			int aux = i;
			while (aux < (cantidadNodosSalida - 1) && nodosSalida[aux + 1].getColor() == nodosSalida[i].getColor()) {
				aux++;
				if (grafo.sonAdyacentes(nodosSalida[i].getIndice() - 1, nodosSalida[aux].getIndice() - 1)) {
					System.out.println("Hay nodos adyacentes del mismo color");
					return false;
				}
			}
		}
		return true;
	}
	
	
}