package com.eye.learn.day003;

import java.util.Scanner;
import java.util.Stack;

public class StackRunner {

    public static void main(String args[]) {
        StackADT stack = new StackADT();

        while(true) {
            System.out.println("Enter your choice :");
            System.out.println("1)Push \t 2)Pop \t " +
                    "3)isEmpty \t 4)Top \t " +
                    "5)Print \t0)Terminate");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            switch(n) {

                case 0:
                    System.out.println("Terminating");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("Enter a number");
                    int n1 = sc.nextInt();
                    stack.push(n1);
                    break;

                case 2:
                    System.out.println(stack.pop());
                    break;

                case 3:
                    System.out.println(stack.isEmpty());
                    break;

                case 4:
                    System.out.println(stack.peek());
                    break;

                case 5:
                    stack.print();
                    break;

                default:
                    System.out.println("Incorrect entry. Try Again");
                    break;
            }
        }
    }

}
