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

        public void connectToNeighbors(int row, int col) {
            if (row - 1 >= 0 && isOpen(row - 1, col)) { // neighbor above
                allSites.union(rowColToInt(row, col), rowColToInt(row - 1, col));
                sitesConnectedToTop.union(rowColToInt(row, col), rowColToInt(row - 1, col));
            }
            if (row + 1 < grid.length && isOpen(row + 1, col)) { // neighbor below
                allSites.union(rowColToInt(row, col), rowColToInt(row + 1, col));
                sitesConnectedToTop.union(rowColToInt(row, col), rowColToInt(row + 1, col));
            }
            if (col - 1 >= 0 && isOpen(row, col - 1)) { // neighbor left
                allSites.union(rowColToInt(row, col), rowColToInt(row, col - 1));
                sitesConnectedToTop.union(rowColToInt(row, col), rowColToInt(row, col - 1));
            }
            if (col + 1 < grid.length && isOpen(row, col + 1)) { // neighbor right
                allSites.union(rowColToInt(row, col), rowColToInt(row, col + 1));
                sitesConnectedToTop.union(rowColToInt(row, col), rowColToInt(row, col + 1));
            }
        }

        public void open(int row, int col) {      // open the site (row, col) if it is not open already
            if (!isOpen(row, col)) {
                grid[row][col] = true;
                numOpen++;
                connectToNeighbors(row, col); //union with neighbors
                if (row == 0) { //connect to open-top of grid and to
                    allSites.union(topSitesIndex, rowColToInt(row, col));
                    sitesConnectedToTop.union(topSitesIndex, rowColToInt(row, col));
                }
                if (row == grid.length - 1) { //connect to bottom of grid
                    allSites.union(bottomSitesIndex, rowColToInt(row, col));
                }
            }
        }
        public boolean isOpen(int row, int col) {  // is the site (row, col) open?
            checkInput(row, col);
            return grid[row][col];
        }
        public boolean isFull(int row, int col) {  // is the site (row, col) full?
            checkInput(row, col);
            return sitesConnectedToTop.connected(topSitesIndex, rowColToInt(row, col));
        }
        public int numberOfOpenSites() {          // number of open sites
            return numOpen;
        }
        public boolean percolates() {             // does the system percolate?
            return allSites.connected(topSitesIndex, bottomSitesIndex);
        }
        public static void main(String[] args) {  // use for unit testing (not required, but keep this here for the autograder)
        }
}
