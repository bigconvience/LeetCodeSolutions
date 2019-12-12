package com.leet.practices;

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
        if (nums.length <= 2) {
            return nums.length;
        }

        int index = 2;
        for(int i = 2; i < nums.length; i++) {
            if (nums[index - 2] != nums[i]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}

