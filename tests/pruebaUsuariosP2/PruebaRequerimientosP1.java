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
import galeria.Inventario;
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
		
		// ###############################################################################################
		// ############################# Prueba de verificacion/compra/venta #############################
		// ###############################################################################################
		
		boolean resultadoPrueba1;
		
		String tituloCuadro = "dummy_cuadro";
		String tipoCuadro = Pieza.CUADRO;
		int estadoCuadro = Inventario.BODEGA;

		ArrayList<String> autoresCuadro = new ArrayList<String>();
		String nombreAutor1 = "autor1";
		autoresCuadro.add(nombreAutor1);

		String anioCuadro = "1999";
		String ciudadCuadro = "dummy_ciudad";
		String paisCuadro = "dummy_pais";

		HashMap<String, String> otrosAtributosCuadro = new HashMap<String, String>();
		String tecnicaCuadro = "dummy_tecnica";
		String altoCuadro = "1";
		String anchoCuadro = "1";
		String enmarcacionCuadro = "true";
		otrosAtributosCuadro.put(Cuadro.TECNICA, tecnicaCuadro);
		otrosAtributosCuadro.put(Cuadro.ALTO, altoCuadro);
		otrosAtributosCuadro.put(Cuadro.ANCHO, anchoCuadro);
		otrosAtributosCuadro.put(Cuadro.ENMARCACION, enmarcacionCuadro);

		int precioVentaCuadro = 1000;
		Date fechaSalidaGaleriaPiezaCuadro = new Date();


		int montoU1 = 5000;
		administrador.verificarComprador(usuario1, montoU1);
		boolean resultadoMonto1 = usuario1.getMontoMax() == montoU1;
		//System.out.println("	>> Administrador: Verificar U1 comprador = " + resultadoMonto1 );
		assertTrue(resultadoMonto1, "El monto máximo deberia ser 5000");
		resultadoPrueba1 = resultadoMonto1;
		

		administrador.verificarVendedor(usuario1);
		boolean resultadoVendedor1 = usuario1.isVerifVendedor() == true;
		//System.out.println("	>> Administrador: Verificar U1 vendedor = " + resultadoVendedor1 );
		assertTrue(resultadoVendedor1, "El usuario 1 debería ser un vendedor verificado");
		resultadoPrueba1 =  resultadoPrueba1 && resultadoVendedor1;
		

		int montoU2 = 4000;
		administrador.verificarComprador(usuario2, montoU2);
		boolean resultadoMonto2 = usuario2.getMontoMax() == montoU2;
		// System.out.println("	>> Administrador: Verificar U2 comprador = " + resultadoMonto2 );
		assertTrue(resultadoMonto2, "El monto máximo debería ser 4000");
		resultadoPrueba1 = resultadoPrueba1 && resultadoMonto2;
		

		administrador.verificarVendedor(usuario2);
		boolean resultadoVendedor2 = usuario2.isVerifVendedor() == true;
		//System.out.println("	>> Administrador: Verificar U2 vendedor = " + resultadoVendedor2 );
		assertTrue(resultadoVendedor2, "El usuario 2 debería ser un vendedor verificado");
		resultadoPrueba1 = resultadoPrueba1 && resultadoVendedor2;


		Pieza piezaU1 = usuario1.nuevaPiezaParaConsignar(tituloCuadro, tipoCuadro, autoresCuadro, anioCuadro, ciudadCuadro, paisCuadro, otrosAtributosCuadro);
		boolean resultadoPieza1 = piezaU1 != null;
		//System.out.println("	>> UsuarioCorriente1: Crear pieza = " + resultadoPieza1 );
		assertTrue(resultadoPieza1, "La pieza creada debería no se null");
		resultadoPrueba1 = resultadoPrueba1 && resultadoVendedor2;
		

		usuario1.consignarPieza(piezaU1, precioVentaCuadro, fechaSalidaGaleriaPiezaCuadro, administrador);
		boolean resultadoConsignarPieza = administrador.getPiezasDeEntrada().size() != 0;
		//System.out.println("	>> UsuarioCorriente1: Consignar pieza = " + resultadoConsignarPieza);
		assertTrue(resultadoConsignarPieza, "Debería haber una pieza en la cola de entrada a la galería");
		resultadoPrueba1 = resultadoPrueba1 && resultadoConsignarPieza;

		
		administrador.agregarPieza(piezaU1, estadoCuadro, fechaSalidaGaleriaPiezaCuadro, galeria.getInventarioPiezas(), galeria);
		boolean resultadoAgregarPiezaGaleria = galeria.consultarEstadoPieza(tituloCuadro) == estadoCuadro;
		//System.out.println("	>> Administrador: Verificar entrada de pieza a la galeria = " + resultadoAgregarPiezaGaleria);
		assertTrue(resultadoAgregarPiezaGaleria, "El estado de la pieza debería ser " + estadoCuadro);
		resultadoPrueba1 = resultadoPrueba1 && resultadoAgregarPiezaGaleria;

		
		usuario2.aplicarComprarPieza(tituloCuadro, galeria, administrador);
		boolean resultadoAplicarComprar = galeria.getPiezasEnVenta().get(tituloCuadro).get(Galeria.COMPRADOR) != null;
		//System.out.println("	>> UsuarioCorriente2: Aplicar para la compra de una pieza = " + resultadoAplicarComprar );
		assertTrue(resultadoAplicarComprar, "La pieza debería estar en venta");
		resultadoPrueba1 = resultadoPrueba1 && resultadoAplicarComprar;

		
		// TODO Crear nuevo metodo administrador para realizar este proceso
		ArrayList<String> listaAdquisicionesPendientes = administrador.getComprasPendientes();
		String tituloPiezaAdquisicion = listaAdquisicionesPendientes.get(0);
		listaAdquisicionesPendientes.remove(0);

		
		// Si el Usuario2 tiene la pieza que compró y el Usuario1 ya no es dueño de la pieza, la prueba fue exitosa
		administrador.registrarAdquisicionPieza( tituloPiezaAdquisicion, galeria.getMapaUsuariosCorrientes(), galeria, galeria.getPortalPagos(), cajero);
		boolean resultadoUsuario1 = !usuario1.getPiezasActuales().containsKey(tituloCuadro);
		boolean resultadoUsuario2 = usuario2.getPiezasActuales().containsKey(tituloCuadro);
		boolean resultadoGaleria = galeria.consultarEstadoPieza(tituloPiezaAdquisicion) == Inventario.PASADA;
		boolean resultadoAdquisicionPieza = resultadoUsuario1 && resultadoUsuario2 && resultadoGaleria;
		// System.out.println("	>> Administrador: Registrar venta & salida de pieza del inventario = " + resultadoAdquisicionPieza );
		assertTrue(resultadoAdquisicionPieza, "La pieza debería cambiar de usuario");
		resultadoPrueba1 = resultadoPrueba1 && resultadoAdquisicionPieza;
		

		//System.out.println( "End: Resultado prueba = " + resultadoPrueba1 + "\n" );
		
		//System.out.println( "############################################################################" );

		// ###############################################################################################
		// ############################# Prueba de registro de transacciones #############################
		// ###############################################################################################
		
		boolean resultadoPrueba2;
		//System.out.println( "\nStart: Prueba de registro de transacciones" );
		

		// TODO Crear nuevo metodo cajero para realizar este proceso
		ArrayList<Transaccion> listaTransaccionesPendientes = cajero.getTransaccionesPendientes();
		Transaccion transaccionPendiente = listaTransaccionesPendientes.get(0);
		listaTransaccionesPendientes.remove(0);


		cajero.registrarTransaccion(transaccionPendiente, transaccionPendiente.getFecha(), transaccionPendiente.getCodigoTransaccion(), galeria.getPortalPagos());
		Transaccion transaccionBuscada = galeria.getPortalPagos().buscarTransaccionCodigo(transaccionPendiente.getCodigoTransaccion());
		boolean resultadoRegistrarTransaccion = transaccionBuscada != null;
		//System.out.println( "	>> Cajero: Registrar transacción U1->U2 (sin subasta) = " + resultadoRegistrarTransaccion );
		assertTrue(resultadoRegistrarTransaccion, "La transacción debería estar registrada en el sistema");
		resultadoPrueba2 = resultadoRegistrarTransaccion;


		//System.out.println( "End: Resultado prueba = " + resultadoPrueba2 + "\n" );

		//System.out.println( "############################################################################" );
		

		// ##########################################################################
		// ############################# Prueba subasta #############################
		// ##########################################################################
		
		boolean resultadoPrueba3;
		//System.out.println( "\nStart: Prueba subasta" );
		
		
		int precioInicialSubasta = 10;
		int precioMinimoSubasta = 15;
		operador.nuevaSubasta(tituloCuadro, piezaU1, precioInicialSubasta, precioMinimoSubasta, galeria.getInventarioPiezas());
		boolean resultadoCambioEstadoSubasta = galeria.consultarEstadoPieza(tituloCuadro) == Inventario.SUBASTA;
		boolean resultadoPresenciaPiezaMapaSubasta = galeria.getInventarioPiezas().getSubastaActiva().containsKey(tituloCuadro);
		boolean resultadoNuevaSubasta = resultadoCambioEstadoSubasta && resultadoPresenciaPiezaMapaSubasta;
		//System.out.println( "	>> Operador: Empezar subasta = " + resultadoNuevaSubasta );
		assertTrue(resultadoNuevaSubasta, "La pieza debería estar subastada");
		resultadoPrueba3 = resultadoNuevaSubasta;


		administrador.verificarOfertaSubasta(usuario1.getUsername(), operador);
		boolean resultadoVerificacionUsuarioSubasta = operador.getUsuariosVerificadosSubasta().contains(usuario1.getUsername());
		//System.out.println( "	>> Administrador: Registrar U1 para subasta = " + resultadoVerificacionUsuarioSubasta );
		assertTrue(resultadoVerificacionUsuarioSubasta, "Debería haber un usuario registrado en la subasta");
		resultadoPrueba3 = resultadoPrueba3 && resultadoVerificacionUsuarioSubasta;


		int ofertaUsuario = 20;
		usuario1.nuevaOfertaSubasta(tituloCuadro, ofertaUsuario, operador);
		boolean resultadoGuardarOferta = operador.getManejoOfertas().get(tituloCuadro).first() == ofertaUsuario;
		boolean resultadoGuardarOfertaUsuario = operador.getOfertasUsuario().get(tituloCuadro).get(ofertaUsuario) != null;
		boolean resultadoNuevaOferta = resultadoGuardarOferta && resultadoGuardarOfertaUsuario;
		//System.out.println( "	>> UsuarioCorriente1: Nueva oferta = " + resultadoNuevaOferta );
		assertTrue(resultadoNuevaOferta, "Debería haber una nueva oferta en la subasta");
		resultadoPrueba3 = resultadoPrueba3 && resultadoNuevaOferta;


		operador.finalizarSubasta(tituloCuadro, piezaU1, galeria.getInventarioPiezas(), cajero, galeria.getPortalPagos(), administrador);
		boolean resultadoNuevoPropietario = usuario1.getPiezasActuales().containsKey(tituloCuadro);
		//System.out.println( "	>> Operador: Finalizar subasta = " + resultadoNuevoPropietario );
		assertTrue(resultadoNuevoPropietario, "El usuario debería tener la pieza que compró");
		resultadoPrueba3 = resultadoPrueba3 && resultadoNuevoPropietario;


		cajero.registrarTransaccion(transaccionPendiente, transaccionPendiente.getFecha(), transaccionPendiente.getCodigoTransaccion(), galeria.getPortalPagos());
		Transaccion transaccionBuscada2 = galeria.getPortalPagos().buscarTransaccionCodigo(transaccionPendiente.getCodigoTransaccion());
		boolean resultadoRegistrarTransaccion2 = transaccionBuscada2 != null;
		//System.out.println( "	>> Cajero: Registrar transacción U2->U1 (con subasta) = " + resultadoRegistrarTransaccion2 );
		assertTrue(resultadoRegistrarTransaccion2, "La transacción debería estar registrada");
		resultadoPrueba3 = resultadoPrueba3 && resultadoRegistrarTransaccion2;
		
	}

}
