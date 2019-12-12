package com.jbp.utils;

public class ArrayUtils {
    public static void print(int[] nums) {
        if (nums == null) {
            System.out.println("Null nums");
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println("");
    }
}
