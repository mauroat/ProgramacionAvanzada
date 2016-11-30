package dominio;

import java.io.FileNotFoundException;
import java.util.Random;

public class Combate {
	
	private static int contadorCombates = 0; 
	private int idCombate;
	private int ultimoAtacanteE1, ultimoAtacanteE2; 
	private String nombre;
	private int ganador;
	

	
	public Combate(String nombre)
	{
		this.nombre = nombre;
		this.idCombate = getProximoCombate();
	}
	
	public Combate()
	{
		//this.nombre = nombre;
		this.idCombate = getProximoCombate();
	}
	
	
	public void combatir(Equipo e1, Equipo e2) throws FileNotFoundException, CloneNotSupportedException{
		Random r = new Random();
		int aux = r.nextInt(2);
		
		/*
		 * Defino quien ataca primero
		 * */
		while(e1.quedaAlgunoVivo() && e2.quedaAlgunoVivo() ){
			if(aux == 1){
				e1.atacar(e2);
				e2.atacar(e1);
			} else {
				e2.atacar(e1);
				e1.atacar(e2);
			}		
		}

		if(e1.quedaAlgunoVivo()){
			this.ganador = 1;
			e1.repartirExperiencia(e2.calcularExperiencia());
			e1.repartirItem(e2);
			//e2.desequiparEquipo();
		}	else if(e2.quedaAlgunoVivo()){
			this.ganador = 2;
			e2.repartirExperiencia(e1.calcularExperiencia());
			e2.repartirItem(e1);
			//e1.desequiparEquipo();
		}
	}

	public void combatir(Equipo e, Generico g) throws FileNotFoundException, CloneNotSupportedException {
		while(e.quedaAlgunoVivo() && g.estaVivo() ){
			//Ataca primero siempre el generico
				e.atacar(g);
				g.atacar(e.obtenerProximaVictima());		
		}

		if(e.quedaAlgunoVivo()){
			e.repartirExperiencia(g.getNivel()*10);
			e.repartirItem(g);
		}	else if(g.estaVivo()){
			/* Cada miembro del equipo deja su mejor item*/
			e.desequiparEquipo();
		}
	}
	
	
	
	
	private int getProximoCombate(){
		return contadorCombates++;
	}

	public void declararGanador(Equipo e1, Equipo e2) {
		if(e1.quedaAlgunoVivo())
			e1.mostrarGanador();
		else if(e2.quedaAlgunoVivo())
			e2.mostrarGanador();
	}
}
