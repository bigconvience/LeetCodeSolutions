package com.leetcode.bst;

import com.leetcode.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBSTII {
    public static void main(String[] args) {
        case1();
    }


    public static void case1() {
        System.out.println(numTrees(3));


    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }
        return generateHelper(1, n);
    }

    public List<TreeNode> generateHelper(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        if (start == end) {
            ans.add(new TreeNode(start));
            return ans;
        }
        for (int root = start; root <= end; root++) {
            List<TreeNode> leftTree = generateHelper(start, root - 1);
            List<TreeNode> rightTree = generateHelper(root + 1, end);
            for(TreeNode left: leftTree) {
                for (TreeNode right: rightTree) {
                    TreeNode rootNode = new TreeNode(root);
                    rootNode.left = left;
                    rootNode.right = right;
                    ans.add(rootNode);

                }
            }
        }
        return  ans;
    }





    public static int numTrees(int n) {
        int[] f= new int[n+1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int k = 1; k <=i; k++) {
                f[i] += (f[k - 1] * f[i - k]);
            }
        }

        return f[n];
    }

}
