package com.jbp.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-consecutive-sequence
 */
public class ConsecutiveSequence {
    public static void main(String[] args) {
        int[] input = {100, 4, 200, 1, 3, 2};
        System.out.println(new ConsecutiveSequence().longestConsecutive1_1(input));
        System.out.println(new ConsecutiveSequence().longestConsecutive2_1(input));
    }

    public int longestConsecutive1_1(int[] input) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            set.add(input[i]);
        }

        int maxLength = 0;
        for (int i = 0; i < input.length; i++) {
            int j = input[i];
            while (set.contains(j)) {
                j++;
            }
            maxLength = Math.max(maxLength, j - input[i]);
        }
        return maxLength;
    }

    public int longestConsecutive1_2(int[] input) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            set.add(input[i]);
        }

        int maxLength = 0;
        for (int i = 0; i < input.length; i++) {
            int j = input[i];
            if (!set.contains(j - 1)) {
                while (set.contains(j)) {
                    j++;
                }
                maxLength = Math.max(maxLength, j - input[i]);
            }
        }
        return maxLength;
    }


    public int longestConsecutive2_1(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                continue;
            }

            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int sum = left + 1 + right;
            maxLength = Math.max(sum, maxLength);

            map.put(num, -1);
            map.put(num - left, sum);
            map.put(num + right, sum);
        }

        return maxLength;
    }


}
