package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int firstIndex;
    private int lastIndex;

    /** Constructor to create an empty list */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        firstIndex = items.length / 2;
        lastIndex = firstIndex;
    }

    /** Calculates the next firstIndex after an addFirst() */
    private int firstAfterAdd() {
        //firstIndex stays the same if array is empty
        if (isEmpty()) {
            return firstIndex;
        } else if (firstIndex == 0) {
            return items.length - 1;
        } else {
            return firstIndex - 1;
        }
    }

    /** Calculates the next lastIndex after and addLast() */
    private int lastAfterAdd() {
        //lastIndex stays the same if array is empty
        if (isEmpty()) {
            return lastIndex;
        } else if (lastIndex == items.length - 1) {
            return 0;
        } else {
            return lastIndex + 1;
        }
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        if (firstIndex < lastIndex) {
            System.arraycopy(items, firstIndex, newArray, 0, size);
        } else {
//            System.arraycopy(items, firstIndex, newArray, 0, size - firstIndex);
//            System.arraycopy(items, 0, newArray, size - firstIndex, lastIndex + 1);
            for (int i = 0; i < size; i++) {
                newArray[i] = get(i);
            }
        }
        firstIndex = 0;
        lastIndex = size - 1;
        items = newArray;
    }

    @Override
    /** Adds x to the front of the deque. */
    public void addFirst(T x) {
        //case where array is full
        if (size == items.length) {
            resize(size * 2);
        }
        firstIndex = firstAfterAdd();
        items[firstIndex] = x;
        size += 1;
    }

    @Override
    /** Adds x to the end of the deque. */
    public void addLast(T x) {
        //case where array is full
        if (size == items.length) {
            resize(size * 2);
        }
        lastIndex = lastAfterAdd();
        items[lastIndex] = x;
        size += 1;
    }

    @Override
    /** Prints items in deque from first to last, separated by a space */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
        } else {
            System.out.print(items[firstIndex]);
            int i = firstIndex + 1;
            while (i != lastIndex + 1) {
                if (i == items.length) {
                    i = 0;
                }
                System.out.print(" " + items[i]);
                i++;
            }
            System.out.println();
        }
    }

    /** Calculates the next firstIndex after a removeFirst() */
    private int firstAfterRemove() {
        //firstIndex stays the same if removing only entry
        if (size() == 1) {
            return firstIndex;
        } else if (firstIndex == items.length - 1) {
            return 0;
        } else {
            return firstIndex + 1;
        }
    }

    /** Calculates the next lastIndex after a removeLast() */
    private int lastAfterRemove() {
        //lastIndex stays the same if removing only entry
        if (size() == 1) {
            return lastIndex;
        } else if (lastIndex == 0) {
            return items.length - 1;
        } else {
            return lastIndex - 1;
        }
    }

    @Override
    /** Removes and returns the first item in the deque. Returns null if empty */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        //case where array size is < 1/4 items.length
        if (size < items.length / 4 + 1 && items.length > 16) {
            resize(size * 2);
        }
        T removed = items[firstIndex];
        firstIndex = firstAfterRemove();
        size -= 1;
        return removed;

    }

    @Override
    /** Removes and returns the last item in the deque. Returns null if empty */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        //case where array size is < 1/4 items.length
        if (size < items.length / 4 + 1 && items.length > 16) {
            resize(size * 2);
        }
        T removed = items[lastIndex];
        lastIndex = lastAfterRemove();
        size -= 1;
        return removed;
    }

    @Override
    /** Returns the item at the given index */
    public T get(int index) {
        //array is empty or index is out of bounds
        if (isEmpty() || index >= size) {
            return null;
        } else if (firstIndex < lastIndex) {
            return items[firstIndex + index];
        } else {
            if (firstIndex + index < items.length) {
                return items[firstIndex + index];
            } else {
                return items[index - (items.length - firstIndex)];
            }
        }
    }

    @Override
    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;
        ArrayDequeIterator() {
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
        if (!(o instanceof Deque)) {
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
