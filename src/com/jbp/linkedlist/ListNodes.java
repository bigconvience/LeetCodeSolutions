package com.jbp.linkedlist;


import com.jbp.utils.ListNode;
import com.jbp.utils.ListUtils;

import java.util.*;

public class ListNodes {


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

//        partition(listNode1, 3);

        ListUtils.printList(reverseKGroup1(listNode1, 2));
    }


    public void reorderList(ListNode head) {

    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow, fast, slow1;
        slow = fast = slow1 = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                while (slow != slow1) {
                    slow = slow.next;
                    slow1 = slow1.next;
                }
                return slow;
            }
        }

        return null;
    }

    public static ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k < 2)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        for (ListNode end = head; end != null; end = prev.next) {
            for (int i = 1; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            prev = reverse(prev, prev.next, end);
        }

        return dummy.next;
    }

    public static ListNode reverse(ListNode prev, ListNode begin, ListNode end) {
        ListNode end_next = end.next;
        ListNode pre = end_next;
        for (ListNode cur = begin; cur != end.next;) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        prev.next = end;
        return begin;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2)
            return head;

        ListNode next_group = head;
        for (int i = 0; i < k; i++) {
            if (next_group != null) {
                next_group = next_group.next;
            } else {
                return head;
            }

        }
        ListNode new_next_group = reverseKGroup(next_group, k);

        ListNode prev = new_next_group;
        ListNode cur = head;
        while(cur != next_group) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = prev.next;
        while (cur != null
                && cur.next != null) {
            prev.next = cur.next;
            ListNode next = cur.next.next;
            cur.next.next = cur;
            cur.next = next;

            prev = cur;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
       ListNode dummy = new ListNode(-1);
       dummy.next = head;
       ListNode pre = dummy;
       ListNode cur = dummy;
       for (int i = 0; i < n; i++) {
           cur = cur.next;
       }

       while (cur.next != null) {
           pre = pre.next;
           cur = cur.next;
       }

       pre.next = pre.next.next;

        return dummy.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode end = head;
        int i = 1;
        while (end.next != null) {
            end = end.next;
            i++;
        }
        end.next = head;
        k = i - k % i;
        ListNode end2 = end;
        for (int j = 0; j < k; i++) {
            end2 = end2.next;
        }

        head = end2.next;
        end2.next = null;
        return head;
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = head.next;
        if (p.val != head.val) {
            head.next = deleteDuplicates(p);
            return head;
        } else {
            while (p != null && head.val == p.val) {
                p = p.next;
            }
            return p;
        }
    }

    public void recur(ListNode pre, ListNode cur) {
        if (cur == null) {
            return;
        }

        if (pre.val == cur.val) {
            pre.next = cur.next;
            recur(pre, cur.next);
        } else {
            recur(pre.next, cur.next);
        }
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

}
