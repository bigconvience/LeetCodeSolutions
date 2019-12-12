package com.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderTraversal {
    public static void main(String[] args) {
        case1();

        case2();
    }

    public static void case1() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        System.out.println(preorder(root));
    }

    public static void case2() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);


        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;

        System.out.println(preorder(node1));
        System.out.println(preorder1(node1));
        System.out.println(preorder2(node1));
        System.out.println(preorder3(node1));
    }




    public static List<Integer> preorder3(TreeNode root) {
        TreeNode cur = root;
        List<Integer> ans = new ArrayList<>();
        while (cur != null) {
            TreeNode pre = cur.left;
            if (pre == null) {
                ans.add(cur.val);
                cur = cur.right;
            } else {
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    ans.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                }

                if (pre.right == cur) {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }

        return ans;
    }

    public static List<Integer> preorder2(TreeNode root) {
        TreeNode cur = root;
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                ans.add(cur.val);
                stack.push(cur.right);
                stack.push(cur.left);
            }
            cur = stack.pop();
        }
        return ans;
    }

    public static List<Integer> preorder1(TreeNode root) {
       TreeNode cur = root;
       List<Integer> ans = new ArrayList<>();
       Stack<TreeNode> stack = new Stack<>();
       while (cur != null || !stack.isEmpty()) {
           if (cur != null) {
               ans.add(cur.val);
               stack.push(cur.right);
               stack.push(cur.left);
           }
           cur = stack.pop();
       }
       return ans;
    }

    public static List<Integer> preorder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    public static void traversal(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        ans.add(node.val);
        traversal(node.left, ans);
        traversal(node.right, ans);
    }
}
