package com.leetcode.dp;

/**
 * https://leetcode.wang/leetCode-72-Edit-Distance.html
 */
public class EditDistance {
    public static void main(String[] args) {
        new EditDistance().case1();
        new EditDistance().case2();
    }

    public void case1() {
       String word1 = "horse";
       String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }

    public void case2() {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance(word1, word2));
        System.out.println(minDistance_recur(word1, word2));
        System.out.println(minDistance_recur1(word1, word2));
    }

    public int minDistance_recur1(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();

        if (n1 == 0) {
            return n2;
        }
        if (n2 == 0) {
            return n1;
        }

        if (word1.charAt(n1 - 1) == word2.charAt(n2 - 1)) {
            return minDistance_recur1(word1.substring(0, n1 - 1), word2.substring(0, n2 - 1));
        }

        return Math.min(minDistance_recur1(word1.substring(0, n1 - 1), word2.substring(0, n2 -1)),
                Math.min(minDistance_recur1(word1, word2.substring(0, n2 - 1)),
                minDistance_recur1(word1.substring(0, n1 -1), word2))) + 1;
    }

    public int minDistance_recur(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();

        int[] dp = new int[n2 + 1];
        for (int i = 1; i <= n2; i++) {
            dp[i] = i;
        }

        int tmp;
        int prev;
        for (int i = 1; i <= n1; i++) {
            tmp = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n2; j++) {
                prev = tmp;
                tmp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                }
            }
        }

        return dp[n2];
    }

    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();

        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= n2; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j - 1], dp[i][j-1])
                            ,dp[i -1][j]) + 1;
                }
            }
        }

        return dp[n1][n2];
    }
}
