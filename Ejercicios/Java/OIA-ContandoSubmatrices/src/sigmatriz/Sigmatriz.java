package sigmatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Sigmatriz {

	private int valorA, valorB, filas, columnas, m, n, p;
	private int[] respuestas;
	private int[] tiempos;
	private Matriz mat;
	

	private int resultado;
	private int ganadores;
	
	public Sigmatriz(String path) throws FileNotFoundException{
		Scanner sc = new Scanner (new File(path));
		this.valorA= sc.nextInt();
		this.valorB = sc.nextInt();
		this.filas = sc.nextInt();
		this.columnas = sc.nextInt();
		this.p = sc.nextInt();
		
		this.mat = new Matriz(this.filas,this.columnas);
		
		for(int i = 0; i<this.filas;i++)
			for(int j=0; j<this.columnas;j++)
				this.mat.setValorMatriz(i, j, sc.nextInt());
			
		this.respuestas = new int[this.p];
		this.tiempos = new int[this.p];
		
		for(int i = 0; i<this.p;i++)
			this.respuestas[i] = sc.nextInt();
		
		for(int i = 0; i<this.p;i++)
			this.tiempos[i] = sc.nextInt();
		sc.close();
	}
	
	
	public void mostrarDataIn(){
		System.out.println(this.valorA+" "+this.valorB);
		System.out.println(this.filas+" "+this.columnas+" "+this.p);
		for(int i = 0; i<this.filas;i++){
			for(int j=0; j<this.columnas;j++){
				System.out.print(this.mat.getValorMatriz(i, j)+" ");
				if(j==this.columnas-1){
					System.out.println("");
				}
			}			
		}
			
		for(int i = 0; i<this.p;i++)
			System.out.print(this.respuestas[i]+" ");
		System.out.println("");
		for(int i = 0; i<this.p;i++)
			System.out.print(this.tiempos[i]+" ");	
	}

	public int resolver() throws FileNotFoundException{
		Evaluador e = new Evaluador();
		this.resultado = 0;
		int cantidad = e.evaluarMatriz(this);
		for(int i=0; i<this.p;i++){
			if(respuestas[i]==cantidad){
				resultado++;
			}
		}
		
		return this.resultado;
	}
	
	public void guardarResultado(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter (new File (path));
		salida.println(this.resultado);
		salida.close();
	}

	public int getValorA() {
		return valorA;
	}


	public void setValorA(int valorA) {
		this.valorA = valorA;
	}


	public int getValorB() {
		return valorB;
	}


	public void setValorB(int valorB) {
		this.valorB = valorB;
	}


	public int getFilas() {
		return filas;
	}


	public void setFilas(int filas) {
		this.filas = filas;
	}


	public int getColumnas() {
		return columnas;
	}


	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}


	public int getM() {
		return m;
	}


	public void setM(int m) {
		this.m = m;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}


	public int getP() {
		return p;
	}


	public void setP(int p) {
		this.p = p;
	}


	public int[] getRespuestas() {
		return respuestas;
	}


	public void setRespuestas(int[] respuestas) {
		this.respuestas = respuestas;
	}


	public int[] getTiempos() {
		return tiempos;
	}


	public void setTiempos(int[] tiempos) {
		this.tiempos = tiempos;
	}


	public Matriz getMat() {
		return mat;
	}


	public void setMat(Matriz mat) {
		this.mat = mat;
	}
	
	public int getResultado() {
		return resultado;
	}


	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	
	
}
