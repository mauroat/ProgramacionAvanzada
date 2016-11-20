package dominio;

import org.junit.Assert;
import org.junit.Test;

public class GenericoTest {

	@Test
	public void constructorGenerico(){
		Generico g1 = new Generico("Pato");
		
		
		Generico g2 = new Generico("Peto");
		
		Generico g3 = new Generico("Pito");
		
	}
	
	@Test
	public void atacarConGenerico(){
		Generico g1 = new Generico("Pato");
		Generico g2 = new Generico("Peto");
		
		g1.atacar(g2);
		// Si es un elfo puede ser que su vida sea 100 despues de ser atacado
		
	}
	
}
