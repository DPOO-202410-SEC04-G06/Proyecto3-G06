package pagos;

import java.util.*;

import java.io.Serializable;

public class PortalPagos implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -462424157430635809L;
	// ############################################ Atributos publicos
    public static final int SIN_METODO_PAGO = 0;
    public static final int CREDITO = 1;
    public static final int EFECTIVO = 2;
    public static final int TRANSFERENCIA = 3;

    // ############################################ Atributos PortalPagos
    private HashMap<Integer, Transaccion> historialTransaccionesCodigo;
    private HashMap<Date, HashMap<String, Transaccion > > historialTransaccionesFecha;
    private HashMap<Integer, String> codigosExistentes;

    // ############################################ Constructor

    public PortalPagos()
    {
        historialTransaccionesCodigo = new HashMap<Integer, Transaccion>();
        historialTransaccionesFecha = new HashMap<Date, HashMap<String, Transaccion > >();
        codigosExistentes = new HashMap<Integer, String>();
    }

    // ############################################ Getters & Setters

    /**
     * @return HashMap<Integer, Transaccion> return the historialTransaccionesCodigo
     */
    public HashMap<Integer, Transaccion> getHistorialTransaccionesCodigo() 
    {
        return historialTransaccionesCodigo;
    }

    /**
     * @param historialTransaccionesCodigo the historialTransaccionesCodigo to set
     */
    public void setHistorialTransaccionesCodigo(HashMap<Integer, Transaccion> historialTransaccionesCodigo) 
    {
        this.historialTransaccionesCodigo = historialTransaccionesCodigo;
    }

    /**
     * @return HashMap<Date, HashMap<String, Transaccion > > return the historialTransaccionesFecha
     */
    public HashMap<Date, HashMap<String, Transaccion > > getHistorialTransaccionesFecha() 
    {
        return historialTransaccionesFecha;
    }

    /**
     * @param historialTransaccionesFecha the historialTransaccionesFecha to set
     */
    public void setHistorialTransaccionesFecha(HashMap<Date, HashMap<String, Transaccion > > historialTransaccionesFecha) 
    {
        this.historialTransaccionesFecha = historialTransaccionesFecha;
    }

    /**
     * @return HashMap<Integer, String> return the codigosExistentes
     */
    public HashMap<Integer, String> getCodigosExistentes() 
    {
        return codigosExistentes;
    }

    /**
     * @param codigosExistentes the codigosExistentes to set
     */
    public void setCodigosExistentes(HashMap<Integer, String> codigosExistentes) 
    {
        this.codigosExistentes = codigosExistentes;
    }

    // ############################################ Metodos

    /**
     * genera una nueva transacción
     * @param nombreComprador
     * @param nombreVendedor
     * @param fecha
     * @param isSubasta
     * @param precio
     * @param nombrePieza
     * @param metodoPago
     * @return
     */
    public Transaccion nuevaTransaccion(String nombreComprador, String nombreVendedor, Date fecha, boolean isSubasta, int precio, String nombrePieza, int metodoPago) 
    {
        int codigo = generarCodigoTransacción(nombrePieza);
        return new Transaccion( nombreComprador,  nombreVendedor,  fecha,  isSubasta,  precio,  nombrePieza, metodoPago, codigo);
    }

    /**
     * genera un código aleatorio para la transacción
     * @param nombrePieza
     * @return el código de la transacción
     */
    public int generarCodigoTransacción( String nombrePieza )
    {
        int min = 0;
        int max = 1000000000;

        int codigo = (int) (Math.random()*(max-min+1)+min);

        if ( !( codigosExistentes.containsKey(codigo) ) )
        {
            codigosExistentes.put(codigo, nombrePieza);
            return codigo;
        }
        
        return generarCodigoTransacción( nombrePieza );
    }

    /**
     * Agrega una nueva transacción al historial de transacciones
     * @param transaccion
     * @param fecha
     * @param codigoTransaccion
     */
    public void agregarTransaccionHistorial(Transaccion transaccion, Date fecha, int codigoTransaccion)
    {
        historialTransaccionesCodigo.put(codigoTransaccion, transaccion);
        historialTransaccionesFecha.put(fecha, new HashMap<String, Transaccion>() );
        historialTransaccionesFecha.get(fecha).put( transaccion.getNombrePieza() , transaccion );
    }
    
    /**
     * Busca una transacción por su código
     * @param codigoTransaccion
     * @return la transacción buscada
     */
    public Transaccion buscarTransaccionCodigo(int codigoTransaccion)
    {
        return historialTransaccionesCodigo.get( codigoTransaccion );
    }

    /**
     * Busca una transacción por su fecha
     * @param fecha : Formato DD-MM-YYYY
     * @param nombrePieza
     * @return
     */
    public Transaccion buscarTransaccionFecha(Date fecha, String nombrePieza)
    {
        HashMap<String, Transaccion> mapaFecha = historialTransaccionesFecha.get(fecha);
        return mapaFecha.get(nombrePieza);
    }

}
