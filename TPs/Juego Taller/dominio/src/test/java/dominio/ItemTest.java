package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import items.BujiasHescher;
import junit.framework.Assert;

public class ItemTest {

	@Test
	public void itemTest() {
		BujiasHescher item = new BujiasHescher();
		item.setPotencia(100);
		item.setNombre("bujia");
		item.setDestreza(100);
		item.setAtaque(100);
		item.setDefensa(100);
		item.setMagia(100);
		item.setPrioridad(1);
		item.setVelocidad(100);
		Assert.assertEquals(100, item.getAtaque());
		Assert.assertEquals(100, item.getVelocidad());
		Assert.assertEquals(100, item.getPotencia());
		Assert.assertEquals(100, item.getDestreza());
		Assert.assertEquals(1, item.getPrioridad());
		Assert.assertEquals("bujia", item.getNombre());
	}

}
