package com.leet.practices;

/**
 * https://zhuanlan.zhihu.com/p/55666669
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/submissions/
 */
public class MedianOfTwo {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0) {
            return median(nums2);
        }
        if ( n == 0) {
            return median(nums1);
        }

        int[] nums = mergeArray(nums1, nums2);

        return median(nums);
    }

    private static int[] mergeArray(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int current = 0;
        int i = 0, j = 0;
        int size = m + n;
        int[] nums = new int[size];
        while (current < size) {
            if (i >= m) {
                while (j < n) {
                    nums[current++] = nums2[j++];
                }
                break;
            }

            if (j >= n) {
                while (i < m) {
                    nums[current++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] > nums2[j]) {
                nums[current++] = nums2[j++];
            } else {
                nums[current++] = nums1[i++];
            }
        }

        return nums;
    }

    private static double median(int[] nums) {
        int n = nums.length;
        if (n % 2 == 0) {
            return (nums[n/2-1] + nums[n/2]) / 2.0;
        } else {
            return nums[n/2];
        }
    }

}
