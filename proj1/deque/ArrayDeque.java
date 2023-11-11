package deque;

public class ArrayDeque<Item> implements Deque<Item>, Iterable<Item> {
    private Item[] items;
    private int size;
    private int firstIndex;
    private int lastIndex;

    /** Constructor to create an empty list */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        firstIndex = items.length / 2;
        lastIndex = firstIndex;
    }

    /** Calculates the next firstIndex after an addFirst() */
    public int firstAfterAdd() {
        //firstIndex stays the same if array is empty
        if (isEmpty())
            return firstIndex;
        //next firstIndex loops back around to end of array
        else if (firstIndex == 0)
            return items.length - 1;
        else
            return firstIndex - 1;
    }

    /** Calculates the next lastIndex after and addLast() */
    public int lastAfterAdd() {
        //lastIndex stays the same if array is empty
        if (isEmpty())
            return lastIndex;
        //next lastIndex loops back around to front of array
        else if (lastIndex == items.length - 1)
            return 0;
        else
            return lastIndex + 1;
    }

    public void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        if(firstIndex < lastIndex)
            System.arraycopy(items, firstIndex, newArray, 0, size);
        else {
//            System.arraycopy(items, firstIndex, newArray, 0, size - firstIndex);
//            System.arraycopy(items, 0, newArray, size - firstIndex, lastIndex + 1);
            for(int i = 0; i < size; i++) {
                newArray[i] = get(i);
            }
        }
        firstIndex = 0;
        lastIndex = size - 1;
        items = newArray;
    }

    @Override
    /** Adds x to the front of the deque. */
    public void addFirst(Item x) {
        //case where array is full
        if(size == items.length)
            resize(size * 2);
        firstIndex = firstAfterAdd();
        items[firstIndex] = x;
        size += 1;
    }

    @Override
    /** Adds x to the end of the deque. */
    public void addLast(Item x) {
        //case where array is full
        if(size == items.length)
            resize(size * 2);
        lastIndex = lastAfterAdd();
        items[lastIndex] = x;
        size += 1;
    }

    @Override
    /** Prints items in deque from first to last, separated by a space */
    public void printDeque() {
        if(isEmpty())
            System.out.println();
        else {
            System.out.print(items[firstIndex]);
            int i = firstIndex + 1;
            while(i != lastIndex + 1) {
                if(i == items.length)
                    i = 0;
                System.out.print(" " + items[i]);
                i++;
            }
            System.out.println();
        }
    }

    /** Calculates the next firstIndex after a removeFirst() */
    public int firstAfterRemove() {
        //firstIndex stays the same if removing only entry
        if (size() == 1)
            return firstIndex;
        //next firstIndex loops back around to beginning of array
        else if (firstIndex == items.length - 1)
            return 0;
        else
            return firstIndex + 1;
    }

    /** Calculates the next lastIndex after a removeLast() */
    public int lastAfterRemove() {
        //lastIndex stays the same if removing only entry
        if (size() == 1)
            return lastIndex;
        //next lastIndex loops back around to end of array
        else if (lastIndex == 0)
            return items.length - 1;
        else
            return lastIndex - 1;
    }

    @Override
    /** Removes and returns the first item in the deque. Returns null if empty */
    public Item removeFirst() {
        if(isEmpty())
            return null;
        //case where array size is < 1/4 items.length
        if (size < items.length / 4)
            resize(size * 2);
        Item removed = items[firstIndex];
        firstIndex = firstAfterRemove();
        size -= 1;
        return removed;

    }

    @Override
    /** Removes and returns the last item in the deque. Returns null if empty */
    public Item removeLast() {
        if(isEmpty())
            return null;
        //case where array size is < 1/4 items.length
        if (size < items.length / 4)
            resize(size * 2);
        Item removed = items[lastIndex];
        lastIndex = lastAfterRemove();
        size -= 1;
        return removed;
    }

    /** Returns the first item in the deque. Helper for testing */
    public Item getFirst() {
        return items[firstIndex];
    }

    /** Returns the last item in the deque. Helper for testing */
    public Item getLast() {
        return items[lastIndex];
    }

    @Override
    /** Returns the item at the given index */
    public Item get(int index) {
        //array is empty or index is out of bounds
        if (isEmpty() || index >= size)
            return null;
        else if (firstIndex < lastIndex)
            return items[firstIndex + index];
        else {
            if (firstIndex + index < items.length)
                return items[firstIndex + index];
            //index needs to loop back to the front
            else
                return items[index - firstIndex];
        }
    }

    @Override
    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        ArrayDeque D = new ArrayDeque();
        D.addLast(0);
        D.addLast(1);
        D.addLast(2);
        D.addLast(3);
        D.addLast(4);
        D.addLast(5);
        D.addLast(6);
        D.addLast(7);
        D.removeFirst();
        D.removeLast();
        System.out.println("First removed is: " + D.removeFirst() + ", Last removed is: " + D.removeLast());
        D.printDeque();
    }
}
