package shapes;
import java.awt.Polygon;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * Hereda de Figure.
 */
public class Square extends Figure {
    private int size;

    /**
     * Create a new square at default position with default color.
     */
    public Square(){
        super(60, 50, "red");
        size = 30;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize) {
        erase();
        size = newSize;
        draw();
    }

    /*
     * Draw the square with current specifications on screen.
     */
    @Override
    protected void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new java.awt.Rectangle(xPosition, yPosition, size, size));
            canvas.wait(10);
        }
    }
}
