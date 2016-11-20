package habilidades;

import dominio.*;

public class Destreza extends Habilidad{
	
	public Destreza(){
		this.idHabilidad = 1;
		this.nombre = "Destreza";
		this.puntos = 0;
	}
	
	public void afectar(Personaje p){
		if(p.getPuntos() > 0){
			p.setVelocidad(p.getVelocidad()+1);
			p.setDestreza(p.getDestreza()+1);
			p.setPuntos(p.getPuntos()-1);
			this.puntos++;
		}		
	}
	
	
	
}
