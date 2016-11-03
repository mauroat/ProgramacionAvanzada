package libros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Libro {
	
	private int cantidadLibros;
	private int[] alturas;
	private int resultado;
	
	public Libro (String path) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(path));
		this.cantidadLibros = sc.nextInt();
		this.alturas = new int[cantidadLibros];
		for(int i = 0; i < cantidadLibros; i++)
			this.alturas[i] = sc.nextInt();
		sc.close();
	}
	
	
	public void mostrarEntrada(){
		System.out.println(this.cantidadLibros);
		for(int i =0; i<this.cantidadLibros;i++)
			System.out.print(alturas[i]+" ");	
	}
	
	
	public void resolver(){
		int aux=0 , min=999999,  pos=-1;
		// Por cada elemento del array, tomo el de la posicion i...
		if(this.cantidadLibros == 1)
			this.resultado =  1;
		else if (this.cantidadLibros == 2){
			this.resultado =  mayor(0,1);
		} else {
		
			for(int i = 0; i<this.cantidadLibros;i++){
				aux=0;
				// y... calculo la suma de todos los adyacentes, exceptuado dicho elemento
				for(int j = 0; j < this.cantidadLibros - 1;j++){
					// Si el valor de mi puntero coincide con el de la posicion i
					if(i == j){
						// desplazo el puntero
						j++;
					}
					// 
					if(j < this.cantidadLibros - 1 && (j + 1) != i){
						
						aux += Math.abs(alturas[j]-alturas[j+1]);
						
					} else if (j + 1 < this.cantidadLibros - 1 && (j + 1) == i){
						
						aux += Math.abs(alturas[j]-alturas[j+2]);
					}
						
				}
				
				if(aux <= min){
					min= aux;
					pos = i;
				}
			}
			this.resultado =  pos+1;
		}
	}
	
	/*public int resolver(){
		if(this.cantidadLibros==1)
			return 1;
		else if (this.cantidadLibros==2){
			return mayor(0,1);
		} else {
			
			int min = Math.abs(alturas[0]-alturas[2]);
			int pos = 1, aux=-1;
			
			for(int i = 0; i < this.cantidadLibros ;i++){
				if(i==0){
					aux = Math.abs(0-alturas[i+1]);
				} else if (i==this.cantidadLibros-1){
					aux = Math.abs(alturas[i-1]-0);
				} else {
					aux = Math.abs(alturas[i-1]-alturas[i+1]);		
				}
				if(aux <= min){
					min= aux;
					pos = i;
				}
			}
			
			return pos+1;
		}

	}
	*/
	
	public int mayor(int a, int b){
		if(alturas[a]>alturas[b])
			return a;
		return b;
	}
	
	public void guardarResultado(String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter(new File(path));
		salida.println(this.resultado);
		salida.close();
	}


	public int getCantidadLibros() {
		return cantidadLibros;
	}


	public void setCantidadLibros(int cantidadLibros) {
		this.cantidadLibros = cantidadLibros;
	}


	public int[] getAlturas() {
		return alturas;
	}


	public void setAlturas(int[] alturas) {
		this.alturas = alturas;
	}


	public int getResultado() {
		return resultado;
	}


	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
	
}
