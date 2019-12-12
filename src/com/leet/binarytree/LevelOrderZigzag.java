package com.leet.binarytree;

import com.jbp.utils.ListUtils;
import com.jbp.utils.TreeUtils;

import java.util.*;

public class LevelOrderZigzag {
    public static void main(String[] args) {
        case1();
    }

    public static void case1() {
        TreeNode[] nodes = {
                new TreeNode(3),
                new TreeNode(9),
                new TreeNode(20),
                null, null,
                new TreeNode(15),
                new TreeNode(7)
        };


        TreeNode root = TreeUtils.arraysToTree(nodes);

        ListUtils.printListList((List) levelOrder_1_1(root));

        ListUtils.printListList((List) levelOrder_2_1(root));
        ListUtils.printListList((List) levelOrder_2_2(root));
        ListUtils.printListList((List) levelOrder_3_1(root));
    }

    public static List<List<Integer>> levelOrder_3_1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            while (!s1.isEmpty()) {
                TreeNode node = s1.pop();
                if (node != null) {
                    tmp.add(node.val);
                    s2.push(node.left);
                    s2.push(node.right);
                }
            }
            if (!tmp.isEmpty()) {
                ans.add(tmp);
            }

            tmp = new ArrayList<>();
            while (!s2.isEmpty()) {
                TreeNode node = s2.pop();
                if (node != null) {
                    tmp.add(node.val);
                    s1.push(node.right);
                    s1.push(node.left);
                }
            }
            if (!tmp.isEmpty()) {
                ans.add(tmp);
            }
        }

        return ans;
    }

    public static List<List<Integer>> levelOrder_2_2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> treeNode = new LinkedList<>();
        treeNode.add(root);
        boolean leftToRight = true;
        while (!treeNode.isEmpty()) {
            int levelNum = treeNode.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = treeNode.poll();
                if (cur != null) {
                    if (leftToRight) {
                        subList.add(cur.val);
                    } else {
                        subList.add(0, cur.val);
                    }
                    treeNode.add(cur.left);
                    treeNode.add(cur.right);
                }
            }
            leftToRight = !leftToRight;
            if (subList.size() > 0) {
                ans.add(subList);
            }
        }

        return ans;
    }


    public static List<List<Integer>> levelOrder_2_1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> treeNode = new LinkedList<>();
        Queue<Integer> treeLevel = new LinkedList<>();
        treeNode.add(root);
        treeLevel.add(0);
        while (!treeNode.isEmpty()) {
            TreeNode cur = treeNode.poll();
            int level = treeLevel.poll();
            if (cur != null) {
                if (level >= ans.size()) {
                    ans.add(new ArrayList<>());
                }

                if (level % 2 == 0) {
                    ans.get(level).add(cur.val);
                } else {
                    ans.get(level).add(0, cur.val);
                }
                treeNode.add(cur.left);
                treeLevel.add(level + 1);

                treeNode.add(cur.right);
                treeLevel.add(level + 1);
            }
        }
        return ans;
    }


    public static List<List<Integer>> levelOrder_1_1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans, true);
        return ans;
    }

    public static void dfs(TreeNode node, int level, List<List<Integer>> ans, boolean leftToRight) {
        if (node == null) {
            return;
        }
        if (level >= ans.size()) {
            ans.add(new ArrayList<>());
        }
        if (level % 2 == 0) {
            ans.get(level).add(node.val);
        } else {
            ans.get(level).add(0, node.val);
        }
        dfs(node.left, level + 1, ans, !leftToRight);
        dfs(node.right, level + 1, ans, !leftToRight);
    }

}
