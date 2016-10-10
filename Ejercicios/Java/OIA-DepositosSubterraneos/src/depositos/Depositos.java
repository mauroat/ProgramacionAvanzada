package depositos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Depositos {
	
	private int cantDepositos;
	private int volumenTotal;
	private int[][] valores;
	
	public Depositos(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		this.cantDepositos = sc.nextInt();
		this.valores = new int[this.cantDepositos][2];
		for(int i = 0; i<this.cantDepositos;i++){
			this.valores[i][0] = sc.nextInt();	// superficie
			this.valores[i][1] = sc.nextInt();	// profundidad
		}
		this.volumenTotal = sc.nextInt();
		sc.close();
	}
	
	public void mostrarEntrada(){
		System.out.println(this.cantDepositos);
		for (int i = 0; i<this.cantDepositos;i++)
			System.out.println(this.valores[i][0]+" "+this.valores[i][1]);
		System.out.println(this.volumenTotal);
	}
	
	public void resolver(){
		int i=0,volumenAcumulado=0,topeProfundidad,diferencia,sobrante=0,depos=0;
		boolean marca=false;
		
		topeProfundidad = this.valores[i][1];		
		
		while(i < cantDepositos &&  marca == false){
			if(topeProfundidad ==  valores[i][1]){
				volumenAcumulado += valores[i][0] * valores[i][1];
				i++;
			} else {
				if(volumenAcumulado >= this.volumenTotal){
					marca = true;
					diferencia = this.volumenTotal - volumenAcumulado ;
					depos = i;
					sobrante = 0;
				} else {
					topeProfundidad = valores[i][1];			
				}
			}
						
		}
		if(marca == true){
			this.mostrarResultadoBueno(depos, sobrante);
		} else {
			diferencia = this.volumenTotal - volumenAcumulado;
			this.mostrarResultadoMalo(diferencia);
		}
		
	}
	
	public void mostrarResultadoBueno(int depositos, int rebalse){
		System.out.println("Cantidad de depósitos: "+depositos);
	}
	
	public void mostrarResultadoMalo(int rebalse){
		System.out.println("Rebalsan: "+rebalse);
	}
	
}



