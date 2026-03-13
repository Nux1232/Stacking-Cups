import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Simulador que representa una torre que permite apilar tazas y tapas.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 07/03/2026
 */
public class Tower {
    private int width;
    private int maxHeight;
    private ArrayList<StackItem> items;
    private boolean isVisible;
    private boolean lastOperationOk;
    private int cupCounter = 1;
    private int lidCounter = 1;
    private static final int pixelPerCm = 10; 
    private static final int groundY = 250; 
    private static final int centerX = 150;
    
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
        drawRuler();
    } // Cierre del constructor

    /**
     * Adiciona una taza a la torre.
     * Verifica que la taza no exista previamente y que quepa en la altura disponible.
     * 
     * @param size El tamaño de la taza a adicionar.
     */
    public void pushCup(int size) {
        if (existsCup(size)) {
            showError("Ya existe la taza " + size);
            lastOperationOk = false;
            return;
        }
        
        int cupHeightCm = size; 
        int newBaseCm = calculateNextBaseCm("cup", cupHeightCm);
        int newMaxHeight = Math.max(currentHeight(), newBaseCm + cupHeightCm);
        
        if (newMaxHeight > maxHeight) {
            showError("La torre excede la altura máxima.");
            lastOperationOk = false;
            return;
        }

        String color = getColorForSize(size);
        int yPos = groundY - (newBaseCm + cupHeightCm) * pixelPerCm;

        Cup newCup = new Cup(size, color, centerX, yPos, cupCounter++);
        if (isVisible) newCup.getView().makeVisible();
        
        items.add(newCup);
        lastOperationOk = true;
    } // Cierre del método
    
    /**
     * Elimina la última taza añadida a la torre.
     */
    public void popCup() {
        for(int i = items.size() - 1; i >= 0; i--) {
            StackItem item = items.get(i);
            
            if(item.getType().equals("cup")) {
                if (isVisible) item.makeInvisible();
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
     * @param id El identificador de creación de la taza.
     */
    public void removeCup(int id) {
        for(int n = items.size() - 1; n >= 0; n--) {
            StackItem item = items.get(n);
            
            if(item.getType().equals("cup")) {
                if(item.getIdentifier() == id) {
                    if(isVisible) item.makeInvisible();
                    items.remove(n);
                    lastOperationOk = true;
                    return;
                }
            }
        }
        showError("No existe la taza " + id);
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
        if (existsLid(size)) {
            showError("Ya existe la tapa " + size);
            lastOperationOk = false;
            return;
        }

        int lidHeightCm = 1; 
        int newBaseCm = calculateNextBaseCm("lid", lidHeightCm);
        int newMaxHeight = Math.max(currentHeight(), newBaseCm + lidHeightCm);

        if (newMaxHeight > maxHeight) {
            showError("No cabe la tapa.");
            lastOperationOk = false;
            return;
        }

        String color = getColorForSize(size);
        int yPos = groundY - (newBaseCm + lidHeightCm) * pixelPerCm;
        Lid newLid = new Lid(size, color, centerX, yPos, lidCounter++);
        
        if (isVisible) newLid.makeVisible();
        items.add(newLid);
        lastOperationOk = true;
    } // Cierre del método
    
    /**
     * Elimina la última tapa añadida a la torre.
     */
    public void popLid() {
        for(int i = items.size() - 1; i >= 0; i--) {
            StackItem item = items.get(i);
            
            if(item.getType().equals("lid")) {
                if (isVisible) item.makeInvisible();
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
     * @param id El identificador de creación de la tapa.
     */
    public void removeLid(int id) {
        for(int n = items.size() - 1; n >= 0; n--) {
            StackItem item = items.get(n);
            
            if(item.getType().equals("lid")) {
                if(item.getIdentifier() == id) {
                    if(isVisible) item.makeInvisible();
                    items.remove(n);
                    lastOperationOk = true;
                    return;
                }
            }
        }
        showError("No existe la tapa " + id);
        lastOperationOk = false;
    }
    
    /**
     * Calcula y devuelve los identificadores de creación de las tazas que están correctamente tapadas.
     * 
     * @return Un arreglo de enteros con los identificadores de las tazas tapadas.
     */
    public int[] lidedCups() {
        ArrayList<Integer> lidedList = new ArrayList<>();
        
        for (StackItem item : items) {
            if (item.getType().equals("cup")) {
                Cup cup = (Cup) item;
                if (existsLid(cup.getSize())) {
                    lidedList.add(cup.getIdentifier());
                }
            }
        }
        
        int[] result = new int[lidedList.size()];
        for(int i = 0; i < lidedList.size(); i++) {
            result[i] = lidedList.get(i);
        }
        
        lastOperationOk = true;
        return result;
    } // Cierre del método

    /**
     * Construye y retorna la representación en matriz de todos los elementos apilados.
     * 
     * @return Matriz 2D con los identificadores de los objetos, Ejemplo: {{"cup", "5"}, {"lid", "5"}}.
     */
    public String[][] stackingItems() {
        String[][] stackMatrix = new String[items.size()][2];
        
        for (int i = 0; i < items.size(); i++) {
            stackMatrix[i] = identifyItem(items.get(i));
        }
        
        lastOperationOk = true;
        return stackMatrix;
    } // Cierre del método

    /**
     * Reorganiza la torre de tazas y tapas ordenándolas por tamaño de mayor a menor.
     * La base (índice 0) tendrá los elementos más grandes.
     */
    public void orderTower() {
        items.sort((item1, item2) -> {
            int size1 = item1.getSize();
            int size2 = item2.getSize();
            
            if (size1 != size2) {
                return Integer.compare(size2, size1); 
            } else {
                if (item1.getType().equals("cup") && item2.getType().equals("lid")) return -1;
                if (item1.getType().equals("lid") && item2.getType().equals("cup")) return 1;
                return 0;
            }
        });
        
        if (isVisible) redraw();
        lastOperationOk = true;
    } // Cierre del método

    /**
     * Invierte el orden completo de la torre.
     */
    public void reverseTower() {
        Collections.reverse(items);
        if (isVisible) redraw();
        lastOperationOk = true;
    } // Cierre del método

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
     */
    public void makeVisible() {
        isVisible = true;
        drawRuler();
        for (StackItem item : items) {
            if (item.getType().equals("cup")) item.getView().makeVisible();
            if (item.getType().equals("lid")) item.getView().makeVisible();
        }
    } // Cierre del método

    /**
     * Hace invisible el simulador.
     */
    public void makeInvisible() {
        isVisible = false;
        for (StackItem item : items) {
            if (item.getType().equals("cup")) item.getView().makeInvisible();
            if (item.getType().equals("lid")) item.getView().makeInvisible();
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
            StackItem temp = items.get(index1);
            items.set(index1, items.get(index2));
            items.set(index2, temp);
            lastOperationOk = true;
        } else {
            showError("Uno o ambos objetos no existen en la torre.");
            lastOperationOk = false;
        }
    } // Cierre del método
    
    /**
     * Consulta un movimiento de intercambio que reduzca la altura de la torre.
     *
     * @return Arreglo 2D con los identificadores de los objetos a intercambiar,
     * Ejemplo: {{"cup", "4"}, {"lid", "4"}}. Retorna null si no hay ninguno.
     */
    public String[][] swapToReduce() {
        int initialHeight = height();

        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                // intercambiar elementos en la lista
                Collections.swap(items, i, j);

                // ver nueva altura
                int newHeight = height();

                // revertir el intercambio para que no se afecte la altura
                Collections.swap(items, i, j);

                // ver si se redujo la altura
                if (newHeight < initialHeight) {
                    lastOperationOk = true;
                    return new String[][] { 
                        identifyItem(items.get(i)), 
                        identifyItem(items.get(j)) 
                    };
                }
            }
        }
        lastOperationOk = false;
        return null;
    } // Cierre del método

    /**
     * Tapa las tazas que tienen sus respectivas tapas dentro de la torre.
     */
    public void cover() {
        for (StackItem item : items) {
            if (item.getType().equals("cup")) {
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
     * Calcula los niveles (bases) de cada elemento para determinar la altura total.
     * Si un item es una taza y es más pequeña que la taza inmediatamente inferior,
     * se asume que se introduce dentro de ella, compartiendo su misma base.
     */
    private int[] calculateAllBases() {
        int[] bases = new int[items.size()];
        if (items.isEmpty()) return bases;
        
        bases[0] = 0;
        for (int i = 1; i < items.size(); i++) {
            StackItem prevItem = items.get(i - 1);
            StackItem item = items.get(i);
            
            if (item.getType().equals("cup") && prevItem.getType().equals("cup") && item.getHeight() < prevItem.getHeight()) {
                bases[i] = bases[i - 1]; // Se anida, comparte la misma base
            } else {
                int prevHeight = prevItem.getType().equals("lid") ? 1 : prevItem.getHeight();
                bases[i] = bases[i - 1] + prevHeight; // Se apila encima
            }
        }
        return bases;
    }

    /**
     * Calcula la base (en cm) donde se apoyará el siguiente elemento a insertar.
     */
    private int calculateNextBaseCm(String itemType, int itemHeightCm) {
        if (items.isEmpty()) return 0;
        
        int lastIndex = items.size() - 1;
        StackItem lastItem = items.get(lastIndex);
        int[] bases = calculateAllBases();
        int lastBase = bases[lastIndex];
        
        if (itemType.equals("cup") && lastItem.getType().equals("cup") && itemHeightCm < lastItem.getHeight()) {
            return lastBase; // Se anida en la última taza
        } else {
            int lastHeight = lastItem.getType().equals("lid") ? 1 : lastItem.getHeight();
            return lastBase + lastHeight; // Se apila sobre el último elemento
        }
    } // Cierre del método

    /**
     * Calcula la altura lógica total de la torre, considerando que tazas
     * más pequeñas pueden anidarse dentro de tazas más grandes.
     *
     * @return La altura total alcanzada.
     */
    private int currentHeight() {
        if (items.isEmpty()) return 0;
        int[] bases = calculateAllBases();
        int maxH = 0;
        for (int i = 0; i < items.size(); i++) {
            int h = items.get(i).getType().equals("lid") ? 1 : items.get(i).getHeight();
            if (bases[i] + h > maxH) {
                maxH = bases[i] + h;
            }
        }
        return maxH;
    } // Cierre del método

    /**
     * Verifica si ya existe una taza con el tamaño dado en la colección.
     *
     * @param size El tamaño de la taza a verificar.
     * @return true si la taza ya existe, false en caso contrario.
     */
    private boolean existsCup(int size) {
        for(StackItem item: items) {
            if(item.getType().equals("cup") && item.getSize() == size) {
                return true;
            }
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
        for (StackItem item : items) {
            if(item.getType().equals("lid") && item.getSize() == size) {
                return true;
            }
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
        if (!isVisible) return;
        int[] bases = calculateAllBases();
        for(int i = 0; i < items.size(); i++){
            StackItem item = items.get(i);
            int baseCm = bases[i];
            
            if(item.getType().equals("cup")) {
                Cup cup = (Cup) item;
                int yPos = groundY - (baseCm + cup.getSize()) * pixelPerCm;
                cup.getView().moveTo(centerX, yPos);
                cup.getView().makeVisible();
            } else if(item.getType().equals("lid")) {
                Lid lid = (Lid) item;
                int yPos = groundY - (baseCm + 1) * pixelPerCm;
                lid.getView().moveTo(centerX - 2, yPos);
                lid.getView().makeVisible();
            }
        }
    }
    
    /**
     * Dibuja una regla visual que marca la altura máxima permitida.
     * Solo se dibuja si el simulador es visible.
     */
    private void drawRuler() {
        if (!isVisible) return;
        Rectangle axis = new Rectangle();
        axis.changeColor("black");
        axis.changeSize(maxHeight * pixelPerCm, 2);
        
        int rulerX = centerX - 60; 
        int moveX = rulerX - 70;
        int moveY = (groundY - (maxHeight * pixelPerCm)) - 15;
        
        axis.moveHorizontal(moveX);
        axis.moveVertical(moveY);
        axis.makeVisible();
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
            return new String[] {"cup", String.valueOf(((Cup)item).getIdentifier())};
        } else if (item instanceof Lid) {
            return new String[] {"lid", String.valueOf(((Lid)item).getIdentifier())};
        }
        return new String[] {"", ""}; // Caso por defecto de seguridad
    } // Cierre del método
    
    /**
     * Método auxiliar privado para encontrar el índice de un objeto.
     */
    private int findItemIndex(String type, int id) {
        for (int i = 0; i < items.size(); i++) {
            StackItem item = items.get(i);
            if (type.equalsIgnoreCase("cup") && item.getType().equals("cup") && ((Cup)item).getIdentifier() == id) {
                return i;
            } else if (type.equalsIgnoreCase("lid") && item.getType().equals("lid") && ((Lid)item).getIdentifier() == id) {
                return i;
            }
        }
        return -1;
    } // Cierre del método
} // Cierre de la clase