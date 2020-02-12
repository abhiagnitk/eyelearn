package com.eye.learn.day003;

import java.util.Scanner;

public class LinkedListRunner {

    public static void main(String[] args) {

        LinkedListADT list = new LinkedListADT();

        while(true) {
            System.out.println("Enter your choice :");
            System.out.println("1)Insert front \t 2)Insert Last \t " +
                    "3)Delete Front \t 4)Delete last \t " +
                    "5)Print \t 6)ReverseRecursive \t " +
                    "7)Reverse Iterative \t 0)Terminate");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            switch (n) {

                case 0:
                    System.out.println("Terminating");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("Enter a number");
                    int n1 = sc.nextInt();
                    list.insertHead(n1);
                    break;

                case 2:
                    System.out.println("Enter a number");
                    int n2 = sc.nextInt();
                    list.insertTail(n2);
                    break;

                case 3:
                    list.deleteHead();
                    break;

                case 4:
                    list.deleteTail();
                    break;

                case 5:
                    list.print();
                    break;

                case 6:
                    list.reverseRec();
                    break;

                case 7:
                    list.reverseIterative();
                    break;

                default:
                    System.out.println("Incorrect entry. Try Again");
                    break;
            }
        }
    }
}
