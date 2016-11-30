package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import com.google.gson.Gson;

import utilities.Loggin;

public class EscuchaMensajes extends Thread {

	private Cliente cliente;
	private ObjectInputStream entrada;
	private final Gson gson = new Gson();
	private Map<Integer, MensajePersonaje> personajes;
	private Map<Integer, MensajePersonaje> combatiendo;
	private Semaphore semaforo;

	public EscuchaMensajes(Cliente cliente) {
		this.cliente = cliente;
		entrada = cliente.getEntrada();
		semaforo = new Semaphore(1);
	}

	public void run() {

		try {
			Mensaje paquete;
			MensajePersonaje personaje;
			personajes = new HashMap<>();
			combatiendo = new HashMap<>();

			while (true) {

				String objetoLeido = (String) entrada.readObject();
				paquete = gson.fromJson(objetoLeido, Mensaje.class);
				semaforo.acquire();

				switch (paquete.getComando()) {

				case "conectado":
					personajes = (Map<Integer, MensajePersonaje>) gson.fromJson(objetoLeido, MensajeDePersonajes.class).getPersonajes();

					semaforo.release();
					break;

				case "movimiento":
					personaje = (MensajePersonaje) gson.fromJson(objetoLeido, Mensaje.class);
					personajes.get(personaje.getIdPersonaje()).setPosX(personaje.getPosX());
					personajes.get(personaje.getIdPersonaje()).setPosY(personaje.getPosY());
					personajes.get(personaje.getIdPersonaje()).setDireccion(personaje.getDireccion());
					personajes.get(personaje.getIdPersonaje()).setDireccion(personaje.getDireccion());
					personajes.get(personaje.getIdPersonaje()).setAncho(personaje.getAncho());
					personajes.get(personaje.getIdPersonaje()).setAlto(personaje.getAlto());
					semaforo.release();
					break;
				
				case "colision":
					personaje = (MensajePersonaje) gson.fromJson(objetoLeido, Mensaje.class);
					combatiendo = (Map<Integer, MensajePersonaje>) gson.fromJson(objetoLeido, MensajeDePersonajes.class).getCombatiendo();
					semaforo.release();
					break;
					
				}
			}
		} catch (IOException e) {
			Loggin.getInstance().error("Error Escuchar mensaje: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Loggin.getInstance().error("Error ClassNotFoundException: " + e.getMessage());
		} catch (Exception e) {
			Loggin.getInstance().error("Error escucharMensaje: " + e.getMessage());
		}
	}

	public Map<Integer, MensajePersonaje> getPersonajes() {
		return personajes;
	}

	public Map<Integer, MensajePersonaje> getCombatiendo() {
		return combatiendo;
	}

	public void setCombatiendo(Map<Integer, MensajePersonaje> combatiendo) {
		this.combatiendo = combatiendo;
	}

	public void setPersonajes(Map<Integer, MensajePersonaje> personajes) {
		this.personajes = personajes;
	}
}