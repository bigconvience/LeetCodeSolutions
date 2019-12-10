package com.jbp.bst;

import com.jbp.binarytree.TreeNode;
import com.jbp.utils.TreeUtils;

import java.util.Stack;

/**
 * https://leetcode.wang/leetcode-99-Recover-Binary-Search-Tree.html
 */
public class RecoveryBST {
    public static void main(String[] args) {
        new RecoveryBST().case1();
        new RecoveryBST().case2();
        new RecoveryBST().case3();
    }

    public void case1() {
        TreeNode[] nodes = {
                new TreeNode(1),
                new TreeNode(3),
                null, null,
                new TreeNode(2),
                null, null
        };


        TreeNode root = TreeUtils.arraysToTree(nodes);

        recoverTree2_1_1(root);
    }

    public void case2() {
        TreeNode[] nodes = {
                new TreeNode(3),
                new TreeNode(1),
                new TreeNode(4),
                null, null,
                new TreeNode(2),
                null
        };


        TreeNode root = TreeUtils.arraysToTree(nodes);
        recoverTree2_2_1(root);
    }


    public void case3() {
        TreeNode[] nodes = {
                new TreeNode(3),
                new TreeNode(1),
                new TreeNode(4),
                null, null,
                new TreeNode(2),
                null
        };


        TreeNode root = TreeUtils.arraysToTree(nodes);
        recoverTree2_2_2(root);
    }

    public void recoverTree2_2_2(TreeNode root) {
        mFirst = null;
        mSecond = null;
        mPrev = null;
        inorderTraversal1(root);
        swap(mFirst, mSecond);
    }

    private void inorderTraversalMorris(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (mPrev != null && mPrev.val > cur.val) {
                    if (mFirst == null) {
                        mFirst = mPrev;
                    }
                    mSecond = cur;
                }
                mPrev = cur;
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
                    if (mPrev != null && mPrev.val > cur.val) {
                        if (mFirst == null) {
                            mFirst = mPrev;
                        }
                        mSecond = cur;
                    }
                    mPrev = cur;
                    cur = cur.right;
                }
            }
        }
    }

    private void inorderTraversal1(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (mPrev != null && mPrev.val > root.val) {
                if (mFirst == null) {
                    mFirst = mPrev;
                }
                mSecond = root;
            }
            mPrev = root;
            cur = cur.right;
        }
    }

    private void swap(TreeNode first, TreeNode second) {
        if (first == null || second == null) {
            return;
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    TreeNode mFirst = null;
    TreeNode mSecond = null;
    TreeNode mPrev = null;

    public void recoverTree2_2_1(TreeNode root) {
        mFirst = null;
        mSecond = null;
        mPrev = null;
        inorderTraversal(root);
        swap(mFirst, mSecond);
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);
        if (mPrev != null && mPrev.val > root.val) {
            if (mFirst == null) {
                mFirst = mPrev;
            }
            mSecond = root;
        }
        mPrev = root;
        inorderTraversal(root.right);
    }

    public void recoverTree2_1_1(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode leftMax = getMaxOfBst(root.left);
        TreeNode rightMin = getMinOfBst(root.right);

        if (leftMax != null && rightMin != null) {
            if (leftMax.val > rightMin.val) {
                swap(leftMax, rightMin);
            }
        }

        if (rightMin != null) {
            if (rightMin.val < root.val) {
                swap(rightMin, root);
            }
        }

        if (leftMax != null) {
            if (leftMax.val > root.val) {
                swap(leftMax, root);
            }
        }

        recoverTree2_1_1(root.left);
        recoverTree2_1_1(root.right);
    }


    private TreeNode getMaxOfBst(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftMax = getMaxOfBst(root.left);
        TreeNode rightMax = getMaxOfBst(root.right);
        TreeNode max = root;
        if (leftMax != null && leftMax.val > max.val) {
            max = leftMax;
        }

        if (rightMax != null && rightMax.val > max.val) {
            max = rightMax;
        }
        return max;
    }

    private TreeNode getMinOfBst(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftMin = getMinOfBst(root.left);
        TreeNode rightMin = getMinOfBst(root.right);
        TreeNode min = root;

        if (leftMin != null && leftMin.val < min.val) {
            min = leftMin;
        }

        if (rightMin != null && rightMin.val < min.val) {
            min = rightMin;
        }
        return min;
    }
}
