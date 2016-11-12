package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

public class Corridas {
	private int CANTIDAD_CORRIDAS;
	private int CANTIDAD_NODOS = 50;
	private int PORCENTAJE_ADYACENCIA;

	private int[] estadisticas ;//= new int[CANTIDAD_NODOS];
//	private static int[] estadisticasWP = new int[CANTIDAD_NODOS-1];
//	private static int[] estadisticasMAT = new int[CANTIDAD_NODOS-1];
	
	//private int mejorCorrida = 0;
	//private int mejorColores = 0;
	
	
	@Test
	public void ejecucionN5PAdy60() throws FileNotFoundException{
		
		this.PORCENTAJE_ADYACENCIA = 60;
		this.CANTIDAD_CORRIDAS = 10000;
		this.CANTIDAD_NODOS = 5;
		this.estadisticas = new int[CANTIDAD_NODOS];
		
		inicializarVector(estadisticas);
		
		GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_"+CANTIDAD_NODOS+"_PA"+PORCENTAJE_ADYACENCIA+".in");
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			
			
			gn.secuencialAleatorio();
			this.estadisticas[gn.getCantColores()]++;
			gn.mezclar();
			gn.matula();
			this.estadisticas[gn.getCantColores()]++;
			gn.mezclar();
			gn.welshPowell();
			this.estadisticas[gn.getCantColores()]++;			
			gn.mezclar();
		}

		generarResultado("test/Estadisticas/GA_"+CANTIDAD_NODOS+"_PA"+PORCENTAJE_ADYACENCIA+".txt");
		
		
	}
	
	private void inicializarVector(int[] vector) {
		for(int i = 0; i < CANTIDAD_NODOS ; i++)
			vector[i] = 0;
	}

	//@Test
	public void ejecucionPAdy40() throws FileNotFoundException{
		GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_N600_PA40.in");
	
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			
			gn.secuencialAleatorio();
			this.estadisticas[i] = gn.getCantColores();
			gn.matula();
			this.estadisticas[i] = gn.getCantColores();
			gn.welshPowell();
			this.estadisticas[i] = gn.getCantColores();
			
			gn.mezclar();	
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
			this.estadisticas[i] = gn.getCantColores();
			
			gn.matula();
			//gn.exportarResultado("Resultado2.out");
			this.estadisticas[i] = gn.getCantColores();
			
			gn.welshPowell();
			//gn.exportarResultado("Resultado3.out");
			this.estadisticas[i] = gn.getCantColores();
		}
		
		int[] valoresSA = calcularMenores(estadisticas);
		int[] valoresMAT = calcularMenores(estadisticas);
		int[] valoresWP = calcularMenores(estadisticas);
		
		generarResultado("test/Estadisticas/SecAl_N600_PAdy60.txt");
		generarResultado("test/Estadisticas/Matula_N600_PAdy60.txt");
		generarResultado("test/Estadisticas/WP_N600_PAdy60.txt");
	}
	
	//@Test
	public void ejecucionPAdy90() throws FileNotFoundException{
		
		for(int i = 0; i < CANTIDAD_CORRIDAS ;i++){
			GrafoNDNP gn = new GrafoNDNP("test/Entrada/GA_N600_PA90.in");
			
			gn.secuencialAleatorio();
			//gn.exportarResultado("Resultado1.out");
			this.estadisticas[i] = gn.getCantColores();		
			//gn.mezclar();
			//gn.matula();
			//gn.exportarResultado("Resultado2.out");
			//this.estadisticasMAT[i] = gn.getCantColores();
			//gn.mezclar();
			//gn.welshPowell();
			//gn.exportarResultado("Resultado3.out");
			//this.estadisticasWP[i] = gn.getCantColores();
		}
		
		int[] valoresSA = calcularMenores(estadisticas);
		//int[] valoresMAT = calcularMenores(estadisticasMAT);
		//int[] valoresWP = calcularMenores(estadisticasWP);
		
		generarResultado("test/Estadisticas/SecAl_N600_PAdy90.txt");
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
	
	
	
	public void generarResultado(String path) throws FileNotFoundException{	
		PrintWriter salida = new PrintWriter (new File(path));
		salida.println(this.CANTIDAD_NODOS+ " "+PORCENTAJE_ADYACENCIA);
		
		for(int i = 0 ; i < CANTIDAD_NODOS ; i++){
			salida.println(this.estadisticas[i]);
		}
		salida.close();
	}
	
}
