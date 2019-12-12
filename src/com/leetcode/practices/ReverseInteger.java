package com.leetcode.practices;


/**
 * Created by jiangbenpeng on 9/3/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int str = -123;
        Answer answer = new Answer();
        System.out.println(answer.reverse(str));
    }

    static class Answer {

        public int reverse(int x) {
            int result = 0;

            while (x != 0) {
                int next = result * 10 + x % 10;
                if (next / 10 != result) {
                    return 0;
                }
                result = next;
                x /= 10;
            }

            return result;
        }
    }
}
