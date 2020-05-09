package com.eye.learn.dp.utility;

public class DPUtility {

    /*
    1. Write an efficient program to find the sum of contiguous subarray within a
    one-dimensional array of numbers which has the largest sum.
    Input : {-2, -3, 4, -1, -2, 1, 5, -3}
    Output: 7
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
}
