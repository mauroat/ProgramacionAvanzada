package ponjas;
import java.io.File;



public class Torneo {
	private int resultado[] ; 
	private Luchador listaLuchadores[];
	
	public Torneo (int cant){
		this.resultado = new int [cant];
		this.listaLuchadores = new Luchador [cant];
	}
	
	public Torneo (){
		this.resultado = new int [0];
		this.listaLuchadores = new Luchador [0];
	}
	
	public void resolver(){
		for(int i = 0; i < this.listaLuchadores.length; i++){
			this.resultado[i] = 0;
			for(int j=0; j < this.listaLuchadores.length; j++){
				if(this.listaLuchadores[i].domina(this.listaLuchadores[j]))
					this.resultado[i]++;
			}
			System.out.println("El luchador "+i+" domina "+resultado[i]+" luchador/es");
		}
	}
	
	
	public static void main(String[] args) {
		Sting path = "";

	}

}
