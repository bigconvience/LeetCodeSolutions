package com.jbp.practices;


import java.util.*;

/**
 * Created by jiangbenpeng on 9/3/16.
 * https://leetcode.com/problems/zigzag-conversion/*
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class Atoi {


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Atoi().getPermutation(5, 16));
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[] used = new boolean[9];

        for (int i = 0; i < 9; i++) {
            Arrays.fill(used, false);
            for (int j = 0; j < 9; j++) {
                if (!isValid(board[i][j], used)) {
                    return false;
                }
            }

            Arrays.fill(used, false);
            for (int j = 0; j < 9; j++) {
                if (!isValid(board[j][i], used)) {
                    return false;
                }
            }
        }

        Arrays.fill(used, false);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                for (int i = r * 3; i < r * 3 + 3; i++) {
                    for (int j =  c * 3; j < c * 3 + 3; j++) {
                        if (!isValid(board[j][i], used)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean isValid(char chr, boolean[] used) {
        if (chr == '.') return true;

        if (used[chr - 1]) {
            return false;
        }

        used[chr - 1] = true;
        return true;
    }

    public String getPermutation(int n, int k) {
        --k;

        StringBuilder input = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            input.append(i);
        }
        int base = fn(n - 1);
        StringBuilder builder = new StringBuilder();
        for (int i = n - 1; i > 0; k %= base, base /= i, i--) {
            int index = k / base;
            char ax = input.charAt(index);
            builder.append(ax);
            input.deleteCharAt(index);
        }
        builder.append(input.charAt(0));

        return builder.toString();
    }

    public int fn(int n) {
        int result = 1;
        for (int i = n; i > 1; i--) {
            result *= i;
        }
        return result;
    }

    public int[] buildSequence(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        return nums;
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int size = nums.length;
        int partitionPoint = -1;
        for (int i = size - 2; i >=0; i--) {
            if (nums[i] < nums[i+1]) {
                partitionPoint = i;
                break;
            }
        }

        if (partitionPoint == -1) {
            reverse(nums, 0, size - 1);
            return;
        }

        int swapPoint = 0;
        for (int i = size - 1; i > partitionPoint; i--) {
            if (nums[i] > nums[partitionPoint]) {
                swapPoint = i;
                break;
            }
        }
        swap(nums, partitionPoint, swapPoint);
        reverse(nums, partitionPoint + 1, size - 1);


    }

    private  static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ": ");
        }
        System.out.println("");
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
