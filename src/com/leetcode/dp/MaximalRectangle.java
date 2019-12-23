package com.leetcode.dp;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args) {

    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }

            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public int getLargestRectangleArea_stack(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        int maxArea = 0;
        while (p < n) {
            int height = heights[p];
            if (stack.isEmpty() || stack.peek() < height) {
                stack.push(height);
                p++;
            } else {
                int curHeight = stack.pop();
                int prevIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = curHeight * (p - prevIndex - 1);
                maxArea = Math.max(area, maxArea);
            }
        }

        while (!stack.isEmpty()) {
            int height = stack.pop();
            int prevIndex = stack.isEmpty() ? -1 : stack.peek();
            int area = height * (n - prevIndex - 1);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }


    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        return getMaxArea(heights, 0, heights.length - 1);
    }

    public int getMaxArea(int[] heights, int start, int end) {
        if (start == end) {
            return heights[start];
        }
        int mid = (start + end) >>> 1;
        int left = getMaxArea(heights, start, mid);
        int right = getMaxArea(heights, mid + 1, end);
        int middle = getMidArea(heights, start, mid, end);
        return Math.max(left, Math.max(right, middle));
    }

    public int getMidArea(int[] heights, int left, int mid, int right) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return heights[left];
        }

        int i = mid;
        int j = mid + 1;
        int minH = Math.min(heights[mid], heights[j]);
        int area = minH * 2;
        while (i >= left && j <= right) {
            minH = Math.min(minH, Math.min(heights[i], heights[j]));
            area = Math.max(area, minH * (j - i + 1));
            if (i == left) {
                j++;
            } else if (j == right) {
                i--;
            } else if (heights[i- 1] >= heights[j + 1]) {
                i--;
            } else {
                j++;
            }
        }
        return area;
    }


}

