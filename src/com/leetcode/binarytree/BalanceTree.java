package com.leetcode.binarytree;

import com.jbp.utils.TreeUtils;

public class BalanceTree {
    public static void main(String[] args) {
        new BalanceTree().case1();
    }

    public void case1() {
        System.out.println(isBalanced1(TreeUtils.tree11()));
    }

    public boolean isBalanced1(TreeNode root) {
        return getBalancedHeight(root) >= 0;
    }

    private int getBalancedHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = getBalancedHeight(node.left);
        if (left == -1) {
            return -1;
        }

        int right = getBalancedHeight(node.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getTreeHeight(root.left);
        int right = getTreeHeight(root.right);
        if (Math.abs(right - left) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int getTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getTreeHeight(root.left), getTreeHeight(root.right)) + 1;
    }
}
