package test;

import org.junit.Test;

public class ProbadorTest {

	final static String DIR_ENTRADA = "../Lote de pruebas/Entrada/";
	final static String DIR_SALIDA = "../Lote de pruebas/Salida/";
	final static String FILE_NAME = "04 - CuatroNodosDisconexo";
	
	
	@Test
	public void probadorTest(){
		String entrada = DIR_ENTRADA + FILE_NAME + ".in";
		String salidaSA = DIR_SALIDA + "SA/" + FILE_NAME + ".out";
		String salidaMatula = DIR_SALIDA + "Matula/" + FILE_NAME + ".out";
		String salidaWP = DIR_SALIDA + "WP/" + FILE_NAME + ".out";

		Probador probador;
		
		probador = new Probador(entrada,salidaSA);
		if(probador.isCorrecto()){
			System.out.println("Secuencial Aleatorio --> TODO OK");
		}else{
			System.err.println("Secuencial Aleatorio --> ERROR");
		}
		
		probador = new Probador(entrada,salidaMatula);
		if(probador.isCorrecto()){
			System.out.println("Matula --> TODO OK");
		}else{
			System.err.println("Matula --> ERROR");
		}
		
		probador = new Probador(entrada,salidaWP);
		if(probador.isCorrecto()){
			System.out.println("Secuencial WelshPowell --> TODO OK");
		}else{
			System.err.println("Secuencial WelshPowell --> ERROR");
		}
	}
}
