package com.glorist.backtrace;

import com.introduction.dp.Matrix;

public class Backtrace {


    class data {
        int n;

        public data(int n) {
            this.n = n;
        }
    }

    class ncandidats {
        int n;

        public ncandidats(int n) {
            this.n = n;
        }
    }

    public static void main(String[] args) {
        new Backtrace().generate_subsets(3);
    }

    private static final int NMAX = 100;
    private static final int MAXCANDIDATES = 100;
    boolean finished = false;                  /* found all solutions yet? */

    private void generate_subsets(int n) {
        int[] a = new int[NMAX];
        backtrack(a, 0, new data(n));
    }

    private void backtrack(int a[], int k, data input) {
        int[] c = new int[MAXCANDIDATES];           /* candidates for next position */
        ncandidats ncandidates = new ncandidats(0);                /* next position candidate count */
        int i;                          /* counter */

        if (is_a_solution(a, k, input))
            process_solution(a, k, input);
        else {
            k = k + 1;
            construct_candidates(a, k, input, c, ncandidates);
            for (i = 0; i < ncandidates.n; i++) {
                a[k] = c[i];
                make_move(a, k, input);

                backtrack(a, k, input);
                if (finished) return;    /* terminate early */

                unmake_move(a, k, input);
            }
        }
    }

    private boolean is_a_solution(int a[], int k, data input) {
        return k == input.n;
    }

    private void process_solution(int a[], int k, data input) {
        System.out.print("{");
        for (int i = 1; i <= k; i++) {
            if (a[i] == 1) {
                System.out.print(i + " ");
            }
        }
        System.out.println("}");
    }

    private void construct_candidates(int a[], int k, data input, int[] c, ncandidats ncandidates) {
        c[0] = 1;
        c[1] = 0;
        ncandidates.n = 2;
    }

    private void make_move(int a[], int k, data input) {

    }

    private void unmake_move(int a[], int k, data input) {

    }


}
