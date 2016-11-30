package dominio;

public abstract class Item {
	
	protected int prioridad;
	protected String nombre;
	protected int ataque;
	protected int defensa;
	protected int magia;
	protected int velocidad;
	protected int potencia;
	protected int destreza;
	
	public Item(){
		this.prioridad = 0;
		this.nombre = "";
		this.ataque = 0;
		this.defensa = 0;
		this.magia = 0;
		this.velocidad = 0;
		this.potencia = 0;
		this.destreza = 0;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getMagia() {
		return magia;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}	
	
}
