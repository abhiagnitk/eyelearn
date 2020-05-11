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

    //-----------------------------------------------------------------------------------------------------//

    /*
    2. Given an array of integers, find the subset of non-adjacent elements with the maximum sum.
    Calculate the sum of that subset.
    Input: 2 1 5 8 4
    Output: 11
    Input: 3 5 -7 8 10
    Output: 15
    Practice: https://www.hackerrank.com/challenges/max-array-sum/problem
     */

    // Recursive Implementation

    static int maxSubsetSumRecursive(int[] arr) {
        return maxSubsetSumRecursiveHelper(arr, 0, arr.length);
    }

    static int maxSubsetSumRecursiveHelper(int[] arr, int i, int n) {
        if (i == n - 1) return arr[n - 1];
        if (i == n - 2) return Math.max(arr[n - 1], arr[n - 2]);
        int res = Math.max(maxSubsetSumRecursiveHelper(arr, i + 1, n),
                Math.max(
                        maxSubsetSumRecursiveHelper(arr, i + 2, n) + arr[i],
                        arr[i])
        );
        return res;
    }

    //Recursive Implementation with Memoization
    static int maxSubsetSumRecursiveWithMemoization(int[] arr) {
        int maxArr[] = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            maxArr[i] = Integer.MIN_VALUE;
        return maxSubsetSumRecursiveWithMemoziationHelper(arr, 0, arr.length, maxArr);
    }

    static int maxSubsetSumRecursiveWithMemoziationHelper(int[] arr, int i, int n, int maxArr[]) {
        if(maxArr[i] != Integer.MIN_VALUE) return maxArr[i];
        if (i == n - 1) return maxArr[i] = arr[n - 1];
        if (i == n - 2) return maxArr[i] = Math.max(arr[n - 1], arr[n - 2]);
        int res = Math.max(maxSubsetSumRecursiveWithMemoziationHelper(arr, i + 1, n, maxArr),
                Math.max(
                        maxSubsetSumRecursiveWithMemoziationHelper(arr, i + 2, n, maxArr) + arr[i],
                        arr[i])
        );
        maxArr[i] = res;
        return res;
    }

    //Iterative Implementation
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

    //-----------------------------------------------------------------------------------------------------//

    /*
    3. LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them.
    LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
    LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
    Practice: https://leetcode.com/problems/longest-common-subsequence/
     */

    //Recursive
    public int longestCommonSubsequenceRecursive(String s1, String s2) {
        return lcsHelperRec(s1, s2, 0, 0);
    }

    public int lcsHelperRec(String s1, String s2, int i, int j) {
        if(i >= s1.length() || j >= s2.length()) return 0;

        if(s1.charAt(i) == s2.charAt(j)) {
            return  1 + lcsHelperRec(s1, s2, i + 1, j + 1);
        }

        return Math.max(lcsHelperRec(s1, s2, i + 1, j),
                lcsHelperRec(s1, s2, i, j + 1));
    }

    //Recursive Implementation with Memoization - Top down
    public int longestCommonSubsequenceWithMemoization(String s1, String s2) {
        int table[][] = new int[s1.length()][s2.length()];
        for(int i = 0; i < s1.length(); i++) {
            for(int j = 0; j < s2.length(); j++) {
                table[i][j] = -1;
            }
        }
        return lcsHelper(s1, s2, 0, 0, table);
    }

    public int lcsHelper(String s1, String s2, int i, int j, int table[][]) {
        if(i >= s1.length() || j >= s2.length()) return 0;
        if(table[i][j] != -1) return table[i][j];

        if(s1.charAt(i) == s2.charAt(j)) {
            return table[i][j] = 1 + lcsHelper(s1, s2, i + 1, j + 1, table);
        }

        return table[i][j] = Math.max(lcsHelper(s1, s2, i + 1, j, table),
                lcsHelper(s1, s2, i, j + 1, table));
    }

    //Iterative - Bottom up
    public int longestCommonSubsequenceIterative(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int table[][] = new int[m + 1][n + 1];

        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    table[i][j] = 1 + table[i - 1][j - 1];
                else
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
            }
        }

        return table[m][n];
    }

    //-----------------------------------------------------------------------------------------------------//

    /*
    4. Coin change
    Given a value N, if we want to make change for N cents, and we have infinite supply of each of
    S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.
    For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output is 4.
    For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
    So the output should be 5.
    Practice: https://leetcode.com/problems/coin-change-2/
     */

    //Recursive implementation
    public int changeRecursive(int amount, int[] coins) {
        return changeRecursiveHelper(amount, coins, 0);
    }

    public int changeRecursiveHelper(int amount, int[] coins, int i) {
        if(amount == 0) return 1;
        if(amount < 0 || i >= coins.length) return 0;
        return changeRecursiveHelper(amount - coins[i], coins, i) +
                changeRecursiveHelper(amount, coins, i + 1);

    }

    //Recursive implementation with memoization
    public int changeRecursiveMemoization(int amount, int[] coins) {
        int table[][] = new int[coins.length][amount];
        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j < amount; j++) {
                table[i][j] = -1;
            }
        }
        return changeRecursiveMemoizationHelper(amount, coins, 0, table);
    }

    public int changeRecursiveMemoizationHelper(int amount, int[] coins, int i, int table[][]) {
        if(amount == 0) return 1;
        if(amount < 0 || i >= coins.length) return 0;
        if(table[i][amount - 1] != -1) return table[i][amount - 1];
        return table[i][amount - 1] = changeRecursiveMemoizationHelper(amount - coins[i], coins, i, table) +
                changeRecursiveMemoizationHelper(amount, coins, i + 1, table);

    }

    //Iterative - Bottom up
    public int changeIterative(int amount, int[] coins) {
        int table[] = new int[amount + 1];
        table[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i]; j < amount + 1; j++) {
                table[j]  += table[j - coins[i]];
            }
        }
        return table[amount];
    }

    //-----------------------------------------------------------------------------------------------------//

}
