package cuadras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Cuadra {
	private int cantidad, diferencia, neutrales;
	private int[][] cuadras;
	private int resultado;
	
	public Cuadra(String path) throws FileNotFoundException{
		Scanner sc = new Scanner (new File(path));
		this.cantidad = sc.nextInt();
		this.diferencia = sc.nextInt();
		this.neutrales= sc.nextInt();
		this.cuadras = new int[this.cantidad][3];
		for (int i = 0; i<this.cantidad;i++){
			this.cuadras[i][0] = sc.nextInt(); // Club A
			this.cuadras[i][1] = sc.nextInt(); // Club B
			this.cuadras[i][2] = sc.nextInt(); // Neutrales
		}
		sc.close();
	}
	
	public void mostrarEntrada(){
		System.out.println(this.cantidad+" "+this.diferencia+" "+this.neutrales);
		for (int i=0;i<this.cantidad;i++)
			System.out.println(this.cuadras[i][0]+" "+this.cuadras[i][1]+" "+this.cuadras[i][2]);
	}
	
	
	public void resolver(){
		int difAB = 0, neutro = 0;
		boolean marca = false;
		this.resultado = 0;
		
		for(int i=0;i<this.cantidad;i++){
			difAB = Math.abs(this.cuadras[i][0]-this.cuadras[i][1]);
			neutro = this.cuadras[i][2]-(this.cuadras[i][0]+this.cuadras[i][1]);
			if(difAB <= this.diferencia && neutro <= this.neutrales)
				this.resultado++;			
		}
	}

	public void escribirResultado(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter(new File(path));
		salida.println(this.resultado);
		salida.close();
	}
	
	
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(int diferencia) {
		this.diferencia = diferencia;
	}

	public int getNeutrales() {
		return neutrales;
	}

	public void setNeutrales(int neutrales) {
		this.neutrales = neutrales;
	}

	public int[][] getCuadras() {
		return cuadras;
	}

	public void setCuadras(int[][] cuadras) {
		this.cuadras = cuadras;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
	
	
}
