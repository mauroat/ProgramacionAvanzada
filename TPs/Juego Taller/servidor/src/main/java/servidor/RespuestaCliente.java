package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import cliente.Cliente;
import cliente.Mensaje;
import cliente.MensajeCombate;
import cliente.MensajeDePersonajes;
import cliente.MensajePersonaje;
import cliente.Usuario;
import dominio.Personaje;
import razas.Humano;
import utilities.Loggin;

public class RespuestaCliente extends Thread {

	private final Socket socket;
	private final ObjectInputStream entrada;
	private final ObjectOutputStream salida;
	private String salaCliente;
	private MensajePersonaje mensajePersonaje;
	private MensajeDePersonajes mensajeDePersonajes;
	private MensajeCombate mensajeCombate;
	private final Gson gson = new Gson();

	public RespuestaCliente(String ip, Socket socket, ObjectInputStream entrada, ObjectOutputStream salida) {
		this.socket = socket;
		this.entrada = entrada;
		this.salida = salida;
	}

	public void run() {
		try {

			Mensaje msj;
			Mensaje msjA = new Mensaje(null, null);
			DBControlador con;

			Personaje p1 = new Humano(""); // auxiliar para la BD
			Usuario usuario = new Usuario();

			String cadenaLeida = (String) entrada.readObject();

			while (!((msj = gson.fromJson(cadenaLeida, Mensaje.class)).getComando().equals("desconectar"))) {
				switch (msj.getComando()) {
				case "registrar":
					con = new DBControlador();
					con.connect();
					msjA.setComando("estadoRegistro");
					usuario = (Usuario) Cliente.desconversor(msj.getMensaje(), Usuario.class);

					if (con.registrarUsuario(usuario)) {
						{
							msjA.setMensaje("1");
							salida.writeObject(gson.toJson(msjA));
						}
					} else {
						msjA.setMensaje("0");
						salida.writeObject(false);
					}
					con.close();
					break;

				case "crearPersonaje":
					Personaje p;
					con = new DBControlador();
					con.connect();
					p = (Personaje) entrada.readObject();
					con.registrarPersonaje(p, usuario);
					salida.writeObject((int) p.getIdPersonaje());
					break;

				case "login":
					con = new DBControlador();
					con.connect();
					msjA.setComando("estadoLogin");
					if (con.loguearUsuario((Usuario) Cliente.desconversor(msj.getMensaje(), Usuario.class))) {
						msjA.setMensaje("1");
						salida.writeObject(gson.toJson(msjA));
						MensajePersonaje pp = con.getPersonajeEdit((Usuario) Cliente.desconversor(msj.getMensaje(), Usuario.class));
						salida.writeObject(gson.toJson(pp));
					} else {
						msjA.setMensaje("0");
						salida.writeObject(gson.toJson(msjA));
					}
					con.close();
					break;

				case "salir":
					msjA.setComando("salir");
					msjA.setMensaje(null);
					salida.writeObject(gson.toJson(msjA));
					break;

				case "cerrar":
					msjA.setComando("cerrar");
					msjA.setMensaje(null);
					salida.writeObject(gson.toJson(msjA));
					break;

				case "conectado":
					mensajePersonaje = (MensajePersonaje) (gson.fromJson(cadenaLeida, MensajePersonaje.class)).clone();

					Servidor.getPersonajes().put(mensajePersonaje.getIdPersonaje(),
							(MensajePersonaje) mensajePersonaje.clone());

					for (RespuestaCliente conectado : Servidor.getConectados()) {
						mensajeDePersonajes = new MensajeDePersonajes(Servidor.getPersonajes());
						mensajeDePersonajes.setComando("conectado");
						conectado.salida.writeObject(gson.toJson(mensajeDePersonajes));
					}
					break;

				case "colision":
					mensajePersonaje = (MensajePersonaje) (gson.fromJson(cadenaLeida, MensajePersonaje.class)).clone();
					Servidor.getCombatiendo().put(mensajePersonaje.getIdPersonaje(),
							(MensajePersonaje) mensajePersonaje.clone());
					Servidor.getCombatiendo().put(mensajePersonaje.getIdPersonajeColision(),
							(MensajePersonaje) mensajePersonaje.clone());

					mensajeDePersonajes = new MensajeDePersonajes(Servidor.getCombatiendo());
					mensajeDePersonajes.setComando("colision");

					for (RespuestaCliente conectado : Servidor.getConectados()) {
						mensajeDePersonajes = new MensajeDePersonajes(Servidor.getCombatiendo());
						mensajeDePersonajes.setComando("conectado");
						conectado.salida.writeObject(gson.toJson(mensajeDePersonajes));
					}
					break;

				case "movimiento":
					mensajePersonaje = (MensajePersonaje) (gson.fromJson((String) cadenaLeida, MensajePersonaje.class));

					Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).setPosX(mensajePersonaje.getPosX());
					Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).setPosY(mensajePersonaje.getPosY());
					Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).setDireccion(mensajePersonaje.getDireccion());
					Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).setFrame(mensajePersonaje.getFrame());
					Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).setAlto(mensajePersonaje.getAlto());
					Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).setAncho(mensajePersonaje.getAncho());

					synchronized (Servidor.getConectados()) {
						for (RespuestaCliente conectado : Servidor.getConectados()) {
							MensajePersonaje pj = (MensajePersonaje) Servidor.getPersonajes().get(mensajePersonaje.getIdPersonaje()).clone();
							pj.setComando("movimiento");
							conectado.salida.writeObject(gson.toJson(mensajeDePersonajes));
						}
					}
					break;

				case "mostrarMapas":
					Loggin.getInstance().info("Mapa: " + msj.getMensaje());
					break;

				case "actualizarPersonaje":
					con = new DBControlador();
					con.connect();
					if (con.actualizarElPersonaje(
							(MensajePersonaje) Cliente.desconversor(msj.getMensaje(), MensajePersonaje.class))) {
						msjA.setMensaje("1");
						salida.writeObject(gson.toJson(msjA));
					} else {
						msjA.setMensaje("0");
						salida.writeObject(gson.toJson(msjA));
					}
					break;
				}
				cadenaLeida = (String) entrada.readObject();
			}

			entrada.close();
			salida.close();
			socket.close();

			Servidor.getPersonajes().remove(mensajePersonaje.getIdPersonaje());
			Servidor.getConectados().remove(this);

			for (RespuestaCliente conectado : Servidor.getConectados()) {
				mensajeDePersonajes = new MensajeDePersonajes(Servidor.getPersonajes());
				mensajeDePersonajes.setComando("conectado");
				conectado.salida.writeObject(gson.toJson(mensajeDePersonajes));
			}

			Loggin.getInstance().info(msj.getIp() + " se ha desconectado.");

		} catch (IOException e) {
			Loggin.getInstance().error("Error atencion de peticiones: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Loggin.getInstance().error("Error atencion de peticiones: " + e.getMessage());
		}
	}

	public MensajePersonaje getMensajePersonaje() {
		return mensajePersonaje;
	}

	public void setMensajePersonaje(MensajePersonaje mensajePersonaje) {
		this.mensajePersonaje = mensajePersonaje;
	}

	public MensajeDePersonajes getMensajeDePersonajes() {
		return mensajeDePersonajes;
	}

	public void setMensajeDePersonajes(MensajeDePersonajes mensajeDePersonajes) {
		this.mensajeDePersonajes = mensajeDePersonajes;
	}

	public MensajeCombate getMensajeCombate() {
		return mensajeCombate;
	}

	public void setMensajeCombate(MensajeCombate mensajeCombate) {
		this.mensajeCombate = mensajeCombate;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}
}
