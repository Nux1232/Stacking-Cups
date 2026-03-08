/**
 * Clase padre que puede ser una taza o tapa.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 07/03/2026
 */
public abstract class StackItem {
    protected int identifier;
    protected String color;
    
    /**
     * Constructor de la clase StackItem.
     * 
     * @param size El identificador del item.
     * @param color El color del item.
     */
    public StackItem(int identifier, String color) {
        this.identifier = identifier;
        this.color = color;
    } // Cierre del constructor
    
    /**
     * Retorna la vista gráfica de un item: taza o tapa.
     * @return Rectangle El objeto que permite representar visualmente el item.
     */ 
    public abstract Rectangle getView(); // Cierre del método
    
    /**
     * Retorna la altura de un item: taza o tapa.
     * @return int La altura del item.
     */
    public abstract int getHeight(); // Cierre del método
    
    /**
     * Obtiene el tamaño de un item a partir de su identificador: taza o tapa.
     * @return int El tamaño del item.
     */
    public int getIdentifier() {
        return identifier;
    } //Cierre del método
    
    /**
     * Retorna si es una taza o tapa.
     */
    public abstract String getType(); // Cierre del método
    
    /**
     * Hace que el item se pueda visualizar.
     */
    public abstract void makeVisible(); // Cierre del método
    
    /**
     * Hace que el item no se pueda ver.
     */
    public abstract void makeInvisible(); // Cierre del método
} // Cierre de la clase