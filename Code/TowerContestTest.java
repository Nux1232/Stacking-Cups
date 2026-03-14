import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase TowerContest (2 pruebas por cada método).
 *
 * @author Juan Pablo Cuervo Contreras 
 * @author David Felipe Ortiz Salcedo
 * @version 14/03/2026
 */
public class TowerContestTest {
    private TowerContest contest;

    /**
     * Default constructor for test class TowerContestTest
     */
    public TowerContestTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        contest = new TowerContest();
    } // Cierre del caso de prueba

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba

    // ==========================================
    // Pruebas para el método solve(int n, int h)
    // ==========================================

    /**
     * Prueba 1 para el método solve.
     * Verifica que retorne la secuencia correcta de tazas para un caso válido (n=4, h=9).
     */
    @Test
    public void testSolveCasoValido() {
        // El caso n=4, h=9 es válido.
        // Verificamos que devuelva la secuencia calculada correctamente.
        String resultado = contest.solve(4, 9);
        assertEquals("7 3 5 1", resultado, "El resultado debería ser la secuencia '7 3 5 1' para n=4 y h=9");
    } // Cierre del caso de prueba

    /**
     * Prueba 2 para el método solve.
     * Verifica que retorne 'impossible' para un caso donde la altura es inalcanzable (h mayor al máximo).
     */
    @Test
    public void testSolveCasoImposible() {
        // El caso n=4, h=100 es inalcanzable porque el máximo es 16 (n^2).
        String resultado = contest.solve(4, 100);
        assertEquals("impossible", resultado, "El resultado debería ser 'impossible' para un h inalcanzable");
    } // Cierre del caso de prueba

    // ==============================================
    // Pruebas para el método simulate(int n, int h)
    // ==============================================

    /**
     * Prueba 1 para el método simulate.
     * Verifica que no lance excepciones al intentar simular gráficamente un caso válido.
     */
    @Test
    public void testSimulateCasoValido() {
        // Se espera que la ejecución termine correctamente sin excepciones al crear la visualización.
        assertDoesNotThrow(() -> {
            contest.simulate(4, 9);
        }, "El método simulate no debería lanzar excepciones al graficar un caso válido");
    } // Cierre del caso de prueba

    /**
     * Prueba 2 para el método simulate.
     * Verifica que no lance excepciones al intentar simular un caso imposible.
     * Nota: En entorno gráfico, esto desplegará un JOptionPane, pero validamos que a nivel de código no falle.
     */
    @Test
    public void testSimulateCasoImposible() {
        // Se espera que la ejecución termine mostrando el mensaje sin lanzar excepciones en el código.
        assertDoesNotThrow(() -> {
            // Este llamado internamente hace un JOptionPane.showMessageDialog por ser "impossible"
            contest.simulate(4, 100);
        }, "El método simulate no debería lanzar excepciones para un caso imposible");
    } // Cierre del caso de prueba

    // ============================================
    // Pruebas para el método main(String[] args)
    // ============================================

    /**
     * Prueba 1 para el método main.
     * Verifica que el método principal se ejecute completamente sin lanzar errores.
     */
    @Test
    public void testMainEjecucionExitosa() {
        assertDoesNotThrow(() -> {
            TowerContest.main(new String[]{});
        }, "El método main no debería lanzar excepciones durante su ejecución");
    } // Cierre del caso de prueba

    /**
     * Prueba 2 para el método main.
     * Verifica que la salida por consola (System.out) del método main contenga los resultados esperados.
     */
    @Test
    public void testMainSalidaConsola() {
        // Capturar la salida estándar de consola
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Ejecutar el main
        TowerContest.main(new String[]{});

        // Restaurar la salida original
        System.setOut(originalOut);

        // Validar el contenido de la salida
        String salida = outContent.toString();
        assertTrue(salida.contains("Resultados de la Competencia"), "La salida debe contener el encabezado");
        assertTrue(salida.contains("n=4, h=9 => 7 3 5 1"), "La salida debe mostrar el resultado del primer caso evaluado");
        assertTrue(salida.contains("n=4, h=100 => impossible"), "La salida debe mostrar el resultado del caso imposible");
    } // Cierre del caso de prueba
} // Cierre de la clase