package mapa;

import java.awt.Graphics;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;

import juego.Juego;
import utilities.Loggin;

public class Mapa {
	private Juego juego;
	private Grafo grafoDeTilesSinObstaculos;

	private float[] iso = new float[2];
	private int[][] tiles;
	private int[][] tilesInv;
	
	private int ancho;
	private int alto;
	private int spawnX;
	private int spawnY;
	private int xOffset;
	private int yOffset;	

	private int xMinimo;
	private int xMaximo;
	private int yMinimo;
	private int yMaximo;
	

	public Mapa(Juego juego) { 
	    this.juego = juego;
	    cargarMapa(juego.getTipoMapa()); 
	    convertirMapaAGrafo();
	}
	
	public void actualizar() {

	}

	public static float[] isoA2D(float x, float y) {
		float[] dosD = new float[2];

		dosD[0] = (x / (Tile.ANCHO / 2) + y / (Tile.ALTO / 6)) / 2;
		dosD[1] = (y / (Tile.ALTO / 6) - (x / (Tile.ANCHO / 2))) / 2;

		return dosD;
	}

	public static float[] dosDaIso(float x, float y) {
		float[] iso = new float[2];

		iso[0] = (x - y) * (Tile.ANCHO / 2);
		iso[1] = (x + y) * (Tile.ALTO / 6);

		return iso;
	}

	public static int[] mouseATile(float x, float y) {
		int tile[] = new int[2];

		tile[0] = (int) Math.floor((y / (Tile.ALTO / 3)) + (x / Tile.ANCHO)) + 1;
		tile[1] = (int) Math.floor((-x / Tile.ANCHO) + (y / (Tile.ALTO / 3))) + 1;

		return tile;
	}
	
	public void graficar(Graphics g) {
		xOffset = juego.getEstadoJuego().getPersonaje().getxOffset();
		yOffset = juego.getEstadoJuego().getPersonaje().getYOffset();

		xMinimo = (int) (juego.getCamara().getxOffset() - xOffset - 30);
		xMaximo = xMinimo + juego.getAncho() + xOffset + 30;
		yMinimo = (int) juego.getCamara().getyOffset() - yOffset + 5;
		yMaximo = yMinimo + juego.getAlto() + yOffset - 5; 

		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				iso = dosDaIso(j, i);
				if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)) {
					getTile(j, i).graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
							(int) (iso[1] - juego.getCamara().getyOffset()));
				}
			}
		}
	}

	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.pasto;
		}
		return t;
	}

	 private void cargarMapa(String tipoMapa) { 
		    String archivo = convertirArchivoAString(tipoMapa);
		String[] tokens = archivo.split("\\s+");
		ancho = Integer.parseInt(tokens[0]); 
	    alto = Integer.parseInt(tokens[1]); 
	    spawnX = Integer.parseInt(tokens[2]); 
	    spawnY = Integer.parseInt(tokens[3]);

		tiles = new int[ancho][alto];
		tilesInv = new int[alto][ancho];
		
		for (int y = 0; y < alto; y++)
			for (int x = 0; x < ancho; x++){
				tiles[x][y] = Integer.parseInt(tokens[(x + y * ancho + 4)]); 
		        tilesInv[y][x] = tiles[x][y];
			}
				
	}
	 
	 private String convertirArchivoAString(String tipoMapa) { 
		    StringBuilder builder = new StringBuilder(); 
		 
		    try { 
		      String path = obtenerPathMapa(tipoMapa); 
		      BufferedReader br = new BufferedReader(new FileReader(path)); 
		      String linea; 
		 
		      while ((linea = br.readLine()) != null) { 
		        builder.append(linea + System.lineSeparator()); 
		      } 
		    } catch (IOException e) { 
		      Loggin.getInstance().error("Error mundo: "+e.getMessage()); 
		    } 
		 
		    return builder.toString(); 
		  } 
		 
		  private String obtenerPathMapa(String nroMapa) { 
		    String pathMapa = ""; 
		     
		    switch(nroMapa) 
		    { 
		      case "llanura": pathMapa = "resources/mapas/mapa1.txt"; break; 
		      case "desierto": pathMapa = "resources/mapas/mapa2.txt"; break; 
		    } 
		     
		    return pathMapa; 
	  } 
		 
	private void convertirMapaAGrafo(){
		Nodo [][] nodos = new Nodo[ancho][alto];
		int indice = 0;
		for(int y=0; y<alto; y++)
			for(int x=0; x<ancho; x++)
				nodos[y][x] = new Nodo(indice++, x, y);
		int xFinal = ancho;
		int yFinal = alto;
		for(int x=0; x<yFinal; x++){
			for(int y=0; y<xFinal; y++){
				if(!Tile.tiles[tilesInv[x][y]].esObstaculo()){
					if(y<yFinal-1 && !Tile.tiles[tilesInv[x][y+1]].esObstaculo()){
						nodos[x][y].agregarAdyacente(nodos[x][y+1]);
						nodos[x][y+1].agregarAdyacente(nodos[x][y]);
					}
					if(x<xFinal-1){
						if(y>0 && !Tile.tiles[tilesInv[x+1][y-1]].esObstaculo()
								&& !Tile.tiles[tilesInv[x+1][y]].esObstaculo() && !Tile.tiles[tilesInv[x][y-1]].esObstaculo()){
							nodos[x][y].agregarAdyacente(nodos[x+1][y-1]);
							nodos[x+1][y-1].agregarAdyacente(nodos[x][y]);
						}
						if(!Tile.tiles[tilesInv[x+1][y]].esObstaculo()){
							nodos[x][y].agregarAdyacente(nodos[x+1][y]);
							nodos[x+1][y].agregarAdyacente(nodos[x][y]);
						}
						if(y<yFinal-1 && !Tile.tiles[tilesInv[x+1][y+1]].esObstaculo()
								&& !Tile.tiles[tilesInv[x+1][y]].esObstaculo() && !Tile.tiles[tilesInv[x][y+1]].esObstaculo()){
							nodos[x][y].agregarAdyacente(nodos[x+1][y+1]);
							nodos[x+1][y+1].agregarAdyacente(nodos[x][y]);
						}
					}					
				}
			}
		}

		grafoDeTilesSinObstaculos = new Grafo(ancho*alto);
		indice = 0;
		for(int i=0; i<ancho; i++)
			for(int j=0; j<alto; j++)
				grafoDeTilesSinObstaculos.agregarNodo(nodos[i][j]);
	}
	
	public Grafo obtenerGrafoDeTilesSinObstaculos(){
		return grafoDeTilesSinObstaculos;
	}
	
	public int obtenerAncho(){
		return ancho;
	}
	
	public int obtenerAlto(){
		return alto;
	}
	
}