package consola;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * Esta es una clase abstracta que implementa métodos útiles para todas las consolas de la aplicación.
 */
public abstract class ConsolaBasica
{
    /**
     * Le pide al usuario que ingrese una cadena de caracteres
     * @param mensaje El mensaje con el que se solicita la información
     * @return La cadena introducida por el usuario
     */
    protected String pedirCadenaAlUsuario( String mensaje )
    {
        return JOptionPane.showInputDialog(null, mensaje, "Entrada de texto", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Le pide confirmación al usuario, indicándole que debe responder 'sí' o 'no'.
     * @param mensaje El mensaje con el que se solicita la información
     * @return Retorna true únicamente si el usuario responde 'sí', 'si' o 'sí', independientemente de las minúsculas y las mayúsculas. Retorna false en cualquier otro caso.
     */
    protected boolean pedirConfirmacionAlUsuario( String mensaje )
    {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }

    /**
     * Le pide al usuario que ingrese un número que no puede tener cifras decimales
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected int pedirEnteroAlUsuario( String mensaje )
    {
        while(true) {
            try {
                String input = JOptionPane.showInputDialog(null, mensaje, "Entrada de número", JOptionPane.QUESTION_MESSAGE);
                return Integer.parseInt(input);
            } catch( NumberFormatException nfe ) {
                JOptionPane.showMessageDialog(null, "El valor digitado no es un entero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Le pide al usuario que ingrese un número que puede tener cifras decimales
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected double pedirNumeroAlUsuario( String mensaje )
    {
        while(true) {
            try {
                String input = JOptionPane.showInputDialog(null, mensaje, "Entrada de número", JOptionPane.QUESTION_MESSAGE);
                return Double.parseDouble(input);
            } catch( NumberFormatException nfe ) {
                JOptionPane.showMessageDialog(null, "El valor digitado no es un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Le pide al usuario que seleccione una de las opciones que se le presenten
     * @param coleccionOpciones
     * @return Retorna la opción seleccionada (el valor, no su posición).
     */
    protected String pedirOpcionAlUsuario( Collection<? extends Object> coleccionOpciones )
    {
        String[] opciones = new String[coleccionOpciones.size()];
        int pos = 0;
        for( Iterator<? extends Object> iterator = coleccionOpciones.iterator(); iterator.hasNext(); pos++ )
        {
            opciones[pos] = iterator.next().toString();
        }

        String opcionSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Selección de opción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return opcionSeleccionada;
    }

    /**
     * Muestra un menú y le pide al usuario que seleccione una opción
     * @param nombreMenu El nombre del menu
     * @param opciones Las opciones que se le presentan al usuario
     * @return El número de la opción seleccionada por el usuario, contando desde 1
     */
    protected int mostrarMenu( String nombreMenu, String[] opciones )
    {
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, nombreMenu, "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return opcionSeleccionada + 1;
    }
}
