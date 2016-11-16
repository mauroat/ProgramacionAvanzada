package kruskal;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GrafoTest {

	@Test
	public void test() throws FileNotFoundException {
		Grafo g = new Grafo("minotauro.in");
		g.Kruskal();
		g.mostrar();
	}

}
