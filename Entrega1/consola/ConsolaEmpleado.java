package consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controlador.ControladorGaleria;
import galeria.*;
import pagos.*;
import piezas.*;
import usuarios.*;

public abstract class ConsolaEmpleado extends ConsolaBasica 
{
	// ############################################ Atributos

	ControladorGaleria controladorGaleria;
	String nombreUsuario;
	String nombreAdmin;
	String nombreCajero;
	String nombreOperador;
	Empleado usuario;
		
	// ############################################ Constructor
	
	protected ConsolaEmpleado(ControladorGaleria controladorGaleria)
	{
		super();
		this.controladorGaleria = controladorGaleria;
		this.controladorGaleria.usuarioDeLaSesion = controladorGaleria.galeria.usuarioDeLaSesion;
		this.nombreUsuario = controladorGaleria.usuarioDeLaSesion.getNombre();
		this.usuario = (Empleado) controladorGaleria.usuarioDeLaSesion;
		this.nombreAdmin = controladorGaleria.galeria.nombreAdministrador;
		this.nombreCajero = controladorGaleria.galeria.nombreCajero;
		this.nombreOperador = controladorGaleria.galeria.nombreOperador;
	}
	
	// ############################################ Run
	
	/**
	 * Metodo principal para correr la consola del usuario
	 * @throws IOException 
	 */
	public abstract void correrConsola() throws IOException;
	
	// ############################################ Metodos
	
	/**
	 * Actualiza el estado de una pieza en la galeria. El empleado corriente solamente podra actualizar 
	 * el estado a bodega o a exposicion
	 * @throws IOException 
	 */
	protected void actualizarEstadoPieza() throws IOException
	{
		int newState;
		
		String nombrePieza = this.pedirCadenaAlUsuario("Digite el nombre de la pieza que desea actualizar");
		
		String[] opcionesEstado = { "Bodega", "Exponer" };
		
		int iUpdateState = this.mostrarMenu( "Elija el nuevo estado", opcionesEstado);
		
		if ( iUpdateState == 1 )
		{
			newState = Inventario.BODEGA;
		}
		else
		{
			newState = Inventario.EXPUESTA;
		}
		
		usuario.actualizarEstadoPieza(nombrePieza, newState, controladorGaleria.galeria.getInventarioPiezas());
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Cambia el propietario de una pieza actualmente en la galeria
	 * @throws IOException 
	 */
	protected void cambiarPropietarioPieza() throws IOException
	{
		String nombreUsuario = this.pedirCadenaAlUsuario("Digite el nombre del nuevo propietario");
		UsuarioCorriente propietario = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nombreUsuario);
		
		if ( propietario == null )
		{
			System.out.println( "No se encontro el usuario, intente de nuevo");
			correrConsola();
		}
		
		String nombrePieza = this.pedirCadenaAlUsuario("Digite el nombre de la pieza");
		Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria(nombrePieza);
		
		if ( pieza == null )
		{
			System.out.println( "No se encontro el la pieza, intente de nuevo");
			correrConsola();
		}
		
		usuario.cambiarPropietarioPieza(nombrePieza, pieza, propietario);
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Busca una transaccion en el sistema
	 * @throws IOException 
	 */
	protected void buscarTransaccion() throws IOException
	{
		String[] opcionesBuscar = { "Buscar por codigo", "Buscar por fecha" };
		int iBuscarT = this.mostrarMenu("Seleccione una opcion", opcionesBuscar);
		
		if (iBuscarT == 1)
		{
			int codigo = this.pedirEnteroAlUsuario("Ingrese el codigo de la transaccion");
			Transaccion transaccion = usuario.buscarTransaccionCodigo(codigo, controladorGaleria.galeria.getPortalPagos());
			if ( transaccion == null )
			{
				System.out.println( "Transaccion no encontrada, vuelva a intentarlo");
				correrConsola();
			}
			else
			{
			System.out.println( "Transaccion encontrada");
			// TODO
			}
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Consulta una pieza
	 * @throws IOException 
	 */
	protected void consultarPieza() throws IOException
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
	protected void consultarHistorialPieza() throws IOException
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
	protected void consultarHistorialArtista() throws IOException
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
}
