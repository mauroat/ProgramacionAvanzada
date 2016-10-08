package transmitiendo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Transmitiendo {

	private Hashtable<Integer,Integer> vecinos=new Hashtable<Integer,Integer>();
	private int cantidad,resultado,primero,ultimo;
	
	
	public Transmitiendo(String path) throws IOException {
		Scanner sc = new Scanner(new File(path));
		this.cantidad = sc.nextInt();
		while(sc.hasNextLine()){
			vecinos.put(sc.nextInt(), sc.nextInt());
		}		
		sc.close();
	}
	
	
	public void resolver()  {
		int a,b,maxSeguidos=1,marca=0;
		this.primero = this.primero();
		this.ultimo = this.ultimo();
		Enumeration<Integer> claves = this.vecinos.keys();
		Enumeration<Integer> datos = this.vecinos.elements();
		
		a = this.primero;
		while(marca == 0){
			a = this.vecinos.get(a);
			if(a != this.ultimo){
				maxSeguidos++;
			}else{
				marca=1;
			}
		}
		
		this.resultado = maxSeguidos;

	}
	
	private int ultimo(){
		int ultimo=0, marca=0, fin=0;
		Enumeration<Integer> datos = this.vecinos.elements();
		
		
		// Obtengo el primero
		while (datos.hasMoreElements() && fin == 0){
			Enumeration<Integer> claves = this.vecinos.keys();
			ultimo = datos.nextElement();
			
			while(claves.hasMoreElements()){
				if(ultimo == claves.nextElement()){
					marca=1;
				}else{
					//primero = claves.nextElement();
				}
			}
			if(marca == 0){
				fin = 1;
			}
		}
		return ultimo;
	}
	
	
	private int primero(){
		int primero=0, marca=0, fin=0;
		Enumeration<Integer> claves = this.vecinos.keys();
		
		// Obtengo el primero
		while (claves.hasMoreElements() && fin == 0){
			Enumeration<Integer> datos = this.vecinos.elements();
			primero = claves.nextElement();
			
			while(datos.hasMoreElements()){
				if(primero == datos.nextElement()){
					marca=1;
				}else{
					//primero = claves.nextElement();
				}
			}
			if(marca == 0){
				fin = 1;
			}
		}
		return primero;
	}
	
	public void verVecinos(){
		System.out.println(this.cantidad);
		Enumeration<Integer> claves = this.vecinos.keys();
		Enumeration<Integer> datos = this.vecinos.elements();
		while (claves.hasMoreElements() && datos.hasMoreElements()) {
		  System.out.print(claves.nextElement()+" ");
		  System.out.println(datos.nextElement());
		}
		
	}

	public void grabarResultado(String path) throws IOException{
		PrintWriter salida = new PrintWriter(new FileWriter(path));  //  preparo el arch de salida
		salida.println(this.resultado);
		salida.println(this.ultimo+" "+this.primero);
		salida.close();
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
