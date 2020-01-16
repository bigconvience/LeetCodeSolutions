package com.glorist.backtrace;

public class Sudoku {
    static final int DIMENSION = 9;
    static final int NCELLS = DIMENSION * DIMENSION;

    class point {
        int x, y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class boardtype {
        int[][] m = new int[DIMENSION + 1][DIMENSION + 1];
        int freecount;
        point[] move = new point[NCELLS + 1];

        public boardtype(int[][] m, int freecount, point[] move) {
            this.m = m;
            this.freecount = freecount;
            this.move = move;
        }
    }

    class ncandidates {
        int n;

        public ncandidates(int n) {
            this.n = n;
        }
    }

    private void construct_candidates(int a[], int k, boardtype boardtype, int c[],
                                      ncandidates ncandidates) {
        int x, y;
        
    }
}
