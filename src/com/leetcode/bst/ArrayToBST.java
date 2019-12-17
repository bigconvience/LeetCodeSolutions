package com.leetcode.bst;

import com.jbp.utils.TreeUtils;
import com.leetcode.binarytree.TreeNode;

public class ArrayToBST {
    public static void main(String[] args) {

    }

    public void case1() {
        int[] s = {-10,-3,0,5,9};
        sortedArrayToBST(s);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    public TreeNode buildBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, start, mid - 1);
        root.right = buildBST(nums, mid + 1, end);
        return root;
    }
}
