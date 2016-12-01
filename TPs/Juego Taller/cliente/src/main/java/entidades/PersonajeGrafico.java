package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;

import juego.Grafico;
import juego.Juego;
import mapa.Grafo;
import mapa.Mapa;
import mapa.Nodo;
import utilities.Loggin;

public class PersonajeGrafico {

	Juego juego;

	// Tama�o de la entidad
	private int ancho;
	private int alto;

	// Posiciones
	private float x;
	private float y;
	private float dx;
	private float dy;
	private float xInicio;
	private float yInicio;
	private float xFinal;
	private float yFinal;
	private int xOffset;
	private int yOffset;
	private int drawX;
	private int drawY;
	private int posMouse[];
	private int[] tile;

	// Calculo de movimiento
	private float difX;
	private float difY;
	private float relacion;

	// Posicion final
	private float auxX;
	private float auxY;

	// Movimiento Actual
	private static final int verticalSup = 0;
	private static final int diagonalSupDer = 1;
	private static final int horizontalDer = 2;
	private static final int diagonalInfDer = 3;
	private static final int verticalInf = 4;
	private static final int diagonalInfIzq = 5;
	private static final int horizontalIzq = 6;
	private static final int diagonalSupIzq = 7;
	private int movimientoHacia = 6;
	private boolean enMovimiento;

	// Animaciones
	private LinkedList<BufferedImage[]> animaciones;
	private final Animacion moverIzq;
	private final Animacion moverArribaIzq;
	private final Animacion moverArriba;
	private final Animacion moverArribaDer;
	private final Animacion moverDer;
	private final Animacion moverAbajoDer;
	private final Animacion moverAbajo;
	private final Animacion moverAbajoIzq;

	private final Gson gson = new Gson();
	private int intervaloEnvio = 0;
	
	// pila de movimiento
	private PilaDeTiles pilaMovimiento;
	private int [] tileActual;
	private int [] tileFinal;
	private int [] tileMoverme;
	
	private Mapa mapa;

	public PersonajeGrafico(Juego juego, Mapa mundo, int ancho, int alto, float spawnX, float spawnY, int velAnimacion) {
		this.juego = juego;
		this.ancho = ancho;
		this.alto = alto;
		this.mapa = mundo;
		xOffset = ancho / 2;
		yOffset = alto / 2;
		x = (int)(spawnX / 64) * 64;
		y = (int)(spawnY / 32) * 32;

		this.animaciones = obtenerAnimacion(juego.getRaza());
		 
		moverArriba = new Animacion(velAnimacion, animaciones.get(0)); 
	    moverArribaDer = new Animacion(velAnimacion, animaciones.get(1)); 
	    moverDer = new Animacion(velAnimacion, animaciones.get(2)); 
	    moverAbajoDer = new Animacion(velAnimacion, animaciones.get(3)); 
	    moverAbajo = new Animacion(velAnimacion, animaciones.get(4)); 
	    moverAbajoIzq = new Animacion(velAnimacion, animaciones.get(5)); 
	    moverIzq = new Animacion(velAnimacion, animaciones.get(6)); 
	    moverArribaIzq = new Animacion(velAnimacion, animaciones.get(7)); 
	  } 


	public LinkedList<BufferedImage[]> obtenerAnimacion(String raza) {
		if(raza.equals("ORCO"))
			return Grafico.orco;
		else if(raza.equals("HUMANO"))
			return Grafico.humano;
		else
			return Grafico.elfo;
	}

	public void actualizar() {
		if(enMovimiento){
			moverIzq.actualizar();
			moverArribaIzq.actualizar();
			moverArriba.actualizar();
			moverArribaDer.actualizar();
			moverDer.actualizar();
			moverAbajoDer.actualizar();
			moverAbajo.actualizar();
			moverAbajoIzq.actualizar();
		}else{
			moverIzq.reset();
			moverArribaIzq.reset();
			moverArriba.reset();
			moverArribaDer.reset();
			moverDer.reset();
			moverAbajoDer.reset();
			moverAbajo.reset();
			moverAbajoIzq.reset();
		}

		getEntrada();
		mover();
		juego.getCamara().Centrar(this);
	}

