package socket;
import java.io.*;
import java.net.*;

public class Servidor {

	Servidor(){
		try {
			ServerSocket server = new ServerSocket(10000);
			for(int i=1; i<4;i++){
				Socket cliente = server.accept(); // Se queda esperando hasta tener peticion
				// Armo el buffer para recibir la información
				DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());	// Para Texto se usa ObjectOutputStream
				// Escribo la salida con lo que puse en el buffer
				salida.writeUTF("Hola cliente "+i);
				cliente.close();
			}
		} catch (Exception e){
			
		}
	}
	
	public static void main(String []args){
		new Servidor();
	}
	
}
