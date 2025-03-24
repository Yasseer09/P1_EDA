package eda.adt;
import java.util.Iterator;

import eda.exceptions.WrongIndexException;

public interface List<E> extends Iterable<E>{
    void insert(int pos, E data) throws WrongIndexException;
    void delete(int pos) throws WrongIndexException;
    E get(int pos) throws WrongIndexException;
    int search(E data);
    java.util.Iterator<E> iterator();
    int size();

    
} 
