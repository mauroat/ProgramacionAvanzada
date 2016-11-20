package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cliente.*;
import dominio.Personaje;

public class Conector {

	private String url = "BloodyWars.db";
	Connection connect;

	public void connect() {
		try {
			connect = DriverManager.getConnection("jdbc:sqlite:" + url);
			if (connect != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException ex) {
			System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
		}
	}

	public void close() {
		try {
			connect.close();
		} catch (SQLException ex) {
			Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public boolean registrarUsuario (Usuario user) {
		ResultSet result = null;
		
		try {
			PreparedStatement st1 = connect.prepareStatement("select * from USUARIO where NOMBRE= ?");
			st1.setString(1, user.getNombre_usuario().toUpperCase());
			result = st1.executeQuery();

			if (!result.next()) {

				PreparedStatement st = connect.prepareStatement("insert into USUARIO (NOMBRE, CONTRASENA) values (?,?)");
				st.setString(1, user.getNombre_usuario().toUpperCase());
				st.setString(2, user.getPassword_usuario());
				st.execute();
				// USUARIO REGISTRADO
				return true;
			} else {
				// System.out.println("Ya se usa ese user");
				return false;
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			// USUARIO REPETIDO
			return false;
		}

	}

	public boolean registrarPersonaje(Personaje p1, Usuario u1) {

		try {

			PreparedStatement st = connect.prepareStatement(
					"insert into PERSONAJE (ID_PERSONAJE, ID_USUARIO, NIVEL, EXPERIENCIA, VIDA, ENERGIA, ATAQUE, DEFENSA, "
					+ "MAGIA, PUNTOS, DESTREZA, VELOCIDAD, POTENCIA, RAZA, CASTA) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			st.setInt(1, u1.getIdPj());
			st.setInt(2, u1.getIdPj());
			st.setInt(3, p1.getNivel());
			st.setInt(4, p1.getExperiencia());
			st.setInt(5, p1.getVida());
			st.setInt(6, p1.getEnergia());
			st.setInt(7, p1.getAtaque());
			st.setInt(8, p1.getDefensa());
			st.setInt(9, p1.getMagia());
			st.setInt(10, p1.getPuntos());
			st.setInt(11, p1.getDestreza());
			st.setInt(12, p1.getVelocidad());
			st.setInt(13, p1.getPotencia());
			st.setString(14, p1.getClase().getNombre());
			st.setString(15, p1.getRaza());

			st.execute();

			ResultSet rs = st.getGeneratedKeys();
			if (rs != null && rs.next()) {
				int idPersonaje = rs.getInt(1);

				p1.setIdPersonaje(idPersonaje);////

				PreparedStatement st3 = connect
						.prepareStatement("update USUARIO set ID_PERSONAJE=? where NOMBRE=? and CONTRASENA=?");
				st3.setInt(1, idPersonaje);
				st3.setString(2, u1.getNombre_usuario());
				st3.setString(3, u1.getPassword_usuario());
				st3.execute();
				if (this.registrarInventarioMochila(idPersonaje))
					return true;
			}
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/*A MODIFICAR*/
	public boolean registrarInventarioMochila(int idInventarioMochila) {
		ResultSet result = null;
		try {

			PreparedStatement st1 = connect.prepareStatement(
					"insert into inventario(idInventario,manos1,manos2,pie,cabeza,pecho,accesorio) values (?,-1,-1,-1,-1,-1,-1)");
			st1.setInt(1, idInventarioMochila);
			PreparedStatement st2 = connect.prepareStatement(
					"insert into mochila(idMochila,item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13,item14,item15,item16,item17,item18,item19,item20) values(?,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1)");
			st2.setInt(1, idInventarioMochila);

			st1.execute();
			st2.execute();

			PreparedStatement st3 = connect.prepareStatement("update personaje set idInventario=? where idPersonaje=?");
			st3.setInt(1, idInventarioMochila);
			st3.setInt(2, idInventarioMochila);

			PreparedStatement st4 = connect.prepareStatement("update personaje set idMochila=? where idPersonaje=?");
			st4.setInt(1, idInventarioMochila);
			st4.setInt(2, idInventarioMochila);

			st3.execute();
			st4.execute();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean loguearUsuario(Usuario user) {
		ResultSet result = null;
		try {
			PreparedStatement st = connect
					.prepareStatement("select * from USUARIO where NOMBRE = ? AND CONTRASENA = ? ");
			st.setString(1, user.getNombre_usuario().toUpperCase());
			st.setString(2, user.getPassword_usuario());
			result = st.executeQuery();

			if (result.next()) {
				//System.out.println("Se logueo!");
				return true;
			}
			//System.out.println("No se logueo!");

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void mostrarUsuarios() {
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("select * from USUARIO");
			result = st.executeQuery();
			while (result.next()) {
				System.out.print("ID Usuario: ");
				System.out.println(result.getInt("ID_USUARIO"));
				
				System.out.print("Usuario: ");
				System.out.println(result.getString("NOMBRE"));

				System.out.print("Password: ");
				System.out.println(result.getString("CONTRASENA"));

				System.out.println("< ----- >");
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	// OJO CON ESTA QUERY QUE BORRA TODOS LOS REGISTROS DE LA TABLA USUARIO
	public void borrarRegistro() {
		try {
			PreparedStatement st = connect.prepareStatement("delete from USUARIO");
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PaquetePersonaje getPersonaje(Usuario user) {
		ResultSet result = null;
		try {
			System.out.println("Usuario: " + user.getNombre_usuario());
			PreparedStatement st = connect.prepareStatement("select * from USUARIO where NOMBRE = ?");
			st.setString(1, user.getNombre_usuario());
			result = st.executeQuery();
			int idPersonaje = result.getInt("ID_USUARIO");
			
			st = connect.prepareStatement("select * from PERSONAJE where ID_PERSONAJE = ?");
			st.setInt(1, idPersonaje);
			result = st.executeQuery();
			String casta = result.getString("CASTA");
			String raza = result.getString("RAZA");
			

			PaquetePersonaje personaje = new PaquetePersonaje();
		
			personaje.setIdPersonaje(idPersonaje);
			personaje.setRaza(raza);
			personaje.setCasta(casta);
		
			return personaje;
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return new PaquetePersonaje(-1, null, null, -1, -1, -1);
	}
}
