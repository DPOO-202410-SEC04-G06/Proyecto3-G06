package pruebaUsuariosP2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controlador.ControladorGaleria;
import galeria.Galeria;
import pagos.Transaccion;
import piezas.Cuadro;
import piezas.Pieza;
import usuarios.Administrador;
import usuarios.Cajero;
import usuarios.Empleado;
import usuarios.Operador;
import usuarios.UsuarioCorriente;

class PruebaRequerimientosP1 {

	private Galeria galeria;
	
	// Datos prueba Admin
	String nombreAdmin;
	String telefonoAdmin;
	String usernameAdmin;
	String passwordAdmin;
	int rolAdmin;
	
	// Datos prueba Cajero
	String nombreCajero;
	String telefonoCajero;
	String usernameCajero;
	String passwordCajero;
	int rolCajero;
	
	// Datos prueba Operador
	String nombreOperador;
	String telefonoOperador;
	String usernameOperador;
	String passwordOperador ;
	int rolOperador ;
	
	// Datos prueba UsuarioCorriente1
	String nombreUsuario1 ;
	String telefonoUsuario1 ;
	String usernameUsuario1;
	String passwordUsuario1 ;
	
	// Datos prueba UsuarioCorriente 2
	String nombreUsuario2 ;
	String telefonoUsuario2;
	String usernameUsuario2 ;
	String passwordUsuario2 ;
	
	Administrador administrador ;
	Cajero cajero ;
	Operador operador ;
	UsuarioCorriente usuario1 ;
	UsuarioCorriente usuario2 ;

	@BeforeEach
	void setUp() throws Exception 
	{
		galeria = new Galeria();
		
		
		// Datos prueba Admin
		 nombreAdmin = "Adrian";
		 telefonoAdmin = "111-222-333";
		 usernameAdmin = "adrian123";
		 passwordAdmin = "1234";
		 rolAdmin = Empleado.ADMIN;
		
		// Datos prueba Cajero
		 nombreCajero = "Harry";
		 telefonoCajero = "555-444-333";
		 usernameCajero = "harry123";
		 passwordCajero = "9090";
		 rolCajero = Empleado.CAJERO;
		
		// Datos prueba Operador
		 nombreOperador = "Juan";
		 telefonoOperador = "123-456-789";
		 usernameOperador = "juan123";
		 passwordOperador = "9876";
		 rolOperador = Empleado.OP;
		
		// Datos prueba UsuarioCorriente1
		 nombreUsuario1 = "Bob";
		 telefonoUsuario1 = "000-000-000";
		 usernameUsuario1 = "bob000";
		 passwordUsuario1 = "0000";
		
		// Datos prueba UsuarioCorriente 2
		 nombreUsuario2 = "Carlos";
		 telefonoUsuario2 = "111-111-111";
		 usernameUsuario2 = "carlos111";
		 passwordUsuario2 = "1111";
		
		// Creacion de usuarios de preuba
		galeria.crearEmpleado(nombreAdmin, telefonoAdmin, usernameAdmin, passwordAdmin, rolAdmin); // new Admin
		galeria.crearEmpleado(nombreCajero, telefonoCajero, usernameCajero, passwordCajero, rolCajero); // new Cajero
		galeria.crearEmpleado(nombreOperador, telefonoOperador, usernameOperador, passwordOperador, rolOperador); // new Operador
		galeria.crearUsuarioCorriente(nombreUsuario1, telefonoUsuario1, usernameUsuario1, passwordUsuario1); // new UC1
		galeria.crearUsuarioCorriente(nombreUsuario2, telefonoUsuario2, usernameUsuario2, passwordUsuario2); // new UC2
		
		// get Usuarios
		 administrador = (Administrador) galeria.buscarUsuarioUsername( galeria.nombreAdministrador );
		 cajero = (Cajero) galeria.buscarUsuarioUsername( galeria.nombreCajero );
		 operador = (Operador) galeria.buscarUsuarioUsername( galeria.nombreOperador );
		 usuario1 = (UsuarioCorriente) galeria.buscarUsuarioUsername( usernameUsuario1 );
		 usuario2 = (UsuarioCorriente) galeria.buscarUsuarioUsername( usernameUsuario2 );

	}

	@Test
	void test() 
	{
		
	}

}
