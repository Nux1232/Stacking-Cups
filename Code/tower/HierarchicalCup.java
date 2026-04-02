package tower;
import java.util.*;
import shapes.*;

/**
 * Clase hija que desplaza (intercambia lugar) con los objetos que sean menores a esta y
 * si llega al fondo, no se puede eliminar de la torre.
 *
 * @author Juan Pablo Cuervo Conteras
 * @author David Felipe Ortiz Salcedo
 * @version 28/03/2026
 */
public class HierarchicalCup extends Cup {
    private boolean reachedBase = false;

    /**
     * Constructor de la clase HierarchicalCup.
     * 
     * @param size El tamaño de la taza hierarchical.
     * @param color El color visual de la taza hierarchical.
     * @param x La coordenada X deseada.
     * @param y La coordenada Y deseada.
     * @param creationId El id único de creación.
     */
    public HierarchicalCup(int size, String color, int x, int y, int creationId) {
        super(size, color, x, y, creationId);
        this.bottom.changeColor("magenta");
    } // Cierre del constructor

    /**
     * Método abstracto que tiene como fin definir el comportamiento de una taza hierarchical.
     * Si la taza hierarchical encuentra un objeto que tenga un tamaño menor a este,
     * lo elimina hasta llegar al fondo y no se deja quitar.
     * 
     * @param items La lista que contiene un item.
     */
    @Override
    public void actionPush(ArrayList<StackItem> items){
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getSize() < this.size) {
                items.add(i, this);
                reachedBase = (i == 0);
                return;
            }
        }
        reachedBase = items.isEmpty();
    } // Cierre del método
    
    /**
     * Método que hace que la taza hierarchical no pueda ser eliminada si está en la base.
     */
    @Override
    public boolean hasReachedBase() {
        return reachedBase;
    } // Cierre del método
} // Cierre de la clase