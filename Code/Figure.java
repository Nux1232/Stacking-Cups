/**
 * Clase padre abstracta que agrupa los comportamientos comunes de las figuras.
 */
public abstract class Figure {
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;

    public Figure(int xPosition, int yPosition, String color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.isVisible = false;

    }

    public void makeVisible() {
        isVisible = true;
        draw();
    }

    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    public void moveRight() { moveHorizontal(20); }
    public void moveLeft() { moveHorizontal(-20); }
    public void moveUp() { moveVertical(-20); }
    public void moveDown() { moveVertical(20); }

    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }
    
    public void moveTo(int newX, int newY) {
        erase();
        xPosition = newX;
        yPosition = newY;
        draw();
    }

    public void slowMoveHorizontal(int distance) {
        int delta = (distance < 0) ? -1 : 1;
        distance = Math.abs(distance);
        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    public void slowMoveVertical(int distance) {
        int delta = (distance < 0) ? -1 : 1;
        distance = Math.abs(distance);
        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Borra la figura de la pantalla. Como la lógica es igual para todas,
     * se implementa directamente en la clase padre.
     */
    protected void erase() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Dibuja la figura. Cada figura hija debe definir CÓMO dibujarse a sí misma.
     */
    protected abstract void draw();
}