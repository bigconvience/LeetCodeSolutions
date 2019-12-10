package com.jbp.bst;

import com.jbp.binarytree.TreeNode;
import com.jbp.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class BSTGenerate {
    public static void main(String[] args) {
        case1();
    }



    public static void case1() {
       List<TreeNode> nodeList =  generateTrees1(3);
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

            for (TreeNode leftTree: leftTrees) {
                for (TreeNode rightTree: rightTrees) {
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
