package com.leet.bst;

import com.leet.binarytree.InorderTraversal;
import com.leet.binarytree.TreeNode;
import com.jbp.utils.ListUtils;

import java.util.List;

public class BSTUtils {
    public static void inorder(TreeNode treeNode) {
        List<Integer> ans = InorderTraversal.inorderMorris(treeNode);
        ListUtils.printList(ans);
    }

    public static TreeNode generate(int n) {
        return UniqueBSTII.generateTrees1(n).get(0);
    }
}
