package com.ms.arrays;

/**
 * https://zhuanlan.zhihu.com/p/55993035
 */
public class SentenceReverse {
    public static void main(String[] args) {
        char[] input = "this is a book".toCharArray();
        convert(input);
    }

    public static void convert(char[] input) {
        if (input.length == 0) {
            return;
        }
        reverse(input, 0, input.length - 1);
        int i = 0;
        while (i < input.length) {
            while (input[i] == ' ') {
                i++;
            }

            int j = i + 1;
            while (j < input.length && input[j] != ' ') {
                j++;
            }

            reverse(input, i, j - 1);
            i = j + 1;
        }

        System.out.println(input);
    }

    public static void reverse(char[] input, int start, int end) {
        while (start < end) {
            swap(input, start++, end--);
        }
    }

    public static void swap(char[] input, int start, int end) {
        char tmp = input[start];
        input[start] = input[end];
        input[end] = tmp;
    }


}
