package cliente;

import java.io.Serializable;

public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -4202478779889131246L;
	private String mensaje;
	private String nick;
	private String ip;
	private String sala;
	private String comando;

	public Mensaje() {		
	}
	
	public Mensaje(String mensaje, String nick, String ip, String sala, String comando) {
		this.mensaje = mensaje;
		this.nick = nick;
		this.ip = ip;
		this.sala = sala;
		this.comando = comando;
	}
	
	public Mensaje(String mensaje, String comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	public Mensaje(String comando) {
		this.comando = comando;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getNick() {
		return nick;
	}

	public String getIp() {
		return ip;
	}

	public String getSala() {
		return sala;
	}

	public String getComando() {
		return comando;
	}
}
