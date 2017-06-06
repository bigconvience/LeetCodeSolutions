package com.jbp.leetcode;

/**
 * Created by jiangbenpeng on 01/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(new DivideTwoIntegers().divide(Integer.MIN_VALUE, 2));
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (divisor == -1 && dividend == Integer.MIN_VALUE)) {
            return Integer.MAX_VALUE;
        }

        if (divisor == 1) {
            return dividend;
        }

        long tmpDvd = Math.abs((long)dividend);
        long tmpDvr = Math.abs((long)divisor);
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        int result = 0;


        while (tmpDvd >= tmpDvr) {
            int shiftNums = 0;

            while (tmpDvd >= (tmpDvr << shiftNums)) {
                shiftNums++;
            }

            result += (1 << (shiftNums - 1));
            tmpDvd -= (tmpDvr << (shiftNums -1));
        }

        if (sign == 1) {
            return result;
        } else {
            return -result;
        }
    }
}
