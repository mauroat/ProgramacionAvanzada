package test;

import org.junit.Test;

import grafo.Generador;

public class GeneradorTest {
	@Test
	public void generadorParaCorridas(){
		Generador generador = new Generador();
//		generador.grafoRegularDadoNYPorcentajeAdy(4, 90);
//		generador.grafoRegularDadoNYPorcentajeAdy(1000, 75);
		//generador.grafoRegularDadoNYPorcentajeAdy(1000, 50);
//		generador.grafoDadoNYPorcentajeAdy(600, 40);
//		generador.grafoDadoNYPorcentajeAdy(600, 60);
//		generador.grafoDadoNYPorcentajeAdy(600, 90);
		generador.grafoNPartito(800, 400);
		generador.exportarGrafo("grafo.in");
	}
	
}
