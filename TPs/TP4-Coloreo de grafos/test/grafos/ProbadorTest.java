package grafos;

import java.io.FileNotFoundException;

import org.junit.Test;

import probador.Probador;

public class ProbadorTest {

	@Test
	public void probadorColoreo() throws FileNotFoundException{
		GrafoNDNP g = new GrafoNDNP("test/Entrada/GA_N200_PA60.in");
		g.matula();
		g.exportar("test/Salida esperada/GA_N200_PA60.out");
		
		Probador p = new Probador();
		p.probador("test/Entrada/GA_N200_PA60.in", "test/Salida esperada/GA_N200_PA60.out");
		
	}
	
}
