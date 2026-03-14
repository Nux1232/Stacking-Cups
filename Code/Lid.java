/**
 * Representa una tapa que se coloca sobre las tazas en la simulación.
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 14/03/2026
 */
public class Lid extends StackItem {
    private Rectangle view;

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
    
    @Override
    /**
     * Obtiene la vista gráfica de la tapa.
     *
     * @return El objeto Rectangle que representa visualmente a la tapa.
     */
    
    public Rectangle getView() {
        return view;
    } // Cierre del método
    
    @Override
    /**
     * Retorna la altura de la tapa (debe ser 1cm).
     * 
     * @return int La altura de la tapa.
     */
    public int getHeight() {
        return 1;
    } // Cierre del método
    
    @Override
    /**
     * Hace que la tapa se pueda visualizar.
     */
    public void makeVisible() {
        view.makeVisible();
    } // Cierre del método
    
    @Override
    /**
     * Hace que la tapa se pueda ver.
     */
    public void makeInvisible() {
        view.makeInvisible();
    } // Cierre del método
    
    @Override
    /**
     * Dice de qué tipo es el item (en este caso, una tapa).
     * 
     * @return String Qué tipo de item es.
     */
    public String getType() {
        return "lid";
    } // Cierre del método
} // Cierre de la clase