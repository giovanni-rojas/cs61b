package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class TestArrayDeque {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> arrayDeque = new ArrayDeque<String>();
        assertTrue("not empty", arrayDeque.isEmpty());
        arrayDeque.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        // assertEquals(1, arrayDeque.size());
        assertFalse("not 1 item", arrayDeque.isEmpty());

        arrayDeque.addLast("middle");
        assertEquals(2, arrayDeque.size());

        arrayDeque.addLast("back");
        assertEquals(3, arrayDeque.size());

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that arrayDeque is empty afterward. */
    public void addRemoveTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("not empty", arrayDeque.isEmpty());

        arrayDeque.addFirst(10);
        // should not be empty
        assertFalse("not 1 item", arrayDeque.isEmpty());

        arrayDeque.removeFirst();
        // should be empty
        assertTrue("not empty", arrayDeque.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(3);

        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();

        int size = arrayDeque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  arrayDeque1 = new ArrayDeque<String>();
        ArrayDeque<Double>  arrayDeque2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> arrayDeque3 = new ArrayDeque<Boolean>();

        arrayDeque1.addFirst("string");
        arrayDeque2.addFirst(3.14159);
        arrayDeque3.addFirst(true);

        String s = arrayDeque1.removeFirst();
        double d = arrayDeque2.removeFirst();
        boolean b = arrayDeque3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("not null,", null, arrayDeque.removeFirst());
        assertEquals("not null,", null, arrayDeque.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            arrayDeque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("not same", i, (double) arrayDeque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("not same", i, (double) arrayDeque.removeLast(), 0.0);
        }

    }

    @Test
    //Randomized test of n = 1000
    public void randomizedTest() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        ArrayDeque<Integer> B = new ArrayDeque<>();
        ArrayDeque<Integer> C = new ArrayDeque<>();
        C.addLast(999);

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 8);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                A.addFirst(randVal);
                B.addFirst(randVal);
                //System.out.println("AD addFirst(" + randVal + ") , AD_Copy addFirst(" + randVal + ")");
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
                //System.out.println("AD addLast(" + randVal + "), AD_Copy addLast(" + randVal + ")");
            } else if (operationNumber == 2) {
                // size
                int size = A.size();
                int sizeCopy = B.size();
                //System.out.println("AD size: " + size + ", AD_Copy size: " + sizeCopy);
            } else if (operationNumber == 3) {
                // removeFirst
                if (A.isEmpty()) {
                    System.out.println("Empty deque removeFirst");
                } else {
                    int firstAD = A.removeFirst();
                    int firstCopy = B.removeFirst();
                    //System.out.println("AD removeFirst(): " + firstAD + ", AD_Copy removeFirst(): " + firstCopy);
                }
            } else if (operationNumber == 4) {
                // removeLast
                if (A.isEmpty()) {
                    System.out.println("Empty deque removeLast");
                } else {
                    int lastAD = A.removeLast();
                    int lastCopy = B.removeLast();
                    //System.out.println("AD removeLast(): " + lastAD + ", AD_Copy removeLast(): " + lastCopy);
                }
            } else if (operationNumber == 5) {
                // printDeque
                A.printDeque();
                B.printDeque();
            } else if (operationNumber == 6) {
                // get
                if (A.isEmpty()) {
                    System.out.println("Empty deque get");
                } else {
                    int randVal = StdRandom.uniform(0, A.size());
                    //System.out.println("AD get(" + randVal + "): " + A.get(randVal) + ", AD_Copy get(" + randVal + "): " + B.get(randVal));
                }
            } else if (operationNumber == 7) {
                // equals
                if (A.isEmpty()) {
                    System.out.println("Empty deque equals");
                } else {
                    boolean copyEquals = A.equals(B);
                    boolean simpleEquals = A.equals(C);
                    assertTrue(copyEquals);
                    assertFalse(simpleEquals);
                }
            }
        }
    }
}
