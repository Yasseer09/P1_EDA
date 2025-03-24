package eda.ds;

import java.util.Collection;
import java.util.Iterator;
import eda.adt.List;
import java.util.ListIterator;

import eda.exceptions.WrongIndexException;

public class ListImpl<E> implements List<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class CIterator implements Iterator<E> {
        Node<E> next = null;

        CIterator(Node<E> next) {

            this.next = next;
        }

        @Override
        public E next() {
            Node<E> prev = next;
            next = next.next;
            return prev.data;
        }

        public boolean hasNext(){
            return false;
        }
    }

    private Node<E> first = null;
    private int count = 0; // numero de datos que hay

    public ListImpl() {
        // contructor
    }

    public void insert(int pos, E data) throws WrongIndexException {
        System.out.println("insert(" + pos + "," + data + ")");
        // comprobacion de que pos es valido
        if (pos < 0 || pos > count) {
            throw new WrongIndexException("Índice fuera de los límites");
        }
        // proceso para insertar el nuevo dato
        Node<E> newNode = new Node<>(data, null);
        if (pos == 0) {
            //es el primer nodo
            newNode.next = first;
            first = newNode;
        } else {
            Node<E> prev = first;
            for (int i = 0; i < pos - 1; i++) {
                //guardarmos el elemento en la posicion que nos pasan
                prev = prev.next;
            }
            //nos apunta al elemento que queremos añadir
            newNode.next = prev.next;
            //lo añadimos
            prev.next = newNode;
        }
        count++;
    }

    public void delete(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= count) {
            throw new WrongIndexException("Posicion invalida");
        }
        if(pos==0){
            //se elimina el primer nodo de la lista
            first=first.next;

        }else{
            Node<E> prev=first;
            //recorremos la lista hasta la posicion anterior a la que queremos eliminar
            for(int i=0;i<pos-1;i++){
                //nos apunta hacia el elemento que queremos eliminar
                prev=prev.next;
            }
            //actualizamos la referencia del siguiente nodo
            prev.next=prev.next.next;


        }
        count--;


    }

    public E get(int pos) throws WrongIndexException {
        System.out.println("get(" + pos + ")");
        // comprobacion de que pos es valido
        if (pos < 0 || pos >= count) {
            throw new WrongIndexException("Índice fuera de los límites");
        }
        // proceso para obtener el dato en la posición pos
        Node<E> prev = first;
        for (int i = 0; i < pos; i++) {
            //nos apunta hacia el elemento que queremos devolver
            prev = prev.next;
        }
        //devolvemos el dato
        return prev.data;
    
    }

    public int search(E data) {
        // Proceso para buscar el dato en la lista
        Node<E> prev = first;
        int pos = 0;
        while(prev!=null){
            //si el igual el nodo que el que nos pasan
            if (prev.data.equals(data)) {
                return pos;
            }
            //apunta al siguiente nodo
            prev = prev.next;
            //aumenta la pos para el siguiente nodo
            pos++;
        }
        return 1;
    }

    public java.util.Iterator<E> iterator() {

        return new CIterator(first);
    }

    public int size() {
        return count;
    }



}
