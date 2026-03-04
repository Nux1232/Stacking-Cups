import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba para comprobar que el proyecto funciona.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 1.0
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
     * Verifica que la altura de la torre sea de 0cm.
     */
    @Test
    public void shouldBeAtLeastZero() {
        Tower newTower = new Tower(10, 40);
        assertEquals(0, newTower.height());
    } // Cierre del caso de prueba
    
    // pushCup
    /**
     * La altura de la torre debe incrementar de forma correcta.
     */
    @Test
    public void shouldIncreaseCorrectly() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(1);
        assertEquals(1, newTower.height());
    } // Cierre del caso de prueba
    
    // pushLid
    /**
     * Verifica que el tamaño de la tapa no sea mayor a 1cm.
     */
    @Test
    public void shouldNotHaveMoreThan1Cm() {
        Tower newTower = new Tower(10, 40);
        int initialHeight = newTower.height();
        newTower.pushLid(1);
        int finalHeight = newTower.height();
        assertEquals(1, finalHeight - initialHeight);
    } // Cierre del caso de prueba
    
    // swap
    /**
     * Verifica que la taza y tapa tengan el mismo número.
     */
    @Test
    public void shouldHaveSameNumber() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(13);
        newTower.pushLid(35);
        assertTrue(newTower.ok());
    } // Cierre del caso de prueba
    
    /**
     * Verifica que al intercambiar taza y tapa, no aumente la altura.
     */
    @Test
    public void shouldNotExceedHeight() {
        Tower newTower = new Tower(10, 40);
        int initialHeight = newTower.height();
        newTower.pushCup(25);
        newTower.pushLid(15);
        assertEquals(0, initialHeight);
    } // Cierre del caso de prueba
    
    //swapToReduce
    /**
     * Verifica si la altura se redujo o no al intercambiar dos objetos.
     */
    @Test
    public void shouldReduceHeight() {
        Tower newTower = new Tower(10, 40);
        newTower.pushCup(50);
        newTower.pushLid(30);
        assertNull(newTower.swapToReduce());
    }
    
    // cover
    /**
     * Verifica que no se tapen dos o más tapas.
     */
    @Test
    public void shouldNotCoverALid() {
        Tower newTower = new Tower(10, 40);
        newTower.pushLid(10);
        newTower.pushLid(10);
        assertFalse(newTower.ok());
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