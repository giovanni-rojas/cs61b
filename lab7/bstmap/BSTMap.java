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
    private BSTNode bstMap;

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
            if (k != null && k.equals(key)) {
                return this;
            }
            else if (left != null && k.compareTo(key) < 0) {
                return left.get(k);
            }
            else if (right != null && k.compareTo(key) > 0) {
                return right.get(k);
            }
            return null;
        }

    }

    /** Returns the value corresponding to KEY or null if no such value exists. */
    public V get(K key) {
        if (bstMap == null) {
            return null;
        }
        BSTNode lookup = bstMap.get(key);
        if(lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    public int size() {
        return size;
    }

    /** Removes all the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        bstMap = null;
    }

    /** Inserts the key-value pair of KEY and VALUE into this dictionary,
     *  replacing the previous value associated to KEY, if any. */
    public void put(K key, V val) {
        if (bstMap != null) {
            BSTNode lookup = bstMap.get(key);

            //enter key/val into non-empty bstMap, where key not does not exist yet
            if (lookup == null) {
                put(key, val, bstMap);
                size = size + 1;
            }
            else {
                lookup.val = val;
            }
        }
        //empty map
        else {
            bstMap = new BSTNode(key, val, null, null);
            size = size + 1;
        }
    }

    private BSTNode put(K key, V val, BSTNode bstNode) {
        if (bstNode == null) {
            return new BSTNode(key, val, null, null);
        }
        if (key.compareTo(bstNode.key) < 0) {
            bstNode.left = put(key, val, bstNode.left);
        }
        else if (key.compareTo(bstNode.key) > 0) {
            bstNode.right = put(key, val, bstNode.right);
        }
        return bstNode;
    }

    /** Returns true if and only if this dictionary contains KEY as the
     *  key of some key-value pair. */
    public boolean containsKey(K key) {
        if (bstMap.get(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }


    /** An iterator that iterates over the keys of the dictionary. */
    private class BSTMapIterator implements Iterator<K> {

        /** Stores the current key-value pair. */
        private BSTNode current;

        /** Create a new BSTMapIter by setting cur to the first node in the
         *  BST that stores the key-value pairs. */
        public BSTMapIterator() {
            current = bstMap;
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

    public void printInOrder() {
        printInOrder(bstMap);
    }

    private void printInOrder(BSTNode bstNode) {
        while (bstNode != null) {
            printInOrder(bstNode.left);
            printInOrder(bstNode.right);
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
