package dominio;

import java.util.HashMap;
import java.util.Map;

import org.omg.Messaging.SyncScopeHelper;


public abstract class Casta {

	protected Map<Integer, Habilidad> habilidades = new HashMap<Integer, Habilidad>(); 
	protected String nombre;
	protected int idCasta;
	
	public void agregarHabilidad(Habilidad h) {
		if(this.habilidades.size()<=3){
			//No puede tener más de 3 habilidades;
			this.habilidades.put(h.getIdHabilidad(), h);
		}			
	}


	public Map<Integer, Habilidad> getHabilidades(){
		return this.habilidades;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getIdCasta() {
		return idCasta;
	}


	public void setIdCasta(int idCasta) {
		this.idCasta = idCasta;
	}


	public void setHabilidades(Map<Integer, Habilidad> habilidades) {
		this.habilidades = habilidades;
	}
	
}
