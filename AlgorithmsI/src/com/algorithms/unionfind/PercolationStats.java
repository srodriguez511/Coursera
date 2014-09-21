package com.algorithms.unionfind;

import com.algorithms.std.StdIn;
import com.algorithms.std.StdOut;
import com.algorithms.std.StdRandom;
import com.algorithms.std.StdStats;

public class PercolationStats {
    private double[] resultFractions;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        resultFractions = new double[T];

        for (int count = 0; count < T; count++) {
            Percolation percolation = new Percolation(N);

            int sites = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);

                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    sites++;
                }

                resultFractions[count] = (double) sites / (N * N);
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(resultFractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(resultFractions);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / (Math.sqrt(resultFractions.length)));
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / (Math.sqrt(resultFractions.length)));
    }

    // test client, described below
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats stats = new PercolationStats(N, T);

        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo()
                + ", " + stats.confidenceHi());
    }
}
