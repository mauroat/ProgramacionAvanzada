package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cliente.Usuario;
import utilities.Loggin;
import java.awt.Font;

//import connection.Mensaje;
//import entities.Usuario;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JFrame frmRegistro;
//	private JTextField txtUsuario;
	private JTextField txtUsuario_1;
	private JPasswordField txtPassword;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JButton btnRegistrar;
	private Splash sp;


	public Registro(final Usuario usuario,final Semaphore semaforo, Splash s) {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		sp=s;
		
		
		frmRegistro = new JFrame();
		frmRegistro.setResizable(false);
		frmRegistro.setTitle("BloodyWars - Registro");
		frmRegistro.setBounds(100, 100, 474, 249);
		//frmRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistro.getContentPane().setLayout(null);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		lblUsuario.setBounds(21, 122, 77, 14);
		frmRegistro.getContentPane().add(lblUsuario);

		lblPassword = new JLabel("Contrasena:");
		lblPassword.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		lblPassword.setBounds(21, 147, 77, 14);
		frmRegistro.getContentPane().add(lblPassword);
		
		frmRegistro.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sp.setVisible(true);
				dispose();
			}
		});
		
//		lblRepetirPassword = new JLabel("Repetir Contrasena:");
//		lblRepetirPassword.setBounds(22, 108, 165, 29);
//		frmRegistro.getContentPane().add(lblRepetirPassword);

//		txtUsuario = new JTextField();
//		frmRegistro.getContentPane().add(txtUsuario);
//		txtUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPassword.setBounds(108, 147, 350, 20);
		frmRegistro.getContentPane().add(txtPassword);

		txtUsuario_1 = new JTextField();
		txtUsuario_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsuario_1.setColumns(10);
		txtUsuario_1.setBounds(108, 119, 350, 20);
		frmRegistro.getContentPane().add(txtUsuario_1);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(camposCompletos()){
					usuario.setNombre_usuario(txtUsuario_1.getText().toUpperCase());
					usuario.setPassword_usuario(txtPassword.getText());
					usuario.setAccion("registro");
					usuario.setOpcion(1);
					semaforo.release();
					frmRegistro.dispose();
				}
			}
		});
		btnRegistrar.setBounds(343, 189, 115, 20);
		frmRegistro.getContentPane().add(btnRegistrar);
		frmRegistro.setVisible(true);
	}


	/*private boolean nombreUsuarioValido(String nombre) {
		msj.setId("nombreUsuarioValido");
		msj.setMensaje(nombre);
		this.login.enviarMensaje(msj);
		String resp = this.login.leerRespuesta();
		if (resp.equals("Ok")) {
			return true;
		}
		return false;
	}
*/
/*
	protected void registrar() {
		if (!nombreUsuarioValido(txtUsuario.getText())) {
			JOptionPane.showMessageDialog(null, "Nombre de usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			if (camposCompletos() && coincidenContrasenas()) {
				Usuario u = new Usuario(txtUsuario.getText(), password1.getText());
				String resp = "";
				msj.setId("registrarse");
				// Envio un usuario como mensaje
				msj.setMensaje(gson.toJson(u));
				this.login.enviarMensaje(msj);
				resp = this.login.leerRespuesta();
				if (resp.equals("Ok")) {
					JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "Usuario",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, resp, "Error", JOptionPane.ERROR_MESSAGE);
				}
				cancelar();
			}
		}
	}



	private boolean coincidenContrasenas() {
		if (password1.getText().equals(password2.getText()))
			return true;
		JOptionPane.showMessageDialog(null, "No coinciden las contrasenas", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}
*/

	private boolean camposCompletos() {
		if (txtUsuario_1.getText().length() != 0 && txtPassword.getPassword().length != 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Complete todos los campos", "Campo vacio", JOptionPane.WARNING_MESSAGE);
		return false;
	}
	
	private void cancelar() {
		txtUsuario_1.setText("");
		txtPassword.setText("");
//		password2.setText("");
	}
}
