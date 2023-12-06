package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {
        private boolean[][] grid;
        private int numOpen;
        private WeightedQuickUnionUF allSites;
        private WeightedQuickUnionUF sitesConnectedToTop;
        private int topSitesIndex;
        private int bottomSitesIndex;

        public Percolation(int N) {             // create N-by-N grid, with all sites initially blocked
            if (N <= 0) {
                throw new java.lang.IllegalArgumentException();
            }
            numOpen = 0;
            grid = new boolean[N][N];
            allSites = new WeightedQuickUnionUF(N * N + 2);
            sitesConnectedToTop = new WeightedQuickUnionUF(N * N + 1);
            topSitesIndex = N * N;
            bottomSitesIndex = N * N + 1;
        }

        /** check for row/col out of bounds */
        private void checkInput(int row, int col) {
            if(row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
                throw new java.lang.IndexOutOfBoundsException();
            }
        }

        /** converts (row,col) pair to single int representation */
        private int rowColToInt(int row, int col) {
            return (row * grid.length) + col;
        }

        public void open(int row, int col) {      // open the site (row, col) if it is not open already

        }
        public boolean isOpen(int row, int col) {  // is the site (row, col) open?
            checkInput(row, col);
            return grid[row][col];
        }
        public boolean isFull(int row, int col) {  // is the site (row, col) full?
            return false;
        }
        public int numberOfOpenSites() {          // number of open sites
            return numOpen;
        }
        public boolean percolates() {             // does the system percolate?
            return false;
        }
        public static void main(String[] args) {  // use for unit testing (not required, but keep this here for the autograder)
        }
}
