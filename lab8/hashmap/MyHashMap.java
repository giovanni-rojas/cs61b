package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Gio R
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private Set<K> keys;
    private static final int INIT_CAPACITY = 16;
    private static final double INIT_LOAD_FACTOR = 0.75;

    //Number of elements
    private int n;

    //Number of buckets
    private int m;

    private double maxLoadFactor;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(INIT_CAPACITY, INIT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, INIT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        n = 0;
        m = initialSize;
        maxLoadFactor = maxLoad;
        buckets = createTable(initialSize);
        for (int i = 0; i < m; i ++) {
            buckets[i] = createBucket();
        }
        keys = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        n = 0;
        for (int i = 0; i < m; i ++) {
            buckets[i] = createBucket();
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    /**
     * Returns an int hash of an inserted key
     */
    private int hash(K key) {
        return Math.floorMod(key.hashCode() & 0x7fffffff, m);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node target = getNode(key);
        if (target != null) {
            return target.value;
        }
        return null;
    }

    private Node getNode(K key) {
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return n;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        Node current = getNode(key);
        double currentLoadFactor = (double) n / m;
        if (currentLoadFactor > maxLoadFactor) {
            resize(m * 2);
        }
        //Case where key already exists, so replace val
        if (containsKey(key)) {
            current.value = value;
        }
        //Key does not currently exist
        else {
            keys.add(key);
            int i = hash(key);
            Node newNode = createNode(key, value);
            buckets[i].add(newNode);
            n++;
        }
    }

    private void resize(int newSize) {
        MyHashMap<K, V> newHashMap = new MyHashMap<>(newSize, maxLoadFactor);
        for (K key : keys) {
            newHashMap.put(key, get(key));
        }
        this.m = newHashMap.m;
        this.buckets = newHashMap.buckets;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
