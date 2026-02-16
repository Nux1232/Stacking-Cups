
import javax.swing.*;

/**
 * A lid that moves.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
public class Lid {
    private int size;
    private Rectangle view;

    public Lid(int size, String color, int x, int y) {
        this.size = size;
        
        // La tapa es un poco más ancha que la taza para que parezca una tapa
        int widthPx = (size * 20) + 4; 
        int heightPx = 10; // 1 cm = 10 px
        
        view = new Rectangle();
        view.changeSize(heightPx, widthPx);
        view.changeColor(color);
        
        // Ajuste de posición (centrar respecto a la taza que es más angosta)
        view.moveHorizontal((x - 2) - 70); // -2 para centrar el ancho extra
        view.moveVertical(y - 15);
    }
    
    public Rectangle getView() {
        return view;
    }

    public int getSize() {
        return size;
    }
}