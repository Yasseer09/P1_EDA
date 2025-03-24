package eda.solutions;
import eda.ds.HashTable;

public class TestTabla {
    public static void main(String[] args){
        //Primero vamos a crear una tabla
        HashTable<String, Integer> hashTable = new HashTable<>();

        //Insertar elementos en la tabla
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);

        //Recuperar un elemento de la tabla
        System.out.println("El valor asociado con la clave 'b' es: " + hashTable.get("b"));

        //Eliminar un elemento de la tabla
        hashTable.remove("b");

        // Verificar si una clave está en la tabla
        System.out.println("La clave 'c' está en la tabla? " + hashTable.contains("c"));

        // Obtener el tamaño de la tabla
        System.out.println("Tamaño de la tabla: " + hashTable.size());

        // Verificar si la tabla está vacía
        System.out.println("La tabla está vacía? " + hashTable.isEmpty());

        // Limpiar la tabla
        hashTable.clear();


    }
}
