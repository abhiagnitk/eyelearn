package com.eye.learn.day003;

public class QueueADT {

    private int[] arr;
    private int head;
    private int tail;

    public QueueADT() {
        this.arr = new int[5];
        this.head = 0;
        this.tail = -1;
    }

    public void enqueue(int elem) {
        if(arr.length -1 <= tail) {
            int[] newArr = new int[tail * 2 + 2];
            System.arraycopy(arr, 0, newArr, 0, tail + 1);
            arr = newArr;
        }
        arr[++tail] = elem;
    }

    public int dequeue() {
        if(tail < head) {
            System.out.println("Queue is empty");
            return Integer.MAX_VALUE;
        }
        return arr[head++];
    }

    public boolean isEmpty() {
        if(tail < head) {
            System.out.println("Queue is empty");
            return true;
        }
        return false;
    }

    public int peek() {
        if(tail < head) {
            System.out.println("Queue is empty");
            return Integer.MAX_VALUE;
        }
        return arr[head];
    }

    public void print() {
        if(tail < head) {
            System.out.println("Queue is empty");
            return;
        }
        int i = head;
        while(i <= tail) {
            System.out.println(arr[i] + "\t");
            i++;
        }
    }
}
