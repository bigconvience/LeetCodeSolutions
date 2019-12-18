package com.leetcode.bst;

import com.leetcode.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArrayToBST {
    public static void main(String[] args) {
        new ArrayToBST().case1();
        new ArrayToBST().case2();
    }

    public void case1() {
        int[] s = {-10,-3,0,5,9};
        sortedArrayToBST_BFS(s);
    }

    public void case2() {
        int[] s = {-10,-3,0,5,9};
        sortedArrayToBST_DFS(s);
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length);
    }

    public TreeNode buildBST(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, start, mid);
        root.right = buildBST(nums, mid + 1, end);
        return root;
    }

    public TreeNode sortedArrayToBST_BFS(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        Queue<MyTreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(0);
        MyTreeNode myNode = new MyTreeNode(root, 0, nums.length);
        queue.offer(myNode);
        while (!queue.isEmpty()) {
            myNode = queue.poll();

            int start = myNode.start;
            int end = myNode.end;
            int mid = (start + end) >>> 1;

            TreeNode cur = myNode.root;
            cur.val = nums[mid];
            if (start < mid ) {
                cur.left = new TreeNode(0);
                queue.offer(new MyTreeNode(cur.left, start, mid));
            }
            if (mid + 1 < end) {
                cur.right = new TreeNode(0);
                queue.offer(new MyTreeNode(cur.right, mid + 1, end));
            }
        }

        return root;
    }

    public TreeNode sortedArrayToBST_DFS(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        Stack<MyTreeNode> stack = new Stack<>();
        int start = 0;
        int end = nums.length;
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode cur = root;
        stack.push(new MyTreeNode(cur, start, end));
        while (1 < end - start || !stack.isEmpty()) {
            while (1 < end - start) {
                end = (start + end) >>> 1;
                mid = (start + end) >>> 1;
                cur.left = new TreeNode(nums[mid]);
                cur = cur.left;
                stack.push(new MyTreeNode(cur, start, end));
            }

            MyTreeNode node = stack.pop();
            cur = node.root;
            start = node.start;
            end = node.end;
            mid = (start + end) >>> 1;
            start = mid + 1;
            if (start < end) {
                mid = (start + end) >>> 1;
                cur.right = new TreeNode(nums[mid]);
                cur = cur.right;
                stack.push(new MyTreeNode(cur, start, end));
            }
        }

        return root;
    }

    public static class MyTreeNode {
        TreeNode root;
        int start;
        int end;

        public MyTreeNode(TreeNode root, int start, int end) {
            this.root = root;
            this.start = start;
            this.end = end;
        }
    }
}
