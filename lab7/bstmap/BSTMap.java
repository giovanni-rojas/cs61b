package bstmap;

import java.util.Iterator;
import java.util.Set;

/** A data structure that uses a Binary Search Tree to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Key operations are get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. */
public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {

    int size = 0;

    /** Keys and values are stored in a BST of BSTNode objects.
     *  This variable stores the root node containing the first pair in BST. */
    private BSTNode bstNode;

    /** Represents one node in the BST that stores the key-value pairs
     *  in the dictionary. */
    private class BSTNode {

        /** Stores the key of the key-value pair of this node in the BST. */
        K key;
        /** Stores the value of the key-value pair of this node in the BST. */
        V val;
        /** Stores the next left and right children of the BSTNode. */
        BSTNode left;
        BSTNode right;

        /** Stores @code k as the key in this key-value pair, @code v as the value, and
         *  @code l and @code r as the left and right children of this node in the BST. */
        BSTNode(K k, V v, BSTNode l, BSTNode r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }

        /** Returns the BSTNode in this BST of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        BSTNode get(K k) {
            throw new UnsupportedOperationException();
        }

    }

    /** Returns the value corresponding to KEY or null if no such value exists. */
    public V get(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    /** Removes all the mappings from this map. */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /** Inserts the key-value pair of KEY and VALUE into this dictionary,
     *  replacing the previous value associated to KEY, if any. */
    public void put(K key, V val) {
        throw new UnsupportedOperationException();
    }

    /** Returns true if and only if this dictionary contains KEY as the
     *  key of some key-value pair. */
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }


    /** An iterator that iterates over the keys of the dictionary. */
    private class BSTMapIter implements Iterator<K> {

        /** Stores the current key-value pair. */
        private BSTNode current;

        /** Create a new BSTMapIter by setting cur to the first node in the
         *  BST that stores the key-value pairs. */
        public BSTMapIter() {
            current = bstNode;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

}
