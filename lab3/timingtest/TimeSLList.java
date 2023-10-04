package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> NList = new AList<>();
        AList<Double> timesAL = new AList<>();
        AList<Integer> opsAL = new AList<>();

        for(int N = 1000; N <= 128000; N *= 2){
            SLList<Integer> SLL = new SLList<>();

            for(int i = 0; i < N; i++){
                SLL.addLast(i);
            }
            int ops;
            int M = 10000;
            Stopwatch sw = new Stopwatch();
            for(ops = 0; ops < 10000; ops++) {
                SLL.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            NList.addLast(N);
            timesAL.addLast(timeInSeconds);
            opsAL.addLast(ops);
        }
        printTimingTable(NList, timesAL, opsAL);
    }
}
