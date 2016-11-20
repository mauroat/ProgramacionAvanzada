package dominio;

import razas.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dominio.*;
import items.*;

public abstract class Personaje implements Peleador {

	protected int 
				idPersonaje,
				vida,
				experiencia,
				nivel,
				energia, 
				puntos,	// @mauroat - agregado 19/10/16 - modificado 24/10/16
				ataque, 
				defensa,
				magia,				
				destreza,
				velocidad,
				potencia,
				puedeAgregarAtaque; // @mauroat - 24/10/16 : Esto se crea para satisfacer la historia de usuario 13-

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	protected List<Item> mochila;
	protected Ubicacion ubicacion;
	protected String nombre;
	protected String raza;
	protected Casta clase;
	protected Map<String, Ataque> ataques = new HashMap<String, Ataque>(); 
	protected Alianza alianzaActual;
	protected Calendar limiteMinimoPermanenciaAlianza;

	public Personaje(String nombre){
		this.nombre = nombre;
		this.vida = 100;
		this.energia = 100;
		this.experiencia = 0;
		this.nivel = 1;	
		this.puntos = 0;
		this.puedeAgregarAtaque = 0;
		this.destreza = 0;
		this.potencia = 0;
		this.velocidad = 0;
		this.clase = null;
		this.alianzaActual = null;
		this.mochila = new LinkedList<Item>();
	}

	/*
	 * Copiar personaje
	 * */
	public Personaje(Personaje p){
		this.nombre = p.nombre;
		this.vida = p.vida;
		this.energia = p.energia;
		this.ataque = p.ataque;
		this.defensa = p.defensa;
		this.magia = p.magia;
		this.experiencia = p.experiencia;
		this.nivel = p.nivel;	
		this.puntos = p.puntos;
		this.puedeAgregarAtaque = p.puedeAgregarAtaque;
		this.destreza = p.destreza;
		this.velocidad = p.velocidad;
		this.potencia = p.potencia;
		this.clase = p.clase;
		this.ataques = p.ataques;	
		this.alianzaActual = p.alianzaActual;
		
		this.mochila = p.mochila;
	}
	
	
	/* 
	 * @mauroat - 18/10/16
	 * Se modifica este metodo para que se sume mas experiencia en caso que mate al atacado. 
	 * Mi idea es que cuando esten implementadas las razas tambien afecten la experiencia.
	 * 
	 * */

	
	/**
	 * METODOS DE LA INTERFAZ PELEADOR
	 * */
	
	public final void atacar (Peleador atacado)  {
		if(atacado.estaVivo()){
			if (this.puedeAtacar()) {
				atacado.serAtacado(calcularPuntosDeAtaque());
				// El siguiente metodo podria implementarse cuando definamos la ubicacion de los personajes
				//atacado.despuesDeSerAtacado();
				this.energia -= calcularPuntosDeAtaque();

				if(atacado.estaVivo()){
					// Por cada ataque que haga, mi experiencia sube en 8
					this.experiencia += 8;

				} else {
					// Si en cambio, mato al atacado, mi experiencia sube en 10*el nivel del atacado
					this.experiencia+=(atacado.getNivel()*10);
				}

				this.despuesDeAtacar();	
			} 
		}
	}

