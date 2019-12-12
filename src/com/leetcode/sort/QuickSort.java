package com.leetcode.sort;

import com.jbp.utils.ArrayUtils;

/**
 * 算法导论 P85 quickSort
 *
 */
public class QuickSort {
    public static int count = 0;

    public static void main(String[] args) {
//        int[] nums1 = {2, 8, 7, 1, 3, 5, 6, 4};
        int[] nums1 = {2, 2, 2, 2, 2, 2, 2, 2};
        qSort(nums1);
        ArrayUtils.print(nums1);

        System.out.println(count);
    }

    public static void qSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int p, int r) {
        if (p < r) {
            int q = partition(nums, p, r);
            System.out.println("Q: " + q + ":" + nums[q]);
            quickSort(nums, p, q - 1);
            quickSort(nums, q + 1, r);
        }
    }

    public static int partition(int[] nums, int start, int end) {
        int x = nums[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            count++;
            if (nums[j] <= x) {
                exchange(nums, ++i, j);
            }
        }
        exchange(nums, ++i, end);
        return i;
    }

    public static void exchange(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
