package habilidades;

import dominio.*;

public class Velocidad extends Habilidad{

	public Velocidad(){
		this.idHabilidad = 6;
		this.nombre = "Velocidad";
		this.puntos = 0;
	}
	
	public void afectar(Personaje p){
		if(p.getPuntos() > 0){
			p.setVelocidad(p.getVelocidad()+1);
			p.setAtaque(p.getAtaque()+1);
			p.setPuntos(p.getPuntos()-1);
			this.puntos++;
		}
	}
}
