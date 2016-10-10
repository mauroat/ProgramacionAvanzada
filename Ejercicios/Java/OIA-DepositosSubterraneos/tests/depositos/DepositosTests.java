package depositos;

import java.io.FileNotFoundException;

import org.junit.Test;

public class DepositosTests {
	//@Test
	public void pruebaConstructor() throws FileNotFoundException{
		Depositos d = new Depositos ("01.in");
		d.mostrarEntrada();
	}
	
	@Test
	public void pruebaResolver1() throws FileNotFoundException{
		Depositos d = new Depositos ("01.in");
		d.resolver();
	}
}
