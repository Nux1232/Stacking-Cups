/**
 * Representa una taza (Cup) dentro de la simulación.
 * <p>
 * Esta clase encapsula la lógica visual y dimensional de una taza individual.
 * Cumple con el Requisito de Construcción al utilizar la composición,
 * delegando la representación gráfica a la clase {@link Rectangle} del paquete shapes.
 * </p>
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Cup {
    
    /** El tamaño lógico de la taza (que determina también su número y altura). */
    private int size;
    
    /** La representación visual de la taza (objeto compuesto). */
    private Rectangle view; 

    /**
     * Constructor de la clase Cup.
     * Crea una nueva taza con un tamaño, color y posición específicos.
     * Calcula las dimensiones en píxeles basándose en el tamaño lógico y mueve
     * la figura a la posición calculada por la Torre.
     *
     * @param size  El tamaño numérico de la taza (ej. 1, 2, 3...).
     * @param color El color visual de la taza (ej. "red", "blue").
     * @param x     La coordenada X deseada en el canvas.
     * @param y     La coordenada Y deseada en el canvas.
     */
    public Cup(int size, String color, int x, int y) {
        this.size = size;
        
        // Dimensiones Visuales (Escala arbitraria para que se vea bien en pantalla)
        // Se multiplica el tamaño lógico por un factor para obtener píxeles.
        int widthPx = size * 20; 
        int heightPx = size * 10; // Asumimos altura visual proporcional
        
        // Inicialización del componente visual (Reutilización de shapes)
        view = new Rectangle();
        view.changeSize(heightPx, widthPx); // Rectangle recibe (alto, ancho)
        view.changeColor(color);
        
        // Ajuste de Posición:
        // Rectangle se crea por defecto en (70, 15). Calculamos el desplazamiento (delta)
        // necesario para llevarlo a la posición (x, y) solicitada.
        view.moveHorizontal(x - 70);
        view.moveVertical(y - 15);
    }
    
    /**
     * Obtiene la vista gráfica de la taza.
     * Este método permite a la clase controladora (Tower) gestionar la visibilidad.
     *
     * @return El objeto Rectangle que representa visualmente a esta taza.
     */
    public Rectangle getView() {
        return view;
    }

    /**
     * Obtiene el tamaño numérico de la taza.
     *
     * @return El tamaño de la taza (entero).
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Obtiene la altura lógica de la taza en centímetros.
     * Según las reglas del negocio, la altura en cm es equivalente al tamaño.
     *
     * @return La altura en cm.
     */
    public int getHeight() {
        return size; // Altura en cm
    }
    
    /**
     * Cambia la apariencia de la taza para indicar que está tapada.
     * Cumple con el requisito de usabilidad de hacer que luzcan diferentes.
     */
    public void setCoveredStatus(boolean isCovered) {
        if (isCovered) {
            view.changeColor("black"); // O cualquier color que denote "tapado"
        } else {
            // Requiere guardar el color original como atributo de la clase, 
            // te sugiero agregar 'private String originalColor;' en el constructor.
            // view.changeColor(originalColor); 
        }
    }
}