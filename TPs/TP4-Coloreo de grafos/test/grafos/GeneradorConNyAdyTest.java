package grafos;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GeneradorConNyAdyTest {

	@Test
	public void generator() throws FileNotFoundException{
		Grafo g = new Grafo();
		g.generadorConNyPorcentajeAdyacencia(5, 90);
		g.exportar("GA_N5_PA90.in");
		
	}
	
}
