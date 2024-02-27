package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.Optional;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        String methodCalls = "";

        for (int i = 0; i < 50; i += 1) {
            int methodCase = StdRandom.uniform(4);
            switch (methodCase) {
                case 0:
                    methodCalls += "addFirst(" + i + ")" + '\n';
                    sad.addFirst(i);
                    assertEquals(methodCalls, (Integer) sad.get(0), (Integer) i);
                    break;
                case 1:
                    methodCalls += "addLast(" + i + ")" + '\n';
                    sad.addLast(i);
                    assertEquals(methodCalls, (Integer) sad.get(sad.size() - 1), (Integer) i);
                    break;
                case 2:
                    if (sad.isEmpty()) {
                        break;
                    }
                    methodCalls += "removeFirst()" + '\n';
                    Integer first = sad.get(0);
                    Integer removedFirst = sad.removeFirst();
                    assertEquals(methodCalls, first, removedFirst);
                    break;
                case 3:
                    if (sad.isEmpty()) {
                        break;
                    }
                    methodCalls += "removeLast()" + '\n';
                    Integer last = sad.get(sad.size() - 1);
                    Integer removedLast = sad.removeLast();
                    assertEquals(methodCalls, last, removedLast);
                    break;
            }
        }
    }
}

