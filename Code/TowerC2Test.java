import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba para comprobar que el simulador funciona.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 08/03/2026
 */
public class TowerC2Test
{
    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
    } // Cierre del caso de prueba
    
    // Tower (Constructor)
    /**
     * Verifica que al instanciar un objeto, la altura de la torre sea de 0cm.
     */
    @Test
    public void shouldStartWithHeightZero() {
        Tower newTower = new Tower(10, 40);
        assertEquals(0, newTower.height());
    } // Cierre del caso de prueba
    
    // pushCup
    /**
     * La altura de la torre debe incrementar de forma correcta.
     */
    @Test
    public void shouldHeightIncreaseCorrectly() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(1);
        assertEquals(1, newTower.height());
    } // Cierre del caso de prueba
    
    // pushLid
    /**
     * Verifica que la altura de la torre aumente al agregarle una tapa.
     */
    @Test
    public void shouldIncreaseHeight() {
        Tower newTower = new Tower(10, 40);
        newTower.pushLid(1);
        assertEquals(1, newTower.height());
    } // Cierre del caso de prueba
    
    // swap
    /**
     * Verifica que se pueda intercambiar entre tazas.
     */
    @Test
    public void shouldSwapBetweenItems() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(13);
        newTower.pushLid(35);
        String[] o1 = {"cup", "13"};
        String[] o2 = {"lid", "35"};
        newTower.swap(o1, o2);
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    /**
     * Verifica que no se pueda intercambiar con un objeto que no existe.
     */
    @Test
    public void shouldNotSwapIfItemDoesNotExist() {
        Tower newTower = new Tower(10, 40);
        int initialHeight = newTower.height();
        newTower.pushCup(25);
        newTower.pushLid(18);
        String[] o1 = {"cup", "25"};
        String[] o2 = {"lid", "35"};
        newTower.swap(o1, o2);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    //swapToReduce
    /**
     * Verifica que la altura se reduzca al intercambiar dos objetos.
     */
    @Test
    public void shouldReduceHeight() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(10);
        newTower.pushLid(20);
        newTower.pushCup(50);
        
        String[][] move = newTower.swapToReduce();
        assertTrue(move == null || move.length == 2);
    }
    
    // cover
    /**
     * Verifica que se pueda cubrir una taza con una tapa.
     */
    @Test
    public void shouldCoverCupWithLid() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(10);
        newTower.pushLid(10);
        newTower.cover();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    // Propuesta
    /**
     * La torre no debe permitir que se agregue la misma taza dos veces.
     */
    @Test
    public void accordingToCuervoOrtizShouldNotHaveTwoSameCups() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(15);
        newTower.pushCup(15);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba
    
    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
} // Cierre de la clase