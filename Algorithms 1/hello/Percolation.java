/* *****************************************************************************
 *  Name:              Hao Shi
 *  Coursera User ID:  ziv.hao.shek@gmail.com
 *  Last modified:     Jan 18, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private class Node {
        private int value;
        private boolean isOpen = false;

        public Node(int value) {
            this.setValue(value);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }
    }

    private int n;
    private Node[] sites;
    private int openCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        // flatten the grid and use helper functions to get the correct index from row and col or vice versa
        int total = n * n;
        sites = new Node[total];
        for (int i = 0; i < total; i++) {
            sites[i] = new Node(i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBoundary(row, col);
        if (!isOpen(row, col)) {
            int index = getIndex(row, col);
            getNode(index).setOpen(true);
            openCount++;

            int[] rows = new int[] { row - 1, row, row + 1, row }; // up, right, bottom, left
            int[] cols = new int[] { col, col + 1, col, col - 1 };
            for (int i = 0; i < 4; i++) {
                int r = rows[i];
                int c = cols[i];
                if (isValid(r, c)) {
                    int otherIndex = getIndex(r, c);
                    if (getNode(otherIndex).isOpen() && !connected(index, otherIndex)) {
                        union(index, otherIndex);
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBoundary(row, col);
        return getNode(row, col).isOpen();
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBoundary(row, col);
        return !getNode(row, col).isOpen();
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getNode(i).isOpen() && getNode(n, j + 1).isOpen() && connected(i, getIndex(n, j
                        + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkBoundary(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        getNode(i).setValue(j);
    }

    private boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int i) {
        while (i != getNode(i).getValue()) {
            i = getNode(i).getValue();
        }
        return i;
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && col >= 1 && row <= n && col <= n;
    }

    private Node getNode(int index) {
        return sites[index];
    }

    private Node getNode(int row, int col) {
        return getNode(getIndex(row, col));
    }

    // row and col starts from 1, need to account for that
    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    public static void main(String[] args) {
        int n = 100;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int randomRow = StdRandom.uniform(1, n + 1);
            int randomCol = StdRandom.uniform(1, n + 1);
            percolation.open(randomRow, randomCol);
        }
        System.out.println(percolation.numberOfOpenSites() / (double) (n * n));
    }
}
