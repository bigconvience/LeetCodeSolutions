package com.leet.binarytree;

import com.jbp.utils.ListUtils;
import com.jbp.utils.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
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
    }

    public static List<List<Integer>> levelOrder_2_2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> treeNode = new LinkedList<>();
        treeNode.add(root);
        while (!treeNode.isEmpty()) {
            int levelNum = treeNode.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = treeNode.poll();
                if (cur != null) {
                    subList.add(cur.val);
                    treeNode.add(cur.left);
                    treeNode.add(cur.right);
                }
            }
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

                ans.get(level).add(cur.val);

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
        levelOrder(root, 1, ans);
        return ans;
    }

    public static void levelOrder(TreeNode node, int level, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        if (level > ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(level - 1).add(node.val);
        levelOrder(node.left, level + 1, ans);
        levelOrder(node.right, level + 1, ans);
    }

}
