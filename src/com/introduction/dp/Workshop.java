package com.introduction.dp;

/**
 * 算法导论 车间装配
 */
public class Workshop {
    public static void main(String[] args) {
        new Workshop().case1();
    }

    public void case1() {
        int[] e = {2, 4};
        int[] x = {3, 2};
        int[][] a = {{7, 9, 3, 4, 8, 4},
                {8, 5, 6, 4, 5, 7}};
        int[][] t = {{2, 3, 1, 3, 4},
                {2, 1, 2, 2, 1}};

        fastestWay(a, t, e, x, a[0].length);
    }

    public void fastestWay(int[][] a, int[][] t, int[] e, int[] x, int n) {
        int[][] f = new int[2][n];
        int[][] l = new int[2][n];

        f[0][0] = e[0] + a[0][0];
        f[1][0] = e[1] + a[1][0];
        for (int i = 1; i < n; i++) {
            int firstFirst = f[0][i - 1] + a[0][i];
            int secondFirst = f[1][i - 1] + t[1][i - 1] + a[0][i];
            if (firstFirst <= secondFirst) {
                f[0][i] = firstFirst;
                l[0][i] = 1;
            } else {
                f[0][i] = secondFirst;
                l[0][i] = 2;
            }

            int secondSecond = f[1][i - 1] + a[1][i];
            int firstSecond = f[0][i - 1] + t[0][i - 1] + a[1][i];
            if (secondSecond <= firstSecond) {
                f[1][i] = secondSecond;
                l[1][i] = 2;
            } else {
                f[1][i] = firstSecond;
                l[1][i] = 1;
            }
        }

        int firstMin = f[0][n-1] + x[0];
        int secondMin = f[1][n-1] + x[1];
        int fMin;
        int lMin;
        if (firstMin <= secondMin) {
            fMin = firstMin;
            lMin = 1;
        } else {
            fMin = secondMin;
            lMin = 2;
        }

        System.out.println("fMin: " + fMin + " lMin: " + lMin);

        printStations(l, lMin, n);
    }

    public void printStations(int[][] l, int lMin, int n) {
        System.out.println("line:" + lMin + " station:" + n);

        for (int i = n - 1; i > 0; i--) {
            lMin = l[lMin - 1][i];
            System.out.println("line:" + lMin + " station:" + i);
        }
    }
}
