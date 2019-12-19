package com.leetcode.bst;

import com.jbp.utils.ListNode;
import com.leetcode.binarytree.TreeNode;

public class ListToBST {
    public static void main(String[] args) {
        new ListToBST().case1();
    }

    public void case1() {
        int[] nums = {-10,-3,0,5,9};
        ListNode list = ListNode.arraysToList(nums);
        sortedListToBST(list);
    }

    ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        cur = head;
        int end = 0;
        while (head != null) {
            end++;
            head = head.next;
        }

        return getAns(0, end);
    }

    public TreeNode getAns(int start, int end) {
        if (start >= end) {
            return null;
        }

        int mid = (start + end) >>> 1;
        TreeNode left = getAns(start, mid);

        TreeNode root = new TreeNode(cur.val);
        root.left = left;
        cur = cur.next;
        root.right = getAns(mid + 1, end);
        return root;
    }
}
