package eda.solutions;
import eda.ds.ListImpl;
import eda.adt.List;
import eda.exceptions.WrongIndexException;

public class TestLista{
// Método principal para realizar pruebas
    public static void main(String[] args) {
        // Creación de una instancia de ListImpl
        List<String> list = new ListImpl<>();
        try {
            // Operaciones de inserción
            list.insert(0, "A");
            list.insert(1, "B");
            list.insert(2, "C");
            list.insert(1, "D");

            // Impresión del tamaño de la lista
            System.out.println("Tamaño: " + list.size());

            // Obtención de un elemento en una posición dada
            System.out.println("Posicion del elemento 2: " + list.get(2));

            // Búsqueda de un elemento y obtención de su posición
            System.out.println("Indice de 'B': " + list.search("B"));

            // Eliminación de un elemento en una posición dada
            list.delete(1);
            System.out.println("Tamaño despues de eliminar: " + list.size());

        } catch (WrongIndexException e) {
            e.printStackTrace();
        }
    }

}