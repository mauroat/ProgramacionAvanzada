package grafos;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GeneradorConNyAdyTest {
	private int CANTIDAD_CORRIDAS;
	private int CANTIDAD_NODOS;
	private int PORCENTAJE_ADYACENCIA;
	
	@Test
	public void generator() throws FileNotFoundException{
		Grafo g = new Grafo();
		
		this.PORCENTAJE_ADYACENCIA = 60;
		this.CANTIDAD_CORRIDAS = 10000;
		this.CANTIDAD_NODOS = 5;
		
		g.generadorConNyPorcentajeAdyacencia(CANTIDAD_NODOS, PORCENTAJE_ADYACENCIA);
		g.exportar("test/Entrada/GA_"+CANTIDAD_NODOS+"_PA"+PORCENTAJE_ADYACENCIA+".in");
		
	}
	
}
