package consola;

import java.util.*;

import controlador.ControladorGaleria;
import piezas.*;
import usuarios.Administrador;
import usuarios.UsuarioCorriente;

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
	 */
	private void consignarPieza()
	{
		Pieza pieza = nuevaPieza();
		
		int precioPieza = this.pedirEnteroAlUsuario( "\nPor favor introduzca el precio de la pieza, -1 si no la desea vender" );
		
		int anio = this.pedirEnteroAlUsuario( "\nPor favor ingrese el año de salida de la pieza" );
		int mes = this.pedirEnteroAlUsuario( "\nPor favor ingrese el mes de salida de la pieza" );
		int dia = this.pedirEnteroAlUsuario( "\nPor favor ingrese el dia de salida de la pieza" );
		
		@SuppressWarnings("deprecation")
		Date date = new Date( anio, mes, dia );
		
		usuario.consignarPieza( pieza , precioPieza, date, (Administrador) controladorGaleria.galeria.buscarEmpleadoUsername( nombreAdmin ));
		
		System.out.println( "Pieza consignada adecuadamente." );
		
		correrConsola();
	}
	
	private void comprarPieza()
	{
		
		correrConsola();
	}
	
	private void realizarOferta()
	{
		
		correrConsola();
	}
	
	private void consultarPieza()
	{
		
		correrConsola();	
	}
	
	private void consultarHistorialPieza()
	{
		
		correrConsola();
	}
	
	private void consultarHistorialArtista()
	{
		
		correrConsola();
	}
	
	// ############################################ Run
	
	public void correrConsola( )
	{
		
		String[] opcionesMenuUsuario = { "Consignar pieza", "Comprar pieza", "Realizar oferta", "Consultar pieza",
										"Consultar historial pieza", "Consultar historial artista"};
		
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
			
			default:
			{
				correrConsola( );
				break;
			}
		}
		
	}
	
}
