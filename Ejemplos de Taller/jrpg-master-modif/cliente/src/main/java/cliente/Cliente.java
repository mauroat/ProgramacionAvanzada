package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.*;
import frames.*;
import gui.CrearPersonaje;
import gui.Login;
import gui.MenuPrincipal;
import juego.Juego;
import razas.Humano;

public class Cliente extends Thread {
	private Socket cliente;
	private String miIp;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Semaphore semaforo;
	private String nickname;
	private String sala;
	private final Gson gson = new Gson();

	public Cliente(String ip, int puerto) throws UnknownHostException, IOException {
		cliente = new Socket(ip, puerto);
		miIp = cliente.getInetAddress().getHostAddress();
		salida = new ObjectOutputStream(cliente.getOutputStream());
		entrada = new ObjectInputStream(cliente.getInputStream());
		semaforo = new Semaphore(0);
	}

	public void run() {

		try {
			Usuario usuario=null;
			FrameInicial fr1;
			boolean opcion = false;
			Personaje p1 = new Humano(""); // auxiliar para la BD
			Personaje personaje = new Humano("");
			PaquetePersonaje pp = null;

			while (opcion == false) {
				Semaphore sem = new Semaphore(0);
				usuario = new Usuario(null, null, -1);
				fr1 = new FrameInicial(usuario, sem);
				fr1.setVisible(true);
				sem.acquire();
				// Mensaje
				Paquete paquete = new Paquete();
				paquete.setMensaje(Cliente.conversor(usuario, usuario.getClass()));

				switch (usuario.getAccion()) {
					case "registro":
						paquete.setComando("registrar");
						break;
					case "inicioSesion":
						paquete.setComando("iniciarSesion");
						break;
					case "salir":
						paquete.setComando("salir");
						break;
					case "cerrar":
						paquete.setComando("cerrar");
						break;
				}

				salida.writeObject(gson.toJson(paquete));

				paquete = gson.fromJson((String) entrada.readObject(), Paquete.class);
				
				switch (paquete.getComando()) {
				
					case "estadoRegistro":
						if (paquete.getMensaje().equals("1")) {
							personaje = new Humano(usuario.getNombre_usuario()); // O PIPI
							CrearPersonaje cpFrm = new CrearPersonaje (paquete, sem);
							cpFrm.setVisible(true);
							sem.acquire();
							paquete.setComando("creacionPersonaje");
	
							salida.writeObject(gson.toJson(paquete));
							salida.writeObject(personaje);
							JOptionPane.showMessageDialog(null, "Registro exitoso");
							personaje.setIdPersonaje((int) entrada.readObject());
							opcion = true;
						
							pp = new PaquetePersonaje(personaje.getIdPersonaje(), personaje.getRaza(), personaje.getClase().getNombre(), 0, 0, 0);
							
							
						} else {
							if (paquete.getMensaje().equals("0"))
								JOptionPane.showMessageDialog(null, "No se pudo registrar");
							opcion = false;
						}
						break;
	
					case "estadoInicioSesion":
						if (paquete.getMensaje().equals("1")) {
							JOptionPane.showMessageDialog(null, "Bienvenido");
							pp = gson.fromJson((String) entrada.readObject(), PaquetePersonaje.class);
							opcion = true;
						} else {
							if (paquete.getMensaje().equals("0"))
								JOptionPane.showMessageDialog(null, "Error en nombre de usuario o contraseña");	
							opcion = false;
						}
						break;
	
					case "salir":
						opcion = true;
						break;
	
					case "cerrar":
						opcion = false;
						break;
				}

			}
			
			Semaphore semaforo = new Semaphore(0);
//			Paquete paquete = new Paquete(null, "mostrarMapas");
			Paquete paquete = new Paquete(null, "menuPrincipal");
			MenuPrincipal menu = new MenuPrincipal(paquete,semaforo,pp);
			menu.setVisible(true);
//			ElegirMapa em = new ElegirMapa(paquete, sem);
//			em.setVisible(true);
			semaforo.acquire();
//			pp.setMundo(Integer.parseInt(paquete.getMensaje()));
			salida.writeObject(gson.toJson(paquete));

			Juego play = new Juego("BloodyWars", 1024, 768, this, pp);
			play.start();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws UnknownHostException, IOException {
		Cliente cliente = new Cliente("localhost", 9999);
		cliente.start();
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public String getMiIp() {
		return miIp;
	}

	public void setMiIp(String miIp) {
		this.miIp = miIp;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}

	public void setSalida(ObjectOutputStream salida) {
		this.salida = salida;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public void liberarSemaforo() {
		semaforo.release();
	}

	public String getNick() {
		return nickname;
	}

	public void setNick(String nick) {
		this.nickname = nick;
	}

	public static <T> String conversor(Object obj, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.toJson(obj, clazz);
	}

	public static <T> Object desconversor(String obj, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(obj, clazz);
	}
	
	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

}
