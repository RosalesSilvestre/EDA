package clases;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class generador {

    // Función para generar una permutación aleatoria de los números del 1 al 1000
    public static List<Integer> generateRandomPermutation(int n) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers); // Mezcla los elementos de la lista para generar una permutación aleatoria
        return numbers;
    }

    // Función para generar 100 permutaciones aleatorias y escribirlas en un archivo de texto
    public static void generatePermutationsToFile(String fileName, int numberOfPermutations, int range) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < numberOfPermutations; i++) {
                List<Integer> permutation = generateRandomPermutation(range);
                for (int num : permutation) {
                    writer.write(num + " ");
                }
                writer.newLine();
                writer.flush();// Nueva línea para cada permutación
            }
            System.out.println("Permutaciones generadas y escritas en el archivo: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = "src\\clases\\permutaciones.txt";
        int numberOfPermutations = 500;
        int range = 1000;

        // Genera 100 permutaciones de números entre 1 y 1000 y las guarda en un archivo de texto
        generatePermutationsToFile(fileName, numberOfPermutations, range);
    }
}
