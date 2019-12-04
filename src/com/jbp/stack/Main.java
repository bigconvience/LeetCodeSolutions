package com.jbp.stack;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String a = "[[]]}";
        System.out.println(isValid(a));
    }

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        } else if (s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (stack.isEmpty()) {
                if (chr == '('
                        || chr == '['
                        || chr == '{') {
                    stack.push(chr);
                } else {
                    return false;
                }
            } else {
                char top = stack.peek();
                if (chr == ')' || chr == '}' || chr == ']') {
                    if ((top == '(' && chr == ')')
                            || (top == '[' && chr == ']')
                            || (top == '{' && chr == '}')
                    ) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    stack.push(chr);
                }

            }
        }
        return stack.isEmpty();
    }
}
