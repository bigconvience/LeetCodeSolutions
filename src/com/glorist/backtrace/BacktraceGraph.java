package com.glorist.backtrace;

public class BacktraceGraph {


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

    class edgenode {
        int y;
        edgenode next;

        public edgenode(int val, edgenode next) {
            this.y = val;
            this.next = next;
        }
    }

    class graph {
        edgenode[] edges;

        public graph(edgenode[] edges) {
            this.edges = edges;
        }
    }

    public static void main(String[] args) {
        new BacktraceGraph().generate_subsets(3);
    }

    private static final int NMAX = 100;
    private static final int MAXCANDIDATES = 100;
    private boolean finished = false;                  /* found all solutions yet? */
    private int solution_count = 0;
    private graph g;

    private void generate_subsets(int n) {
        int[] a = new int[NMAX];
        backtrack(a, 0, new data(n));
    }

    private void backtrack(int a[], int k, data input) {
        int[] c = new int[MAXCANDIDATES];           /* candidates for next position */
        ncandidats ncandidates = new ncandidats(0);                /* next position candidate count */
        int i;                          /* counter */

        if (is_a_solution(a, k, input.n))
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

    private boolean is_a_solution(int a[], int k, int t) {
        return a[k] == t;
    }

    private void process_solution(int a[], int k, data input) {
        solution_count++;
    }

    private void construct_candidates(int a[], int k, data input, int[] c, ncandidats ncandidates) {
        boolean[] in_sol = new boolean[NMAX];
        for (int i = 0; i < k; i++) {
            in_sol[a[i]] = true;
        }
        int last;
        edgenode p;

        if (k == 1) {
            c[0] = 1;
            ncandidates.n = 1;
        } else {
            ncandidates.n = 0;
            last = a[k - 1];
            p = g.edges[last];
            while (p != null) {
                if (!in_sol[p.y]) {
                    c[ncandidates.n++] = p.y;
                }
                p = p.next;
            }
        }
    }

    private void make_move(int a[], int k, data input) {

    }

    private void unmake_move(int a[], int k, data input) {

    }


}
