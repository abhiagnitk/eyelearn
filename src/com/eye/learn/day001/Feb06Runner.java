package com.eye.learn.day001;

import java.util.Scanner;

public class Feb06Runner {

    public static void main(String args[]) {

        while(true) {
            System.out.println("Enter your choice :");
            System.out.println("1)Factorial Recursive \t 2)Factorial Iterative \t " +
                    "3)SumOfDigits Recursive \t 4)SumOfDigits Iterative \t " +
                    "5)Fibonacci Recursive \t 6)Fibonacci Iterative \t 0)Terminate");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            switch(n) {

                case 0:
                    System.out.println("Terminating");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("Enter a non-negative number");
                    int n1 = sc.nextInt();
                    System.out.println(factorialRecursive(n1));
                    break;

                case 2:
                    System.out.println("Enter a non-negative number");
                    int n2 = sc.nextInt();
                    System.out.println(factorialIterative(n2));
                    break;

                case 3:
                    System.out.println("Enter a non-negative number");
                    int n3 = sc.nextInt();
                    System.out.println(sumOfDigitsRecursive(n3));
                    break;

                case 4:
                    System.out.println("Enter a non-negative number");
                    int n4 = sc.nextInt();
                    System.out.println(sumOfDigitsIterative(n4));
                    break;

                case 5:
                    System.out.println("Enter a non-negative number");
                    int n5 = sc.nextInt();
                    printArr(fibonacciSeriesRecursive(n5));
                    break;

                case 6:
                    System.out.println("Enter a non-negative number");
                    int n6 = sc.nextInt();
                    printArr(fibonacciSeriesIterative(n6));
                    break;

                default:
                    System.out.println("Incorrect entry. Try Again");
                    break;
            }
        }
    }

    public static int factorialRecursive(int n) {
        if(n <= 0) return 1;
        return n * factorialRecursive(n - 1);
    }

    public static int factorialIterative(int n) {
        if(n <= 0) return 1;
        int f = 1;
        for(int i = 2; i <= n; i++) {
            f = f * i;
        }
        return f;
    }

    public static int sumOfDigitsIterative(int num) {
        int sum = 0;
        while(num > 0) {
            int r = num % 10;
            num = num / 10;
            sum = sum + r;
        }
        return sum;
    }

    public static int sumOfDigitsRecursive(int num) {
        if(num == 0) return 0;
        return (num % 10) + sumOfDigitsRecursive(num / 10);
    }

    public static int fibonacciRecursive(int n, int table[]) {
        if( n == 0 || n == 1) return table[n] = 1;
        return table[n] = fibonacciRecursive(n - 1, table) + fibonacciRecursive(n - 2, table);
    }

    public static int[] fibonacciSeriesIterative(int n) {
        int table[] = new int[n + 1];
        table[0] = 1;
        table[1] = 1;

        for(int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }

        return table;
    }

    public static int[] fibonacciSeriesRecursive(int n) {
        int table[] = new int[n + 1];
        table[0] = 1;
        fibonacciRecursive(n, table);
        return table;
    }

    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
