/* *****************************************************************************
 *  Name:              Hao Shi
 *  Coursera User ID:  ziv.hao.shek@gmail.com
 *  Last modified:     Jan 18, 2022
 **************************************************************************** */

public class Percolation {

    private class Node {
        public int value;
        public boolean isOpen = false;

        public Node(int value) {
            this.value = value;
        }
    }

    private int n;
    private int total;
    private Node[] sites;
    private int openCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        // flatten the grid and use helper functions to get the correct index from row and col or vice versa
        total = n * n;
        sites = new Node[total];
        for (int i = 0; i < total; i++) {
            sites[i] = new Node(i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBoundary(row, col);
        if (!isOpen(row, col)) {
            sites[getIndex(row, col)].isOpen = true;
            openCount++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBoundary(row, col);
        return sites[getIndex(row, col)].isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBoundary(row, col);
        return !sites[getIndex(row, col)].isOpen;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    private void checkBoundary(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // row and col starts from 1, need to account for that
    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private int getRow(int index) {
        return (index / n) + 1;
    }

    private int getCol(int index) {
        return (index % n) + 1;
    }

    public static void main(String[] args) {

    }
}
