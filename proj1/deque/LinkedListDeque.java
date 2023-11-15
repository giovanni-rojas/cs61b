package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;
        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }


    private Node sentinel;
    private int size;

    /** Creates an empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

//    /** Creates a LinkedListDeque with one <T> */
//    public LinkedListDeque(T x) {
//        sentinel = new Node(null, null, null);
//        sentinel.next = new Node(x, sentinel, sentinel);
//        sentinel.prev = sentinel.next;
//        size = 1;
//    }

    @Override
    /** Adds x to the front of the deque. */
    public void addFirst(T x) {
        if (isEmpty()) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            Node p = new Node(x, sentinel, sentinel.next);
            sentinel.next.prev = p;
            sentinel.next = p;
        }
        size = size + 1;
    }

    @Override
    /** Adds x to the end of the deque. */
    public void addLast(T x) {
        if (isEmpty()) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            Node p = new Node(x, sentinel.prev, sentinel);
            sentinel.prev.next = p;
            sentinel.prev = p;
        }
        size = size + 1;
    }

    @Override
    /** Prints items in deque from first to last, separated by a space */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
        } else {
            Node p = sentinel.next;
            System.out.print(p.item);
            p = p.next;
            while (p != sentinel) {
                System.out.print(" " + p.item);
                p = p.next;
            }
            System.out.println();
        }
    }

    @Override
    /** Removes and returns the first item in the deque. Returns null if empty */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node first = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first.item;
        }
    }

    @Override
    /** Removes and returns the last item in the deque. Returns null if empty */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node last = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last.item;
        }
    }

    @Override
    /** Returns the item at the given index */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        } else {
            int i = 0;
            for (Node p = sentinel.next; p != sentinel; p = p.next) {
                if (i == index) {
                    return p.item;
                }
                i++;
            }
            return null;
        }
    }

    /** Helper function for getRecursive */
    private T getRecursiveHelper(int targetIndex, int currIndex, Node p) {
        if (p.item == null) {
            return null;
        } else if (currIndex == targetIndex) {
            return p.item;
        }
        return getRecursiveHelper(targetIndex, currIndex + 1, p.next);
    }

    /** Returns the item at the given index, using recursion */
    public T getRecursive(int index) {
        if (index >= size()) {
            return null;
        }
        return getRecursiveHelper(index, 0, sentinel.next);
    }

    @Override
    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new LLDequeIterator();
    }

    private class LLDequeIterator implements Iterator<T> {
        private int position;
        LLDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnT = get(position);
            position += 1;
            return returnT;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }
        Deque<T> od = (Deque<T>) o;
        if (this.size != od.size()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (!(get(i).equals(od.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
