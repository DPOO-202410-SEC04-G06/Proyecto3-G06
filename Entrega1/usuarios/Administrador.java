package usuarios;

import java.util.*;

import galeria.Galeria;
import galeria.Inventario;
import pagos.PortalPagos;
import pagos.Transaccion;
import piezas.Pieza;

public class Administrador extends Empleado 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6912507643313112434L;
	// ############################################ Atributos Administrador
    private ArrayList< String > verificacionesConsignacionPendientes; // verif vendedor
    private ArrayList< String > verificacionesCompraPiezas; // verif comprador
    private ArrayList< String > verificacionesUsuarioSubasta; // verif subasta
    private ArrayList< Pieza > piezasDeEntrada;
    private HashMap< String, Date > fechasSalidaPiezas;
    private ArrayList< String > comprasPendientes;// titulos de la pieza
    private ArrayList< Pieza > piezasDeSalida;

    // ############################################ Constructor

    public Administrador(String nombreU, String telefonoU, String usernameU, String passwordU)
    {
        super(nombreU, telefonoU, usernameU, passwordU);
        setRol( Empleado.ADMIN );
        verificacionesConsignacionPendientes = new ArrayList< String >();
        verificacionesCompraPiezas = new ArrayList< String >();
        verificacionesUsuarioSubasta = new ArrayList< String >();
        piezasDeEntrada = new ArrayList< Pieza >();
        fechasSalidaPiezas = new HashMap< String, Date >();
        piezasDeSalida = new ArrayList< Pieza >();
        comprasPendientes = new ArrayList< String >();
    }

    // ############################################ Getters & Setters

    public ArrayList<String> getVerificacionesConsignacionPendientes() 
    {
		return verificacionesConsignacionPendientes;
	}

	public ArrayList<String> getVerificacionesCompraPiezas() 
    {
		return verificacionesCompraPiezas;
	}

	public ArrayList<String> getVerificacionesUsuarioSubasta() 
    {
		return verificacionesUsuarioSubasta;
	}

    public ArrayList<Pieza> getPiezasDeEntrada()
    {
        return piezasDeEntrada;
    }

    public ArrayList<String> getComprasPendientes()
    {
        return comprasPendientes;
    }

    public HashMap<String, Date> getFechasSalidaPiezas()
    {
        return fechasSalidaPiezas;
    }

    public ArrayList<Pieza> getPiezasDeSalida()
    {
        return piezasDeSalida;
    }

	public void setVerificacionesConsignacionPendientes(ArrayList<String> verificacionesConsignacionPendientes) 
    {
		this.verificacionesConsignacionPendientes = verificacionesConsignacionPendientes;
	}

	public void setVerificacionesCompraPiezas(ArrayList<String> verificacionesCompraPiezas) 
    {
		this.verificacionesCompraPiezas = verificacionesCompraPiezas;
	}

	public void setVerificacionesUsuarioSubasta(ArrayList<String> verificacionesUsuarioSubasta) 
    {
		this.verificacionesUsuarioSubasta = verificacionesUsuarioSubasta;
	}

    public void setPiezasDeEntrada(ArrayList<Pieza> piezasDeEntrada)
    {
        this.piezasDeEntrada = piezasDeEntrada;
    }

    public void setComprasPendientes(ArrayList<String> comprasPendientes) 
    {
		this.comprasPendientes = comprasPendientes;
	}

    public void setFechasSalidaPiezas(HashMap< String, Date > fechasSalidaPiezas)
    {
        this.fechasSalidaPiezas = fechasSalidaPiezas;
    }

    public void setPiezasDeSalida(ArrayList<Pieza> piezasDeSalida)
    {
        this.piezasDeSalida = piezasDeSalida;
    }

    // ############################################ Metodos
    
    /**
     * Verifica un vendedor de una pieza y lo actualiza
     * @param username
     * @param mapaDeUsuariosCorrientes
     * @return el mapa de usuarios con el usuario actualizado
     */
    public void verificarVendedor(UsuarioCorriente usuario)
    {
        usuario.setVerifVendedor(true);
    }

    /**
     *  Actualiza la pila de vendedores pendientes
     * @return El username del usuario pendiente
     */
    public String nextVendedorPendiente()
    {
        if ( !verificacionesConsignacionPendientes.isEmpty() )
        {
            String nextUsername = this.verificacionesConsignacionPendientes.get(0);
            verificacionesConsignacionPendientes.remove(nextUsername);
            return nextUsername;
        }
        return null;
    }

	/**
     * Verifica un comprador de una pieza y lo actualiza
     * @param username
     * @param mapaDeUsuariosCorrientes
     * @return el mapa de usuarios con el usuario actualizado
     */
    public void verificarComprador(UsuarioCorriente usuario, int montoMax)
    {
        usuario.setVerifComprador(true);
        usuario.setMontoMax(montoMax);
    }

    /**
     * Actualiza la pila de compradores pendientes
     * @return El username del usuario pendiente
     */
    public String nextCompradorPendiente()
    {
        if ( !verificacionesCompraPiezas.isEmpty() )
        {
            String nextUsername = this.verificacionesCompraPiezas.get(0);
            verificacionesCompraPiezas.remove(nextUsername);
            return nextUsername;
        }
        return null;
    }

    /**
     * Registra la adquisición de una pieza de un usuario
     * @param nombrePieza
     * @param mapaDeUsuariosCorrientes
     * @param galeria
     * @param portalPagos
     * @param cajero
     */
    public void registrarAdquisicionPieza(String nombrePieza,  HashMap<String, UsuarioCorriente> mapaDeUsuariosCorrientes, Galeria galeria, PortalPagos portalPagos, Cajero cajero)
    {
        HashMap<String, HashMap<String, String> > piezasEnVenta = galeria.getPiezasEnVenta();

        String username = piezasEnVenta.get( nombrePieza ).get( Galeria.COMPRADOR );
        int precio = Integer.parseInt(  piezasEnVenta.get( nombrePieza ).get( Galeria.PRECIO_PIEZA )  ) ;

        UsuarioCorriente usuario = mapaDeUsuariosCorrientes.get(username);

        if ( precio <= usuario.getMontoMax() )
        {
            Pieza pieza = galeria.consultarPiezaGaleria( nombrePieza );
            cambiarPropietarioPieza( nombrePieza, pieza, usuario );
            galeria.removerPiezaEnVenta( nombrePieza );
            actualizarEstadoPieza(nombrePieza, Inventario.PASADA, galeria.getInventarioPiezas());
            nuevaTransaccion(usuario.getNombre(), 
                                usuario, 
                                pieza.getNombrePropietario(), 
                                pieza.getPropietario(), 
                                Transaccion.NO_SUBASTADO, 
                                precio, nombrePieza, 
                                usuario.getMetodoPago(), 
                                cajero, 
                                portalPagos);
        }
        else
        {
            galeria.desbloquearPieza(nombrePieza);
        }
    }

    public String nextAdquisicion()
    {
        if ( !comprasPendientes.isEmpty() )
        {
            String next = this.getComprasPendientes().get(0);
            comprasPendientes.remove(next);
            return next;
        }
        return null;
    }

    /**
     * Actualiza el monto maximo de compra de un usuario
     * @param username
     * @param nuevoMonto
     * @param mapaDeUsuariosCorrientes
     */
    public void actualizarMontoMaximo(String username, int nuevoMonto, HashMap<String, UsuarioCorriente> mapaDeUsuariosCorrientes)
    {
        UsuarioCorriente usuario = mapaDeUsuariosCorrientes.get(username);
        usuario.setMontoMax(nuevoMonto);
    }

    /**
     * Verifica un nuevo usuario para realizar una subasta y lo agrega a la lista del operador
     * @param username
     * @param operadorGaleria
     * @return el operador con la lista actualizada
     */
    public void verificarOfertaSubasta(String username, Operador operadorGaleria)
    {
        operadorGaleria.nuevoUsuarioVerificadoSubasta( username );
    }

    /**
     * Actualiza la pila de ofertas pendientes para una subasta
     * @return El username de la oferta pendiente 
     */
    public String nextOfertaSubasta()
    {
        if ( !verificacionesUsuarioSubasta.isEmpty() )
        {
            String nextUsername = this.verificacionesUsuarioSubasta.get(0);
            verificacionesUsuarioSubasta.remove(nextUsername);
            return nextUsername;
        }
        return null;
    }

    /**
     * Agrega un nuevo usuario a la cola de usuarios de subasta por verificar
     * @param nombreUsuario
     */
    public void nuevaOfertaUsuario( String nombreUsuario )
    {
        verificacionesUsuarioSubasta.add(nombreUsuario);
    }


    /**
     * Agrega una nueva pieza a la lista de piezas por entrar a la galeria, el administrador verifica los datos
     * @param pieza
     */
    public void nuevaPiezaEntrada(Pieza pieza)
    {
        piezasDeEntrada.add(pieza);
    }

    /**
     * Agrega una nueva pieza al inventario de la galeria
     * @param pieza
     * @param estado
     * @param inventarioPiezas
     * @return el inventario actualizado
     */
    public void agregarPieza(Pieza pieza, int estado, Date fechaSalida, Inventario inventarioPiezas, Galeria galeria)
    { 
        inventarioPiezas.agregarPieza(pieza, estado);
        if ( pieza.getPrecioVenta() != Pieza.SIN_PRECIO )
        {
            galeria.nuevaPiezaEnVenta(pieza);
        }

        ArrayList<String> autores = pieza.getAutores();
        Iterator<String> iteratorAutores = autores.iterator();

        String autor = iteratorAutores.next();
        while ( iteratorAutores.hasNext() )
        {
            galeria.nuevaPiezaAutor( autor, pieza );
            autor = iteratorAutores.next();
        }

    }

    /**
     * Actualiza la pila de piezas de entrada
     * @return
     */
    public Pieza nextPIn()
    {
        if ( !piezasDeEntrada.isEmpty() )
        {
            Pieza next = this.piezasDeEntrada.get(0);
            piezasDeEntrada.remove(next);
            return next;
        }
        return null;
    }

    /**
     * Agrega una nueva pieza al mapa de fechas de salida
     * @param nombrePieza
     * @param fechaSalida
     */
    public void agregarFechaSalidaPieza(String nombrePieza, Date fechaSalida)
    {
        fechasSalidaPiezas.put(nombrePieza, fechaSalida);
    }

    /**
     * Agrega una nueva pieza a la lista de piezas por salir de la galeria, el administrador verifica los datos
     * @param pieza
     */
    public void nuevaPiezaSalida(Pieza pieza)
    {
        piezasDeSalida.add(pieza);
    }

    /**
     * Elimina una pieza de las piezas actuales de la galeria, la guarda en las piezas pasadas
     * @param nombrePieza
     * @param inventarioPiezas
     * @return El inventario actualizado
     */
    public void registrarSalidaPieza(String nombrePieza, Inventario inventarioPiezas, Galeria galeria)
    {
        inventarioPiezas.actualizarEstadoPieza(nombrePieza, Inventario.PASADA);
        if ( galeria.getPiezasEnVenta().containsKey(nombrePieza) )
        {
            galeria.removerPiezaEnVenta(nombrePieza);
        }
        fechasSalidaPiezas.remove(nombrePieza);
    }

    /**
     * Actualiza la pila de piezas de salida
     * @return
     */
    public Pieza nextPOut()
    {
        if ( !piezasDeSalida.isEmpty() )
        {    Pieza next = this.piezasDeSalida.get(0);
            piezasDeSalida.remove(next);
            return next;
        }
        return null;
    }

    /**
     * Calcula el valor total de la colección de piezas de un usuario.
     * @param usuario
     * @return El valor total de la colección
     */
    public int consultarValorColeccionUsuario( UsuarioCorriente usuario )
    {
        int total = 0;
        HashMap< String, Pieza> mapaPiezasActualesusuario = usuario.getPiezasActuales();

        Set<String> setTitulosPiezas = mapaPiezasActualesusuario.keySet();

        Iterator<String> iteratorTitulosPiezas = setTitulosPiezas.iterator();

        String nextKey = iteratorTitulosPiezas.next();
        while ( iteratorTitulosPiezas.hasNext() )
        {
            Pieza nextPieza = mapaPiezasActualesusuario.get(nextKey);

            if ( nextPieza != null )
            {
                total +=  nextPieza.getPrecioVenta();
            }

            nextKey = iteratorTitulosPiezas.next();
        }

        return total;
    }   

}
