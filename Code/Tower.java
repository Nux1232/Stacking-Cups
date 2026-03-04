import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Simulador de Torre de Tazas.
 * Esta clase representa una torre que permite apilar tazas (Cups) y tapas (Lids).
 * Gestiona la lógica de apilamiento, restricciones de altura y visualización.
 * Cumple con el Requisito de Construcción al reutilizar componentes del paquete shapes.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 2.0
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
    
    // Ciclo 1
    /**
     * Crea una nueva torre con las dimensiones especificadas.
     * 
     * @param width El ancho deseado de la torre.
     * @param maxHeight La altura máxima permitida para la torre (en cm).
     */
    public Tower(int width, int maxHeight) {
        this.width = width;
        this.maxHeight = maxHeight;
        this.items = new ArrayList<>();
        this.isVisible = false;
        this.lastOperationOk = true;
        drawRuler(); // Requisito Usabilidad 4: Marcar centímetros
    } // Cierre del constructor

    /**
     * Adiciona una taza a la torre.
     * Verifica que la taza no exista previamente y que quepa en la altura disponible.
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
    } // Cierre del método
    
    /**
     * Elimina la última taza añadida a la torre.
     */
    public void popCup() {
        for(int i = items.size() - 1; i >= 0; i--) {
            if(items.get(i) instanceof Cup) {
                Cup lastCupAdded = (Cup) items.get(i);
                if (isVisible) lastCupAdded.getView().makeInvisible();
                items.remove(i);
                lastOperationOk = true;
                return;
            }
        }
        showError("No hay tazas para eliminar.");
        lastOperationOk = false;
    }
    
    /**
     * Elimina una taza en específico de la torre.
     * 
     * @param size El tamaño de la taza.
     */
    public void removeCup(int size) {
        for(int i = items.size() - 1; i >= 0; i--) {
            if(items.get(i) instanceof Cup) {
                Cup specificCup = (Cup) items.get(i);
                if(specificCup.getSize() == size) {
                    if(isVisible) specificCup.getView().makeInvisible();
                    items.remove(i);
                    lastOperationOk = true;
                    return;
                }
            }
        }
        showError("No existe la taza " + size);
        lastOperationOk = false;
    }

    /**
     * Adiciona una tapa a la torre.
     * Verifica que la tapa no exista previamente y que quepa en la altura disponible.
     * Las tapas siempre miden 1 cm de altura.
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
    } // Cierre del método
    
    /**
     * Elimina la última tapa añadida a la torre.
     */
    public void popLid() {
        for(int i = items.size() - 1; i >= 0; i--) {
            if(items.get(i) instanceof Lid) {
                Lid lastLidAdded = (Lid) items.get(i);
                if (isVisible) lastLidAdded.getView().makeInvisible();
                items.remove(i);
                lastOperationOk = true;
                return;
            }
        }
        showError("No hay tapas para eliminar.");
        lastOperationOk = false;
    }
    
    /**
     * Elimina una tapa en específico de la torre.
     * 
     * @param size El tamaño de la tapa.
     */
    public void removeLid(int size) {
        for(int i = items.size() - 1; i >= 0; i--) {
            if(items.get(i) instanceof Lid) {
                Lid specificLid = (Lid) items.get(i);
                if(specificLid.getSize() == size) {
                    if(isVisible) specificLid.getView().makeInvisible();
                    items.remove(i);
                    lastOperationOk = true;
                    return;
                }
            }
        }
        showError("No existe la tapa " + size);
        lastOperationOk = false;
    }
    
    /**
     * Ordena los elementos de la torre de mayor a menor.
     * (solo se incluyen los que quepan).
     */
    public void orderTower() {
        if(isVisible) {
            for(Object item: items) {
                if(item instanceof Cup) ((Cup) item).getView().makeInvisible();
                if(item instanceof Lid) ((Cup) item).getView().makeInvisible();
            }
        }
        
        Collections.reverse(items);
        
        int totalHeight = 0;
        ArrayList<Object> fittedItems = new ArrayList<>();
        
        for(Object item: items) {
            int itemHeight = 0;
            if(item instanceof Cup) itemHeight = ((Cup) item).getSize();
            if(item instanceof Lid) itemHeight = 1;
            
            if(totalHeight + itemHeight <= maxHeight) {
                fittedItems.add(item);
                totalHeight += itemHeight;
            }
        }
        items = fittedItems;
        if(isVisible) redraw();
        lastOperationOk = true;
    }
    
    /**
     * Ordena los elementos de forma inversa.
     * (solo se incluyen los que quepan).
     */
    public void reverseTower() {
        
    }

    /**
     * Consulta la altura actual de los elementos apilados.
     *
     * @return La altura total actual de la torre en centímetros.
     */
    public int height() {
        return currentHeight();
    } // Cierre del método
    

    /**
     * Hace visible el simulador.
     * Muestra la regla y todos los elementos (tazas y tapas) en el canvas.
     */
    public void makeVisible() {
        isVisible = true;
        drawRuler();
        for (Object item : items) {
            if (item instanceof Cup) ((Cup)item).getView().makeVisible();
            if (item instanceof Lid) ((Lid)item).getView().makeVisible();
        }
    } // Cierre del método

    /**
     * Hace invisible el simulador.
     * Oculta todos los elementos visuales del canvas.
     */
    public void makeInvisible() {
        isVisible = false;
        // Borrar regla visualmente (opcional si implementa eraseRuler)
        for (Object item : items) {
            if (item instanceof Cup) ((Cup)item).getView().makeInvisible();
            if (item instanceof Lid) ((Lid)item).getView().makeInvisible();
        }
    } // Cierre del método
    
    /**
     * Termina el simulador.
     */
    public void exit() {
        System.exit(0);
    } // Cierre del método

    /**
     * Indica si la última operación realizada fue exitosa.
     *
     * @return true si la última operación se completó correctamente, false en caso contrario.
     */
    public boolean ok() {
        return lastOperationOk;
    } // Cierre del método
    
    // Ciclo 2
    /**
     * Crea una torre especificando la cantidad de tazas.
     * Genera tazas con tamaños siguiendo la fórmula 2i - 1.
     *
     * @param cups Cantidad de tazas a generar.
     */
    public Tower(int cups) {
        this.width = 100; // Ancho por defecto
        this.maxHeight = 100; // Alto por defecto suficientemente grande
        this.items = new ArrayList<>();
        this.isVisible = false;
        this.lastOperationOk = true;
        
        for (int i = 1; i <= cups; i++) {
            // Cálculo del tamaño según el pdf: 1, 3, 5, 7...
            int size = (2 * i) - 1; 
            pushCup(size);
        }
    } // Cierre del método

    /**
     * Intercambia la posición de dos elementos en la torre.
     *
     * @param o1 Arreglo con tipo y tamaño del primer objeto, ej: {"cup", "4"}
     * @param o2 Arreglo con tipo y tamaño del segundo objeto, ej: {"lid", "4"}
     */
    public void swap(String[] o1, String[] o2) {
        int index1 = findItemIndex(o1[0], Integer.parseInt(o1[1]));
        int index2 = findItemIndex(o2[0], Integer.parseInt(o2[1]));

        if (index1 != -1 && index2 != -1) {
            Object temp = items.get(index1);
            items.set(index1, items.get(index2));
            items.set(index2, temp);
            lastOperationOk = true;
            // Nota: Aquí se debería llamar a un método que recalcule 
            // las coordenadas 'Y' visuales de toda la torre.
        } else {
            showError("Uno o ambos objetos no existen en la torre.");
            lastOperationOk = false;
        }
    } // Cierre del método
    
    /**
     * Consulta un movimiento de intercambio que reduzca la altura de la torre.
     *
     * @return Arreglo 2D con los identificadores de los objetos a intercambiar,
     * ej: {{"cup", "4"}, {"lid", "4"}}. Retorna null si no hay ninguno.
     */
    public String[][] swapToReduce() {
        int initialHeight = height();

        // Iteramos sobre todos los pares posibles en la torre
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                
                // 1. Simular el intercambio en la lista
                Object temp = items.get(i);
                items.set(i, items.get(j));
                items.set(j, temp);

                // 2. Comprobar la nueva altura de la torre simulada
                int newHeight = height();

                // 3. Revertir el intercambio inmediatamente (¡muy importante para no alterar el estado real!)
                items.set(j, items.get(i));
                items.set(i, temp);

                // 4. Evaluar si logramos reducir la altura
                if (newHeight < initialHeight) {
                    lastOperationOk = true;
                    return new String[][] { 
                        identifyItem(items.get(i)), 
                        identifyItem(items.get(j)) 
                    };
                }
            }
        }
        
        // Si se probaron todas las combinaciones y ninguna redujo la altura
        lastOperationOk = false;
        return null;
    } // Cierre del método

    /**
     * Tapa las tazas que tienen sus respectivas tapas dentro de la torre.
     */
    public void cover() {
        for (Object item : items) {
            if (item instanceof Cup) {
                Cup cup = (Cup) item;
                if (existsLid(cup.getSize())) {
                    cup.setCoveredStatus(true);
                }
            }
        }
        lastOperationOk = true;
    } // Cierre del método
    
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
    } // Cierre del método

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
    } // Cierre del método

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
    } // Cierre del método

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
    } // Cierre del método

    /**
     * Obtiene un color consistente basado en el tamaño del elemento.
     *
     * @param size El tamaño del elemento.
     * @return El nombre del color asignado.
     */
    private String getColorForSize(int size) {
        String[] colors = {"red", "blue", "green", "magenta", "black", "yellow"};
        return colors[size % colors.length];
    } // Cierre del método

    /**
     * Muestra un mensaje de error utilizando JOptionPane, solo si el simulador es visible.
     *
     * @param msg El mensaje de error a mostrar.
     */
    private void showError(String msg) {
        if (isVisible) JOptionPane.showMessageDialog(null, msg);
    } // Cierre del método
    
    /**
     * Dibuja de nuevo la taza o tapa, sólo si el simulador es visible.
     */
    private void redraw() {
        int totalHeight = 0;
        for(Object item: items){
            if(item instanceof Cup) {
                Cup cup = (Cup) item;
                int yPos = GROUND_Y - (totalHeight + cup.getSize()) * PIXELS_PER_CM;
                cup.getView().moveVertical(yPos);
                cup.getView().makeVisible();
                totalHeight += cup.getSize();
            } else if(item instanceof Lid) {
                Lid lid = (Lid) item;
                int yPos = GROUND_Y - (totalHeight + 1) * PIXELS_PER_CM;
                lid.getView().moveVertical(yPos);
                lid.getView().makeVisible();
                totalHeight ++;
            }
        }
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
    } // Cierre del método
    
    /**
     * Método auxiliar privado para encontrar el índice de un objeto.
     */
    private int findItemIndex(String type, int size) {
        for (int i = 0; i < items.size(); i++) {
            Object item = items.get(i);
            if (type.equalsIgnoreCase("cup") && item instanceof Cup && ((Cup)item).getSize() == size) {
                return i;
            } else if (type.equalsIgnoreCase("lid") && item instanceof Lid && ((Lid)item).getSize() == size) {
                return i;
            }
        }
        return -1;
    } // Cierre del método
    
    /**
     * Método auxiliar privado para obtener la representación String[] de un objeto,
     * requerida por el formato de salida de swapToReduce.
     *
     * @param item El objeto (Cup o Lid) a identificar.
     * @return Un arreglo de String con el tipo y tamaño, ej: {"cup", "4"}.
     */
    private String[] identifyItem(Object item) {
        if (item instanceof Cup) {
            return new String[] {"cup", String.valueOf(((Cup)item).getSize())};
        } else if (item instanceof Lid) {
            return new String[] {"lid", String.valueOf(((Lid)item).getSize())};
        }
        return new String[] {"", ""}; // Caso por defecto de seguridad
    } // Cierre del método
} // Cierre de la clase