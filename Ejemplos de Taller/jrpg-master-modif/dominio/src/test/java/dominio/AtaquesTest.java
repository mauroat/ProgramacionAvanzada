package dominio;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import ataques.*;
import dominio.*;

import razas.*;

public class AtaquesTest {

	
	/*	@mauroat - 18-10-16:
	 *  Este test verifica que los ataques se agreguen y quiten correctamente
	 * */
	@Test
	public void añadirYQuitarAtaques(){
		Personaje p = new Elfo("Gago");
		// Agrego dos ataques
		p.agregarAtaque(new CuerpoACuerpo());
		p.agregarAtaque(new Hechizo());
		Assert.assertEquals(2, p.getCantidadAtaques());
		//p.getListaAtaques();
		
		// Quito un ataque
		p.quitarAtaque(new Hechizo());
		Assert.assertEquals(1, p.getCantidadAtaques());
		
		// Intengo quitar un ataque que no tengo
		p.quitarAtaque(new PuñoDragon());
		Assert.assertEquals(1, p.getCantidadAtaques());
		//p.getListaAtaques();
	}
	
	/*	@mauroat - 18-10-16:
	 *  Este test verifica que los ataques se agreguen y quiten correctamente
	 * */
	@Test
	public void atacarConAtaques() {
		// Atacante
		Personaje a = new Elfo("Anibal");
		// Atacado
		Personaje f = new Elfo("Falopa");
		
		// Agrego dos ataques
		a.agregarAtaque(new CuerpoACuerpo());
		a.agregarAtaque(new Hechizo());
		Assert.assertEquals(2, a.getCantidadAtaques());
		
		// El elfo tiene por defecto +5 de ataque. Atacar con "Hechizo" causa un daño de +6.
		// Todo esto combinado deberia restarle un daño de 11. Sin embargo tiene +5 de defensa, 
		// por ende su vida pasaría a ser 100-11+5 = 94
		a.atacar(f, a.getAtaque("Hechizo"));
		Assert.assertEquals(6,a.calcularPuntosDeAtaque());
		Assert.assertEquals(92, f.getVida());
		
		// El elfo tiene por defecto 5 de ataque. Atacar con "Hechizo" causa un daño de +5.
		// Todo esto combinado deberia restarle un daño de 11, por ende su vida pasaría a ser 89-10+5 = 89
		a.atacar(f, a.getAtaque("Cuerpo a cuerpo"));
		Assert.assertEquals(85, f.getVida());
	}
}
