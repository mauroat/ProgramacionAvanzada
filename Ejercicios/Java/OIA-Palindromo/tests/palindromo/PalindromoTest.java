package palindromo;

import java.io.FileNotFoundException;

import org.junit.Test;

public class PalindromoTest {

	//@Test
	public void constructorTest() throws FileNotFoundException{
		Palindromo p = new Palindromo("palabra1.in");
		p.verPalabra();
	}
	
	@Test
	public void probarResolver() throws FileNotFoundException{
		Palindromo p = new Palindromo("palabra1.in");
		p.verPalabra();
		p.resolver();
		for(int i=0;i<p.getListado().length;i++){
			System.out.println(p.getListadoValor(i));
		}
		
	}
}
