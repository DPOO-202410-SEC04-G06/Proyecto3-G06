package consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
			// TODO buscar transaccion
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
			System.out.println("\nPieza encontrada...");
			System.out.println("Titulo: " + pieza.getTitulo() );
			System.out.println("Precio de venta (-1 si no esta en venta): " + pieza.getPrecioVenta() );
			System.out.println("Autores: " + pieza.getAutores() );
			System.out.println("Año: " + pieza.getAnio());
			System.out.println("Ciudad: " + pieza.getCiudad());
			System.out.println("Pais: " + pieza.getCiudad());
			System.out.println("Estado: " + pieza.getEstado());
			System.out.println("Propietario actual: " + pieza.getPropietario());

			boolean continuar = this.pedirConfirmacionAlUsuario("Desea realizar otra consulta?");

			if (continuar)
			{
				consultarPieza();
			}
		}
		else
		{
			System.out.println("Pieza no fue encontrada");
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();	
	}
	
	/**
	 * Muestra el historial de usuarios de una pieza
	 * @param pieza
	 */
	private void showUsuarios( Pieza pieza )
	{
		HashMap<String, UsuarioCorriente> mapaUsuarios = pieza.getMapaUsuarios();

		Set<String> usernamesSet = mapaUsuarios.keySet();

		Iterator<String> usernameIt = usernamesSet.iterator();

		System.out.println("\nMostrando los 3 primeros resultados...");
		int i = 0;
		while ( i < 3 && usernameIt.hasNext() )
		{
			String next = usernameIt.next();
			System.out.println( next );
			i += 1;
		}

		boolean confirmacion = this.pedirConfirmacionAlUsuario( "Desea buscar un usuario especifico en el historial?");

		if (!confirmacion)
		{
			System.out.println( "Consulta finalizada." );
		}
		else
		{
			String username = this.pedirCadenaAlUsuario("Ingrese el nombre de usuario (username) que desea buscar");
			UsuarioCorriente usuario = mapaUsuarios.get(username);

			if ( usuario == null )
			{
				System.out.println("Usuario no encontrado.");
			}
			else
			{
				System.out.println("Usuario encontrado...");
				System.out.println("Nombre: " + usuario.getNombre() );
				System.out.println("Telefono: " + usuario.getTelefono() );
				System.out.println("Nombre de usuario (username): " + usuario.getUsername() );

				showUsuarios( pieza );

			}
		}

	}

	/**
	 * Muestra el historial de transacciones de una pieza
	 * @param pieza
	 */
	private void showTransacciones( Pieza pieza )
	{
		HashMap<Integer,Transaccion> mapaTransacciones = pieza.getHistorialPagosCodigo();

		Set<Integer> transaccionesSet = mapaTransacciones.keySet();

		Iterator<Integer> transIt = transaccionesSet.iterator();

		System.out.println("\nMostrando los 3 primeros resultados...");
		int i = 0;
		while ( i < 3 && transIt.hasNext() )
		{
			Integer next = transIt.next();
			System.out.println( next );
			i += 1;
		}

		boolean confirmacion = this.pedirConfirmacionAlUsuario( "Desea buscar una transaccion especifica en el historial?");

		if (!confirmacion)
		{
			System.out.println( "Consulta finalizada." );
		}
		else
		{
			Integer code = this.pedirEnteroAlUsuario("Ingrese el codigo de transaccion que desea buscar");
			Transaccion transaccion = mapaTransacciones.get(code);

			if ( transaccion == null )
			{
				System.out.println("Transaccion no encontrado.");
			}
			else
			{
				System.out.println("Transaccion encontrada...");
				System.out.println("Codigo: " + transaccion.getCodigoTransaccion() );
				System.out.println("Comprador: " + transaccion.getNombreComprador() );
				System.out.println("Vendedor: " + transaccion.getNombreVendedor() );
				System.out.println("Precio de venta: " + transaccion.getPrecio() );
				System.out.println("Fecha: " + transaccion.getFecha() );

				showTransacciones(pieza);

			}
		}
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
			System.out.println("\nPieza encontrada...");

			String[] opciones = { "Consultar historial de usuarios de la pieza", "Consultar historial de transacciones" };

			int iInput = this.mostrarMenu( "Elija una opcion", opciones);

			if ( iInput == 1 )
			{
				showUsuarios( pieza );
			}
			else
			{
				showTransacciones( pieza );
			}
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
				// TODO consultar historial de un artista
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
