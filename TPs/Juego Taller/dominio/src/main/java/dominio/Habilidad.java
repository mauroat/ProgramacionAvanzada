package dominio;

/*
 * @mauroat - 24/10/16
 * Cada vez que se asigne un punto a una habilidad existente, se utilizará el método afectar(), lo que incrementará
 * sus puntos base
 * 
 * */

public abstract class Habilidad {

	protected int idHabilidad;
	protected String nombre;
	protected int puntos;
	//protected int ataque, defensa, magia, destreza, potencia, velocidad;
	
	
	public abstract void afectar(Personaje p);
	

	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	public int getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(int idHabilidad) {
		this.idHabilidad = idHabilidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
