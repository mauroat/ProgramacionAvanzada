package servidor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import cliente.Cliente;
import cliente.Mensaje;
import cliente.MensajePersonaje;
import cliente.Usuario;
import dominio.Personaje;
import utilities.Loggin;

// obtenido de https://andrejserafim.wordpress.com/2010/10/07/testing-threads-with-junit/s
public class ServidorTest {

	@Test
	public void pruebaThreadServidor() throws InterruptedException{
		 Servidor s = new Servidor() {
		     @Override 
		     public void run() {
		    	 Assert.fail();
		      }           
	     };
		Thread t1 = new Thread(s);
		t1.start();
		t1.join();
	}
	
	@Test
	public void actualizarRaza(){
		Usuario user = new Usuario();
		user.setNombre_usuario("GUS");
		
		DBControlador db = new DBControlador();
		db.connect();
		MensajePersonaje pp = db.getPersonaje(user);
		Assert.assertEquals("ORCO", pp.getRaza());
		
		MensajePersonaje msj = new MensajePersonaje();
		msj.setIdPersonaje(3);
		msj.setRaza("HUMANO");
		msj.setCasta("HECHICERO");
		db.actualizarElPersonaje(msj);
		pp = db.getPersonaje(user);
		Assert.assertEquals("HUMANO", pp.getRaza());
		
		MensajePersonaje dejarOriginal = new MensajePersonaje();
		dejarOriginal.setIdPersonaje(3);
		dejarOriginal.setRaza("ORCO");
		dejarOriginal.setCasta("HECHICERO");
		db.actualizarElPersonaje(dejarOriginal);
	}
	
	@Test
	public void conectadosTest(){
		Servidor server = new Servidor();
		server.start();
		Assert.assertTrue(server.getConectados().isEmpty());
		
	}
}
