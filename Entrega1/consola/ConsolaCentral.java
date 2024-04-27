package consola;

import controlador.ControladorGaleria;
import galeria.Galeria;
import usuarios.Usuario;

public class ConsolaCentral 
{
	// ############################################ Atributos

	private static ConsolaEmpleadoCorriente cEmpleado;
	private static ConsolaAdministrador cAdmin;
	private static ConsolaCajero cCajero;
	private static ConsolaOperador cOperador;
	private static ConsolaUsuarioCorriente cUsuarioCorriente;
	private static ControladorGaleria controladorGaleria;
	private static Galeria galeria;
	private static Usuario usuarioActual;
	
	// ############################################ Metodos

	private static void printMenu()
	{

	}


	private static void iniciarSesion( String username, String password )
	{
		Usuario usuario;

		usuarioActual = usuario;
	}

	// ############################################ Main

	public static void main(String[] args) 
	{
		


	}
}
