package com.jbp.practices;

/**
 * Created by jiangbenpeng on 9/3/16.
 * https://leetcode.com/problems/zigzag-conversion/*
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(new SolutionManacher().longestPalindrome("abacdfgdcaba"));
        System.out.println(new SolutionManacher().longestPalindrome("aba"));
    }


    public static class SolutionManacher {
        public String longestPalindrome(String s) {
            if (s.isEmpty()) {
                return s;
            }

            char[] str = new char[2002];
            int[] p = new int[2002];
            int len = init(str, s);
            calculate(str, len, p);

            int maxLength = 0;
            int id = 1;

            for (int i = 0; i < maxLength; i++) {
                if (p[i] > maxLength) {
                    maxLength = p[i];
                    id = i;
                }
            }
            char[] result = new char[maxLength - 1];

            int middle = id;
            if (str[id] == '#') {
                middle++;
            }


            return s.substring(id, id + maxLength);
        }

        private void calculate(char[] str, int length, int[] p) {
             int id = 0;
            int mx = 0;

            for (int i = 1; i < length; i++) {
                if (mx > i) {
                    p[i] = Math.max(p[2 * id  - i], p[id] + id - i);
                } else {
                    p[i] = 1;
                }

                for (; str[i-p[id]] == str[i + p[id]]; p[id]++);

                if (i + p[i] > mx) {
                    mx = p[i] + i;
                    id = i;
                }
            }
        }

        private int init(char[] str, String s) {
            int n;
            str[0] = '$';
            str[1] = '#';
            for (int i = 0; i < s.length(); i++) {
                str[i * 2 + 2] = s.charAt(i);
                str[i * 2 + 3] = '#';
            }
            n = s.length() * 2 + 2;
            str[n] = 0;
            return n;
        }


    }

    public static class SolutionDY {
        public String longestPalindrome(String s) {
            if (s.isEmpty()) {
                return s;
            }
            char[][] p = new char[1000][1000];

            int maxLength = 1;
            int maxStart = 0;
            for (int i = 0; i < s.length(); i++) {
                p[i][i] = 1;
            }

            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    p[i][i + 1] = 1;
                    maxLength = 2;
                    maxStart = i;
                }
            }

            for (int len = 3; len <= s.length(); len++) {
                for (int i = 0; i < s.length() - len + 1; i++) {
                    int j = len + i - 1;
                    if (s.charAt(i) == s.charAt(j) && p[i + 1][j - 1] == 1) {
                        p[i][j] = 1;
                        maxStart = i;
                        maxLength = len;
                    }
                }
            }

            return s.substring(maxStart, maxStart + maxLength);
        }
    }
}
