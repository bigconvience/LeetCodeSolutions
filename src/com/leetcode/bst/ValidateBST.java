package com.leetcode.bst;

import com.leetcode.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.wang/leetCode-98-Validate-Binary-Search-Tree.html
 * more practise
 */
public class ValidateBST {
    public static void main(String[] args) {
        new ValidateBST().case1();
    }

    public void case1() {
        long max = Integer.MAX_VALUE + 1;
        long max2 = (long) Integer.MAX_VALUE + 1;
        System.out.println("max: " + max + " max2: " + max2);
    }

    public boolean isValidInorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if (pre != null && pre.val >= cur.val) {
                return false;
            }

            pre = cur;
            cur = cur.right;
        }

        return true;
    }

    public boolean isValidBFS(TreeNode root) {
        if (root == null ||
                root.left == null && root.right == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> minValue = new LinkedList<>();
        Queue<Integer> maxValue = new LinkedList<>();
        queue.offer(root);
        minValue.offer(null);
        maxValue.offer(null);

        while (!queue.isEmpty()) {
            Integer min = minValue.poll();
            Integer max = maxValue.poll();
            TreeNode node = queue.poll();

            if (min != null && min >= node.val) {
                return false;
            }

            if (max != null && max <= node.val) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
                minValue.offer(min);
                maxValue.offer(node.val);
            }

            if (node.right != null) {
                queue.offer(node.right);
                minValue.offer(node.val);
                maxValue.offer(max);
            }
        }

        return true;
    }

    public boolean isValidDFS(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }

        //利用三个栈来保存对应的节点和区间
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> minValues = new LinkedList<>();
        LinkedList<Integer> maxValues = new LinkedList<>();
        //头结点入栈
        TreeNode pNode = root;
        stack.push(pNode);
        minValues.push(null);
        maxValues.push(null);

        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                Integer max = maxValues.peek();
                Integer min = minValues.peek();

                if (max != null && pNode.val >= max) {
                    return false;
                }

                if (min != null && pNode.val <= min) {
                    return false;
                }

                if (pNode.left != null) {
                    stack.push(pNode.left);
                    minValues.push(min);
                    maxValues.push(pNode.val);
                }
                pNode = pNode.left;
            } else {
                TreeNode node = stack.pop();
                Integer max = maxValues.pop();
                Integer min = minValues.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    minValues.push(node.val);
                    maxValues.push(max);
                }
                pNode = node.right;
            }
        }
        return true;
    }

    public boolean isValidBST1(TreeNode root) {
        long max = (long) Integer.MAX_VALUE + 1;
        long min = (long) Integer.MIN_VALUE - 1;
        return getAns(root, min, max);
    }

    public boolean getAns(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }
        return getAns(root.left, min, root.val) && getAns(root.right, root.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null ||
                root.right == null && root.left == null) {
            return true;
        }

        if (isValidBST(root.left)) {
            if (root.left != null) {
                int max = getMaxOfBST(root.left);
                if (root.val <= max) {
                    return false;
                }
            }
        } else {
            return false;
        }

        if (isValidBST(root.right)) {
            if (root.right != null) {
                int min = getMinOfBST(root.right);
                if (root.val >= min) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private int getMaxOfBST(TreeNode node) {
        int max = node.val;
        while (node.right != null) {
            node = node.right;
            //注意此处的条件
            if (max < node.val) {
                max = node.val;
            }
        }

        return max;
    }

    private int getMinOfBST(TreeNode node) {
        int min = node.val;
        while (node.left != null) {
            node = node.left;
            if (min > node.val) {
                min = node.val;
            }
        }
        return min;
    }
}
