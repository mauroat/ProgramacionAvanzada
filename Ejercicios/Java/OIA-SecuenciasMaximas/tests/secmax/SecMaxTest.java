package secmax;

import java.io.FileNotFoundException;

import org.junit.Test;

public class SecMaxTest {
	//@Test
	public void resolverTest() throws FileNotFoundException{
		Secmax s = new Secmax("01.in");
		//s.mostrarValores();
		s.resolver();
		s.mostrarResultados(s.getContador(), s.getSecuenciaMax());
		s.guardarResultado("01.out");
	}
	
	//@Test
	public void resolverFatiga() throws FileNotFoundException{
		Secmax s = new Secmax("02.in");
		//s.mostrarValores();
		s.resolver();
		s.mostrarResultados(s.getContador(), s.getSecuenciaMax());
		s.guardarResultado("02.out");
	}
	
	//@Test
	public void resolverFatigaTodosInvalidos() throws FileNotFoundException{
		Secmax s = new Secmax("03.in");
		//s.mostrarValores();
		s.resolver();
		s.mostrarResultados(s.getContador(), s.getSecuenciaMax());
		s.guardarResultado("03.out");
	}
	
	@Test
	public void resolverFatigaTodosValidos() throws FileNotFoundException{
		Secmax s = new Secmax("04.in");
		//s.mostrarValores();
		s.resolver();
		s.mostrarResultados(s.getContador(), s.getSecuenciaMax());
		s.guardarResultado("04.out");
	}
}
