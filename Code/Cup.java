/**
 * Representa una taza dentro de la simulación.
 * Esta clase encapsula la lógica visual y dimensional de una taza individual.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 07/03/2026
 */

public class Cup extends StackItem {
    private Rectangle view; 

    /**
     * Constructor de la clase Cup.
     *
     * @param i  El identificador de la taza (Ejemplo: 1, 2, 3...).
     * @param color El color visual de la taza (Ejemplo: "red", "blue").
     * @param x     La coordenada X deseada.
     * @param y     La coordenada Y deseada.
     */
    public Cup(int identifier, String color, int x, int y) {
        super(identifier, color);
        int widthPx = identifier * 20; 
        int heightPx = identifier * 10;
        
        view = new Rectangle();
        view.changeSize(heightPx, widthPx);
        view.changeColor(color);
        view.moveHorizontal(x - 70);
        view.moveVertical(y - 15);
    } // Cierre del constructor
    
    /**
     * Obtiene la vista gráfica de la taza.
     * Este método permite a la clase controladora (Tower) gestionar la visibilidad.
     *
     * @return El objeto Rectangle que representa visualmente a la taza.
     */
    public Rectangle getView() {
        return view;
    } // Cierre del método
    
    /**
     * Obtiene la altura de la taza en cm a partir de su identificador.
     *
     * @return La altura en cm.
     */
    public int getHeight() {
        return identifier;
    } // Cierre del método
    
    /**
     * Dice de qué tipo es (en este caso una taza).
     * 
     * @return String Qué tipo de item es.
     */
    public String getType() {
        return "cup";
    } // Cierre del método
    
    /**
     * Hace que la taza se pueda visualizar.
     */
    public void makeVisible() {
        view.makeVisible();
    }
    
    /**
     * Hace que la taza se pueda visualizar.
     */
    public void makeInvisible() {
        view.makeInvisible();
    }
    
    /**
     * Cambia la apariencia de la taza para indicar que está tapada.
     * Cumple con el requisito de usabilidad de hacer que luzcan diferentes.
     */
    public void setCoveredStatus(boolean isCovered) {
        if (isCovered) {
            view.changeColor("blue"); // O cualquier color que denote "tapado"
        } else {
            // Requiere guardar el color original como atributo de la clase, 
            // te sugiero agregar 'private String originalColor;' en el constructor.
            // view.changeColor(originalColor); 
        }
    } // Cierre del método
} //Cierre de la clase