package rescatando;

public class Nodo {

	private int posicion;
	private String ocupadoPor;
	
	public Nodo(){
		this.posicion = 0;
		this.ocupadoPor = null;
	}
	
	public Nodo(int posicion){
		this.posicion = posicion;
		this.ocupadoPor = null;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

//	public int getGrado() {
//		return grado;
//	}

//	public void setGrado(int grado) {
//		this.grado = grado;
//	}

	public String getOcupadoPor() {
		return ocupadoPor;
	}

	public void setOcupadoPor(String ocupadoPor) {
		this.ocupadoPor = ocupadoPor;
	}
	
	
}
