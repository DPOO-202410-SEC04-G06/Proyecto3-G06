package pagos;

import java.io.Serializable;
import java.util.*;

public class Transaccion implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8236629865870912511L;
	// ############################################ Atributos publicos
    public static final boolean NO_SUBASTADO = false;
    public static final boolean SUBASTADO = true;

    // ############################################ Atributos Transaccion
    private int metdoDePago;
    private boolean subastado;
    private String nombreComprador;
    private String nombreVendedor;
    private int precio;
    private String nombrePieza;
    private Date fecha;
    private int codigoTransaccion;
    
    // ############################################ Constructor

    public Transaccion(String nombreComprador, String nombreVendedor, Date fecha, boolean isSubasta, int precio, String nombrePieza, int metodoPago, int codigoTransaccion) 
    {
        this.nombreComprador = nombreComprador;
        this.nombreVendedor = nombreVendedor;
        this.fecha = fecha;
        this.subastado = isSubasta;
        this.precio = precio;
        this.nombrePieza = nombrePieza;
        this.metdoDePago = metodoPago;
        this.codigoTransaccion = codigoTransaccion;
    }

    // ############################################ Getters & Setters

    public int getMetdoDePago() 
    {
        return metdoDePago;
    }

    public void setMetdoDePago(int metdoDePago) 
    {
        this.metdoDePago = metdoDePago;
    }

    public boolean isSubastado() {
        return subastado;
    }

    public void setSubastado(boolean subastado) 
    {
        this.subastado = subastado;
    }

    public String getNombreComprador() 
    {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) 
    {
        this.nombreComprador = nombreComprador;
    }

    public String getNombreVendedor() 
    {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) 
    {
        this.nombreVendedor = nombreVendedor;
    }

    public int getPrecio() 
    {
        return precio;
    }

    public void setPrecio(int precio) 
    {
        this.precio = precio;
    }

    public String getNombrePieza() 
    {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) 
    {
        this.nombrePieza = nombrePieza;
    }

    public Date getFecha() 
    {
        return fecha;
    }

    public void setFecha(Date fecha) 
    {
        this.fecha = fecha;
    }

    public int getCodigoTransaccion() 
    {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(int codigoTransaccion) 
    {
        this.codigoTransaccion = codigoTransaccion;
    }
}
