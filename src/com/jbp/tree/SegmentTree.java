package com.jbp.tree;

/**
 * 线段树学习
 *
 * @link = https://zhuanlan.zhihu.com/p/34150142
 *
 * 4N复杂度 https://blog.csdn.net/gl486546/article/details/78243098
 */
public class SegmentTree {

    private int[] base; // 给定数组
    Node[] nodes;// 存储线段树的数组

    public SegmentTree(int[] nums) {
        base = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            base[i] = nums[i];
        }
        //需要 4n 的空间，上边链接给出了原因
        nodes = new Node[base.length * 4];
    }

    public void build(int index)// 构造一颗线段树，传入下标
    {
        Node node = nodes[index];// 取出该下标下的节点
        if (node == null) {// 根节点需要手动创建
            nodes[index] = new Node(0, this.base.length - 1);
            node = nodes[index];
        }
        if (node.start == node.end) {// 如果这个线段的左端点等于右端点则这个点是叶子节点
            node.data = base[node.start];
            node.index = node.start;
        } else { // 否则递归构造左右子树
            int mid = (node.start + node.end) >> 1;// 现在这个线段的中点
            nodes[(index << 1) + 1] = new Node(node.start, mid);// 左孩子线段
            nodes[(index << 1) + 2] = new Node(mid + 1, node.end);// 右孩子线段
            build((index << 1) + 1);// 构造左孩子
            build((index << 1) + 2);// 构造右孩子
            if (nodes[(index << 1) + 1].data <= nodes[(index << 1) + 2].data) {
                node.data = nodes[(index << 1) + 1].data;
                node.index = nodes[(index << 1) + 1].index;
            } else {
                node.data = nodes[(index << 1) + 2].data;
                node.index = nodes[(index << 1) + 2].index;
            }
        }
    }

    public Node query(int index, int start, int end) {
        Node node = nodes[index];
        if (node.start > end || node.end < start)
            return null;// 当前区间和待查询区间没有交集，那么返回一个极大值
        if (node.start >= start && node.end <= end)
            return node;// 如果当前的区间被包含在待查询的区间内则返回当前区间的最小值
        Node left = query((index << 1) + 1, start, end);
        int dataLeft = left == null ? Integer.MAX_VALUE : left.data;
        Node right = query((index << 1) + 2, start, end);
        int dataRight = right == null ? Integer.MAX_VALUE : right.data;
        return dataLeft <= dataRight ? left : right;

    }


    public static class Node// 节点
    {
        int start;// 区间的左端点
        int end;// 区间的右端点
        int data;// 该区间的最小值
        int index; // 该节点最小值对应数组的下标

        public Node(int start, int end)// 构造方法中传入左端点和右端点
        {
            this.start = start;
            this.end = end;
        }
    }
}
