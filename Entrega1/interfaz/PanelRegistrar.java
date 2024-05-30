package interfaz;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelRegistrar extends JPanel{
	
	// Atributos de la interfaz
	private JTextField nombre;
	private JTextField correo;
	private JTextField telefono;
	private JTextField tipo;
	private JTextField login;
	private JTextField contraseña;
	private JButton BtnAceptar;
	
	public PanelRegistrar() {
		IniciarRegistro();
		ConfigurarRegistro();
		AgregarRegistro();
	}

	private void IniciarRegistro() {
		nombre = new JTextField(15);
		correo = new JTextField(15);
		telefono = new JTextField(15);
		tipo = new JTextField(15);
		login = new JTextField(15);
		contraseña = new JTextField(15);
		BtnAceptar = new JButton("Aceptar");
	}
	
	private void ConfigurarRegistro() {
		nombre.setEditable(true);
		correo.setEditable(true);
		telefono.setEditable(true);
		tipo.setEditable(true);
		login.setEditable(true);
		contraseña.setEditable(true );	
		
	}
	
	private void AgregarRegistro() {
        add(new JLabel("Nombre:"));
        add(nombre);
        add(new JLabel("Correo:"));
        add(correo);
        add(new JLabel("Telefono:"));
        add(telefono);
        add(new JLabel("Tipo:"));
        add(tipo);
        add(new JLabel("Login:"));
        add(login);
        add(new JLabel("Contraseña:"));
        add(contraseña);
		
	}

}
