package consola;

import java.util.ArrayList;
import java.util.HashMap;

import controlador.ControladorGaleria;
import galeria.*;
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
		this.nombreUsuario = controladorGaleria.usuarioDeLaSesion.getNombre();
		this.usuario = (Empleado) controladorGaleria.usuarioDeLaSesion;
		this.nombreAdmin = controladorGaleria.galeria.nombreAdministrador;
		this.nombreCajero = controladorGaleria.galeria.nombreCajero;
		this.nombreOperador = controladorGaleria.galeria.nombreOperador;
	}
	
	// ############################################ Run
	
	/**
	 * Metodo principal para correr la consola del usuario
	 */
	public abstract void correrConsola();
	
	// ############################################ Metodos
	
	/**
	 * Actualiza el estado de una pieza en la galeria. El empleado corriente solamente podra actualizar 
	 * el estado a bodega o a exposicion
	 */
	protected void actualizarEstadoPieza()
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
		correrConsola();
	}
	
	/**
	 * Cambia el propietario de una pieza actualmente en la galeria
	 */
	protected void cambiarPropietarioPieza()
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
		correrConsola();
	}
	
	/**
	 * Genera una nueva transaccion en el sistema
	 */
	protected void nuevaTransaccion()
	{
		//TODO
	}
	
	/**
	 * Busca una transaccion en el sistema
	 */
	protected void buscarTransaccion()
	{
		//TODO
	}
	
	/**
	 * Consulta una pieza
	 */
	protected void consultarPieza()
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
		correrConsola();	
	}
	
	/**
	 * Consulta informacion acerca del historial de una pieza
	 */
	protected void consultarHistorialPieza()
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
		
		correrConsola();
	}
	
	/**
	 * Consulta el historial de un artista
	 */
	protected void consultarHistorialArtista()
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
		
		correrConsola();
	}
}
