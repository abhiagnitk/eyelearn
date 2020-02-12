package com.eye.learn.day003;

public class StackADT {
    private int[] arr;
    private int top;

    public StackADT() {
        this.arr = new int[5];
        this.top = -1;
    }

    public StackADT(int size) {
        this.arr = new int[size];
        this.top = -1;
    }

    public void push(int elem) {
        if(arr.length - 1 <= top) {
            System.out.println("Increasing stack size");
            int[] newArr = new int[top * 2 + 2];
            System.arraycopy(arr, 0, newArr, 0, top + 1);
            arr = newArr;
        }
        System.out.println("top is " +top);
        arr[++top] = elem;
    }

    public int pop() {
        if(top == -1) {
            System.out.println("Stack is Empty");
            return Integer.MIN_VALUE;
        }
        int poppedElement = arr[top--];
        return poppedElement;
    }

    public boolean isEmpty() {
        if(top == -1)
            return true;
        return false;
    }

    public int peek() {
        if(top == -1) {
            System.out.println("Stack is Empty");
            return Integer.MIN_VALUE;
        }
        int poppedElement = arr[top];
        return poppedElement;
    }

    public void print() {
        if(top == -1) {
            System.out.println("Stack is Empty");
            return;
        }
        int i = top;
        while(i >= 0) {
            System.out.print(arr[i] + "\t");
            i--;
        }
        System.out.println();
    }
}
