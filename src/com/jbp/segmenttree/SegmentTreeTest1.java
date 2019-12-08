package com.jbp.segmenttree;

public class SegmentTreeTest1 {
    public static void main(String[] args) {
        // write your code here
        int[] base = {5,9,7,4,6,1};//基础数组中有六个点

        new SegmentTreeTest1().largestRectangleArea(base);
    }

    public void largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return;
        }
        //构造线段树
        SegmentTree tree = new SegmentTree(heights);
        tree.build(0);

        System.out.println("a");
    }

}
