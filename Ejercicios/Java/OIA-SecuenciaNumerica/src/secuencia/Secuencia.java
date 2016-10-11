package secuencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Secuencia {

	private int S, L, P, I, N;
	private long LoF;

	public Secuencia(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		this.S = sc.nextInt();
		this.L = sc.nextInt();
		this.LoF=0;
		this.P=0;
		this.I=0;
		this.N=0;
		sc.close();
	}
	
	public void secuencia(){
		int i=-1, aux=this.S, anterior=0, antMayor=-1;
		boolean corte=false;

		while(this.LoF<this.L && corte == false){			
			// Aumento mi contador
			this.LoF++;
			anterior = aux;
						
			// Verifico si es par o impar		
			if(esPar(aux)== true ){
				this.P++;				
				aux/=2;
			} else {
				this.I++;
				aux=(aux*3)+1;
			}
			
			// Verifico si crece o decrece
			if(antMayor == 1 && anterior > aux || antMayor == -1){
				antMayor=0;
				i++;
			} else if (antMayor == 0 && anterior < aux || antMayor == -1){
				antMayor=1;
				i++;
			} else {
				i=1;
			}
			
			if(i>this.N){
				this.N = i;
			}
			
			// Verifico que llegue a 1
			if(anterior == 1){
				corte=true;
			}		
		}
		this.verDatos();
	}
	
	public void guardarResultado(String path) throws IOException{
		PrintWriter salida = new PrintWriter(new FileWriter(path));
		salida.println("S: "+this.S+ " L: "+this.L+" LoF: "+this.LoF+" P: "+this.P+" I: "+this.I+" N: "+this.N);
		salida.close();
	}
	
	public void verDatos(){
		System.out.println("S: "+this.S);
		System.out.println("L: "+this.L);
		System.out.println("LoF: "+this.LoF);
		System.out.println("P: "+this.P);
		System.out.println("I: "+this.I);
		System.out.println("N: "+this.N);
	}
	
	
	public static boolean esPar(int numero) {
        //Si el resto de numero entre 2 es 0, el numero es par
        if(numero % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
	
	
}
