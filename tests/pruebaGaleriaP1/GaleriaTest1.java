package pruebaGaleriaP1;

import java.io.IOException;

import controlador.ControladorGaleria;
import galeria.Galeria;
import usuarios.Administrador;
import usuarios.Empleado;
import usuarios.Usuario;

public class GaleriaTest1 
{
	private static ControladorGaleria controlador = new ControladorGaleria();
	private static Galeria galeria = controlador.getGaleria();

	public static void main(String[] args) 
	{
		System.out.println( "\n### Prueba persistencia y creación usuarios ###\n" );
		
		// Reset los datos de prueba
		try 
		{
			controlador.salvarGaleria(galeria);
			galeria = controlador.cargarGaleria();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		// Datos prueba persistencia y creación de usuarios
		String nombreAdmin = "Adrian";
		String telefonoAdmin = "111-222-333";
		String usernameAdmin = "adrian123";
		String passwordAdmin = "1234";
		
		String nombreNuevoAdmin = "Harry";
		String telefonoNuevoAdmin = "555-444-333";
		String usernameNuevoAdmin = "harry123";
		String passwordNuevoAdmin = "9090";
		
		String nombreUsuario = "Bob";
		String telefonoUsuario = "000-000-000";
		String usernameUsuario = "bob000";
		String passwordUsuario = "0000";

		int rolAdmin = Empleado.ADMIN;
		int rolCorriente = Empleado.CORRIENTE;

		boolean resultadoPrueba;

		// ###################################################################################################################
		// ############################# Prueba de creación y cambio de usuarios/salvar y cargar #############################
		// ###################################################################################################################

		System.out.println( "Start: Prueba de creación y cambio de usuarios/salvar y cargar" );

		galeria.crearEmpleado(nombreAdmin, telefonoAdmin, usernameAdmin, passwordAdmin, rolAdmin);
		galeria.crearUsuarioCorriente(nombreUsuario, telefonoUsuario, usernameUsuario, passwordUsuario);
		Usuario admin = galeria.buscarUsuarioUsername( usernameAdmin );
		

		boolean pruebaAdmin = (admin instanceof Administrador);
		System.out.println( "	>> Galeria: Se creó el administrador = " + pruebaAdmin );
		resultadoPrueba = pruebaAdmin;


		boolean pruebaGuardarAdmin = galeria.nombreAdministrador.equals(usernameAdmin);
		System.out.println( "	>> Galeria: Se almacena el nombre del administrador = " + pruebaGuardarAdmin  );
		resultadoPrueba = resultadoPrueba && pruebaAdmin;


		boolean pruebaUsuario = galeria.buscarUsuarioUsername(usernameUsuario) != null;
		System.out.println( "	>> Galeria: Se creó el usuario corriente = " + pruebaUsuario  );
		resultadoPrueba = resultadoPrueba && pruebaUsuario;
		
		// Guardar el estado para verificar información
		try 
		{
			controlador.salvarGaleria(galeria);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		// Deberia reemplazar el current admin y actualizar los datos en la galeria
		galeria.crearEmpleado(nombreNuevoAdmin, telefonoNuevoAdmin, usernameNuevoAdmin, passwordNuevoAdmin, rolAdmin);
		

		boolean pruebaCambioRol = galeria.nombreAdministrador.equals(usernameNuevoAdmin);
		System.out.println( "	>> Galeria: Cambio de administrador:" + pruebaCambioRol );
		resultadoPrueba = resultadoPrueba && pruebaCambioRol;

		
		Usuario nuevoAdmin = galeria.buscarUsuarioUsername(usernameNuevoAdmin);
		Usuario oldAdmin = galeria.buscarUsuarioUsername(usernameAdmin);
		

		boolean pruebaOldAdmin = ( (Empleado) oldAdmin).getRol() ==  rolCorriente;
		System.out.println( "	>> Galeria: Cambio de rol del antiguo administrador = " + pruebaOldAdmin );
		resultadoPrueba = resultadoPrueba && pruebaOldAdmin;


		boolean pruebaNewAdmin = ( (Empleado) nuevoAdmin).getRol() == rolAdmin;

		System.out.println( "	>> Galeria: Asignación apropiada del rol del nuevo administrador = " + pruebaNewAdmin );


		// Cargar el estado anterior de la galeria
		try 
		{
			galeria = controlador.cargarGaleria();
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}


		boolean pruebaSalvadoYCarga = galeria.nombreAdministrador.equals(usernameAdmin);
		System.out.println( "	>> Galeria: Salvar y cargar datos = " + pruebaSalvadoYCarga );
		resultadoPrueba = resultadoPrueba && pruebaSalvadoYCarga;


		System.out.println( "End: Resultado prueba = " + resultadoPrueba + "\n");

		
		// Guardar estado actual de la galeria
		try 
		{
			controlador.salvarGaleria(galeria);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
