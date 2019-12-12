package com.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {
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

        System.out.println(inorderTraversal(root));
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

        System.out.println(inorderTraversal(node1));
        System.out.println(inorder1(node1));
        System.out.println(inorderMorris(node1));
    }

    public static List<Integer> inorder1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode top = stack.pop();
            ans.add(top.val);
            cur = top.right;
        }

        return ans;
    }

    /**
     * https://leetcode.wang/leetCode-94-Binary-Tree-Inorder-Traversal.html?h=traversal
     * @param root
     * @return
     */
    public static List<Integer> inorderMorris(TreeNode root) {
       TreeNode cur = root;
        List<Integer> result = new ArrayList<>();
        while (cur != null) {
           TreeNode prev = cur.left;
           if (prev == null) {
               result.add(cur.val);
               cur = cur.right;
           } else {
               while (prev.right != null && prev.right != cur) {
                   prev = prev.right;
               }

               if (prev.right == null) {
                   prev.right = cur;
                   cur = cur.left;
               } else if (prev.right == cur) {
                   prev.right = null;
                   result.add(cur.val);
                   cur = cur.right;
               }
           }
       }
        return result;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        inorderTraver(root, result);

        return result;
    }

    public static void inorderTraver(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return;
        }
        inorderTraver(treeNode.left, result);
        result.add(treeNode.val);
        inorderTraver(treeNode.right, result);
    }
}