	/* 
	 * @mauroat - 18/10/16
	 * Se sobrecarga el metodo atacar, para que se apliquen los daños causados por los ataques que posee cada personaje.
	 * 
	 * */
	public final void atacar(Personaje atacado, Ataque a)  {
		if(atacado.estaVivo()){
			if (this.puedeAtacar()) {

				// Si es curacion, entonces afecta positivamente directamente al atacado
				// Su energia disminuye acorde unicamente a sus puntos de ataque, ya que la curacion tiene valores negativos
				if(a.getIdAtaque() == 7){
					atacado.serAtacado(a.aplicarAtaque());
					this.energia -= calcularPuntosDeAtaque();	
				} 
				// Si es regeneracion, aumentara la energia del atacante +30
				else if (a.getIdAtaque() == 6){
					this.energia += a.aplicarAtaque();
				}
				// Si el ataque no es curacion, entonces impactara sumando el 
				//daño del ataque + los puntos de ataque
				else {
					atacado.serAtacado(calcularPuntosDeAtaque()+a.aplicarAtaque());
					// El siguiente metodo podra implementarse cuando definamos la ubicacion de los personajes
					//atacado.despuesDeSerAtacado();
					this.energia -= calcularPuntosDeAtaque()+a.aplicarAtaque();
				}
				
				if(atacado.estaVivo()){
					// Por cada ataque que haga, mi experiencia sube en 8
					this.experiencia += 8;					
				} else {
					// Si en cambio, mato al atacado, mi experiencia sube en 10*el nivel del atacado
					this.experiencia+=(atacado.getNivel()*10);
				}

				this.despuesDeAtacar();	
			}
		}
	}

	public void despuesDeSerAtacado(){
		if(this.vida <=0)
			this.morir();
	}	

	public void despuesDeAtacar()  { 
		this.verificarNivel();

	}	

	/*
	 * Ataque
	 * */
	
	public int calcularPuntosDeAtaque(){
		return ataque + obtenerPuntosDeAtaque();	
	}
	
	private int obtenerPuntosDeAtaque(){
		if(this.mochila != null){
			int sumatoria = 0;
			for(Item i : mochila){
				sumatoria += i.getAtaque();
			}
			return sumatoria;
		} else {
			return 0;
		}
	}
	
	/*
	 * Defensa
	 * */
	
	public int calcularPuntosDeDefensa(){
		return defensa + obtenerPuntosDeDefensa();	
	}
	
	
	private int obtenerPuntosDeDefensa(){
		if(this.mochila != null){
			int sumatoria = 0;
			for(Item i : mochila){
				sumatoria += i.getDefensa();
			}
			return sumatoria;
		} else {
			return 0;
		}
	}
	
	/*
	 * Magia 
	 * */

	public int calcularPuntosDeMagia(){
		return magia + obtenerPuntosDeMagia();
	}
	
	private int obtenerPuntosDeMagia(){
		if(this.mochila != null){
			int sumatoria = 0;
			for(Item i : mochila){
				sumatoria += i.getMagia();
			}
			return sumatoria;
		} else {
			return 0;
		}
	}
	/*
	 * Destreza
	 * */
	
	public int calcularPuntosDeDestreza(){
		return destreza + obtenerPuntosDeDestreza();
	}
	
	private int obtenerPuntosDeDestreza(){
		if(this.mochila != null){
			int sumatoria = 0;
			for(Item i : mochila){
				sumatoria += i.getDestreza();
			}
			return sumatoria;
		} else {
			return 0;
		}
	}
	
	/*
	 * Velocidad
	 * */
	
	public int calcularPuntosDeVelocidad(){
		return velocidad + obtenerPuntosDeVelocidad();
	}
	
	private int obtenerPuntosDeVelocidad(){
		if(this.mochila != null){
			int sumatoria = 0;
			for(Item i : mochila){
				sumatoria += i.getVelocidad();
			}
			return sumatoria;
		} else {
			return 0;
		}
	}
	
	/*
	 * Potencia
	 * */
	
	public int calcularPuntosDePotencia(){
		return potencia + obtenerPuntosDePotencia();
	}
	
	private int obtenerPuntosDePotencia(){
		if(this.mochila != null){
			int sumatoria = 0;
			for(Item i : mochila){
				sumatoria += i.getPotencia();
			}
			return sumatoria;
		} else {
			return 0;
		}
	}
	
	@Override
	public int getNivel() {
		return nivel;
	}
	
	@Override
	public boolean estaVivo() {
		return this.vida > 0;
	}
	
	@Override
	public void agregarItem(Item i){
		if(i != null)
			this.mochila.add(i);
	}
	
