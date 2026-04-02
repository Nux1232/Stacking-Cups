package towerr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase con casos de prueba para verificar que funcionan las tazas y tapas hijas.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 30/03/2026
 */ 
public class TowerC4Test {
    private Tower newTower;

    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
        newTower = new Tower(10, 60);
    } // Cierre del caso de prueba
    
    // OpenerCup
    /**
     * Caso de prueba que consiste en verificar que si no hay tapas que haya en la torre
     * entonces se queda en la base.
     */
    @Test
    public void shouldEnterNormally() {
        newTower.pushCup("opener", 1);
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    /**
     * Caso de prueba que verifica que una OpenerCup no deba eliminar tazas sino tapas.
     */
    @Test
    public void shouldNotDeleteCups() {
        newTower.pushCup("opener", 1);
        newTower.pushCup("opener", 2);
        assertEquals(2, newTower.stackingItems().length);
    } // Cierre del caso de prueba
    
    // HierarchicalCup
    /**
     * Caso de prueba que verifica que una HierarchicalCup haya movido objetos de menor
     * tamaño a esta y quede en la base.
     */
    @Test
    public void shouldMoveSmallObjectsIfReachedBase() {
        newTower.pushCup("normal", 1);
        newTower.pushCup("normal", 2);
        newTower.pushCup("hierarchical", 3);
        assertEquals("cup", newTower.stackingItems()[0][0]);
    } // Cierre del caso de prueba
    
    /**
     * Caso de prueba que comprueba que una HierarchicalCup no pueda ser eliminada si
     * llegó a la base.
     */
    @Test
    public void shouldNotPopIfReachedBase() {
        newTower.pushCup("hierarchical", 1);
        newTower.popCup();
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    // FearfulLid
    /**
     * Caso de prueba que verifica que no se pueda apilar una tapa miedosa si su
     * compañera no existe.
     */
    @Test
    public void shouldNotEnterIfPartnerCupDoesntExists() {
        newTower.pushLid("fearful", 1);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    // CrazyLid
    /**
     * Caso de prueba que comprueba que una tapa loca pueda ser eliminada
     * sin restricciones.
     */
    @Test
    public void shouldExitNormally() {
        newTower.pushLid("crazy", 1);
        newTower.removeLid(1);
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    /**
     * Caso de prueba que verifica que no tape la taza compañera sino que se coloque
     * como base.
     */
    @Test
    public void shouldNotCoverPartnerCup() {
        newTower.pushCup("normal", 1);
        newTower.pushLid("crazy", 1);
        assertEquals("lid", newTower.stackingItems()[0][0]);
    } // Cierre del caso de prueba
    
    /**
     * Método llamado después de cada caso de prueba.     
    */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
} // Cierre de la clase