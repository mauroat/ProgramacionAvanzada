package grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrizSimetricaTest {

	@Test
	public void matrizSimetricaTest(){
		
		MatrizSimetrica mat1 = new MatrizSimetrica(5);	
		try{
			for(int i = 0; i < 5; i ++)
				for(int j = i+1; j < 5; j ++)
					mat1.setDato(i,j);
		}
		catch(Exception E){
			E.printStackTrace();
		}
		
	}
	
	
}
