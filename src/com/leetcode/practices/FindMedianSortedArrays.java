package com.leetcode.practices;


/**
 * Created by jiangbenpeng on 9/3/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] sum1 = {1, 3};
        int[] sum2 = {2};

        System.out.println(new Solution().findMedianSortedArrays(sum1, sum2));

        int[] sum3 = {1, 2};
        int[] sum4 = {3, 4};
        System.out.println(new Solution().findMedianSortedArrays(sum3, sum4));
    }

    public static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int len = nums1.length + nums2.length;
            if ((len & 1) == 1) {
                return findKth(nums1, 0, nums2, 0, len / 2 + 1);
            } else {
                return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len/ 2 + 1)) / 2.0 ;
            }
        }

        private int findKth(int[] a1, int i1, int[] a2, int i2, int k) {
            if (i1 >= a1.length) {
                return a2[i2 + k - 1];
            }

            if (i2 >= a2.length) {
                return a1[i1 + k - 1];
            }

            if (k == 1) {
                return Math.min(a1[i1], a2[i2]);
            }

            int len = k / 2;

            int k1 = i1 + len  - 1 < a1.length ? a1[i1 + len - 1]: Integer.MAX_VALUE;
            int k2 = i2 + len  - 1 < a2.length ? a2[i2 + len - 1]: Integer.MAX_VALUE;

            if (k1 < k2) {
                return findKth(a1, i1 + len, a2, i2, k - len);
            } else {
                return findKth(a1, i1, a2, i2 + len, k - len);
            }
        }
    }
}
