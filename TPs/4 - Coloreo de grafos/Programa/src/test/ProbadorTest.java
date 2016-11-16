package test;

import java.io.FileNotFoundException;

import org.junit.Test;

public class ProbadorTest {

	final static String DIR_ENTRADA = "../Lote de pruebas/Entrada/";
	final static String DIR_SALIDA = "../Lote de pruebas/Salida/";
	final static String FILE_NAME = "04 - CuatroNodosDisconexo";
	
	
	@Test
	public void probadorTest() throws FileNotFoundException{
		String entrada = "grafo.in";
		String salidaWP = "grafo.graf";
		

		/*	
		Probador probador = new Probador(entrada, salidaSA);
		if(probador.esCorrecto()){
			System.out.println("Secuencial Aleatorio --> TODO OK");
		}else{
			System.err.println("Secuencial Aleatorio --> ERROR");
		}
		/*
		probador = new Probador(entrada,salidaMatula);
		if(probador.esCorrecto()){
			System.out.println("Matula --> TODO OK");
		}else{
			System.err.println("Matula --> ERROR");
		}
		*/
		
		Probador probador = new Probador(entrada,salidaWP);
		if(probador.esCorrecto()){
			System.out.println("Secuencial WelshPowell --> TODO OK");
		}else{
			System.err.println("Secuencial WelshPowell --> ERROR");
		}
	}

}
