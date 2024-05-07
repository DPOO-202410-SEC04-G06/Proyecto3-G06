package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import galeria.Galeria;
import persistencia.CentralPersistencia;
import usuarios.Usuario;

public class ControladorGaleria 
{
    // ############################################ Atributos Controlador Galeria
    public Galeria galeria;
    public Usuario usuarioDeLaSesion;
    private CentralPersistencia centralPersistencia;

    // ############################################ Constructor

    public ControladorGaleria()
    {
        centralPersistencia = new CentralPersistencia();
        galeria = new Galeria();
        usuarioDeLaSesion = null;
    }

    // ############################################ Getters & Setters

    public Galeria getGaleria()
    {
        return galeria;
    }

    public Usuario getusuarioDeLaSesion()
    {
        return usuarioDeLaSesion;
    }

    // ############################################ Metodos

    /**
     * Inicia sesión con los datos de un usuario
     * @param username
     * @param password
     * @return resultado del inicio de sesión
     */
    public boolean iniciarSesion( String username, String password )   
    {
        boolean result = galeria.iniciarSesion(username, password);
        usuarioDeLaSesion = galeria.usuarioDeLaSesion;
        return result;
    }
    
    /**
     * Cierra la sesion actual
     */
    public void cerrarSesion()
    {
    	galeria.cerrarSesion();
    	usuarioDeLaSesion = null;
    }

    /**
     * Guarda el estado actual del programa
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public void salvarGaleria( Galeria galeria ) throws IOException
    {
        centralPersistencia.salvarGaleria( CentralPersistencia.FOLDER + CentralPersistencia.FILENAME, galeria );
    }

    /**
     * Carga la última instancia del programa que haya sido guardada
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    public Galeria cargarGaleria() throws ClassNotFoundException, IOException
    {
        Galeria galeria = centralPersistencia.cargarGaleria( CentralPersistencia.FOLDER + CentralPersistencia.FILENAME );
        usuarioDeLaSesion = galeria.usuarioDeLaSesion;
        this.galeria = galeria;
        return galeria;
    }

}
