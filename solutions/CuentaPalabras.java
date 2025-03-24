package eda.solutions;
import eda.ds.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class CuentaPalabras {
    public static void main(String[] args) {
        // Nombre del archivo
        String fileName = "Texto.txt"; // Se debe especificar solo el nombre del archivo si está en el mismo directorio que la clase

        // Verificar si el archivo existe
        if (Files.exists(Paths.get(fileName))) {
            // Crear una tabla hash para contar las palabras
            HashTable<String, Integer> contadorPalabras = new HashTable<>();

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String linea;
                int numeroLinea = 1;
                while ((linea = br.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(linea, " ,.!?:;");
                    int numeroPalabra = 1;
                    while (tokenizer.hasMoreTokens()) {
                        String palabra = tokenizer.nextToken().toLowerCase();
                        Integer contador = contadorPalabras.get(palabra);
                        if (contador == null) {
                            contadorPalabras.put(palabra, 1);
                        } else {
                            contadorPalabras.put(palabra, contador + 1);
                        }
                        System.out.println(palabra + " : [(" + numeroLinea + ":" + numeroPalabra + ")]");
                        numeroPalabra++;
                    }
                    numeroLinea++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Mostrar el conteo de palabras
            System.out.println("Conteo de palabras:");
            System.out.println(contadorPalabras);
        } else {
            System.out.println("El archivo '" + fileName + "' no existe en la ruta especificada.");
        }
    }

}
