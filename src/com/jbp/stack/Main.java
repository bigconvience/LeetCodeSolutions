package com.jbp.stack;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String a = "[[]]}";
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(height));

        int[] height1 = {1};
        System.out.println(largestRectangleArea(height1));

    }

    public static int largestRectangleArea(int[] heights) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < heights.length; i++) {
            hashSet.add(heights[i]);
        }

        int result  = 0;
        for (Integer height: hashSet) {
            int maxRange = 0;
            int length = 0;
            for (int i = 0; i < heights.length; i++) {
                if (heights[i] >= height) {
                    length++;
                } else {
                    maxRange = Math.max(maxRange, length);
                    length = 0;
                }
            }
            maxRange = Math.max(maxRange, length);
            result = Math.max(result, maxRange * height);
        }
        return result;
    }
}
