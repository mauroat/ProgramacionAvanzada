package habilidades;

import dominio.*;

public class Fuerza extends Habilidad{

	public Fuerza(){
		this.idHabilidad = 3;
		this.nombre = "Fuerza";
		this.puntos = 0;
	}
	
	public void afectar(Personaje p){
		if(p.getPuntos() > 0){
			p.setAtaque(p.getAtaque()+1);
			p.setPotencia(p.getPotencia()+1);
			p.setPuntos(p.getPuntos()-1);
			this.puntos++;
		}
	}
}
