package dominio;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Equipo {

	protected List<Personaje> listaPeleadores = new LinkedList<Personaje>();
	protected List<Personaje> listaPeleadoresMuertos = new LinkedList<Personaje>();
	protected int proximaVictima;
	
	/*
	 * Esto tendra que resolverlo el server
	 * */
	
	public Equipo (Personaje p){		
		this.proximaVictima = -1;
		agregar(p);
		if(p.getAlianzaActual() != null)
			for (Personaje aliado : p.getAlianzaActual().getIntegrantes()) 
				if(aliado.seEncuentraCerca(p) && aliado != p)		
					agregar(aliado);

	}
		
	public final void depurarEquipo() {
		for(Personaje integrante : listaPeleadores){
			if(!integrante.estaVivo()){
				listaPeleadoresMuertos.add(integrante);
				listaPeleadores.remove(integrante);
			}
				
		}
		
	}
	

	
	public void atacar(Equipo otro) throws FileNotFoundException {
		Peleador victima = otro.obtenerProximaVictima();
		for (Personaje atacante : listaPeleadores) {
			if(atacante.puedeAtacar()){
				// ESTO DEBERA CAMBIARSE PARA QUE EL ATACANTE ELIJA SI ATACAR NORMAL
				// O CON UN ATAQUE
				atacante.atacar(victima);
			} else {
				atacante.setEnergia(atacante.getEnergia()+20);
			}
			
		}
	}

	public void atacar(Generico g) throws FileNotFoundException {
		Peleador victima = g;
		for (Personaje atacante : listaPeleadores) {
			if(atacante.puedeAtacar()){
				// ESTO DEBERA CAMBIARSE PARA QUE EL ATACANTE ELIJA SI ATACAR NORMAL
				// O CON UN ATAQUE
				atacante.atacar(victima);
			} else {
				atacante.setEnergia(atacante.getEnergia()+20);
			}
			
		}
	}
	
	public boolean agregar(Personaje personaje) {
		return listaPeleadores.add(personaje);
	}

	
	public Peleador obtenerProximaVictima() {
		depurarEquipo();
		this.proximaVictima++;
		
		/*
		 * Si el contador se me va a la mierda lo reseteo
		 * */
		
		if(this.proximaVictima >= this.listaPeleadores.size())
			this.proximaVictima = 0;
		
		if(listaPeleadores.isEmpty()) {		
			//throw new RuntimeException("El batallón está vacío");
		}
		return listaPeleadores.get(this.proximaVictima);
	}
	
	public boolean quedaAlgunoVivo(){
		boolean marca = false;
		for(int i = 0; i< this.listaPeleadores.size();i++)
			if(listaPeleadores.get(i).estaVivo())
				marca = true;	
		return marca;
		
	}
	

	public int calcularExperiencia(){		
		int suma = listaPeleadores.get(0).getNivel();		
		for (int i = 1; i < listaPeleadores.size();i++){		
			suma += listaPeleadores.get(0).getAlianzaActual().getIntegrantes().get(i).getNivel();
		}
		return suma*10;
	}
	

	public void repartirExperiencia(int experiencia){
		int puntosPorPersonaje = experiencia / listaPeleadores.size();
		
		for (int i = 0; i<listaPeleadores.size();i++){
			if(listaPeleadores.get(i).estaVivo())
				listaPeleadores.get(i).setExperiencia(listaPeleadores.get(i).getExperiencia()+puntosPorPersonaje);
		}
	}
	
	/*
	 * @mauroat - 07/11/16
	 * Se fija en la 
	 * */
	public void repartirItem(Equipo equipoPerdedor) throws CloneNotSupportedException {		
		Random r = new Random();
		
		/*
		 * Luego de que muera el ultimo, tengo que mandarlo a la lista de peleadores muertos y eliminarlo de 
		 * la lista de peleadores
		 * */
		equipoPerdedor.listaPeleadoresMuertos.add(equipoPerdedor.listaPeleadores.get(0));
		equipoPerdedor.listaPeleadores.remove(0);
		
		/*
		 * Aleatoriamente entre los que queden vivos del equipo ganador, ire asignando items de los personajes abatidos.
		 * A su vez, dichos items se iran desequipando de estos ultimos.
		 * */

		for(Personaje abatido : equipoPerdedor.listaPeleadoresMuertos){
			int posicionDePersonajeGanador = r.nextInt(this.listaPeleadores.size());			
			this.listaPeleadores.get(posicionDePersonajeGanador).agregarItem(abatido.dejarItem());
		}
		
	}

	/*
	 * @mauroat - 07/11/16
	 * Lo que hace este metodo es asignar el item del generico a un personaje random del equipo ganador, 
	 * siempre y cuando esté vivo.
	 * 
	 * */
	public void repartirItem(Generico g) throws CloneNotSupportedException {
		Random r = new Random();
		
		/*
		 * Como los personajes muertos se van depurando a otra lista, no hace falta q pregunte si esta vivo
		 * */
		
		int posicion = r.nextInt(listaPeleadores.size());
		this.listaPeleadores.get(posicion).agregarItem(g.dejarItem());
			
	}
	
	public void desequiparEquipo(){
		for(Personaje abatido : this.listaPeleadoresMuertos)
			abatido.dejarItem();
	}
	
	
	public List<String> mostrarGanador() {
        LinkedList ganadores = new LinkedList<String>();
        for (int i = 0; i < listaPeleadores.size();i++){
            ganadores.add(listaPeleadores.get(i).getNombre());
        }
        return ganadores;
    }

	public List<Personaje> getListaPeleadores() {
		return listaPeleadores;
	}

	public void setListaPeleadores(List<Personaje> listaPeleadores) {
		this.listaPeleadores = listaPeleadores;
	}
	
	
	
	
}