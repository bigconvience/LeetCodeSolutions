package com.jbp.utils;

import java.util.Formatter;

public class MatrixUtils {
    /**
     * 打印矩阵
     * @param matrix
     */
    public static void print(int[][] matrix) {
        if (isEmpty(matrix)) {
            System.out.println("Empty");
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("Column:" + i + " ");
            Formatter f1 = new Formatter(System.out);
            for (int j = 0; j < matrix[0].length; j++) {
                f1.format("%1$-10d", matrix[i][j]);
            }
            System.out.println("");
        }
        System.out.println("---end---");
    }


    public static boolean isEmpty(int[][] matrix) {
       return  (matrix == null || matrix.length == 0);
    }
}
