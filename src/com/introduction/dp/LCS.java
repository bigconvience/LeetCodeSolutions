package com.introduction.dp;

import com.jbp.utils.MatrixUtils;

public class LCS {
    public static void main(String[] args) {
        new LCS().case1();
        new LCS().case2();
        new LCS().case3();
    }

    public void case1() {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        lscLength(X, Y);
    }

    public void case2() {
        String X = "10010101";
        String Y = "010110110";
        lscLength(X, Y);
    }

    public void case3() {
        String X = "567128";
        String Y = "125678";
        lscLength(X, Y);
    }

    public void lscLength(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] c = new int[m + 1][n + 1];
        char[][] b = new char[m + 1][n + 1];

        for (int i = 1; i < m; i++) {
            c[i][0] = 0;
        }

        for (int i = 1; i < n; i++) {
            c[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = '\\';
                } else if (c[i -1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i -1][j];
                    b[i][j] = '|';
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = '-';
                }
            }
        }

//        MatrixUtils.print(c);
        printLCS(b, X, m, n);
        System.out.println(" length: " + c[m][n]);
    }

    public void printLCS(char[][] b, String X, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j] == '\\') {
            printLCS(b, X, i - 1, j - 1);
            System.out.print("" + X.charAt(i - 1));
        } else if (b[i][j] == '|') {
            printLCS(b, X, i - 1, j);
        } else {
            printLCS(b, X, i, j - 1);
        }
    }

}
