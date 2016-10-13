package sigmatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Evaluador {
	private Sigmatriz s;
	private Matriz aux;
	private int cantidad = 0;
	
	public int evaluarMatriz(Sigmatriz s) throws FileNotFoundException{
		int valor=0;
		// Inicializo las variables-objeto
		this.s = s;
		this.aux = new Matriz(s.getFilas(),s.getColumnas());
		
		// Inicializo mi matriz auxiliar
		this.aux.inicializarMatriz();
		
		// La variable i me define el pivote de fila
		for(int i = 0; i<s.getFilas(); i++){
			// La variable j me define el pivote de columna
			for(int j = 0; j<s.getColumnas(); j++){
				// La variable k me define recorredor de fila
				for(int m=i; m<s.getFilas();m++){
					// La variable l me define el recorredor de columna
					for(int n=j; n<s.getColumnas();n++){
						if(i==m && j==n){
							valor = s.getMat().getValorMatriz(m, n);
						} else if (i==m && n>j){
							valor = s.getMat().getValorMatriz(m, n) +aux.getValorMatriz(m, n-1);
						} else if (m>i && n==j){
							valor = s.getMat().getValorMatriz(m, n) +aux.getValorMatriz(m-1, n);
						} else if (m>i && n>j){
							valor = s.getMat().getValorMatriz(m, n) +aux.getValorMatriz(m, n-1) + aux.getValorMatriz(m-1, n) - aux.getValorMatriz(m-1, n-1);
						} else if (m<i && n<j){
							valor = s.getValorA()-1;
						} 
						if(valor >=s.getValorA() && valor <= s.getValorB()){
							this.cantidad++;
						}
						
						aux.setValorMatriz(m, n, valor);	
						
					}
				}
			}		
		}
		return cantidad;
	}

	public void generarCasoPrueba(String path, int filas, int columnas, int valorA, int valorB, int p) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter (new File (path));
			
		salida.println(valorA+" "+valorB);
		salida.println(filas+" "+columnas+" "+p);
		for(int i=0; i<filas;i++){
			for(int j=0; j<columnas;j++){
				salida.print(100+" ");				
			}
			salida.println("");
		}
		
		for(int i=0;i<p;i++){
			salida.print(1+" ");
		}
		salida.println("");
		for(int i=0;i<p;i++){
			salida.print(5+" ");
		}
		salida.close();
	}
	
	
	
	public Sigmatriz getS() {
		return s;
	}

	public void setS(Sigmatriz s) {
		this.s = s;
	}

	public Matriz getAux() {
		return aux;
	}

	public void setAux(Matriz aux) {
		this.aux = aux;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
