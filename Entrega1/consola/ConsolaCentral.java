package consola;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaCentral extends ConsolaBasica
{
	// ############################################ Atributos

	private static ConsolaEmpleadoCorriente cEmpleado;
	private static ConsolaAdministrador cAdmin;
	private static ConsolaCajero cCajero;
	private static ConsolaOperador cOperador;
	private static ConsolaUsuarioCorriente cUsuarioCorriente;
	private static ControladorGaleria controladorGaleria;
	
	// ############################################ Metodos privados

	/**
	 * Metodo para iniciar sesión de un usuario corriente
	 */
	private void iniciarSesion()
	{
		String iUsername = this.pedirCadenaAlUsuario("\nIngrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		boolean result = controladorGaleria.iniciarSesion( iUsername, iPassword );

		if ( result )
		{
			System.out.println( "\nInicio de sesión exitoso: Bienvenido "+controladorGaleria.usuarioDeLaSesion.getNombre() );
			cUsuarioCorriente = new ConsolaUsuarioCorriente(controladorGaleria);
			cUsuarioCorriente.correrConsola( );
		}

		System.out.println( "\nNo se encontró el usuario en el sistema" );
		correrAplicacion();
	}

	/**
	 * Valida la existencia de un empleado en la galería
	 * @return El empleado buscado o null de lo contrario
	 */
	private Empleado validarEmpleado()
	{
		String iUsername = this.pedirCadenaAlUsuario("\nIngrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		Empleado empleado = controladorGaleria.galeria.buscarEmpleadoUsername(iUsername);

		if ( empleado != null )
		{
			controladorGaleria.iniciarSesion( iUsername, iPassword );
			System.out.println( "\nInicio de sesión exitoso: Bienvenido " + empleado.getNombre() );
		}

		return empleado;
	}

	/**
	 * Metodo para entrar al portal de empleados e iniciar sesión
	 */
	private void portalEmpleados()
	{
		Empleado empleado = validarEmpleado();

		if ( empleado == null )
		{
			System.out.println( "\nNo se encontró el usuario en el sistema" );
			correrAplicacion();
		}

		if ( empleado instanceof Administrador )
		{
			cAdmin = new ConsolaAdministrador(controladorGaleria);
			// TODO correr consola admin
			cAdmin.correrConsola( );
		}
		else if ( empleado instanceof Operador )
		{
			cOperador = new ConsolaOperador(controladorGaleria);
			// TODO correr consola op
			cOperador.correrConsola( );
		}
		else if ( empleado instanceof Cajero )
		{
			cCajero = new ConsolaCajero(controladorGaleria);
			// TODO correr consola teller
			cCajero.correrConsola( );
		}
		else
		{
			cEmpleado = new ConsolaEmpleadoCorriente(controladorGaleria);
			// TODO correr consola staff
			cEmpleado.correrConsola();
		}

		correrAplicacion();
	}

	/**
	 * Metodo para crear un nuevo usuario corriente
	 */
	private void crearNuevoUsuario()
	{

		String iName = this.pedirCadenaAlUsuario("\nIngrese su nombre");
		String iPhone = this.pedirCadenaAlUsuario("Ingrese su número de teléfono");
		String iUsername = this.pedirCadenaAlUsuario("Ingrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		controladorGaleria.galeria.crearUsuarioCorriente(iName, iPhone, iUsername, iPassword);

		System.out.println("\nUsuario creado exitosamente");

		correrAplicacion();
	}

	// ############################################ Run

	public void correrAplicacion()
	{

		try 
		{

			controladorGaleria.cargarGaleria();
			controladorGaleria.galeria = controladorGaleria.galeria;

			String[] opcionesMenuPrincipal = { "Iniciar sesión", "Soy empleado", "Crear nuevo usuario", "Salir" };

			int iInput = this.mostrarMenu( "Bienvenido al menú principal de la galería", opcionesMenuPrincipal);
			
			switch ( iInput )
			{
				case 1: // Iniciar sesión
				{
					iniciarSesion();
					break;
				}

				case 2: // Soy empleado
				{
					portalEmpleados();
					break;
				}

				case 3: // Crear nuevo usuario
				{
					crearNuevoUsuario();
					break;
				}

				default:
				{
					break;
				}
			}
			
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			
		}

	}

	// ############################################ Main

	public static void main(String[] args) 
	{
		controladorGaleria = new ControladorGaleria();
		ConsolaCentral ca = new ConsolaCentral();
		ca.correrAplicacion();
	}
}
