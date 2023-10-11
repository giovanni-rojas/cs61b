package deque;

public class LinkedListDeque<Item> {
    private class Node {
        public Item item;
        public Node prev;
        public Node next;
        public Node(Item i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /*The first item (if it exists) is at sentinel.next, and the last item (if it exists) is at sentinel.prev */
    private Node sentinel;
    private int size;

    /** Creates an empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

//    /** Creates a LinkedListDeque with one <Item> */
//    public LinkedListDeque(Item x) {
//        sentinel = new Node(null, null, null);
//        sentinel.next = new Node(x, sentinel, sentinel);
//        sentinel.prev = sentinel.next;
//        size = 1;
//    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Adds x to the front of the deque. */
    public void addFirst(Item x) {
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

    /** Adds x to the end of the deque. */
    public void addLast(Item x) {
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

    /** Prints items in deque from first to last, separated by a space */
    public void printDeque() {
        if(isEmpty()) {
            System.out.println();
        } else {
            Node p = sentinel.next;
            System.out.print(p.item);
            p = p.next;
            while(p != sentinel){
                System.out.print(" " + p.item);
                p = p.next;
            }
            System.out.println();
        }
    }

    /** Removes and returns the first item in the deque. Returns null if empty */
    public Item removeFirst() {
        if(isEmpty()) {
            return null;
        } else {
            Node first = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first.item;
        }
    }

    /** Removes and returns the last item in the deque. Returns null if empty */
    public Item removeLast() {
        if(isEmpty()) {
            return null;
        } else {
            Node last = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last.item;
        }
    }

    /** Returns the first item in the deque. Helper for testing */
    public Item getFirst() {
        return sentinel.next.item;
    }

    /** Returns the last item in the deque. Helper for testing */
    public Item getLast() {
        return sentinel.prev.item;
    }

    /** Returns the item at the given index */
    public Item get(int index) {
        if(isEmpty()) {
            return null;
        } else {
            int i = 0;
            for(Node p = sentinel.next; p != sentinel; p = p.next){
                if(i == index) {
                    return p.item;
                }
                i++;
            }
            return null;
        }
    }

    /** Helper function for getRecursive */
    private Item getRecursiveHelper(int targetIndex, int currIndex, Node p) {
        if (p.item == null) {
            return null;
        } else if (currIndex == targetIndex) {
            return p.item;
        }
        return getRecursiveHelper(targetIndex, currIndex + 1, p.next);
    }

    /** Returns the item at the given index, using recursion */
    public Item getRecursive(int index) {
        if(index >= size()) {
            return null;
        }
        return getRecursiveHelper(index, 0, sentinel.next);
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        LinkedListDeque D = new LinkedListDeque();
        D.addLast(1);
        D.addLast(2);
        D.addLast(3);
        D.addLast(4);
        D.addLast(5);
        //System.out.println("First removed is: " + D.removeFirst() + ", Last removed is: " + D.removeLast());
        System.out.println(D.getRecursive(6));
        D.printDeque();
    }
}
