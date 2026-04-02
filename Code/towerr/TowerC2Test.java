package towerr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba para comprobar que el proyecto funciona.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 15/03/2026
 */
public class TowerC2Test {
    /**
     *
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
    }
    // Tower (Constructor)
    /**
     * Verifica que la altura de la torre sea de 0cm.
     * Verifica que al instanciar un objeto, la altura de la torre sea de 0cm.
     */
    @Test
    public void shouldStartWithHeightZero() {
        Tower newTower = new Tower(10, 40);
        assertEquals(0, newTower.height());
    } // Cierre del caso de prueba
    
    /**
     * La altura de la torre debe incrementar de forma correcta.
     */
    @Test
    public void shouldHeightIncreaseCorrectly() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 1);
        assertEquals(1, newTower.height());
    } // Cierre del caso de prueba
    
    //pushCup
    /**
     * Verifica que no se pueda agregar una taza duplicada a la torre.
     */
    @Test
    public void shouldNotAddAnExistentCup() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 23);
        newTower.pushCup("cup", 23);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    //popCup
    /**
     * Verifica que no se pueda eliminar tazas si la torre no tiene.
     */
    @Test
    public void shouldNotDeleteIfCupsDoesntExists() {
        Tower newTower = new Tower(10, 40);
        newTower.popCup();
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    //removeCup
    /**
     * Verifica que la taza a la que se desee eliminar, la altura de la torre se reduzca.
     */
    @Test
    public void shouldDecreaseHeight() {
        Tower newTower = new Tower(10, 40);
        int initialHeight = newTower.height();
        newTower.pushCup("cup", 23);
        newTower.removeCup(1);
        int finalHeight = newTower.height();
        assertEquals(initialHeight, finalHeight);
    } // Cierre del caso de prueba

    // pushLid
    /**
     * Verifica que el tamaño de la tapa no sea mayor a 1cm.
     * Verifica que la altura de la torre aumente al agregarle una tapa.
     */
    @Test 
    public void shouldIncreaseHeight() {
        Tower newTower = new Tower(10, 40);
        int initialHeight = newTower.height();
        newTower.pushLid("lid", 1);
        int finalHeight = newTower.height();
        assertEquals(1, finalHeight - initialHeight);
        assertEquals(1, newTower.height());
    } // Cierre del caso de prueba
    
    //popLid
    /**
     * Verifica que no se pueda eliminar tapas si la torre no tiene.
     */
    @Test
    public void shouldNotDeleteLidIfDoesntExists() {
        Tower newTower = new Tower(10, 40);
        newTower.popLid();
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    //removeLid
    /**
     * Verifica que no se pueda eliminar una tapa que ya ha sido eliminada anteriormente.
     */
    @Test
    public void shouldNotDeleteTheSameLid() {
        Tower newTower = new Tower(10, 40);
        newTower.pushLid("lid", 3);
        newTower.pushLid("lid", 5);
        newTower.removeLid(2);
        newTower.removeLid(2);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    //orderTower
    /**
     * Verifica que al tener tapa y taza con mismo tamaño, ordena la torre (la taza debe ir primero). 
     */
    @Test
    public void shouldOrderIfLidAndCupHaveTheSameSize() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 3);
        newTower.pushLid("lid", 3);
        newTower.orderTower();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    //reverseTower
    /**
     * Verifica que al realizar la reversa dos veces, las tazas y tapas estén en su posición original.
     */
    @Test
    public void shouldStayInTheirOriginalPosition() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 3);
        newTower.pushLid("lid", 3);
        newTower.pushCup("cup", 14);
        newTower.pushLid("lid", 14);
        newTower.reverseTower();
        newTower.reverseTower();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba

    // swap
    /**
     * Verifica que no se pueda intercambiar entre un item existente y uno que no existe.
     */
    @Test
    public void shouldNotSwapBetweenIfItemsDoesntExists() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 3);
        String[] o1 = {"cup", "3"};
        String[] o2 = {"lid", "5"};
        newTower.swap(o1, o2);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    // cover
    /**
     * Verifica que no se tapen dos o más tapas.
     * Verifica que se pueda cubrir una taza con una tapa.
     */
    @Test
    public void shouldCoverCupWithLid() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 10);
        newTower.pushLid("lid", 10);
        newTower.pushLid("lid", 10);
        assertFalse(newTower.ok());
        newTower.cover();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    //lidedCups
    /**
     * Verifica que se retorne una lista vacía de una taza al no existir.
     */
    @Test
    public void shouldReturnEmptyListIfCupDoesntExists() {
        Tower newTower = new Tower(10, 40);
        int[] noItem = newTower.lidedCups();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    //stackingItems
    /**
     * Verifica que no se retorne la matriz si los items no existen.
     */
    @Test
    public void shouldReturnNothingIfItemsDoesntExists() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 25);
        newTower.pushLid("lid", 4);
        String[][] sinItem = newTower.stackingItems();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba

    //swapToReduce
    /**
     * Verifica si la altura se redujo o no al intercambiar dos objetos.
     * Verifica que la altura se reduzca al intercambiar dos objetos.
     */
    @Test
    public void shouldReduceHeight() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup("cup", 10);
        newTower.pushLid("lid", 20);
        newTower.pushCup("cup", 50);
        newTower.pushLid("lid", 30);
        assertNull(newTower.swapToReduce());
        
        String[][] move = newTower.swapToReduce();
        assertTrue(move == null || move.length == 2);
    } // Cierre del caso de prueba

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
} // Cierre de la clase