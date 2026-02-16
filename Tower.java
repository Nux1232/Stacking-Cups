/**
 * A tower that has cups and lids.
 *
 * @author Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version 1.0
 */
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Simulador de Torre de Tazas.
 * Cumple Requisito de Construcción: Reutiliza componentes de shapes.
 */
public class Tower {
    // Dimensiones
    private int width;
    private int maxHeight;
    
    // Almacenamiento (Requisito: permitir tazas y tapas mezcladas)
    private ArrayList<Object> items; 
    
    // Estado
    private boolean isVisible;
    private boolean lastOperationOk;

    // Configuración Visual (Escala: 1cm = 10 pixeles)
    private static final int PIXELS_PER_CM = 10; 
    private static final int GROUND_Y = 250; 
    private static final int CENTER_X = 150;

    // --- REQUISITO 1: Crear torre dados ancho y alto ---
    public Tower(int width, int maxHeight) {
        this.width = width;
        this.maxHeight = maxHeight;
        this.items = new ArrayList<>();
        this.isVisible = false;
        this.lastOperationOk = true;
        drawRuler(); // Requisito Usabilidad 4: Marcar centímetros
    }

    // --- REQUISITO 2: Adicionar Taza ---
    public void pushCup(int size) {
        // Validación Diseño: Solo una taza por número [cite: 62]
        if (existsCup(size)) {
            showError("Ya existe la taza " + size);
            lastOperationOk = false;
            return;
        }
        
        // Validación Funcional: Altura máxima [cite: 16]
        // Asumimos que la altura de la taza es igual a su tamaño (en cm)
        int cupHeightCm = size; 
        if (currentHeight() + cupHeightCm > maxHeight) {
            showError("La torre excede la altura máxima.");
            lastOperationOk = false;
            return;
        }

        // Usabilidad 2 y 3: Color consistente
        String color = getColorForSize(size);
        
        // Cálculo de posición visual
        int yPos = calculateNextY(cupHeightCm);

        // Creación (Construcción: reutiliza shapes internamente)
        Cup newCup = new Cup(size, color, CENTER_X, yPos);
        if (isVisible) newCup.getView().makeVisible();
        
        items.add(newCup);
        lastOperationOk = true;
    }

    // --- REQUISITO 3: Adicionar Tapa ---
    public void pushLid(int size) {
        // Validación Diseño: Solo una tapa por número
        if (existsLid(size)) {
            showError("Ya existe la tapa " + size);
            lastOperationOk = false;
            return;
        }

        // Requisito Funcional: Tapa mide 1 cm 
        int lidHeightCm = 1; 

        if (currentHeight() + lidHeightCm > maxHeight) {
            showError("No cabe la tapa.");
            lastOperationOk = false;
            return;
        }

        String color = getColorForSize(size); // Mismo color de la taza
        int yPos = calculateNextY(lidHeightCm);

        Lid newLid = new Lid(size, color, CENTER_X, yPos);
        if (isVisible) newLid.getView().makeVisible();

        items.add(newLid);
        lastOperationOk = true;
    }

    // --- REQUISITO 6 y 7: Consultar Información ---
    public int height() {
        return currentHeight();
    }

    // --- REQUISITO 8: Visibilidad ---
    public void makeVisible() {
        isVisible = true;
        drawRuler();
        for (Object item : items) {
            if (item instanceof Cup) ((Cup)item).getView().makeVisible();
            if (item instanceof Lid) ((Lid)item).getView().makeVisible();
        }
    }

    public void makeInvisible() {
        isVisible = false;
        // Borrar regla visualmente (opcional si implementas eraseRuler)
        for (Object item : items) {
            if (item instanceof Cup) ((Cup)item).getView().makeInvisible();
            if (item instanceof Lid) ((Lid)item).getView().makeInvisible();
        }
    }

    public boolean ok() {
        return lastOperationOk;
    }

    // --- MÉTODOS PRIVADOS (Auxiliares) ---

    // Calcula la posición Y para el nuevo elemento (apilado invertido)
    private int calculateNextY(int itemHeightCm) {
        int currentHeightPx = currentHeight() * PIXELS_PER_CM;
        int itemHeightPx = itemHeightCm * PIXELS_PER_CM;
        return GROUND_Y - currentHeightPx - itemHeightPx;
    }

    private int currentHeight() {
        int h = 0;
        for (Object item : items) {
            if (item instanceof Cup) h += ((Cup)item).getHeight(); // Altura lógica
            else if (item instanceof Lid) h += 1; // Tapa siempre 1
        }
        return h;
    }

    // Solución a tu error de compilación
    private boolean existsCup(int size) {
        for (Object item : items) {
            if (item instanceof Cup && ((Cup)item).getSize() == size) return true;
        }
        return false;
    }

    private boolean existsLid(int size) {
        for (Object item : items) {
            if (item instanceof Lid && ((Lid)item).getSize() == size) return true;
        }
        return false;
    }

    private String getColorForSize(int size) {
        String[] colors = {"red", "blue", "green", "magenta", "black", "yellow"};
        return colors[size % colors.length];
    }

    // Requisito Usabilidad 5: JOptionPane solo si visible 
    private void showError(String msg) {
        if (isVisible) JOptionPane.showMessageDialog(null, msg);
    }

    // Requisito Usabilidad 4: Marcar centímetros
    private void drawRuler() {
        if (!isVisible) return;
        // Dibuja una línea vertical simple usando Rectangle
        Rectangle axis = new Rectangle();
        axis.changeColor("black");
        axis.changeSize(maxHeight * PIXELS_PER_CM, 2); // Alto, Ancho
        // Mover a posición (Un poco a la izquierda del centro)
        int rulerX = CENTER_X - 60; 
        int moveX = rulerX - 70; // 70 es default X de Rectangle
        int moveY = (GROUND_Y - (maxHeight * PIXELS_PER_CM)) - 15; // 15 es default Y
        
        axis.moveHorizontal(moveX);
        axis.moveVertical(moveY);
        axis.makeVisible();
    }
}