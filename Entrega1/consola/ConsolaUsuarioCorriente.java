package consola;

import java.io.IOException;
import java.util.*;

import controlador.ControladorGaleria;
import pagos.PortalPagos;
import piezas.*;
import usuarios.*;

public class ConsolaUsuarioCorriente extends ConsolaBasica 
{
	// ############################################ Atributos
	
	ControladorGaleria controladorGaleria;
	String nombreUsuario;
	String nombreAdmin;
	String nombreCajero;
	String nombreOperador;
	UsuarioCorriente usuario;
	
	// ############################################ Constructor
	
	public ConsolaUsuarioCorriente( ControladorGaleria controladorGaleria ) 
	{
		super();
		this.controladorGaleria = controladorGaleria;
		this.nombreUsuario = controladorGaleria.usuarioDeLaSesion.getNombre();
		this.usuario = (UsuarioCorriente) controladorGaleria.usuarioDeLaSesion;
		this.nombreAdmin = controladorGaleria.galeria.nombreAdministrador;
		this.nombreCajero = controladorGaleria.galeria.nombreCajero;
		this.nombreOperador = controladorGaleria.galeria.nombreOperador;
	}
	
	// ############################################ Metodos
	
	/**
	 * Pide una lista de autores al usuario
	 * @return la lista de autores de una pieza
	 */
	private ArrayList<String> pedirAutores()
	{
		ArrayList<String> listaAutores = new ArrayList<String>();
		
		String autor = this.pedirCadenaAlUsuario("Ingrese el nombre del autor");
		
		listaAutores.add(autor);
		
		boolean stop = false;
		
		while ( !stop )
		{
			
			boolean otro = this.pedirConfirmacionAlUsuario("Desea agregar otro autor?");
			
			if ( otro )
			{
				autor = this.pedirCadenaAlUsuario("Ingrese el nombre del autor");
				listaAutores.add(autor);
			}
			else
			{
				stop = true;
			}
			
		}
		return listaAutores;	
	}
	
