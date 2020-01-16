package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class RandomNode {

    public static void main(String[] args) {

    }
    public Node copyRandomList1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1, null, null);
        Node cur = head, prev = dummy;

        while (cur != null) {
            if (map.containsKey(cur)) {
                prev.next = map.get(cur);
            } else {
                Node next = new Node(cur.val, null, null);
                map.put(cur, next);
                prev.next = next;
            }

            prev = prev.next;
            prev.random = cur.random;

            if (prev.random == null) {
                continue;
            }

            if (map.containsKey(prev.random)) {
                prev.random = map.get(prev.random);
            } else {
                Node random = new Node(prev.random.val, null, null);
                map.put(prev.random, random);
                prev.random = random;
            }

            cur = cur.next;
        }

        return dummy.next;
    }


    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node l1 = head, l2;
        while (l1 != null) {
            l2 = new Node(l1.val, l1.next, null);
            l1.next = l2;
            l1 = l2.next;
        }

        l1  = head;
        while (l1 != null) {
            l2 = l1.next;
            l2.random = l1.random != null ? l1.random.next : null;
            l1 = l2.next;
        }

        l1 = head;
        Node l2Head = l1.next;
        while (l1 != null) {
            l2 = l1.next;
            l1.next = l2.next;
            l2.next = l1.next != null ? l1.next.next : null;
            l1 = l1.next;
        }

        return l2Head;
    }

    public class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
