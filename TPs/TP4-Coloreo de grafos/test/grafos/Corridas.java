package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

public class Corridas {
	final static int CANTIDAD_CORRIDAS = 10000;
	final static int CANTIDAD_NODOS = 12;
	final static int PORCENTAJE_ADYACENCIA = 60;

	private static int[] estadisticasSA = new int[CANTIDAD_CORRIDAS];
	private static int[] estadisticasWP = new int[CANTIDAD_CORRIDAS];
	private static int[] estadisticasMAT = new int[CANTIDAD_CORRIDAS];
	
	private int mejorCorrida = 0;
	private int mejorColores = 0;
	
	
	//@Test
	public void ejecucionN5PAdy40() throws FileNotFoundException{
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_N5_PA90.in");
			
			gn.secuencialAleatorio();
			//gn.exportarResultado("test/Salida/Resultado1.out");
			this.estadisticasSA[i] = gn.getCantColores();
			
			gn.matula();
			//gn.exportarResultado("Resultado2.out");
			this.estadisticasMAT[i] = gn.getCantColores();
			
			gn.welshPowell();
			//gn.exportarResultado("Resultado3.out");
			this.estadisticasWP[i] = gn.getCantColores();
		}
		
		int[] valoresSA = calcularMenores(estadisticasSA);
		int[] valoresMAT = calcularMenores(estadisticasMAT);
		int[] valoresWP = calcularMenores(estadisticasWP);
		
		generarResultado("test/Estadisticas/SecAl_N5_PAdy90.txt","Secuencial Aleatorio",valoresSA);
		generarResultado("test/Estadisticas/Matula_N5_PAdy90.txt","Matula",valoresMAT);
		generarResultado("test/Estadisticas/WP_N5_PAdy90.txt","Welsh Powell",valoresWP);
		
	}
	
	@Test
	public void ejecucionPAdy40() throws FileNotFoundException{
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_N600_PA40.in");
			
			gn.secuencialAleatorio();
			//gn.exportarResultado("test/Salida/Resultado1.out");
			this.estadisticasSA[i] = gn.getCantColores();
			
			gn.matula();
			//gn.exportarResultado("Resultado2.out");
			this.estadisticasMAT[i] = gn.getCantColores();
			
			gn.welshPowell();
			//gn.exportarResultado("Resultado3.out");
			this.estadisticasWP[i] = gn.getCantColores();
		}
		
	/*	int[] valoresSA = calcularMenores(estadisticasSA);
		int[] valoresMAT = calcularMenores(estadisticasMAT);
		int[] valoresWP = calcularMenores(estadisticasWP);
		
		generarResultado("test/Estadisticas/SecAl_N600_PAdy40.txt","Secuencial Aleatorio",valoresSA);
		generarResultado("test/Estadisticas/Matula_N600_PAdy40.txt","Matula",valoresMAT);
		generarResultado("test/Estadisticas/WP_N600_PAdy40.txt","Welsh Powell",valoresWP);
		*/
	}
	
	
	//@Test
	public void ejecucionPAdy60() throws FileNotFoundException{
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_N600_PA60.in");
			
			gn.secuencialAleatorio();
			//gn.exportarResultado("Resultado1.out");
			this.estadisticasSA[i] = gn.getCantColores();
			
			gn.matula();
			//gn.exportarResultado("Resultado2.out");
			this.estadisticasMAT[i] = gn.getCantColores();
			
			gn.welshPowell();
			//gn.exportarResultado("Resultado3.out");
			this.estadisticasWP[i] = gn.getCantColores();
		}
		
		int[] valoresSA = calcularMenores(estadisticasSA);
		int[] valoresMAT = calcularMenores(estadisticasMAT);
		int[] valoresWP = calcularMenores(estadisticasWP);
		
		generarResultado("test/Estadisticas/SecAl_N600_PAdy60.txt","Secuencial Aleatorio",valoresSA);
		generarResultado("test/Estadisticas/Matula_N600_PAdy60.txt","Matula",valoresMAT);
		generarResultado("test/Estadisticas/WP_N600_PAdy60.txt","Welsh Powell",valoresWP);
	}
	
	//@Test
	public void ejecucionPAdy90() throws FileNotFoundException{
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_N600_PA90.in");
			
			gn.secuencialAleatorio();
			//gn.exportarResultado("Resultado1.out");
			this.estadisticasSA[i] = gn.getCantColores();		
			//gn.mezclar();
			//gn.matula();
			//gn.exportarResultado("Resultado2.out");
			//this.estadisticasMAT[i] = gn.getCantColores();
			//gn.mezclar();
			//gn.welshPowell();
			//gn.exportarResultado("Resultado3.out");
			//this.estadisticasWP[i] = gn.getCantColores();
		}
		
		int[] valoresSA = calcularMenores(estadisticasSA);
		//int[] valoresMAT = calcularMenores(estadisticasMAT);
		//int[] valoresWP = calcularMenores(estadisticasWP);
		
		generarResultado("test/Estadisticas/SecAl_N600_PAdy90.txt","Secuencial Aleatorio",valoresSA);
		//generarResultado("test/Estadisticas/Matula_N600_PAdy90.txt","Matula",valoresMAT);
		//generarResultado("test/Estadisticas/WP_N600_PAdy90.txt","Welsh Powell",valoresWP);
		
	}
	
	private boolean mayor(int a, int b){
		return a > b;
	}
	
	private int[] calcularMenores(int[] estadisticas){
		int[] valores = new int [2]; // posicion 1 - posicion, posicion 2 - valor
		
		valores[1] = 999999999;
		
		for(int i = 0; i < CANTIDAD_CORRIDAS;i++){
			if(estadisticas[i] < valores[1]){
				valores[0] = i;
				valores[1] = estadisticas[i];
				
			}
		}
	
		return valores;		
	}
	
	
	
	public void generarResultado(String path, String algoritmo, int[] valores) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter (new File(path));
		salida.println("Algoritmo: "+algoritmo);
		salida.println("Menor cantidad de colores: "+valores[1]);
		salida.println("Nro de corrida: "+valores[0]);

		salida.close();
	}
	
}
