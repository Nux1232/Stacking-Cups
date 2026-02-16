import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Simulador de Torre de Tazas.
 * <p>
 * Esta clase representa una torre que permite apilar tazas (Cups) y tapas (Lids).
 * Gestiona la lógica de apilamiento, restricciones de altura y visualización.
 * Cumple con el Requisito de Construcción al reutilizar componentes del paquete shapes.
 * </p>
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 1.0
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

    /**
     * Crea una nueva torre con las dimensiones especificadas.
     * Requisito 1: Crear torre dados ancho y alto.
     *
     * @param width     El ancho deseado de la torre.
     * @param maxHeight La altura máxima permitida para la torre (en cm).
     */
    public Tower(int width, int maxHeight) {
        this.width = width;
        this.maxHeight = maxHeight;
        this.items = new ArrayList<>();
        this.isVisible = false;
        this.lastOperationOk = true;
        drawRuler(); // Requisito Usabilidad 4: Marcar centímetros
    }

    /**
     * Adiciona una taza a la torre.
     * Verifica que la taza no exista previamente y que quepa en la altura disponible.
     * Requisito 2: Adicionar una taza de la torre.
     *
     * @param size El tamaño de la taza a adicionar.
     */
    public void pushCup(int size) {
        // Validación Diseño: Solo una taza por número
        if (existsCup(size)) {
            showError("Ya existe la taza " + size);
            lastOperationOk = false;
            return;
        }
        
        // Validación Funcional: Altura máxima
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

    /**
     * Adiciona una tapa a la torre.
     * Verifica que la tapa no exista previamente y que quepa en la altura disponible.
     * Las tapas siempre miden 1 cm de altura.
     * Requisito 3: Adicionar una tapa de la torre.
     *
     * @param size El tamaño asociado a la tapa.
     */
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

    /**
     * Consulta la altura actual de los elementos apilados.
     * Requisito 6: Consultar la altura de los elementos apilados.
     *
     * @return La altura total actual de la torre en centímetros.
     */
    public int height() {
        return currentHeight();
    }

    /**
     * Hace visible el simulador.
     * Muestra la regla y todos los elementos (tazas y tapas) en el canvas.
     * Requisito 8: Hacer visible el simulador.
     */
    public void makeVisible() {
        isVisible = true;
        drawRuler();
        for (Object item : items) {
            if (item instanceof Cup) ((Cup)item).getView().makeVisible();
            if (item instanceof Lid) ((Lid)item).getView().makeVisible();
        }
    }

    /**
     * Hace invisible el simulador.
     * Oculta todos los elementos visuales del canvas.
     * Requisito 8: Hacer invisible el simulador.
     */
    public void makeInvisible() {
        isVisible = false;
        // Borrar regla visualmente (opcional si implementas eraseRuler)
        for (Object item : items) {
            if (item instanceof Cup) ((Cup)item).getView().makeInvisible();
            if (item instanceof Lid) ((Lid)item).getView().makeInvisible();
        }
    }

    /**
     * Indica si la última operación realizada fue exitosa.
     *
     * @return true si la última operación se completó correctamente, false en caso contrario.
     */
    public boolean ok() {
        return lastOperationOk;
    }

    // --- MÉTODOS PRIVADOS (Auxiliares) ---

    /**
     * Calcula la coordenada Y en píxeles para posicionar el siguiente elemento.
     * Se basa en un apilamiento invertido (Y=0 es arriba).
     *
     * @param itemHeightCm La altura del elemento a adicionar en cm.
     * @return La coordenada Y calculada.
     */
    private int calculateNextY(int itemHeightCm) {
        int currentHeightPx = currentHeight() * PIXELS_PER_CM;
        int itemHeightPx = itemHeightCm * PIXELS_PER_CM;
        return GROUND_Y - currentHeightPx - itemHeightPx;
    }

    /**
     * Calcula la altura lógica total acumulada de la torre.
     *
     * @return La suma de alturas de tazas y tapas.
     */
    private int currentHeight() {
        int h = 0;
        for (Object item : items) {
            if (item instanceof Cup) h += ((Cup)item).getHeight(); // Altura lógica
            else if (item instanceof Lid) h += 1; // Tapa siempre 1
        }
        return h;
    }

    /**
     * Verifica si ya existe una taza con el tamaño dado en la colección.
     *
     * @param size El tamaño de la taza a verificar.
     * @return true si la taza ya existe, false en caso contrario.
     */
    private boolean existsCup(int size) {
        for (Object item : items) {
            if (item instanceof Cup && ((Cup)item).getSize() == size) return true;
        }
        return false;
    }

    /**
     * Verifica si ya existe una tapa con el tamaño dado en la colección.
     *
     * @param size El tamaño de la tapa a verificar.
     * @return true si la tapa ya existe, false en caso contrario.
     */
    private boolean existsLid(int size) {
        for (Object item : items) {
            if (item instanceof Lid && ((Lid)item).getSize() == size) return true;
        }
        return false;
    }

    /**
     * Obtiene un color consistente basado en el tamaño del elemento.
     *
     * @param size El tamaño del elemento.
     * @return El nombre del color asignado.
     */
    private String getColorForSize(int size) {
        String[] colors = {"red", "blue", "green", "magenta", "black", "yellow"};
        return colors[size % colors.length];
    }

    /**
     * Muestra un mensaje de error utilizando JOptionPane, solo si el simulador es visible.
     *
     * @param msg El mensaje de error a mostrar.
     */
    private void showError(String msg) {
        if (isVisible) JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Dibuja una regla visual que marca la altura máxima permitida.
     * Solo se dibuja si el simulador es visible.
     */
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