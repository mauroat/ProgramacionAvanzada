package probador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import grafos.*;

public class Probador {

	private GrafoNDNP grafo;
	private Integer cantidadNodosColor;
	private Integer cantidadColoresColor;
	private Integer cantidadAristasColor;
	private Integer porcentajeAdyacenciaColor;
	private Integer gradoMaximoColor;
	private Integer gradoMinimoColor;
	private Nodo[] nodosColor;

	
	public boolean probador(String entrada, String coloreado) throws FileNotFoundException{
		
		GrafoNDNP g1 = new GrafoNDNP(entrada);

		Scanner sc = new Scanner (new File (coloreado));	
	//	while(sc.has)
		
		boolean marca = true;
		
		/** 
		 * CANTIDAD DE NODOS
		 * */
		if (g1.getCantidadNodos() != this.cantidadNodosColor){
			System.out.println("ERROR: Distinta cantidad de nodos");
			marca = false;
		}
		/**
		 * CANTIDAD DE ARISTAS
		 * */
		if (g1.getCantidadAristas() != this.cantidadAristasColor){
			System.out.println("ERROR: Distinta cantidad de aristas");
			marca = false;
		}
		/**
		 * PORCENTAJE DE ADYACENCIA
		 * */
		if (g1.getPorcentajeAdy() != this.porcentajeAdyacenciaColor){
			System.out.println("ERROR: Distinto porcentaje de Adyacencia");
			marca = false;
		}
		/**
		 * GRADO MAXIMO
		 * */
		if (g1.getGradoMax() != this.gradoMaximoColor){
			System.out.println("ERROR: Distinto grado maximo");
			marca = false;
		}
		
		/**
		 * GRADO MINIMO
		 * */
		if (g1.getGradoMin() != this.gradoMinimoColor){
			System.out.println("ERROR: Distinto grado minimo");
			marca = false;
		}
		
		return marca;
		
	}
	
	
}
