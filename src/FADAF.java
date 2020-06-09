/*
 * Name: Yin Lam Lai
 * PID: A15779757
 */

import java.util.*;

/**
 * Creates a FADAF structure that uses a Hash table and a DAF Tree
 *
 * @param <K> Generic type of key
 * @param <D> Generic type of data
 * @author Yin Lam Lai
 * @since 6/6/2020
 */
public class FADAF<K extends Comparable<? super K>, D> {

    DAFTree<K, D> tree;
    HashTable<K> hashTable;

    /**
     * Constructor for FADAF.
     * 
     * @param capacity initial capacity
     * @throws IllegalArgumentException if capacity is less than the minimum
     *                                  threshold
     */
    public FADAF(int capacity) {
        tree = new DAFTree<>();
        hashTable = new HashTable<>(capacity);

    }

    /**
     * Returns the total number of key-data pairs stored.
     * 
     * @return count of key-data pairs
     */
    public int size() {
        return tree.size();
    }

    /**
     * Returns the total number of unique keys stored.
     * 
     * @return count of unique keys
     */
    public int nUniqueKeys() {
        return tree.nUniqueKeys();
    }

    /**
     * Insert the given key-data pair.
     * 
     * @param key  key to insert
     * @param data data to insert
     * @return true if the pair is inserted, false if the pair was already present
     * @throws NullPointerException if key or data is null
     */
    public boolean insert(K key, D data) {
        DAFTree<K, D>.DAFNode<K, D> check = tree.insert(key, data);
        if (check == null) {
            return false;
        }
        if (!hashTable.lookup(key)) {
            hashTable.insert(key);
        }
        return true;

    }

    /**
     * Remove all key-data pairs that share the given key from the FADAF.
     * 
     * @param key key to remove
     * @return true if at least 1 pair is removed, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean removeAll(K key) {
        if (hashTable.delete(key)) {
            tree.removeAll(key);
            return true;
        }
        return false;
    }

    /**
     * Remove the specified pair from the FADAF.
     * 
     * @param key  key of the pair to remove
     * @param data data of the pair to remove
     * @return true if this pair is removed, false if this pair is not present
     * @throws NullPointerException if key or data is null
     */
    public boolean remove(K key, D data) {
        boolean removedFromTree = tree.remove(key, data);
        if (!removedFromTree) {
            return false;
        }
        if (!tree.lookupAny(key)) {
            hashTable.delete(key);
            return true;

        }
        return true;
    }

    /**
     * Check if any pair with the given key is stored.
     * 
     * @param key key to lookup
     * @return true if any pair is found, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean lookupAny(K key) {
        return hashTable.lookup(key);
    }

    /**
     * Check if a pair with the given key and data is stored.
     * 
     * @param key  key of the pair to lookup
     * @param data data of the pair to lookup
     * @return true if the pair is found, false otherwise
     * @throws NullPointerException if key or data is null
     */
    public boolean lookup(K key, D data) {
        return tree.lookup(key, data);
    }

    /**
     * Return a LinkedList of all keys (including duplicates) in ascending order.
     * 
     * @return a list of all keys, empty list if no keys stored
     */
    public LinkedList<K> getAllKeys() {
        LinkedList<K> keys = new LinkedList<>();
        Iterator<DAFTree<K, D>.DAFNode<K, D>> iter = tree.iterator();
        while (iter.hasNext()) {
            keys.addFirst(iter.next().key);
        }
        return keys;
    }

    /**
     * Return a LinkedList of data paired with the given key.
     * 
     * @param key target key
     * @return a list of data
     * @throws NullPointerException if the key is null
     */
    public LinkedList<D> getAllData(K key) {
        return tree.getAllData(key);
    }

    /**
     * Return the minimum key stored.
     * 
     * @return minimum key, or null if no keys stored
     */
    public K getMinKey() {
       
        Iterator<DAFTree<K, D>.DAFNode<K, D>> iter = tree.iterator();
        if (!iter.hasNext()) {
            return null;
        }
        K min = iter.next().key;
        while (iter.hasNext()) {
            K next = iter.next().key;

            if (min.compareTo(next) > 0) {
                min = next;
            }
        }

        return min;
    }

    /**
     * Return the maximum key stored.
     * 
     * @return maximum key, or null if no keys stored
     */
    public K getMaxKey() {

        Iterator<DAFTree<K, D>.DAFNode<K, D>> iter = tree.iterator();
        if (!iter.hasNext()) {
            return null;
        }
        K max = iter.next().key;
        while (iter.hasNext()) {
            K next = iter.next().key;

            if (max.compareTo(next) < 0) {
                max = next;
            }
        }
        return max;
    }

}
