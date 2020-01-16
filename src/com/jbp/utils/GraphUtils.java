package com.jbp.utils;

import java.util.ArrayList;

public class GraphUtils {
    public static ArrayList<ArrayList<Integer>> matrixToList(int[][] matrix) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> edges = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    edges.add(j);
                }
            }
            list.add(edges);
        }
        return list;
    }

    public static int[][] listToMatrix(ArrayList<ArrayList<Integer>> list) {
        int n = list.size();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                matrix[i][list.get(i).get(j)] = 1;
            }
        }

        return matrix;
    }
}
