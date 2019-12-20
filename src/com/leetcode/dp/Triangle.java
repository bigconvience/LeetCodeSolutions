package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        int[][] triangle = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };

        int[] nums= {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new Triangle().maxSubArray1(nums));
        System.out.println(new Triangle().maxSubArray(nums));
    }

    public int maxSubArray1(int[] nums) {
        return getSubMax(0, nums.length - 1, nums);
    }

    private int getSubMax(int start, int end, int[] nums) {
        //递归出口
        if (start == end) {
            return nums[start];
        }
        int mid = (start + end) / 2;
        //要找的数组不包含 mid，然后得到左边和右边最大的值
        int leftMax = getSubMax(start, mid, nums);
        int rightMax = getSubMax(mid + 1, end, nums);
        //要找的数组包含 mid
        int containsMidMax = getContainMidMax(start, end, mid, nums);
        //返回它们 3 个中最大的
        return Math.max(containsMidMax, Math.max(leftMax, rightMax));
    }

    private int getContainMidMax(int start, int end, int mid, int[] nums) {
        int containsMidLeftMax = 0; //初始化为 0 ，防止最大的值也小于 0
        //找左边最大
        if (mid > 0) {
            int sum = 0;
            for (int i = mid - 1; i >= start; i--) {
                sum += nums[i];
                if (sum > containsMidLeftMax) {
                    containsMidLeftMax = sum;
                }
            }

        }
        int containsMidRightMax = 0;
        //找右边最大
        if (mid < end) {
            int sum = 0;
            for (int i = mid + 1; i <= end; i++) {
                sum += nums[i];
                if (sum > containsMidRightMax) {
                    containsMidRightMax = sum;
                }
            }
        }
        return containsMidLeftMax + nums[mid] + containsMidRightMax;
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        if (nums.length == 0) {
            return max;
        }
        int prev = nums[0];
        max = prev;
        for (int i = 1; i < nums.length; i++) {
            if (prev < 0) {
                prev = nums[i];
            } else {
                prev += nums[i];
            }
            max = Math.max(max, prev);
        }
        return max;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);

        int min = dp[0];

        int prev;
        int temp;
        for (int i = 1; i < m; i++) {
            temp = dp[0];
            min = Integer.MAX_VALUE;
            for (int j = 0; j < triangle.get(i).size(); j++) {
                prev = temp;
                temp = triangle.get(i).get(j);

                if (j == 0) {
                    dp[j] = dp[j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i - 1).size()) {
                    dp[j] = prev + triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(prev, dp[j]) + triangle.get(i).get(j);
                }
                min = Math.min(min, dp[j]);
            }
        }

        return min;
    }
}
