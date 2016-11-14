package grafo;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GrafoTest {

	@Test
	public void constructorTest() throws FileNotFoundException{
		Grafo g = new Grafo("test/IN/01-CasoEstandar.IN"); 
		System.out.println();
		g.kruskal();
		g.exportarResultado("test/OUT/01-CasoEstandar.OUT2");
	}
}
