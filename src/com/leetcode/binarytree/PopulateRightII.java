package com.leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.wang/leetcode-116-Populating-Next-Right-Pointers-in-Each-Node.html
 */
public class PopulateRightII {
    public static void main(String[] args) {

    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;
        Node tail;
        while (cur != null) {
            Node dummy = new Node(-1);
            tail = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = tail.left;
                    tail = tail.next;
                }

                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }

            cur = dummy.next;
        }


        return root;
    }
}
