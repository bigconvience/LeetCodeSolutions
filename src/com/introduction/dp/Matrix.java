package com.introduction.dp;

import com.jbp.utils.MatrixUtils;

public class Matrix {
    public static void main(String[] args) {
        new Matrix().case1();
        new Matrix().case2();
    }

    public void case1() {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p);
    }

    public void case2() {
        int[] p = {5, 10, 3, 12, 5, 50, 6};
        matrixChainOrder(p);
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
                    int q = m[i][k] + m[k+1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        MatrixUtils.print(m);
        MatrixUtils.print(s);

        printOptimalParens(s, 1, n);
    }

    public void printOptimalParens(int[][]s, int i, int j) {
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
