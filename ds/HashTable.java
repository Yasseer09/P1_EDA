package eda.ds;
import eda.adt.Dictionary;
import java.util.Iterator;
public class HashTable<K, V> implements Dictionary<K, V>{
    //capacidad de la tabla
    private static final int cubetas = 16;
    // Umbral del factor de carga
    private static final double factor_carga = 0.75;

    private TableEntry<K, V>[] table; //Array de entradas de la tabla
    private int tamaño; //tamaño de la tabla

    public HashTable() {
        this(cubetas);
    }

    public HashTable(int cubetas) {
        table=new TableEntry[cubetas];
        tamaño=0;

    }

    private static class TableEntry<K, V> {
        K key;
        V value;
        TableEntry<K, V> next;

        TableEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private class Citerator implements Iterator<K> {
        private int index; //indice actual
        private TableEntry<K, V> entradas; // entrada actual en la tabla

        Citerator() { // Constructor del iterador
            this.index = -1;
            this.entradas = null;
        }

        @Override
        // Comprueba si hay un elemento siguiente en el iterador
        public boolean hasNext() {
            // Si hay una entrada siguiente en la lista enlazada actual
            if (entradas != null && entradas.next != null) {
                return true;
            }
            // Busca la próxima entrada no nula en la tabla
            for (int i = index + 1; i < table.length; i++) {
                if (table[i] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        // Devuelve la siguiente clave en el iterador
        public K next() {
            if (entradas != null && entradas.next != null) { // Si hay una entrada siguiente en la lista enlazada actual
                entradas = entradas.next; // Avanza a la siguiente entrada en la lista enlazada
                return entradas.key;  // Devuelve la clave de la entrada actual
            }
            do { // Busca la próxima entrada no nula en la tabla
                index++; // Avanza al siguiente índice en la tabla
                entradas = table[index]; // Obtiene la entrada en el índice actual
            } while (entradas == null);
            return entradas.key; // Devuelve la clave de la entrada actual
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Método para insertar una entrada en la tabla
    @Override
    public V put(K key, V value) {
        // Calcula el índice de la tabla para la clave dada
        int index = hash(key);
        // Obtiene la entrada actual en el índice calculado
        TableEntry<K, V> entradas = table[index];
        // Busca la clave en la lista enlazada en el índice calculado
        while (entradas != null) {
            // Si la clave ya existe, actualiza su valor y devuelve el valor antiguo
            if (entradas.key.equals(key)) {
                V valorAntiguo = entradas.value;
                entradas.value = value;
                return valorAntiguo;
            }
            // Avanza a la siguiente entrada en la lista enlazada
            entradas = entradas.next;
        }
        // Crea una nueva entrada y la coloca al principio de la lista enlazada
        TableEntry<K, V> nuevaEntrada = new TableEntry<>(key, value);
        nuevaEntrada.next = table[index];
        table[index] = nuevaEntrada;
        // Incrementa el tamaño de la tabla
        tamaño++;
        return null;
    }

    // Método para obtener el valor asociado con una clave
    @Override
    public V get(K key) {
        // Calcula el índice de la tabla para la clave dada
        int index = hash(key);
        // Obtiene la entrada actual en el índice calculado
        TableEntry<K, V> entradas = table[index];
        // Busca la clave en la lista enlazada en el índice calculado
        while (entradas != null) {
            // Si encuentra la clave, devuelve su valor asociado
            if (entradas.key.equals(key)) {
                return entradas.value;
            }
            // Avanza a la siguiente entrada en la lista enlazada
            entradas = entradas.next;
        }
        // Si la clave no existe en la tabla devuelve null
        return null;
    }


    //Metodo para eliminar una entrada de tabla
    @Override
    public V remove(K key){
        // Calcula el índice de la tabla para la clave dada
        int index = hash(key);
        // Obtiene la entrada actual en el índice calculado
        TableEntry<K, V> entradas = table[index];
        // Entrada anterior en la lista enlazada
        TableEntry<K, V> prevEntradas = null;
        // Busca la clave en la lista enlazada en el índice calculado
        while(entradas!=null){
            // Si encuentra la clave, elimina la entrada y devuelve su valor asociado
            if (entradas.key.equals(key)) {
                // Si hay una entrada anterior en la lista enlazada
                if (prevEntradas != null) {
                    prevEntradas.next = entradas.next;
                } else {
                    // Si la entrada es la primera en la lista enlazada
                    table[index] = entradas.next;
                }
                // Decrementa el tamaño de la tabla
                tamaño--;
                // Devuelve el valor asociado con la clave eliminada
                return entradas.value;

            }
            // Avanza a la siguiente entrada en la lista enlazada
            prevEntradas = entradas;
            entradas = entradas.next;
        }
        // La clave no existe en la tabla
        return null;

    }

    // Método para verificar si una clave está en la tabla
    public boolean contains(K key){
        return get(key) != null;
    }

    //Metodo para obtener el tamaño de la tabla
    public int size(){
        return tamaño;
    }

    //Metodo para verificiar si la tabla esta vacia
    public boolean isEmpty(){
        return tamaño==0;
    }

    //Metodo para eliminar todas las entradas de la tabla
    public void clear(){
        // Itera sobre todas las entradas de la tabla
        for (int i = 0; i < table.length; i++) {
            // Establece la entrada en el índice i como nula
            table[i] = null;
        }
        //establece el tamaño a 0
        tamaño=0;
    }

    //Metodo para obtener un iterador sobre las claves en la tabla
    public Iterator<K> iterator(){
        return new Citerator();
    }



}
