package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    public static void main(String[] args) {

        String s1 = "a";
        System.out.println(new Palindrome().partition(s1));

        String s = "aabb";
        System.out.println(new Palindrome().partition(s));

        String a = "bb";
        System.out.println(new Palindrome().partition(a));

        String c = "cdd";
        System.out.println(new Palindrome().partition(c));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return ans;
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len <= 3 || dp[i+1][j-1]);
            }
        }

        partitionConquer(s, 0, s.length(), dp, new ArrayList<String>(), ans);
        return ans;
    }

    public void  partitionConquer(String s, int start, int end, boolean[][] dp, List<String> temp, List<List<String>> ans) {
        if (start == end) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < end; i++) {
            if (dp[start][i]) {
                temp.add(s.substring(start, i+1));
                partitionConquer(s, i + 1, end, dp, temp, ans);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        if (s == null) {
            return false;
        }

        if (start < 0 || end >= s.length()) {
            return false;
        }

        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }


}
