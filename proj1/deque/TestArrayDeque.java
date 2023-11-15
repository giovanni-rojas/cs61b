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
        assertTrue("A newly initialized ArrayDeque should be empty", arrayDeque.isEmpty());
		arrayDeque.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        // assertEquals(1, arrayDeque.size());
        assertFalse("arrayDeque should now contain 1 item", arrayDeque.isEmpty());

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
		assertTrue("arrayDeque should be empty upon initialization", arrayDeque.isEmpty());

		arrayDeque.addFirst(10);
		// should not be empty
		assertFalse("arrayDeque should contain 1 item", arrayDeque.isEmpty());

		arrayDeque.removeFirst();
		// should be empty
		assertTrue("arrayDeque should be empty after removal", arrayDeque.isEmpty());
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
        assertEquals("Should return null on an empty Deque,", null, arrayDeque.removeFirst());
        assertEquals("Should return null on an empty Deque,", null, arrayDeque.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            arrayDeque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeLast(), 0.0);
        }

    }

    @Test
    //Randomized test of n = 1000
    public void randomizedTest() {
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        ArrayDeque<Integer> AD_Copy = new ArrayDeque<>();
        ArrayDeque<Integer> AD_Simple = new ArrayDeque<>();
        AD_Simple.addLast(999);

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 8);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                AD.addFirst(randVal);
                AD_Copy.addFirst(randVal);
                System.out.println("AD addFirst(" + randVal + ") , AD_Copy addFirst(" + randVal + ")");
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                AD.addLast(randVal);
                AD_Copy.addLast(randVal);
                System.out.println("AD addLast(" + randVal + "), AD_Copy addLast(" + randVal + ")");
            } else if (operationNumber == 2) {
                // size
                int size = AD.size();
                int sizeCopy = AD_Copy.size();
                System.out.println("AD size: " + size + ", AD_Copy size: " + sizeCopy);
            } else if (operationNumber == 3) {
                // removeFirst
                if(AD.isEmpty()) {
                    System.out.println("Empty deque removeFirst");
                } else {
                    int firstAD = AD.removeFirst();
                    int firstCopy = AD_Copy.removeFirst();
                    System.out.println("AD removeFirst(): " + firstAD + ", AD_Copy removeFirst(): " + firstCopy);
                }
            } else if (operationNumber == 4) {
                // removeLast
                if(AD.isEmpty()) {
                    System.out.println("Empty deque removeLast");
                } else {
                    int lastAD = AD.removeLast();
                    int lastCopy = AD_Copy.removeLast();
                    System.out.println("AD removeLast(): " + lastAD + ", AD_Copy removeLast(): " + lastCopy);
                }
            } else if (operationNumber == 5) {
                // printDeque
                AD.printDeque();
                AD_Copy.printDeque();
            } else if (operationNumber == 6) {
                // get
                if(AD.isEmpty()) {
                    System.out.println("Empty deque get");
                } else {
                    int randVal = StdRandom.uniform(0, AD.size());
                    System.out.println("AD get(" + randVal + "): " + AD.get(randVal) + ", AD_Copy get(" + randVal + "): " + AD_Copy.get(randVal));
                }
            } else if (operationNumber == 7) {
                // equals
                if(AD.isEmpty()) {
                    System.out.println("Empty deque equals");
                } else {
                    boolean copyEquals = AD.equals(AD_Copy);
                    boolean simpleEquals = AD.equals(AD_Simple);
                    assertTrue(copyEquals);
                    assertFalse(simpleEquals);
                }
            }
        }
    }
}
