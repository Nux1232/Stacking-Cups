/**
 * Clase padre que puede ser una taza o tapa.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 14/03/2026
 */
public abstract class StackItem {
    protected int size;
    protected int creationId;
    protected String color;
    
    /**
     * Constructor de la clase StackItem.
     * 
     * @param size El tamaño del item.
     * @param color El color del item.
     * @param creationId El identificador único por orden de creación.
     */
    public StackItem(int size, String color, int creationId) {
        this.size = size;
        this.color = color;
        this.creationId = creationId;
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
     * Obtiene el identificador único de creación de un item.
     * @return int El identificador de creación.
     */
    public int getIdentifier() {
        return creationId;
    } //Cierre del método

    /**
     * Obtiene el tamaño de un item.
     * @return int El tamaño del item.
     */
    public int getSize() {
        return size;
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