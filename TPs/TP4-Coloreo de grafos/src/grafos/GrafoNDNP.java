package grafos;

import java.io.*;
import java.util.*;

public class GrafoNDNP {
	
	private int cantNodos; 
	private int cantAristas; 
	private double porcentajeAdy; 
	private MatrizSimetrica vector; 
	private Nodo[] nodos; 
	private int gradoMaximo; 
	private int gradoMinimo; 
	private int cantColores; 

	
	public GrafoNDNP(String path) throws FileNotFoundException
	{	
		/*
		 * Abro el Scanner y seteo los parametros iniciales
		 * */
		Scanner sc = new Scanner(new File(path));
		
		this.cantNodos = sc.nextInt();
		this.cantAristas = sc.nextInt();
		this.porcentajeAdy = sc.nextDouble();
		this.gradoMaximo = sc.nextInt();
		this.gradoMinimo = sc.nextInt();
		this.vector = new MatrizSimetrica(cantNodos);
		
		
		/*
		 * Para cada arista...
		 * */
		
		while(sc.hasNextLine())	{
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

		while (nodosColoreados < cantNodos) {
			color++;
			for (int i = 0; i < cantNodos; i++) {
				if (puedoColorear(i, color)) {
					nodos[i].setColor(color);
					nodosColoreados++;
				}
			}
		}

		cantColores = color;
	}
	
	public boolean puedoColorear(int posicion, int color) {
		if (nodos[posicion].getColor() != 0) {
			return false;
		}

		for (int i = 0; i < cantNodos; i++) {
			if (i != posicion && vector.sonAdyacentes(i,posicion)) {
				if (nodos[i].getColor() == color) {
					return false;
				}
			}
		}

		return true;
	}
	
	
	public void  secuencialAleatorio()
	{
		
	}
	
	public void  Matula()
	{
		
	}
	
	public void  WelshPowell()
	{
		
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

			salida.println(cantNodos + " " + cantColores + " "
					+ cantAristas + " " + porcentajeAdy + " "
					+ gradoMaximo + " " + gradoMinimo);

			for (int i = 0; i < cantNodos; i++) {
				sb.append(nodos[i].getIndice()+1 + " " + nodos[i].getColor() + "\n");
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
		for (int i = 0; i < nodos.length; i++) {
			nodos[i].setColor(0);;
		}
	}
}
