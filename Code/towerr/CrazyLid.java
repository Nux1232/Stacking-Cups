package towerr;
import shapes.*;
import java.util.*;

/**
 * Clase hija que en vez de tapar a la taza compañera, se coloca como base.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 29/03/2026
 */
public class CrazyLid extends Lid {
    
    /**
     * Constructor de la clase CrazyLid.
     * 
     * @param size El tamaño de la tapa loca.
     * @param color El color de la tapa loca.
     * @param x     La coordenada X deseada.
     * @param y     La coordenada Y deseada.
     * @param creationId El identificador de orden de creación.
     */
    public CrazyLid(int size, String color, int x, int y, int creationId) {
        super(size, color, x, y, creationId);
    } // Cierre del constructor

    /**
     * Método que verifica si una tapa loca puede entrar a la torre.
     * 
     * @param items La lista que contiene tazas y tapas.
     * @return boolean Retorna true si puede entrar o false si no.
     */
    @Override
    public boolean lidCanEnter(ArrayList<StackItem> items) {
        return true;
    }// Cierre del método
    
    /**
     * Método que verifica si una tapa loca puede salir de la torre.
     * 
     * @param items La lista que contiene tazas y tapas.
     * @return boolean Retorna true si puede salir o false si no.
     */
    @Override
    public boolean lidCanExit(ArrayList<StackItem> items, int index) {
        return true;
    }// Cierre del método
    
    /**
     * Método que agrega la tapa loca como base de su taza compañera.
     * 
     * @param items La lista que contiene tazas y tapas.
     */
    @Override
    public void actionPush(ArrayList<StackItem> items) {
        items.add(0, this);
    } // Cierre del método
} // Cierre de la clase