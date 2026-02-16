
/**
 * A cup that moves.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Cup {
    private int size;
    private Rectangle view; // Reutilizamos shapes.Rectangle

    public Cup(int size, String color, int x, int y) {
        this.size = size;
        
        // Dimensiones Visuales (Escala arbitraria para que se vea bien)
        int widthPx = size * 20; 
        int heightPx = size * 10; // Asumimos altura visual proporcional
        
        view = new Rectangle();
        view.changeSize(heightPx, widthPx); // Rectangle(alto, ancho)
        view.changeColor(color);
        
        // Mover a la posición calculada por Tower
        // Rectangle nace en (70, 15). Calculamos el desplazamiento.
        view.moveHorizontal(x - 70);
        view.moveVertical(y - 15);
    }
    
    public Rectangle getView() {
        return view;
    }

    public int getSize() {
        return size;
    }
    
    public int getHeight() {
        return size; // Altura en cm
    }
}