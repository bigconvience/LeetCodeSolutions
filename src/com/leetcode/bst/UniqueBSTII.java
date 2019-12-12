package com.leetcode.bst;

import com.leetcode.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBSTII {
    public static void main(String[] args) {
        case1();
    }


    public static void case1() {
        List<TreeNode> nodeList1 = generateTrees1(3);

        List<TreeNode> nodeList2 = generateTrees2(3);

        List<TreeNode> nodeList3 = generateTrees3(3);
    }

    public static List<TreeNode> generateTrees3(int n) {
        List<TreeNode> pre = new ArrayList<TreeNode>();
        if (n == 0) {
            return pre;
        }
        pre.add(null);
        //每次增加一个数字
        for (int i = 1; i <= n; i++) {
            List<TreeNode> cur = new ArrayList<TreeNode>();
            //遍历之前的所有解
            for (TreeNode root : pre) {
                //插入到根节点
                TreeNode insert = new TreeNode(i);
                insert.left = root;
                cur.add(insert);
                //插入到右孩子，右孩子的右孩子...最多找 n 次孩子
                for (int j = 0; j <= n; j++) {
                    TreeNode root_copy = treeCopy(root); //复制当前的树
                    TreeNode right = root_copy; //找到要插入右孩子的位置
                    int k = 0;
                    //遍历 j 次找右孩子
                    for (; k < j; k++) {
                        if (right == null)
                            break;
                        right = right.right;
                    }
                    //到达 null 提前结束
                    if (right == null)
                        break;
                    //保存当前右孩子的位置的子树作为插入节点的左孩子
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right = insert; //右孩子是插入的节点
                    insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
                    //加入结果中
                    cur.add(root_copy);
                }
            }
            pre = cur;

        }
        return pre;
    }

    private static TreeNode treeCopy(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode root = new TreeNode(node.val);
        root.left = treeCopy(node.left);
        root.right = treeCopy(node.right);
        return root;
    }

    public static List<TreeNode> generateTrees2(int n) {
        List<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<>();
            for (int root = 1; root <= len; root++) {
                List<TreeNode> leftTrees = dp[root - 1];
                List<TreeNode> rightTrees = dp[len - root];
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightNode : rightTrees) {
                        TreeNode rootNode = new TreeNode(root);
                        rootNode.left = leftTree;
                        rootNode.right = cloneTree(rightNode, root);
                        dp[len].add(rootNode);
                    }
                }
            }
        }

        return dp[n];
    }

    private static TreeNode cloneTree(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }
        TreeNode root = new TreeNode(node.val + offset);
        root.left = cloneTree(node.left, offset);
        root.right = cloneTree(node.right, offset);
        return root;
    }

    public static List<TreeNode> generateTrees1(int n) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (n == 0) {
            return ans;
        }
        return getAns(1, n);
    }

    private static List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        if (start == end) {
            ans.add(new TreeNode(start));
            return ans;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = getAns(start, i - 1);
            List<TreeNode> rightTrees = getAns(i + 1, end);

            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    ans.add(root);
                }
            }
        }

        return ans;
    }

}