	/**
	 * FIN METODOS DE LA INTERFAZ PELEADOR
	 */
	

	/*
	 * Devuelve el item y desequipa a la vez
	 * */
	@Override
	public Item dejarItem(){
		if(this.mochila != null){
			int flagPrioridad = -1, flagIDItem=-1;
			Item itemDeMaxPrioridad = null;
			for(int i = 0; i < this.mochila.size(); i++){
				if(this.mochila.get(i).getPrioridad() > flagPrioridad){
					flagPrioridad = this.mochila.get(i).getPrioridad();
					itemDeMaxPrioridad = this.mochila.get(i);
					flagIDItem = i;
				}
			}
			if(flagPrioridad > 0){
				this.mochila.remove(flagIDItem);
				return itemDeMaxPrioridad;
			}
			
		} 
		return null;		
	}
	
	/* @mauroat - 19/10/16
	 * Al subir de nivel se suman dos puntos para sumar a las habilidades
	 * */

	private void verificarNivel()  {
		if(this.experiencia >= experienciaRequerida(this.nivel)){
			this.nivel++;
			this.puntos += 2;

			/*
			 * @mauroat - 24/10/16
			 * Si el personaje alcanza un nivel multiplo de 5, podra agregar un ataque. 
			 * */

			if(this.nivel % 5 == 0){
				this.puedeAgregarAtaque += 1;
			}
		}		
	}

	/* @mauroat - 17/10/16
	 * Esto sera reemplazado por una consulta a una base de datos
	 * */ 
	private int experienciaRequerida(int nivel) {
		try{

			Properties niveles = new Properties();
			niveles.load(new FileInputStream("config/niveles.properties"));
			return Integer.parseInt(niveles.getProperty(String.valueOf(nivel)));
		} catch(Exception e){
			e.printStackTrace();
		} 
		return 0;
	}

	public abstract boolean puedeAtacar();
	
	public abstract String getRaza();

	public void morir(){
		this.vida=0;
		//Tengo que dejar el mejor item	
		this.reaparecer();
		this.revivir();
	}

	private void revivir() {
		this.serCurado();
		this.serEnergizado();		
	}

	private void reaparecer() {
		// Este metodo tiene que reubicarme en una zona segura		
	}


	/* @mauroat - 18/10/16
	 * Modifico este metodo para que los puntos de defensa amortiguen el daño recibido en los ataques
	 * */
	@Override
	public void serAtacado(int dano){
		if(dano > 0)
			this.vida -= dano-this.calcularPuntosDeDefensa();
		else
			this.vida -= dano;
		if(this.vida > 100)
			this.vida = 100;
		
	}

	public void serCurado() {
		this.vida = 100;
	}

	public void serEnergizado() {
		this.energia = 100;
	}

	public int getVida() {
		return vida;
	}

	public int getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getDestreza() {
		return destreza;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Casta getClase() {
		return clase;
	}

	public String getNombreClase() {
		return clase.getNombre();
	}

	
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}


	public int getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	/* LISTA DE ITEMS */

	public String getLista() {
		return this.raza +" equipado con:";
	}

	public int getTamañoLista() {
		return 0;
	}

	/* HASMAP DE ATAQUES  */

	public void agregarAtaque(Ataque a){
		this.ataques.put(a.getNombre(), a);
		this.puedeAgregarAtaque--;
	}

	public void quitarAtaque(Ataque a){
		this.ataques.remove(a.getNombre());		
		// Si lo quiere sacar no puede elegir otro, que se joda.
		//this.puedeAgregarAtaque++;
	}

	public LinkedList<String> getListaAtaques(){
		int i = 1;
		LinkedList<String> ataques = new LinkedList<String>();
		for (Map.Entry<String, Ataque> entry : this.ataques.entrySet()) {
			ataques.add("Ataque "+i+": "+entry.getKey());
			i++;
		}
		return ataques;
	}

