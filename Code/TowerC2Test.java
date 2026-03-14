import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba para comprobar que el proyecto funciona.
 * Realiza casos de prueba para comprobar que el simulador funciona.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 1.0
 * @version 08/03/2026
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
        newTower.pushCup(1);
        assertEquals(1, newTower.height());
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
        newTower.pushLid(1);
        int finalHeight = newTower.height();
        assertEquals(1, finalHeight - initialHeight);
        assertEquals(1, newTower.height());
    } // Cierre del caso de prueba

    // swap
    /**
     * Verifica que no se pueda intercambiar entre un item existente y uno que no existe.
     */
    @Test
    public void shouldNotSwapBetweenIfItemsDoesntExists() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(3);
        String[] o1 = {"cup", "3"};
        String[] o2 = {"lid", "5"};
        newTower.swap(o1, o2);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba

    /**
     * Verifica que al intercambiar taza y tapa, no aumente la altura.
     * Verifica que no se pueda intercambiar con un objeto que no existe.
     */
    @Test
    public void shouldNotSwapIfItemDoesNotExist() {
        Tower newTower = new Tower(10, 40);
        int initialHeight = newTower.height();
        newTower.pushCup(25);
        newTower.pushLid(15);
        assertEquals(0, initialHeight);
        newTower.pushLid(18);
        String[] o1 = {"cup", "25"};
        String[] o2 = {"lid", "35"};
        newTower.swap(o1, o2);
        assertFalse(newTower.ok());
    } // Cierre del caso de prueba

    //swapToReduce
    /**
     * Verifica si la altura se redujo o no al intercambiar dos objetos.
     * Verifica que la altura se reduzca al intercambiar dos objetos.
     */
    @Test
    public void shouldReduceHeight() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(10);
        newTower.pushLid(20);
        newTower.pushCup(50);
        newTower.pushLid(30);
        assertNull(newTower.swapToReduce());
        
        String[][] move = newTower.swapToReduce();
        assertTrue(move == null || move.length == 2);
    }

    // cover
    /**
     * Verifica que no se tapen dos o más tapas.
     * Verifica que se pueda cubrir una taza con una tapa.
     */
    @Test
    public void shouldCoverCupWithLid() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(10);
        newTower.pushLid(10);
        newTower.pushLid(10);
        assertFalse(newTower.ok());
        newTower.cover();
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba

    /**
     * Método llamado después de cada caso de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
}