	public void getEntrada() {

		posMouse = juego.getHandlerMouse().obtenerPosicion();
		
		if(juego.getHandlerMouse().obtenerRecorrido()){
			
			tileMoverme = Mapa.mouseATile(posMouse[0]+ juego.getCamara().getxOffset() - xOffset, posMouse[1]+ juego.getCamara().getyOffset() - yOffset);
			
			juego.getHandlerMouse().setearRecorrido(false);
			
			pilaMovimiento = null;
			
		}

		if (!enMovimiento && tileMoverme != null) {
			
			enMovimiento = false;

			xInicio = x;
			yInicio = y;	
			
			tileActual = Mapa.mouseATile(x, y);
			
			if(tileMoverme[0] < 0 || tileMoverme[1] < 0 || tileMoverme[0] >= mapa.obtenerAncho() || tileMoverme[1] >= mapa.obtenerAlto()){
				enMovimiento = false;
				juego.getHandlerMouse().setearRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}
			
			
			if(tileMoverme[0] == tileActual[0] && tileMoverme[1] == tileActual[1]
					|| mapa.getTile(tileMoverme[0], tileMoverme[1]).esObstaculo()){ 		         
				
				tileMoverme = null;
				enMovimiento = false;
				juego.getHandlerMouse().setearRecorrido(false);
				pilaMovimiento = null;
				return;
			}	
			
			if(pilaMovimiento == null)
				pilaMovimiento = obtenerCaminoMasCorto(tileActual[0], tileActual[1], tileMoverme[0], tileMoverme[1]);
			
			// Me muevo al primero de la pila
			NodoDePila nodoActualTile = pilaMovimiento.pop();
			
			if(nodoActualTile == null){
				
				enMovimiento = false;
				juego.getHandlerMouse().setearRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}
			
			tileFinal = new int[2];
			tileFinal[0] = nodoActualTile.obtenerX();
			tileFinal[1] = nodoActualTile.obtenerY();
			
			xFinal = Mapa.dosDaIso(tileFinal[0], tileFinal[1])[0];
			yFinal = Mapa.dosDaIso(tileFinal[0], tileFinal[1])[1];
			
			if(tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] - 1)
				movimientoHacia = verticalSup;
				//movimientoHacia = diagonalSupDer;
			
			if(tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] + 1)
				movimientoHacia = verticalInf;
				//verticalInf = true;
			
