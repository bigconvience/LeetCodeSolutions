package com.jbp.practices;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(new DivideTwoIntegers().removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int pre = 0;
        for (int cur = 1; cur < nums.length; cur++) {
            if (nums[pre] != nums[cur]) {
                nums[++pre] = nums[cur];
            }
        }
        return pre + 1;
    }
}

