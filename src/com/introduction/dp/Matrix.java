package com.introduction.dp;

import com.jbp.utils.MatrixUtils;

public class Matrix {
    public static void main(String[] args) {
        new Matrix().case4();
        new Matrix().case2();
        new Matrix().case3();
    }

    public void case1() {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p);
    }

    public void case2() {
        int[] p = {5, 10, 3, 12, 5, 50, 6};
        matrixChainOrder(p);
    }

    public void case3() {
        int[] p = {5, 10, 3, 12, 5, 50, 6};
        recursiveMatrixChainOrder(p);
    }

    public void case4() {
        int[] p = {5, 10, 3, 12, 5, 50, 6};
        memorizedMatrixChain(p);
    }

    public void memorizedMatrixChain(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        lookupChain(p, 1, n, m, s);

        printOptimalParens(s, 1, n);
        System.out.println("");
    }

    public int lookupChain(int[] p, int i, int j, int[][] m, int[][] s) {
        if (m[i][j] < Integer.MAX_VALUE) {
            return m[i][j];
        }

        if (i == j) {
            m[i][j] = 0;
            return 0;
        }

        for (int k = i; k < j; k++) {
            int q = lookupChain(p, i, k, m, s) + lookupChain(p, k + 1, j, m, s) + p[i - 1] * p[k] * p[j];
            if (q < m[i][j]) {
                m[i][j] = q;
                s[i][j] = k;
            }
        }

        return m[i][j];
    }


    public void recursiveMatrixChainOrder(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        recursiveMatrixChain(p, 1, n, m, s);

        printOptimalParens(s, 1, n);
        System.out.println("");
    }

    public int recursiveMatrixChain(int[] p, int i, int j, int[][] m, int[][] s) {
        if (i == j) {
            return 0;
        }

        m[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int q = recursiveMatrixChain(p, i, k, m, s) +
                    recursiveMatrixChain(p, k + 1, j, m, s) + p[i - 1] * p[k] * p[j];
            if (q < m[i][j]) {
                m[i][j] = q;
                s[i][j] = k;
            }
        }
        return m[i][j];
    }

    public void matrixChainOrder(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
//        MatrixUtils.print(m);
//        MatrixUtils.print(s);

        printOptimalParens(s, 1, n);
        System.out.println("");
    }

    public void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
