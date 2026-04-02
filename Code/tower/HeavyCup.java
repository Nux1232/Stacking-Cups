package tower;
import java.util.*;
import shapes.*;

/**
 * Clase hija que no puede ser removida de la torre una vez que entra.
 *
 * @author Juan Pablo Cuervo Conteras
 * @author David Felipe Ortiz Salcedo
 * @version 01/04/2026
 */
public class HeavyCup extends Cup {

    /**
     * Constructor de la clase HeavyCup.
     * 
     * @param size El tamaño de la taza heavy.
     * @param color El color visual de la taza heavy.
     * @param x La coordenada X deseada.
     * @param y La coordenada Y deseada.
     * @param creationId El id único de creación.
     */
    public HeavyCup(int size, String color, int x, int y, int creationId) {
        super(size, color, x, y, creationId);
        this.bottom.changeColor("green");
    } // Cierre del constructor

    /**
     * Método abstracto que tiene como fin definir el comportamiento de una taza heavy.
     * Actúa como una taza normal al entrar.
     * 
     * @param items La lista que contiene un item.
     */
    @Override
    public void actionPush(ArrayList<StackItem> items){
        // Comportamiento normal al empujar
    } // Cierre del método

    /**
     * Método que hace que la taza pesada no pueda ser eliminada.
     * Engaña al simulador diciendo que siempre ha alcanzado la base.
     */
    @Override
    public boolean hasReachedBase() {
        return true;
    } // Cierre del método
} // Cierre de la clase
