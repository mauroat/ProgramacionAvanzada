package secmax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Secmax {

	private int cantidad;
	private int contador, secuenciaMax;
	private int[] valores;
	
	public Secmax(String path) throws FileNotFoundException{
		Scanner sc = new Scanner (new File(path));
		this.cantidad = sc.nextInt();
		this.valores = new int[this.cantidad];
		
		for(int i = 0; i<this.cantidad;i++){
			valores[i] = sc.nextInt();
		}
		sc.close();
	}
	
	public void mostrarValores(){
		for(int i = 0; i<this.cantidad;i++){
			System.out.println(valores[i]);
		}
	}
	
	public void resolver(){
		int secuencia=0;
		this.contador=0;
		this.secuenciaMax=0;
		
		for(int i = 0; i<this.cantidad;i++){
			if(multiplo(valores[i],2) || multiplo(valores[i],3) || multiplo(valores[i],5)){
				secuencia=0;
				// Valor inválido
			} else {
				this.contador++;
				secuencia++;
			}
			if(secuencia>this.secuenciaMax)
				this.secuenciaMax = secuencia;
			
		}
	}
	
	public boolean multiplo(int valor, int divisor){
		return valor%divisor == 0;
	}
	
	public void mostrarResultados(int c, int s){
		System.out.println("Cantidad de numeros válidos: "+c);
		System.out.println("Secuencia máxima: "+s);
	}
	
	public void guardarResultado(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter(new File(path));
		salida.println(this.contador);
		salida.println(this.secuenciaMax);
		salida.close();
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getContador() {
		return contador;
	}

	public int getSecuenciaMax() {
		return secuenciaMax;
	}
	
	
}
