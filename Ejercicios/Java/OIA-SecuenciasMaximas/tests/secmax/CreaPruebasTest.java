package secmax;

import java.io.FileNotFoundException;

import org.junit.Test;

public class CreaPruebasTest {
	//@Test
	public void casoDeFatiga() throws FileNotFoundException{
		CreaPruebas p = new CreaPruebas ("02.in",100000);
	}
	//@Test
	public void casoDeFatigaTodosInvalidos() throws FileNotFoundException{
		CreaPruebas p = new CreaPruebas ("03.in",100000);
	}
	//@Test
	public void casoDeFatigaTodosValidos() throws FileNotFoundException{
		CreaPruebas p = new CreaPruebas ("04.in",100000);
	}
}
