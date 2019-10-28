package com.jbp.practices;

import java.util.Stack;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MinStack {
    public static void main(String[] args) {
        System.out.println(new DivideTwoIntegers().divide(Integer.MIN_VALUE, 2));
    }


    private Stack<Integer> mMinStack;
    private Stack<Integer> mStack;

    /** initialize your data structure here. */
    public MinStack() {
        mMinStack = new Stack<>();
        mStack = new Stack<>();
    }

    public void push(int x) {
        mStack.push(x);
        if (mMinStack.isEmpty()) {
            mMinStack.push(x);
        } else {
            mMinStack.push(Math.min(x, mMinStack.peek()));
        }
    }

    public void pop() {
       mMinStack.pop();
       mStack.pop();
    }

    public int top() {
        return mStack.peek();
    }

    public int getMin() {
        return mMinStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
