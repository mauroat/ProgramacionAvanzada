package test;

import org.junit.Test;

import model.Generador;

public class GeneradorTest {
	@Test
	public void generadorParaCorridas(){
		Generador generador = new Generador();
//		generador.grafoRegularDadoNYPorcentajeAdy(1000, 75);
		generador.grafoRegularDadoNYPorcentajeAdy(1000, 50);
//		generador.grafoDadoNYPorcentajeAdy(600, 40);
//		generador.grafoDadoNYPorcentajeAdy(600, 60);
//		generador.grafoDadoNYPorcentajeAdy(600, 90);
		generador.exportarGrafo("grafo.in");
	}
	
}
