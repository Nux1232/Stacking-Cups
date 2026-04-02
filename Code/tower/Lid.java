package tower;
import shapes.*;
import java.util.*;

/**
 * Representa una tapa que se coloca sobre las tazas en la simulación.
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 14/03/2026
 */
public abstract class Lid extends StackItem {
    protected Rectangle view;

    /**
     * Constructor de la clase Lid.
     * 
     * @param size El tamaño de la tapa (debe coincidir con el de la taza).
     * @param color El color de la tapa.
     * @param x     La coordenada X deseada.
     * @param y     La coordenada Y deseada.
     * @param creationId El identificador de orden de creación.
     */
    public Lid(int size, String color, int x, int y, int creationId) {
        super(size, color, creationId);
        int widthPx = (size * 20) + 4;
        int heightPx = 10; 
 
        view = new Rectangle();
        view.changeSize(heightPx, widthPx);
        view.changeColor(color);
        view.moveHorizontal((x - 2) - 70); 
        view.moveVertical(y - 15);
    } // Cierre del constructor
    
    /**
     * Obtiene la vista gráfica de la tapa.
     *
     * @return Rectangle El objeto Rectangle que representa visualmente a la tapa.
     */
    @Override
    public Rectangle getView() {
        return view;
    } // Cierre del método
    
    /**
     * Retorna la altura de la tapa (debe ser 1cm).
     * 
     * @return int La altura de la tapa.
     */
    @Override
    public int getHeight() {
        return 1;
    } // Cierre del método
    
    /**
     * Hace que la tapa se pueda visualizar.
     */
    @Override
    public void makeVisible() {
        view.makeVisible();
    } // Cierre del método
    
    /**
     * Hace que la tapa no se pueda ver.
     */
    @Override
    public void makeInvisible() {
        view.makeInvisible();
    } // Cierre del método
    
    /**
     * Dice de qué tipo es el item (en este caso, una tapa).
     * 
     * @return String Qué tipo de item es.
     */
    @Override
    public String getType() {
        return "lid";
    } // Cierre del método
    
    /**
     * Método que verifica si una tapa puede entrar a la torre.
     * 
     * @param items La lista que contiene tazas y tapas.
     * @return boolean Retorna true si puede entrar o false si no.
     */
    public abstract boolean lidCanEnter(ArrayList<StackItem> items); // Cierre del método
    
    /**
     * Método que verifica si una tapa puede salir de la torre.
     * 
     * @param items La lista que contiene tazas y tapas.
     * @return boolean Retorna true si puede salir o false si no.
     */
    public abstract boolean lidCanExit(ArrayList<StackItem> items, int index); // Cierre del método
    
    /**
     * Método que define el comportamiento de una tapa.
     */
    public abstract void actionPush(ArrayList<StackItem> items); // Cierre del método
} // Cierre de la clase