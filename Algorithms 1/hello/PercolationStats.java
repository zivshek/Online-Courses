/* *****************************************************************************
 *  Name:              Hao Shi
 *  Coursera User ID:  ziv.hao.shek@gmail.com
 *  Last modified:     Jan 20, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final double mean;
    private final double stddev;
    private final double confLo;
    private final double confHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        double[] results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomCol = StdRandom.uniform(1, n + 1);
                percolation.open(randomRow, randomCol);
            }
            results[i] = percolation.numberOfOpenSites() / (double) (n * n);
        }

        // calculate here once to avoid repeated computations
        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
        double sqrtT = Math.sqrt(trials);
        this.confLo = this.mean - (CONFIDENCE * this.stddev / sqrtT);
        this.confHi = this.mean + (CONFIDENCE * this.stddev / sqrtT);
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }
}
