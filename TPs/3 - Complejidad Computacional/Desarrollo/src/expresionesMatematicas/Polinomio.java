package expresionesMatematicas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Polinomio 
{
	// Atributos
	private int grado;
	private double[] coeficientes; // La posición 0 del array contiene el coeficiente de grado n y la posición n-1 contiene el término indepte.
	
	// Constructores
	public Polinomio (int grado, double[] coeficientes)
	{
		this.grado = grado;
		this.coeficientes = new double[grado + 1];
		this.coeficientes = coeficientes;
	}
	
	public Polinomio (String path) throws FileNotFoundException, IOException {
		Locale.setDefault(new Locale("en","US"));
		Scanner sc = new Scanner(new File(path));
		this.grado = sc.nextInt();
		this.coeficientes = new double[this.grado + 1];
		for(int i = 0; i < this.grado+1; i++)
			this.coeficientes[i] = sc.nextDouble();
		sc.close();
	}
	
	
	
	// Métodos Privados
	private double potencia (double x, int n)
	{
		if (n == 0)
			return 1;
		else if (n == 1)
			return x;
		else
			return x * potencia (x, n-1);
	}
	
	private double potenciaConParidad(double x, int n)
	{
		if (n == 0)
			return 1;
		else if (n % 2 == 0)
			return potenciaConParidad(x * x, n / 2);
		else
			return x * potenciaConParidad(x, n - 1);
	}
	
	// Métodos Públicos
	/**
	 * Complejidad computacional: ¿n*(n-1)?
	 * @param x
	 * @return
	 */
	
	public void mostrarEntrada(){
		System.out.println(this.grado);
		for (int i = 0; i < this.coeficientes.length; i++)
			System.out.println(this.coeficientes[i]);
	}
	
	public double evaluarMSucesivas (double x)
	{
		double resultado = 0;
		int auxGrado = this.grado;
		double auxProd;
			
		for (int i = 0; i < this.coeficientes.length; i++)
		{
			auxProd = x;
			
			for (int j = 1; j < auxGrado; j++)
				auxProd *= x;
			
			if (auxGrado > 0)
				resultado += coeficientes[i] * auxProd;
			else
				resultado += coeficientes[i];
				
			auxGrado--;
		}
		
		return resultado;
	}
	
	public double evaluarRecursiva (double x)
	{
		double resultado = 0;
		
		for (int i = this.grado; i > -1; i--)
			resultado += potencia(x, i) * this.coeficientes[this.grado - i];
		
		return resultado;
	}
	
	public double evaluarRecursivaPar (double x)
	{
		double resultado = 0;
		
		for (int i = this.grado; i > -1; i--)
			resultado += potenciaConParidad(x, i) * this.coeficientes[this.grado - i];
		
		return resultado;
	}
	
	public double evaluarProgDinamica(double x ){
		double xn = 1.0;
		double resultado = this.coeficientes[this.grado];
		for(int i = this.grado - 1; i > -1;i--){
			xn*=x;
			resultado += this.coeficientes[i]*xn;
		}
		return resultado;
	}
	
	public double evaluarMejorada(double x)
	{
		double result = 0.0;
		for(int i = 0; i <= this.grado;i++){
			result = result * x + this.coeficientes[i];
		}		
		return result;
	}
	
	public double evaluarHorner (double x)
	{
		double resultado = 0;
		
		for (int i = 0; i < this.grado + 1; i++)
		{
			resultado = this.coeficientes[i] + (x * resultado);
		}
		
		return resultado;
	}
	
	
	public double evaluarPow(double x ){
		double aux = 0.0;
		for (int i = this.grado; i >= 0; i-- )
			aux += Math.pow(x, i) * this.coeficientes[this.grado-i];
		return aux;
	}
	



}