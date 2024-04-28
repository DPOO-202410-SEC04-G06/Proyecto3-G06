package consola;

import controlador.ControladorGaleria;
import galeria.Galeria;
import usuarios.Usuario;

public class ConsolaCentral extends ConsolaBasica
{
	// ############################################ Atributos

	private static ConsolaEmpleadoCorriente cEmpleado;
	private static ConsolaAdministrador cAdmin;
	private static ConsolaCajero cCajero;
	private static ConsolaOperador cOperador;
	private static ConsolaUsuarioCorriente cUsuarioCorriente;
	private static ControladorGaleria controladorGaleria;
	private static Galeria galeria;
	private static Usuario usuarioDeLaSesion;
	
	// ############################################ Metodos privados

	/**
	 * Metodo para iniciar sesión de un usuario corriente
	 */
	private void iniciarSesion()
	{
		String iUsername = this.pedirCadenaAlUsuario("\nIngrese su usuario (username)");
		String iPassword = this.pedirCadenaAlUsuario("Ingrese su contraseña");

		boolean result = galeria.iniciarSesion( iUsername, iPassword );

		if ( result )
		{
			System.out.println( "\nInicio de sesión exitoso" );
			correrAplicacion();
		}

		System.out.println( "\nNo se encontró el usuario en el sistema" );
		correrAplicacion();
	}

	/**
	 * Metodo para entrar al portal de empleados e iniciar sesión
	 */
	private void portalEmpleados()
	{
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

		galeria.crearUsuarioCorriente(iName, iPhone, iUsername, iPassword);

		System.out.println("\nUsuario creado exitosamente");

		correrAplicacion();

	}

	// ############################################ Run

	public void correrAplicacion()
	{

		try 
		{

			controladorGaleria.cargarGaleria();
			galeria = controladorGaleria.galeria;

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
