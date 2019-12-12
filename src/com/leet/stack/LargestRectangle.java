package com.leet.stack;

import java.util.Stack;

/**
 * https://leetcode.wang/leetCode-84-Largest-Rectangle-in-Histogram.html?h=rectangle
 */
public class LargestRectangle {

    public static void main(String[] args) {
        // write your code here
        int[] height = {2, 1, 5, 6, 2, 3};
        int[] height1 = {1};

        System.out.println(largestRectangleArea1(height));
        System.out.println(largestRectangleArea1(height1));

        System.out.println(largestRectangleArea2(height));
        System.out.println(largestRectangleArea2(height1));

        System.out.println(largestRectangleArea3(height));
        System.out.println(largestRectangleArea3(height1));
    }

    public static int largestRectangleArea1(int[] heights) {
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


    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int[] leftLessMin = new int[heights.length];
        int[] rightLessMin = new int[heights.length];

        leftLessMin[0] = -1;
        rightLessMin[heights.length - 1] = heights.length;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = leftLessMin[p];
            }
            leftLessMin[i] = p;
        }

        for (int i = heights.length - 2; i >=0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                p = rightLessMin[p];
            }
            rightLessMin[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = (rightLessMin[i] -  leftLessMin[i] - 1) * heights[i];
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }


    public static int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        int maxArea = 0;
        while (p < heights.length) {
            if (stack.isEmpty()) {
                stack.push(p++);
            } else {
                int top = stack.peek();
                if (heights[p] >= heights[top]) {
                    stack.push(p++);
                } else {
                    int height = heights[stack.pop()];
                    int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    int rightLessMin = p;
                    int area = (rightLessMin - leftLessMin - 1) * height;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            int rightLessMin = heights.length;
            int area = (rightLessMin - leftLessMin - 1) * heights[top];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
