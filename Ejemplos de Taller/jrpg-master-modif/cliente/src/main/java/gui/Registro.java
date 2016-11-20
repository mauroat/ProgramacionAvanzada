package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Usuario;

//import connection.Mensaje;
//import entities.Usuario;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JFrame frmRegistro;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
//	private Login login;
	private JLabel lblUsuario;
	private JLabel lblPassword;
//	private JLabel lblRepetirPassword;
	private JButton btnRegistrar;


	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	public Registro() {

	}

	public Registro(final Usuario usuario,final Semaphore semaforo) {

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

//		gson = new Gson();

		frmRegistro = new JFrame();
		frmRegistro.setResizable(false);
		frmRegistro.setTitle("Registro");
		frmRegistro.setBounds(100, 100, 474, 324);
		frmRegistro.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmRegistro.getContentPane().setLayout(null);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(21, 32, 122, 29);
		frmRegistro.getContentPane().add(lblUsuario);

		lblPassword = new JLabel("Contrasena:");
		lblPassword.setBounds(22, 72, 122, 29);
		frmRegistro.getContentPane().add(lblPassword);

//		lblRepetirPassword = new JLabel("Repetir Contrasena:");
//		lblRepetirPassword.setBounds(22, 108, 165, 29);
//		frmRegistro.getContentPane().add(lblRepetirPassword);

		txtUsuario = new JTextField();
		frmRegistro.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(220, 76, 215, 20);
		frmRegistro.getContentPane().add(txtPassword);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(219, 36, 216, 20);
		frmRegistro.getContentPane().add(txtUsuario);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(camposCompletos()){
					usuario.setNombre_usuario(txtUsuario.getText().toUpperCase());
					usuario.setPassword_usuario(txtPassword.getText());
					usuario.setAccion("registro");
					usuario.setOpcion(0);
					semaforo.release();
					dispose();
				}
				
			}
		});
		btnRegistrar.setBounds(159, 174, 133, 29);
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
	private void salir() {
//		login.visible(true);
		frmRegistro.dispose();
	}

	private boolean camposCompletos() {
		if (txtUsuario.getText().length() != 0 && txtPassword.getPassword().length != 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Complete todos los campos", "Campo vacio", JOptionPane.WARNING_MESSAGE);
		return false;
	}
	
	private void cancelar() {
		txtUsuario.setText("");
		txtPassword.setText("");
//		password2.setText("");
	}
}
