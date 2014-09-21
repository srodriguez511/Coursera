package com.algorithms.unionfind;

public class Percolation {
    private boolean[][] array;
    private int top = 0;
    private int bottom;
    private int length;
    private WeightedQuickUnionUF QUW1;
    private WeightedQuickUnionUF QUW2;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        array = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = false;
            }
        }

        length = N;
        // Last index
        bottom = length * length + 1;
        // add index entry for top and bottom
        QUW1 = new WeightedQuickUnionUF(N * N + 2);
        // add index entry for top only
        QUW2 = new WeightedQuickUnionUF(N * N + 1);
    }

    private int getIndex(int i, int j) {
        return length * (i - 1) + j;
    }

    public void open(int i, int j) {
        if (i < 1 || j < 1 || i > length || j > length) {
            throw new IndexOutOfBoundsException();
        }

        array[i - 1][j - 1] = true;

        if (i == 1) {
            QUW1.union(getIndex(i, j), top);
            QUW2.union(getIndex(i, j), top);
        }
        
        if (i == length) {
            QUW1.union(getIndex(i, j), bottom);
        }

        // Need to connect the top,right,left,bot;
        // Top
        if (i > 1 && isOpen(i - 1, j)) {
            QUW1.union(getIndex(i, j), getIndex(i - 1, j));
            QUW2.union(getIndex(i, j), getIndex(i - 1, j));
        }
        // Bot
        if (i < length && isOpen(i + 1, j)) {
            QUW1.union(getIndex(i, j), getIndex(i + 1, j));
            QUW2.union(getIndex(i, j), getIndex(i + 1, j));
        }
        // Left
        if (j > 1 && isOpen(i, j - 1)) {
            QUW1.union(getIndex(i, j), getIndex(i, j - 1));
            QUW2.union(getIndex(i, j), getIndex(i, j - 1));
        }
        // Right
        if (j < length && isOpen(i, j + 1)) {
            QUW1.union(getIndex(i, j), getIndex(i, j + 1));
            QUW2.union(getIndex(i, j), getIndex(i, j + 1));
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i < 1 || j < 1 || i > length || j > length) {
            throw new IndexOutOfBoundsException();
        }

        return array[i - 1][j - 1];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (i < 1 || j < 1 || i > length || j > length) {
            throw new IndexOutOfBoundsException();
        }

        return isOpen(i, j) && QUW2.connected(top, getIndex(i, j));
    }

    // does the system percolate?
    public boolean percolates() {
        return QUW1.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);

        p.open(1, 1);
        System.out.println(p.percolates());

        p.open(2, 1);
        System.out.println(p.percolates());

        p.open(3, 1);
        System.out.println(p.percolates());

        p.open(4, 1);
        System.out.println(p.percolates());
    }
}
