package tower;
import java.util.*;
import shapes.*;

/**
 * Clase hija que elimina las tapas que le impidan el paso al ser colocada en la torre.
 *
 * @author Juan Pablo Cuervo Conteras
 * @author David Felipe Ortiz Salcedo
 * @version 28/03/2026
 */
public class OpenerCup extends Cup {

    /**
     * Constructor de la clase OpenerCup.
     * 
     * @param size El tamaño de la taza opener.
     * @param color El color visual de la taza opener.
     * @param x La coordenada X deseada.
     * @param y La coordenada Y deseada.
     * @param creationId El id único de creación.
     */
    public OpenerCup(int size, String color, int x, int y, int creationId) {
        super(size, color, x, y, creationId);
        this.bottom.changeColor("yellow");
    } // Cierre del constructor

    /**
     * Método abstracto que tiene como fin definir el comportamiento de una taza opener.
     * Si la taza opener encuentra una tapa, entonces la elimina hasta
     * encontrar una taza cualquiera.
     * 
     * @param items La lista que contiene un item.
     */
    @Override
    public void actionPush(ArrayList<StackItem> items){
        for(StackItem item: items) {
            if(item.getType().equals("lid")) {
                item.makeInvisible();
            }
        }
        items.removeIf(item -> item.getType().equals("lid"));
    } // Cierre del método
} // Cierre de la clase