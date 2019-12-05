package com.jbp.tree;

public class SegmentTreeTest {
    public static void main(String[] args) {
        // write your code here
        int[] base = {2, 1, 5, 6, 2, 3};//基础数组中有六个点

        System.out.println(new SegmentTreeTest().largestRectangleArea(base));
    }

    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        //构造线段树
        SegmentTree tree = new SegmentTree(heights);
        tree.build(0);
        return getMaxArea(tree, 0, heights.length - 1, heights);
    }

    public int getMaxArea(SegmentTree tree, int start, int end, int[] heights) {
        if (start == end) {
            return heights[start];
        }

        if (start > end) {
            return Integer.MIN_VALUE;
        }

        int min = tree.query(0, start, end).index;
        int area1 = heights[min] * (end - start + 1);
        int area2 = getMaxArea(tree, start, min - 1, heights);
        int area3 = getMaxArea(tree, min + 1, end, heights);
        return Math.max(Math.max(area1, area2), area3);
    }
}
