package com.leetcode.binarytree;

import java.util.Stack;

public class SameTree {

    public static void main(String[] args) {
        // write your code here

        new SameTree().case1();
    }

    public void case1() {
        TreeNode tree1 = tree1();
        TreeNode tree2 = tree2();
        TreeNode tree3 = tree3();

        System.out.println(isSameTree(tree1, tree2));
        System.out.println(isSameTree(tree3, tree2));
        System.out.println(isSymmetric(tree3));
        System.out.println(isSymmetric1(tree3));
    }

    public boolean isSymmetric1(TreeNode node) {
        if (node == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node.right);
        stack.push(node.left);

        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            stack.push(right.right);
            stack.push(left.left);

            stack.push(right.left);
            stack.push(left.right);
        }

        return true;
    }

    public boolean isSymmetric(TreeNode node) {
        if (node == null) {
            return true;
        }
        return isSymmetric(node.left, node.right);
    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val
                && isSymmetric(p.left, q.right)
                && isSymmetric(p.right, q.left);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(q);
        stack.push(p);
        while (!stack.isEmpty() && !stack.isEmpty()) {
            TreeNode nodeP = stack.pop();
            TreeNode nodeQ = stack.pop();

            if (nodeP == null && nodeQ == null) {
                continue;
            }
            if (nodeP == null || nodeQ == null) {
                return false;
            }

            if (nodeP.val != nodeQ.val) {
                return false;
            }
            stack.push(nodeQ.right);
            stack.push(nodeP.right);

            stack.push(nodeQ.left);
            stack.push(nodeP.left);

        }

        return true;
    }

    public TreeNode tree1() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        return root;
    }

    public TreeNode tree2() {
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

    public TreeNode tree3() {
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


    public boolean sameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val &&
                sameTree(p.left, q.left)
                && sameTree(p.right, q.right);
    }
}
