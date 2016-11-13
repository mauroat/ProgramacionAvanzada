package test;

import org.junit.Test;

import model.GrafoNDNP;

public class ColoreoTest {

	final static String DIR_IN = "../Lote de pruebas/Entrada/";
	final static String DIR_OUT = "../Lote de pruebas/Salida/";
	final static String FILE_NAME = "GR_N1000_ADY75";

	@Test
	public void coloreandoTest() {
		
		String pathDeEntrada = DIR_IN + FILE_NAME + ".in";
		String pathSalidaSA = DIR_OUT + "SA/" + FILE_NAME + ".out";
		String pathSalidaMatula = DIR_OUT + "Matula/" + FILE_NAME + ".out";
		String pathSalidaWP = DIR_OUT + "WP/" + FILE_NAME + ".out";

		GrafoNDNP grafo = new GrafoNDNP(pathDeEntrada);

		grafo.coloreoSecuencialAleatorio();
		grafo.exportarResultado(pathSalidaSA);

		grafo.coloreoMatula();
		grafo.exportarResultado(pathSalidaMatula);

		grafo.coloreoWelshPowell();
		grafo.exportarResultado(pathSalidaWP);	
	}
}
