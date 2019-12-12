package com.leet.bst;

import com.leet.binarytree.TreeNode;

public class BST2Linked {
    public static void main(String[] args) {
       new BST2Linked().case1();
    }

    public void case1() {
        int n = 9;
        TreeNode bst = BSTUtils.generate(n);
        BSTUtils.inorder(bst);


    }
}
