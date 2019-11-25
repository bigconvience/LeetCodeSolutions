package com.jbp.linkedlist;

public class RandomNode {

    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        for (Node cur = head; cur != null; cur = cur.next.next) {
            Node node = new Node(cur.val, null, null);
            node.next = cur.next;
            cur.next = node;
        }

        for (Node cur = head;
             cur != null;
             ) {
            Node node = cur.next;
            if (cur.random != null) {
                node.random = cur.random.next;
            }
            cur = node.next;
        }

        Node dummy = new Node(-1, head, null);
        for (Node cur = head, newCur = dummy; cur != null; cur = cur.next) {
            newCur.next = cur.next;
            newCur = newCur.next;
            cur.next = newCur.next;
        }
        return dummy.next;
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
