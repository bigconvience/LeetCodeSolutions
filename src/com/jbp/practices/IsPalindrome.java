package com.jbp.practices;

/**
 * Created by jiangbenpeng on 06/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println("isPalindrome: " + new IsPalindrome().isPalindrome(121));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }

        while (x != 0) {
            int right = x % 10;
            int left = x / div;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}
