import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.Set;
import java.util.HashSet;

/**
 * Clase que resuelve y simula el problema de la maratón de las tazas.
 * Implementa la lógica matemática para encontrar el orden de las tazas y su respectiva visualización gráfica.
 *
 * @author Juan Pablo Cuervo Contreras
 * @author David Felipe Ortiz Salcedo
 * @version 09/03/2026
 */
public class TowerContest {

    /**
     * Resuelve el problema de la maratón determinando el orden de las tazas.
     * La entrada y la salida corresponden a lo definido en el problema de la maratón.
     *
     * @param n Cantidad de tazas a poner en la torre.
     * @param h La altura requerida que debe alcanzar la torre.
     * @return Cadena de texto con las alturas de las tazas en el orden que deben colocarse, o "impossible" si no es posible.
     */
    public String solve(int n, int h) {
        long targetH = h; // Casteo a long interno para evitar desbordamientos matemáticos con n grandes
        
        if(targetH < (2L * n - 1) || targetH > ((long)n * n)) {
            return "impossible";
        }
        
        // Estrategia 1: Base Stack (C, n, B)
        long targetC = targetH - (2L * n - 1);
        List<Integer> C = getSubset(n - 1, targetC);
        if (C != null) {
            Set<Integer> cSet = new HashSet<>(C);
            List<Integer> seq = new ArrayList<>();
            
            Collections.sort(C);
            seq.addAll(C);
            seq.add(n);
            
            List<Integer> B = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                if (!cSet.contains(i)) {
                    B.add(i);
                }
            }
            B.sort(Collections.reverseOrder());
            seq.addAll(B);
            
            return formatResult(seq);
        }
        
        // Estrategia 2: Inner Stack (n, A, B)
        long targetAPrime = targetH - (2L * n - 2);
        List<Integer> APrime = getSubset(n - 2, targetAPrime);
        if (APrime != null) {
            Set<Integer> aPrimeSet = new HashSet<>(APrime);
            List<Integer> seq = new ArrayList<>();
            
            seq.add(n);
            
            List<Integer> AList = new ArrayList<>(APrime);
            AList.add(n - 1);
            Collections.sort(AList);
            seq.addAll(AList);
            
            List<Integer> B = new ArrayList<>();
            for (int i = 1; i <= n - 2; i++) {
                if (!aPrimeSet.contains(i)) {
                    B.add(i);
                }
            }
            B.sort(Collections.reverseOrder());
            seq.addAll(B);
            
            return formatResult(seq);
        }
        
        return "impossible";
    }

    /**
     * Simula y grafica la solución del problema de la maratón empleando la clase Tower.
     * La entrada corresponde a lo definido en el problema de la maratón. La salida es la imagen 
     * de la solución, si existe y es posible graficarla; en caso contrario, presenta un mensaje indicándolo.
     * 
     * @param n Cantidad de tazas a poner en la torre.
     * @param h La altura requerida que debe alcanzar la torre.
     */
    public void simulate(int n, int h) {
        String result = solve(n, h);
        
        if (result.equals("impossible")) {
            JOptionPane.showMessageDialog(null, "No es posible graficar la solución para n=" + n + " y h=" + h + " (impossible).");
            return;
        }
        
        int width = 200; // Ancho predeterminado para la torre
        Tower simulator = new Tower(width, h);
        
        String[] heightsStr = result.split(" ");
        for (String heightStr : heightsStr) {
            int cupHeight = Integer.parseInt(heightStr);
            simulator.pushCup(cupHeight); // Envía el tamaño calculado real (ej: 1, 3, 5, 7)
        }
        
        simulator.makeVisible();
    }

    /**
     * Calcula un subconjunto válido de números impares que sumen el valor objetivo.
     * Método auxiliar privado.
     * 
     * @param m   El límite superior del índice (taza) a evaluar.
     * @param S   La suma objetivo que se desea alcanzar.
     * @return    Una lista con los índices de las tazas que forman la suma, o null si no existe combinación.
     */
    private List<Integer> getSubset(int m, long S) {
        if (S == 0) {
            return new ArrayList<>();
        }
        if (m <= 0) {
            return null;
        }
        
        int validK = -1;
        for (int k = m; k >= 1; k--) {
            if ((k % 2) == (S % 2)) {
                long minSum = (long) k * k;
                long maxSum = (long) k * (2L * m - k);
                if (S >= minSum && S <= maxSum) {
                    validK = k;
                    break;
                }
            }
        }
        
        if (validK == -1) {
            return null;
        }
        
        List<Integer> subset = new ArrayList<>();
        int k = validK;
        long currentS = S;
        
        for (int i = m; i >= 1; i--) {
            long x = 2L * i - 1;
            if (k > 0 && currentS - x >= (long)(k - 1) * (k - 1)) {
                subset.add(i);
                currentS -= x;
                k--;
                if (k == 0) {
                    break;
                }
            }
        }
        return subset;
    }

    /**
     * Formatea la secuencia de tazas a la salida requerida por el problema.
     * Método auxiliar privado.
     * 
     * @param seq Lista con los índices de orden de las tazas.
     * @return    Cadena con las alturas (2x-1) de las tazas en orden separadas por espacio.
     */
    private String formatResult(List<Integer> seq) {
        StringJoiner result = new StringJoiner(" ");
        for (int cup : seq) {
            long height = 2L * cup - 1;
            result.add(String.valueOf(height));
        }
        return result.toString();
    }

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
    }
}
