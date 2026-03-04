import javax.swing.*;

/**
 * Representa una tapa (Lid) que se coloca sobre las tazas en la simulación.
 * <p>
 * Esta clase encapsula la lógica visual de la tapa. Cumple con el Requisito Funcional
 * de tener una altura fija de 1 cm y con el Requisito de Construcción al reutilizar
 * la clase {@link Rectangle} del paquete shapes mediante composición.
 * </p>
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Lid {
    
    /** El tamaño numérico asociado a la tapa (correspondiente a su taza). */
    private int size;
    
    /** La representación visual de la tapa (objeto compuesto). */
    private Rectangle view;

    /**
     * Constructor de la clase Lid.
     * Crea una tapa visualmente distinguible.
     * <p>
     * Implementa la lógica de dimensiones:
     * 1. La altura se fija en 10 píxeles (equivalente a 1 cm según el requisito).
     * 2. El ancho se calcula para ser ligeramente mayor que la taza, mejorando la representación visual.
     * </p>
     *
     * @param size  El tamaño numérico de la tapa.
     * @param color El color de la tapa (debe coincidir con el de la taza).
     * @param x     La coordenada X deseada en el canvas.
     * @param y     La coordenada Y deseada en el canvas.
     */
    public Lid(int size, String color, int x, int y) {
        this.size = size;
        
        // La tapa es un poco más ancha que la taza para que parezca una tapa
        // Se suman 4 píxeles extra al ancho estándar de la taza
        int widthPx = (size * 20) + 4; 
        
        // Requisito Funcional: Las tapas miden 1 cm de alto.
        // Asumimos escala 1 cm = 10 px
        int heightPx = 10; 
        
        // Inicialización del componente visual
        view = new Rectangle();
        view.changeSize(heightPx, widthPx);
        view.changeColor(color);
        
        // Ajuste de posición (centrar respecto a la taza que es más angosta)
        // Se resta 2 a X para compensar los 4 píxeles extra de ancho (centrado)
        // Se ajusta respecto a la posición por defecto de Rectangle (70, 15)
        view.moveHorizontal((x - 2) - 70); 
        view.moveVertical(y - 15);
    } // Cierre del constructor
    
    /**
     * Obtiene la vista gráfica de la tapa.
     * Permite a la clase Tower gestionar la visibilidad de este objeto.
     *
     * @return El objeto Rectangle que representa visualmente a esta tapa.
     */
    public Rectangle getView() {
        return view;
    } // Cierre del método

    /**
     * Obtiene el tamaño numérico de la tapa.
     *
     * @return El tamaño de la tapa.
     */
    public int getSize() {
        return size;
    } // Cierre del método
} // Cierre de la clase