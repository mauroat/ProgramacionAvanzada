package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GrafoNDNP {
	
	private int cantNodos; 
	private int cantAristas; 
	private double porcAdyacencia; 
	private MatrizSimetrica matriz; 
	private Nodo[] listaNodos; 
	private int gradoMaximo; 
	private int gradoMinimo; 
	private int cantColores; 

	public GrafoNDNP() { 
	} 
	
	public GrafoNDNP(String path) throws FileNotFoundException
	{	
		Scanner sc = new Scanner(new File(path));
		
		this.cantNodos = sc.nextInt();
		this.cantAristas = sc.nextInt();
		this.porcAdyacencia = sc.nextDouble();
		this.gradoMaximo = sc.nextInt();
		this.gradoMinimo = sc.nextInt();

		while(sc.hasNextLine())
		{
			int nodoOrigen = sc.nextInt();
			int nodoDestino = sc.nextInt();
			this.matriz.getDato(nodoOrigen, nodoDestino);
			this.listaNodos[nodoOrigen].incrementarGrado();
			this.listaNodos[nodoDestino].incrementarGrado();
		}
		
		sc.close();
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
	
}
