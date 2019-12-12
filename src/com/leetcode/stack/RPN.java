package com.leetcode.stack;

import java.util.Stack;

public class RPN {
    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        String[] tokens1 = {"4", "13", "5", "/", "+"};

        System.out.println(evalRPN(tokens));
        System.out.println(evalRPN(tokens1));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isOperator(token)) {
                int right = stack.pop();
                int left = stack.pop();
                int val = eval(token, left, right);
                stack.push(val);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.pop();
    }

    private static boolean isOperator(String operator) {
        return "+".equals(operator)
                || "-".equals(operator)
                || "*".equals(operator)
                || "/".equals(operator);
    }

    private static int eval(String operator, int left, int right) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                    return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            default:
                throw new IllegalArgumentException("");
        }
    }
}
