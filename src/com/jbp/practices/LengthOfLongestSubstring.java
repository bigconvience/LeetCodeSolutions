package com.jbp.practices;


/**
 * Created by jiangbenpeng on 9/3/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String str =  "pwwkew";

        System.out.println(new Solution().lengthOfLongestSubstring(str));

        str = "bbbbb";
        System.out.println(new Solution().lengthOfLongestSubstring(str));

         str = "abcabcbb";
        System.out.println(new Solution().lengthOfLongestSubstring(str));

    }

    public static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int start, end;
            start = end = 0;

            int cmpStart = start;
            int cmpEnd = end + 1;

            while (cmpStart < s.length() && cmpEnd < s.length()) {
                int sameEnd = -1;
                while (cmpEnd < s.length()) {
                    sameEnd = containEnd(s, cmpStart, cmpEnd);
                    if (sameEnd > -1) {
                        cmpEnd--;
                        break;
                    }
                    cmpEnd++;
                }
                if (cmpEnd >= s.length()) {
                    cmpEnd--;
                }

                int oldLength = end - start + 1;
                int newLength = cmpEnd - cmpStart + 1;
                if (newLength > oldLength) {
                    start = cmpStart;
                    end = cmpEnd;
                }

                cmpStart = sameEnd == -1? s.length(): sameEnd + 1;
            }

            return end - start + 1;
        }

        private int containEnd(String s, int start, int end) {
            if (start >= end || s == null || s.length() == 0) {
                return -1;
            }

            if (start < 0 || end >= s.length()) {
                return -1;
            }

            char endChar = s.charAt(end);
            for (int i = end - 1; i>= start; i--) {
                if (s.charAt(i) == endChar) {
                    return i;
                }
            }
            return -1;
        }
    }
}
