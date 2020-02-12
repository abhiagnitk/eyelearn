package com.eye.learn.day003;

import java.util.Scanner;
import java.util.Stack;

public class QueueRunner {

    public static void main(String args[]) {
        QueueADT queue = new QueueADT();

        while(true) {
            System.out.println("Enter your choice :");
            System.out.println("1)Enqueue \t 2)Dequeue \t " +
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
                    queue.enqueue(n1);
                    break;

                case 2:
                    System.out.println(queue.dequeue());
                    break;

                case 3:
                    System.out.println(queue.isEmpty());
                    break;

                case 4:
                    System.out.println(queue.peek());
                    break;

                case 5:
                    queue.print();
                    break;

                default:
                    System.out.println("Incorrect entry. Try Again");
                    break;
            }
        }
    }

}

