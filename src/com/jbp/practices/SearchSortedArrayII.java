package com.jbp.practices;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class SearchSortedArrayII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 1, 1, 1};
//        int[] nums = {5};
        System.out.println("index: " + new SearchSortedArrayII().search(nums, 3));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;

        int mid = (start + end) / 2;

        while (start + 1 < end) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[start] > nums[mid]){
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                start++;
            }
            mid = (start + end) / 2;
        }

        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
