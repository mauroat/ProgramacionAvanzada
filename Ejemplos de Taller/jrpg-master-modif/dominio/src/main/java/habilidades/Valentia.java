package habilidades;

import dominio.*;

public class Valentia extends Habilidad{

	public Valentia(){
		this.idHabilidad = 5;
		this.nombre = "Valentia";
		this.puntos = 0;
	}
	
	public void afectar(Personaje p){
		if(p.getPuntos() > 0){
			p.setAtaque(p.getAtaque()+2);
			p.setPuntos(p.getPuntos()-1);
			this.puntos++;
		}
	}
}
