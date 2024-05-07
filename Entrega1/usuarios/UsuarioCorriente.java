package usuarios;

import java.util.*;

import galeria.Galeria;
import galeria.Inventario;
import pagos.PortalPagos;
import pagos.Transaccion;
import piezas.Cortometraje;
import piezas.Cuadro;
import piezas.Escultura;
import piezas.Fotografia;
import piezas.Pieza;

public class UsuarioCorriente extends Usuario 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4955565761463368557L;
	// ############################################ Atributos UsuarioCorriente
    private boolean verifComprador;
    private boolean verifVendedor;
    private int montoMax;
    private int metodoPago; // depende de los atributos publicos de Transaccion
    private HashMap<String, Pieza> piezasActuales;
    private HashMap<String, Pieza> piezasPasadas;
    private HashMap<Integer, Transaccion> historialPagosCodigo;
    private HashMap<Date, HashMap<String, Transaccion > > historialPagosFecha;

    // ############################################ Constructor

    public UsuarioCorriente(String nombreU, String telefonoU, String usernameU, String passwordU)
    {
        super(nombreU, telefonoU, usernameU, passwordU);
        verifComprador = false;
        verifVendedor = false;
        montoMax = 0;
        metodoPago = PortalPagos.SIN_METODO_PAGO;
        piezasActuales = new HashMap<String, Pieza>();
        piezasPasadas = new HashMap<String, Pieza>();
        historialPagosCodigo = new HashMap<Integer, Transaccion>();
        historialPagosFecha = new HashMap<Date, HashMap<String, Transaccion > >();
    }

    // ############################################ Getters & Setters

    /**
     * @return boolean return the verifComprador
     */
    public boolean isVerifComprador() 
    {
        return verifComprador;
    }

    /**
     * @param verifComprador the verifComprador to set
     */
    public void setVerifComprador(boolean verifComprador) 
    {
        this.verifComprador = verifComprador;
    }

    /**
     * @return boolean return the verifVendedor
     */
    public boolean isVerifVendedor() {
        return verifVendedor;
    }

    /**
     * @param verifVendedor the verifVendedor to set
     */
    public void setVerifVendedor(boolean verifVendedor) 
    {
        this.verifVendedor = verifVendedor;
    }

    /**
     * @return int return the montoMax
     */
    public int getMontoMax() 
    {
        return montoMax;
    }

    /**
     * @param montoMax the montoMax to set
     */
    public void setMontoMax(int montoMax) 
    {
        this.montoMax = montoMax;
    }

    /**
     * @return int return the metodoPago
     */
    public int getMetodoPago() 
    {
        return metodoPago;
    }

    /**
     * @param metodoPago the metodoPago to set
     */
    public void setMetodoPago(int metodoPago) 
    {
        this.metodoPago = metodoPago;
    }

    /**
     * @return HashMap<String, Pieza> return the piezasActuales
     */
    public HashMap<String, Pieza> getPiezasActuales() 
    {
        return piezasActuales;
    }

    /**
     * @param piezasActuales the piezasActuales to set
     */
    public void setPiezasActuales(HashMap<String, Pieza> piezasActuales) 
    {
        this.piezasActuales = piezasActuales;
    }

    /**
     * @return HashMap<String, Pieza> return the piezasPasadas
     */
    public HashMap<String, Pieza> getPiezasPasadas() 
    {
        return piezasPasadas;
    }

    /**
     * @param piezasPasadas the piezasPasadas to set
     */
    public void setPiezasPasadas(HashMap<String, Pieza> piezasPasadas) 
    {
        this.piezasPasadas = piezasPasadas;
    }

    /**
     * @return HashMap<Integer, Transaccion> return the historialPagosCodigo
     */
    public HashMap<Integer, Transaccion> getHistorialPagosCodigo() 
    {
        return historialPagosCodigo;
    }

    /**
     * @param historialPagosCodigo the historialPagosCodigo to set
     */
    public void setHistorialPagosCodigo(HashMap<Integer, Transaccion> historialPagosCodigo) 
    {
        this.historialPagosCodigo = historialPagosCodigo;
    }

    /**
     * @return HashMap<Date, Transaccion> return the historialPagosFecha
     */
    public HashMap<Date, HashMap<String, Transaccion > > getHistorialPagosFecha() 
    {
        return historialPagosFecha;
    }

    /**
     * @param historialPagosFecha the historialPagosFecha to set
     */
    public void setHistorialPagosFecha(HashMap<Date, HashMap<String, Transaccion > > historialPagosFecha) 
    {
        this.historialPagosFecha = historialPagosFecha;
    }

    // ############################################ Metodos

    /**
     * Genera una nueva pieza del usuario
     * @param titulo
     * @param tipo
     * @param estado
     * @param autores
     * @param anio
     * @param ciudad
     * @param pais
     * @param otrosAtributos
     * @return La nueva pieza del tipo especificado por el usuario
     */
    public Pieza nuevaPiezaParaConsignar(String titulo, String tipo, ArrayList<String> autores, String anio, String ciudad, String pais,
                                            HashMap<String, String> otrosAtributos)
    {
        switch ( tipo )
        {
            case Pieza.CUADRO:
            {
                return new Cuadro(titulo, tipo, Inventario.NO_ESTA, autores, anio, ciudad, pais, this, 
                                        otrosAtributos.get("tecnica"), otrosAtributos.get("alto"), otrosAtributos.get("ancho"), otrosAtributos.get("enmarcacion"));
            }
            case Pieza.ESCULTURA:
            {
                return new Escultura(titulo, tipo, Inventario.NO_ESTA, autores, anio, ciudad, pais, this,
                                        otrosAtributos.get("materiales"), otrosAtributos.get("alto"), otrosAtributos.get("largo"), otrosAtributos.get("ancho"), otrosAtributos.get("usoDeElectricidad"));
            }
            case Pieza.CORTOMETRAJE:
            {
                return new Cortometraje(titulo, tipo, Inventario.NO_ESTA, autores, anio, ciudad, pais, this,
                                        otrosAtributos.get("duracion"), otrosAtributos.get("genero"));
            }
            case Pieza.FOTOGRAFIA:
            {
                return new Fotografia(titulo, tipo, Inventario.NO_ESTA, autores, anio, ciudad, pais, this,
                                        otrosAtributos.get("color"), otrosAtributos.get("alto"), otrosAtributos.get("ancho"));
            }
            default:
            {
                return null;
            }
        }
    }

    /**
     * Consigna una nueva pieza al administrador para que sea verificada
     * @param pieza
     * @param precioVenta
     * @param fechaSalidaGaleria
     * @param administrador
     */
    public void consignarPieza( Pieza pieza, int precioVenta, Date fechaSalidaGaleria, Administrador administrador)
    {
        pieza.setPrecioVenta(precioVenta);
        adquirirPieza( pieza.getTitulo(), pieza);
        administrador.getVerificacionesConsignacionPendientes().add( getUsername() );
        administrador.nuevaPiezaEntrada(pieza);
        administrador.agregarFechaSalidaPieza(pieza.getTitulo(), fechaSalidaGaleria);
    }

    /**
     * bloquea la pieza en la galeria hasta que el administrador verifique la compra
     * @param nombrePieza
     * @param galeria
     */
    public void aplicarComprarPieza( String nombrePieza, Galeria galeria, Administrador administrador )
    {
        administrador.getVerificacionesCompraPiezas().add( getUsername() );
        administrador.getComprasPendientes().add(nombrePieza);
        galeria.bloquearPiezaEnVenta( nombrePieza, getUsername() );
    }

    /**
     * Agrega una nueva oferta de subasta al operador
     * @param nombrePieza
     * @param oferta
     * @param operador
     */
    public boolean nuevaOfertaSubasta( String nombrePieza, int oferta, Operador operador)
    {
        if ( operador.getUsuariosVerificadosSubasta().contains(this.getUsername() ))
        {
            operador.registrarOferta( this, nombrePieza, oferta );
            return true;
        }
        return false;
    }

    /**
     * Busca una pieza que esté en el registro del usuario
     * @param nombrePieza
     * @return la pieza buscada
     */
    public Pieza buscarPiezaUsuario(String nombrePieza)
    {
        if ( buscarPiezaActualUsusario(nombrePieza) != null )
        {
            return buscarPiezaActualUsusario(nombrePieza);
        }
        else if ( buscarPiezaPasadaUsuario(nombrePieza) != null )
        {
            return buscarPiezaPasadaUsuario(nombrePieza);
        }
        return null;
    }

    /**
     * busca la pieza pasada de un usuario
     * @param nombrePieza
     * @return la pieza pasada
     */
    public Pieza buscarPiezaPasadaUsuario(String nombrePieza)
    {
        return piezasPasadas.get(nombrePieza);
    }

    /**
     * busca una pieza actual del usuario
     * @param nombrePieza
     * @return la pieza actual
     */
    public Pieza buscarPiezaActualUsusario(String nombrePieza)
    {
        return piezasActuales.get(nombrePieza);
    }

    /**
     * Agrega una nueva pieza al registro del usuario
     * @param nombrePieza
     * @param pieza
     */
    public void adquirirPieza(String nombrePieza, Pieza pieza)
    {
        Pieza posiblePiezaPasada = buscarPiezaPasadaUsuario(nombrePieza);
        if ( posiblePiezaPasada == null )
        {
            piezasActuales.put(nombrePieza, pieza);
        }
        else
        {
            piezasPasadas.remove(nombrePieza);
            piezasActuales.put(nombrePieza, pieza);
        }
    }

    /**
     * quita una pieza de las piezas actuales del usuario
     * @param nombrePieza
     */
    public void removerPiezaActual(String nombrePieza)
    {
        Pieza pieza = buscarPiezaActualUsusario(nombrePieza);
        piezasActuales.remove(nombrePieza);
        piezasPasadas.put(nombrePieza, pieza);
    }

    /**
     * agrega una nueva transacción al usuario
     * @param transaccion
     * @param fecha
     */
    public void agregarTransaccion(Transaccion transaccion, Date fecha)
    {
        int codigoTransaccion = transaccion.getCodigoTransaccion();
        historialPagosCodigo.put(codigoTransaccion, transaccion);
        historialPagosFecha.put(fecha, new HashMap<String, Transaccion>() );
        historialPagosFecha.get(fecha).put( transaccion.getNombrePieza() , transaccion );
    }

    /**
     * Busca una transacción del usuario basado en la fecha
     * @param fecha
     * @param nombrePieza
     * @return
     */
    public Transaccion buscarTransaccionUsuarioFecha(Date fecha, String nombrePieza)
    {
        HashMap<String, Transaccion> mapaFecha = historialPagosFecha.get(fecha);
        return mapaFecha.get(nombrePieza);
    }
    
    /**
     * Busca una transacción del usuario con base al código de transacción
     * @param codigo
     * @return
     */
    public Transaccion buscarTransaccionUsuarioCodigo(int codigo)
    {
        return historialPagosCodigo.get(codigo);
    }

}
