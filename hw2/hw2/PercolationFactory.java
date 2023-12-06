package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationFactory {
    public Percolation make(int N) {
        return new Percolation(N);
    }
}
