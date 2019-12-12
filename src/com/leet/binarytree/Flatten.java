package com.leet.binarytree;

import com.jbp.utils.TreeUtils;

import java.util.Stack;

public class Flatten {
    public static void main(String[] args) {
        new Flatten().flatten(TreeUtils.tree1());
        System.out.println("3nd");
    }

    TreeNode pre = null;

    /**
     * 前序遍历会丢失右子节点，使用后序遍历
     * @param root
     */
    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten1(root.right);
        flatten1(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.peek();

            if (cur.left == null || cur.left == prev) {
                stack.pop();
                cur.right = prev;
                cur.left = null;
                prev = cur;
                cur = null;
            } else {
                cur = cur.left;
            }
        }
    }


    private void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (prev != null) {
                prev.right = cur;
                prev.left = null;
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }

            prev = cur;
        }

    }

    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
                cur = cur.right;
            }
        }
    }
}
