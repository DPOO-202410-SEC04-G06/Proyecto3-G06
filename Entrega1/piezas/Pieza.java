package piezas;

import java.util.*;

import usuarios.UsuarioCorriente;

import java.io.Serializable;

public abstract class Pieza implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9032654481485444924L;
	// ############################################ Atributos publicos
    public static final int SIN_PRECIO = -1;
    public static final String CUADRO = "Cuadro";
    public static final String ESCULTURA = "Escultura";
    public static final String CORTOMETRAJE = "Cortometraje";
    public static final String FOTOGRAFIA = "Fotografia";

    // ############################################ Atributos Pieza
    private String titulo;
    private String tipo;
    private int estado;
    private ArrayList<String> autores;
    private String anio;
    private String ciudad;
    private String pais;
    private UsuarioCorriente propietario;
    private int precioVenta;
    private int precioMinSubasta;
    private int precioInicialSubasta;
    
    /*
     * Nuevo entrega 2: mapa de usuarios que fueron dueños se las pieza
     * Llaves: username del usuario
     * Valores: informacion del historial de la pieza
     */
    private HashMap< String, HashMap< String, Object > > mapaUsuarios;

    // ############################################ Constructor

    public Pieza(String titulo, String tipo, int estado, ArrayList<String> autores, String anio, String ciudad, String pais, UsuarioCorriente propietario)
    {
        this.titulo = titulo;
        this.tipo = tipo;
        this.estado = estado;
        this.autores = autores;
        this.anio = anio;
        this.ciudad = ciudad;
        this.pais = pais;
        this.propietario = propietario;
    }

    // ############################################ Getters & Setters

    /**
     * @return String return the titulo
     */
    public String getTitulo() 
    {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) 
    {
        this.titulo = titulo;
    }

    /**
     * @return String return the tipo
     */
    public String getTipo() 
    {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }

    /**
     * @return int return the estado
     */
    public int getEstado() 
    {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) 
    {
        this.estado = estado;
    }

    /**
     * @return ArrayList<String> return the autores
     */
    public ArrayList<String> getAutores() 
    {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(ArrayList<String> autores) 
    {
        this.autores = autores;
    }

    /**
     * @return String return the anio
     */
    public String getAnio() 
    {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) 
    {
        this.anio = anio;
    }

    /**
     * @return String return the ciudad
     */
    public String getCiudad() 
    {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) 
    {
        this.ciudad = ciudad;
    }

    /**
     * @return String return the pais
     */
    public String getPais() 
    {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) 
    {
        this.pais = pais;
    }

    /**
     * @return Usuario return the propietario
     */
    public UsuarioCorriente getPropietario() 
    {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(UsuarioCorriente propietario) 
    {
        this.propietario = propietario;
    }

    /**
     * @return int return the precioVenta
     */
    public int getPrecioVenta() 
    {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(int precioVenta) 
    {
        this.precioVenta = precioVenta;
    }

    /**
     * @return int return the precioMinSubasta
     */
    public int getPrecioMinSubasta() 
    {
        return precioMinSubasta;
    }

    /**
     * @param precioMinSubasta the precioMinSubasta to set
     */
    public void setPrecioMinSubasta(int precioMinSubasta) 
    {
        this.precioMinSubasta = precioMinSubasta;
    }

    /**
     * @return int return the precioInicialSubasta
     */
    public int getPrecioInicialSubasta() 
    {
        return precioInicialSubasta;
    }

    /**
     * @param precioInicialSubasta the precioInicialSubasta to set
     */
    public void setPrecioInicialSubasta(int precioInicialSubasta) 
    {
        this.precioInicialSubasta = precioInicialSubasta;
    }

    // ############################################ Metodos

    /**
     * Retorna el nombre del propietario actual de la pieza
     * @return nombre del propietario
     */
    public String getNombrePropietario()
    {
        return propietario.getUsername();
    }

    /**
     * Retorna el telefono del propietario
     * @return telefono del propietario
     */
    public String getTelefonoPropietario()
    {
        return propietario.getTelefono();
    }

    /**
     * Cambia el propietario de una pieza
     * @param usuario
     */
    public void cambiarPropietarioPieza(UsuarioCorriente usuario)
    {
        propietario = usuario;
    }

    /**
     * Retorna la información solicitada de una pieza específica
     * @param informacion : atributo de la pieza
     * @return el atributo solicitado
     */
    public abstract String getInformacion(String informacion);

}
