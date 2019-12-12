package com.jbp.utils;

import com.leet.binarytree.TreeNode;

import java.util.List;

public class TreeUtils {
    public static TreeNode arraysToTree(TreeNode[] input) {
        return buildTree(input, 0);
    }

    private static TreeNode buildTree(TreeNode[] input, int index) {
        if (input == null
                || index >= input.length) {
            return null;
        }
        TreeNode node = input[index];
        int left = index * 2 + 1;
        if (left < input.length && node != null) {
            node.left = buildTree(input, left);
        }

        int right = index * 2 + 2;
        if (right < input.length && node != null) {
            node.right = buildTree(input, right);
        }

        return node;
    }

    public static TreeNode left(List<TreeNode> input, int height) {
        int index = height * 2 + 1;
        if (index < input.size()) {
            return input.get(index);
        } else {
            return null;
        }
    }

    public static TreeNode right(List<TreeNode> input, int height) {
        int index = height * 2 + 2;
        if (index < input.size()) {
            return input.get(index);
        } else {
            return null;
        }
    }


    public static TreeNode tree1() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        return root;
    }

    public static TreeNode tree11() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.left = node1;
        root.right = node2;

        return root;
    }

    public static TreeNode tree2() {
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

        return node1;
    }

    public static TreeNode tree3() {
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

        return node1;
    }


}
