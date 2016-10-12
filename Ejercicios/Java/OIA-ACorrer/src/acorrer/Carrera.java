package acorrer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Carrera {

	private int a, cm, cf, l;
	private Categoria[] catFemeninas;
	private Categoria[] catMasculinos;
	private Corredor[] corredores;	
	private int[] ganadores;
	
	/* Constructor */
	public Carrera(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		// Valores de la primer linea
		this.a = sc.nextInt();
		this.cf = sc.nextInt();
		this.cm = sc.nextInt();
		this.l = sc.nextInt();
			
		catFemeninas = new Categoria[this.cf];
		catMasculinos = new Categoria[this.cm];
		corredores = new Corredor[this.a];
		ganadores = new int [this.l];
		
		for(int j=0; j<this.cf; j++){
			catFemeninas[j] = new Categoria('F',sc.nextInt(),sc.nextInt());
		}
		
		for(int j=0; j<this.cm; j++){
			catMasculinos[j] = new Categoria('M',sc.nextInt(),sc.nextInt());
		}
		
		for(int i=0; i<this.a; i++){
			int e = sc.nextInt();
			char s = sc.next().charAt(0);
			
			if(s == 'F'){
				for(int j=0; j<this.cf;j++){
					if(catFemeninas[j].participa(e)){
						Corredor c = new Corredor (i,e,s,j);
						corredores[i] = c;
					}
				}					
			} else {
				for(int j=0; j < this.cm;j++){
					if(catMasculinos[j].participa(e)){
						Corredor c = new Corredor (i,e,s,j);
						corredores[i] = c;
					}
				}
			}
		}
		for(int i=0; i<this.l; i++){
			this.ganadores[i] = sc.nextInt();
		}
		sc.close();
	}
	
	public void asignarPodio(){
		for(int i=0; i<this.ganadores.length;i++){
			if(corredores[i].getSexo() == 'F'){
				catFemeninas[corredores[i].getCat()].asignarPodio(ganadores[i]);
			} else {
				catMasculinos[corredores[i].getCat()].asignarPodio(ganadores[i]);
			}	
		}
	}
	
	
	
	public void mostrarEntrada(){
		System.out.println(this.a+" "+this.cf+" "+this.cm+" "+this.l);
		for(int i = 0; i < this.cf ; i++){
			System.out.println(catFemeninas[i].getMinimo()+" "+catFemeninas[i].getMaximo());
		}
		for(int i = 0; i < this.cm ; i++){
			System.out.println(catMasculinos[i].getMinimo()+" "+catMasculinos[i].getMaximo());
		}	
		for(int i = 0; i < this.a ; i++){
			System.out.println(corredores[i].getEdad()+" "+corredores[i].getSexo());
		}
		for(int i = 0; i < this.l ; i++){
			System.out.println(ganadores[i]);
		}	
	}

	public void escribirResultado(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter(new File(path));
		for(int i=1;i<=this.cf;i++){
			salida.println(i+" "+catFemeninas[i].getPodio().getOro()+" "+catFemeninas[i].getPodio().getPlata()+" "+catFemeninas[i].getPodio().getBronce());
		}
		for(int i=1;i<=this.cm;i++){
			salida.println(i+" "+catMasculinos[i].getPodio().getOro()+" "+catFemeninas[i].getPodio().getPlata()+" "+catFemeninas[i].getPodio().getBronce());
		}
		salida.close();
	}
	
	
	
	
	
}
