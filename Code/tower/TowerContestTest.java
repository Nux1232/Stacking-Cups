package tower;

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
 * @version 15/03/2026
 */
public class TowerContestTest {
    private TowerContest contest;

    /**
     *
     * Método llamado antes de los casos de prueba.
     */
    @BeforeEach
    public void setUp() {
        contest = new TowerContest();
    } // Cierre del caso de prueba
    
    // solve
    /**
     * Verifica que retorne la secuencia correcta de tazas para un caso válido. Ejemplo: n = 4 y h = 9
     */
    @Test
    public void shouldSolveAValidCase() {
        String answer = contest.solve(4, 9);
        assertEquals("7 3 5 1", answer);
    } // Cierre del caso de prueba

    /**
     * Verifica que retorne 'impossible' donde excede la altura de la torre.
     */
    @Test
    public void shouldReturnImpossible() {
        String resultado = contest.solve(4, 100);
        assertEquals("impossible", resultado);
    } // Cierre del caso de prueba
    
    // simulate
    /**
     * Verifica que no lance excepciones al intentar simular gráficamente un caso válido.
     */
    @Test
    public void shouldNotThrowExceptionAtAValidCase() {
        assertDoesNotThrow(() -> {
            contest.simulate(4, 9);
        }, "No se debe lanzar excepción");
    } // Cierre del caso de prueba

    /**
     * Verifica que no lance excepciones al intentar simular un caso imposible.
     */
    @Test
    public void shouldThrowExceptionIfImpossible() {
        assertDoesNotThrow(() -> {
            contest.simulate(4, 100);
        }, "No se debe lanzar excepción");
    } // Cierre del caso de prueba

    //main
    /**
     * Verifica que el método principal se ejecute normal.
     */
    @Test
    public void shouldExecuteNormally() {
        assertDoesNotThrow(() -> {
            main(new String[]{});
        }, "No se debe lanzar excepción");
    } // Cierre del caso de prueba

    /**
     * Verifica que la salida por consola tenga los resultados esperados.
     */
    @Test
    public void shouldReturnExpectedAnswer() {
        // Capturar la salida estándar de consola
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Ejecutar el main
        main(new String[]{});

        // Restaurar la salida original
        System.setOut(originalOut);

        // Validar el contenido de la salida
        String salida = outContent.toString();
        assertTrue(salida.contains("Resultados de la Competencia"), "La salida debe contener el encabezado");
        assertTrue(salida.contains("n=4, h=9 => 7 3 5 1"), "La salida debe mostrar el resultado del primer caso evaluado");
        assertTrue(salida.contains("n=4, h=100 => impossible"), "La salida debe mostrar el resultado del caso imposible");
    } // Cierre del caso de prueba
    
        /**
     * Método principal para ejecutar y validar los casos de prueba de la competencia.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        TowerContest contest = new TowerContest();

        int[][] testCases = {
                {4, 9},
                {4, 100}
        };

        System.out.println("Resultados de la Competencia");
        for (int[] test : testCases) {
            int n = test[0];
            int h = test[1];
            String result = contest.solve(n, h);
            System.out.println("n=" + n + ", h=" + h + " => " + result);
        }
    } // Cierre del método
    /**
     *
     * Método llamado después de los casos de prueba.
     */
    @AfterEach
    public void tearDown() {
    } // Cierre del caso de prueba
} // Cierre de la clase