	/**
	 * Pide la informacion necesaria para crear un nuevo cuadro
	 * @param titulo
	 * @param tipo
	 * @param autores
	 * @param anio
	 * @param ciudad
	 * @param pais
	 * @return El cuadro creado
	 */
	private Pieza nuevoCuadro(String titulo, String tipo, ArrayList<String> autores, String anio, String ciudad, String pais)
	{
		HashMap<String, String> datos = new HashMap<String, String>();
		
		String a1 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Cuadro.ALTO);
		datos.put(Cuadro.ALTO, a1);
		String a2 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Cuadro.ANCHO);
		datos.put(Cuadro.ANCHO, a2);
		String a3 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion ( true o false ): " + Cuadro.ENMARCACION);
		datos.put(Cuadro.ENMARCACION, a3);
		String a4 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Cuadro.TECNICA);
		datos.put(Cuadro.TECNICA, a4);
		
		return usuario.nuevaPiezaParaConsignar(titulo, tipo, autores, anio, ciudad, pais, datos);
	}
	
	/**
	 * Pide la informacion necesaria para crear una nueva escultura
	 * @param titulo
	 * @param tipo
	 * @param autores
	 * @param anio
	 * @param ciudad
	 * @param pais
	 * @return La nueva escultura
	 */
	private Pieza nuevaEscultura(String titulo, String tipo, ArrayList<String> autores, String anio, String ciudad, String pais)
	{
		HashMap<String, String> datos = new HashMap<String, String>();
		
		String a1 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Escultura.ALTO);
		datos.put(Escultura.ALTO, a1);
		String a2 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Escultura.ANCHO);
		datos.put(Escultura.ANCHO, a2);
		String a3 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Escultura.LARGO);
		datos.put(Escultura.LARGO, a3);
		String a4 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Escultura.MATERIALES);
		datos.put(Escultura.MATERIALES, a4);
		String a5 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion ( true o false): " + Escultura.USO_ELECTRICIDAD);
		datos.put(Escultura.USO_ELECTRICIDAD, a5);
		
		return usuario.nuevaPiezaParaConsignar(titulo, tipo, autores, anio, ciudad, pais, datos);
	}
	
	/**
	 * Pide la informacion necesaria para crear un nuevo cortometraje
	 * @param titulo
	 * @param tipo
	 * @param autores
	 * @param anio
	 * @param ciudad
	 * @param pais
	 * @return El cortometraje
	 */
	private Pieza nuevoCortometraje(String titulo, String tipo, ArrayList<String> autores, String anio, String ciudad, String pais)
	{
		HashMap<String, String> datos = new HashMap<String, String>();
		
		String a1 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Cortometraje.DURACION);
		datos.put(Cortometraje.DURACION, a1);
		String a2 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Cortometraje.GENERO);
		datos.put(Cortometraje.GENERO, a2);
		
		return usuario.nuevaPiezaParaConsignar(titulo, tipo, autores, anio, ciudad, pais, datos);
	}
	
	/**
	 * Pide la informacion necesaria para crear una nueva fotografia
	 * @param titulo
	 * @param tipo
	 * @param autores
	 * @param anio
	 * @param ciudad
	 * @param pais
	 * @return La fotografia
	 */
	private Pieza nuevaFotografia(String titulo, String tipo, ArrayList<String> autores, String anio, String ciudad, String pais)
	{
		HashMap<String, String> datos = new HashMap<String, String>();
		
		String a1 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Fotografia.ALTO);
		datos.put(Fotografia.ALTO, a1);
		String a2 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion: " + Fotografia.ANCHO);
		datos.put(Fotografia.ANCHO, a2);
		String a3 = this.pedirCadenaAlUsuario("Ingrese la siguiente informacion (true o false): " + Fotografia.COLOR);
		datos.put(Fotografia.COLOR, a3);
		
		return usuario.nuevaPiezaParaConsignar(titulo, tipo, autores, anio, ciudad, pais, datos);
	}
	
	/**
	 * Crea una nueva pieza con el input del usuario
	 * @return la nueva pieza
	 */
	private Pieza nuevaPieza()
	{
		
		String[] opcionesNuevaPieza = { "Cuadro", "Escultura", "Cortometraje", "Fotografia" };
		int iNuevaPieza = this.mostrarMenu( "Escoja la clase de pieza", opcionesNuevaPieza);
		
		String titulo = this.pedirCadenaAlUsuario("Escriba el titulo de la pieza");
		ArrayList<String> autores = pedirAutores();
		String anio = this.pedirCadenaAlUsuario("Ingrese el año de la pieza");
		String ciudad = this.pedirCadenaAlUsuario("Ingrese la ciudad de la pieza");
		String pais = this.pedirCadenaAlUsuario("Ingrese el pais de la pieza");
		
		switch ( iNuevaPieza )
		{
			case 1: // Cuadro
			{
				return nuevoCuadro( titulo, Pieza.CUADRO, autores, anio, ciudad, pais );
			}
			
			case 2:
			{
				return nuevaEscultura( titulo, Pieza.ESCULTURA, autores, anio, ciudad, pais );
			}
			
			case 3:
			{
				return nuevoCortometraje( titulo, Pieza.CORTOMETRAJE, autores, anio, ciudad, pais );
			}
			
			case 4:
			{
				return nuevaFotografia( titulo, Pieza.FOTOGRAFIA, autores, anio, ciudad, pais );
			}
			
			default:
			{
				return nuevaPieza();
			}
		}
		
	}
	
	/**
	 * Consigna una nueva pieza a la galeria
	 * @throws IOException 
	 */
	private void consignarPieza() throws IOException
	{
		Pieza pieza = nuevaPieza();
		
		int precioPieza = this.pedirEnteroAlUsuario( "Por favor introduzca el precio de la pieza, -1 si no la desea vender" );
		
		int anio = this.pedirEnteroAlUsuario( "Por favor ingrese el año de salida de la pieza" );
		int mes = this.pedirEnteroAlUsuario( "Por favor ingrese el mes de salida de la pieza" );
		int dia = this.pedirEnteroAlUsuario( "Por favor ingrese el dia de salida de la pieza" );
		
		@SuppressWarnings("deprecation")
		Date date = new Date( anio, mes, dia );
		
		usuario.consignarPieza( pieza , precioPieza, date, (Administrador) controladorGaleria.galeria.buscarEmpleadoUsername( nombreAdmin ));
		
		System.out.println( "Pieza consignada adecuadamente." );
		
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Realiza una oferta para adquirir una pieza
	 * @throws IOException 
	 */
	private void comprarPieza() throws IOException
	{
		String nombrePieza = this.pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
		usuario.aplicarComprarPieza(nombrePieza, controladorGaleria.galeria);
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Realiza una nueva oferta a una pieza en subasta
	 * @throws IOException 
	 */
	private void realizarOferta() throws IOException
	{
		String nombrePieza = this.pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
		int oferta = this.pedirEnteroAlUsuario("Ingrese la oferta que desea realizar");
		boolean resultado = usuario.nuevaOfertaSubasta(nombrePieza, oferta, (Operador) controladorGaleria.galeria.buscarEmpleadoUsername( nombreOperador ));
		
		if ( resultado )
		{
			System.out.println( "Oferta registrada exitosamente" );
		}
		else
		{
			System.out.println( "Error al registrar la oferta. Verifique si tiene autorizacion o si la pieza esta en subasta" );
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Consulta una pieza
	 * @throws IOException 
	 */
	private void consultarPieza() throws IOException
	{
		String nombrePieza = this.pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
		Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria(nombrePieza);
		if ( pieza != null )
		{
			System.out.println("Pieza encontrada");
			// TODO terminar la consulta
		}
		else
		{
			System.out.println("Pieza no fue encontrada");
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();	
	}
	
	/**
	 * Consulta informacion acerca del historial de una pieza
	 * @throws IOException 
	 */
	private void consultarHistorialPieza() throws IOException
	{
		String nombrePieza = this.pedirCadenaAlUsuario("Ingrese el nombre de la pieza");
		Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria(nombrePieza);
		if ( pieza != null )
		{
			System.out.println("Pieza encontrada");
			// TODO
		}
		else
		{
			System.out.println("Pieza no fue encontrada");
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Consulta el historial de un artista
	 * @throws IOException 
	 */
	private void consultarHistorialArtista() throws IOException
	{
		String nombreArtista = this.pedirCadenaAlUsuario("Ingrese el nombre del artista");
		HashMap<String, ArrayList<Pieza>> mapaArtistas = controladorGaleria.galeria.getMapaAutores();
		
		if ( mapaArtistas != null )
		{
			ArrayList<Pieza> autor = mapaArtistas.get(nombreArtista);
			if ( autor != null )
			{
				System.out.println("Autor encontrado");
				// TODO
			}
			else
			{
				System.out.println("Autor no fue encontrado");
			}
		}
		else
		{
			System.out.println("Autor no fue encontrado");
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Cambia el metodo de pago del usuario
	 * @throws IOException 
	 */
	private void cambiarMetodoPago() throws IOException
	{
		int metodoActual = usuario.getMetodoPago();
		
		System.out.println("Metodo de pago actual" + metodoActual );
		
		String[] opcionesMetodoPago = { "Credito" , "Efectivo", "Transferencia" };
		int iCambiarMetodo = this.mostrarMenu("Elija un nuevo metodo de pago", opcionesMetodoPago);
		
		switch ( iCambiarMetodo )
		{
			case 1: // Credito
			{
				usuario.setMetodoPago(PortalPagos.CREDITO);
				break;
			}
			case 2: // Efectivo
			{
				usuario.setMetodoPago(PortalPagos.EFECTIVO);
				break;
			}
			case 3: // Transferencia
			{
				usuario.setMetodoPago(PortalPagos.TRANSFERENCIA);
				break;
			}
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		System.out.println("Metodo de pago cambiado adecuadamente");
		
	}
	
	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Consignar pieza", "Comprar pieza", "Realizar oferta", "Consultar pieza",
										"Consultar historial pieza", "Consultar historial artista", "Cambiar metodo de pago",
										"Cerrar sesion"};
		
		int iInput = this.mostrarMenu( "Menu de " + nombreUsuario , opcionesMenuUsuario );
		
		switch ( iInput )
		{
			case 1: // Consignar pieza
			{
				consignarPieza();
				break;
			}
			
			case 2: // Comprar pieza
			{
				comprarPieza();
				break;
			}
			
			case 3: // Realizar oferta
			{
				realizarOferta();
				break;
			}
			
			case 4: // Consultar pieza
			{
				consultarPieza();
				break;
			}
			
			case 6: // Consultar historial pieza
			{
				consultarHistorialPieza();
				break;
			}
			
			case 7: // Consultar historial artista
			{
				consultarHistorialArtista();
				break;
			}
			
			case 8: // Cambiar metodo de pago
			{
				cambiarMetodoPago();
				break;
			}
			
			case 9: // Cerrar sesion
			{
				controladorGaleria.cerrarSesion();
				break;
			}
			
			default:
			{
				correrConsola( );
				break;
			}
		}
		
	}
	
}
