package com.ms.dp;

import com.jbp.utils.ArrayUtils;

/**
 * https://blog.csdn.net/weixin_30636089/article/details/97844051
 */
public class LIS {
    public static void main(String[] args) {
        new LIS().case1();
        new LIS().case2();
        new LIS().case3();
        new LIS().case4();
    }

    public void case1() {
        int[] s = {5, 6, 7, 1, 2, 8};
        System.out.println(getLongestAscend(s));
    }

    public void case2() {
        int[] s = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(getLongestAscend(s));
    }


    public void case3() {
        int[] s = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        lisNLogn(s);
    }

    public void case4() {
        int[] s = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        lisNLogn(s);
    }

    /**
     * dp维护好
     * @param s
     */
    public void lisNLogn(int[] s) {
        int n = s.length;
        int[] b = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < s.length; i++) {
            dp[i] = 1;
        }
        b[0] = s[0];
        int len = 1;

        int index = 0;
        for (int i = 1; i < n; i++) {
            if (s[i] > b[len - 1]) {
                b[len++] = s[i];
                dp[i] = len;
                index = i;
            } else {
                int pos = binarySearch(b, len, s[i]);
                b[pos] = s[i];
            }
        }
        System.out.println("Len: " + len);
        ArrayUtils.print(b);

        printLIS(s, dp, len, index);
    }

    public int binarySearch(int[] b, int len, int s) {
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (b[mid] > s) {
                right = mid - 1;
            } else if (b[mid] < s) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public int getLongestAscend(int[] s) {
        int n = s.length;
        int[] l = new int[n];
        for (int i = 0; i < s.length; i++) {
            l[i] = 1;
        }
        int maxLength = 1;
        int maxIndex = 0;

        for (int j = 1; j < s.length; j++) {
            for (int i = 0; i < j; i++) {
                if (s[i] < s[j] && l[j] < l[i] + 1) {
                    l[j] = l[i] + 1;
                    maxLength = Math.max(maxLength, l[j]);
                    maxIndex = j;
                }
            }
        }

        printLIS(s, l, maxLength, maxIndex);

        return maxLength;
    }

    /**
     * https://blog.csdn.net/s448312891/article/details/80318746
     *
     * @param attr
     * @param dp
     * @param len
     * @param index
     */
    public void printLIS(int[] attr, int[] dp, int len, int index) {
        int[] result = new int[len];
        result[--len] = attr[index];

        for (int i = index; i >= 0; i--) {
            if (attr[i] < attr[index] && dp[i] + 1 == dp[index]) {
                result[--len] = attr[i];
                index = i;
            }
        }
        ArrayUtils.print(result);
    }


}
