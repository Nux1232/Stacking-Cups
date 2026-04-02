package towerr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba que tiene como objetivo verificar que el proyecto completo funcione
 * correctamente.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 30/03/2026
 */
public class TowerATest {
    private Tower newTower;

    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
        newTower = new Tower(10, 100);
    } // Cierre de la clase de prueba
    
    /**
     * Caso de prueba que verifica que no se puedan agregar dos tazas del mismo tamaño
     * sin importar que tipo de taza sea.
     */
    @Test
    public void shouldNotAddTwoSameCups() {
        newTower.pushCup("opener", 5);
        newTower.pushCup("hierarchical", 5);
        assertFalse(newTower.ok());
    } // Cierre de la clase de prueba

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre de la clase de prueba
} // Cierre de la clase