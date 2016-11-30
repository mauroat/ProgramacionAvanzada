package cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import javax.rmi.CORBA.Util;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Personaje;
import gui.MenuPrincipal;
import gui.Splash;
import juego.Juego;
import razas.Elfo;
import razas.Humano;
import razas.Orco;
import utilities.Loggin;

public class Cliente extends Thread {

	private Socket cliente;
	private String ip;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Semaphore semaforo;
	private String nickname;
	private String sala;
	private int puerto;
	private final Gson gson = new Gson();

	public Cliente(){
		leerConfig();
		try {
			cliente = new Socket(this.ip, this.puerto);
			ip = cliente.getInetAddress().getHostAddress();
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error servidor no iniciado");
			Loggin.getInstance().error("Error al inciar el cliente, servidor no iniciado");
		}
		semaforo = new Semaphore(0);
		Loggin.getInstance().info("Inicia cliente: " + ip);
	}

	public void run() {

		try {
			Usuario usuario = null;
			Splash fr1;
			boolean opcion = false;

			// Hardcodeo asqueroso para evitar meter mano en dominio
			Personaje p1 = new Humano(""); // auxiliar para la BD
			Personaje p2 = new Orco("");
			Personaje p3 = new Elfo("");

			MensajePersonaje pp = null;

			while (opcion == false) {
				Semaphore semaforo = new Semaphore(0);
				usuario = new Usuario(null, null, -1);
				fr1 = new Splash(usuario, semaforo);
				fr1.setVisible(true);
				semaforo.acquire();
				// Mensaje
				Mensaje paquete = new Mensaje();
				paquete.setMensaje(Cliente.conversor(usuario, usuario.getClass()));

				switch (usuario.getAccion()) {
				case "login":
					paquete.setComando("login");
					break;
				case "registro":
					paquete.setComando("registrar");
					break;
				case "salir":
					paquete.setComando("salir");
					break;
				case "cerrar":
					paquete.setComando("cerrar");
					break;
				}

				salida.writeObject(gson.toJson(paquete));
				paquete = gson.fromJson((String) entrada.readObject(), Mensaje.class);

				switch (paquete.getComando()) {
				case "estadoLogin":
					if (paquete.getMensaje().equals("1")) {
						pp = gson.fromJson((String) entrada.readObject(), MensajePersonaje.class);
						opcion = true;
					} else {
						if (paquete.getMensaje().equals("0"))
							JOptionPane.showMessageDialog(null, "Error en nombre de usuario o contraseña");
						opcion = false;
					}
					break;

				case "estadoRegistro":
					if (paquete.getMensaje().equals("1")) {
						salida.writeObject(gson.toJson(paquete));
						JOptionPane.showMessageDialog(null, "Registro exitoso, por favor vuelva a loguearse para verificar su usuario");
						opcion = false;

					} else {
						if (paquete.getMensaje().equals("0"))
							JOptionPane.showMessageDialog(null, "No se pudo registrar, inténtelo nuevamente");
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
			Mensaje paquete = new Mensaje(null, "menuPrincipal");
			MenuPrincipal menu = new MenuPrincipal(paquete, semaforo, pp, this);
			menu.setVisible(true);

			semaforo.acquire();
			pp.setMundo(Integer.parseInt(paquete.getMensaje()));
			pp.setNick(usuario.getNombre_usuario());
			salida.writeObject(gson.toJson(paquete));

			Juego play = new Juego("BloodyWars", 1024, 768, this, pp, getNombreMundo(pp.getMundo()));
			Clip sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File(Util.class.getResource("/wav/got.wav").getFile())));
			sonido.start();
			sonido.loop(Clip.LOOP_CONTINUOUSLY);
			
			play.start();
		} catch (IOException e) {
			Loggin.getInstance().error("Error Cliente: " + e.getMessage());
		} catch (InterruptedException e) {
			Loggin.getInstance().error("Error InterruptedException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Loggin.getInstance().error("Error ClassNotFoundException: " + e.getMessage());
		} catch (LineUnavailableException e) {
			Loggin.getInstance().error("Error LineUnavailableException: "+ e.getMessage());
		} catch (UnsupportedAudioFileException e) {
			Loggin.getInstance().error("Error UnsupportedAudioFileException: "+ e.getMessage());
		}

	}

	private String getNombreMundo(int mundo) {
		return mundo == 1 ? "llanura" : "desierto";
	}

	public static void main(String args[]){
		try{
			Cliente cliente = new Cliente();
			cliente.start();
		}catch(Exception e){
			Loggin.getInstance().error("Error iniciar cliente: "+e.getMessage());
		}
	}

	private void leerConfig() {
		String linea;
		String[] splitLine;
		Scanner sc = null;
		try {
			sc = new Scanner(new File("App.config"));
		} catch (FileNotFoundException e) {
			Loggin.getInstance().error("Error leerConfig " + e.getMessage());
		}
		linea = sc.nextLine();
		splitLine = linea.split(":");
		this.ip = splitLine[1];

		linea = sc.nextLine();
		splitLine = linea.split(":");
		this.puerto = Integer.parseInt(splitLine[1]);
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public String getMiIp() {
		return ip;
	}

	public void setMiIp(String miIp) {
		this.ip = miIp;
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
