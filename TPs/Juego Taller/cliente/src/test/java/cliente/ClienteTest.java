package cliente;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import junit.framework.Assert;

public class ClienteTest {

	@SuppressWarnings("deprecation")
	@Test
	public void loginUsuarioOkTest() throws IOException, JsonSyntaxException, ClassNotFoundException {
		Gson gson = new Gson();
		Cliente c= new Cliente();
		Mensaje m = new Mensaje();
		m.setComando("login");
		Usuario usuario= new Usuario();
		usuario.setNombre_usuario("GUS");
		usuario.setPassword_usuario("123");
		m.setMensaje(Cliente.conversor(usuario, Usuario.class));
		c.getSalida().writeObject(gson.toJson(m));
		m = gson.fromJson((String) c.getEntrada().readObject(), Mensaje.class);
		Assert.assertEquals(true, m.getMensaje().equals("1"));
	}
	
	public void loginUsuarioErrorTest() throws IOException, JsonSyntaxException, ClassNotFoundException {
		Gson gson = new Gson();
		Cliente c= new Cliente();
		Mensaje m = new Mensaje();
		m.setComando("login");
		Usuario usuario= new Usuario();
		usuario.setNombre_usuario("GUS");
		m.setMensaje(Cliente.conversor(usuario, Usuario.class));
		c.getSalida().writeObject(gson.toJson(m));
		m = gson.fromJson((String) c.getEntrada().readObject(), Mensaje.class);
		Assert.assertEquals(false, m.getMensaje().equals("1"));
	}
	
	
	@Test
	public void getPersonajeUsuarioTest() throws IOException, JsonSyntaxException, ClassNotFoundException {
		Gson gson = new Gson();
		Cliente c= new Cliente();
		Mensaje m = new Mensaje();
		m.setComando("login");
		Usuario usuario= new Usuario();
		usuario.setNombre_usuario("GUS");
		usuario.setPassword_usuario("123");
		m.setMensaje(Cliente.conversor(usuario, Usuario.class));
		c.getSalida().writeObject(gson.toJson(m));
		m = gson.fromJson((String) c.getEntrada().readObject(), Mensaje.class);
		m.getMensaje().equals("1");
		MensajePersonaje pp = gson.fromJson((String) c.getEntrada().readObject(), MensajePersonaje.class);
		Assert.assertEquals("ORCO", pp.getRaza());
		Assert.assertEquals("CHAMAN", pp.getCasta());
		
	}
}
