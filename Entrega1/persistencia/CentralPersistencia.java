package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import galeria.Galeria;

public class CentralPersistencia 
{
    // ############################################ Atributos publicos
    public static final String FOLDER = "./Entrega1Datos/";
    public static String FILENAME = "galeria_info";

    // ############################################ Métodos

    /**
     * 
     * @param newFilename
     */
    public static void cambiarFilename(String newFilename)
    {
        FILENAME = newFilename;
    }

    /**
     * 
     * @param archivo
     * @param galeria
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public Galeria cargarGaleria(String archivo) throws FileNotFoundException, IOException, ClassNotFoundException 
	{

		File arch = new File( archivo );
		if( arch.exists( ) ) 
		{
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream( arch ) );
            Galeria galeria = ( Galeria ) ois.readObject( ); 
            ois.close( );
            return galeria;
		}
		else
		{
		    return new Galeria( );
		}
	}

    /**
     * 
     * @param archivo
     * @param galeria
     * @throws FileNotFoundException
     * @throws IOException
     */
	public void salvarGaleria(String archivo, Galeria galeria) throws FileNotFoundException, IOException
	{
		File arch = new File(archivo);
		ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( arch ) );
		oos.writeObject( galeria );
		oos.close( );
	}
}
