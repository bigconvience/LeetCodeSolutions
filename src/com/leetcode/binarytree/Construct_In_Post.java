package com.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.wang/leetcode-106-Construct-Binary-Tree-from-Inorder-and-Postorder-Traversal.html
 */
public class Construct_In_Post {
    public static void main(String[] args) {
        new Construct_In_Post().case1();
    }

    public void case1() {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        buildTree(inorder, postorder);
    }

    Map<Integer, Integer> indexMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
           indexMap.put(inorder[i], i);
        }

        return buildTreeHelper(inorder, 0, inorder.length,
                postorder, 0, postorder.length);
    }

    private TreeNode buildTreeHelper(int[] inorder, int iStart, int iEnd,
                                     int[] postorder, int pStart, int pEnd) {
        if (iStart >= iEnd) {
            return null;
        }

        int rootVal = postorder[pEnd - 1];
        TreeNode root = new TreeNode(rootVal);
        int iRootIndex = indexMap.get(rootVal);
        int leftNum = iRootIndex - iStart;

        root.left = buildTreeHelper(inorder, iStart, iRootIndex, postorder, pStart, pStart + leftNum);
        root.right = buildTreeHelper(inorder, iRootIndex + 1, iEnd, postorder, pStart + leftNum, pEnd - 1);

        return root;
    }
}
