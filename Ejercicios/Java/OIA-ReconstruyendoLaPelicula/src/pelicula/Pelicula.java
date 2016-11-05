package pelicula;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pelicula {

	private int cantidadSegmentos;
	private int ultimoSegmento;
	private Segmento[] segmentos;
	
	
	public Pelicula(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		cantidadSegmentos = sc.nextInt();
		ultimoSegmento = sc.nextInt();
		
		segmentos = new Segmento[cantidadSegmentos];
		
		for (int i = 0; i < cantidadSegmentos;i++){
			segmentos[i] = new Segmento(sc.nextInt(), sc.nextInt(), sc.nextInt());			
		}
		
		sc.close();
	}
	
	public void mostrarEntrada(){
		System.out.println(this.cantidadSegmentos+" "+ultimoSegmento);
		for(int i = 0; i<cantidadSegmentos;i++){
			System.out.println(segmentos[i].getNumero()+" "+segmentos[i].getEscenaInicial()+" "+segmentos[i].getEscenaFinal());
		}
		
		
	}
}
