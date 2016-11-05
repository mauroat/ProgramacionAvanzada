package pelicula;

public class Segmento {

	private int numero, escenaInicial, escenaFinal;
	
	public Segmento(int numero, int ini, int fin){
		this.numero = numero;
		this.escenaInicial = ini;
		this.escenaFinal = fin;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getEscenaInicial() {
		return escenaInicial;
	}

	public void setEscenaInicial(int escenaInicial) {
		this.escenaInicial = escenaInicial;
	}

	public int getEscenaFinal() {
		return escenaFinal;
	}

	public void setEscenaFinal(int escenaFinal) {
		this.escenaFinal = escenaFinal;
	}
	
	
}
