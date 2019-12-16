package com.ms.dp;

import com.jbp.utils.ArrayUtils;
/**
 * https://blog.csdn.net/weixin_30636089/article/details/97844051
 */
public class LongestAscend {
    public static void main(String[] args) {
        new LongestAscend().case1();
        new LongestAscend().case2();
    }

    public void case1() {
        int[] s = {5, 6, 7, 1, 2, 8};
        System.out.println(getLongestAscend(s));
    }

    public void case2() {
        int[] s = {2,3,4,9,2,7,10,3,8};
        System.out.println(getLongestAscend(s));
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
     * @param attr
     * @param dp
     * @param len
     * @param index
     */
    public void printLIS(int[] attr, int[] dp, int len, int index) {
        int[] result = new int[len];
        result[--len] = attr[index];

        for (int i = index; i >= 0; i--) {
            if (attr[i] < attr[index] && dp[i] + 1 == dp[index]){
                result[--len] = attr[i];
                index = i;
            }
        }
        ArrayUtils.print(result);
    }


}
