package com.eye.learn.dp.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    //Recursive Implementation with Memoization - Top down
    static int maxSubsetSumRecursiveWithMemoization(int[] arr) {
        int maxArr[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            maxArr[i] = Integer.MIN_VALUE;
        return maxSubsetSumRecursiveWithMemoziationHelper(arr, 0, arr.length, maxArr);
    }

    static int maxSubsetSumRecursiveWithMemoziationHelper(int[] arr, int i, int n, int maxArr[]) {
        if (maxArr[i] != Integer.MIN_VALUE) return maxArr[i];
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

    //Iterative Implementation - Bottom up
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

    //Recursive Implementation
    public int longestCommonSubsequenceRecursive(String s1, String s2) {
        return lcsHelperRec(s1, s2, 0, 0);
    }

    public int lcsHelperRec(String s1, String s2, int i, int j) {
        if (i >= s1.length() || j >= s2.length()) return 0;

        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsHelperRec(s1, s2, i + 1, j + 1);
        }

        return Math.max(lcsHelperRec(s1, s2, i + 1, j),
                lcsHelperRec(s1, s2, i, j + 1));
    }

    //Recursive Implementation with Memoization - Top down
    public int longestCommonSubsequenceWithMemoization(String s1, String s2) {
        int table[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                table[i][j] = -1;
            }
        }
        return lcsHelper(s1, s2, 0, 0, table);
    }

    public int lcsHelper(String s1, String s2, int i, int j, int table[][]) {
        if (i >= s1.length() || j >= s2.length()) return 0;
        if (table[i][j] != -1) return table[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
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

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
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
        if (amount == 0) return 1;
        if (amount < 0 || i >= coins.length) return 0;
        return changeRecursiveHelper(amount - coins[i], coins, i) +
                changeRecursiveHelper(amount, coins, i + 1);

    }

    //Recursive implementation with memoization - Top down
    public int changeRecursiveMemoization(int amount, int[] coins) {
        int table[][] = new int[coins.length][amount];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount; j++) {
                table[i][j] = -1;
            }
        }
        return changeRecursiveMemoizationHelper(amount, coins, 0, table);
    }

    public int changeRecursiveMemoizationHelper(int amount, int[] coins, int i, int table[][]) {
        if (amount == 0) return 1;
        if (amount < 0 || i >= coins.length) return 0;
        if (table[i][amount - 1] != -1) return table[i][amount - 1];
        return table[i][amount - 1] = changeRecursiveMemoizationHelper(amount - coins[i], coins, i, table) +
                changeRecursiveMemoizationHelper(amount, coins, i + 1, table);

    }

    //Iterative - Bottom up
    public int changeIterative(int amount, int[] coins) {
        int table[] = new int[amount + 1];
        table[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount + 1; j++) {
                table[j] += table[j - coins[i]];
            }
        }
        return table[amount];
    }

    //-----------------------------------------------------------------------------------------------------//
    /*
    5. Number of BSTs with n keys
    Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
    Practice: https://leetcode.com/problems/unique-binary-search-trees/
    Note: If n >= 20, int won't be able to hold that large value
     */

    //Recursive Implementation
    public int numTreesRecursive(int n) {
        if (n <= 1) return 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (numTreesRecursive(i - 1) * numTreesRecursive(n - i));
        }
        return sum;
    }

    //Recursion with Memoization - Top down
    public int numTreesMemoization(int n) {
        int table[] = new int[n + 1];
        table[0] = 1;
        table[1] = 1;
        return numTreesMemoizationHelper(n, table);
    }

    public int numTreesMemoizationHelper(int n, int table[]) {
        if (n <= 1) return 1;
        if (table[n] != 0) return table[n];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (numTreesMemoizationHelper(i - 1, table) * numTreesMemoizationHelper(n - i, table));
        }
        return table[n] = sum;
    }

    //Iterative - Bottom up
    public int numTreesIterative(int n) {
        if (n <= 1) return 1;
        int table[] = new int[n + 1];
        table[0] = 1;
        table[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            table[i] = 0;
            for (int j = 1; j <= i; j++) {
                table[i] += table[j - 1] * table[i - j];
            }
        }
        return table[n];
    }


    //-----------------------------------------------------------------------------------------------------//
    /*
    6. Tiling Problem
    Given a “2 x n” board and tiles of size 2 x 1, count the number of ways to tile the given board using the 2 x 1 tiles.
    A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
    Input n = 3 Output: 3
    Input n = 4 Output: 5
    Since the number of ways can be very large,
    return the number of ways modulo (10^9 + 7).
    Practice: https://www.interviewbit.com/problems/tiling-problem/
     */

    //Recursive Implementation
    public static int tilingRecusrive(int A) {
        final int M = 1000000007;
        if (A == 1) return 1;
        if (A == 2) return 2;
        return (tilingRecusrive(A - 1) + tilingRecusrive(A - 2)) % M;
    }

    //Recursion with Memoization - Top down
    public int tilingRecusriveMemoization(int A) {
        if (A == 1) return 1;
        int table[] = new int[A + 1];
        table[1] = 1;
        table[2] = 2;
        return tilingRecursiveMemoizationHelper(A, table);
    }

    public int tilingRecursiveMemoizationHelper(int A, int table[]) {
        final int M = 1000000007;
        if (table[A] != 0) return table[A];
        return table[A] = (tilingRecursiveMemoizationHelper(A - 1, table) +
                tilingRecursiveMemoizationHelper(A - 2, table)) % M;
    }

    //Iterative - Bottom up
    public int tilingIterative(int A) {
        final int M = 1000000007;
        int a1 = 1;
        int a2 = 2;
        if (A == 1) return a1;
        for (int i = 3; i <= A; i++) {
            int temp = (a1 + a2) % M;
            a1 = a2 % M;
            a2 = temp;
        }
        return a2;
    }

    //-----------------------------------------------------------------------------------------------------//

    /*
    7. Number of ways to reach nth stair using upto m steps at a time
    Count the number of ways a person can climb n stairs if the person can climb up to m stairs at a time.
    Input: n = 4, m = 2    Output: 5
    Input: n = 7, m= 3     Output: 44
    Practice: https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem
     */

    //Recursive Implementation
    static int stepPermsRec(int n, int m) {
        if (n == 1) return 1;
        int ways = 0;
        if (n <= m)
            ways = 1;
        for (int i = 1; i <= m && i < n; i++) {
            ways += stepPermsRec(n - i, m);
        }
        return ways;
    }


    //Recursion with memoization - Top down
    static int stepPermsMemoization(int n, int m) {
        int table[] = new int[n + 1];
        table[1] = 1;
        return stepPermsRecHelper(n, m, table);
    }

    static int stepPermsRecHelper(int n, int m, int table[]) {
        if (n == 1) return 1;
        if (table[n] != 0) return table[n];
        int ways = 0;
        if (n <= m)
            ways = 1;

        for (int i = 1; i <= m && i < n; i++) {
            ways += stepPermsRecHelper(n - i, m, table);
        }
        return table[n] = ways;
    }

    //Iterative - Bottom up
    static int stepPermsIterative(int n, int m) {
        if (n == 1) return 1;
        int table[] = new int[n + 1];
        table[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (i <= m)
                table[i] = 1;
            for (int j = 1; j <= m && j < i; j++) {
                table[i] += table[i - j];
            }
        }
        return table[n];
    }

    //Sliding Window Approach - Try on your own

    //-----------------------------------------------------------------------------------------------------//
    /*
    8. Minimum Edit Distance - Given two words word1 and word2, find the minimum number of operations
    required to convert word1 to word2.
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Input: word1 = "sunday", word2 = "saturday"
    Output: 3
    Practice: https://leetcode.com/problems/edit-distance/
     */

    //Recursive implementation
    public int minDistanceRecursive(String s1, String s2) {
        return minDistanceRecHelper(s1, s2, 0, 0);
    }

    public int minDistanceRecHelper(String s1, String s2, int i, int j) {
        if (i >= s1.length()) return s2.length() - j;
        if (j >= s2.length()) return s1.length() - i;

        if (s1.charAt(i) == s2.charAt(j))
            return minDistanceRecHelper(s1, s2, i + 1, j + 1);

        return 1 + Math.min(minDistanceRecHelper(s1, s2, i + 1, j + 1),
                Math.min(minDistanceRecHelper(s1, s2, i, j + 1),
                        minDistanceRecHelper(s1, s2, i + 1, j)));
    }

    //Recursion with memoization - Top down
    public int minDistanceMemoization(String s1, String s2) {
        int table[][] = new int[s1.length()][s2.length()];
        return minDistanceMemHelper(s1, s2, 0, 0, table);
    }

    public int minDistanceMemHelper(String s1, String s2, int i, int j, int table[][]) {
        if (i >= s1.length()) return s2.length() - j;
        if (j >= s2.length()) return s1.length() - i;
        if (table[i][j] != 0) return table[i][j];
        if (s1.charAt(i) == s2.charAt(j))
            return table[i][j] = minDistanceMemHelper(s1, s2, i + 1, j + 1, table);

        return table[i][j] = 1 + Math.min(minDistanceMemHelper(s1, s2, i + 1, j + 1, table),
                Math.min(minDistanceMemHelper(s1, s2, i, j + 1, table),
                        minDistanceMemHelper(s1, s2, i + 1, j, table)));
    }

    //Iterative - Bottom up
    public int minDistanceIterative(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        s1 = "a" + s1;
        s2 = "a" + s2;
        int table[][] = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) table[i][0] = i;
        for (int j = 0; j < n + 1; j++) table[0][j] = j;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    table[i][j] = 1 + Math.min(table[i - 1][j - 1],
                            Math.min(table[i - 1][j],
                                    table[i][j - 1]));
                }
            }
        }
        return table[m][n];
    }

    //-----------------------------------------------------------------------------------------------------//
    /*
    Jump Games
    9a.Given an array of non-negative integers, you are initially positioned at the first index of the array.
    Each element in the array represents your maximum jump length at that position.
    Your goal is to reach the last index in the minimum number of jumps.
    Input: [2,3,1,1,4]  Output: 2
    Input: [1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9]    Output: 3
    Practice: https://leetcode.com/problems/jump-game-ii/
     */

    //Recursive implementation
    public int jumpRec(int[] nums) {
        return jumpRecHelper(nums, 0);
    }

    public int jumpRecHelper(int[] nums, int i) {
        if (i >= nums.length - 1) return 0;
        if (nums[i] <= 0) return Integer.MAX_VALUE;
        if (nums[i] >= nums.length - i) return 1;
        int min = Integer.MAX_VALUE;
        for (int j = i + 1; j <= nums[i] + i && j < nums.length; j++) {
            //System.out.println("jumpRecHelper(" +j+")" );
            min = Math.min(min, jumpRecHelper(nums, j));
        }
        if (min != Integer.MAX_VALUE)
            return 1 + min;
        else
            return min;
    }

    //Recursion with memoization - Top down
    public int jumpMemoization(int[] nums) {
        int table[] = new int[nums.length];
        return jumpMemHelper(nums, 0, table);
    }

    public int jumpMemHelper(int[] nums, int i, int table[]) {
        if (i >= nums.length - 1) return 0;
        if (nums[i] <= 0) return table[i] = Integer.MAX_VALUE;
        if (table[i] != 0) return table[i];
        if (nums[i] >= nums.length - i) return table[i] = 1;
        int min = Integer.MAX_VALUE;
        for (int j = i + 1; j <= nums[i] + i && j < nums.length; j++) {
            //System.out.println("jumpRecHelper(" +j+")" );
            min = Math.min(min, jumpMemHelper(nums, j, table));
        }
        if (min != Integer.MAX_VALUE)
            return table[i] = 1 + min;
        return table[i] = min;
    }

    //Iterative - Bottom up
    public int jumpIterativeFromEnd(int[] nums) {
        int n = nums.length;
        int table[] = new int[n];
        table[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j < n && j <= nums[i] + i; j++) {
                min = Math.min(table[j], min);
            }
            if (min != Integer.MAX_VALUE)
                table[i] = 1 + min;
            else
                table[i] = min;
        }
        return table[0];
    }

    //Iterative - From beginning -- More efficient
    public int jumpIterativeFromBeginning(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int table[] = new int[n];
        int lastIndexTraversed = 1;
        int i = 0;
        while (i < n - 1) {
            if (nums[i] + i >= n - 1)
                return 1 + table[i];

            while (lastIndexTraversed <= nums[i] + i) {
                table[lastIndexTraversed] = 1 + table[i];
                lastIndexTraversed++;
            }
            i++;
        }

        return table[n - 1] + 1;
    }

    /*
    Jump Games
    9b. Count number of ways to jump to reach end
    Given an array of numbers where each element represents the max number of jumps
    that can be made forward from that element. For each array element,
    count number of ways jumps can be made from that element to reach the end of the array.
    If an element is 0, then move cannot be made through that element.
    The element that cannot reach to the end should have a count "-1".
    Question: https://www.geeksforgeeks.org/count-number-ways-jump-reach-end/
    Similar question to practice: https://leetcode.com/problems/jump-game/
     */

    //Recursive implementation
    public boolean canJumpRecursive(int[] nums) {
        if (nums.length == 1) return true;
        return canJumpHelper(nums, 0) != 0 ? true : false;

    }

    public int canJumpHelper(int[] nums, int i) {
        if (i >= nums.length - 1) return 0;
        int sum = 0;
        if (nums[i] + i >= nums.length - 1)
            sum = 1;
        for (int j = i + 1; j <= nums[i] + i && j < nums.length; j++) {
            sum += canJumpHelper(nums, j);
        }
        return sum;
    }

    //Recursive implementation with memoization - Top down
    public boolean canJumpMemoization(int[] nums) {
        if (nums.length == 1) return true;
        int[] table = new int[nums.length - 1];
        return canJumpMemHelper(nums, 0, table) != 0 ? true : false;
    }

    public int canJumpMemHelper(int[] nums, int i, int[] table) {
        if (i >= nums.length - 1 || nums[i] <= 0) return 0;
        if (table[i] != 0) return table[i];
        int sum = 0;
        if (nums[i] + i >= nums.length - 1)
            sum = 1;
        for (int j = i + 1; j <= nums[i] + i && j < nums.length; j++) {
            sum += canJumpMemHelper(nums, j, table);
        }
        return table[i] = sum;
    }

    public boolean canJumpIterative(int[] nums) {
        if (nums.length == 1) return true;
        int n = nums.length;
        int table[] = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int sum = 0;
            if (nums[i] + i >= n - 1)
                sum = 1;
            for (int j = i + 1; j <= nums[i] + i && j < n; j++) {
                sum += table[j];
            }
            table[i] = sum;
        }
        return table[0] != 0;
    }

    //Iterative - Bottom up
    public boolean canJumpIterative1(int[] nums) {
        if (nums.length == 1) return true;
        int n = nums.length;
        boolean table[] = new boolean[n];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] + i >= n - 1)
                table[i] = true;
            else {
                int j = i + 1;
                while (j <= nums[i] + i && j < n) {
                    if (table[j]) {
                        table[i] = true;
                        break;
                    }
                    j++;
                }
            }
        }
        return table[0];
    }

    //-----------------------------------------------------------------------------------------------------//

    /*
    Reach destination in a Maze
    10a. A robot is located at the top-left corner of a m x n grid. The robot can only move either
    down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
    How many possible unique paths are there?
    Input: m = 3, n = 2     Output: 3
    Input: m = 7, n = 3     Output: 28
    Practice: https://leetcode.com/problems/unique-paths/
     */

    //Recursive implementation
    public int uniquePathsRecursive(int m, int n) {
        return uniquePathsRecHelper(m - 1, n - 1, m, n);
    }

    public int uniquePathsRecHelper(int i, int j, int m, int n) {
        if (i == 0 || j == 0) return 1;
        return uniquePathsRecHelper(i - 1, j, m, n) +
                uniquePathsRecHelper(i, j - 1, m, n);
    }

    //Recursion with memoization - Top down
    public int uniquePathsMemoization(int m, int n) {
        int table[][] = new int[m][n];
        return uniquePathsMemHelper(m - 1, n - 1, m, n, table);
    }

    public int uniquePathsMemHelper(int i, int j, int m, int n, int table[][]) {
        if (i == 0 || j == 0) return 1;
        if (table[i][j] != 0) return table[i][j];
        return table[i][j] = uniquePathsMemHelper(i - 1, j, m, n, table) +
                uniquePathsMemHelper(i, j - 1, m, n, table);
    }

    //Iterative - Bottom up
    public int uniquePathsIterative(int m, int n) {
        int table[][] = new int[m][n];
        for (int i = 0; i < m; i++) table[i][0] = 1;
        for (int i = 0; i < n; i++) table[0][i] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                table[i][j] = table[i - 1][j] + table[i][j - 1];
            }
        }
        return table[m - 1][n - 1];
    }

    /*
    Reach destination in a Maze
    10b. A robot is located at the top-left corner of a m x n grid. The robot can only move either down or right
    at any point in time. The robot is trying to reach the bottom-right corner of the grid.
    Now consider if some obstacles are added to the grids. How many unique paths would there be?
    An obstacle and empty space is marked as 1 and 0 respectively in the grid.
    Input: [[0,0,0],[0,1,0],[0,0,0],[0,0,0]]
    Output: 4
    Practice: https://leetcode.com/problems/unique-paths-ii/
     */

    //Recursive implementation
    public int uniquePathsWithObstaclesRecursive(int[][] a) {
        if (a[0][0] == 1) return 0;
        int m = a.length;
        int n = a[0].length;
        return uniquePathsWithObstaclesRecHelper(a, m - 1, n - 1);
    }

    public int uniquePathsWithObstaclesRecHelper(int[][] a, int i, int j) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;
        if (a[i][j] == 1) return 0;
        return uniquePathsWithObstaclesRecHelper(a, i - 1, j) +
                uniquePathsWithObstaclesRecHelper(a, i, j - 1);
    }

    //Recursion with memoization - Top down
    public int uniquePathsWithObstaclesMemoization(int[][] a) {
        if (a[0][0] == 1) return 0;
        int m = a.length;
        int n = a[0].length;
        int table[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(table[i], -1);
        return uniquePathsWithObstaclesMemHelper(a, m - 1, n - 1, table);
    }

    public int uniquePathsWithObstaclesMemHelper(int[][] a, int i, int j, int table[][]) {
        if (i == 0 && j == 0) return 1;
        if (i < 0 || j < 0) return 0;
        if (a[i][j] == 1) return table[i][j] = 0;
        if (table[i][j] != -1) return table[i][j];
        return table[i][j] = uniquePathsWithObstaclesMemHelper(a, i - 1, j, table) +
                uniquePathsWithObstaclesMemHelper(a, i, j - 1, table);
    }

    //Iterative - Bottom up
    public int uniquePathsWithObstaclesIterative(int[][] a) {
        if (a[0][0] == 1) return 0;
        int m = a.length;
        int n = a[0].length;
        int table[][] = new int[m][n];
        table[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (a[i][0] == 0)
                table[i][0] = 1;
            else
                break;
        }
        for (int i = 1; i < n; i++) {
            if (a[0][i] == 0)
                table[0][i] = 1;
            else
                break;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][j] == 0)
                    table[i][j] = table[i - 1][j] + table[i][j - 1];
            }
        }
        return table[m - 1][n - 1];
    }

    /*
    Reach destination in a Maze
    10c. Given a m x n grid filled with non-negative numbers,
    find a path from top left to bottom right which minimizes the sum of all numbers along its path.
    Note: You can only move either down or right at any point in time.
    Input: [[1,3,1],[1,5,1],[4,2,1]]    Output: 7
    Practice: https://leetcode.com/problems/minimum-path-sum/
     */

    //Recursive Implementation
    public int minPathSumRecursive(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return minPathSumRecHelper(grid, m - 1, n - 1);
    }

    public int minPathSumRecHelper(int[][] grid, int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (i == 0) return grid[i][j] + minPathSumRecHelper(grid, i, j - 1);
        if (j == 0) return grid[i][j] + minPathSumRecHelper(grid, i - 1, j);
        return grid[i][j] + Math.min(minPathSumRecHelper(grid, i - 1, j),
                minPathSumRecHelper(grid, i, j - 1));
    }

    //Recursion with Memoization - Top down
    public int minPathSumMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int table[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(table[i], -1);
        }
        return minPathSumMemHelper(grid, m - 1, n - 1, table);
    }

    public int minPathSumMemHelper(int[][] grid, int i, int j, int table[][]) {
        if (i < 0 || j < 0) return 0;
        if (table[i][j] != -1) return table[i][j];
        if (i == 0)
            return table[i][j] = grid[i][j] + minPathSumMemHelper(grid, i, j - 1, table);
        if (j == 0)
            return table[i][j] = grid[i][j] + minPathSumMemHelper(grid, i - 1, j, table);
        return table[i][j] = grid[i][j] + Math.min(minPathSumMemHelper(grid, i - 1, j, table),
                minPathSumMemHelper(grid, i, j - 1, table));
    }

    //Iterative - Bottom up
    public int minPathSumIterative(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int table[][] = new int[m][n];
        table[0][0] = grid[0][0];

        for (int i = 1; i < m; i++)
            table[i][0] = grid[i][0] + table[i - 1][0];

        for (int i = 1; i < n; i++)
            table[0][i] = grid[0][i] + table[0][i - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                table[i][j] = grid[i][j] + Math.min(table[i - 1][j], table[i][j - 1]);
            }
        }
        return table[m - 1][n - 1];
    }

    //-----------------------------------------------------------------------------------------------------//

    /*
    11. Given an input string and a dictionary of words, find out if the input string can be segmented into a
    space-separated sequence of dictionary words.
    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
    Practice: https://leetcode.com/problems/word-break/
     */

    //Recursive implementation
    public boolean wordBreakRecursive(String s, List<String> wordDict) {
        Set<String> words = wordDict.stream().collect(Collectors.toSet());
        return wordBreakHelper(s, words, s.length() - 1);
    }

    public boolean wordBreakHelper(String s, Set<String> words, int i) {
        if (s == null || s.length() == 0) return true;
        if (i < 0) return false;
        String s1 = s.substring(i, s.length());
        if (words.contains(s1)) {
            return wordBreakHelper(s.substring(0, s.length() - s1.length()), words, s.length() - s1.length()) ||
                    wordBreakHelper(s, words, i - 1);
        }
        return wordBreakHelper(s, words, i - 1);
    }

    //Iterative - Bottom up
    public boolean wordBreakIterative(String s, List<String> wordDict) {
        Set<String> words = wordDict.stream().collect(Collectors.toSet());
        if (words.contains(s)) return true;
        int n = s.length();
        boolean table[] = new boolean[n + 1];
        table[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                if (table[j]) {
                    if (words.contains(s.substring(i, j))) {
                        table[i] = true;
                        break;
                    }
                }
            }
        }
        return table[0];
    }

    //-----------------------------------------------------------------------------------------------------//

    /*

     */

    //Recursive Implementation
    static boolean isSubsetSumRec(int a[],
                                  int target, int i) {
        if (target == 0) return true;
        if (target < 0) return false;
        if (i >= a.length) return target == 0;
        return isSubsetSumRec(a, target - a[i], i + 1) || isSubsetSumRec(a, target, i + 1);
    }

    //Iterative implementation - Bottom up
    static boolean isSubsetSumIterative(int a[], int target) {
        if (target == 0) return true;
        int n = a.length;
        boolean table[][] = new boolean[n + 1][target + 1];
        table[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j - a[i - 1] < 0) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = table[i - 1][j] || table[i - 1][j - a[i - 1]];
                }
            }
        }
        return table[n][target];
    }

    //-----------------------------------------------------------------------------------------------------//

    /*
    Longest Increasing Subsequence
    13. Given an unsorted array of integers, find the length of longest increasing subsequence.
    Input: [10,9,2,5,3,7,101,18]
    Output: 4
    Practice: https://leetcode.com/problems/longest-increasing-subsequence/
     */

    //Recursive Implementation
    public int lengthOfLISRec(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, lisHelper(nums, i));
        }
        return max;
    }

    public int lisHelper(int[] nums, int i) {
        if (i >= nums.length) return 0;
        int max = 0;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] < nums[j])
                max = Math.max(max, lisHelper(nums, j));
        }
        return 1 + max;
    }

    //Recursion with memoization - Top down
    public int lengthOfLISMemoization(int[] nums) {
        int max = 0;
        int table[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, lisMemHelper(nums, i, table));
        }
        return max;
    }

    public int lisMemHelper(int[] nums, int i, int table[]) {
        if (i >= nums.length) return 0;
        if (table[i] != 0) return table[i];
        int max = 0;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] < nums[j])
                max = Math.max(max, lisMemHelper(nums, j, table));
        }
        return table[i] = 1 + max;
    }

    //Iterative implementation - Bottom up
    public int lengthOfLISIterative(int[] nums) {
        int table[] = new int[nums.length];
        int max_till_now = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    table[i] = Math.max(table[i], table[j]);
                    if (max_till_now == table[i]) {
                        break;
                    }
                }
            }
            table[i]++;
            if (max_till_now < table[i])
                max_till_now = table[i];
        }
        return max_till_now;
    }
    //-----------------------------------------------------------------------------------------------------//

    /*
    14. Minimum partition sum / Partition Equal Subset Sum
    Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
    such that the sum of elements in both subsets is equal.
    Input: [1, 5, 11, 5]
    Output: true
    Practice: https://leetcode.com/problems/partition-equal-subset-sum/
    Similar question: https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
     */

    //Recursive implementation
    public boolean canPartitionRecursive(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        return canPartitionRecHelper(nums, sum, 0, 0);
    }

    public boolean canPartitionRecHelper(int[] nums, int s, int s1, int i) {
        int diff = Math.abs(s - s1 - s1);
        if (diff == 0) return true;
        if (i >= nums.length) return diff == 0;
        return canPartitionRecHelper(nums, s, s1 + nums[i], i + 1) ||
                canPartitionRecHelper(nums, s, s1, i + 1);
    }

    //Iterative Implementation - Bottom up
    public boolean canPartitionIterative(int[] a) {
        int sum = Arrays.stream(a).sum();
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        int n = a.length;
        boolean table[][] = new boolean[n + 1][sum + 1];
        table[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j - a[i - 1] < 0) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = table[i - 1][j] || table[i - 1][j - a[i - 1]];
                }
            }
        }
        return table[n][sum];
    }

    //-----------------------------------------------------------------------------------------------------//

    /*
    15. Longest Palindromic Subsequence - Given a string s, find the longest palindromic subsequence's length in s.
    Input: "bbbab"  Output: 4  (bbbb)
    Input: "GEEKSFORGEEKS"  Output: 5 (EEKEE)
    Practice:
     */

    //Recursive Implementation
    public int longestPalindromeSubseqRec(String s) {
        return longestPalindromeSubseqRecHelper(s, 0, s.length() - 1);
    }

    public int longestPalindromeSubseqRecHelper(String s, int i, int j) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) != s.charAt(j)) {
            return Math.max(longestPalindromeSubseqRecHelper(s, i + 1, j),
                    longestPalindromeSubseqRecHelper(s, i, j - 1));
        }

        return Math.max(2 + longestPalindromeSubseqRecHelper(s, i + 1, j - 1),
                Math.max(longestPalindromeSubseqRecHelper(s, i + 1, j),
                        longestPalindromeSubseqRecHelper(s, i, j - 1)));
    }

    //Iterative implementation - Bottom Up
    public int longestPalindromeSubseqIterative(String s) {
        int n = s.length();
        int table[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            table[i][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                table[i][j] = Math.max(table[i][j - 1], table[i + 1][j]);
                if (s.charAt(i) == s.charAt(j))
                    table[i][j] = Math.max(table[i][j], 2 + table[i + 1][j - 1]);
            }
        }
        return table[0][n - 1];
    }

    /*
    16. Longest Palindromic Substring - Given a string s, find the longest palindromic substring in s.
     */

    //Iterative - Brute Force
    public String longestPalindromeIterative(String s) {
        int maxLength = 0;
        String maxLenSubString = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = "";
            for (int j = i; j < s.length(); j++) {
                s1 = s1 + s.charAt(j);
                if (isPalindrome(s1) && maxLength < s1.length()) {
                    maxLenSubString = s1;
                    maxLength = s1.length();
                }
            }
        }
        return maxLenSubString;
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    //Recursive implementation
    public String longestPalindromeRecursive(String s) {
        if (s.length() == 0 || s.length() == 1) return s;
        return longestPalindromeRecHelper(s, 0, s.length() - 1);
    }

    public String longestPalindromeRecHelper(String s, int i, int j) {
        if (i == j) return "" + s.charAt(i);
        if (j - i == 1) {
            if (s.charAt(i) == s.charAt(j)) {
                return s.substring(i, j + 1);
            }
            return "" + s.charAt(i);
        }

        if (s.charAt(i) == s.charAt(j)) {
            if (longestPalindromeRecHelper(s, i + 1, j - 1).length() == j - i - 1) {
                return s.substring(i, j + 1);
            }
            return max(longestPalindromeRecHelper(s, i + 1, j),
                    longestPalindromeRecHelper(s, i, j - 1));
        }

        return max(longestPalindromeRecHelper(s, i + 1, j),
                longestPalindromeRecHelper(s, i, j - 1));
    }

    public String max(String s1, String s2) {
        if (s1.length() > s2.length()) return s1;
        return s2;
    }

    //Iterative - Bottom up
    public String longestPalindromeDp(String s) {
        int n = s.length();
        if (n < 2) return s;
        boolean table[][] = new boolean[n][n];
        for (int i = 0; i < n; i++)
            table[i][i] = true;

        int maxLength = 1;
        int start = 0;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                if (table[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    table[i][j] = true;
                    if (k >= maxLength) {
                        maxLength = k;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }
}
