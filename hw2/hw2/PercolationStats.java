package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] threshold;
    public PercolationStats(int N, int T, PercolationFactory pf) {   // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        threshold = new double[T];
        for(int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }

            threshold[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }
    public double mean() {                                          // sample mean of percolation threshold
        return StdStats.mean(threshold);
    }
    public double stddev() {                                         // sample standard deviation of percolation threshold
        return StdStats.stddev(threshold);
    }
    public double confidenceLow() {                                 // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }
    public double confidenceHigh() {                                // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }
}