package interfaz;

import controlador.ControladorGaleria;
import usuarios.Empleado;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import consola.ConsolaCentral;
import consola.ConsolaUsuarioCorriente;

public class PanelIngresar extends JPanel {

	// Atributos de la interfaz
	private JTextField login;
	private JTextField contraseña;
	private JButton BtnAceptar;
	private static ControladorGaleria controladorGaleria;

	public PanelIngresar() {
		IniciarRegistro();
		ConfigurarRegistro();
		AgregarRegistro();
	}

	private void IniciarRegistro() {
		login = new JTextField(15);
		contraseña = new JTextField(15);
		BtnAceptar = new JButton("Iniciar Sesion");
	}

	private void ConfigurarRegistro() {
		controladorGaleria = new ControladorGaleria();
		try {
			controladorGaleria.cargarGaleria();
		} catch (ClassNotFoundException | IOException e1) {

			e1.printStackTrace();
		}
		login.setEditable(true);
		contraseña.setEditable(true);
		BtnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userLogin = login.getText();
				String userPassword = contraseña.getText();
				System.out.println("Usuario: " + userLogin); // Debug
				System.out.println("Contraseña: " + userPassword); // Debug

				Empleado empleado = controladorGaleria.galeria.buscarEmpleadoUsername(userLogin);
				if (empleado != null) {
					controladorGaleria.iniciarSesion(userLogin, userPassword);
					JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso: Bienvenido " + empleado.getNombre());
					ConsolaCentral consolaCentral = new ConsolaCentral(controladorGaleria);

					try {
						consolaCentral.portalEmpleados();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					boolean result = controladorGaleria.iniciarSesion(userLogin, userPassword);
					System.out.println("Resultado del inicio de sesión: " + result); // Debug
					if (result) {
						JOptionPane.showMessageDialog(null,
								"Inicio de sesión exitoso: Bienvenido "
										+ controladorGaleria.usuarioDeLaSesion.getNombre());
						ConsolaCentral consolaCentral = new ConsolaCentral(controladorGaleria);

						consolaCentral.correrAplicacion();

					} else {
						JOptionPane.showMessageDialog(null, "No se encontró el usuario en el sistema", "Error",
								JOptionPane.ERROR_MESSAGE);
						System.out.println("No se encontró el usuario en el sistema");
					}
				}
			}
		});
	}

	private void AgregarRegistro() {
		add(new JLabel("Login:"));
		add(login);
		add(new JLabel("Contraseña:"));
		add(contraseña);
		add(BtnAceptar);
	}
}
