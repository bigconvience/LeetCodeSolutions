package com.jbp.practices;


import java.util.*;

public class Atoi {


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(2);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        partition(listNode1, 3);
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode dummyLeft = new ListNode(-1);
        ListNode dummyRight = new ListNode(-1);
        ListNode left = dummyLeft;
        ListNode right = dummyRight;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                left.next = cur;
                left = left.next;
            } else {
                right.next = cur;
                right = right.next;
            }
            cur = cur.next;
        }

        left.next = dummyRight.next;
        right.next = null;
        return dummyLeft.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
