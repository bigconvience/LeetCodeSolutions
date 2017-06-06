package com.jbp.leetcode;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MinimumInSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 0, 1, 2};
//        int[] nums = {0, 2};
        MinimumInSortedArray maxProduct = new MinimumInSortedArray();
        System.out.println(maxProduct.findMin(nums));

    }

    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end) / 2;
        while (start + 1 < end) {
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
            mid = (start + end) / 2;
        }


        return Math.min(nums[start], nums[end]);
    }
}
