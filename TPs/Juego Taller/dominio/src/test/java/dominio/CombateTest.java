package dominio;

import java.io.FileNotFoundException;

import org.junit.Test;

import razas.Orco;

public class CombateTest {

	@Test
	public void combateTest() throws FileNotFoundException, CloneNotSupportedException {
		Equipo e1= new Equipo(new Orco("orco1"));
		Equipo e2= new Equipo(new Orco("orco2"));
		Combate c= new Combate();
		Combate c1= new Combate("c1");
		Generico g=new Generico();
		c1.combatir(e1, e2);
		c.combatir(e1, g);
		c.declararGanador(e1, e2);
	}

}
