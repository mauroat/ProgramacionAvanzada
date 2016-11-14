package rescatando;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GrafoTest {

	@Test
	public void constructorTest() throws FileNotFoundException{
		Grafo g = new Grafo("test/Entrada/01-CasoEstandar.in");
		//g.dijsktra(nodoOrigen);
	}
	
}
