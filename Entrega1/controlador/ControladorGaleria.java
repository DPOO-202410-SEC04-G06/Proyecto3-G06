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
    public Usuario usuarioActual;
    private CentralPersistencia centralPersistencia;

    // ############################################ Constructor

    public ControladorGaleria()
    {
        centralPersistencia = new CentralPersistencia();
        galeria = new Galeria();
        usuarioActual = null;
    }

    // ############################################ Getters & Setters

    public Galeria getGaleria()
    {
        return galeria;
    }

    public Usuario getUsuarioActual()
    {
        return usuarioActual;
    }

    // ############################################ Metodos

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
        usuarioActual = galeria.usuarioDeLaSesion;
        return galeria;
    }

}
