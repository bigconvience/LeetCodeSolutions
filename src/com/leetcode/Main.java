package com.leetcode;


import com.jbp.utils.ListNode;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] nums = {1, 0, 2};
        System.out.println("aaa");
        System.out.println(new Main().isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int start = 0, end = s.length() - 1;

        while (start < end) {
            char left = s.charAt(start);
            char right = s.charAt(end);
            if (Character.isSpaceChar(left) || !Character.isLetterOrDigit(left)) {
                start++;
                continue;
            }

            if (Character.isSpaceChar(right) || !Character.isLetterOrDigit(right)) {
                end--;
                continue;
            }

            if (Character.toLowerCase(left) !=
                    Character.toLowerCase(right)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