	public Map<String, Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(Map<String, Ataque> ataques) {
		this.ataques = ataques;
	}

	public int getCantidadAtaques(){
		return this.ataques.size();
	}

	public Ataque getAtaque(String ataque){
		return this.ataques.get(ataque);		
	}
	
	/*
	 * METODOS DE ITEMS
	 * 
	 * */

	

	public int getPrioridad() {
		return 0;
	}

	public boolean compararPrioridad(int p1, int p2){
		return p1 == p2;
	}

	public Personaje getPersonajeDecorado() {
		return null;
	}

	public void setPersonajeDecorado(Personaje p) {
		// No hace nada
	}


	public boolean interactuarConOtroPersonaje(Personaje p)  {
		if(ubicacion.contiene(p.getUbicacion()))
			return p.respuesta();
		return false; 	 
	}

	public boolean respuesta() {
		return true;
	}
/*
	public boolean equals(Personaje obj) {
		if(this.usuarioPersonaje.equals(obj.usuarioPersonaje))
			return true;
		return false;	
	}
*/
	public int getPuedeAgregarAtaque() {
		return puedeAgregarAtaque;
	}

	public void setPuedeAgregarAtaque(int puedeAgregarAtaque) {
		this.puedeAgregarAtaque = puedeAgregarAtaque;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion u) {
		this.ubicacion = u;
	}

	public int getMagia() {
		return magia;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}

	public boolean seEncuentraCerca(Personaje obj)	 {
		return this.ubicacion.distanciaA(obj.ubicacion) <= (this.ubicacion.getRadio() + obj.ubicacion.getRadio());
	}

	public void reubicar()	 {
		this.setUbicacion(obtenerLugarSeguroRandom());
	}

	private Ubicacion obtenerLugarSeguroRandom()	 {
		Random r = new Random();
		Map<Integer, Ubicacion> ListaUbicacion = new HashMap<Integer, Ubicacion>(); 
		/*Validar que no haya obstaculos ni personajes en las ubicaciones*/
		ListaUbicacion.put(0, new Ubicacion(0,0));
		ListaUbicacion.put(1, new Ubicacion(15,0));
		ListaUbicacion.put(2, new Ubicacion(90,90));
		ListaUbicacion.put(3, new Ubicacion(90,150));

		return ListaUbicacion.get(r.nextInt(4));
	}

	public boolean formarAlianzaCon(Personaje p) {
		/*
		 * Si this no tiene alianza, la creo y me agrego
		 */
		if (this.alianzaActual == null) {
			Alianza a = new Alianza();
			this.alianzaActual = a;
			a.agregarAAlianza(this);
		}
		
		/*
		 * Si p no tiene alianza, lo agrego a mi alianza
		 * */
		
		if (p.alianzaActual == null) {
			
			p.alianzaActual = this.alianzaActual;
			alianzaActual.agregarAAlianza(p);
			
			return true;
		} else
			// EL PERSONAJE YA ESTA EN UNA ALIANZA, NO SE PUEDE VOLVER A ALIAR
		return false;
	}
	
	public void dejarAlianzaActual(){
		this.alianzaActual.getIntegrantes().remove(this);
		this.alianzaActual = null;
	}
	
	
	
	public void setAlianzaActual(Alianza alianzaActual) {
		this.alianzaActual = alianzaActual;
	}

	public Alianza getAlianzaActual() {
		return alianzaActual;
	}

	public Calendar getLimiteMinimoPermanenciaAlianza() {
		return limiteMinimoPermanenciaAlianza;
	}

	public void setLimiteMinimoPermanenciaAlianza(Calendar limiteMinimoPermanenciaAlianza) {
		this.limiteMinimoPermanenciaAlianza = limiteMinimoPermanenciaAlianza;
	}

	public List<Item> getMochila() {
		return mochila;
	}

	public void setMochila(List<Item> mochila) {
		this.mochila = mochila;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setClase(Casta clase) {
		this.clase = clase;
	}


	





}
