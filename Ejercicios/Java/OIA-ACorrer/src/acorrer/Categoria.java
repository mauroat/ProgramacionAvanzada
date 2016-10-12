package acorrer;

public class Categoria {
	//private int id;
	private char sexo;
	private int minimo;
	private int maximo;
	private Podio podio;
	
	public Categoria(char sexo, int minimo, int maximo) {		
		//this.id = id;
		this.sexo = sexo;
		this.minimo = minimo;
		this.maximo = maximo;
		this.podio = new Podio(0,0,0);
	}
	
	public boolean participa(int edad){
		if(edad >= this.minimo && edad <=this.maximo)
			return true;		
		return false;
	}

	public void asignarPodio(int corredor){
		if (podio.getOro() == 0) {
			podio.setOro(corredor);
		}else if (podio.getPlata() == 0) {
			podio.setPlata(corredor);
		}else if (podio.getBronce() == 0) {
			podio.setBronce(corredor);
		}
		
	}
	
	

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public Podio getPodio() {
		return podio;
	}

	public void setPodio(Podio podio) {
		this.podio = podio;
	}
	
	
	
}
