package com.jbp.binarytree;

import java.util.*;

/**
 * https://leetcode.wang/leetcode-145-Binary-Tree-Postorder-Traversal.html?q=traversal
 */
public class PostOrderTraversal {
    public static void main(String[] args) {
        case2();
    }


    public static void case2() {
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

        System.out.println(postorder(node1));
        System.out.println(postorder1(node1));
        System.out.println(postorder2(node1));
        System.out.println(postorder3(node1));
        System.out.println(postorder_3_1(node1));
        System.out.println(postOrder_4_1(node1));
    }

    public static List<Integer> postOrder_4_1(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                }

                if (prev.right == cur) {
                    prev.right = null;
                    TreeNode head = reverseList(cur.left);
                    revereList(head, ans);
                    cur = cur.right;
                }
            }
        }

        TreeNode head = reverseList(root);
        revereList(head, ans);
        return ans;
    }

    private static void revereList(TreeNode head, List<Integer> ans) {
        TreeNode cur = head;
        TreeNode prev = null;
        while (cur != null) {
            ans.add(cur.val);
            TreeNode tmp = cur.right;
            cur.right = prev;
            prev = cur;
            cur = tmp;
        }
    }

    private static TreeNode reverseList(TreeNode head) {
        TreeNode tail = head;
        head = head.right;
        tail.right = null;
        while (head != null) {
            TreeNode tmp = head.right;
            head.right = tail;
            tail = head;
            head = tmp;
        }

        return tail;
    }


    public static List<Integer> postorder_3_1(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                ans.addFirst(cur.val);
                stack.push(cur);
                cur = cur.right;
           } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }

        return ans;
    }



    /**
     * https://leetcode.wang/leetcode-145-Binary-Tree-Postorder-Traversal.html#%E6%80%9D%E6%83%B3%E4%B8%89
     * @param root
     * @return
     */
    public static List<Integer> postorder3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            if (top == null) {
                continue;
            }

            if (!stack.isEmpty() && stack.peek() == top) {
                stack.push(top.right);
                stack.push(top.right);
                stack.push(top.left);
                stack.push(top.left);
            } else {
                ans.add(top.val);
            }
        }


        return ans;
    }

    public static List<Integer> postorder2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode tmp = stack.peek();
            if (tmp.right != null && tmp.right != last) {
                cur = tmp.right;
            } else {
                ans.add(tmp.val);
                last = tmp;
                stack.pop();
            }
        }

        return ans;
    }


    public static List<Integer> postorder1(TreeNode root) {
        TreeNode cur = root;
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null && !set.contains(cur)) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right == null || set.contains(cur)) {
                set.add(cur);
                ans.add(cur.val);
                stack.pop();
                if (stack.isEmpty()) {
                    return ans;
                }
                cur = stack.peek().right;
            } else {
                set.add(cur);
                cur = cur.right;
            }

        }
        return ans;
    }

    public static List<Integer> postorder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    public static void traversal(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        traversal(node.left, ans);
        traversal(node.right, ans);
        ans.add(node.val);
    }
}
