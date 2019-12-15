package com.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Distinct Subsequences
 * https://leetcode.wang/leetcode-115-Distinct-Subsequences.html
 */
public class Subsequences {
    public static void main(String[] args) {
        new Subsequences().case3();

        new Subsequences().case2();
    }

    /**
     * 递归分治
     */
    public void case1() {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(numDistinct(s, t));
    }

    public void case2() {
        String s = "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc";
        String t = "bcddceeeebecbc";
        long start = System.currentTimeMillis();
        System.out.println(numDistinct1(s, t));
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end -  start));
    }

    /**
     * 递归回溯
     */
    public void case3() {
        String s = "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc";
        String t = "bcddceeeebecbc";
        long start = System.currentTimeMillis();
        System.out.println(numDistinct3(s, t));
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end -  start));
    }

    int count = 0;
    public int numDistinct3(String s, String t) {
        Map<String, Integer> map = new HashMap<>();
        numDistinctHelper3(s, 0, t, 0, map);
        return count;
    }

    public void numDistinctHelper3(String s, int sStart, String t, int tStart, Map<String, Integer> map) {
        if (tStart == t.length()) {
            count++;
            return;
        }

        if (sStart == s.length()) {
            return;
        }

        String key = sStart + "@" + tStart;
        if (map.containsKey(key)) {
            count += map.get(key);
            return;
        }

        int preCount = count;
        if (s.charAt(sStart) == t.charAt(tStart)) {
            numDistinctHelper3(s, sStart + 1, t, tStart + 1, map);
        }
        numDistinctHelper3(s, sStart + 1, t, tStart, map);
        map.put(key, count - preCount);
    }



    public int numDistinct(String s, String t) {
        return numDistinctHelper(s, 0, t, 0);
    }

    public int numDistinct1(String s, String t) {
        Map<String, Integer> map = new HashMap<>();
        return numDistinctHelper(s, 0, t, 0, map);
    }


    public int numDistinctHelper(String s, int sStart, String t, int tStart, Map<String, Integer> map) {
        if (tStart == t.length()) {
            return 1;
        }
        if (sStart == s.length()) {
            return 0;
        }

        String key = sStart + "@" + tStart;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int count;
        if (s.charAt(sStart) == t.charAt(tStart)) {
            count =  numDistinctHelper(s, sStart + 1, t, tStart + 1, map)
                    + numDistinctHelper(s, sStart + 1, t, tStart, map);
        } else {
            count = numDistinctHelper(s, sStart + 1, t, tStart, map);
        }

        map.put(key, count);

        return count;
    }


    public int numDistinctHelper(String s, int sStart, String t, int tStart) {
        if (tStart == t.length()) {
            return 1;
        }
        if (sStart == s.length()) {
            return 0;
        }

        if (s.charAt(sStart) == t.charAt(tStart)) {
            return numDistinctHelper(s, sStart + 1, t, tStart + 1)
                    + numDistinctHelper(s, sStart + 1, t, tStart);
        } else {
            return numDistinctHelper(s, sStart + 1, t, tStart);
        }
    }
}
