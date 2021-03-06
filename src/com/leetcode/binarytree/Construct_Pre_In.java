package com.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.wang/leetcode-106-Construct-Binary-Tree-from-Inorder-and-Postorder-Traversal.html
 */
public class Construct_Pre_In {
    public static void main(String[] args) {

    }

    public void case1() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] postorder = {9, 3, 15, 20, 7};

    }

    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree_1(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree_1(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree_1(int[] preorder, int pStart, int pEnd,
                               int[] inorder, int iStart, int iEnd) {
        if (pStart >= pEnd) {
            return null;
        }

        int rootVal = preorder[pStart];
        TreeNode root = new TreeNode(rootVal);
        int iRootIndex = indexMap.get(rootVal);
        int leftNum = iRootIndex - iStart;
        root.left = buildTree_1(preorder, pStart + 1, pStart + leftNum + 1,
                inorder, iStart, iRootIndex);

        root.right = buildTree_1(preorder, pStart + leftNum + 1, pEnd,
                inorder, iRootIndex + 1, iEnd);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int pStart, int pEnd,
                               int[] inorder, int iStart, int iEnd) {
        if (pStart >= pEnd) {
            return null;
        }

        int rootVal = preorder[pStart];
        TreeNode root = new TreeNode(rootVal);
        int iRootIndex = iStart;
        for (int i = iStart; i < iEnd; i++) {
            if (inorder[i] == rootVal) {
                iRootIndex = i;
                break;
            }
        }

        int leftNum = iRootIndex - iStart;

        root.left = buildTree(preorder, pStart + 1, pStart + leftNum + 1,
                inorder, iStart, iRootIndex);

        root.right = buildTree(preorder, pStart + leftNum + 1, pEnd,
                inorder, iRootIndex + 1, iEnd);
        return root;
    }
}
