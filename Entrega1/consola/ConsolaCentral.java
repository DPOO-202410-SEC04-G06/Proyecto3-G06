package consola;

import java.io.IOException;

import javax.swing.JOptionPane;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaCentral extends ConsolaBasica {
	// ############################################ Atributos

	private static ConsolaEmpleadoCorriente cEmpleado;
	private static ConsolaAdministrador cAdmin;
	private static ConsolaCajero cCajero;
	private static ConsolaOperador cOperador;
	private static ConsolaUsuarioCorriente cUsuarioCorriente;
	private static ControladorGaleria controladorGaleria;

	public ConsolaCentral(ControladorGaleria controladorGaleria) {
		ConsolaCentral.controladorGaleria = controladorGaleria;
	}

	// ############################################ Métodos privados

	/**
	 * Método para iniciar sesión de un usuario corriente
	 * 
	 * @throws IOException
	 */
	private void iniciarSesion() throws IOException {
		String iUsername = this.pedirCadenaAlUsuario("Ingrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		boolean result = controladorGaleria.iniciarSesion(iUsername, iPassword);
		System.out.println(result);
		if (result) {
			JOptionPane.showMessageDialog(null,
					"Inicio de sesión exitoso: Bienvenido " + controladorGaleria.usuarioDeLaSesion.getNombre());
			cUsuarioCorriente = new ConsolaUsuarioCorriente(controladorGaleria);
			cUsuarioCorriente.correrConsola();
		} else {
			JOptionPane.showMessageDialog(null, "No se encontró el usuario en el sistema", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		correrAplicacion();
	}

	/**
	 * Valida la existencia de un empleado en la galería
	 * 
	 * @return El empleado buscado o null de lo contrario
	 */
	private Empleado validarEmpleado() {
		String iUsername = this.pedirCadenaAlUsuario("Ingrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		Empleado empleado = controladorGaleria.galeria.buscarEmpleadoUsername(iUsername);

		if (empleado != null) {
			controladorGaleria.iniciarSesion(iUsername, iPassword);
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso: Bienvenido " + empleado.getNombre());
		}

		return empleado;
	}

	/**
	 * Crea un nuevo empleado en la galería
	 * 
	 * @throws IOException
	 */
	private void crearEmpleado() throws IOException {
		String iName = this.pedirCadenaAlUsuario("Ingrese su nombre");
		String iPhone = this.pedirCadenaAlUsuario("Ingrese su número de teléfono");
		String iUsername = this.pedirCadenaAlUsuario("Ingrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		String[] opciones = { "Administrador", "Cajero", "Operador", "Empleado corriente" };

		int input = this.mostrarMenu("Elija el tipo de empleado", opciones);

		int iRol;
		switch (input) {
			case 1:
				iRol = Empleado.ADMIN;
				break;

			case 2:
				iRol = Empleado.CAJERO;
				break;

			case 3:
				iRol = Empleado.OP;
				break;
			case 4:
				iRol = Empleado.CORRIENTE;
				break;

			default:
				iRol = Empleado.CORRIENTE;
				break;
		}

		controladorGaleria.galeria.crearEmpleado(iName, iPhone, iUsername, iPassword, iRol);
		controladorGaleria.salvarGaleria(controladorGaleria.galeria);
		JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		correrAplicacion();
	}

	/**
	 * Método para entrar al portal de empleados e iniciar sesión
	 * 
	 * @throws IOException
	 */
	public void portalEmpleados() throws IOException {
		Empleado empleado = validarEmpleado();

		if (empleado == null) {
			JOptionPane.showMessageDialog(null, "No se encontró el usuario en el sistema", "Error",
					JOptionPane.ERROR_MESSAGE);

			boolean confirmacion = this.pedirConfirmacionAlUsuario("¿Desea crear un nuevo empleado? (Para pruebas)");

			if (confirmacion) {
				crearEmpleado();
				controladorGaleria.salvarGaleria(controladorGaleria.galeria);
			}

			correrAplicacion();
		}

		if (empleado instanceof Administrador) {
			cAdmin = new ConsolaAdministrador(controladorGaleria);
			cAdmin.correrConsola();
		} else if (empleado instanceof Operador) {
			cOperador = new ConsolaOperador(controladorGaleria);
			cOperador.correrConsola();
		} else if (empleado instanceof Cajero) {
			cCajero = new ConsolaCajero(controladorGaleria);
			cCajero.correrConsola();
		} else {
			cEmpleado = new ConsolaEmpleadoCorriente(controladorGaleria);
			cEmpleado.correrConsola();
		}

		correrAplicacion();
	}

	/**
	 * Método para crear un nuevo usuario corriente
	 * 
	 * @throws IOException
	 */
	private void crearNuevoUsuario() throws IOException {
		String iName = this.pedirCadenaAlUsuario("Ingrese su nombre");
		String iPhone = this.pedirCadenaAlUsuario("Ingrese su número de teléfono");
		String iUsername = this.pedirCadenaAlUsuario("Ingrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		controladorGaleria.galeria.crearUsuarioCorriente(iName, iPhone, iUsername, iPassword);
		controladorGaleria.salvarGaleria(controladorGaleria.galeria);
		JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		correrAplicacion();
	}

	// ############################################ Run

	public void correrAplicacion() {
		try {
			controladorGaleria = new ControladorGaleria();
			controladorGaleria.cargarGaleria();

			String[] opcionesMenuPrincipal = { "Iniciar sesión", "Soy empleado", "Crear nuevo usuario", "Salir" };

			int iInput = this.mostrarMenu("Bienvenido al menú principal de la galería", opcionesMenuPrincipal);

			switch (iInput) {
				case 1: // Iniciar sesión
					iniciarSesion();
					break;

				case 2: // Soy empleado
					portalEmpleados();
					break;

				case 3: // Crear nuevo usuario
					crearNuevoUsuario();
					break;

				case 4:
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ############################################ Main

	public static void main(String[] args) {
		ConsolaCentral ca = new ConsolaCentral(controladorGaleria);
		ca.correrAplicacion();
	}
}
