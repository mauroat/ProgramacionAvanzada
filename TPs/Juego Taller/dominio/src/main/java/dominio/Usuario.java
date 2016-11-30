package dominio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {

	private int idUsuario;
	private String username;
	private String password;

	public Usuario(String username, String password) {
		this.username = username;
		hashPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		hashPassword(password);
	}

	public int guardarUsuario() {
		return 1;
	}

	public int validarIngreso(String username, String password) {
		return 0;
	}

	private void hashPassword(String pas) {
		String passwordToHash = pas;
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// Loguear Error.
		}
		this.password = generatedPassword;
	}
	 
	  public boolean equals(Usuario obj) {
	 
	     if(this.idUsuario == obj.idUsuario && this.username == obj.username)
	 
	       return true;
	 
	     return false;  
	 
	   }
	 
}