package com.jbp.practices;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by jiangbenpeng on 02/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {1, -1};
//        int[] nums = {0, 2};
        int k = 3;
        MaxSlidingWindow maxProduct = new MaxSlidingWindow();
        int[] result = maxProduct.maxSlidingWindow1(nums, k);
        System.out.println(result);

    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 > k && !deque.isEmpty() && (deque.getFirst() == (i - k))) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && (nums[deque.getLast()] < nums[i])) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (i + 1 >= k) {
                ans[i + 1 - k] = nums[deque.getFirst()];
            }
        }

        return ans;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        int[] ans = new int[nums.length - k + 1];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++) {
            if (i + 1 > k) {
                priorityQueue.remove(nums[i - k]);
            }

            priorityQueue.offer(nums[i]);
            if (i + 1 >= k) {
                ans[i + 1 - k] = priorityQueue.peek();
            }
        }

        return ans;

    }
}
