package criterios_aceptacion;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import razas.*;



/***
 * 
 * 12) Como Personaje, quiero cambiar las alianzas establecidas cada cierta
 * cantidad de tiempo. Motivación: Para poder traicionar a mis aliados.
 * 
 * 
 * 
 ***/

public class Historia12Test {

	/***
	 * 
	 * 1. Dado un Personaje miembro de una Alianza, cuando se exceda el tiempo
	 * minimo de pertenencia en la misma y decida abandonarla, entonces el
	 * Personaje deja de formar parte de esta.
	 * 
	 ***/

	@Test
	public void historia12Criterio01_Test() throws FileNotFoundException{

		Personaje p1 = new Humano("jose");
		Personaje p2 = new Elfo("dani");
		Personaje p3 = new Elfo("Harry");
		Alianza alianza = new Alianza("Somos re cracks");
			
		alianza.agregarAAlianza(p2);
		alianza.agregarAAlianza(p1);
		alianza.agregarAAlianza(p3);
		
		Assert.assertEquals(3, alianza.cantidadMiembrosAlianza());

		Calendar actual = Calendar.getInstance();
		actual.add(Calendar.MINUTE, -3);
		p2.setLimiteMinimoPermanenciaAlianza(actual);
		alianza.dejarAlianza(p2);

		Assert.assertEquals(3, alianza.cantidadMiembrosAlianza());

		actual.add(Calendar.MINUTE, -10);
		p3.setLimiteMinimoPermanenciaAlianza(actual);
		alianza.dejarAlianza(p3);

		Assert.assertEquals(2, alianza.cantidadMiembrosAlianza());
	}

	/***
	 * 
	 * 2. Dado un Personaje, cuando se encuentre cercano a otro e interactuen,
	 * entonces este podra combatir contra el hasta definir un ganador.
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/

	@Test
	public void historia12Criterio02_Test() throws FileNotFoundException, CloneNotSupportedException {
		Personaje p1 = new Humano("Humano1");
		p1.setClase(new Guerrero());
		
		Personaje p2 = new Orco("Humano2");		
		p2.setClase(new Hechicero());

		/*
		 * Se preparan los equipos a pelear
		 */
		Equipo e1 = new Equipo(p1);
		Equipo e2 = new Equipo(p2);

		/*
		 * Compruebo que esten todos vivos
		 */
		Assert.assertEquals(true, e1.quedaAlgunoVivo());
		Assert.assertEquals(true, e2.quedaAlgunoVivo());

		/*
		 * Se ataca por turnos. La idea es que cuando un equipo se queda sin
		 * peleadores, se sumen los niveles de todos sus integrantes y se los
		 * multiplique por 10. Ese numero sera dividido por la cantidad de
		 * peleadores del equipo ganador y se le sumara a cada uno a su
		 * experiencia.
		 * 
		 */

		/*
		 * Arranca el combate
		 * */
			
		Random r = new Random();
		int aux = r.nextInt(2);
		
		/*
		 * Defino quien ataca primero
		 * */
		while(e1.quedaAlgunoVivo() && e2.quedaAlgunoVivo() ){
			if(aux == 1){
				e1.atacar(e2);
				e2.atacar(e1);
			} else {
				e2.atacar(e1);
				e1.atacar(e2);
			}		
		}

		if(e1.quedaAlgunoVivo()){
			e1.repartirExperiencia(e2.calcularExperiencia());
			e1.repartirItem(e2);		
		}	else if(e2.quedaAlgunoVivo()){
			e2.repartirExperiencia(e1.calcularExperiencia());
			e2.repartirItem(e1);
		}
		

		Assert.assertEquals(true, p1.estaVivo());
		Assert.assertEquals(false, p2.estaVivo());


	}
}
