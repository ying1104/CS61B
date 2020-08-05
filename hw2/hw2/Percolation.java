package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numOfOpenSites;
    private int[][] grid;
    private int gridSize;
    private WeightedQuickUnionUF checkPercolation;
    private WeightedQuickUnionUF checkFull;

    /** Creates N-by-N grid, with all sites initially blocked. */
    public Percolation(int N){
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        numOfOpenSites = 0;
        grid = new int[N][N];
        gridSize = N;
        checkPercolation = new WeightedQuickUnionUF(N * N + 2);
        checkFull = new WeightedQuickUnionUF(N * N + 1);
    }

    /** Convert row and col to 1D. */
    private int xyTo1D(int row, int col) {
        return row * gridSize + col + 1;
    }

    /** Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) throws IndexOutOfBoundsException {
        if (grid[row][col] != 1) {
            grid[row][col] = 1;
            numOfOpenSites++;
            if (row == 0) {
                checkPercolation.union(0, xyTo1D(row, col));
                checkFull.union(0, xyTo1D(row, col));
            }
            if (row == gridSize - 1) {
                checkPercolation.union(xyTo1D(row, col), gridSize * gridSize + 1);
            }
            if (row > 0 && isOpen(row - 1, col)) {
                checkPercolation.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                checkFull.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (row < gridSize - 1 && isOpen(row + 1, col)) {
                checkPercolation.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                checkFull.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                checkPercolation.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                checkFull.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (col < gridSize - 1 && isOpen(row, col + 1)) {
                checkPercolation.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                checkFull.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException {
        return grid[row][col] == 1;
    }

    /** Is the site (row, col) full? */
    public boolean isFull(int row, int col) throws IndexOutOfBoundsException {
        return checkFull.connected(0, xyTo1D(row, col));
    }

    /** Number of open sites. */
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    /** Does the system percolate? */
    public boolean percolates() {
        return checkPercolation.connected(0, gridSize * gridSize + 1);
    }

    /** Use for unit testing (not required). */
    public static void main(String[] args) {
        Percolation testPerco = new Percolation(-10);
        System.out.println(testPerco.grid[5][5]);
    }

}
