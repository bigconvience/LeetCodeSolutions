package com.leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.wang/leetcode-116-Populating-Next-Right-Pointers-in-Each-Node.html
 */
public class PopulateRight {
    public static void main(String[] args) {

    }

    public Node connect_1(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        Node prev =root;
        Node cur = null;
        while (prev.left != null) {
            if (cur == null) {
                prev.left.next = prev.right;
                prev = start.left;
                cur = start.right;
                start = prev;
            } else {
                prev.left.next = prev.right;
                prev.right.next = cur.left;
                prev = prev.next;
                cur = cur.next;
            }
        }
        return root;
    }


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            Node pre = null;
            for (int i = 0; i < levelNum; i++) {
                Node cur = queue.poll();
                if (i > 0) {
                    pre.next = cur;
                    cur.next = null;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                pre = cur;
            }
        }

        return root;
    }
}
