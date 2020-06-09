/*
 * Name: Yin Lam Lai
 * PID: A15779757
 */

import java.util.*;

/**
 * Creates a Hashtable that takes a takes in one input
 *
 * @param <T> Generic type of value
 * @author Yin lam Lai
 * @since A15779757
 */
public class HashTable<T> {

    // constants
    public static final int RESIZE_FACTOR = 2; // resize factor
    public static final int MIN_CAPACITY = 10; // minimum initial capacity
    public static final double MAX_LOAD_FACTOR = (double) 2 / 3; // maximum load factor

    // instance variables
    private LinkedList<T>[] table; // data storage
    private int nElems; // number of elements stored

    /**
     * Constructor for hash table.
     * 
     * @throws IllegalArgumentException if capacity is less than the minimum
     *                                  threshold
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException();
        }
        table = new LinkedList[capacity];
        nElems = 0;
        for (int n = 0; n < table.length; n++) {
            table[n] = new LinkedList<>();
        }

    }

    /**
     * Insert the value into the hash table.
     *
     * @param value value to insert
     * @return true if the value was inserted, false if the value was already
     *         present
     * @throws NullPointerException if the value is null
     */
    public boolean insert(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (lookup(value)) {
            return false;
        }
        double rehash1 = (double) nElems / capacity();

        if (rehash1 > MAX_LOAD_FACTOR) {
            rehash();
        }
        table[hashValue(value)].add(value);
        nElems++;
        return true;

    }

    /**
     * Delete the given value from the hash table.
     *
     * @param value value to delete
     * @return true if the value was deleted, false if the value was not found
     * @throws NullPointerException if the value is null
     */
    public boolean delete(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (!lookup(value)) {
            return false;
        }

        LinkedList<T> list = table[hashValue(value)];
        list.remove(value);
        nElems--;
        return true;
    }

    /**
     * Check if the given value is present in the hash table.
     *
     * @param value value to look up
     * @return true if the value was found, false if the value was not found
     * @throws NullPointerException if the value is null
     */
    public boolean lookup(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        LinkedList list = table[hashValue(value)];
        if (list.contains(value)) {
            return true;
        }
        return false;
    }

    /**
     * Get the total number of elements stored in the hash table.
     *
     * @return total number of elements
     */
    public int size() {
        return nElems;
    }

    /**
     * Get the capacity of the hash table.
     *
     * @return capacity
     */
    public int capacity() {
        return table.length;
    }

    /**
     * Hash function calculated by the hash code of value.
     *
     * @param value input
     * @return hash value (index)
     */
    private int hashValue(T value) {
        return value.hashCode() % table.length;
    }

    /**
     * Double the capacity of the array and rehash all values.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<T> listOG = new LinkedList<>();
        for (LinkedList<T> n : table) {
            if (n.isEmpty()) {
                continue;
            } else {
                listOG.addAll(n);
            }
        }
        table = new LinkedList[capacity() * RESIZE_FACTOR];
        for (int n = 0; n < table.length; n++) {
            table[n] = new LinkedList<>();
        }
        nElems = 0;
        for (int n = 0; n < listOG.size(); n++) {
            insert(listOG.get(n));

        }
    }

}
