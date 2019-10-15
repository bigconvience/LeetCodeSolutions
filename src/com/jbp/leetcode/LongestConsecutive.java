package com.jbp.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive1(nums1));
    }

    //聚类法 https://www.jianshu.com/p/170d23453012
    public static int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = 1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (map.get(value) != null) {
                continue;
            }
            map.put(value, 1);

            if (map.get(value - 1) != null) {
                length = Math.max(length, mergeCluster(map, value-1, value));
            }

            if (map.get(value + 1) != null) {
                length = Math.max(length, mergeCluster(map, value, value+1));
            }
        }
        return length;
    }

    private static int mergeCluster(Map<Integer, Integer> map, int left, int right) {
        int upper = right + map.get(right) - 1;
        int lower = left - map.get(left) + 1;
        int length = upper - lower + 1;
        map.put(upper, length);
        map.put(lower, length);
        return length;
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Boolean> used = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            used.put(nums[i], false);
        }

        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (used.get(value)) {
                continue;
            }

            int length = 1;
            used.put(value, true);
            for (int j = value + 1; used.get(j) != null; j++) {
                length++;
                used.put(j, true);
            }

            for (int j = value - 1; used.get(j) != null; j--) {
                length++;
                used.put(j, true);
            }

            longest = Math.max(length, longest);

        }

        return longest;
    }
}
