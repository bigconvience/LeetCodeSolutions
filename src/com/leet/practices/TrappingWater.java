package com.leet.practices;


import java.util.Stack;

/**
 * https://zhuanlan.zhihu.com/p/79811305
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class TrappingWater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(height));
    }


    public static int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int result = 0;
        Stack<Integer> mVector = new Stack<>();
        int current = 0;
        while (current < height.length) {
            while (!mVector.isEmpty() && height[mVector.peek()] < height[current]) {
                int bar = height[mVector.peek()];
                mVector.pop();
                if (mVector.isEmpty()) {
                    break;
                }

                result += (Math.min(height[current], height[mVector.peek()]) - bar) * ( current - mVector.peek() - 1);
            }
            mVector.push(current);
            current++;
        }

        return result;
    }

    public static int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        final int size = height.length;
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (height[i] > height[max]) max = i;
        }

        int result = 0;
        for (int i = 0, top = 0; i < max; i++) {
            if (height[i] > top) {
                top = height[i];
            } else {
               result += top - height[i];
            }
        }

        for (int i = size - 1, top = 0; i > max; i--) {
            if (height[i] > top) {
                top = height[i];
            } else {
                result += top - height[i];
            }
        }

        return result;
    }

    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

       final int size = height.length;
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];

        leftMax[0] = 0;
        rightMax[size - 1] = 0;
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
            rightMax[size - i - 1] = Math.max(rightMax[size - i], height[size - i]);
        }

        int result = 0;
        for (int i = 1; i < size - 1; i++) {
            int delta = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (delta > 0) {
                result += delta;
            }
        }

        return result;
    }
}

