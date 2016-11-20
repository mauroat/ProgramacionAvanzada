package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cliente.Usuario;

//import connection.Mensaje;
//import entities.Usuario;
//import utilities.Loggin;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = -6471222468956323219L;
	private JPanel contentPane;
//	private Conexion conexion;
	private JTextField txtUsuario;
	private JPasswordField tpContrasena;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JButton btnIngresar;
	private Login login;
	private Socket cliente;
	private DataOutputStream out;
	private DataInputStream in;
	private int puerto;
	private String ip;
	private Gson gson;
//	private Mensaje msj;
	private String username;
	private JButton btnConexion;

	public Socket getCliente() {
		return cliente;
	}

	public Login(final Usuario usuario, final Semaphore semaforo){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				usuario.setAccion("cerrar");
				semaforo.release();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});
		
		setTitle("BloodyWars - Login");
		setLogin(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			leerConfig();	// VER SI TODAVIA SIRVE
		} catch (Exception e) {
//			Loggin.getInstance().error("Error conexion con servidor "+e.getMessage());
		}

		

		JLabel lblBloodyWars = new JLabel("BloodyWars");
		lblBloodyWars.setHorizontalAlignment(SwingConstants.CENTER);
		lblBloodyWars.setBounds(61, 11, 236, 48);
		contentPane.add(lblBloodyWars);

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsuario.setBounds(7, 84, 96, 20);
		contentPane.add(lblUsuario);
		lblContrasena = new JLabel("Contrase\u00F1a: ");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContrasena.setBounds(7, 108, 96, 20);
		contentPane.add(lblContrasena);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsuario.setBounds(107, 84, 251, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		tpContrasena = new JPasswordField();
		tpContrasena.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tpContrasena.addKeyListener(new KeyAdapter() {

		});
		tpContrasena.setBounds(107, 108, 251, 20);
		contentPane.add(tpContrasena);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIngresar.setBounds(107, 132, 120, 21);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.setAccion("inicioSesion");
				usuario.setNombre_usuario(txtUsuario.getText());
				usuario.setPassword_usuario(tpContrasena.getText());
				usuario.setOpcion(1);
				semaforo.release();
			
				dispose();
			}
		});
		

		contentPane.add(btnIngresar);
		
		btnConexion = new JButton("Conexi\u00F3n");
		btnConexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		btnConexion.setBounds(237, 131, 121, 23);
		contentPane.add(btnConexion);
		visible(true);

	}
	
	private boolean camposCompletos() {
		if (txtUsuario.getText().length() != 0 && tpContrasena.getPassword().length != 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Complete todos los campos", "Campo vacio", JOptionPane.WARNING_MESSAGE);
		return false;
	}

	private void leerConfig(){
		String linea;
		String[] splitLine;
		Scanner sc= null;
		try {
			sc = new Scanner(new File("src/main/resources/App.config"));
		} catch (FileNotFoundException e) {
//			Loggin.getInstance().error("Error leerConfig "+e.getMessage());
		}
		linea = sc.nextLine();
		splitLine = linea.split(":");
		this.ip = splitLine[1];

		linea = sc.nextLine();
		splitLine = linea.split(":");
		this.puerto = Integer.parseInt(splitLine[1]);
	}

	public void setLogin(Login miLogin) {
		this.login = miLogin;
	}

	public void cancelar() {
		txtUsuario.setText("");
		tpContrasena.setText("");
		txtUsuario.grabFocus();
	}

	public void visible(boolean value) {
		this.setVisible(value);
	}

	public String getUsername() {
		return username.toUpperCase();
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
