package towerr;
import shapes.*;
import java.util.*;
import javax.swing.*;

/**
 * Clase hija que mira si no hay una taza compañera entonces no entra. Y si
 * está tapando a su taza, no puede ser eliminada.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 28/03/2026
 */
public class FearfulLid extends Lid {
    /**
     * Constructor de la clase FearfulLid.
     * 
     * @param size El tamaño de la tapa miedosa.
     * @param color El color de la tapa miedosa.
     * @param x     La coordenada X deseada.
     * @param y     La coordenada Y deseada.
     * @param creationId El identificador de orden de creación.
     */
    public FearfulLid(int size, String color, int x, int y, int creationId) {
        super(size, color, x, y, creationId);
    } // Cierre del constructor
    
    /**
     * Método que verifica si una tapa miedosa puede entrar a la torre.
     * 
     * @param items La lista que contiene tazas y tapas.
     * @return boolean Retorna true si puede entrar o false si no.
     */
    @Override
    public boolean lidCanEnter(ArrayList<StackItem> items) {
        for(StackItem item:items) {
            if(item.getType().equals("cup") && item.getSize() == this.size) {
                return true;
            }
        }
        return false;
    }// Cierre del método
    
    /**
     * Método que verifica si una tapa miedosa puede salir de la torre.
     * 
     * @param items La lista que contiene tazas y tapas.
     * @return boolean Retorna true si puede salir o false si no.
     */
    @Override
    public boolean lidCanExit(ArrayList<StackItem> items, int index) {
        if(index == 0) return true;
        
        StackItem cupPartner = items.get(index - 1);
        return !(cupPartner.getType().equals("cup") && cupPartner.getSize() == this.size);
    }// Cierre del método
    
    /**
     * Método que agrega la tapa de acuerdo a su comportamiento.
     * En este caso no hace nada.
     * 
     * @param items La lista que contiene tazas y tapas.
     */
    @Override
    public void actionPush(ArrayList<StackItem> items) {
    } // Cierre del método
} // Cierre de la clase