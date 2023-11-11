package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;
public class TestMaxArrayDeque {
    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            int aLength = a.length();
            int bLength = b.length();

            for (int i = 0; i < Math.min(aLength, bLength); i++) {
                int aChar = a.charAt(i);
                int bChar = b.charAt(i);

                if (aChar != bChar) {
                    return aChar - bChar;
                }
            }

            if (aLength != bLength) {
                return aLength - bLength;
            }
            return 0;
        }
    }

    @Test
    /** Test creating a MaxArrayDeque with the given Comparator. */
    public void maxTest() {
        MaxArrayDeque<String> maxArrayDeque = new MaxArrayDeque<>(new StringLengthComparator());

        maxArrayDeque.addLast("this");
        maxArrayDeque.addLast("is");
        maxArrayDeque.addLast("a");
        maxArrayDeque.addLast("loooooong");
        maxArrayDeque.addLast("string");
        maxArrayDeque.addLast("x");

        assertEquals(maxArrayDeque.max(), "loooooong");
        assertEquals(maxArrayDeque.max(new StringComparator()), "x");
    }

    @Test
    /** Test creating a MaxArrayDeque with the given Comparator. */
    public void comparatorMaxTest() {
        MaxArrayDeque<String> maxArrayDeque = new MaxArrayDeque<>(new StringLengthComparator());

        maxArrayDeque.addLast("this");
        maxArrayDeque.addLast("is");
        maxArrayDeque.addLast("a");
        maxArrayDeque.addLast("loooooong");
        maxArrayDeque.addLast("string");

        assertEquals(maxArrayDeque.max(), maxArrayDeque.get(3));
    }
}
