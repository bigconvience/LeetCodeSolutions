package com.leetcode.practices;

/**
 * Created by jiangbenpeng on 9/3/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class DuplicateNumber {
    public static void main(String[] args) {
        int[] str =  {1, 2, 3, 4, 5, 6, 4};
        Solution answer = new Solution();

        int a = 10;
        boolean b = true;
        boolean c = false;

        if (a == 9 ||
                b && c ) {
            System.out.println("a");
        } else {
            System.out.println("b");
        }
    }

    static class Solution {
        public int findDuplicate(int[] nums) {
            int low = 1;
            int high = nums.length - 1;

            while (low < high) {
                int middle = (low + high) / 2;
                int count = 0;
                for (int num : nums) {
                    if (num <= middle) {
                        count++;
                    }
                }
                if (count > middle) {
                    high = middle;
                } else {
                    low = middle + 1;
                }

            }

            return low;
        }
    }
}
