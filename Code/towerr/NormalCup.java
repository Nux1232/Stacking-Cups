package towerr;
import java.util.*;
import shapes.*;

/**
 * Clase hija que se comporta igual a la clase padre.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 28/03/2026
 */
public class NormalCup extends Cup {

    /**
     * Constructor de la clase NormalCup.
     * 
     * @param size El tamaño de la taza normal.
     * @param color El color visual de la taza normal.
     * @param x La coordenada X deseada.
     * @param y La coordenada Y deseada.
     * @param creationId El id único de creación.
     */
    public NormalCup(int size, String color, int x, int y, int creationId) {
        super(size, color, x, y, creationId);
    } // Cierre del constructor
    
    /**
     * Método abstracto que tiene como fin definir el comportamiento de una taza normal.
     * Al ser una taza como siempre, no hace nada.
     * 
     * @param items La lista que contiene un item.
     */
    @Override
    public void actionPush(ArrayList<StackItem> items){
    } // Cierre del método
} // Cierre de la clase