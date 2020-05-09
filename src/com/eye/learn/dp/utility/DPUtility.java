package com.eye.learn.dp.utility;

public class DPUtility {

    /*
    1. Write an efficient program to find the sum of contiguous subarray within a
    one-dimensional array of numbers which has the largest sum.
    Input : {-2, -3, 4, -1, -2, 1, 5, -3}
    Output: 7
    Practice : https://leetcode.com/problems/maximum-subarray/
     */

    public int getMaxContiguousSubArraySum(int a[]) {
        int currSum = a[0];
        int maxSum = a[0];

        for (int i = 1; i < a.length; i++) {
            if (currSum > 0)
                currSum += a[i];
            else
                currSum = a[i];
            if (currSum > maxSum)
                maxSum = currSum;
        }
        return maxSum;
    }

    /*
    2. Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset.
    Input: 2 1 5 8 4
    Output: 11
    Input: 3 5 -7 8 10
    Output: 15
    Practice: https://www.hackerrank.com/challenges/max-array-sum/problem
     */
    static int maxSubsetSum(int[] arr) {
        int s1 = arr[0];
        int s2 = Math.max(arr[0], arr[1]);
        int max = s2;
        for (int i = 2; i < arr.length; i++) {
            int temp = Math.max(arr[i] + s1, Math.max(s2, arr[i]));
            max = Math.max(temp, max);
            s1 = Math.max(s1, s2);
            s2 = temp;
        }
        return max;
    }
}
