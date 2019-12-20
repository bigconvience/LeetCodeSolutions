package com.introduction.stringmatching;

public class Naive {
    public static void main(String[] args) {
        new Naive().case1();
        new Naive().case2();
    }

    public void case1() {
       String t = "acaabc";
       String p = "aab";
       System.out.println(impl1(t.toCharArray(), p.toCharArray()));
    }

    public void case2() {
        String t = "00010001010001";
        String p = "0001";
        System.out.println(impl1(t.toCharArray(), p.toCharArray()));
    }

    public int impl1(char[] t, char[] p) {
        if (t == null || p == null) {
            return 0;
        }

        int count = 0;
        int m = t.length;
        int n = p.length;
        int s = m - n + 1;

        for (int i = 0; i < s; i++) {
            if (equals(t, i, p)) {
                System.out.println("index: " + i);
                count++;
            }
        }

        return count;
    }

    public boolean equals(char[] t, int start, char[] p) {
        for (int i = 0; i < p.length; i++) {
            if (t[i + start] != p[i]) {
                return false;
            }
        }
        return true;
    }
}
