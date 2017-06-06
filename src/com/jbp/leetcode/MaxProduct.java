package com.jbp.leetcode;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MaxProduct {
    public static void main(String[] args) {
//        int[] nums = {2,3,-2,4};
        int[] nums = {0, 2};
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maxProduct(nums));

    }

    public int maxProduct(int[] nums) {
        int curMax = nums[0];
        int curMin = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int tmp = curMax * nums[i];
            curMax = Math.max(nums[i], Math.max(tmp, curMin * nums[i]));
            curMin = Math.min(nums[i], Math.min(tmp, curMin * nums[i]));
            max = Math.max(max, curMax);
        }

        return max;
    }
}
