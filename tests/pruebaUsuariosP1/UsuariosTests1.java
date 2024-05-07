package pruebaUsuariosP1;

import java.io.IOException;
import java.util.*;

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

public class UsuariosTests1 
{
	private static ControladorGaleria controlador = new ControladorGaleria();
	private static Galeria galeria = controlador.getGaleria();

	public static void main(String[] args) 
	{
		
		System.out.println( "\n### Prueba requerimientos usuario ###\n" );
		
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
		
		// Datos prueba Admin
		String nombreAdmin = "Adrian";
		String telefonoAdmin = "111-222-333";
		String usernameAdmin = "adrian123";
		String passwordAdmin = "1234";
		int rolAdmin = Empleado.ADMIN;
		
		// Datos prueba Cajero
		String nombreCajero = "Harry";
		String telefonoCajero = "555-444-333";
		String usernameCajero = "harry123";
		String passwordCajero = "9090";
		int rolCajero = Empleado.CAJERO;
		
		// Datos prueba Operador
		String nombreOperador = "Juan";
		String telefonoOperador = "123-456-789";
		String usernameOperador = "juan123";
		String passwordOperador = "9876";
		int rolOperador = Empleado.OP;
		
		// Datos prueba UsuarioCorriente1
		String nombreUsuario1 = "Bob";
		String telefonoUsuario1 = "000-000-000";
		String usernameUsuario1 = "bob000";
		String passwordUsuario1 = "0000";
		
		// Datos prueba UsuarioCorriente 2
		String nombreUsuario2 = "Carlos";
		String telefonoUsuario2 = "111-111-111";
		String usernameUsuario2 = "carlos111";
		String passwordUsuario2 = "1111";
		
		// Creacion de usuarios de preuba
		galeria.crearEmpleado(nombreAdmin, telefonoAdmin, usernameAdmin, passwordAdmin, rolAdmin); // new Admin
		galeria.crearEmpleado(nombreCajero, telefonoCajero, usernameCajero, passwordCajero, rolCajero); // new Cajero
		galeria.crearEmpleado(nombreOperador, telefonoOperador, usernameOperador, passwordOperador, rolOperador); // new Operador
		galeria.crearUsuarioCorriente(nombreUsuario1, telefonoUsuario1, usernameUsuario1, passwordUsuario1); // new UC1
		galeria.crearUsuarioCorriente(nombreUsuario2, telefonoUsuario2, usernameUsuario2, passwordUsuario2); // new UC2
		
		// get Usuarios
		Administrador administrador = (Administrador) galeria.buscarUsuarioUsername( galeria.nombreAdministrador );
		Cajero cajero = (Cajero) galeria.buscarUsuarioUsername( galeria.nombreCajero );
		Operador operador = (Operador) galeria.buscarUsuarioUsername( galeria.nombreOperador );
		UsuarioCorriente usuario1 = (UsuarioCorriente) galeria.buscarUsuarioUsername( usernameUsuario1 );
		UsuarioCorriente usuario2 = (UsuarioCorriente) galeria.buscarUsuarioUsername( usernameUsuario2 );


		// ###############################################################################################
		// ############################# Prueba de verificacion/compra/venta #############################
		// ###############################################################################################
		
		boolean resultadoPrueba1;
		System.out.println( "Start: Prueba de verificacion/compra/venta" );
		
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
		System.out.println("	>> Administrador: Verificar U1 comprador = " + resultadoMonto1 );
		resultadoPrueba1 = resultadoMonto1;
		

		administrador.verificarVendedor(usuario1);
		boolean resultadoVendedor1 = usuario1.isVerifVendedor() == true;
		System.out.println("	>> Administrador: Verificar U1 vendedor = " + resultadoVendedor1 );
		resultadoPrueba1 =  resultadoPrueba1 && resultadoVendedor1;
		

		int montoU2 = 4000;
		administrador.verificarComprador(usuario2, montoU2);
		boolean resultadoMonto2 = usuario2.getMontoMax() == montoU2;
		System.out.println("	>> Administrador: Verificar U2 comprador = " + resultadoMonto2 );
		resultadoPrueba1 = resultadoPrueba1 && resultadoMonto2;
		

		administrador.verificarVendedor(usuario2);
		boolean resultadoVendedor2 = usuario2.isVerifVendedor() == true;
		System.out.println("	>> Administrador: Verificar U2 vendedor = " + resultadoVendedor2 );
		resultadoPrueba1 = resultadoPrueba1 && resultadoVendedor2;


		Pieza piezaU1 = usuario1.nuevaPiezaParaConsignar(tituloCuadro, tipoCuadro, autoresCuadro, anioCuadro, ciudadCuadro, paisCuadro, otrosAtributosCuadro);
		boolean resultadoPieza1 = piezaU1 != null;
		System.out.println("	>> UsuarioCorriente1: Crear pieza = " + resultadoPieza1 );
		resultadoPrueba1 = resultadoPrueba1 && resultadoVendedor2;
		

		usuario1.consignarPieza(piezaU1, precioVentaCuadro, fechaSalidaGaleriaPiezaCuadro, administrador);
		boolean resultadoConsignarPieza = administrador.getPiezasDeEntrada().size() != 0;
		System.out.println("	>> UsuarioCorriente1: Consignar pieza = " + resultadoConsignarPieza);
		resultadoPrueba1 = resultadoPrueba1 && resultadoConsignarPieza;

		
		administrador.agregarPieza(piezaU1, estadoCuadro, fechaSalidaGaleriaPiezaCuadro, galeria.getInventarioPiezas(), galeria);
		boolean resultadoAgregarPiezaGaleria = galeria.consultarEstadoPieza(tituloCuadro) == estadoCuadro;
		System.out.println("	>> Administrador: Verificar entrada de pieza a la galeria = " + resultadoAgregarPiezaGaleria);
		resultadoPrueba1 = resultadoPrueba1 && resultadoAgregarPiezaGaleria;

		
		usuario2.aplicarComprarPieza(tituloCuadro, galeria);
		boolean resultadoAplicarComprar = galeria.getPiezasEnVenta().get(tituloCuadro).get(Galeria.COMPRADOR) != null;
		System.out.println("	>> UsuarioCorriente2: Aplicar para la compra de una pieza = " + resultadoAplicarComprar );
		resultadoPrueba1 = resultadoPrueba1 && resultadoAplicarComprar;

		
		// TODO Crear nuevo metodo administrador para realizar este proceso
		ArrayList<String> listaAdquisicionesPendientes = administrador.getVerificacionesCompraPiezas();
		String tituloPiezaAdquisicion = listaAdquisicionesPendientes.get(0);
		listaAdquisicionesPendientes.remove(0);

		
		// Si el Usuario2 tiene la pieza que compró y el Usuario1 ya no es dueño de la pieza, la prueba fue exitosa
		administrador.registrarAdquisicionPieza( tituloPiezaAdquisicion, galeria.getMapaUsuariosCorrientes(), galeria, galeria.getPortalPagos(), cajero);
		boolean resultadoUsuario1 = !usuario1.getPiezasActuales().containsKey(tituloCuadro);
		boolean resultadoUsuario2 = usuario2.getPiezasActuales().containsKey(tituloCuadro);
		boolean resultadoGaleria = galeria.consultarEstadoPieza(tituloPiezaAdquisicion) == Inventario.PASADA;
		boolean resultadoAdquisicionPieza = resultadoUsuario1 && resultadoUsuario2 && resultadoGaleria;
		System.out.println("	>> Administrador: Registrar venta & salida de pieza del inventario = " + resultadoAdquisicionPieza );
		resultadoPrueba1 = resultadoPrueba1 && resultadoAdquisicionPieza;
		

		System.out.println( "End: Resultado prueba = " + resultadoPrueba1 + "\n" );
		
		System.out.println( "############################################################################" );

		// ###############################################################################################
		// ############################# Prueba de registro de transacciones #############################
		// ###############################################################################################
		
		boolean resultadoPrueba2;
		System.out.println( "\nStart: Prueba de registro de transacciones" );
		

		// TODO Crear nuevo metodo cajero para realizar este proceso
		ArrayList<Transaccion> listaTransaccionesPendientes = cajero.getTransaccionesPendientes();
		Transaccion transaccionPendiente = listaTransaccionesPendientes.get(0);
		listaTransaccionesPendientes.remove(0);


		cajero.registrarTransaccion(transaccionPendiente, transaccionPendiente.getFecha(), transaccionPendiente.getCodigoTransaccion(), galeria.getPortalPagos());
		Transaccion transaccionBuscada = galeria.getPortalPagos().buscarTransaccionCodigo(transaccionPendiente.getCodigoTransaccion());
		boolean resultadoRegistrarTransaccion = transaccionBuscada != null;
		System.out.println( "	>> Cajero: Registrar transacción U1->U2 (sin subasta) = " + resultadoRegistrarTransaccion );
		resultadoPrueba2 = resultadoRegistrarTransaccion;


		System.out.println( "End: Resultado prueba = " + resultadoPrueba2 + "\n" );

		System.out.println( "############################################################################" );
		

		// ##########################################################################
		// ############################# Prueba subasta #############################
		// ##########################################################################
		
		boolean resultadoPrueba3;
		System.out.println( "\nStart: Prueba subasta" );
		
		
		int precioInicialSubasta = 10;
		int precioMinimoSubasta = 15;
		operador.nuevaSubasta(tituloCuadro, piezaU1, precioInicialSubasta, precioMinimoSubasta, galeria.getInventarioPiezas());
		boolean resultadoCambioEstadoSubasta = galeria.consultarEstadoPieza(tituloCuadro) == Inventario.SUBASTA;
		boolean resultadoPresenciaPiezaMapaSubasta = galeria.getInventarioPiezas().getSubastaActiva().containsKey(tituloCuadro);
		boolean resultadoNuevaSubasta = resultadoCambioEstadoSubasta && resultadoPresenciaPiezaMapaSubasta;
		System.out.println( "	>> Operador: Empezar subasta = " + resultadoNuevaSubasta );
		resultadoPrueba3 = resultadoNuevaSubasta;


		administrador.verificarOfertaSubasta(usuario1.getUsername(), operador);
		boolean resultadoVerificacionUsuarioSubasta = operador.getUsuariosVerificadosSubasta().contains(usuario1.getUsername());
		System.out.println( "	>> Administrador: Registrar U1 para subasta = " + resultadoVerificacionUsuarioSubasta );
		resultadoPrueba3 = resultadoPrueba3 && resultadoVerificacionUsuarioSubasta;


		int ofertaUsuario = 20;
		usuario1.nuevaOfertaSubasta(tituloCuadro, ofertaUsuario, operador);
		boolean resultadoGuardarOferta = operador.getManejoOfertas().get(tituloCuadro).first() == ofertaUsuario;
		boolean resultadoGuardarOfertaUsuario = operador.getOfertasUsuario().get(tituloCuadro).get(ofertaUsuario) != null;
		boolean resultadoNuevaOferta = resultadoGuardarOferta && resultadoGuardarOfertaUsuario;
		System.out.println( "	>> UsuarioCorriente1: Nueva oferta = " + resultadoNuevaOferta );
		resultadoPrueba3 = resultadoPrueba3 && resultadoNuevaOferta;


		operador.finalizarSubasta(tituloCuadro, piezaU1, galeria.getInventarioPiezas(), cajero, galeria.getPortalPagos(), administrador);
		boolean resultadoNuevoPropietario = usuario1.getPiezasActuales().containsKey(tituloCuadro);
		System.out.println( "	>> Operador: Finalizar subasta = " + resultadoNuevoPropietario );
		resultadoPrueba3 = resultadoPrueba3 && resultadoNuevoPropietario;


		cajero.registrarTransaccion(transaccionPendiente, transaccionPendiente.getFecha(), transaccionPendiente.getCodigoTransaccion(), galeria.getPortalPagos());
		Transaccion transaccionBuscada2 = galeria.getPortalPagos().buscarTransaccionCodigo(transaccionPendiente.getCodigoTransaccion());
		boolean resultadoRegistrarTransaccion2 = transaccionBuscada2 != null;
		System.out.println( "	>> Cajero: Registrar transacción U2->U1 (con subasta) = " + resultadoRegistrarTransaccion2 );
		resultadoPrueba3 = resultadoPrueba3 && resultadoRegistrarTransaccion2;


		System.out.println( "End: Resultado prueba = " + resultadoPrueba3 + "\n" );


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
