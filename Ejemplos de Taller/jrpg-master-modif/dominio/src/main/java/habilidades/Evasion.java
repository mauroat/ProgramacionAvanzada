package habilidades;

import dominio.Habilidad;
import dominio.Personaje;

public class Evasion extends Habilidad{

	public Evasion(){
		this.idHabilidad = 2;
		this.nombre = "Evasión";
		this.puntos = 0;
	}
	
	public void afectar(Personaje p){
		if(p.getPuntos() > 0){
			p.setDefensa(p.getDefensa()+2);
			p.setPuntos(p.getPuntos()-1);
			this.puntos++;
		}
	}
}
