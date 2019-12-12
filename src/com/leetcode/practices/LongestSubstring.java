package com.leetcode.practices;

import java.util.Arrays;

/**
 * Created by jiangbenpeng on 9/3/16.
 * https://leetcode.com/problems/zigzag-conversion/*
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }

    public static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int[] map = new int[256];
            Arrays.fill(map, -1);
            int start = 0;
            int end;
            int result = 0;

            for (end = 0; end < s.length(); end++) {
                char ch = s.charAt(end);
                while (map[ch] != -1 && start < end) {
                    map[s.charAt(start)] = -1;
                    start++;
                }

                map[ch] = 0;
                result = Math.max(result, end - start + 1);

            }

            return result;
        }
    }
}
