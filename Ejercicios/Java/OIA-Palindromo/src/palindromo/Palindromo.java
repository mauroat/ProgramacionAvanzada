package palindromo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Palindromo {
	private String palabra;
	private String[] listado;
	
	Palindromo(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		this.palabra = sc.nextLine();
		sc.close();			
	}
	
	public void verPalabra(){
		System.out.println(palabra);
	}
	
	public char[] separarEnLetras(){
		char[] palArray = new char[palabra.length()];
		for(int i = 0; i < palabra.length(); i++){
			palArray[i] = palabra.charAt(i);
		}
		return palArray;
	}
	
	public int palindromo (String palabra, int izq, int der, int contador){
		if(palabra.charAt(izq) == palabra.charAt(der)){
			return palindromo(palabra,izq-1,der-1,contador+1);
		}
		return contador;
		
	}
	
	
	public void resolver(){
		for(int i=1; i<this.palabra.length()-1;i++){
			int cantidad = palindromo(this.palabra,i,this.palabra.length(),0 );
			if(cantidad > 0){
				for(int j=i-cantidad ;j<i+cantidad;j++){
					listado[i] = this.palabra.valueOf(j);
				}
				listado[i]+= "palindromo";
			}
		}
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String[] getListado() {
		return listado;
	}

	public void setListado(String[] listado) {
		this.listado = listado;
	}
	
	public String getListadoValor(int i) {
		return listado[i];
	}
	
	
}
