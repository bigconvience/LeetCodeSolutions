package com.jbp.practices;

/**
 * Created by jiangbenpeng on 9/3/16.
 * https://leetcode.com/problems/zigzag-conversion/*
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ZigZag {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
    }

    public static class Solution {
        public String convert(String s, int numRows) {
            if (s.length() <= numRows || numRows == 1) {
                return s;
            }

            int length = s.length();
            int step = 2 * (numRows - 1);
            int count = 0;
            char[] answer = new char[length];

            for (int i = 0; i < numRows; i++) {
                int interval = step - i * 2;
                for (int j = i; j < length; j += step) {
                    answer[count++] = s.charAt(j);
                    if (interval > 0 && interval < step
                            && j + interval < length && count < length) {
                        answer[count++] = s.charAt(j + interval);
                    }
                }
            }


            return new String(answer);
        }
    }
}
