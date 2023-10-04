package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  public static void testThreeAddThreeRemove(){
      AListNoResizing<Integer> goodAL = new AListNoResizing<>();
      BuggyAList<Integer> badAL = new BuggyAList<>();
      int i = 3;
      while(i <= 5){
          goodAL.addLast(i);
          badAL.addLast(i);
          i++;
      }
      while(goodAL.size() != 0 || badAL.size() != 0 && goodAL.size() == badAL.size()){
          assertEquals(goodAL.removeLast(), badAL.removeLast());
      }
  }

    public static void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyL = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyL.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                // size
                int size = L.size();
                //System.out.println("size: " + size);
            }
            else if (operationNumber == 2) {
                // getLast
                int size = L.size();
                int buggySize = buggyL.size();
                if(size > 0 && buggySize > 0) {
                    int last = L.getLast();
                    int buggyLast = buggyL.getLast();
                    //System.out.println("getLast last: " + last + ", getLast buggyLast: " + buggyLast);
                    assertEquals(last, buggyLast);
                }
            }
            else if (operationNumber == 3) {
                // removeLast
                int size = L.size();
                int buggySize = buggyL.size();
                if(size > 0 && buggySize > 0) {
                    int last = L.removeLast();
                    int buggyLast = buggyL.removeLast();
                    //System.out.println("removeLast last: " + last + ", removeLast buggyLast: " + buggyLast);
                    assertEquals(last, buggyLast);
                }
            }
        }
    }

  public static void main(String[] args){
      randomizedTest();
  }
}
