package com.jbp.stack;

import java.util.HashSet;

public class LargestRectangle {

    public static void main(String[] args) {
        // write your code here
        String a = "[[]]}";
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(height));

        int[] height1 = {1};
        System.out.println(largestRectangleArea(height1));

    }

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        return findArea(heights, 0, heights.length - 1);
    }

    private static int findArea(int[] height, int left, int right) {
        if (left == right) {
            return height[left];
        }

        int mid = (left + right) / 2;
        int area1 = findArea(height, left, mid);
        int area2 = findArea(height, mid + 1, right);
        int area3 = findMidArea(height, mid, left, right);
        return Math.max(Math.max(area1, area2), area3);
    }

    private static int findMidArea(int[] height, int mid, int left, int right) {
        int i = mid;
        int j = mid + 1;

        int minH = Math.min(height[i], height[j]);
        int maxArea = minH * 2;
        while (i >= left && j <= right) {
            minH = Math.min(minH, Math.min(height[i], height[j]));
            maxArea = Math.max(maxArea, minH * (j - i + 1));
            if (i == left) j++;
            else if (j == right) i--;
            else if (height[i - 1] > height[j + 1]){
                i--;
            } else {
                j++;
            }
        }
        return maxArea;
    }
}
