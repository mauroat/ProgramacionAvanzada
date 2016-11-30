package habilidades;

import dominio.Habilidad;
import dominio.Personaje;

public class Inteligencia extends Habilidad{
	
	public Inteligencia(){
		this.idHabilidad = 4;
		this.nombre = "Inteligencia";
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
