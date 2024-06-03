package consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

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
	 * Método principal para correr la consola del usuario
	 * @throws IOException 
	 */
	public abstract void correrConsola() throws IOException;
	
	// ############################################ Métodos
	
	/**
	 * Actualiza el estado de una pieza en la galería. El empleado corriente solamente podrá actualizar 
	 * el estado a bodega o a exposición
	 * @throws IOException 
	 */
	protected void actualizarEstadoPieza() throws IOException
	{
		int newState;
		
		String nombrePieza = JOptionPane.showInputDialog("Digite el nombre de la pieza que desea actualizar");
		
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
	 * Cambia el propietario de una pieza actualmente en la galería
	 * @throws IOException 
	 */
	protected void cambiarPropietarioPieza() throws IOException
	{
		String nombreUsuario = JOptionPane.showInputDialog("Digite el nombre del nuevo propietario");
		UsuarioCorriente propietario = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nombreUsuario);
		
		if ( propietario == null )
		{
			JOptionPane.showMessageDialog(null, "No se encontró el usuario, intente de nuevo");
			correrConsola();
		}
		
		String nombrePieza = JOptionPane.showInputDialog("Digite el nombre de la pieza");
		Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria(nombrePieza);
		
		if ( pieza == null )
		{
			JOptionPane.showMessageDialog(null, "No se encontró la pieza, intente de nuevo");
			correrConsola();
		}
		
		usuario.cambiarPropietarioPieza(nombrePieza, pieza, propietario);
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Busca una transacción en el sistema
	 * @throws IOException 
	 */
	protected void buscarTransaccion() throws IOException
	{
		String[] opcionesBuscar = { "Buscar por código", "Buscar por fecha" };
		int iBuscarT = this.mostrarMenu("Seleccione una opción", opcionesBuscar);
		
		if (iBuscarT == 1)
		{
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de la transacción"));
			Transaccion transaccion = usuario.buscarTransaccionCodigo(codigo, controladorGaleria.galeria.getPortalPagos());
			if ( transaccion == null )
			{
				JOptionPane.showMessageDialog(null, "Transacción no encontrada, vuelva a intentarlo");
				correrConsola();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Transacción encontrada...\n" +
					"Código: " + transaccion.getCodigoTransaccion() + "\n" +
					"Comprador: " + transaccion.getNombreComprador() + "\n" +
					"Vendedor: " + transaccion.getNombreVendedor() + "\n" +
					"Precio de venta: " + transaccion.getPrecio() + "\n" +
					"Fecha: " + transaccion.getFecha());
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
		String nombrePieza = JOptionPane.showInputDialog("Ingrese el nombre de la pieza");
		Pieza pieza = controladorGaleria.galeria.consultarTodasLasPiezas(nombrePieza);
		if ( pieza != null )
		{
			JOptionPane.showMessageDialog(null, "Pieza encontrada...\n" +
				"Título: " + pieza.getTitulo() + "\n" +
				"Precio de venta (-1 si no está en venta): " + pieza.getPrecioVenta() + "\n" +
				"Autores: " + pieza.getAutores() + "\n" +
				"Año: " + pieza.getAnio() + "\n" +
			
				"Ciudad: " + pieza.getCiudad() + "\n" +
				"País: " + pieza.getPais() + "\n" +
				"Estado: " + pieza.getEstado() + "\n" +
				"Propietario actual: " + pieza.getPropietario().getUsername());
			
			boolean continuar = JOptionPane.showConfirmDialog(null, "¿Desea realizar otra consulta?", "Consulta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

			if (continuar)
			{
				consultarPieza();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Pieza no encontrada");
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

		StringBuilder messageBuilder = new StringBuilder("\nMostrando los 3 primeros resultados...\n");
		int i = 1;
		while ( i <= 3 && usernameIt.hasNext() )
		{
			String next = usernameIt.next();
			messageBuilder.append("Usuario ").append(i).append(": ").append(next).append("\n");
			i += 1;
		}
		messageBuilder.append("\n¿Desea buscar un usuario específico en el historial?");

		boolean confirmacion = JOptionPane.showConfirmDialog(null, messageBuilder.toString(), "Consulta de historial de usuarios", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

		if (!confirmacion)
		{
			JOptionPane.showMessageDialog(null, "Consulta finalizada.");
		}
		else
		{
			String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario (username) que desea buscar");
			UsuarioCorriente usuario = mapaUsuarios.get(username);

			if ( usuario == null )
			{
				JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuario encontrado...\n" +
					"Nombre: " + usuario.getNombre() + "\n" +
					"Teléfono: " + usuario.getTelefono() + "\n" +
					"Nombre de usuario (username): " + usuario.getUsername());

				showUsuarios(pieza);
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

		StringBuilder messageBuilder = new StringBuilder("\nMostrando los 3 primeros resultados...\n");
		int i = 1;
		while ( i <= 3 && transIt.hasNext() )
		{
			Integer next = transIt.next();
			messageBuilder.append("Código ").append(i).append(": ").append(next).append("\n");
			i += 1;
		}
		messageBuilder.append("\n¿Desea buscar una transacción específica en el historial?");

		boolean confirmacion = JOptionPane.showConfirmDialog(null, messageBuilder.toString(), "Consulta de historial de transacciones", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

		if (!confirmacion)
		{
			JOptionPane.showMessageDialog(null, "Consulta finalizada.");
		}
		else
		{
			int code = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código de transacción que desea buscar"));
			Transaccion transaccion = mapaTransacciones.get(code);

			if ( transaccion == null )
			{
				JOptionPane.showMessageDialog(null, "Transacción no encontrada.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Transacción encontrada...\n" +
					"Código: " + transaccion.getCodigoTransaccion() + "\n" +
					"Comprador: " + transaccion.getNombreComprador() + "\n" +
					"Vendedor: " + transaccion.getNombreVendedor() + "\n" +
					"Precio de venta: " + transaccion.getPrecio() + "\n" +
					"Fecha: " + transaccion.getFecha());

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
		String nombrePieza = JOptionPane.showInputDialog("Ingrese el nombre de la pieza");
		Pieza pieza = controladorGaleria.galeria.consultarTodasLasPiezas(nombrePieza);
		if ( pieza != null )
		{
			JOptionPane.showMessageDialog(null, "Pieza encontrada...");

			String[] opciones = { "Consultar historial de usuarios de la pieza", "Consultar historial de transacciones" };

			int iInput = JOptionPane.showOptionDialog(null, "Elija una opción", "Consulta de historial de la pieza", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

			if ( iInput == 0 )
			{
				showUsuarios(pieza);
			}
			else
			{
				showTransacciones(pieza);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Pieza no fue encontrada");
		}

		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	/**
	 * Muestra la informacion de un autor
	 * @param nombre
	 * @param autor
	 */
	private void showArtista( String nombre, ArrayList<Pieza> autor )
	{
		StringBuilder messageBuilder = new StringBuilder("Mostrando información de ").append(nombre).append("\n");

		Iterator<Pieza> piezasIt = autor.iterator();

		messageBuilder.append("\nMostrando los 3 primeros resultados...\n");
		int i = 1;
		while ( i <= 3 && piezasIt.hasNext() )
		{
			Pieza next = piezasIt.next();
			messageBuilder.append("Pieza").append(i).append(": ").append(next.getTitulo()).append("\n");
			i += 1;
		}
		messageBuilder.append("\n¿Desea buscar una pieza específica en el historial?");

		boolean confirmacion = JOptionPane.showConfirmDialog(null, messageBuilder.toString(), "Consulta de historial de artista", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

		if (!confirmacion)
		{
			JOptionPane.showMessageDialog(null, "Consulta finalizada.");
		}
		else
		{
			piezasIt = autor.iterator();
			String titulo =
			JOptionPane.showInputDialog("Ingrese el título de la pieza que desea buscar");
			
			boolean found = false;
			Pieza pieza = null;
			while ( piezasIt.hasNext() && !found )
			{
				Pieza next = piezasIt.next();
				if ( next.getTitulo().equals(titulo) )
				{
					found = true;
					pieza = next;
				}
			}

			if ( pieza == null )
			{
				JOptionPane.showMessageDialog(null, "Pieza no encontrada.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Pieza encontrada...\n" +
					"Título: " + pieza.getTitulo() + "\n" +
					"Precio de venta (-1 si no está en venta): " + pieza.getPrecioVenta() + "\n" +
					"Autores: " + pieza.getAutores() + "\n" +
					"Año: " + pieza.getAnio() + "\n" +
					"Ciudad: " + pieza.getCiudad() + "\n" +
					"País: " + pieza.getPais() + "\n" +
					"Estado: " + pieza.getEstado() + "\n" +
					"Propietario actual: " + pieza.getPropietario().getUsername());

				showTransacciones(pieza);

				showArtista(nombre, autor);
			}
		}
	}

	/**
	 * Consulta el historial de un artista
	 * @throws IOException 
	 */
	protected void consultarHistorialArtista() throws IOException
	{
		String nombreArtista = JOptionPane.showInputDialog("Ingrese el nombre del autor");
		HashMap<String, ArrayList<Pieza>> mapaArtistas = controladorGaleria.galeria.getMapaAutores();
		
		if ( mapaArtistas != null )
		{
			ArrayList<Pieza> autor = mapaArtistas.get(nombreArtista);
			if ( autor != null )
			{
				JOptionPane.showMessageDialog(null, "Autor encontrado...");
				
				showArtista(nombreArtista, autor);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Autor no fue encontrado");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Autor no fue encontrado");
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
}
