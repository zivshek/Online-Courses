/* *****************************************************************************
 *  Name:              Hao Shi
 *  Coursera User ID:  ziv.hao.shek@gmail.com
 *  Last modified:     Jan 20, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[] sites;
    private final WeightedQuickUnionUF uf;
    private int openCount = 0;
    private final int top;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        // flatten the grid and use helper functions to get the correct index from row and col or vice versa
        int total = n * n;
        top = total;
        bottom = total + 1;
        sites = new boolean[total];
        uf = new WeightedQuickUnionUF(total + 2);
        for (int i = 0; i < n; i++) {
            uf.union(i, top);
        }
        for (int i = (n - 1) * n; i < total; i++) {
            uf.union(i, bottom);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBoundary(row, col);
        if (!isOpen(row, col)) {
            int index = getIndex(row, col);
            sites[index] = true;
            openCount++;

            int[] rows = { row - 1, row, row + 1, row }; // up, right, bottom, left
            int[] cols = { col, col + 1, col, col - 1 };
            for (int i = 0; i < rows.length; i++) {
                int r = rows[i];
                int c = cols[i];
                if (isValid(r, c)) {
                    int otherIndex = getIndex(r, c);
                    if (sites[otherIndex] && uf.find(index) != uf.find(otherIndex)) {
                        uf.union(index, otherIndex);
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBoundary(row, col);
        return sites[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBoundary(row, col);
        final int i = getIndex(row, col);
        return sites[i] && (uf.find(top) == uf.find(i));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1) return isOpen(1, 1);
        return uf.find(top) == uf.find(bottom);
    }

    private void checkBoundary(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && col >= 1 && row <= n && col <= n;
    }

    // row and col starts from 1, need to account for that
    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }
}
