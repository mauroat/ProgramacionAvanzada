package kruskal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Grafo {
	private List<Arista> aristas;
	private List<Arista> solucion;
	int[] padre;
	private int cantNodos;
	
	public Grafo(String path) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(path));
		this.cantNodos = sc.nextInt();
		this.aristas = new ArrayList<Arista>();
		this.solucion = new ArrayList<Arista>();
		
		for(int i = 0; i < this.cantNodos; i++)
		{
			for(int j = 0; j < this.cantNodos; j++)
			{
				int costo = sc.nextInt();
				if(costo > 0)
					this.aristas.add(new Arista(i, j, costo));
			}
		}
		sc.close();
	}
	
	public void Kruskal()
	{
		this.padre = new int[this.cantNodos];
		Collections.sort(aristas);
		int costo = 0;
		
		for(int i = 1; i < this.cantNodos; i++)
			this.padre[i] = i;
		
		for(Arista a : aristas)
		{
			if(find(a.getOrigen()) != find(a.getDestino()))
			{
				costo += a.getCosto();
				solucion.add(a);
				union(a.getOrigen(), a.getDestino());
			}
		}
	}
	
	public int find(int x)
	{
		return (x == padre[x]? x : find(this.padre[x]));
	}
	
	public void union (int x, int y)
	{
		padre[find(x)] = find(y);
	}
	
	public void mostrar()
	{
		for(Arista a : solucion)
		{
			System.out.println("Origen: " + a.getOrigen() + "  Destino: " + a.getDestino() + "  Costo: " + a.getCosto());
		}
	}
}
