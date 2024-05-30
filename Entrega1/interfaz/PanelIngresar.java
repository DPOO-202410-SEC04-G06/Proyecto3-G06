package interfaz;

import controlador.ControladorGaleria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import consola.ConsolaUsuarioCorriente;


public class PanelIngresar extends JPanel{
	
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
		BtnAceptar = new JButton("Aceptar");
	}
	
	private void ConfigurarRegistro() {

		login.setEditable(true);
		contraseña.setEditable(true );	
		BtnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = login.getText();
				String password = contraseña.getText();
				// Aquí puedes usar las variables usuario y password como necesites
				System.out.println("Usuario: " + usuario + ", Contraseña: " + password);
				boolean result = controladorGaleria.iniciarSesion( usuario, password );
				
				if ( result )
				{
					JFrame frame = new InterfazUsuarioCorriente(controladorGaleria);
                    frame.setVisible(true);
					
				}
				else
				{
					System.out.println( "No se encontró el usuario en el sistema" );
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
