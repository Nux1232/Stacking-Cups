package tower;
import java.util.*;
import shapes.*;

/**
 * Representa una taza dentro de la simulación.
 * Esta clase encapsula la lógica visual y dimensional de una taza individual.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 30/03/2026
 */

public abstract class Cup extends StackItem {
    protected Rectangle left;
    protected Rectangle right;
    protected Rectangle bottom;

    /**
     * Constructor de la clase Cup.
     *
     * @param size El tamaño de la taza.
     * @param color El color visual de la taza.
     * @param x La coordenada X deseada.
     * @param y La coordenada Y deseada.
     * @param creationId El id único de creación.
     */
    public Cup(int size, String color, int x, int y, int creationId) {
        super(size, color, creationId);
        int widthPx = size * 20; 
        int heightPx = size * 10;
        int thickness = 10;
        
        left = new Rectangle();
        left.changeSize(heightPx, thickness);
        left.changeColor(color);
        left.moveHorizontal(x - 70);
        left.moveVertical(y - 15);
        
        right = new Rectangle();
        right.changeSize(heightPx, thickness);
        right.changeColor(color);
        right.moveHorizontal(x - 70 + widthPx - thickness);
        right.moveVertical(y - 15);
        
        bottom = new Rectangle();
        bottom.changeSize(thickness, widthPx);
        bottom.changeColor(color);
        bottom.moveHorizontal(x - 70);
        bottom.moveVertical(y - 15 + heightPx - thickness);
    } // Cierre del constructor
    
    /**
     * Obtiene la vista gráfica de la taza.
     * Este método permite a la clase controladora (Tower) gestionar la visibilidad.
     *
     * @return El objeto Rectangle que representa visualmente a la taza.
     */
    @Override
    public Rectangle getView() {
        return bottom;
    } // Cierre del método
    
    /**
     * Mueve el objeto a una posición indicada.
     * 
     * @param x La posición en x deseada.
     * @param y La posición en y deseada.
     */
    public void moveTo(int x, int y) {
        int widthPx = size * 20;
        int heightPx = size * 10;
        int thickness = 8;
        
        left.moveTo(x - 20, y);
        right.moveTo(x - 20 + widthPx - thickness, y);
        bottom.moveTo(x - 20, y + heightPx - thickness);
    } // Cierre del método
    
    /**
     * Obtiene la altura de la taza en cm a partir de su tamaño.
     *
     * @return La altura en cm.
     */
    @Override
    public int getHeight() {
        return size;
    } // Cierre del método
    
    /**
     * Dice de qué tipo es (en este caso una taza).
     * 
     * @return String Qué tipo de item es.
     */
    @Override
    public String getType() {
        return "cup";
    } // Cierre del método
    
    /**
     * Hace que la taza se pueda visualizar.
     */
    @Override
    public void makeVisible() {
        left.makeVisible();
        right.makeVisible();
        bottom.makeVisible();
    } // Cierre del método
    
    /**
     * Hace que la taza no se pueda visualizar.
     */
    @Override
    public void makeInvisible() {
        left.makeInvisible();
        right.makeInvisible();
        bottom.makeInvisible();
    } // Cierre del método
    
    /**
     * Cambia la apariencia de la taza para indicar que está tapada.
     * Cumple con el requisito de usabilidad de hacer que luzcan diferentes.
     */
    public void setCoveredStatus(boolean isCovered) {
        if (isCovered) {
            left.changeColor("blue");
            right.changeColor("blue");
            bottom.changeColor("blue");
        } 
    } // Cierre del método
    
    /**
     * Método abstracto que tiene como fin definir el comportamiento de una taza.
     * 
     * @param items La lista que contiene un item.
     */
    public abstract void actionPush(ArrayList<StackItem> items); // Cierre del método
} //Cierre de la clase