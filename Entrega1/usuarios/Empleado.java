package usuarios;

import java.util.*;

import galeria.Inventario;
import pagos.PortalPagos;
import pagos.Transaccion;
import piezas.Pieza;

public class Empleado extends Usuario 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2563582448293879044L;
	// ############################################ Atributos publicos
    public static final int ADMIN = 0;
    public static final int OP = 1;
    public static final int CAJERO = 2;
    public static final int CORRIENTE = 3;

    // ############################################ Atributos Empleado
    private int rol;

    // ############################################ Constructor

    public Empleado(String nombreU, String telefonoU, String usernameU, String passwordU)
    {
        super(nombreU, telefonoU, usernameU, passwordU);
        rol = CORRIENTE;
    }

    // ############################################ Getters & Setters

    /**
     * @return int return the rol
     */
    public int getRol() 
    {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(int rol) 
    {
        this.rol = rol;
    }

    // ############################################ Metodos

    /**
     * Actualiza el estado actual de una pieza, se ve reflejado en el inventario
     * @param nombrePieza
     * @param nuevoEstado
     * @param inventarioPiezas
     */
    public void actualizarEstadoPieza(String nombrePieza, int nuevoEstado, Inventario inventarioPiezas )
    {
        inventarioPiezas.actualizarEstadoPieza( nombrePieza, nuevoEstado );
    }

    /**
     * Realiza el cambio de propietario de una pieza, se ve reflejado en el perfil del usuario y en el inventario
     * @param nombrePieza
     * @param pieza
     * @param propietario
     */
    public void cambiarPropietarioPieza(String nombrePieza, Pieza pieza, UsuarioCorriente propietario )
    {
        UsuarioCorriente antiguoPropietario = pieza.getPropietario();
        antiguoPropietario.removerPiezaActual( nombrePieza );

        pieza.cambiarPropietarioPieza(propietario);
        propietario.adquirirPieza(nombrePieza, pieza);
    }

    /**
     * Genera una nueva transacción y se la asigna a los usuarios correspondientes. Tambien guarda el registro
     * en el historial de pagos en el Portal de Pagos.
     * @param nombre_comprador
     * @param nombre_vendedor
     * @param is_subasta
     * @param precio
     * @param nombre_pieza
     * @param metodo_pago
     */
    public void nuevaTransaccion(String nombre_comprador, UsuarioCorriente comprador,  String nombre_vendedor, UsuarioCorriente vendedor, boolean is_subasta, 
                                        int precio, String nombre_pieza, int metodo_pago, Cajero cajero, PortalPagos portalPagos)
    {
        Date fecha = new Date();
        Transaccion transaccion =  portalPagos.nuevaTransaccion( nombre_comprador, nombre_vendedor, fecha, is_subasta, precio, nombre_pieza, comprador.getMetodoPago() );
        comprador.agregarTransaccion(transaccion, fecha);
        vendedor.agregarTransaccion(transaccion, fecha);
        cajero.nuevaTransaccionPendiente(transaccion);
    }

    /**
     * Busca una transaccion en el historial de transacciones del portal de pagos de la galeria.
     * Si no encuentra una transaccion de la pieza en la fecha indicada, retorna null.
     * @param fecha : String en formato DD-MM-YYYY
     * @param nombrePieza
     * @param portalPagos 
     * @return Transaccion buscada, si no la encuentra retorna null
     */
    public Transaccion buscarTransaccionFecha(Date fecha, String nombrePieza, PortalPagos portalPagos)
    {
        return portalPagos.buscarTransaccionFecha( fecha, nombrePieza );
    }

    /**
     * Busca una transaccion en el historial de transacciones del portal de pagos de la galeria.
     * Si no encuentra una transaccion de la pieza en la fecha indicada, retorna null.
     * @param codigoTransaccion : EL codigo de la transaccion 
     * @param portalPagos 
     * @return Transaccion buscada, si no la encuentra retorna null
     */
    public Transaccion buscarTransaccionCodigo(int codigoTransaccion, PortalPagos portalPagos)
    {
        return portalPagos.buscarTransaccionCodigo( codigoTransaccion );
    }
}
