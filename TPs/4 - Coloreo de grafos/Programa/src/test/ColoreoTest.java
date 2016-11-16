package test;

import java.io.FileNotFoundException;

import org.junit.Test;

import grafo.GrafoNDNP;

public class ColoreoTest {

	final static String DIR_IN = "s";
	final static String DIR_OUT = "../Lote de pruebas/Salida/";
	final static String FILE_NAME = "GR_N1000_ADY75";

	//@Test
	public void coloreandoTest() throws FileNotFoundException {
		
		String pathDeEntrada = DIR_IN + FILE_NAME + ".in";
		String pathSalidaSA = DIR_OUT + "SA/" + FILE_NAME + ".out";
		String pathSalidaMatula = DIR_OUT + "Matula/" + FILE_NAME + ".out";
		String pathSalidaWP = DIR_OUT + "WP/" + FILE_NAME + ".out";

		GrafoNDNP grafo = new GrafoNDNP(pathDeEntrada);

		grafo.secuencialAleatorio();
		grafo.exportar(pathSalidaSA);

		grafo.matula();
		grafo.exportar(pathSalidaMatula);

		grafo.welshPowell();
		grafo.exportar(pathSalidaWP);	
	}
	
	
	@Test
	public void colorearNPartito() throws FileNotFoundException{
		GrafoNDNP g = new GrafoNDNP("grafo.in");
		g.welshPowell();
		g.exportar("grafo.GRAF");
	}
	
}
