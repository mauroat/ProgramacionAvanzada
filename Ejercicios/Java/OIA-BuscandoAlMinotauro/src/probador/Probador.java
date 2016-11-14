package probador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import grafo.Grafo;

public class Probador {

	private int cantidadAristas;
	private boolean[] presencia;
	
	/*
	public Probador(String entrada, String resultado) throws FileNotFoundException{
		Grafo g = new Grafo (entrada);
		
		
		Scanner sc = new Scanner (new File (resultado));
		this.cantidadAristas = sc.nextInt();
		this.presencia = new boolean[cantidadAristas];
		while(sc.hasNextLine()){
			int i = sc.nextInt();
			int j = sc.nextInt();
			
			if((!presencia[i-1] || !presencia[j-1])){
				presencia[i-1] = true;
				presencia[j-1] = true;
			} else{
				
			}
				
		}
		
		
		sc.close();
		Arrays.fill(presencia, false);
		
	}
	*/
	
}
