package com.jbp.practices;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MinimumInSortedArrayII {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 1, 1, 1, 1};
        int[] nums = {3, 3, 1};
        MinimumInSortedArrayII maxProduct = new MinimumInSortedArrayII();
        System.out.println(maxProduct.findMin(nums));

    }

    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end) / 2;
        while (start + 1 < end) {
            if (nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end--;
            }
            mid = (start + end) / 2;
        }


        return Math.min(nums[start], nums[end]);
    }
}
