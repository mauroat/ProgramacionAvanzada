package cliente;

import java.io.Serializable;

public class MensajeCombate extends Mensaje implements Serializable, Cloneable {

	private static final long serialVersionUID = 2508647840686493806L;
	private int id;
	private int idEnemigo;
	
	public MensajeCombate(){
		setComando("combate");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEnemigo() {
		return idEnemigo;
	}
	
	public void setIdEnemigo(int idEnemigo){
		this.idEnemigo = idEnemigo;
	}
}