			if(tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] + 1)
				movimientoHacia = horizontalIzq;
				//horizontalIzq = true;
			
			if(tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] - 1)
				movimientoHacia = horizontalDer;
				//horizontalDer = true;
			
			if(tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1])
				movimientoHacia = diagonalSupIzq;
				//diagonalSupIzq = true;
			
			if(tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1])
				movimientoHacia = diagonalInfDer;
				//diagonalInfDer = true;
			
			if(tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] - 1)
				movimientoHacia = diagonalSupDer;
				//movimientoHacia = verticalInf;
			
			if(tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] + 1)
				movimientoHacia = diagonalInfIzq;
				//diagonalInfIzq = true;
			
			//juego.getHandlerMouse().setNuevoRecorrido(false);
			enMovimiento = true;
		}
	}

	public void mover() {

		dx = 0;
		dy = 0;
		
		double paso = 1;

		if (enMovimiento && !(x==xFinal && y==yFinal-32)) {			
			if(movimientoHacia == verticalSup)
				dy-=paso;
			else if(movimientoHacia == verticalInf)
				dy+=paso;
			else if(movimientoHacia == horizontalDer)
				dx+=paso;
			else if(movimientoHacia == horizontalIzq)
				dx-=paso;
			else if(movimientoHacia == diagonalInfDer) {
				dx+=paso;
				dy+=paso/2;
			} else if (movimientoHacia == diagonalInfIzq) {
				dx-=paso;
				dy+=paso/2;
			} else if (movimientoHacia == diagonalSupDer) {
				dx+=paso;
				dy-=paso/2;
			} else if (movimientoHacia == diagonalSupIzq) {
				dx-=paso;
				dy-=paso/2;
			}
			
			x+=dx;
			y+=dy;
			
			if(intervaloEnvio == 2) {
				enviarPosicion();
				intervaloEnvio = 0;
			}
			
			intervaloEnvio++;
				
			if (x==xFinal && y==yFinal-32) {
				enMovimiento = false;
			}
		}
	}


		

	public void graficar(Graphics g) {
		drawX = (int) (x - juego.getCamara().getxOffset());
		drawY = (int) (y - juego.getCamara().getyOffset());
		g.drawImage(getFrameAnimacionActual(), drawX, drawY, ancho, alto, null);
		g.drawImage(obtenerImagenCuadro(), 300, 0, 300, 90, null);
		g.drawImage(obtenerImagenRaza(this.juego.getRaza()), 310, 0, 90, 90, null);
		g.setColor(Color.WHITE); 
		g.drawString("RAZA: " + this.juego.getRaza(), 410, 40);
		g.drawString("CASTA: GUERRERO", 410, 55);
		g.drawString("NIVEL: 1", 410, 70);
		g.setColor(Color.RED); 
		Font myFont = new Font ("OCR A Extended", Font.BOLD, 16);
		g.setFont (myFont);
	    g.drawString(this.juego.getNickName().toUpperCase(), drawX + 10, drawY - 10);
	}

	private Image obtenerImagenCuadro() {
		try {
			return ImageIO.read(new File(("resources/imagenes/cuadro.jpg")));
		} catch (IOException e) {
			Loggin.getInstance().error("Error ObtenerImagenCuadro: "+e.getMessage());
		}
		return null;
	}


	private Image obtenerImagenRaza(String raza){
		if (raza != null && !raza.isEmpty()) {
			if (raza.contentEquals("HUMANO")) {
				try {
					return ImageIO.read(new File(("resources/imagenes/personajes/humano_short.png")));
				} catch (IOException e) {
					Loggin.getInstance().error("Error ObtenerImagenRaza: "+e.getMessage());
				}
			} else if (raza.contentEquals("ELFO")) {
				try {
					return ImageIO.read(new File(("resources/imagenes/personajes/elfo_short.png")));
				} catch (IOException e) {
					Loggin.getInstance().error("Error ObtenerImagenRaza: "+e.getMessage());
				}
			} else if (raza.contentEquals("ORCO")) {
				try {
					return ImageIO.read(new File(("resources/imagenes/personajes/orco_short.png")));
				} catch (IOException e) {
					Loggin.getInstance().error("Error ObtenerImagenRaza: "+e.getMessage());
				}
			}
		}
		return null;
	}


	private BufferedImage getFrameAnimacionActual() {
		if (movimientoHacia == horizontalIzq) {
			return moverIzq.getFrameActual();
		} else if (movimientoHacia == horizontalDer) {
			return moverDer.getFrameActual();
		} else if (movimientoHacia == verticalSup) {
			return moverArriba.getFrameActual();
		} else if (movimientoHacia == verticalInf) {
			return moverAbajo.getFrameActual();
		} else if (movimientoHacia == diagonalInfIzq) {
			return moverAbajoIzq.getFrameActual();
		} else if (movimientoHacia == diagonalInfDer) {
			return moverAbajoDer.getFrameActual();
		} else if (movimientoHacia == diagonalSupIzq) {
			return moverArribaIzq.getFrameActual();
		} else if (movimientoHacia == diagonalSupDer) {
			return moverArribaDer.getFrameActual();
		}

		return moverAbajo.getFrameActual();
	}
	
	private int getDireccion() {
		return movimientoHacia;
	}
	
	private int getFrame() {
		if (movimientoHacia == horizontalIzq) {
			return moverIzq.getCuadro();
		} else if (movimientoHacia == horizontalDer) {
			return moverDer.getCuadro();
		} else if (movimientoHacia == verticalSup) {
			return moverArriba.getCuadro();
		} else if (movimientoHacia == verticalInf) {
			return moverAbajo.getCuadro();
		} else if (movimientoHacia == diagonalInfIzq) {
			return moverAbajoIzq.getCuadro();
		} else if (movimientoHacia == diagonalInfDer) {
			return moverAbajoDer.getCuadro();
		} else if (movimientoHacia == diagonalSupIzq) {
			return moverArribaIzq.getCuadro();
		} else if (movimientoHacia == diagonalSupDer) {
			return moverArribaDer.getCuadro();
		}

		return 0;
	}

	private void enviarPosicion() {
		juego.getPersonaje().setPosX(x);
		juego.getPersonaje().setPosY(y);
		juego.getPersonaje().setDireccion(getDireccion());
		juego.getPersonaje().setFrame(getFrame());
		juego.getPersonaje().setComando("movimiento");
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje()));
		} catch (IOException e) {
			Loggin.getInstance().error("Error Entidad: "+e.getMessage());
		}
	}
	
	private PilaDeTiles obtenerCaminoMasCorto(int xInicial, int yInicial, int xFinal, int yFinal) {
		Grafo grafoLibres = mapa.obtenerGrafoDeTilesSinObstaculos();
		// Transformo las coordenadas iniciales y finales en indices
		int nodoInicial = (yInicial-grafoLibres.obtenerNodos()[0].obtenerY())*(int)Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xInicial-grafoLibres.obtenerNodos()[0].obtenerX();
		int nodoFinal = (yFinal-grafoLibres.obtenerNodos()[0].obtenerY())*(int)Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xFinal-grafoLibres.obtenerNodos()[0].obtenerX();
		
		// Hago todo
		double[] vecCostos = new double[grafoLibres.obtenerCantidadDeNodosTotal()];
		int[] vecPredecesores = new int[grafoLibres.obtenerCantidadDeNodosTotal()];
		boolean[] conjSolucion = new boolean[grafoLibres.obtenerCantidadDeNodosTotal()];
		int cantSolucion = 0;
		// Lleno la matriz de costos de numeros grandes
		for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
			vecCostos[i] = Double.MAX_VALUE;
		}
		// Adyacentes al nodo inicial
		conjSolucion[nodoInicial] = true;
		cantSolucion++;
		vecCostos[nodoInicial] = 0;
		Nodo[] adyacentes = grafoLibres.obtenerNodos()[nodoInicial].obtenerNodosAdyacentes();
		for (int i = 0; i < grafoLibres.obtenerNodos()[nodoInicial].obtenerCantidadDeAdyacentes(); i++) {
			if(estanEnDiagonal(grafoLibres.obtenerNodos()[nodoInicial],grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()]))
				vecCostos[adyacentes[i].obtenerIndice()] = 1.5;
			else
				vecCostos[adyacentes[i].obtenerIndice()] = 1;
			vecPredecesores[adyacentes[i].obtenerIndice()] = nodoInicial;
		}
		// Aplico Dijkstra
		while(cantSolucion < grafoLibres.obtenerCantidadDeNodosTotal()){
			// Elijo W perteneciente al conjunto restante tal que el costo de W sea minimo
			double minimo = Double.MAX_VALUE;
			int indiceMinimo = 0;
			Nodo nodoW = null;
			for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
				if(!conjSolucion[i] && vecCostos[i]<minimo){
					nodoW = grafoLibres.obtenerNodos()[i];
					minimo = vecCostos[i];
					indiceMinimo = i;
				}
			}
			// Pongo a W en el conj solucion
			conjSolucion[indiceMinimo] = true;
			cantSolucion++;
			// Por cada nodo I adyacente a W del conj restante
			// Le sumo 1 al costo de ir hasta W y luego ir hasta su adyacente
			adyacentes = grafoLibres.obtenerNodos()[indiceMinimo].obtenerNodosAdyacentes();
			for (int i = 0; i < grafoLibres.obtenerNodos()[indiceMinimo].obtenerCantidadDeAdyacentes(); i++) {
				double valorASumar = 1;
				if(estanEnDiagonal(grafoLibres.obtenerNodos()[indiceMinimo],grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()]))
					valorASumar = 1.5;
				if(vecCostos[indiceMinimo] + valorASumar < vecCostos[adyacentes[i].obtenerIndice()]){
					vecCostos[adyacentes[i].obtenerIndice()] = vecCostos[indiceMinimo] + valorASumar;
					vecPredecesores[adyacentes[i].obtenerIndice()] = indiceMinimo;
				}
			}
		}
		// Creo el vector de nodos hasta donde quiere llegar
		PilaDeTiles camino = new PilaDeTiles();
		int caca = 30;
		while(nodoFinal != nodoInicial){
			
			camino.push(new NodoDePila(grafoLibres.obtenerNodos()[nodoFinal].obtenerX(),grafoLibres.obtenerNodos()[nodoFinal].obtenerY()));
			nodoFinal = vecPredecesores[nodoFinal];
			
		}
		// A ver que onda esto
		return camino;
	}
	
	private boolean estanEnDiagonal(Nodo nodoUno, Nodo nodoDos){
		if(nodoUno.obtenerX() == nodoDos.obtenerX() || nodoUno.obtenerY() == nodoDos.obtenerY())
			return false;
		return true;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	public int getxOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
}