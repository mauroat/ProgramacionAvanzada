package socket;
import java.io.*;
import java.net.*;

public class Cliente {
	Cliente(){
		try{
			Socket cliente = new Socket("localhost",10000);
			DataInputStream lectura = new DataInputStream(cliente.getInputStream());
			System.out.println(lectura.readUTF());
			cliente.close();
		} catch(Exception e){
			
		}
	}
	
	
	public static void main(String []args){
		new Cliente();
	}
	
}
