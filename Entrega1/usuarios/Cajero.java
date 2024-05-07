package usuarios;

import java.util.*;

import pagos.PortalPagos;
import pagos.Transaccion;

public class Cajero extends Empleado 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2284199293865834185L;
	// ############################################ Atributos Cajero
    private ArrayList<Transaccion> transaccionesPendientes;

    // ############################################ Constructor

    public Cajero(String nombreU, String telefonoU, String usernameU, String passwordU)
    {
        super(nombreU, telefonoU, usernameU, passwordU);
        setRol( Empleado.CAJERO );
        transaccionesPendientes = new ArrayList<Transaccion>();
    }

    // ############################################ Getters & Setters

    /**
     * @return ArrayList<Transaccion> return the transaccionesPendientes
     */
    public ArrayList<Transaccion> getTransaccionesPendientes() 
    {
        return transaccionesPendientes;
    }

    /**
     * @param transaccionesPendientes the transaccionesPendientes to set
     */
    public void setTransaccionesPendientes(ArrayList<Transaccion> transaccionesPendientes) 
    {
        this.transaccionesPendientes = transaccionesPendientes;
    }

    // ############################################ Metodos

    /**
     * Agrega una nueva transacción a la lista de transacciones por verificar
     * @param transaccion
     */
    public void nuevaTransaccionPendiente(Transaccion transaccion)
    {
        transaccionesPendientes.add(transaccion);
    }

    /**
     * Registra una nueva transacción en el sistema
     * @param transaccion
     * @param fecha
     * @param codigoTransaccion
     * @param portalPagos
     */
    public void registrarTransaccion(Transaccion transaccion, Date fecha, int codigoTransaccion, PortalPagos portalPagos)
    {
        portalPagos.agregarTransaccionHistorial(transaccion, fecha, codigoTransaccion);
    }

    /**
     * Actualiza la pila de transacciones pendientes
     * @return La transaccion pendiente
     */
    public Transaccion nextTransaccionPendiente()
    {
        if ( !transaccionesPendientes.isEmpty() )
        {   
            Transaccion nextTransaccion = this.transaccionesPendientes.get(0);
            this.transaccionesPendientes.remove(nextTransaccion);
            return nextTransaccion;
        }
        return null;
    }
}
