package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayDeque;
import java.util.Optional;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();
        String methodCalls = "";

        for (int i = 0; i < 100; i += 1) {
            int methodCase = StdRandom.uniform(4);
            switch (methodCase) {
                case 0:
                    methodCalls += "addFirst(" + i + ")" + '\n';
                    sad.addFirst(i);
                    ad.addFirst(i);
                    assertEquals(methodCalls, (Integer) sad.get(0), (Integer) ad.get(0));
                    break;
                case 1:
                    methodCalls += "addLast(" + i + ")" + '\n';
                    sad.addLast(i);
                    ad.addLast(i);
                    assertEquals(methodCalls, (Integer) sad.get(sad.size() - 1), (Integer) ad.get(sad.size() - 1));
                    break;
                case 2:
                    if (sad.isEmpty()) {
                        break;
                    }
                    methodCalls += "removeFirst()" + '\n';
                    Integer sadFirst = sad.removeFirst();
                    Integer adFirst = ad.removeFirst();
                    assertEquals(methodCalls, sadFirst, adFirst);
                    break;
                case 3:
                    if (sad.isEmpty()) {
                        break;
                    }
                    methodCalls += "removeLast()" + '\n';
                    Integer sadLast = sad.removeLast();
                    Integer adLast = ad.removeLast();
                    assertEquals(methodCalls, sadLast, adLast);
                    break;
            }
            methodCalls += "size()" + '\n';
            assertEquals(methodCalls, sad.size(), ad.size());
        }
    }
}

