import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba realizado por los autores para comprobar que el proyecto funciona.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 1.0
 * @version 08/03/2026
 */
public class TowerCC2Test {
    /**
     * Método llamado antes de cada caso de prueba.
     */
    @BeforeEach
    public void setUp() {
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
}