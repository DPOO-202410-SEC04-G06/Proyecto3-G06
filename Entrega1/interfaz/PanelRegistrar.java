package interfaz;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import controlador.ControladorGaleria;
import usuarios.Empleado;

public class PanelRegistrar extends JPanel {

	// Atributos de la interfaz
	private JTextField nombre;
	private JTextField correo;
	private JTextField telefono;
	private JTextField username;
	private JTextField contraseña;
	private JButton BtnRegistrar;
	private JButton BtnCancelar; // Nuevo botón
	private JCheckBox chkEmpleado; // Nuevo check button
	private JComboBox<String> cmbTipoEmpleado; // Combo box para tipos de empleado
	private ControladorGaleria controladorGaleria; // Controlador para manejar la lógica del usuario

	public PanelRegistrar(ControladorGaleria controlador) {
		this.controladorGaleria = controlador; // Inicialización del controlador
		IniciarRegistro();
		ConfigurarRegistro();
		AgregarRegistro();
	}

	private void IniciarRegistro() {
		nombre = new JTextField(15);
		correo = new JTextField(15);
		telefono = new JTextField(15);
		username = new JTextField(15);
		contraseña = new JTextField(15);
		BtnRegistrar = new JButton("Registrar");
		BtnCancelar = new JButton("Cancelar"); // Inicialización del nuevo botón

		chkEmpleado = new JCheckBox("Es empleado"); // Inicialización del check button
		chkEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbTipoEmpleado.setEnabled(chkEmpleado.isSelected());
			}
		});

		cmbTipoEmpleado = new JComboBox<>(new String[] { "Administrador", "Cajero", "Operador", "Empleado corriente" });
		cmbTipoEmpleado.setEnabled(false); // Por defecto no habilitado

		// Añadir action listeners a los botones
		BtnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					crearNuevoUsuario();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error al crear el usuario", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		BtnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
	}

	private void ConfigurarRegistro() {
		nombre.setEditable(true);
		correo.setEditable(true);
		telefono.setEditable(true);
		username.setEditable(true);
		contraseña.setEditable(true);
	}

	private void AgregarRegistro() {
		add(new JLabel("Nombre:"));
		add(nombre);
		add(new JLabel("Correo:"));
		add(correo);
		add(new JLabel("Telefono:"));
		add(telefono);
		add(new JLabel("User Name:"));
		add(username);
		add(new JLabel("Contraseña:"));
		add(contraseña);
		add(chkEmpleado); // Añadir el check button
		add(new JLabel("Tipo de Empleado:"));
		add(cmbTipoEmpleado); // Añadir el combo box
		add(BtnRegistrar);
		add(BtnCancelar); // Añadir el nuevo botón
	}

	private void crearNuevoUsuario() throws IOException {
		if (nombre.getText().isEmpty() || correo.getText().isEmpty() || telefono.getText().isEmpty()
				|| username.getText().isEmpty() || contraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String iName = nombre.getText();
		String iCorreo = correo.getText();
		String iPhone = telefono.getText();
		String iUsername = username.getText();
		String iPassword = contraseña.getText();

		if (chkEmpleado.isSelected()) {
			String selectedRole = cmbTipoEmpleado.getSelectedItem().toString();
			int iRol = obtenerCodigoRol(selectedRole);

			controladorGaleria.galeria.crearEmpleado(iName, iPhone, iUsername, iPassword, iRol);
		} else {
			controladorGaleria.galeria.crearUsuarioCorriente(iName, iPhone, iUsername, iPassword);
		}

		controladorGaleria.salvarGaleria(controladorGaleria.galeria);
		JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		limpiarCampos();
	}

	private int obtenerCodigoRol(String rol) {
		switch (rol) {
			case "Administrador":
				return Empleado.ADMIN;
			case "Cajero":
				return Empleado.CAJERO;
			case "Operador":
				return Empleado.OP;
			case "Empleado corriente":
				return Empleado.CORRIENTE;
			default:
				return Empleado.CORRIENTE;
		}
	}

	private void limpiarCampos() {
		nombre.setText("");
		correo.setText("");
		telefono.setText("");
		username.setText("");
		contraseña.setText("");
		chkEmpleado.setSelected(false);
		cmbTipoEmpleado.setEnabled(false);
	}
}
