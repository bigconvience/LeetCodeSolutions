package com.jbp.binarytree;

import java.util.ArrayList;
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
        if (left < input.length) {
            node.left = buildTree(input, left);
        }

        int right = index * 2 + 2;
        if (right < input.length) {
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
}
