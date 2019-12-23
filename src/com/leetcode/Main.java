package com.leetcode;

import com.leetcode.binarytree.TreeNode;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String S = "rabbbit";
        String T = "rabbit";
        System.out.println(new Main().getNum(S, T));
        System.out.println(new Main().getNum_dp(S, T));

        if (true) {
            return;
        }

        System.out.println(new Main().uniquePaths(7, 3));

        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new Main().minPathSum(grid));

        int[][] grid1 = {{0, 1}};
        System.out.println(new Main().uniquePathsWithObstacles(grid1));

        int[][] grid2 = {{1, 4, 8, 6, 2, 2, 1, 7}, {4, 7, 3, 1, 4, 5, 5, 1}, {8, 8, 2, 1, 1, 8, 0, 1}, {8, 9, 2, 9, 8, 0, 8, 9}, {5, 7, 5, 7, 1, 8, 5, 5}, {7, 0, 9, 4, 5, 6, 5, 6}, {4, 9, 9, 7, 9, 1, 9, 0}};
        System.out.println(new Main().minPathSum(grid2));

        System.out.println(new Main().uniquePaths(grid2));
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int sale = 0, buy = 0; sale < prices.length; sale++) {
            if (prices[sale] >= prices[buy]) {
                maxProfit = Math.max(prices[sale] - prices[buy], maxProfit);
            } else {
                buy = sale;
            }
        }
        return maxProfit;
    }

    public int getNum_dp(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        int m = s.length();
        int n = t.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        int prev;
        int temp;
        for (int i = m - 1; i >=0; i--) {
            temp = dp[n];
            for (int j = n - 1; j >= 0; j--) {
                prev = temp;
                temp = dp[j];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] = dp[j] + prev;
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[0];
    }


    public int getNum(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        return conquer(s, 0, t, 0);
    }

    Map<String, Integer> map = new HashMap<>();
    public int conquer(String s, int sStart, String t, int tStart) {
        if (t.length() == tStart) {
            return 1;
        }
        if (s.length() == sStart) {
            return 0;
        }
        String key = sStart + "@" + tStart;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int num;
        if (s.charAt(sStart) == t.charAt(tStart)) {
            num = conquer(s, sStart + 1, t, tStart + 1) + conquer(s, sStart + 1, t, tStart);
        } else {
            num =  conquer(s, sStart + 1, t, tStart);
        }
        map.put(key, num);
        return num;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null ||
                root.left == null && root.right == null) {
            return true;
        }

        Queue<TreeNode> leftQ = new LinkedList<>();
        Queue<TreeNode> rightQ = new LinkedList<>();
        leftQ.offer(root.left);
        rightQ.offer(root.right);

        while (!leftQ.isEmpty() && !rightQ.isEmpty()) {
            TreeNode left = leftQ.poll();
            TreeNode right = rightQ.poll();

            if (left.val != right.val) {
                return false;
            }

            if (left.left != null && right.right != null) {
                leftQ.offer(left.left);
                rightQ.offer(right.right);
            } else if (left.left != null && right.right == null
                    || left.left == null && right.right != null) {
                return false;
            }

            if (left.right != null && right.left != null) {
                leftQ.offer(left.right);
                rightQ.offer(right.left);
            } else if (left.right != null && right.left == null
                    || left.right == null && right.left != null) {
                return false;
            }
        }

        return leftQ.isEmpty() && rightQ.isEmpty();
    }

    public static int uniquePaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n]; //
        // 初始化
        dp[0][0] = arr[0][0];
        // 初始化最左边的列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        // 初始化最上边的行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        char[][] c = new char[m][n];
        c[0][0] = '+';

        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
            c[i][0] = '|';
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
            c[0][j] = '-';
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    c[i][j] = '|';
                    dp[i][j] = dp[i - 1][j];
                } else {
                    c[i][j] = '-';
                    dp[i][j] = dp[i][j - 1];
                }
                dp[i][j] += grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if (obstacleGrid[0][0] == 1) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> path = new ArrayList<>();
        TreeNode prev = null;
        TreeNode cur = root;
        int curSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                path.add(cur.val);
                curSum += cur.val;
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right == null && cur.left == null && curSum == sum) {
                ans.add(new ArrayList<>(path));
            }
            if (cur.right == null || cur.right == prev) {
                stack.pop();
                path.remove(path.size() - 1);

                curSum -= cur.val;
                prev = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }

        return ans;
    }

    public void getAnsHelper(TreeNode node, int sum, List<List<Integer>> ans, List<Integer> tmp) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (node.val == sum) {
                tmp.add(node.val);
                ans.add(new ArrayList<>(tmp));
                tmp.remove(tmp.size() - 1);
            }
            return;
        }

        if (node.left == null) {
            tmp.add(node.val);
            getAnsHelper(node.right, sum - node.val, ans, tmp);
            tmp.remove(tmp.size() - 1);
            return;
        }

        if (node.right == null) {
            tmp.add(node.val);
            getAnsHelper(node.left, sum - node.val, ans, tmp);
            tmp.remove(tmp.size() - 1);
            return;
        }


        tmp.add(node.val);
        getAnsHelper(node.right, sum - node.val, ans, tmp);
        tmp.remove(tmp.size() - 1);

        tmp.add(node.val);
        getAnsHelper(node.right, sum - node.val, ans, tmp);
        tmp.remove(tmp.size() - 1);
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        TreeNode cur = root;
        int curNum = 0;
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                curNum += cur.val;
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();

            if (cur.right == null && cur.left == null && sum == curNum) {
                return true;
            }
            if (cur.right == null || cur.right == prev) {
                stack.pop();
                prev = cur;
                curNum -= cur.val;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    static class MyTreeNode {
        TreeNode node;
        int sum;

        public MyTreeNode(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 1;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }

        return level;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (!queue.isEmpty()) {
                level++;
            }
        }

        return level;
    }
}
