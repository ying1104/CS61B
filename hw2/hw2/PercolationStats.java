package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] testResult;
    private int testTime;

    /** Performs T independent experiments on an N-by-N grid.*/
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        testResult = new double[T];
        testTime = T;
        for (int testNum = 0; testNum < T; testNum++) {
            Percolation test = pf.make(N);
            while (!test.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                test.open(row, col);
            }
            testResult[testNum] = (double) test.numberOfOpenSites() / (double) (N * N);
        }
    }

    /** Sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(testResult);
    }

    /** Sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(testResult);
    }

    /** low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(testTime);
    }

    /** high endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(testTime);
    }
}
