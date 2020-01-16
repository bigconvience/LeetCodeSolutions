package com.introduction.graph;

import com.jbp.utils.GraphUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
 */
public class BFS {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int nov = sc.nextInt();
            int edg = sc.nextInt();

            for (int i = 0; i < nov; i++)
                list.add(i, new ArrayList<Integer>());

            for (int i = 1; i <= edg; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            boolean vis[] = new boolean[nov];
            for (int i = 0; i < nov; i++)
                vis[i] = false;

            System.out.println( " ");

            new BFS().bfs(0, list, vis, nov);
            new BFS().bfs(0, GraphUtils.listToMatrix(list));
            System.out.println();
        }
    }

    private void bfs(int s, int[][] matrix) {
        int n = matrix.length;
        Color[] color = new Color[n];
        int[] d = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = Color.WHITE;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        color[s] = Color.GRAY;
        d[s] = 0;
        p[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            for (int v = 0; v < n; v++) {
                if (matrix[u][v] == 0) {
                    continue;
                }
                if (color[v] == Color.WHITE) {
                    d[v] = d[u] + 1;
                    p[s] = u;
                    color[v] = Color.GRAY;
                    queue.offer(v);
                }
            }
            color[u] = Color.BLACK;
        }

        System.out.println( " ");
    }

    private void bfs(int s, ArrayList<ArrayList<Integer>> list) {
        int n = list.size();
        Color[] color = new Color[n];
        int[] d = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = Color.WHITE;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        color[s] = Color.GRAY;
        d[s] = 0;
        p[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            for (Integer v : list.get(u)) {
                if (color[v] == Color.WHITE) {
                    d[v] = d[u] + 1;
                    p[s] = u;
                    color[v] = Color.GRAY;
                    queue.offer(v);
                }
            }
            color[u] = Color.BLACK;
        }

        System.out.println( " ");
    }

    enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    static void bfs(int s, ArrayList<ArrayList<Integer>> list, boolean vis[], int nov) {
        // add your code here
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        vis[s] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");

            ArrayList<Integer> edges = list.get(u);
            for (Integer v : edges) {
                if (!vis[v]) {
                    vis[v] = true;
                    queue.add(v);
                }
            }
        }
        System.out.println( " ");
    }
}
