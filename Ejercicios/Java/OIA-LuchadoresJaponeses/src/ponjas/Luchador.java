package ponjas;

public class Luchador {

	private int peso;
	private int altura;
	
	/* CONSTRUCTORES */
	public Luchador (){
		this.peso = 0;
		this.altura = 0;				
	}
	
	public Luchador (int peso, int altura){
		this.peso = peso;
		this.altura = altura;
	}
	/* FUNCIONES VARIAS */
	public String toString (){
		return "Peso: " + this.peso + "Altura: " +this.altura;
	}
	
	public boolean domina (Luchador l){
		if ((this.peso >= l.peso && this.altura > l.altura) || (this.peso > l.peso && this.altura >= l.altura))
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
