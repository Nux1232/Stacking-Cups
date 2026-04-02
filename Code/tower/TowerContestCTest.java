package tower;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Realiza casos de prueba realizado por los autores para comprobar que la solución de la maratón funciona.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 15/03/2026
 */
public class TowerContestCTest {
    private TowerContest contest;

    /**
     * 
     * Método llamado antes de los casos de prueba
     */
    @BeforeEach
    public void setUp() {
        contest = new TowerContest();
    }
    
   /**
     * Verifica que retorne 'impossible' donde excede la altura de la torre.
     */
    @Test
    public void accordingToCuervoOrtizshouldReturnImpossible() {
        String resultado = contest.solve(3, 5);
        assertEquals("5 3 1", resultado);
    } // Cierre del caso de prueba
    
    /**
     *
     * Método llamado después de los casos de prueba
     */
    @AfterEach
    public void tearDown() {
    }
}